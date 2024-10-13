package io.streams;

import java.io.*;

public class DataStreamEtcMain {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF("회원A"); // UTF-8 형식으로 인코딩해서 해당 데이터를 내보냄 ( String은 writeUTF로 해주어야 한다. )
        dos.writeInt(20); // 자바의 int 로 저장 (4byte)
        dos.writeDouble(10.5); // 자바의 double 로 저장 (8byte)
        dos.writeBoolean(true); // 자바의 double 로 저장 (1byte)
        dos.close();

        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);

        System.out.println(dis.readUTF()); // 주의) 읽을 때, 반드시 저장한 순서대로 읽어야 한다. 그렇지 않으면 잘못된 데이터가 조회될 수 있다.
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        dis.close();
    }
}

// 자바 데이터 타입을 사용하면서, 회원 데이터를 편리하게 저장하고 불러오는 것을 확인할 수 있다.

// =================================================
// 저장한 data.dat 파일을 직접 열어보면 제대로 보이지 않는다.
// 왜냐하면 writeUTF() 의 경우 UTF-8 형식으로 저장하지만, 나머지의 경우에는 문자가 아니라 각 타입에 맞는 byte 단위로 저장하기 때문이다.
// 예를 들어서 자바에서 int는 4byte를 묶어서 사용한다. ( 해당 byte가 그대로 저장되는 것이다. )
// 텍스트 편집기는 자신의 문자 집합을 사용해서 byte를 문자로 표현하려고 시도하지만, 문자 집합에 없는 단어이거나 또는 전혀 예상하지 않은 문자로 디코딩 될 것이다.
