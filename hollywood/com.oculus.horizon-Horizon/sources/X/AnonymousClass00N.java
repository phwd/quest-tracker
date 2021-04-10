package X;

import com.oculus.library.database.contract.LibraryDBContract;

/* renamed from: X.00N  reason: invalid class name */
public final class AnonymousClass00N extends AnonymousClass038 {
    public final long A00;

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass00N) obj).A00 == this.A00;
        }
        return true;
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass038
    public final String A02() {
        long j = this.A00;
        if (j > 2147483647L || j < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Long.toString(j);
        }
        int i = (int) j;
        String[] strArr = C05010k3.A00;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C05010k3.A01;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    public final int hashCode() {
        long j = this.A00;
        return ((int) j) ^ ((int) (j >> 32));
    }

    public AnonymousClass00N(long j) {
        this.A00 = j;
    }
}
