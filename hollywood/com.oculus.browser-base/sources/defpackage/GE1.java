package defpackage;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: GE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GE1 {

    /* renamed from: a  reason: collision with root package name */
    public static final GE1 f8076a = new DE1("base16()", "0123456789ABCDEF");
    public final EE1 b;
    @NullableDecl
    public final Character c;

    static {
        new HE1("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
        new HE1("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
        new GE1(new EE1("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray()), '=');
        new GE1(new EE1("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV".toCharArray()), '=');
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GE1(defpackage.EE1 r5, @org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Character r6) {
        /*
            r4 = this;
            r4.<init>()
            java.util.Objects.requireNonNull(r5)
            r4.b = r5
            r0 = 0
            r1 = 1
            if (r6 == 0) goto L_0x0022
            char r2 = r6.charValue()
            byte[] r5 = r5.g
            int r3 = r5.length
            if (r2 >= r3) goto L_0x001c
            byte r5 = r5[r2]
            r2 = -1
            if (r5 == r2) goto L_0x001c
            r5 = r1
            goto L_0x001d
        L_0x001c:
            r5 = r0
        L_0x001d:
            if (r5 != 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r5 = r0
            goto L_0x0023
        L_0x0022:
            r5 = r1
        L_0x0023:
            if (r5 == 0) goto L_0x0028
            r4.c = r6
            return
        L_0x0028:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r6
            java.lang.String r6 = "Padding character %s was already in alphabet"
            java.lang.String r6 = defpackage.AD1.a(r6, r1)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.GE1.<init>(EE1, java.lang.Character):void");
    }

    public final String a(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    public final String b(byte[] bArr, int i, int i2) {
        DD1.c(0, i2, bArr.length);
        EE1 ee1 = this.b;
        StringBuilder sb = new StringBuilder(JE1.a(i2, ee1.f, RoundingMode.CEILING) * ee1.e);
        try {
            c(sb, bArr, 0, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public void c(Appendable appendable, byte[] bArr, int i, int i2) {
        int i3 = 0;
        DD1.c(0, i2 + 0, bArr.length);
        while (i3 < i2) {
            d(appendable, bArr, i3 + 0, Math.min(this.b.f, i2 - i3));
            i3 += this.b.f;
        }
    }

    public final void d(Appendable appendable, byte[] bArr, int i, int i2) {
        DD1.c(i, i + i2, bArr.length);
        int i3 = 0;
        DD1.d(i2 <= this.b.f);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = ((i2 + 1) << 3) - this.b.d;
        while (i3 < (i2 << 3)) {
            EE1 ee1 = this.b;
            appendable.append(ee1.b[((int) (j >>> (i5 - i3))) & ee1.c]);
            i3 += this.b.d;
        }
        if (this.c != null) {
            while (i3 < (this.b.f << 3)) {
                appendable.append(this.c.charValue());
                i3 += this.b.d;
            }
        }
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof GE1) {
            GE1 ge1 = (GE1) obj;
            if (!this.b.equals(ge1.b) || !AbstractC4540rD1.a(this.c, ge1.c)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.b.hashCode() ^ Arrays.hashCode(new Object[]{this.c});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.b.f7947a);
        if (8 % this.b.d != 0) {
            if (this.c == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.c);
                sb.append("')");
            }
        }
        return sb.toString();
    }
}
