package androidx.media;

import X.AbstractC05200uh;
import X.AnonymousClass02C;
import X.AnonymousClass0Ce;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AudioAttributesCompat implements AnonymousClass0Ce {
    public static final SparseIntArray A01;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public AbstractC05200uh A00;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public @interface AttributeUsage {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        A01 = sparseIntArray;
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AbstractC05200uh r1 = this.A00;
        AbstractC05200uh r0 = ((AudioAttributesCompat) obj).A00;
        if (r1 != null) {
            return r1.equals(r0);
        }
        if (r0 == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }
}
