package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();
        connectionRefused();
    }

    private static void unknownHostEx1() throws IOException {
        try {
            new Socket("999,999,999,999", 80); // 없는 대역의 IP에 접근
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void unknownHostEx2() throws IOException {
        try {
            new Socket("google.gogo", 80); // DNS에 없는 도메인의 경우
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException { // 연결이 거절되는 경우
        try {
            Socket socket = new Socket("localhost", 45678); // 미사용 포트
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
