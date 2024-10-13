package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// [ 파일 복사 V1 ]
// FileInputStream 에서 readAllBytes 를 통해 한 번에 모든 데이터를 읽고 write(bytes) 를 통해 한 번에 모든 데이터를 저장한다.
// => 자바가 copy.dat 파일의 데이터를 자바 프로세스가 사용하는 메모리에 불러온다. 그리고 메모리에 있는 데이터를 copy_new.dat 에 전달한다.
public class FileCopyMainV1 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("temp/copy.dat");
        FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");

        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 1282ms
    }
}
