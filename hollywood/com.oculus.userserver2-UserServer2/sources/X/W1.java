package X;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

public final class W1 {
    public static W1 A02;
    public final KeyStore A00;
    public final HashMap<Principal, X509Certificate> A01;

    public W1() {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidCAStore");
            instance.load(null, null);
            HashMap<Principal, X509Certificate> hashMap = new HashMap<>();
            Enumeration<String> aliases = instance.aliases();
            while (aliases.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) instance.getCertificate(aliases.nextElement());
                if (x509Certificate != null) {
                    hashMap.put(x509Certificate.getSubjectX500Principal(), x509Certificate);
                }
            }
            this.A01 = hashMap;
            this.A00 = instance;
        } catch (FileNotFoundException | IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new AssertionError(e);
        }
    }
}
