package X;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.cert.X509Certificate;

/* renamed from: X.1b9  reason: invalid class name */
public final class AnonymousClass1b9 {
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
                instance.update(bArr, 0, read);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read2 = inputStream2.read(bArr);
                if (read2 == -1) {
                    return instance.verify(byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, read2);
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public AnonymousClass1b9(X509Certificate x509Certificate) {
        this.A00 = x509Certificate;
    }
}
