v3 버전 + SocketCloseUtil 을 이용한 자원 정리


---


참고)
- 기존 v3 버전 코드의 문제는 클라이언트를 직접 종료하면 서버의 Session 에  java.io.EOFException(Windows의 경우, java.net.SocketException: Connection reset) 이 발생하면서 자원을 제대로 정리하지 못했다.
  - 참고) 두 예외 모두 java.io.IOException 의 자식이므로 예제 코드에서 예외를 잡을 수 있다.
- 버전 v4 의 변경한 코드에서는 서버에 접속한 클라이언트를 직접 종료해도 서버의 Session 이 연결 종료라는 메시지를 남기면서 자원을 잘 정리하는 것을 확인할 수 있다.
- 클라이언트의 연결을 직접 종료시
  - MAC: TCP 연결 정상 종료
  - 윈도우: TCP 연결 강제 종료
