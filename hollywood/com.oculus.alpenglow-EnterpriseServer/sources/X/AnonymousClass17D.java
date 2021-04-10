package X;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.SSLException;

/* renamed from: X.17D  reason: invalid class name */
public final class AnonymousClass17D {
    public SecureRandom A00;

    public AnonymousClass17D() throws AnonymousClass11f {
        try {
            this.A00 = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException unused) {
            throw new AnonymousClass11f((byte) 80, new SSLException("SHA1PRNG algorithm not found."));
        }
    }
}
