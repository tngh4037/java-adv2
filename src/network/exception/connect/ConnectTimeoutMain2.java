package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

// TCP 연결 타임아웃 - 직접 설정
public class ConnectTimeoutMain2 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket(); // 생성자 인자를 비워두면, 객체를 생성할 때 바로 연결을 시도하지 않음. ( 인자로 IP, PORT를 모두 전달하면 생성자에서 바로 TCP 연결을 시도한다. )
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 1000); // 1초 동안 연결되지 않으면 예외가 발생한다. => 이렇게 직접 타임아웃에 대한 정보를 직접 주면, 예외 발생시 SocketTimeoutException 이 발생한다.
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }
}
