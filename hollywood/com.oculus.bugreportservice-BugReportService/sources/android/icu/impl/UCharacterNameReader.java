package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.UCharacterName;
import java.io.IOException;
import java.nio.ByteBuffer;

final class UCharacterNameReader implements ICUBinary.Authenticate {
    private int m_algnamesindex_;
    private ByteBuffer m_byteBuffer_;
    private int m_groupindex_;
    private int m_groupstringindex_;
    private int m_tokenstringindex_;

    @Override // android.icu.impl.ICUBinary.Authenticate
    public boolean isDataVersionAcceptable(byte[] bArr) {
        return bArr[0] == 1;
    }

    protected UCharacterNameReader(ByteBuffer byteBuffer) {
        ICUBinary.readHeader(byteBuffer, 1970168173, this);
        this.m_byteBuffer_ = byteBuffer;
    }

    /* access modifiers changed from: protected */
    public void read(UCharacterName uCharacterName) {
        this.m_tokenstringindex_ = this.m_byteBuffer_.getInt();
        this.m_groupindex_ = this.m_byteBuffer_.getInt();
        this.m_groupstringindex_ = this.m_byteBuffer_.getInt();
        this.m_algnamesindex_ = this.m_byteBuffer_.getInt();
        char[] chars = ICUBinary.getChars(this.m_byteBuffer_, this.m_byteBuffer_.getChar(), 0);
        byte[] bArr = new byte[(this.m_groupindex_ - this.m_tokenstringindex_)];
        this.m_byteBuffer_.get(bArr);
        uCharacterName.setToken(chars, bArr);
        char c = this.m_byteBuffer_.getChar();
        uCharacterName.setGroupCountSize(c, 3);
        char[] chars2 = ICUBinary.getChars(this.m_byteBuffer_, c * 3, 0);
        byte[] bArr2 = new byte[(this.m_algnamesindex_ - this.m_groupstringindex_)];
        this.m_byteBuffer_.get(bArr2);
        uCharacterName.setGroup(chars2, bArr2);
        int i = this.m_byteBuffer_.getInt();
        UCharacterName.AlgorithmName[] algorithmNameArr = new UCharacterName.AlgorithmName[i];
        for (int i2 = 0; i2 < i; i2++) {
            UCharacterName.AlgorithmName readAlg = readAlg();
            if (readAlg != null) {
                algorithmNameArr[i2] = readAlg;
            } else {
                throw new IOException("unames.icu read error: Algorithmic names creation error");
            }
        }
        uCharacterName.setAlgorithm(algorithmNameArr);
    }

    private UCharacterName.AlgorithmName readAlg() {
        UCharacterName.AlgorithmName algorithmName = new UCharacterName.AlgorithmName();
        int i = this.m_byteBuffer_.getInt();
        int i2 = this.m_byteBuffer_.getInt();
        byte b = this.m_byteBuffer_.get();
        byte b2 = this.m_byteBuffer_.get();
        if (!algorithmName.setInfo(i, i2, b, b2)) {
            return null;
        }
        int i3 = this.m_byteBuffer_.getChar();
        if (b == 1) {
            algorithmName.setFactor(ICUBinary.getChars(this.m_byteBuffer_, b2, 0));
            i3 -= b2 << 1;
        }
        StringBuilder sb = new StringBuilder();
        byte b3 = this.m_byteBuffer_.get();
        while (true) {
            char c = (char) (b3 & 255);
            if (c == 0) {
                break;
            }
            sb.append(c);
            b3 = this.m_byteBuffer_.get();
        }
        algorithmName.setPrefix(sb.toString());
        int length = i3 - ((sb.length() + 12) + 1);
        if (length > 0) {
            byte[] bArr = new byte[length];
            this.m_byteBuffer_.get(bArr);
            algorithmName.setFactorString(bArr);
        }
        return algorithmName;
    }
}
