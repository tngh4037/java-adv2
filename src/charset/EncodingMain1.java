package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// No. 2
public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", StandardCharsets.US_ASCII);
        encoding("A", StandardCharsets.ISO_8859_1); // 참고) ASCII 와 호환
        encoding("A", EUC_KR); // 참고) ASCII 와 호환
        encoding("A", StandardCharsets.UTF_8); // 참고) ASCII 와 호환
        encoding("A", StandardCharsets.UTF_16BE); // 2Byte 단위로 처리, ASCII 와 호환 X

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR); // 2byte 단위로 처리
        encoding("가", MS_949); // 2byte 단위로 처리, (참고. MS_949 -> EUC_KR을 확장해서 만든 것)
        encoding("가", StandardCharsets.UTF_8); // 3byte 단위로 처리, EUC-KR / MS949 와 한글 호환 X
        encoding("가", StandardCharsets.UTF_16BE); // 2byte 단위로 처리, EUC-KR / MS949 와 한글 호환 X
    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}

// 문자를 컴퓨터가 이해할 수 있는 숫자(바이트)로 변경(인코딩)할 때는 반드시, 항상 인코딩표(문자집합)가 있어야 한다.
// 만일 문자 집합을 지정하지 않으면 현재 시스템에서 사용하는 기본 문자집합을 인코딩에 사용한다.