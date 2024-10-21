package was.v9;

import was.httpserver.HttpServer;
import was.httpserver.HttpServlet;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV3;
import was.v8.SearchControllerV8;
import was.v8.SiteControllerV8;

import java.io.IOException;
import java.util.List;

public class ServerMainV9 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteControllerV9(), new SearchControllerV9());
        HttpServlet annotationServlet = new AnnotationServletV3(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);
        servletManager.add("/favicon.ico", new DiscardServlet()); // 참고) 어차피 내부에서 아무것도 안하고, DiscardServlet 를 이전에 만들어 뒀으니, /favicon.ico 에 대해서는 직접 등록해주었다. ( 직접 등록 안하고, SearchControllerV7 등에서 처리해줘도 된다. )

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
