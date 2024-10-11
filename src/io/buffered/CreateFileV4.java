package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

// 한 번에 쓰기
// : 파일의 크기가 크지 않다면 간단하게 한 번에 쓰고 읽는 것도 좋은 방법이다.
// : 이 방법은 성능은 가장 빠르지만, 결과적으로 메모리를 한 번에 많이 사용하기 때문에 파일의 크기가 작아야 한다.
public class CreateFileV4 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BufferedConst.FILE_SIZE]; // 10MB
        for (int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            buffer[i] = 1;
        }
        fos.write(buffer); // 한 번에 write
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 37ms
    }
}

// 실행 시간은 8KB의 버퍼를 직접 사용한 CreateFileV2와 오차 범위 정도로 거의 비슷하다.
// 디스크나 파일 시스템에서 데이터를 읽고 쓰는 기본 단위가 보통 4KB 또는 8KB이기 때문에, 한 번에 쓴다고해서 무작정 빠른 것은 아니다.
