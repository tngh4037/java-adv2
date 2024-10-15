package network.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class SocketCloseUtil {

    public static void closeAll(Socket socket, InputStream inputStream, OutputStream outputStream) {
        close(inputStream);
        close(outputStream);
        close(socket);
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }
}

// 참고) 자원 정리중에 예외가 발생하면 로그를 남기도록 했다.
// 참고) Socket 을 먼저 생성하고, Socket 을 기반으로 InputStream , OutputStream 을 생성하기 때문에, 닫을때는 InputStream, OutputStream 을 먼저 닫고 Socket 을 마지막에 닫아야 한다.