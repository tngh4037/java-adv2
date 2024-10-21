package annotation.basic;

import java.lang.reflect.Method;

@AnnoMeta // 타입에 적용
public class MetaData {
    
    // @AnnoMeta // 필드에 적용 - 컴파일 오류
    private String id;

    @AnnoMeta // 메서드에 적용
    public void call() {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<MetaData> metaDataClass = MetaData.class;
        AnnoMeta typeAnnotation = metaDataClass.getAnnotation(AnnoMeta.class);
        System.out.println("typeAnnotation = " + typeAnnotation);

        Method method = metaDataClass.getMethod("call");
        AnnoMeta methodAnnotation = method.getAnnotation(AnnoMeta.class);
        System.out.println("methodAnnotation = " + methodAnnotation);
    }
}

