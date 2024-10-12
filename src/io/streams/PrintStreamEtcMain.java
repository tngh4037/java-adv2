package io.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// [ 기타 스트림 ] - PrintStream
public class PrintStreamEtcMain {

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt");
        PrintStream printStream = new PrintStream(fos); // PrintStream 스트림을 통해서 나가는 출력은 파일에 저장된다.
        printStream.println("hello java!");
        printStream.println(10); // 숫자로 넣더라도 String 으로 변환된다.
        printStream.println(true); // boolean 으로 넣더라도 String 으로 변환된다.
        printStream.printf("hello %s", "world");
        printStream.close();
    }
}

// PrintStream 을 사용하면 마치 콘솔에 출력하는 것 처럼 파일이나 다른 스트림에 문자를 출력할 수 있다.
