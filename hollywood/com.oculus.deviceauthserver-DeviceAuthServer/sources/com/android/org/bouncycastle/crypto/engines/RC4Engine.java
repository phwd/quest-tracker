package com.android.org.bouncycastle.crypto.engines;

import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.OutputLengthException;
import com.android.org.bouncycastle.crypto.StreamCipher;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.google.common.primitives.UnsignedBytes;

public class RC4Engine implements StreamCipher {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;
    private byte[] workingKey = null;
    private int x = 0;
    private int y = 0;

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public void init(boolean forEncryption, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.workingKey = ((KeyParameter) params).getKey();
            setKey(this.workingKey);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + params.getClass().getName());
    }

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "RC4";
    }

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte in) {
        this.x = (this.x + 1) & 255;
        byte[] bArr = this.engineState;
        int i = this.x;
        this.y = (bArr[i] + this.y) & 255;
        byte tmp = bArr[i];
        int i2 = this.y;
        bArr[i] = bArr[i2];
        bArr[i2] = tmp;
        return (byte) (bArr[(bArr[i] + bArr[i2]) & 255] ^ in);
    }

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) {
        if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + len <= out.length) {
            for (int i = 0; i < len; i++) {
                this.x = (this.x + 1) & 255;
                byte[] bArr = this.engineState;
                int i2 = this.x;
                this.y = (bArr[i2] + this.y) & 255;
                byte tmp = bArr[i2];
                int i3 = this.y;
                bArr[i2] = bArr[i3];
                bArr[i3] = tmp;
                out[i + outOff] = (byte) (bArr[(bArr[i2] + bArr[i3]) & 255] ^ in[i + inOff]);
            }
            return len;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public void reset() {
        setKey(this.workingKey);
    }

    private void setKey(byte[] keyBytes) {
        this.workingKey = keyBytes;
        this.x = 0;
        this.y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[STATE_LENGTH];
        }
        for (int i = 0; i < STATE_LENGTH; i++) {
            this.engineState[i] = (byte) i;
        }
        int i1 = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < STATE_LENGTH; i3++) {
            int i4 = keyBytes[i1] & UnsignedBytes.MAX_VALUE;
            byte[] bArr = this.engineState;
            i2 = (i4 + bArr[i3] + i2) & 255;
            byte tmp = bArr[i3];
            bArr[i3] = bArr[i2];
            bArr[i2] = tmp;
            i1 = (i1 + 1) % keyBytes.length;
        }
    }
}
