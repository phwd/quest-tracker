package java.security.spec;

public abstract class EncodedKeySpec implements KeySpec {
    private byte[] encodedKey;

    public EncodedKeySpec(byte[] bArr) {
        this.encodedKey = (byte[]) bArr.clone();
    }
}
