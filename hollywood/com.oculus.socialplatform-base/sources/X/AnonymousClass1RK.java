package X;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;

/* renamed from: X.1RK  reason: invalid class name */
public final class AnonymousClass1RK {
    public final X509Certificate A00;

    public final boolean A00(InputStream inputStream, InputStream inputStream2) throws IOException {
        try {
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initVerify(this.A00);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                try {
                    instance.update(bArr, 0, read);
                } catch (SignatureException e) {
                    throw new RuntimeException(e);
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read2 = inputStream2.read(bArr);
                if (read2 != -1) {
                    byteArrayOutputStream.write(bArr, 0, read2);
                } else {
                    try {
                        return instance.verify(byteArrayOutputStream.toByteArray());
                    } catch (SignatureException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        } catch (InvalidKeyException e4) {
            throw new RuntimeException(e4);
        }
    }

    public AnonymousClass1RK(X509Certificate x509Certificate) {
        this.A00 = x509Certificate;
    }
}
