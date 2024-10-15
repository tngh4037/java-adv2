package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

// 참고) ClientV4 -> try-with-resources 을 이용한 자원 정리
public class ClientV5 {

    private static final int PORT = 12345; // 서버 포트

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        try (
                Socket socket = new Socket("localhost", PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
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
        }
    }

}

// 참고) Socket, DataInputStream, DataOutputStream 은 내부적으로 다 AutoClosable 을 구현하고 있다.