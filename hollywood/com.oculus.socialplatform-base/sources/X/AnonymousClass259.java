package X;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.SSLException;

/* renamed from: X.259  reason: invalid class name */
public final class AnonymousClass259 {
    public SecureRandom A00;

    public AnonymousClass259() throws AnonymousClass25A {
        try {
            this.A00 = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException unused) {
            throw new AnonymousClass25A((byte) 80, new SSLException("SHA1PRNG algorithm not found."));
        }
    }
}
