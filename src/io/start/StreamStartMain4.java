package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

// 모든 byte 한 번에 읽기
public class StreamStartMain4 {

    // [ 전체 읽기 ]
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] readBytes = fis.readAllBytes();
        System.out.println(Arrays.toString(readBytes)); // [65, 66, 67]
        fis.close();
    }
}

// readAllBytes() 를 사용하면 스트림이 끝날 때 까지(파일의 끝에 도달할 때 까지) 모든 데이터를 한 번에 읽어올 수 있다.