package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

// 하나씩 쓰기
public class CreateFileV1 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < BufferedConst.FILE_SIZE; i++) { // 10MB 크기의 파일 생성
            fos.write(1);
        }
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 89초 걸림...! ( 10MB 만드는데 이렇게 오래걸린다. )
    }
}
