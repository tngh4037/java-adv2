package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Modifier;
import java.util.Arrays;

// 찾은 클래스의 메타데이터로 어떤 일들을 할 수 있는지 알아보자. ( 기본 정보 탐색 )
public class BasicV2 {

    public static void main(String[] args) {
        Class<BasicData> basicData = BasicData.class;

        System.out.println("basicData.getName() = " + basicData.getName()); // 클래스 명 조회 (패키지명 포함)
        System.out.println("basicData.getSimpleName() = " + basicData.getSimpleName()); // 클래스 명 조회 (패키지명 제외)
        System.out.println("basicData.getPackage() = " + basicData.getPackage()); // 패키지 정보

        System.out.println("basicData.getSuperclass() = " + basicData.getSuperclass()); // 부모 클래스
        System.out.println("basicData.getInterfaces() = " + Arrays.toString(basicData.getInterfaces())); // 인터페이스

        System.out.println("basicData.isInterface() = " + basicData.isInterface()); // 인터페이스 인지 확인
        System.out.println("basicData.isEnum() = " + basicData.isEnum()); // Enum 인지 확인
        System.out.println("basicData.isAnnotation() = " + basicData.isAnnotation()); // Annotation 인지 확인

        int modifiers = basicData.getModifiers(); // 참고) getModifiers() 를 통해 조합된 수정자 숫자를 얻고, Modifier 를 사용해서 실제 수정자 정보를 확인할 수 있다.
        System.out.println("basicData.getModifiers() = " + modifiers); // 1
        System.out.println("isPublic = " + Modifier.isPublic(modifiers)); // true
        System.out.println("isPrivate = " + Modifier.isPrivate(modifiers)); // false
        System.out.println("isProtected = " + Modifier.isProtected(modifiers)); // false
        System.out.println("Modifier.toString() = " + Modifier.toString(modifiers)); // public
    }
}

// 참고) 수정자는 접근 제어자와 비 접근 제어자(기타 수정자)로 나눌 수 있다.
// - 접근 제어자: public , protected , default ( package-private ), private
// - 비 접근 제어자: static , final , abstract , synchronized , volatile 등
// getModifiers() 를 통해 수정자가 조합된 숫자를 얻고, Modifier 를 사용해서 실제 수정자 정보를 확인할 수 있다. (ex. public final : 17)