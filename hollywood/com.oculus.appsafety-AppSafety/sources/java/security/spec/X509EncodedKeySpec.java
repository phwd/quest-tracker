package java.security.spec;

public class X509EncodedKeySpec extends EncodedKeySpec {
    public X509EncodedKeySpec(byte[] encodedKey) {
        super(encodedKey);
    }

    @Override // java.security.spec.EncodedKeySpec
    public byte[] getEncoded() {
        return super.getEncoded();
    }

    @Override // java.security.spec.EncodedKeySpec
    public final String getFormat() {
        return "X.509";
    }
}
