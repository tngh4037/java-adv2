package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

// (소켓을 통해) 특정 클라이언트와 통신하기 위한 역할을 하는 객체
public class SessionV5 implements Runnable {

    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (
                socket;
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

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
        }

        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
