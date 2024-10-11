package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// BufferedOutputStream 은 버퍼 기능을 내부에서 대신 처리해준다. ( 따라서 단순한 코드를 유지하면서 버퍼를 사용하는 이점도 함께 누릴 수 있다. )
public class CreateFileV3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BufferedConst.BUFFER_SIZE); // 두 번째 인자로 버퍼 사이즈를 지정한다. ( default: DEFAULT_MAX_BUFFER_SIZE = 8192; -> 기본 사이즈는 자바 버전마다 달라질 수 있다. )

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            bos.write(1);
        }
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 319ms
    }
}

// BufferedOutputStream 은 내부에서 단순히 버퍼 기능만 제공한다. 따라서 반드시 (버퍼가 가득찼을 때 어디로(어느 목적지로) 버퍼를 내보내줘야 하는지) 대상 OutputStream이 있어야 한다.
// 성능도 CreateFileV1 보다 훨씬 빠르게 처리되었다. ( 참고로 CreateFileV2 보다는 다소 성능이 떨어지는데 그 이유는 동기화 코드가 들어있기 때문이다. )