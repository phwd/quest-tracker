package androidx.core.content.res;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RestrictTo
public class FontResourcesParserCompat {

    @Retention(RetentionPolicy.SOURCE)
    public @interface FetchStrategy {
    }

    private FontResourcesParserCompat() {
    }
}
