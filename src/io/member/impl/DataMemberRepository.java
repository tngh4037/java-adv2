package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-data.dat";

    @Override
    public void add(Member member) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH, true);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeUTF(member.getId());
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge());

            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        // try-with-resources
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH, true))) {
            dos.writeUTF(member.getId());
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            DataInputStream dis = new DataInputStream(fis);

            while (dis.available() > 0) { // 더 읽어올 것이 있는가?
                members.add(new Member(dis.readUTF(), dis.readUTF(), dis.readInt()));
            }

            dis.close();

            return members;
        } catch (FileNotFoundException e) { // 아직 파일이 생성되지 않았는데 읽는 경우, FileNotFoundException 발생
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        // try-with-resources
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))) {
            while (dis.available() > 0) { // 더 읽어올 것이 있는가?
                members.add(new Member(dis.readUTF(), dis.readUTF(), dis.readInt()));
            }
            return members;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
    }
}

// DataStream 덕분에 자바의 타입도 그대로 사용하고, 구분자도 제거할 수 있다.
// 물론 이렇게 byte를 직접 저장하면, 문서 파일을 열어서 확인하고 수정하는 것이 어렵다는 단점도 있다.