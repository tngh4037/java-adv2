package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// [ 문자 다루기 ] - OutputStreamWriter / InputStreamReader
public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("write String: " + writeString);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(TextConst.FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

        osw.write(writeString); // (StandardCharsets.UTF_8 문자표를 사용해서) writeString 을 바이트 배열로 변환한 다음에 fos 에 전달해준다.
        osw.close();


        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(TextConst.FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

        StringBuilder content = new StringBuilder();
        int ch;
        while ((ch = isr.read()) != -1) { // 참고) isr.read()는 char 를 반환한다. ( 실제 반환 타입은 int 형이므로 char 형으로 캐스팅해서 사용하면 된다. ( 실제 반환 타입이 int인 이유? 자바의 char 형은 파일의 끝인 -1 을 표현할 수 없으므로 대신 int 를 반환한다. 따라서 char 가 반환된다고 생각하자. (물론 캐스팅이 필요할 수 있지만) ) )
            content.append((char) ch);
        }
        isr.close();

        System.out.println("read String: " + content);
    }
}

// 참고) isr.read() 메서드는 바이트를 읽고, 그것을 char로 변환하여 반환합니다.
// 만약 읽어온 바이트가 3바이트(한글)로 인코딩된 경우, 이 메서드는 해당 바이트들을 하나의 char로 변환하여 반환합니다.
