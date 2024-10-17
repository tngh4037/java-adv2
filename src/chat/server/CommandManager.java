package chat.server;

import java.io.IOException;

// 클라이언트에게 전달받은 메시지(명령)를 처리하는 인터페이스 ( 구현체를 점진적으로 변경하기 위해 인터페이스로 정의했다. )
public interface CommandManager {
    void execute(String totalMessage, Session session) throws IOException;
}
