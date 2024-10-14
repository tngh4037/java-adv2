package network.tcp.autocloseable;

public class ResourceCloseMainV2 {

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
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx(); // CallException occurred !
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            if (resource2 != null) { // 참고) 만약 위에서 객체를 생성 도중에 예외가 발생했다면 resource2 는 null 이 된다. 따라서 null.closeEx(); -> NPE 가 발생하므로 null 체크를 해주었다.
                resource2.closeEx(); // CloseException occurred ! ( 자원을 닫는 도중에 예외 발생 )
            }
            if (resource1 != null) { // 호출 안됨
                resource1.closeEx();
            }
        }
    }
}

// 문제점)
// 1) 자원 정리중에 예외가 발생하는 문제: 자원을 정리하다가 특정 자원 정리에서 예외 발생시, 그 밑의 자원들은 호출되지 않음. ( resource1.closeEx();은 호출X )
// 2) 핵심 예외가 바뀌는 문제: resource2.callEx(); 에 의해 CallException 이 발생했지만, finally 구문에서 CloseException 이 발생했다.
//   ㄴ 그러면 바깥으로 CallException(핵심예외) 이 아닌, CloseException(부가예외) 이 던져진다.
//   ㄴ 개발자에게 중요한 예외는 핵심 예외다. ( CallException 발생시 catch 문에서 환불처리 로직을 넣어놨는데,,, CallException 가 발생했음에도, 예외가 전환되어 문제가 발생할 수 있다. )
//
// 해결)
// - 자원 정리의 코드에서 try-catch를 사용해서 자원 정리 중에 발생하는 예외를 잡아서 처리해보자. ( finally 블럭에서 각각의 자원을 닫을 때도, 예외가 발생하면 예외를 잡아서 처리하도록 하자. )
//