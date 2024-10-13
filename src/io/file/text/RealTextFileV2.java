package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

// Files로 파일에서 문자 읽기 - 라인 단위로 읽기
public class RealTextFileV2 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, StandardCharsets.UTF_8); // 참고) 마지막 인자에 StandardOpenOption.APPEND 를 적용할 수도 있다. -> 기존 내용이 있다면 추가

        // 파일에서 읽기
        // 1) 라인을 구분해서 파일의 내용을 리스트에 한 번에 담아줌
        // System.out.println("== Read String ==");
        // List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); // 라인을 구분해서 리스트에 한 번에 담아줌
        // for (int i = 0; i < lines.size(); i++) {
        //     System.out.println((i + 1) + ": " + lines.get(i));
        // }

        // 2) readAllLines 는 한 번에 담아버리기 때문에(자바 메모리에 올려버리기 때문에), 용량이 크면 OOM이 발생할 여지가 있다.
        // 한 줄씩 읽어서 처리하고자 하는 경우(파일을 한 줄 단위로 메모리에 올림), 아래와 같은 방법을 사용하자. ( Files.lines(path) )  => 파일을 한 줄 단위로 나누어 읽고, 메모리 사용량을 줄이고 싶은 경우
        System.out.println("== Read String ==");
        Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8);
        lineStream.forEach(line -> System.out.println(line));
        lineStream.close();
    }
}

// 참고) ReaderWriterMainV4 와 비교해보자.