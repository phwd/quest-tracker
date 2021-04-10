package defpackage;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: EE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EE1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f7947a;
    public final char[] b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final byte[] g;

    public EE1(String str, char[] cArr) {
        Objects.requireNonNull(str);
        this.f7947a = str;
        Objects.requireNonNull(cArr);
        this.b = cArr;
        try {
            int b2 = JE1.b(cArr.length, RoundingMode.UNNECESSARY);
            this.d = b2;
            int min = Math.min(8, Integer.lowestOneBit(b2));
            try {
                this.e = 8 / min;
                this.f = b2 / min;
                this.c = cArr.length - 1;
                byte[] bArr = new byte[128];
                Arrays.fill(bArr, (byte) -1);
                for (int i = 0; i < cArr.length; i++) {
                    char c2 = cArr[i];
                    DD1.e(c2 < 128, "Non-ASCII character: %s", c2);
                    DD1.e(bArr[c2] == -1, "Duplicate character: %s", c2);
                    bArr[c2] = (byte) i;
                }
                this.g = bArr;
                boolean[] zArr = new boolean[this.e];
                for (int i2 = 0; i2 < this.f; i2++) {
                    zArr[JE1.a(i2 << 3, this.d, RoundingMode.CEILING)] = true;
                }
            } catch (ArithmeticException e2) {
                String str2 = new String(cArr);
                throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e2);
            }
        } catch (ArithmeticException e3) {
            throw new IllegalArgumentException(AbstractC2531fV.s(35, "Illegal alphabet length ", cArr.length), e3);
        }
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof EE1) {
            return Arrays.equals(this.b, ((EE1) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        return this.f7947a;
    }
}
