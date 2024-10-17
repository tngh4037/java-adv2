package chat.server.command;

import chat.server.Session;

import java.io.IOException;

// 명령어를 추상화한 "명령" 이라는 인터페이스를 만들고, 그에 대한 명령어 구현체를 생성
public interface Command {
    void execute(String[] args, Session session) throws IOException;
}
