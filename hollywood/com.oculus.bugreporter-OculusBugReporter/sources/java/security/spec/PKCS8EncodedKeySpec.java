package java.security.spec;

public class PKCS8EncodedKeySpec extends EncodedKeySpec {
    public PKCS8EncodedKeySpec(byte[] encodedKey) {
        super(encodedKey);
    }

    @Override // java.security.spec.EncodedKeySpec
    public byte[] getEncoded() {
        return super.getEncoded();
    }

    @Override // java.security.spec.EncodedKeySpec
    public final String getFormat() {
        return "PKCS#8";
    }
}
