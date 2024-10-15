package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

// 참고) ClientV3 와 동일 + SocketCloseUtil 을 이용한 자원 정리
public class ClientV4 {

    private static final int PORT = 12345; // 서버 포트

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록 안에서 선언할 수 없다.
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {

            socket = new Socket("localhost", PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("전송 문자: ");

                // 서버에게 문자 보내기
                String toSend = scanner.nextLine();
                output.writeUTF(toSend);
                log("client -> server: " + toSend);

                if (toSend.equals("exit")) {
                    break;
                }

                // 서버로부터 문자 받기
                String received = input.readUTF();
                log("client <- server: " + received);
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
