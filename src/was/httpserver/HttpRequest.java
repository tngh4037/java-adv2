package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

// ex) HTTP 요청 메시지
// GET /search?q=hello HTTP/1.1
// Host: localhost:12345
// Connection: keep-alive
// ...
public class HttpRequest {

    private String method; // ex. GET, POST
    private String path; // ex. /search
    private final Map<String, String> queryParameters = new HashMap<>(); // ex. ?q=hello&data=hi
    private final Map<String, String> headers = new HashMap<>(); // ex. Host: www.google.com

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);
        // 메시지 바디는 이후에 처리
    }

    // GET /search?q=hello HTTP/1.1
    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IOException("EOF: No request line received");
        }

        String[] parts = requestLine.split(" ");
        if (parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }

        // GET
        this.method = parts[0];

        // /search
        String[] pathParts = parts[1].split("\\?");
        this.path = pathParts[0];

        // q=hello ( key1=value1&key2=value2... )
        if (pathParts.length > 1) {
            parseQueryParameters(pathParts[1]);
        }
    }

    // key1=value1&key2=value2...
    private void parseQueryParameters(String queryString) {
        String[] split = queryString.split("&");
        for (String param : split) {
            String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8); // 참고) URLDecoder.decode: ASCII가 오면 디코드 하지 않음.
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : ""; // 참고) key1= 와 같이 올수도 있음 ( 그런 경우, 웹에서는 빈 문자로 취급한다. )

            queryParameters.put(key, value);
        }
    }

    // Host: localhost:12345
    // Connection: keep-alive
    // ...
    // (공백 라인)
    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) { // 라인을 읽어들인 결과가 empty 가 아니면
            String[] headerParts = line.split(":");
            headers.put(headerParts[0].trim(), headerParts[1].trim());
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name) {
        return queryParameters.get(name);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}
