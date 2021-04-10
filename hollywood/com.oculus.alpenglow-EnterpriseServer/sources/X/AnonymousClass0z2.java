package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0z2  reason: invalid class name */
public enum AnonymousClass0z2 {
    RSA2048_PKCS15(0),
    RSA2048_PSS(1),
    ECDSAP256(2),
    EDDSA(3);
    
    public int value;

    public byte getEncoded() {
        return (byte) this.value;
    }

    /* access modifiers changed from: public */
    AnonymousClass0z2(int i) {
        this.value = i;
    }
}
