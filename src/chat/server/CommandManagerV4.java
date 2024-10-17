package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// V3 리팩토링 ( command 가 없는 경우에 null 을 체크하고 처리해야 하는 부분이 좀 지저분하다. )
// -> null 인 상황을 처리할 객체(DefaultCommand)를 만들어서 해결 => NullObject Pattern(null일 때 어떻게 할것인가? -> null을 마치 객체(Object)처럼 처리하는 디자인 패턴)
public class CommandManagerV4 implements CommandManager {

    private static final String DELIMITER = "\\|";

    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV4(SessionManager sessionManager) {
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

        // NullObject Pattern
        Command command = commands.getOrDefault(key, defaultCommand); // key로 조회했는데 없으면 기본값(디폴트)을 반환할 수 있다.
        command.execute(args, session);
    }
}
