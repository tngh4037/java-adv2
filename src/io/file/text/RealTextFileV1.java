package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Files로 파일에서 문자 읽기
public class RealTextFileV1 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, StandardCharsets.UTF_8);

        // 파일에서 읽기
        String readString = Files.readString(path, StandardCharsets.UTF_8);

        System.out.println("== Read String ==");
        System.out.println(readString);
    }
}

// 참고) ReaderWriterMainV4 와 비교해보자.