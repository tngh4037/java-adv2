package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

// [ 문자 다루기 ] - BufferedWriter / BufferedReader
public class ReaderWriterMainV4 {

    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("== Write string ==");
        System.out.println(writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(TextConst.FILE_NAME, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(TextConst.FILE_NAME, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;
        while ((line = br.readLine()) != null) { // 한 줄씩 읽어들임. 파일의 끝(EOF)에 도달하면 null 을 반환한다. ( 반환 타입이 String 이기 때문에 EOF를 -1로 표현할 수 없다. 대신에 null 을 반환한다. )
            content.append(line).append("\n"); // 참고) readLine() 을 통해 라인 단위로 읽으면 라인 자체는 제거된다. 따라서 이를 구분해서 보거나 작업하려면 라인구분자를 넣어주어야 한다.
        }
        br.close();

        System.out.println("== Read string ==");
        System.out.println(content);
    }
}

// BufferedOutputStream , BufferedInputStream 과 같이 Reader , Writer 에도 버퍼 보조 기능을 제공하는 BufferedReader , BufferedWriter 클래스가 있다.
// 참고) 추가적으로 BufferedReader 는 한 줄 단위로 문자를 읽는 기능인 readLine() 을 제공한다. ( BufferedReader 에서만 제공되는 부가기능이다. )