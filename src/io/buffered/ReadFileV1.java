package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

// 하나씩 읽기
public class ReadFileV1 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(BufferedConst.FILE_NAME);
        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data;
        while ((data = fis.read()) != -1) {
            fileSize++;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 10MB 읽는데 40초 걸림
    }
}
