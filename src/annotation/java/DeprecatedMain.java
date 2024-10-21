package annotation.java;

public class DeprecatedMain {

    public static void main(String[] args) {
        System.out.println("DeprecatedMain.main");
        DeprecatedClass dc = new DeprecatedClass();
        dc.call1();
        dc.call2(); // IDE 경고
        dc.call3(); // IDE 경고(심각)
    }
}

// 참고) @Deprecated 는 컴파일 시점에 경고를 나타내지만, 프로그램은 작동한다.
