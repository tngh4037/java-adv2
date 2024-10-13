package io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class NewFilesPath {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("temp/..");
        System.out.println("path = " + path); // 상대경로

        // 절대 경로
        System.out.println("Absolute path = " + path.toAbsolutePath()); // 경로의 처음부터 내가 입력한 모든 경로를 다 표현한다.

        // 정규 경로
        System.out.println("Canonical path = " + path.toRealPath()); // 경로의 계산이 모두 끝난 경로이다. ( 절대 경로 + .. 등을 다 계산한 경로 )

        Stream<Path> pathStream = Files.list(path); // 현재 경로에 있는 모든 파일 또는 디렉토리를 반환한다. | 참고) 여기서의 스트림은 입출력의 스트림이 아니라, java8 스트림이다.
        List<Path> list = pathStream.toList();
        pathStream.close(); // 참고) Files.list(path) 메서드는 내부적으로 리소스를 사용하므로, 스트림을 닫아 주어야 한다.

        for (Path p : list) {
            System.out.println((Files.isRegularFile(p) ? "F" : "D") + " | " + p.getFileName());
        }
    }
}
