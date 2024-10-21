package annotation.basic.inherited.interfaces;

public class TestInterfaceImpl implements TestInterface { // TestInterface 에 적용된 @InheritedAnnotation 가 구현체에 적용되지 않는다.
}

// (규칙) @Inherited 는 "클래스 상속"에서만 작동하고, 인터페이스의 구현체에는 적용되지 않는다.
