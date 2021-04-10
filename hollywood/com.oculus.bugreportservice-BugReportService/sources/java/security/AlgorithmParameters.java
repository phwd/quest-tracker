package java.security;

import java.io.IOException;

public class AlgorithmParameters {
    private String algorithm;
    private boolean initialized = false;
    private AlgorithmParametersSpi paramSpi;
    private Provider provider;

    protected AlgorithmParameters(AlgorithmParametersSpi algorithmParametersSpi, Provider provider2, String str) {
        this.paramSpi = algorithmParametersSpi;
        this.provider = provider2;
        this.algorithm = str;
    }

    public static AlgorithmParameters getInstance(String str) {
        try {
            Object[] impl = Security.getImpl(str, "AlgorithmParameters", (String) null);
            return new AlgorithmParameters((AlgorithmParametersSpi) impl[0], (Provider) impl[1], str);
        } catch (NoSuchProviderException unused) {
            throw new NoSuchAlgorithmException(str + " not found");
        }
    }

    public final void init(byte[] bArr) {
        if (!this.initialized) {
            this.paramSpi.engineInit(bArr);
            this.initialized = true;
            return;
        }
        throw new IOException("already initialized");
    }

    public final byte[] getEncoded() {
        if (this.initialized) {
            return this.paramSpi.engineGetEncoded();
        }
        throw new IOException("not initialized");
    }

    public final String toString() {
        if (!this.initialized) {
            return null;
        }
        return this.paramSpi.engineToString();
    }
}
