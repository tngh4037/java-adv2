package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

// No. 1
public class AvailableCharsetsMain {

    public static void main(String[] args) {
        // 이용 가능한 모든 Charset (자바가 제공하는 Charset + OS가 제공하는 Charset)
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }

        System.out.println("======");

        // 문자로 조회 (대소문자 구분X (ex. MS949 / ms949))
        Charset charset1 = Charset.forName("MS949"); // 인자로 문자 집합의 이름이나 별칭을 사용
        System.out.println("charset1 = " + charset1); // x-windows-949

        // 참고) 별칭 조회
        Set<String> aliases = charset1.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }


        // UTF-8 문자로 조회
        Charset charset2 = Charset.forName("UTF-8");
        System.out.println("charset2 = " + charset2);

        // UTF-8 상수로 조회
        Charset charset3 = StandardCharsets.UTF_8;
        System.out.println("charset3 = " + charset3);
        
        // 시스템의 기본 Charset 조회
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);
    }
}
