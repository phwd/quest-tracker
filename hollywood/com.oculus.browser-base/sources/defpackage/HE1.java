package defpackage;

/* renamed from: HE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HE1 extends GE1 {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HE1(java.lang.String r2, java.lang.String r3, @org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Character r4) {
        /*
            r1 = this;
            EE1 r0 = new EE1
            char[] r3 = r3.toCharArray()
            r0.<init>(r2, r3)
            r1.<init>(r0, r4)
            char[] r2 = r0.b
            int r2 = r2.length
            r3 = 64
            if (r2 != r3) goto L_0x0015
            r2 = 1
            goto L_0x0016
        L_0x0015:
            r2 = 0
        L_0x0016:
            defpackage.DD1.d(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.HE1.<init>(java.lang.String, java.lang.String, java.lang.Character):void");
    }

    @Override // defpackage.GE1
    public final void c(Appendable appendable, byte[] bArr, int i, int i2) {
        int i3 = i2 + 0;
        int i4 = 0;
        DD1.c(0, i3, bArr.length);
        while (i2 >= 3) {
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i5] & 255) << 8) | ((bArr[i4] & 255) << 16);
            i4 = i6 + 1;
            int i8 = i7 | (bArr[i6] & 255);
            appendable.append(this.b.b[i8 >>> 18]);
            appendable.append(this.b.b[(i8 >>> 12) & 63]);
            appendable.append(this.b.b[(i8 >>> 6) & 63]);
            appendable.append(this.b.b[i8 & 63]);
            i2 -= 3;
        }
        if (i4 < i3) {
            d(appendable, bArr, i4, i3 - i4);
        }
    }
}
