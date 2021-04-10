package defpackage;

/* renamed from: DE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DE1 extends GE1 {
    public final char[] d = new char[512];

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DE1(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            EE1 r0 = new EE1
            char[] r6 = r6.toCharArray()
            r0.<init>(r5, r6)
            r5 = 0
            r4.<init>(r0, r5)
            r5 = 512(0x200, float:7.175E-43)
            char[] r5 = new char[r5]
            r4.d = r5
            char[] r5 = r0.b
            int r5 = r5.length
            r6 = 16
            r1 = 0
            if (r5 != r6) goto L_0x001d
            r5 = 1
            goto L_0x001e
        L_0x001d:
            r5 = r1
        L_0x001e:
            defpackage.DD1.d(r5)
        L_0x0021:
            r5 = 256(0x100, float:3.59E-43)
            if (r1 >= r5) goto L_0x003a
            char[] r5 = r4.d
            int r6 = r1 >>> 4
            char[] r2 = r0.b
            char r6 = r2[r6]
            r5[r1] = r6
            r6 = r1 | 256(0x100, float:3.59E-43)
            r3 = r1 & 15
            char r2 = r2[r3]
            r5[r6] = r2
            int r1 = r1 + 1
            goto L_0x0021
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DE1.<init>(java.lang.String, java.lang.String):void");
    }

    @Override // defpackage.GE1
    public final void c(Appendable appendable, byte[] bArr, int i, int i2) {
        DD1.c(0, i2 + 0, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i3 + 0] & 255;
            appendable.append(this.d[i4]);
            appendable.append(this.d[i4 | 256]);
        }
    }
}
