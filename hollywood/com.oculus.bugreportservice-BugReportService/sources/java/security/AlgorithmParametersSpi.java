package java.security;

public abstract class AlgorithmParametersSpi {
    /* access modifiers changed from: protected */
    public abstract byte[] engineGetEncoded();

    /* access modifiers changed from: protected */
    public abstract void engineInit(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract String engineToString();
}
