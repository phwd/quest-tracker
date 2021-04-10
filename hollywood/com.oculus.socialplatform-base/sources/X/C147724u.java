package X;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLException;

/* renamed from: X.24u  reason: invalid class name and case insensitive filesystem */
public final class C147724u {
    public MessageDigest A00;
    public MessageDigest A01 = null;

    public final void A00(byte[] bArr) throws AnonymousClass25A {
        if (bArr != null) {
            try {
                this.A01 = (MessageDigest) this.A00.clone();
                this.A00.update(bArr);
            } catch (CloneNotSupportedException e) {
                throw new AnonymousClass25A((byte) 80, new SSLException(e));
            }
        } else {
            throw new AnonymousClass25A((byte) 80, new SSLException("Cannot add null transcript."));
        }
    }

    public final byte[] A01() throws AnonymousClass25A {
        try {
            return ((MessageDigest) this.A00.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new AnonymousClass25A((byte) 80, new SSLException(e));
        }
    }

    public final byte[] A02() throws AnonymousClass25A {
        try {
            return ((MessageDigest) this.A01.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new AnonymousClass25A((byte) 80, new SSLException(e));
        }
    }

    public C147724u(String str) throws AnonymousClass25A {
        try {
            this.A00 = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AnonymousClass25A((byte) 80, new SSLException(e));
        }
    }
}
