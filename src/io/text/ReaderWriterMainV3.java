package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// [ 문자 다루기 ] - FileWriter, FileReader ( OutputStreamWriter 와 InputStreamReader 를 편리하게 사용하도록 도와주는 클래스 )
public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("write String: " + writeString);

        // 파일에 쓰기
        // FileWriter fw = new FileWriter(TextConst.FILE_NAME, StandardCharsets.UTF_8); // 참고) StandardCharsets.UTF_8 생략 시, 시스템의 기본 문자 인코딩 표 적용
        FileWriter fw = new FileWriter(TextConst.FILE_NAME);
        fw.write(writeString);
        fw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(TextConst.FILE_NAME, StandardCharsets.UTF_8);
        int ch;
        while ((ch = fr.read()) != -1) {
            content.append((char) ch);
        }
        fr.close();

        System.out.println("read String: " + content);
    }
}

// 참고) FileWriter 는 내부에서 스스로 FileOutputStream 을 생성해서 사용한다. ( + FileWriter 는 OutputStreamWriter 를 상속 )
// 참고) FileReader 는 내부에서 스스로 FileInputStream 을 생성해서 사용한다. ( + FileReader 는 InputStreamReader 를 상속 )