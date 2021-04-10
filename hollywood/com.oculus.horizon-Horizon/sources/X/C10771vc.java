package X;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLException;

/* renamed from: X.1vc  reason: invalid class name and case insensitive filesystem */
public final class C10771vc {
    public MessageDigest A00;
    public MessageDigest A01 = null;

    public final void A00(byte[] bArr) throws AnonymousClass1v5 {
        if (bArr != null) {
            try {
                this.A01 = (MessageDigest) this.A00.clone();
                this.A00.update(bArr);
            } catch (CloneNotSupportedException e) {
                throw new AnonymousClass1v5((byte) 80, new SSLException(e));
            }
        } else {
            throw new AnonymousClass1v5((byte) 80, new SSLException("Cannot add null transcript."));
        }
    }

    public final byte[] A01() throws AnonymousClass1v5 {
        try {
            return ((MessageDigest) this.A00.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new AnonymousClass1v5((byte) 80, new SSLException(e));
        }
    }

    public final byte[] A02() throws AnonymousClass1v5 {
        try {
            return ((MessageDigest) this.A01.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new AnonymousClass1v5((byte) 80, new SSLException(e));
        }
    }

    public C10771vc(String str) throws AnonymousClass1v5 {
        try {
            this.A00 = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AnonymousClass1v5((byte) 80, new SSLException(e));
        }
    }
}
