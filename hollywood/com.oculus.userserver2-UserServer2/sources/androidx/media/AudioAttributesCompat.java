package androidx.media;

import X.AbstractC0059Eo;
import X.AnonymousClass2O;
import X.TR;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AudioAttributesCompat implements AbstractC0059Eo {
    public static final SparseIntArray A01;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public TR A00;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2O.LIBRARY})
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
        TR tr = this.A00;
        TR tr2 = ((AudioAttributesCompat) obj).A00;
        if (tr != null) {
            return tr.equals(tr2);
        }
        if (tr2 == null) {
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
