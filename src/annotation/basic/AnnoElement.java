package annotation.basic;

import util.MyLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {
    String value();
    int count() default 0;
    String[] tags() default {};

    // MyLogger data(); // 직접 생성한 타입은 사용할 수 없다.
    Class<? extends MyLogger> annoData() default MyLogger.class; // 단, 클래스에 대한 메타데이터 정보는 사용할 수 있다.
}
