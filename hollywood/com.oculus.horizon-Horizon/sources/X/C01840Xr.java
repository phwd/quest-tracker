package X;

import java.util.Arrays;

/* renamed from: X.0Xr  reason: invalid class name and case insensitive filesystem */
public class C01840Xr {
    public final int A00;
    public final byte[] A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C01840Xr)) {
            return false;
        }
        return Arrays.equals(this.A01, ((C01840Xr) obj).A01);
    }

    public C01840Xr(byte[] bArr) {
        this.A01 = bArr;
        this.A00 = Arrays.hashCode(bArr);
    }

    public final int hashCode() {
        return this.A00;
    }
}
