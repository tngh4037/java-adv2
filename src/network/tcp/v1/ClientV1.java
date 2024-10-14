package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class ClientV1 {

    private static final int PORT = 12345; // 서버 포트

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        // 네트워크 연결을 하려면 자바에서는 소켓이라는 객체가 필요하다. (참고. new Socket(...) 호출 시, 클라이언트는 지정한 호스트와 포트에 대한 연결을 시도한다. ) => 참고) 소켓 생성시 나(클라이언트)의 포트를 자동으로 하나 할당해준다.
        Socket socket = new Socket("localhost", PORT); // 내 컴퓨터에 있는 12345번 포트에 TCP/IP를 통해 접속(연결)한다. 참고) 내 PC에서 프로그램 2개를 실행시켜 놓고, 내 컴퓨터 안에서 TCP/IP 통신하도록 했다.

        // socket 에 있는 Stream 을 통해 외부와 통신할 수(데이터를 주고받을 수) 있다.
        DataInputStream input = new DataInputStream(socket.getInputStream()); // socket.getOutputStream(); // 외부에 데이터를 보낼때
        DataOutputStream output = new DataOutputStream(socket.getOutputStream()); // socket.getInputStream(); // 외부에서 전달된 데이터를 받을때
        log("소켓 연결: " + socket);

        // 서버에게 문자 보내기
        String toSend = "Hello";
        output.writeUTF(toSend);
        log("client -> server: " + toSend);

        // 서버로부터 문자 받기
        String received = input.readUTF(); // 참고) 안오면 대기한다. (블로킹)
        log("client <- server: " + received);

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
    }

}
