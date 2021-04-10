package java.security;

public abstract class MessageDigestSpi {
    /* access modifiers changed from: protected */
    public abstract byte[] engineDigest();

    /* access modifiers changed from: protected */
    public abstract void engineReset();

    /* access modifiers changed from: protected */
    public abstract void engineUpdate(byte b);

    /* access modifiers changed from: protected */
    public abstract void engineUpdate(byte[] bArr, int i, int i2);

    public Object clone() {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }
}
