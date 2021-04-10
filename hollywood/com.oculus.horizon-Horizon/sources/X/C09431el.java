package X;

import java.nio.charset.CharacterCodingException;

/* renamed from: X.1el  reason: invalid class name and case insensitive filesystem */
public class C09431el extends AnonymousClass1ec {
    public AnonymousClass1fR A00;
    public String A01;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C09431el(X.AnonymousClass1fR r10, java.lang.String r11) throws java.nio.charset.CharacterCodingException {
        /*
            r9 = this;
            X.1fE r5 = X.AnonymousClass1fE.Close
            r7 = 0
            if (r10 == 0) goto L_0x0034
            java.nio.charset.Charset r0 = X.AnonymousClass1ec.A06
            byte[] r8 = r11.getBytes(r0)
            int r6 = r8.length
            r4 = 2
            int r0 = r6 + r4
            byte[] r3 = new byte[r0]
            int r2 = r10.getValue()
            int r0 = r2 >> 8
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r3[r7] = r0
            r1 = 1
            r0 = r2 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r3[r1] = r0
            java.lang.System.arraycopy(r8, r7, r3, r4, r6)
        L_0x0025:
            r0 = 1
            r1 = 0
            r9.<init>(r5, r0)
            r9.A02 = r1
            r9.A03 = r3
            int r0 = r3.length
            r9.A04 = r0
            r9.A05 = r1
            return
        L_0x0034:
            byte[] r3 = new byte[r7]
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09431el.<init>(X.1fR, java.lang.String):void");
    }

    public C09431el(AnonymousClass1ec r6) throws CharacterCodingException {
        super(r6);
        byte[] bArr = r6.A03;
        if (bArr.length >= 2) {
            this.A00 = AnonymousClass1fR.find((bArr[1] & 255) | ((bArr[0] & 255) << 8));
            byte[] bArr2 = this.A03;
            this.A01 = new String(bArr2, 2, bArr2.length - 2, AnonymousClass1ec.A06);
        }
    }
}
