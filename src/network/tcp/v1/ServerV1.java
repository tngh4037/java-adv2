package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT); // 서버는 ServerSocket(Java에서 서버 측 소켓을 생성하는 클래스) 라는 특별한 소켓이 필요하다. ( new ServerSocket(PORT); 를 통해 12345번 포트로 서버가 뜬다. ( 서버가 12345 포트로 서버 소켓을 열어둔다. 클라이언트는 이제 12345 포트로 서버에 접속할 수 있다. ) )
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        Socket socket = serverSocket.accept(); // 클라이언트와의 통신을 위한 (해당 클라이언트의 접속 정보를 기반으로) 소켓을 생성해준다. ( 이 소켓을 통해서 클라이언트와 서버가 통신. )
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // 클라이언트로 부터 문자 받기
        String received = input.readUTF();
        log("client -> server: " + received);

        // 클라이언트에게 문자 보내기
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}

// 참고) 통신을 할 떄는 클라이언트도 소켓을 가지고 있고, 서버도 소캣을 가지고 있어야 한다.
//   ㄴ 클라이언트와 서버간의 통신은 소켓을 통해서 이루어진다.