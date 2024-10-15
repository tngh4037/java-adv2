package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

// (소켓을 통해) 특정 클라이언트와 통신하기 위한 역할을 하는 객체
public class SessionV6 implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;

    private boolean closed = false;

    public SessionV6(Socket socket,
                     SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;

        this.sessionManager.add(this);
    }

    @Override
    public void run() {

        try {

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
            sessionManager.remove(this);
            close();
        }
    }

    // (1) 세션 종료 시(클라이언트 프로세스 종료) / 2) 서버 종료시) 호출될 수 있다.
    // 그리고 이 두 경우가 동시에 호출될 수 있으므로, 동시성 처리를 해주었다.
    public synchronized void close() {
        if (closed) { // 동시성 처리를 통해 동시에 호출되는 것을 막더라도, 이후에 호출되는 것은 자원을 중복해서 닫으면 안되므로 상태체크를 해주었다. ( 중복 호출 되는 것을 막기 위해 )
            return;
        }

        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;

        log("연결 종료: " + socket);
    }
}
