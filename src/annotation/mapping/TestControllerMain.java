package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {

    public static void main(String[] args) {
        TestController testController = new TestController();

        Class<? extends TestController> aClass = testController.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            SimpleMapping simpleMapping = method.getAnnotation(SimpleMapping.class); // 메서드에 붙어있는 애노테이션을 조회할 수 있다. (메서드 뿐만 아니라, 클래스나 필드, 생성자에서도 조회할 수 있다.)
            if (simpleMapping != null) {
                System.out.println("[" + simpleMapping.value() + "] -> " + method);
            }
        }
    }
}
