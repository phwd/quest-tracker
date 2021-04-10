package X;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.SSLException;

/* renamed from: X.0XY  reason: invalid class name */
public final class AnonymousClass0XY {
    public SecureRandom A00;

    public AnonymousClass0XY() throws AnonymousClass1v5 {
        try {
            this.A00 = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException unused) {
            throw new AnonymousClass1v5((byte) 80, new SSLException("SHA1PRNG algorithm not found."));
        }
    }
}
