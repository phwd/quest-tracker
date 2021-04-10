package X;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* renamed from: X.15W  reason: invalid class name */
public final class AnonymousClass15W extends AbstractC09110yg {
    public final KeyPair A00;

    public AnonymousClass15W() {
        super(EnumC09280yz.PROVIDED_TOKEN_BINDING, AnonymousClass0z2.ECDSAP256);
        try {
            ECGenParameterSpec eCGenParameterSpec = new ECGenParameterSpec("secp256r1");
            KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
            instance.initialize(eCGenParameterSpec);
            try {
                this.A00 = instance.generateKeyPair();
            } catch (AnonymousClass15V unused) {
                throw new RuntimeException("Unable to create key pair for secp256r1");
            }
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            throw new AnonymousClass15V(e);
        }
    }

    public AnonymousClass15W(byte[] bArr, byte[] bArr2) {
        super(EnumC09280yz.PROVIDED_TOKEN_BINDING, AnonymousClass0z2.ECDSAP256);
        try {
            KeyFactory instance = KeyFactory.getInstance("EC");
            this.A00 = new KeyPair(instance.generatePublic(new X509EncodedKeySpec(bArr)), instance.generatePrivate(new PKCS8EncodedKeySpec(bArr2)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Unable to create key pair from data", e);
        }
    }
}
