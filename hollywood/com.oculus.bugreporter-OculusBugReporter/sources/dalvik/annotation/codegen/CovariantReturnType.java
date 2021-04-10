package dalvik.annotation.codegen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Repeatable(CovariantReturnTypes.class)
@Retention(RetentionPolicy.CLASS)
public @interface CovariantReturnType {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface CovariantReturnTypes {
        CovariantReturnType[] value();
    }

    int presentAfter();

    Class<?> returnType();
}
