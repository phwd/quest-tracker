package javax.crypto;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public abstract class CipherSpi {
    /* access modifiers changed from: protected */
    public abstract byte[] engineDoFinal(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom);

    /* access modifiers changed from: protected */
    public abstract void engineInit(int i, Key key, SecureRandom secureRandom);

    /* access modifiers changed from: protected */
    public abstract void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    /* access modifiers changed from: protected */
    public abstract void engineSetMode(String str);

    /* access modifiers changed from: protected */
    public abstract void engineSetPadding(String str);

    /* access modifiers changed from: protected */
    public abstract byte[] engineUpdate(byte[] bArr, int i, int i2);
}
