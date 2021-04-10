package X;

import com.adobe.xmp.impl.Base64;
import java.security.MessageDigest;

/* renamed from: X.1eL  reason: invalid class name and case insensitive filesystem */
public final class C08291eL {
    public final AnonymousClass06o<C08311eN> A00 = new C08131e2(new AnonymousClass0WB(10), new C08301eM(this), AnonymousClass1e3.A00);
    public final AnonymousClass1cJ<AbstractC06491aL, String> A01 = new AnonymousClass1cJ<>(1000);

    public final String A00(AbstractC06491aL r11) {
        String A012;
        AnonymousClass1cJ<AbstractC06491aL, String> r7 = this.A01;
        synchronized (r7) {
            A012 = r7.A01(r11);
        }
        if (A012 == null) {
            AnonymousClass06o<C08311eN> r6 = this.A00;
            C08311eN A19 = r6.A19();
            AnonymousClass1S2.A00(A19);
            C08311eN r5 = A19;
            try {
                MessageDigest messageDigest = r5.A00;
                r11.AAv(messageDigest);
                byte[] digest = messageDigest.digest();
                char[] cArr = C08381eW.A01;
                synchronized (cArr) {
                    for (int i = 0; i < digest.length; i++) {
                        int i2 = digest[i] & Base64.INVALID;
                        int i3 = i << 1;
                        char[] cArr2 = C08381eW.A00;
                        cArr[i3] = cArr2[i2 >>> 4];
                        cArr[i3 + 1] = cArr2[i2 & 15];
                    }
                    A012 = new String(cArr);
                }
            } finally {
                r6.A8z(r5);
            }
        }
        synchronized (r7) {
            r7.A04(r11, A012);
        }
        return A012;
    }
}
