package androidx.room;

import X.AnonymousClass0FK;
import X.AnonymousClass0FL;
import androidx.annotation.RequiresApi;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@RequiresApi(16)
@Retention(RetentionPolicy.CLASS)
public @interface Fts4 {
    Class<?> contentEntity() default Object.class;

    String languageId() default "";

    AnonymousClass0FK matchInfo() default AnonymousClass0FK.FTS4;

    AnonymousClass0FL order() default AnonymousClass0FL.ASC;

    String tokenizer() default "simple";
}
