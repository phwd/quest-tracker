package X;

import java.lang.ref.SoftReference;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.List;

/* renamed from: X.Kd  reason: case insensitive filesystem */
public final class C0213Kd {
    public SoftReference A00 = new SoftReference(null);

    public static byte[][] A00(List list) {
        byte[][] bArr = new byte[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            try {
                bArr[i] = ((Certificate) list.get(i)).getEncoded();
            } catch (CertificateEncodingException e) {
                C0139Dd.A0R("Failed to encode Root CA", e);
            }
        }
        return bArr;
    }
}
