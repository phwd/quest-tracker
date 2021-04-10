package X;

import java.util.Arrays;

/* renamed from: X.17K  reason: invalid class name */
public class AnonymousClass17K {
    public final int A00;
    public final byte[] A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass17K)) {
            return false;
        }
        return Arrays.equals(this.A01, ((AnonymousClass17K) obj).A01);
    }

    public AnonymousClass17K(byte[] bArr) {
        this.A01 = bArr;
        this.A00 = Arrays.hashCode(bArr);
    }

    public final int hashCode() {
        return this.A00;
    }
}
