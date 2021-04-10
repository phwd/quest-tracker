package androidx.core.text.util;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;

public final class LinkifyCompat {
    private static final Comparator<LinkSpec> COMPARATOR = new Comparator<LinkSpec>() {
        /* class androidx.core.text.util.LinkifyCompat.AnonymousClass1 */

        public int compare(LinkSpec linkSpec, LinkSpec linkSpec2) {
            if (linkSpec.start < linkSpec2.start) {
                return -1;
            }
            if (linkSpec.start > linkSpec2.start || linkSpec.end < linkSpec2.end) {
                return 1;
            }
            if (linkSpec.end > linkSpec2.end) {
                return -1;
            }
            return 0;
        }
    };
    private static final String[] EMPTY_STRING = new String[0];

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface LinkifyMask {
    }

    private LinkifyCompat() {
    }

    /* access modifiers changed from: private */
    public static class LinkSpec {
        int end;
        int start;

        LinkSpec() {
        }
    }
}
