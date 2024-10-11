package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

// 버퍼 활용 (버퍼를 직접 다루는 방식)
public class CreateFileV2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BufferedConst.BUFFER_SIZE]; // 한번에 8KB씩 전달 ( 데이터를 buffer 라는 byte[]에 담기 위함. ) | 참고. 데이터를 모아서 전달하거나, 모아서 전달받는 용도로 사용하는 것을 버퍼라 한다
        int bufferIndex = 0;

        for (int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            buffer[bufferIndex] = 1;
            bufferIndex++;

            // 버퍼가 가득 차면 쓰고, 버퍼를 비운다.
            if (bufferIndex == BufferedConst.BUFFER_SIZE) {
                fos.write(buffer);
                bufferIndex = 0;
            }
        }
        
        // 반복문을 마쳤는데 버퍼가 남아있을 수 있다. ( 버퍼에 남은 부분 쓰기 )
        if (bufferIndex > 0) {
            fos.write(buffer, 0, bufferIndex);
        }

        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms"); // 35ms => 0.035초가 걸린것이다.
    }
}

// 참고) 버퍼의 크기에 따른 성능
// 많은 데이터를 한 번에 전달하면 성능을 최적화 할 수 있다. 그런데 버퍼의 크기가 커진다고 해서 속도가 계속 줄어들지는 않는다.
// 왜냐하면 디스크나 파일 시스템에서 데이터를 읽고 쓰는 기본 단위가 보통 4KB 또는 8KB이기 때문이다.
// 결국 버퍼에 많은 데이터를 담아서 보내도 디스크나 파일 시스템에서 해당 단위로 나누어 저장하기 때문에 효율에는 한계가 있다.
// 따라서 버퍼는 보통 4KB, 8KB 정도로 잡는 것이 효율적이다.