package X;

import java.util.Arrays;

/* renamed from: X.37  reason: invalid class name */
public final class AnonymousClass37 extends AnonymousClass7I {
    public static final AnonymousClass37 A01 = new AnonymousClass37(new byte[0]);
    public final byte[] A00;

    @Override // X.AbstractC0222Wi
    public final String A06() {
        return C0466pr.A01.A02(this.A00, false);
    }

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Arrays.equals(((AnonymousClass37) obj).A00, this.A00);
    }

    public final int hashCode() {
        byte[] bArr = this.A00;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // X.AnonymousClass7I, X.AbstractC0222Wi
    public final String toString() {
        return C0466pr.A01.A02(this.A00, true);
    }

    public AnonymousClass37(byte[] bArr) {
        this.A00 = bArr;
    }

    @Override // X.AbstractC0222Wi
    public final Integer A04() {
        return AnonymousClass07.A01;
    }
}
