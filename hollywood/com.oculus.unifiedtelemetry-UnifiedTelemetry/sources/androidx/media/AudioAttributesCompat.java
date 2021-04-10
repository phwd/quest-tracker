package androidx.media;

import X.AnonymousClass2C;
import X.CZ;
import X.Zl;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AudioAttributesCompat implements CZ {
    public static final SparseIntArray A01;
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public Zl A00;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2C.LIBRARY})
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
        Zl zl = this.A00;
        Zl zl2 = ((AudioAttributesCompat) obj).A00;
        if (zl != null) {
            return zl.equals(zl2);
        }
        if (zl2 == null) {
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
