package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface ForeignKey {

    @Retention(RetentionPolicy.CLASS)
    public @interface Action {
    }

    Class<?> entity();
}
