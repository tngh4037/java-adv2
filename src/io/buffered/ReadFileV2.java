package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFileV2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(BufferedConst.FILE_NAME);

        long startTime = System.currentTimeMillis();
        byte[] buffer = new byte[BufferedConst.BUFFER_SIZE];
        int fileSize = 0;
        int size;
        while ((size = fis.read(buffer)) != -1) { // 8192 바이트씩 읽어서 buffer에 넣어줌 ( 읽은 크기 반환 )
            fileSize += size;
        }
        fis.close();
        long endTime = System.currentTimeMillis();

        System.out.println("File name: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 10ms
    }
}
