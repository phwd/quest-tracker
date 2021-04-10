package androidx.media;

import X.AbstractC00303l;
import X.AbstractC0663eB;
import android.util.SparseIntArray;

public class AudioAttributesCompat implements AbstractC00303l {
    public static final SparseIntArray A01;
    public AbstractC0663eB A00;

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
        AbstractC0663eB eBVar = this.A00;
        AbstractC0663eB eBVar2 = ((AudioAttributesCompat) obj).A00;
        if (eBVar != null) {
            return eBVar.equals(eBVar2);
        }
        if (eBVar2 == null) {
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
