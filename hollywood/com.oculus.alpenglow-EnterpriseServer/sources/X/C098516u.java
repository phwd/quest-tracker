package X;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLException;

/* renamed from: X.16u  reason: invalid class name and case insensitive filesystem */
public final class C098516u {
    public MessageDigest A00;
    public MessageDigest A01 = null;

    public final void A00(byte[] bArr) throws AnonymousClass11f {
        if (bArr != null) {
            try {
                this.A01 = (MessageDigest) this.A00.clone();
                this.A00.update(bArr);
            } catch (CloneNotSupportedException e) {
                throw new AnonymousClass11f((byte) 80, new SSLException(e));
            }
        } else {
            throw new AnonymousClass11f((byte) 80, new SSLException("Cannot add null transcript."));
        }
    }

    public final byte[] A01() throws AnonymousClass11f {
        try {
            return ((MessageDigest) this.A00.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(e));
        }
    }

    public final byte[] A02() throws AnonymousClass11f {
        try {
            return ((MessageDigest) this.A01.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(e));
        }
    }

    public C098516u(String str) throws AnonymousClass11f {
        try {
            this.A00 = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(e));
        }
    }
}
