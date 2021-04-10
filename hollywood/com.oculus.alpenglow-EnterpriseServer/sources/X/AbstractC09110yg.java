package X;

import com.facebook.infer.annotation.Nullsafe;
import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yg  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09110yg {
    public EnumC09280yz A00;
    public AnonymousClass0z2 A01;

    public final byte[] A00() {
        KeyPair keyPair = ((AnonymousClass15W) this).A00;
        byte[] byteArray = ((ECPublicKey) keyPair.getPublic()).getW().getAffineX().toByteArray();
        byte[] byteArray2 = ((ECPublicKey) keyPair.getPublic()).getW().getAffineY().toByteArray();
        byte[] bArr = new byte[65];
        int i = 0;
        bArr[0] = 64;
        int length = byteArray.length;
        int max = Math.max(32 - length, 0) + 1;
        int length2 = byteArray2.length;
        int max2 = Math.max(32 - length2, 0) + 1;
        int i2 = 0;
        if (32 < length) {
            i2 = 1;
        }
        if (32 < length2) {
            i = 1;
        }
        System.arraycopy(byteArray, i2, bArr, max, length - i2);
        System.arraycopy(byteArray2, i, bArr, max2 + 32, length2 - i);
        return bArr;
    }

    public AbstractC09110yg(EnumC09280yz r1, AnonymousClass0z2 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
