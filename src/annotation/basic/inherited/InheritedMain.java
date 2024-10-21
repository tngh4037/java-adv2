package annotation.basic.inherited;

import annotation.basic.inherited.extend.Child;
import annotation.basic.inherited.extend.Parent;
import annotation.basic.inherited.interfaces.TestInterface;
import annotation.basic.inherited.interfaces.TestInterfaceImpl;

import java.lang.annotation.Annotation;

public class InheritedMain {

    public static void main(String[] args) {
        print(Parent.class);
        print(Child.class);
        print(TestInterface.class);
        print(TestInterfaceImpl.class);
    }

    private static void print(Class<?> clazz) { // 참고) Class 를 파라미터로 받을 때, 관례상 파라미터명을 clazz로 쓴다. ( class는 자바 키워드 이므로 이름으로 사용할 수 없다. )
        System.out.println("class: " + clazz);
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(" - " + annotation.annotationType().getSimpleName());
        }
        System.out.println();
    }

}
