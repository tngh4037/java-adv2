package was.httpserver;

import was.httpserver.servlet.InternalErrorServlet;
import was.httpserver.servlet.NotFoundServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 서블릿을 관리하고 실행해주는 역할
public class ServletManager {

    private final Map<String, HttpServlet> servletMap = new HashMap<>();

    private HttpServlet defaultServlet; // 찾는 서블릿이 없는 경우 default
    private HttpServlet notFoundErrorServlet = new NotFoundServlet(); // 페이지를 못찾았을 경우
    private HttpServlet internalErrorServlet = new InternalErrorServlet(); // 내부에서 문제가 발생한 경우

    public ServletManager() {
    }

    public void add(String path, HttpServlet servlet) {
        servletMap.put(path, servlet);
    }

    public void setDefaultServlet(HttpServlet defaultServlet) {
        this.defaultServlet = defaultServlet;
    }

    public void setNotFoundErrorServlet(HttpServlet notFoundErrorServlet) {
        this.notFoundErrorServlet = notFoundErrorServlet;
    }

    public void setInternalErrorServlet(HttpServlet internalErrorServlet) {
        this.internalErrorServlet = internalErrorServlet;
    }

    public void execute(HttpRequest request, HttpResponse response) throws IOException {
        try {
            
            HttpServlet servlet = servletMap.getOrDefault(request.getPath(), defaultServlet);
            if (servlet == null) { // 디폴트도 없는 경우 (지정하지 않은 경우)
                throw new PageNotFoundException("request url= " + request.getPath());
            }
            servlet.service(request, response);
            
        } catch (PageNotFoundException e) { // URL 요청 경로를 servletMap 에서 찾을 수 없고, defaultServlet 도 없는 경우
            e.printStackTrace();
            notFoundErrorServlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            internalErrorServlet.service(request, response);
        }
    }
}
