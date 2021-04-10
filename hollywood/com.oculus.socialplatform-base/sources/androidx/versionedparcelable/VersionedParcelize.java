package androidx.versionedparcelable;

import X.AnonymousClass02C;
import androidx.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public @interface VersionedParcelize {
    Class<?> factory() default void.class;

    String jetifyAs() default "";
}
