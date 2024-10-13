package io.file.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

// [ 파일 복사 V3 ]
// Files.copy() 는 자바에 파일 데이터를 불러오지 않고, 운영체제의 파일 복사 기능을 사용한다. ( 따라서 가장 빠르다. 파일을 다루어야 할 일이 있다면 항상 Files 의 기능을 먼저 찾아보자. )
// 이 기능은 파일에서 파일을 복사할 때만 유용하다. 만약 파일의 정보를 읽어서 처리해야 하거나, 스트림을 통해 네트워크에 전달해야 한다면 앞서 설명한 스트림을 직접 사용해야 한다.
public class FileCopyMainV3 {

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        Path source = Path.of("temp/copy.dat");
        Path target = Path.of("temp/copy_new.dat");

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING); // StandardCopyOption.REPLACE_EXISTING: 기존에 존재하면 교체하겠다.
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 157ms
    }
}
