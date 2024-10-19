package was.httpserver;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

// ex) HTTP 응답 메시지
// HTTP/1.1 200 OK
// Content-Type: text/html
// Content-Length: 20
//
// <h1>Hello World</h1>
public class HttpResponse {

    private final PrintWriter writer;
    private int statusCode = 200; // 참고) default를 지정해두었다.
    private final StringBuilder bodyBuilder = new StringBuilder();
    private String contentType = "text/html; charset=UTF-8"; // 참고) default를 지정해두었다.

    public HttpResponse(PrintWriter writer) {
        this.writer = writer;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void writeBody(String body) {
        bodyBuilder.append(body);
    }

    public void flush() {
        int contentLength = bodyBuilder.toString().getBytes(StandardCharsets.UTF_8).length;

        writer.println("HTTP/1.1 " + statusCode + " " + getReasonPhase(statusCode));
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + contentLength);
        writer.println();
        writer.println(bodyBuilder);
        writer.flush();
    }

    private String getReasonPhase(int statusCode) {
        switch (statusCode) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown Status";
        }
    }
}
