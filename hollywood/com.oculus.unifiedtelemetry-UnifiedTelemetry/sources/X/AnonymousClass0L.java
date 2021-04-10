package X;

import com.oculus.library.database.contract.LibraryDBContract;

/* renamed from: X.0L  reason: invalid class name */
public final class AnonymousClass0L extends AnonymousClass30 {
    public final long A00;

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass0L) obj).A00 == this.A00;
        }
        return true;
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final Number A01() {
        return Long.valueOf(this.A00);
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final String A06() {
        long j = this.A00;
        if (j > 2147483647L || j < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Long.toString(j);
        }
        int i = (int) j;
        String[] strArr = C0461of.A00;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C0461of.A01;
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

    public AnonymousClass0L(long j) {
        this.A00 = j;
    }
}
