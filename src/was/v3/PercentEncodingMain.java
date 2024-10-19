package was.v3;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// URLEncoder.encode: 주어진 문자열을 URL에서 안전하게 사용할 수 있도록 인코딩 ( URL에서 안전하지 않거나 ASCII로 표현할 수 없는 문자만 퍼센트 인코딩 )
// ㄴ ASCII 문자(영문 알파벳, 숫자, 일부 특수 문자)는 인코딩되지 않고 그대로 유지된다.
// ㄴ 비ASCII 문자: ASCII로 표현되지 않는 문자(예: 한글, 특수 기호 등)는 퍼센트 인코딩(pen percent-encoding) 방식으로 인코딩된다. 예를 들어, 한글 "안"은 %EC%95%88으로 변환된다.
// ㄴ 참고) 공백: 공백 문자는 +로 인코딩되거나 %20으로 인코딩될 수 있다. ( 이는 URL 쿼리 스트링에서 일반적인 규칙이다. )
public class PercentEncodingMain {

    public static void main(String[] args) {
        String encoded1 = URLEncoder.encode("a", StandardCharsets.UTF_8);
        System.out.println("encoded1 = " + encoded1); // a ( a는 인코딩 하지 X, ASCII로 표현할 수 없는 것들만 인코딩 )

        String encoded2 = URLEncoder.encode("가", StandardCharsets.UTF_8);
        System.out.println("encoded2 = " + encoded2); // %EA%B0%80

        String decode1 = URLDecoder.decode("a", StandardCharsets.UTF_8);
        System.out.println("decode1 = " + decode1); // a ( a는 인코딩되지 않은 문자열이므로, 디코딩할 필요가 없다. 이 경우 결과는 입력값 그대로 반환된다. ) | 디코딩은 인코딩된 데이터(예: %20, %3F 등)를 원래의 문자로 변환하는 과정이기 때문에, 인코딩되지 않은 문자열은 변환 없이 그대로 반환된다.

        String decode2 = URLDecoder.decode(encoded2, StandardCharsets.UTF_8);
        System.out.println("decode2 = " + decode2); // 가
    }
}

// 참고)
// % 인코딩은 데이터 크기에서 보면 효율이 떨어진다.
// 문자 "가"는 단지 3byte만 필요하다. 그런데 % 인코딩을 사용하면 %EA%B0%80 무려 9byte가 사용된다. ( HTTP는 매우 보수적이다. 호환성을 최우선에 둔다. )
