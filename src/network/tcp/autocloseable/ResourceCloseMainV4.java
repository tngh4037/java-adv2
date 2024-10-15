package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");

            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx = " + throwable);
            }

            throw new RuntimeException(e);
        } catch (CloseException e) { // 자원 정리 과정에서 CloseException이 던져지지만, try 구문에서 발생한 예외가 던져지기에 해당 catch문은 실행되지 않음.
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try ( // ★ try 구문이 끝나면 (AutoCloseable 을 구현하여 재정의한) close 호출  ( 중요! 1) try 내부 자원의 선언 순서에 역으로 close 해준다. 2) 만일 try 내부에 여러 자원이 정의되어 있는 상황에서, 특정 자원에서 예외가 발생한 경우라도, 그래도 모든 자원의 close()는 호출된다. )
                ResourceV2 resource1 = new ResourceV2("resource1");
                ResourceV2 resource2 = new ResourceV2("resource2");
        ) {
            resource1.call();
            resource2.callEx(); // CallException occurred !
            System.out.println("test");
        } catch (CallException e) { // 참고) catch 는 안해도 되는데, 실행 순서 확인을 위해서 넣어뒀다.
            System.out.println("ex: " + e);
            throw e;
        }
    }
}

// [ try-with-resources 예외 처리 프로세스 ]
// 
// 1) try 구문에서 예외가 발생하고, 자원 정리 과정(close())에서도 예외가 발생한 경우, 
// 자원정리 과정에서 발생한 예외들은 핵심 예외가 아니라고 생각하고 그 예외는 던지지 않고, 핵심 예외를 던진다. 
// 단, 핵심 예외를 로그로 찍어보면, 자원 정리 과정에서 발생한 예외도 기록된다. (Suppressed: ...) )
//
// 2) try 구문에서 예외가 발생하지 않았고, 자원 정리 과정(close())에서 예외가 발생한 경우, 자원정리 과정에서 발생한 예외가 던져진다.
//
// 3) try 구문에서 예외가 발생하지 않았고, 자원 정리 과정(close())에서, 선언된 여러 자원에서 모두 예외가 발생한 경우,
// 가장 처음 자원을 닫을때 발생한 예외가 던져진다. 단, 해당 예외를 로그로 찍어보면, 다른 자원들에서 발생한 예외들도 볼수있다. (Suppressed: ...)
//