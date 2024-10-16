package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 잠시 대기

        // client -> server: FIN(X), PUSH[1]  ( FIN 을 보내지 않고 데이터 전송 ) || 참고) (정상적인 종료의 경우) client -> server: FIN 를 보내야 함.
        output.write(1);

        // client <- server: RST ( 상대방이 FIN을 보냈으면, 나도 FIN을 보내야 한다. 그렇지 않으면 TCP/IP 규약에 어긋난다. 이 경우 서버에서 RST(당장 연결 끊으라는 신호)라는 메시지가 온다. )
        Thread.sleep(1000); // RST 메시지 전송 대기 ( RST 를 받을 때 까지 잠깐 대기 )

        try {
            int read = input.read(); // RST를 받았는데 read()를 하는 경우 ( 예외 발생 )
            System.out.println("read = " + read);
        } catch (SocketException e) {
            // (MAC) java.net.SocketException: Connection reset
            // (Windows) java.net.SocketException: 현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다.
            e.printStackTrace();
        }

        try {
            output.write(1); // RST를 받았는데 write()를 하는 경우 ( 예외 발생 )
        } catch (SocketException e) {
            // (MAC) java.net.SocketException: Broken pipe
            // (Windows) java.net.SocketException: 현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다.
            e.printStackTrace();
        }

    }
}

// RST 패킷이 도착했다는 것은 현재 TCP 연결에 심각한 문제가 있으므로 해당 연결을 더는 사용하면 안된다는 의미이다.