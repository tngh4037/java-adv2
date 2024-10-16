package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriteHandler implements Runnable {

    private static final String DELIMETER = "|";

    private final DataOutputStream output;
    private final Client client;

    private boolean closed = false;

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMETER + username);

            while (true) {
                String toSend = scanner.nextLine(); // 블로킹 ( 참고. System.in 을 close 하면, 예외(NoSuchElementException)가 발생한다. )
                if (toSend.isEmpty()) {
                    continue;
                }

                if (toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                // "/"로 시작하면 명령어, 나머지(ex. hello, hi, world, ..)는 일반 메시지로 간주
                if (toSend.startsWith("/")) {
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("/message" + DELIMETER + toSend);
                }
            }
        } catch (IOException | NoSuchElementException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    private static String inputUsername(Scanner scanner) {
        System.out.println("이름을 입력하세요. ");
        String username;
        do {
            username = scanner.nextLine();
        } while (username.isEmpty());

        return username;
    }

    public synchronized void close() { // WriteHandler 에서 자원 정리가 필요한 것들 정리
        if (closed) {
            return;
        }

        try {
            System.in.close(); // Scanner 를 닫아줌. ( Scanner 입력 중지, 사용자의 입력을 닫음 ) -> 그렇게 되면, 위 nextLine() 에서 예외가 발생한다. | (참고로 단, 윈도우에서는 예외가 발생하지 않음.. 윈도우의 경우 System.in.close() 를 호출해도 사용자의 콘솔 입력이 닫히지 않는다. 사용자가 어떤 내용이든 입력을 해야 그 다음에 java.util.NoSuchElementException: No line found 예외가 발생하면서 대기 상태에서 빠져나올 수 있다.)   ||   따라서, 윈도우 환경에서 클라이언트와 서버가 연결된 상태에서 서버가 종료되면, 클라이언트의 WriteHandler 스레드는 살아있게 된다.
        } catch (IOException e) {
            log(e);
        }

        closed = true;
        log("writeHandler 종료");
    }
}
