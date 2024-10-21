package was.httpserver.servlet.annotation;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 애노테이션 기반의 컨트롤러를 처리할 수 있는 서블릿
// 리팩토링: 1) 성능 최적화 2) 중복 매핑 문제 해결
public class AnnotationServletV3 implements HttpServlet {

    private final Map<String, ControllerMethod> pathMap;

    public AnnotationServletV3(List<Object> controllers) {
        this.pathMap = new HashMap<>();
        initializePathMap(controllers);
    }

    // 1) 성능 최적화: 최초(애플리케이션 로딩 시점)에 컨트롤러를 한번 다 읽고, 그 내부에 메서드에 적용된 @Mapping 정보(path)를 다 조회해서, 그 path 정보를 key로 하고, 그에 매핑되는 ControllerMethod(컨트롤러&메서드)를 value 로 해서 Map에 저장
    private void initializePathMap(List<Object> controllers) {
        for (Object controller : controllers) {
            Class<?> aClass = controller.getClass();
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String path = annotation.value();

                    // 2) 중복 매핑 문제 해결 (중복 경로 체크)
                    if (pathMap.containsKey(path)) {
                        ControllerMethod controllerMethod = pathMap.get(path);
                        throw new IllegalStateException("경로 중복 등록, path=" + path +
                                ", method=" + method +
                                ", 이미 등록된 메서드=" + controllerMethod.method);
                    }

                    pathMap.put(path, new ControllerMethod(controller, method));
                }
            }
        }
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        ControllerMethod controllerMethod = pathMap.get(path); // O(1) ( 사용자의 경로 요청이 오면, Map에서 바로 찾아서 실행 )
        if (controllerMethod == null) {
            throw new PageNotFoundException("request = " + path);
        }
        controllerMethod.invoke(request, response);
    }

    private static class ControllerMethod {
        private final Object controller;
        private final Method method;

        public ControllerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }

        public void invoke(HttpRequest request, HttpResponse response) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == HttpRequest.class) {
                    args[i] = request;
                } else if (parameterTypes[i] == HttpResponse.class) {
                    args[i] = response;
                } else {
                    throw new IllegalArgumentException("Unsupported parameter type: " + parameterTypes[i]);
                }
            }

            try {
                method.invoke(controller, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
