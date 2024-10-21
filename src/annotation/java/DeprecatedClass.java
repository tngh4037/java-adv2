package annotation.java;

public class DeprecatedClass {

    public void call1() {
        System.out.println("DeprecatedClass.call1");
    }

    @Deprecated // 웬만하면 쓰지마. 향후 문제가 발생할 수 있다. ( 그런데 남겨는 둘게 )
    public void call2() {
        System.out.println("DeprecatedClass.call2");
    }

    @Deprecated(since = "2.4", forRemoval = true) // since: 언제부터 Deprecated 된건지 버전 정보, forRemoval: 미래 버전에 코드가 제거될 예정 여부 ( 즉, 강력하게 사용하지 말라는 뜻 )
    public void call3() {
        System.out.println("DeprecatedClass.call3");
    }
}
