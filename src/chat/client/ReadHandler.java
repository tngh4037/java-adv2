package chat.client;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;

    private boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                System.out.println(received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() { // ReadHandler 에서 자원 정리가 필요한 것들 정리
        if (closed) {
            return;
        }

        // 종료 로직 필요시 작성
        closed = true;
        log("readHandler 종료");
        // ... ( 참고로 예제 코드는 단순하므로 중요한 종료 로직이 없다. )
    }
}
