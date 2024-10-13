package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-txt.dat";
    private static final String DELIMITER = ",";

    @Override
    public void add(Member member) {

        try {
            FileWriter fw = new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
            bw.newLine(); // 다음 라인으로 이동 (enter)
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        // try-with-resources // try-with-resources 구문을 사용해서 자동으로 자원을 정리한다. try 코드 블록이 끝나면 자동으로 close()가 호출되면서 자원을 정리한다.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true))) {
            bw.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();

        try {
            FileReader fr = new FileReader(FILE_PATH, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] memberData = line.split(DELIMITER);
                members.add(new Member(memberData[0], memberData[1], Integer.valueOf(memberData[2])));
            }

            br.close();

            return members;
        } catch (FileNotFoundException e) { // 아직 파일이 생성되지 않았는데 읽는 경우, FileNotFoundException 발생
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        // try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] memberData = line.split(DELIMITER);
                members.add(new Member(memberData[0], memberData[1], Integer.valueOf(memberData[2])));
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
