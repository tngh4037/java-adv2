package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-obj.dat";

    @Override
    public void add(Member member) {
        List<Member> members = findAll();
        members.add(member);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) { // 참고) ObjectOutputStream: 객체 직렬화를 해주는 스트림
            oos.writeObject(members); // 통쨰로 파일에 저장 (참고. append 등과 같이 하나씩 파일에 저장할 수 없음)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object findObject = ois.readObject(); // 저장한 것을 통째로 조회
            List<Member> list = (List<Member>) findObject; // 참고) 반환타입이 Object 이므로 캐스팅 해줘야 한다.
            return list;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
