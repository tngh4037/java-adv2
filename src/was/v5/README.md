(커맨드 패턴을 이용해서) HTTP 서버와 서비스 개발을 위한 로직을 명확하게 분리
- HTTP 서버와 관련된 부분 - was.httpserver 패키지
  - HttpServer , HttpRequestHandler , HttpRequest , HttpResponse
  - HttpServlet , HttpServletManager
  - was.httpserver.servlet 패키지
    - InternalErrorServlet , NotFoundServlet , DiscardServlet
- 서비스 개발을 위한 로직 - v5.servlet 패키지
  - HomeServlet
  - Site1Servlet
  - Site2Servlet
  - SearchServlet

이후에 다른 HTTP 기반의 프로젝트를 시작해야 한다면, HTTP 서버와 관련된 was.httpserver 패키지의 코드를 그대로 재사용하면 된다. 그리고 해당 서비스에 필요한 서블릿을 구현하고, 서블릿 매니저에 등록한 다음에 서버를 실행하면 된다.

여기서 중요한 부분은 새로운 HTTP 서비스(프로젝트)를 만들어도 was.httpserver 부분의 코드를 그대로 재사용 할 수 있고, 또 전혀 변경하지 않아도 된다는 점이다.
