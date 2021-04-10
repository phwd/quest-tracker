package androidx.versionedparcelable;

import X.AnonymousClass02D;
import androidx.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public @interface ParcelField {
    String defaultValue() default "";
}
