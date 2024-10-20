package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

// 필드 탐색
public class FieldV1 {

    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== fields() =====");
        Field[] fields = helloClass.getFields(); // public 만 + 상속 포함
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        System.out.println("===== declaredFields() =====");
        Field[] declaredFields = helloClass.getDeclaredFields(); // 접근제어자 상관없음 + 상속 미포함
        for (Field field : declaredFields) {
            System.out.println("field = " + field);
        }
    }
}
