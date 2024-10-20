package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {

    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== methods() =====");
        Method[] methods = helloClass.getMethods(); // getMethods(): 클래스에 정의된 모든 메서드 정보 조회 ( 참고. 1) public 메서드만 대상 2) 상속 관계에 있는 경우 부모도 포함 )
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("===== declaredMethods() =====");
        Method[] declaredMethods = helloClass.getDeclaredMethods(); // getDeclaredMethods(): 클래스에 정의된 모든 메서드 정보 조회 ( 참고. 1) 접근제한자 상관없이 포함 2) 상속관계는 포함되지 않음(내가 가진 것만) )
        for (Method method : declaredMethods) {
            System.out.println("declaredMethod = " + method);
        }
    }
}
