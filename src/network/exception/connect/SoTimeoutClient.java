package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SoTimeoutClient {
    /*
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            int read = input.read(); // 서버에서 응답을 줄때까지 대기 ( ==> 기본은 무한 대기 )
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.close();
    }
    */

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(3000); // 타임아웃 시간 설정
            int read = input.read();
            System.out.println("read = " + read);

            // socket.setSoTimeout(2000); // 타임아웃 시간 설정
            // int read2 = input.read();
            // System.out.println("read2 = " + read2);
        } catch (Exception e) {
            e.printStackTrace(); // java.net.SocketTimeoutException: Read timed out
        }

        socket.close();
    }
}
