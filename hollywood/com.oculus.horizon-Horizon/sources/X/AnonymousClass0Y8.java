package X;

import com.facebook.infer.annotation.Nullsafe;
import java.security.SignatureException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Y8  reason: invalid class name */
public abstract class AnonymousClass0Y8 {
    public AnonymousClass0Y7 A00;
    public AnonymousClass0Y9 A01;

    public abstract String A00();

    public abstract String A01();

    public abstract byte[] A02();

    public abstract byte[] A03(byte[] bArr) throws SignatureException, AnonymousClass0Y1;

    public AnonymousClass0Y8(AnonymousClass0Y7 r1, AnonymousClass0Y9 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
