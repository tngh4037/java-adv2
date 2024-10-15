package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

// (소켓을 통해) 특정 클라이언트와 통신하기 위한 역할을 하는 객체
public class SessionV4 implements Runnable {

    private final Socket socket;

    public SessionV4(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록 안에서 선언할 수 없다.
        DataInputStream input = null;
        DataOutputStream output = null;

        try {

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // 클라이언트로 부터 문자 받기
                String received = input.readUTF(); // 블로킹 ( 참고. 만약 클라이언트 프로세스 직접 종료시, EOFException(MAC) or SocketException(Windows) 발생 )
                log("client -> server: " + received);

                if (received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }

        } catch (IOException e) {
            log(e);
        } finally {
            // 자원 정리
            SocketCloseUtil.closeAll(socket, input, output);
            log("연결 종료: " + socket);
        }

    }
}
