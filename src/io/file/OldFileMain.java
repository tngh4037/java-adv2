package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

// File 은 파일과 디렉토리를 둘 다 다룬다.
public class OldFileMain {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt"); // 참고) File 객체를 생성했다고 파일이나 디렉토리가 바로 만들어지는 것은 아니다. 메서드를 통해 생성해야 한다. (ex. createNewFile() / mkdir())
        File directory = new File("temp/exampleDir");

        System.out.println("File exists: " + file.exists());
        System.out.println("Directory exists: " + directory.exists());

        boolean created = file.createNewFile(); // 참고) 이미 있으면 생성하지 X
        System.out.println("File created: " + created);

        boolean dirCreated = directory.mkdir(); // 참고) 이미 있으면 생성하지 X
        System.out.println("Directory created: " + dirCreated);

        // boolean deleted = file.delete();
        // System.out.println("File deleted: " + deleted);

        boolean isFile = file.isFile();
        System.out.println("Is file: " + isFile);

        boolean isDirectory = directory.isDirectory();
        System.out.println("Is directory: " + isDirectory);

        String fileName = file.getName();
        System.out.println("File name: " + fileName);

        long fileSize = file.length();
        System.out.println("File size: " + fileSize + " bytes");

        File newFile = new File("temp/newExample.txt"); // 파일의 이름을 변경/이동할 때
        boolean renamed = file.renameTo(newFile);
        System.out.println("File renamed: " + renamed);

        long lastModified = newFile.lastModified(); // 마지막으로 언제 변경됐는지
        System.out.println("Last modified: " + new Date(lastModified));
    }
}

// 참고) 자바 1.0에서 File 클래스가 등장했다. 이후에 자바 1.7에서 File 클래스를 대체할 Files 와 Path 가 등장했다.