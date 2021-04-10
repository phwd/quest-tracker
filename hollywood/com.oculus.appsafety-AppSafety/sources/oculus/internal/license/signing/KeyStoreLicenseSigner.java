package oculus.internal.license.signing;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Pair;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.RSAKeyGenParameterSpec;
import javax.security.auth.x500.X500Principal;
import oculus.internal.BuildCompat;
import oculus.internal.license.parsing.LicenseBlobParser;

public class KeyStoreLicenseSigner implements LicenseSigner {
    private static final String KEY_ALIAS = "license_signer";
    Certificate certificate;
    Signature signer;

    public static KeyStoreLicenseSigner create() {
        try {
            Pair<Certificate, PrivateKey> pair = loadOrGenerateSigningKey();
            Signature signer2 = Signature.getInstance(LicenseBlobParser.SignatureScheme.SHA256_RSASSA_PSS.algorithmName);
            signer2.initSign((PrivateKey) pair.second);
            return new KeyStoreLicenseSigner((Certificate) pair.first, signer2);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize license signer", e);
        }
    }

    private static Pair<Certificate, PrivateKey> loadOrGenerateSigningKey() throws KeyStoreException, IOException, NoSuchAlgorithmException, UnrecoverableEntryException, NoSuchProviderException, CertificateException, InvalidAlgorithmParameterException {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        try {
            return loadEntry(ks.getEntry(KEY_ALIAS, null));
        } catch (NoSuchAlgorithmException | UnrecoverableEntryException e) {
            ks.deleteEntry(KEY_ALIAS);
            return generateSigningKey(ks);
        }
    }

    private static Pair<Certificate, PrivateKey> generateSigningKey(KeyStore ks) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, UnrecoverableEntryException, NoSuchProviderException, CertificateException, InvalidAlgorithmParameterException, IOException {
        KeyGenParameterSpec parameterSpec = new KeyGenParameterSpec.Builder(KEY_ALIAS, 12).setAlgorithmParameterSpec(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F0)).setCertificateSubject(new X500Principal("CN=\"Provisional License Signer for " + new BuildCompat().getSerial() + "\"")).setDigests("SHA-256").setSignaturePaddings("PSS").build();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
        kpg.initialize(parameterSpec);
        kpg.generateKeyPair();
        return loadEntry(ks.getEntry(KEY_ALIAS, null));
    }

    private static Pair<Certificate, PrivateKey> loadEntry(KeyStore.Entry entry) throws UnrecoverableKeyException {
        if (entry instanceof KeyStore.PrivateKeyEntry) {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) entry;
            return new Pair<>(privateKeyEntry.getCertificate(), privateKeyEntry.getPrivateKey());
        }
        throw new UnrecoverableKeyException("incorrect entry type");
    }

    KeyStoreLicenseSigner(Certificate certificate2, Signature signer2) {
        this.certificate = certificate2;
        this.signer = signer2;
    }

    @Override // oculus.internal.license.signing.LicenseSigner
    public Certificate getCertificate() {
        return this.certificate;
    }

    @Override // oculus.internal.license.signing.LicenseSigner
    public LicenseSignature sign(byte[] buf) throws SignatureException {
        this.signer.update(buf);
        return new LicenseSignature(LicenseBlobParser.SignatureScheme.SHA256_RSASSA_PSS, this.signer.sign());
    }
}
