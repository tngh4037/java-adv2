package network.tcp.autocloseable;

public class ResourceCloseMainV1 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = new ResourceV1("resource1");
        ResourceV1 resource2 = new ResourceV1("resource2");

        resource1.call();
        resource2.callEx(); // CallException occurred !

        System.out.println("자원 정리"); // 호출 안됨
        resource2.closeEx();
        resource1.closeEx();
    }
}

// 문제점)
// 로직에서 예외 발생시 자원 정리 구문이 호출되지 않음. ( 예외가 발생하면 자원이 정리되지 않는다. )

// 해결)
// finally 코드 블록을 사용해서 자원을 닫는 코드가 항상 호출되도록 하자.