package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// [ 파일 복사 V2 ]
// InputStream 에는 transferTo() 라는 특별한 메서드가 있다. (자바 9)
// 이 메서드는 InputStream 에서 읽은 데이터를 바로 OutputStream 으로 출력한다.
// transferTo() 는 성능 최적화가 되어 있기 때문에, FileCopyMainV1와 비슷하거나 조금 더 빠르다. ( 상황에 따라 조금 더 느릴 수도 있다. )
public class FileCopyMainV2 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("temp/copy.dat");
        FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");

        fis.transferTo(fos);
        // byte[] bytes = fis.readAllBytes();
        // fos.write(bytes);

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 321ms
    }
}

// transferTo() 덕분에 매우 편리하게 InputStream 의 내용을 OutputStream 으로 전달할 수 있다.