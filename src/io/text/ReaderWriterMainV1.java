package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// [ 문자 다루기 ]
// 스트림의 모든 데이터는 byte 단위를 사용한다.  따라서 byte 가 아닌 문자를 스트림에 직접 전달할 수는 없다.
// 예를 들어서 String 문자를 스트림을 통해 파일에 저장하려면 String 을 byte 로 변환한 다음에 저장해야 한다.
public class ReaderWriterMainV1 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC"; // 문자 "ABC" 를 파일에 저장하고 싶다. -> 문자를 byte로 바꿔야 한다. (encoding 해야 한다.)

        // 문자 -> byte, UTF-8 인코딩
        byte[] writeBytes = writeString.getBytes(StandardCharsets.UTF_8);
        System.out.println("write String: " + writeString);
        System.out.println("write bytes: " + Arrays.toString(writeBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(TextConst.FILE_NAME);
        fos.write(writeBytes); // 참고) 파일에 ABC 라는 문자가 저장 되는게 아니다. 65,66,67 이라는 바이트가 저장되는 것이다. (에디터에서 이 바이트에 해당하는 문자표로 바꿔서 보여주는 것일 뿐)
        fos.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(TextConst.FILE_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();

        // byte -> String, UTF-8 디코딩
        String readString = new String(readBytes, StandardCharsets.UTF_8);
        System.out.println("read bytes: " + Arrays.toString(readBytes));
        System.out.println("read String: " + readString);
    }
}

// 개발자가 번거롭게 위와 같은 변환 과정을 직접하지 않고, 번거로운 변환 과정을 누군가 대신 처리해주면 더 편리하지 않을까? ( ReaderWriterMainV2 )