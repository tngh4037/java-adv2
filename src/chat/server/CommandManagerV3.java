package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// V2 리팩토링 (커맨드 패턴(각각의 명령어 하나하나를 클래스로 구분하고, 다형성을 이용해서 실행할 수 있도록 하는 패턴) 적용)
public class CommandManagerV3 implements CommandManager {

    private static final String DELIMITER = "\\|";

    private final Map<String, Command> commands = new HashMap<>();

    public CommandManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        String key = args[0];

        Command command = commands.get(key);
        if (command == null) {
            session.send("처리할 수 없는 명령어 입니다: " + totalMessage);
            return;
        }

        command.execute(args, session);
    }
}
