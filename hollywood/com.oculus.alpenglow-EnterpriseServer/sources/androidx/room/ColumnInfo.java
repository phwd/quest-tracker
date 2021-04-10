package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface ColumnInfo {

    @Retention(RetentionPolicy.CLASS)
    public @interface Collate {
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface SQLiteTypeAffinity {
    }

    String defaultValue() default "[value-unspecified]";

    String name() default "[field-name]";
}
