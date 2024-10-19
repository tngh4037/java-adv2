package was.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpServerV1 {

    private final int port;

    public HttpServerV1(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 port: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            process(socket);
        }
    }

    private void process(Socket socket) throws IOException {
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
            sleep(5000); // 응답 정보가 너무 빨리 생성된다. 서버 처리 시간이 걸린다고 생각하고 sleep 을 주었다.
            responseToClient(writer);
            log("HTTP 응답 전달 완료");
        }
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

    private void responseToClient(PrintWriter writer) {
        // 웹 브라우저에 전달하는 내용
        String body = "<h1>Hello World</h1>";

        int length = body.getBytes(StandardCharsets.UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n"); // 한줄 띄워야 함. (header, body 구분 라인)
        sb.append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(sb);
        writer.flush(); // PrintWriter 생성 시, autoFlush 를 false 로 했기에, 자동으로 flush 되지 않는다. 따라서 flush() 를 해줘야 전송된다.
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

// 참고) /favicon.ico
// - /favicon.ico 는 웹사이트의 파비콘(favicon)을 요청하는 URL이다.
//   ㄴ 파비콘은 웹사이트의 아이콘으로, 브라우저의 탭, 즐겨찾기 목록, 주소 표시줄 등에서 사이트를 나타내는 작은 이미지이다.
//   ㄴ 일반적으로 16x16 또는 32x32 픽셀 크기의 아이콘 파일이다.
// - 브라우저는 페이지를 로드할 때 자동으로 /favicon.ico를 요청하여 해당 웹사이트의 파비콘을 가져온다.
// - 만약 서버에 이 파일이 존재하지 않으면 404 오류가 발생할 수 있다.
// - 웹사이트에 파비콘을 설정하려면, 루트 디렉토리에 favicon.ico 파일을 두거나 HTML 문서의 <head> 섹션에 다음과 같은 링크를 추가할 수 있다.
//   ㄴ <link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
// 정리)
// - HTML 문서에 별다른 코드가 없을 경우, 브라우저는 기본적으로 /favicon.ico를 요청한다.
//  ㄴ 만약 직접 지정하고자 하는 경우, <link rel="icon" href="" type="" /> 태그를 사용하면 된다. 해당 태그는 웹페이지의 파비콘(favicon)을 지정하는 태그이다.
//

// [ HttpServerV1 문제점 ]
// - 현재 서버는 한 번에 하나의 요청만 처리할 수 있다. ( 첫 번째 요청이 모두 처리되고 나서 두 번째 요청이 처리 )
