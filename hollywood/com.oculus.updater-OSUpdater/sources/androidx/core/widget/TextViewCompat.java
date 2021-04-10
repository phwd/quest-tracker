package androidx.core.widget;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class TextViewCompat {

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface AutoSizeTextType {
    }

    private TextViewCompat() {
    }
}
