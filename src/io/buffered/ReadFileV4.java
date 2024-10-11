package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

// 한 번에 읽기
public class ReadFileV4 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(BufferedConst.FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] bytes = fis.readAllBytes();
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + bytes.length / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 15ms
    }
}

// 실행 시간은 8KB의 버퍼를 사용한 ReadFileV2와 오차 범위 정도로 거의 비슷하다.
// 참고) readAllBytes() 는 자바 구현에 따라 다르지만 보통 4KB, 8KB, 16KB 단위로 데이터를 읽어들인다.
