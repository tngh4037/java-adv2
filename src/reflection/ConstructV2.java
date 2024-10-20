package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 생성자 활용 (객체 생성)
public class ConstructV2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        Constructor<?> constructor = aClass.getDeclaredConstructor(String.class); // String을 매개변수로 받는 생성자 조회
        constructor.setAccessible(true); // private 이므로 접근 허용해줘야 함.
        Object instance = constructor.newInstance("hello");// 생성자를 통한 객체 생성 (인자 값으로 생성자의 매개변수를 넣어준다.)
        System.out.println("instance = " + instance); // 실제 객체가 생성된다. (따라서 BasicData로 캐스팅 해서 쓰면 된다.)

        Method method1 = aClass.getDeclaredMethod("call");
        method1.invoke(instance); // 동적으로 생성한 인스턴스의 메서드 call
    }
}
