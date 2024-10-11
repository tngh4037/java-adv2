package io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

// BufferedXxx 사용
public class ReadFileV3 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(BufferedConst.FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis, BufferedConst.BUFFER_SIZE);
        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data;
        while ((data = bis.read()) != -1) { // 참고) BufferedInputStream 은 버퍼의 크기만큼 데이터를 미리 읽어서 버퍼에 보관해둔다. 따라서 read()를 통해 1byte씩 데이터를 조회해도, 성능이 최적화 된다.
            fileSize++;
        }
        bis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 257ms
    }
}

// ReadFileV1 보다 훨씬 빠르게 처리되었다. ( 참고로 ReadFileV2 보다는 다소 성능이 떨어지는데 그 이유는 동기화 코드가 들어있기 때문이다. )