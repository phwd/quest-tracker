package java.security;

public abstract class SignatureSpi {
    protected SecureRandom appRandom = null;

    /* access modifiers changed from: protected */
    public abstract void engineInitSign(PrivateKey privateKey);

    /* access modifiers changed from: protected */
    public abstract void engineInitVerify(PublicKey publicKey);

    /* access modifiers changed from: protected */
    public abstract void engineUpdate(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract boolean engineVerify(byte[] bArr);

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) {
        this.appRandom = secureRandom;
        engineInitSign(privateKey);
    }

    public Object clone() {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }
}
