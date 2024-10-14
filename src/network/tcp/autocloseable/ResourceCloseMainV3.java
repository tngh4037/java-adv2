package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

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
            if (resource2 != null) {
                try {
                    resource2.closeEx(); // CloseException occurred !
                } catch (CloseException e) {
                    // close() 에서 발생한 예외는 버린다. 필요하면 로깅 정도 ( 참고. 보통 자원을 닫다가 발생한 예외는 개발자가 처리할 수 있는 방법이 별로 없다. 그래서 개발자가 인지할 수 있도록 로깅 정도만 남기는 걸로 충분하다. )
                    System.out.println("close ex: " + e);
                }
            }

            if (resource1 != null) {
                try {
                    resource1.closeEx(); // CloseException occurred !
                } catch (CloseException e) {
                    // close() 에서 발생한 예외는 버린다.
                    System.out.println("close ex: " + e);
                }
            }
        }
    }
}

// 아쉬운 부분)
// - resource 변수를 선언하면서 동시에 할당할 수 없음( try , finally 코드 블록과 변수 스코프가 다른 문제)
// - catch 이후에 finally 호출, 자원 정리가 조금 늦어진다.
// - 개발자가 실수로 close() 를 호출하지 않을 가능성
// - 개발자가 close() 호출 순서를 실수, 보통 자원을 생성한 순서와 반대로 닫아야 함
//
// 보완)
// - 자원 정리 때문에 고통 받는 이런 문제를 한번에 해결하는 것 => try-with-resources 구문
//