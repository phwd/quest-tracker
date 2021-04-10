package com.oculus.secure.unlockulus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;

public class FBSignVerify {
    private final X509Certificate mCertificate;

    public FBSignVerify(X509Certificate certificate) {
        this.mCertificate = certificate;
    }

    public boolean verify(InputStream dataStream, InputStream signatureStream) throws IOException {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(this.mCertificate);
            byte[] buf = new byte[1024];
            while (true) {
                int read = dataStream.read(buf);
                if (read == -1) {
                    break;
                }
                try {
                    signature.update(buf, 0, read);
                } catch (SignatureException e) {
                    throw new RuntimeException(e);
                }
            }
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            while (true) {
                int read2 = signatureStream.read(buf);
                if (read2 != -1) {
                    bout.write(buf, 0, read2);
                } else {
                    try {
                        return signature.verify(bout.toByteArray());
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
}
