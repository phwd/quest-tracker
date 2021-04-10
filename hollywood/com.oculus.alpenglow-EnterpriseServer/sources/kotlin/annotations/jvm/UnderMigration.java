package kotlin.annotations.jvm;

import X.AnonymousClass0o9;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnderMigration {
    AnonymousClass0o9 status();
}
