package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() throws IOException {
        try (
                socket;
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
        ) {
            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 응답 생성중...");
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) { // ex) /search?q=hello
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }

            log("HTTP 응답 전달 완료");
        }
    }

    private void home(PrintWriter writer) {
        writer.println("HTTP1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8"); // 참고) 나의 Content-Type은 html이고, 인코딩은 UTF-8로 되어있어.
        // 참고) 원칙적으로 Content-Length도 계산해서 전달해야 하지만, 예제를 단순하게 설명하기 위해 생략했다.
        writer.println();

        writer.println("<h1>home</h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'>site1</a></li>");
        writer.println("<li><a href='/site2'>site2</a></li>");
        writer.println("<li><a href='/search?q=hello'>검색</a></li>");
        writer.println("</ul>");
        writer.flush();
    }

    private void site1(PrintWriter writer) {
        writer.println("HTTP1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();

        writer.println("<h1>site1</h1>");
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        writer.println("HTTP1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();

        writer.println("<h1>site2</h1>");
        writer.flush();
    }

    // GET /search?q=안녕 HTTP/1.1
    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2); // 참고) "q=" 이후의, 첫 공백(" ") 문자의 발생 위치
        String query = requestString.substring(startIndex + 2, endIndex); // hello
        String decode = URLDecoder.decode(query, StandardCharsets.UTF_8);

        writer.println("HTTP1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();

        writer.println("<h1>Search</h1>");
        writer.println("<ul>");
        writer.println("<li>query: " + query + "</li>"); // %EC%95%88%EB%85%95
        writer.println("<li>decode: " + decode + "</li>"); // 안녕
        writer.println("</ul>");
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        writer.println("HTTP1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();

        writer.println("<h1>404 페이지를 찾을 수 없습니다.</h1>");
        writer.flush();
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) { // HTTP 요청 메시지를 읽어서 -> string 으로 변환
            if (line.isEmpty()) {
                break;
            }

            sb.append(line).append("\n"); // 참고) reader.readLine() 을 하게되면 엔터가 제거되기에, 마지막에 엔터(\n) 을 넣어주었다.
        }

        return sb.toString();
    }

}
