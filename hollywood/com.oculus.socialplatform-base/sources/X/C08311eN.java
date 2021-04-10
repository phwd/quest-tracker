package X;

import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* renamed from: X.1eN  reason: invalid class name and case insensitive filesystem */
public final class C08311eN implements AbstractC08161e6 {
    public final MessageDigest A00;
    public final AbstractC08341eQ A01 = new C08331eP();

    @Override // X.AbstractC08161e6
    @NonNull
    public final AbstractC08341eQ A5G() {
        return this.A01;
    }

    public C08311eN(MessageDigest messageDigest) {
        this.A00 = messageDigest;
    }
}
