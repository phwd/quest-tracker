package X;

import com.facebook.proxygen.HTTPTransportCallback;
import java.io.Serializable;
import java.util.Arrays;

public final class NP implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient char A00;
    public final transient int A01;
    public final transient boolean A02;
    public final transient char[] A03;
    public final transient byte[] A04;
    public final transient int[] A05;
    public final String _name;

    public final String A02(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append('\"');
        }
        int i = Integer.MAX_VALUE >> 2;
        int i2 = 0;
        int i3 = length - 3;
        while (i2 <= i3) {
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] << 8) | (bArr[i4] & 255)) << 8;
            i2 = i5 + 1;
            int i7 = i6 | (bArr[i5] & 255);
            char[] cArr = this.A03;
            sb.append(cArr[(i7 >> 18) & 63]);
            sb.append(cArr[(i7 >> 12) & 63]);
            sb.append(cArr[(i7 >> 6) & 63]);
            sb.append(cArr[i7 & 63]);
            i--;
            if (i <= 0) {
                sb.append('\\');
                sb.append('n');
                i = i;
            }
        }
        int i8 = length - i2;
        if (i8 > 0) {
            int i9 = i2 + 1;
            int i10 = bArr[i2] << 16;
            if (i8 == 2) {
                i10 |= (bArr[i9] & 255) << 8;
            }
            char[] cArr2 = this.A03;
            sb.append(cArr2[(i10 >> 18) & 63]);
            sb.append(cArr2[(i10 >> 12) & 63]);
            char c = '=';
            if (i8 == 2) {
                c = cArr2[(i10 >> 6) & 63];
            }
            sb.append(c);
            sb.append('=');
        }
        if (z) {
            sb.append('\"');
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A00(X.NP r2, char r3, int r4, java.lang.String r5) {
        /*
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: X.NP.A00(X.NP, char, int, java.lang.String):void");
    }

    public final int A01(char c) {
        if (c <= 127) {
            return this.A05[c];
        }
        return -1;
    }

    public final int hashCode() {
        return this._name.hashCode();
    }

    public Object readResolve() {
        String A052;
        String str = this._name;
        NP np = NQ.A00;
        if (!np._name.equals(str)) {
            np = NQ.A01;
            if (!np._name.equals(str)) {
                np = NQ.A03;
                if (!np._name.equals(str)) {
                    np = NQ.A02;
                    if (!np._name.equals(str)) {
                        if (str == null) {
                            A052 = "<null>";
                        } else {
                            A052 = AnonymousClass08.A05("'", str, "'");
                        }
                        throw new IllegalArgumentException(AnonymousClass08.A04("No Base64Variant with name ", A052));
                    }
                }
            }
        }
        return np;
    }

    public final String toString() {
        return this._name;
    }

    public NP(NP np, String str, int i) {
        this.A05 = new int[HTTPTransportCallback.BODY_BYTES_RECEIVED];
        this.A03 = new char[64];
        byte[] bArr = new byte[64];
        this.A04 = bArr;
        this._name = str;
        byte[] bArr2 = np.A04;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        char[] cArr = np.A03;
        System.arraycopy(cArr, 0, this.A03, 0, cArr.length);
        int[] iArr = np.A05;
        System.arraycopy(iArr, 0, this.A05, 0, iArr.length);
        this.A02 = true;
        this.A00 = '=';
        this.A01 = i;
    }

    public NP(String str, String str2, boolean z, char c, int i) {
        int[] iArr;
        this.A05 = new int[HTTPTransportCallback.BODY_BYTES_RECEIVED];
        char[] cArr = new char[64];
        this.A03 = cArr;
        this.A04 = new byte[64];
        this._name = str;
        this.A02 = z;
        this.A00 = c;
        this.A01 = i;
        int length = str2.length();
        if (length == 64) {
            int i2 = 0;
            str2.getChars(0, length, cArr, 0);
            Arrays.fill(this.A05, -1);
            do {
                char c2 = this.A03[i2];
                this.A04[i2] = (byte) c2;
                iArr = this.A05;
                iArr[c2] = i2;
                i2++;
            } while (i2 < length);
            if (z) {
                iArr[c] = -2;
                return;
            }
            return;
        }
        throw new IllegalArgumentException(AnonymousClass08.A01("Base64Alphabet length must be exactly 64 (was ", length, ")"));
    }
}
