package network.exception.close.normal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();
        log("소켓 연결: " + socket);

        Thread.sleep(1000); // 잠시 대기

        socket.close(); // 서버에서 먼저 종료 ( close() 호출 시, 클라이언트에게 FIN 패킷을 보낸다. )
        log("소켓 종료");
    }
}
