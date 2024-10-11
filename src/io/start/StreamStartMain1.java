package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat"); // FileOutputStream: 파일에 (저장하기 위해) 보내기 위해 사용. ( 파일에 데이터를 출력하는 스트림이다. )  |  참고) 파일이 없으면 생성한다. 단, 디렉토리는 만들지 않는다. 따라서 반드시 디렉토리를 만들어 두어야 한다. 그리고 기존에 파일은 있었도, 그 파일 안에 어떤 내용이 있었다면 그 내용은 지우고 저장한다. 기존 내용의 끝에 이어서 쓰려면 생성자의 두번째 인자로 true 를 주면 된다. ( default: false )
        fos.write(65); // 참고) 바이트 하나 입력시 0 ~ 255 사이로 입력
        fos.write(66);
        fos.write(67);
        fos.close(); // 외부에 있는 자원(temp/hello.dat)을 가져다 쓰고나서, 마지막에 꼭 닫아주어야 한다.

        FileInputStream fis = new FileInputStream("temp/hello.dat"); // FileInputStream: 파일에서 데이터를 읽어오는 스트림
        System.out.println(fis.read()); // 65
        System.out.println(fis.read()); // 66
        System.out.println(fis.read()); // 67
        System.out.println(fis.read()); // -1 ( -1: 파일의 끝을 의미 )
        fis.close();
    }
}

// 참고) 파일을 열어보면 65, 66, 67이 보이는게 아니라 A, B, C 로 보인다.
// - FileOutputStream을 사용해 바이트를 직접 쓰면, 그 바이트들이 파일에 저장된다. 그런데 개발툴이나 텍스트 편집기로 열때는, 개발툴이나 텍스트 편집기는 이 바이트들을 ASCII 문자로 변환하여 표시한다.
// - 따라서 만약 파일의 바이트 값을 2진수 형식으로 보고 싶다면, 바이너리 파일 뷰어나 특정 프로그램을 사용해야 한다.
// - 정리) 우리가 사용하는 개발툴이나 텍스트 편집기는 UTF-8 또는 MS949 문자 집합을 사용해서 byte 단위의 데이터를 문자로 디코딩해서 보여준다. 따라서 65, 66, 67 byte를 ASCII 문자인 A, B, C로 인식해서 출력한 것이다.