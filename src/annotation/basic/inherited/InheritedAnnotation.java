package annotation.basic.inherited;

import java.lang.annotation.*;

@Inherited // 해당 애노테이션을 적용한 클래스가 (자식클래스가 있는) 상속관계에 있는 경우, 자식 클래스도 해당 애노테이션이 적용된다.
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {
}

// 참고) @Inherited 어노테이션은 클래스 레벨의 어노테이션에만 적용된다. ( @Target(ElementType.TYPE) )
//   ㄴ 메서드나 필드에는 효과 X