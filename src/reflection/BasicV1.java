package reflection;

import reflection.data.BasicData;

public class BasicV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스의 메타데이터 조회 방법 3가지

        // 1. 클래스에서 찾기
        Class<BasicData> basicDataClass1 = BasicData.class;
        System.out.println("basicDataClass1 = " + basicDataClass1);

        // 2. 인스턴스에서 찾기
        BasicData basicInstance = new BasicData();
        Class<? extends BasicData> basicDataClass2 = basicInstance.getClass();
        System.out.println("basicDataClass2 = " + basicDataClass2);

        // 3. 문자로 찾기 (패키지 명 까지 다 적어줘야 함.)
        String className = "reflection.data.BasicData";
        Class<?> basicDataClass3 = Class.forName(className); // 참고) 못찾으면 ClassNotFoundException 예외 발생
        System.out.println("basicDataClass3 = " + basicDataClass3);
    }
}

// 클래스의 메타데이터는 Class 라는 클래스로 표현된다. 그리고 Class 라는 클래스를 획득하는 3가지 방법이 있다.
// 1. 클래스에서 찾기
// 2. 인스턴스에서 찾기
// 3. 문자로 찾기 (패키지 명 까지 다 적어줘야 함.)