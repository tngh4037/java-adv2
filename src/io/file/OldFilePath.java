package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/.."); // 참고) 상대 경로: 현재 자바 프로그램의 시작 위치부터의 경로 ( 자바 프로그램이 어디서 시작하냐에 따라 경로가 달라짐 )
        System.out.println("path = " + file.getPath());

        // 절대 경로
        System.out.println("Absolute path = " + file.getAbsolutePath()); // 경로의 처음부터 내가 입력한 모든 경로를 다 표현한다.
        
        // 정규 경로
        System.out.println("Canonical path = " + file.getCanonicalPath()); // 경로의 계산이 모두 끝난 경로이다. ( 절대 경로 + .. 등을 다 계산한 경로 )

        File[] files = file.listFiles();// 디렉토리 내 모든 파일/디렉토리 목록 조회
        for (File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }
    }
}
