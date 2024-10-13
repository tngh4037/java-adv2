package io.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

// Files 는 직접 생성할 수 없고, static 메서드를 통해 기능을 제공한다.
public class NewFilesMain {

    public static void main(String[] args) throws IOException {

        Path file = Path.of("temp/example.txt"); // 참고) Files 를 사용할 때 파일이나 디렉토리의 경로는 Path 클래스를 사용해야 한다. ( 기존 File에서는 다 문자로 다뤘지만, 이제는 Path라는 클래스로 다루어야 한다. )
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exists: " + Files.exists(file));
        System.out.println("Directory exists: " + Files.exists(directory));

        try {
            Files.createFile(file); // boolean 을 리턴X
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) { // 파일이 이미 있을 시 FileAlreadyExistsException 발생
            System.out.println(file + " File already exists");
        //    throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(directory);
            System.out.println("Directory created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(directory + " Directory already exists");
       //     throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Files.delete(file);
        // System.out.println("File deleted");

        System.out.println("Is regular file: " + Files.isRegularFile(file)); // 파일 여부 체크
        System.out.println("Is directory: " + Files.isDirectory(directory)); // 디렉토리 여부 체크

        Path fileName = file.getFileName();
        System.out.println("File name: " + fileName);

        long fileSize = Files.size(file);
        System.out.println("File size: " + fileSize  + " bytes");

        Path newFile = Paths.get("temp/newExample.txt"); // 참고) Paths.get(..) 로 경로를 사용할 수도 있지만, Path.of(..) 로 사용할 수도 있다. 최근에는 이렇게 쓰는 추세
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING); // StandardCopyOption.REPLACE_EXISTING: 교체하겠다.
        System.out.println("File moved/renamed");

        System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));

        // 파일의 속성들을 한번에 얻는 방법
        BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("===== Attributes =====");
        System.out.println("Creation time: " + attrs.creationTime());
        System.out.println("Is directory: " + attrs.isDirectory());
        System.out.println("Is regular file: " + attrs.isRegularFile());
        System.out.println("Is symbolic link: " + attrs.isSymbolicLink());
        System.out.println("Size: " + attrs.size());
    }
}
