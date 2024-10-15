package network.tcp.autocloseable;

// 참고) try-with-resources 를 사용하려면 AutoCloseable 인터페이스가 있어야 한다.
public class ResourceV2 implements AutoCloseable {

    private String name;

    public ResourceV2(String name) {
        this.name = name;
    }

    public void call() { // 정상 로직 호출
        System.out.println(name + " call");
    }

    public void callEx() throws CallException { // 비정상 로직 호출 (예외 발생 - CallException)
        System.out.println(name + " callEx");
        throw new CallException(name + " ex");
    }

    @Override
    public void close() throws CloseException {
        System.out.println(name + " close");
        throw new CloseException(name + " ex");
    }
}
