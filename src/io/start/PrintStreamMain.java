package io.start;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

// 콘솔 스트림
public class PrintStreamMain {

    public static void main(String[] args) throws IOException {
        // System.out.println("hello");

        PrintStream printStream = System.out;
        byte[] bytes = "Hello\n".getBytes(StandardCharsets.UTF_8);
        printStream.write(bytes);

        // printStream.println("hello"); // 부가 기능 ( PrintStream 이 자체적으로 제공하는 추가 기능이다. )
    }
}

// System.out 이 사실은 PrintStream 이다. 이 스트림은 OutputStream 를 상속받는다.
// 이 스트림은 자바가 시작될 때 자동으로 만들어진다. 따라서 우리가 직접 생성하지 않는다.
//
// - write(byte[]) : OutputStream 부모 클래스가 제공하는 기능이다.
// - println(String) : PrintStream 이 자체적으로 제공하는 추가 기능이다.
