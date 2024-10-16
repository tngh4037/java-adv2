package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

// TCP/IP 에서 연결을 "정상적으로 종료"하려면,
// (서버 <-> 클라이언트) 둘 다 FIN 패킷을 주고 받아야 한다.
public class NormalCloseClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);

        InputStream input = socket.getInputStream();

        readByInputStream(input, socket);
        // readByBufferedReader(input, socket);
        // readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read(); // 서버에서 socket.close() 를 통해 연결을 끊어버리면(=상대방이 나에게 FIN 패킷을 보낸것), 이제 더이상 나는 받을 메시지가 없게 된다. 따라서 이 경우 EOF인 (-1) 이 반환된다.
        log("read = " + read);

        if (read == -1) { // 나도 자원 정리하고, 마지막에 socket.close(); 로 서버에게 FIN 패킷 전송
            input.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine(); // 서버에서 socket.close() 를 통해 연결을 끊어버리면(=상대방이 나에게 FIN 패킷을 보낸것), 이제 더이상 나는 받을 메시지가 없게 된다. 따라서 이 경우 EOF인 (null) 이 반환된다.
        log("readString = " + readString);

        if (readString == null) { // 나도 자원 정리하고, 마지막에 socket.close(); 로 서버에게 FIN 패킷 전송
            br.close();
            socket.close();
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);

        try {
            String s = dis.readUTF(); // 서버에서 socket.close() 를 통해 연결을 끊어버리면(=상대방이 나에게 FIN 패킷을 보낸것), 이제 더이상 나는 받을 메시지가 없게 된다. 따라서 이 경우 (DataInputStream 에서는) EOFException 이 반환된다.
        } catch (EOFException e) {
            log(e);
        } finally { // 나도 자원 정리하고, 마지막에 socket.close(); 로 서버에게 FIN 패킷 전송
            dis.close();
            socket.close();
        }
    }

}

// 참고) 클라이언트는 서버의 메시지를 3가지 방법으로 읽는다.
// - read() : 1byte 단위로 읽음  |  EOF의 의미를 숫자 -1로 반환한다.
// - readLine() : 라인 단위로 String 으로 읽음  |  BufferedReader() 는 문자 String 을 반환한다. 따라서 -1을 표현할 수 없다. 대신에 null 을 반환한다.
// - readUTF() : DataInputStream 을 통해 String 단위로 읽음  |  DataInputStream 은 이 경우 EOFException 을 던진다.
// - 참고로 각각의 상황에 따라 EOF를 해석하는 방법이 다르다.
//
// 중요) 중요한 점은 EOF가 발생하면 상대방이 "FIN 메시지를 보내면서 소켓 연결을 끊었다는 뜻"이다.
// 따라서 이 경우 소켓에 다른 작업을 하면 안되고, FIN 메시지를 받은 클라이언트도 close() 를 호출해서 상대방에 FIN 메시지를 보내고 소켓 연결을 끊어야 한다.
// 이렇게 하면 서로 FIN 메시지를 주고 받으면서 TCP 연결이 정상 종료된다. ( 둘다 FIN 패킷을 받으면 정상적으로 TCP 연결에 대한 정보가 정상적으로 정리된다. )