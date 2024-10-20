package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 리플렉션을 사용한 동적 메서드 호출
public class MethodV2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 정적 메서드 호출 - 일반적인 메서드 호출
        BasicData helloInstance = new BasicData();
        helloInstance.call(); // 이 부분은 코드를 변경하지 않는 이상, 정적이다.

        // 동적 메서드 호출 - 리플렉션 사용
        Class<? extends BasicData> helloClass = helloInstance.getClass();
        String methodName = "hello"; // 콘솔에서 사용자의 입력을 받았다고 가정

        // 메서드 이름을 변수로 변경할 수 있다.
        Method method1 = helloClass.getDeclaredMethod(methodName, String.class); // 클래스에 선언된 메서드 중, methodName 에 해당하는 메서드를 찾는다. ( 파라미터가 있는 경우, 타입을 두번째 인자에 정의 ) | 참고) 못찾으면 NoSuchMethodException 발생
        Object returnValue = method1.invoke(helloInstance, "hi"); // 첫 번쨰 인자로, 어떤 인스턴스에 있는 메서드(hello)를 호출할지 인스턴스 정보를 줘야한다. | 두 번째 인자로, 파라미터가 있는 경우 값을 넣어주면 된다. | 참고) invoke 과정에서 InvocationTargetException, IllegalAccessException 와 같은 예외가 발생할 수 있다.
        System.out.println("returnValue = " + returnValue);
    }
}

// 참고) invoke() 메서드
// 1) 인스턴스 메서드를 호출하는 경우, 클래스의 인스턴스를 제공해야 한다.
//  ㄴ ex) method1.invoke(helloInstance, "hi");
// 2) static 메서드 호출의 경우, 클래스의 인스턴스는 필요하지 않다. (이 경우 invoke() 메서드에 null을 전달하면 된다.)
//  ㄴ ex) method1.invoke(null, "hi");
