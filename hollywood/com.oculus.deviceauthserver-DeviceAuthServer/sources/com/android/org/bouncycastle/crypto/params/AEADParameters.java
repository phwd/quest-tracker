package com.android.org.bouncycastle.crypto.params;

import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.util.Arrays;

public class AEADParameters implements CipherParameters {
    private byte[] associatedText;
    private KeyParameter key;
    private int macSize;
    private byte[] nonce;

    public AEADParameters(KeyParameter key2, int macSize2, byte[] nonce2) {
        this(key2, macSize2, nonce2, null);
    }

    public AEADParameters(KeyParameter key2, int macSize2, byte[] nonce2, byte[] associatedText2) {
        this.key = key2;
        this.nonce = Arrays.clone(nonce2);
        this.macSize = macSize2;
        this.associatedText = Arrays.clone(associatedText2);
    }

    public KeyParameter getKey() {
        return this.key;
    }

    public int getMacSize() {
        return this.macSize;
    }

    public byte[] getAssociatedText() {
        return Arrays.clone(this.associatedText);
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }
}
