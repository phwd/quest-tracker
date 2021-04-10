package androidx.annotation;

import X.AnonymousClass22;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface InspectableProperty {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EnumEntry {
        String name();
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FlagEntry {
        String name();
    }

    String name() default "";

    AnonymousClass22 valueType() default AnonymousClass22.INFERRED;
}
