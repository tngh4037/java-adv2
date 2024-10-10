package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodingMain2 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ASCII 인코딩 ==");
        test("A", StandardCharsets.US_ASCII, StandardCharsets.US_ASCII);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.ISO_8859_1); // ASCII 확장(LATIN-1) ( 기존 ASCII에 서유럽 문자의 추가, 기존 ASCII 문자 집합과 호환 )
        test("A", StandardCharsets.US_ASCII, EUC_KR); // ASCII 포함 ( ASCII + 자주 사용하는 한글 2350개 + 한국에서 자주 사용하는 기타 글자 )
        test("A", StandardCharsets.US_ASCII, MS_949); // ASCII 포함 ( EUC-KR 의 확장, 기존 ASCII 문자 집합과 호환 가능 )
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_8); // ASCII 포함 ( ASCII 문자는 1바이트로 표현, ASCII 호환 )
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_16BE); // 디코딩 실패 ( ASCII 영문을 2byte로 처리한다. ASCII와 호환되지 않음 )

        System.out.println("== 한글 인코딩 - 기본 ==");
        test("가", StandardCharsets.US_ASCII, StandardCharsets.US_ASCII); // ASCII 한글 지원 X ( 뭔가 인코딩을 하기는 하는데(자바에서는 물음표를 의미하는 63으로 넣는것같음), 해당 인코딩한 값을 디코딩시 ASCII 표에서 그대로 찾기 때문에 전혀 다른 값이 나옴 )  | 참고. 이 경우 흥미롭게도 숫자 63이 되는데 63은 ASCII로 ? 라는 뜻이다. 한마디로 모르는 이상한 문자가 인코딩 되었다는 뜻이다.
        test("가", StandardCharsets.ISO_8859_1, StandardCharsets.US_ASCII); // ASCII 한글 지원 X ( 뭔가 인코딩을 하기는 하는데(자바에서는 물음표를 의미하는 63으로 넣는것같음), 해당 인코딩한 값을 디코딩시 ASCII 표에서 그대로 찾기 때문에 전혀 다른 값이 나옴 )  | 참고. 이 경우 흥미롭게도 숫자 63이 되는데 63은 ASCII로 ? 라는 뜻이다. 한마디로 모르는 이상한 문자가 인코딩 되었다는 뜻이다.
        test("가", EUC_KR, EUC_KR);
        test("가", MS_949, MS_949);
        test("가", StandardCharsets.UTF_8, StandardCharsets.UTF_8);
        test("가", StandardCharsets.UTF_16BE, StandardCharsets.UTF_16BE);

        System.out.println("== 한글 인코딩 - 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR); // X ( EUC-KR 에는 없는 문자 )
        test("뷁", MS_949, MS_949); // O ( MS_949 는 EUC-KR 을 확장해서 모든 한글 다 표현 가능 )
        test("뷁", StandardCharsets.UTF_8, StandardCharsets.UTF_8); // O ( 모든 한글 지원 )
        test("뷁", StandardCharsets.UTF_16BE, StandardCharsets.UTF_16BE); // O ( 모든 한글 지원 )

        System.out.println("== 한글 인코딩 - 디코딩이 다른 경우 ==");
        test("가", EUC_KR, MS_949); // O ( MS_949 는 EUC-KR 을 그대로 해서 확장해서 만든 것 )
        test("가", MS_949, EUC_KR); // O ( MS_949 는 EUC-KR 을 그대로 해서 확장해서 만든 것 )
        test("뷁", EUC_KR, MS_949); // X ( 인코딩 불가(EUC-KR에 없는 특수한 한글 문자), 디코딩 불가 )
        test("뷁", MS_949, EUC_KR); // X ( 인코딩 가능, 디코딩 불가(EUC-KR에 없는 특수한 한글 문자) )

        test("가", EUC_KR, StandardCharsets.UTF_8); // X ( EUC-KR 는 한글 2byte 처리, UTF_8 은 한글 3byte 처리, 두 인코딩이 지원하는 문자 집합이 다름 )
        test("가", StandardCharsets.UTF_8, EUC_KR); // X ( UTF_8 은 한글 3byte 처리, EUC-KR 는 한글 2byte 처리, 두 인코딩이 지원하는 문자 집합이 다름 )

        test("가", MS_949, StandardCharsets.UTF_8); // X ( MS_949 는 한글 2byte 처리, UTF_8 은 한글 3byte 처리, 두 인코딩이 지원하는 문자 집합이 다름 )
        test("가", StandardCharsets.UTF_8, MS_949); // X ( UTF_8 은 한글 3byte 처리, MS_949 는 한글 2byte 처리, 두 인코딩이 지원하는 문자 집합이 다름 )
        
        System.out.println("== 영문 인코딩 - 디코딩이 다른 경우==");
        test("A", EUC_KR, StandardCharsets.UTF_8); // O ( EUC-KR, UTF-8 모두 ASCII 지원 )
        test("A", MS_949, StandardCharsets.UTF_8); // O ( MS_949, UTF-8 모두 ASCII 지원 )
        test("A", StandardCharsets.UTF_8, MS_949); // O ( UTF-8, MS_949 모두 ASCII 지원 )
        test("A", StandardCharsets.UTF_8, StandardCharsets.UTF_16BE); // X ( UTF-16 은 ASCII 호환 X ( ASCII 영문에 2byte 사용 ) )
    }

    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset); // encoding
        String decoded = new String(encoded, decodingCharset);// decoding

        System.out.printf("%s -> [%s] 인코딩 -> %s (%sbyte 사용) -> [%s] 디코딩 -> %s\n",
                text, encodingCharset, Arrays.toString(encoded), encoded.length,
                decodingCharset, decoded
        );
    }
}

// 참고) 문자 -> 바이트, 바이트 -> 문자 변환시 반드시 문자집합이 있어야 한다.