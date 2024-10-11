package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

// byte 를 하나씩 다루는 것이 아니라,
// byte[] 을 사용해서, 데이터를 원하는 크기 만큼 더 편리하게 저장하고 읽는 방법
public class StreamStartMain3 {

    // [ 부분으로 나누어 읽기 ]
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67}; // 한 번에 저장하기 위해 byte 배열을 생성
        fos.write(input); // 바이트 배열을 통째로 전달
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] buffer = new byte[10]; // 하나씩 읽지 않고, 한 번에 읽기위해 10개짜리 바이트 배열 선언 (한번에 10개씩 읽어와보자)
        int readCount = fis.read(buffer, 0, 10); // buffer: 여기에 읽어온 값이 넣어짐, off: 바이트 배열에 몇번째부터 넣을지 지정(인덱스 시작 위치), len: 한번에 읽을 때 최대 몇개까지 읽을지 지정 (바이트 길이에 맞게 지정했다), 리턴값: 몇개 읽었는지 readCount 반환
        // int readCount = fis.read(buffer, 1, 9);
        System.out.println("readCount = " + readCount); // 3: 버퍼에 읽은 총 바이트 수, ( 3byte를 읽었다는 얘기 ) | 참고) 스트림의 끝에 도달하여 더 이상 데이터가 없는 경우 -1을 반환
        System.out.println(Arrays.toString(buffer)); // [65, 66, 67, 0, 0, 0, 0, 0, 0, 0]
        fis.close();
    }
}

// 참고) fis.read(byte[]): offset , length 생략 가능
// - 생략 시, 다음과 같이 지정된다. => offset : 0, length : byte[].length
