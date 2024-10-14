package network.tcp.autocloseable;

public class ResourceV1 {

    private String name;

    public ResourceV1(String name) {
        this.name = name;
    }

    public void call() { // 정상 로직 호출
        System.out.println(name + " call");
    }

    public void callEx() throws CallException { // 비정상 로직 호출 (예외 발생 - CallException)
        System.out.println(name + " callEx");
        throw new CallException(name + " ex");
    }

    public void close() { // 정상 종료
        System.out.println(name + " close");
    }

    public void closeEx() throws CloseException { // 비정상 종료 (예외 발생 - CloseException)
        System.out.println(name + " closeEx");
        throw new CloseException(name + " ex");
    }
}
