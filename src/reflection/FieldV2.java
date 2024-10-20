package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

// 필드 값 변경
public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "userA", 20);
        System.out.println("기존 이름 = " + user.getName());

        Class<? extends User> aClass = user.getClass();
        Field nameField = aClass.getDeclaredField("name");

        // private 필드이기에 접근을 허용하도록 해야한다. (setAccessible(true) 기능은 Method 에서도 제공하므로 private 메서드도 이렇게 호출 가능) | 참고) 이걸 허용하지 않고 set을 하면, java.lang.IllegalAccessException 발생
        nameField.setAccessible(true);
        nameField.set(user, "userB"); // user 인스턴스에 있는 nameField 의 값을 "userB" 로 변경하겠다.
        System.out.println("변경된 이름 = " + user.getName());
    }
}

// 리플렉션을 사용하면, private 필드에 접근해서 값을 변경할 수 있다.
