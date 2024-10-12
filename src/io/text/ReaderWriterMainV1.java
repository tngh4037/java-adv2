package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
