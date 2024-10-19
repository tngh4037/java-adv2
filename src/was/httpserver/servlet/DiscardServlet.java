package was.httpserver.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

public class DiscardServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        // empty
    }
}

// 참고) favicon.ico 같은 요청이 오면 아무것도 안하도록 (무시)