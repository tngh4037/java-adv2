package annotation.basic;

// @AnnoElement(value = "data")
@AnnoElement("data") // 입력 요소가 하나인 경우 value 키워드 생략 가능 ( count 와 tags 는 default가 있기에, 사용시 정의하지 않아도 된다. value 만 정의하면 된다. ) | 참고) 만약, value가 아니라 다른 이름이라면 이런 기능을 제공하지 않는다.
public class ElementData3 {
}
