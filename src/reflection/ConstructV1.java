package reflection;

import java.lang.reflect.Constructor;

// 생성자 탐색
public class ConstructV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        System.out.println("===== constructors() =====");
        Constructor<?>[] constructors = aClass.getConstructors(); // 오직 자신의 생성자만 (only public)
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("===== constructors() =====");
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors(); // 오직 자신의 생성자만 (전부)
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }
}

// 참고) 생성자는 상속이라는 개념이 없다. (상속되는게 X)
