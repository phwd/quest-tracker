package android.icu.util;

import android.icu.util.StringTrieBuilder;
import java.nio.ByteBuffer;

public final class BytesTrieBuilder extends StringTrieBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private byte[] bytes;
    private int bytesLength;
    private final byte[] intBytes = new byte[5];

    private static final class BytesAsCharSequence implements CharSequence {
        private int len;
        private byte[] s;

        public BytesAsCharSequence(byte[] sequence, int length) {
            this.s = sequence;
            this.len = length;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            return (char) (this.s[i] & 255);
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.len;
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int start, int end) {
            return null;
        }
    }

    public BytesTrieBuilder add(byte[] sequence, int length, int value) {
        addImpl(new BytesAsCharSequence(sequence, length), value);
        return this;
    }

    public BytesTrie build(StringTrieBuilder.Option buildOption) {
        buildBytes(buildOption);
        byte[] bArr = this.bytes;
        return new BytesTrie(bArr, bArr.length - this.bytesLength);
    }

    public ByteBuffer buildByteBuffer(StringTrieBuilder.Option buildOption) {
        buildBytes(buildOption);
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i = this.bytesLength;
        return ByteBuffer.wrap(bArr, length - i, i);
    }

    private void buildBytes(StringTrieBuilder.Option buildOption) {
        if (this.bytes == null) {
            this.bytes = new byte[1024];
        }
        buildImpl(buildOption);
    }

    public BytesTrieBuilder clear() {
        clearImpl();
        this.bytes = null;
        this.bytesLength = 0;
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public boolean matchNodesCanHaveValues() {
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int getMaxBranchLinearSubNodeLength() {
        return 5;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int getMinLinearMatch() {
        return 16;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int getMaxLinearMatchLength() {
        return 16;
    }

    private void ensureCapacity(int length) {
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            int newCapacity = bArr.length;
            do {
                newCapacity *= 2;
            } while (newCapacity <= length);
            byte[] newBytes = new byte[newCapacity];
            byte[] bArr2 = this.bytes;
            int length2 = bArr2.length;
            int i = this.bytesLength;
            System.arraycopy(bArr2, length2 - i, newBytes, newBytes.length - i, i);
            this.bytes = newBytes;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int write(int b) {
        int newLength = this.bytesLength + 1;
        ensureCapacity(newLength);
        this.bytesLength = newLength;
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i = this.bytesLength;
        bArr[length - i] = (byte) b;
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int write(int offset, int length) {
        int newLength = this.bytesLength + length;
        ensureCapacity(newLength);
        this.bytesLength = newLength;
        int bytesOffset = this.bytes.length - this.bytesLength;
        while (length > 0) {
            this.bytes[bytesOffset] = (byte) this.strings.charAt(offset);
            length--;
            bytesOffset++;
            offset++;
        }
        return this.bytesLength;
    }

    private int write(byte[] b, int length) {
        int newLength = this.bytesLength + length;
        ensureCapacity(newLength);
        this.bytesLength = newLength;
        byte[] bArr = this.bytes;
        System.arraycopy(b, 0, bArr, bArr.length - this.bytesLength, length);
        return this.bytesLength;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int writeValueAndFinal(int i, boolean isFinal) {
        int length;
        if (i >= 0 && i <= 64) {
            return write(((i + 16) << 1) | isFinal);
        }
        int length2 = 1;
        if (i < 0 || i > 16777215) {
            byte[] bArr = this.intBytes;
            bArr[0] = Byte.MAX_VALUE;
            bArr[1] = (byte) (i >> 24);
            bArr[2] = (byte) (i >> 16);
            bArr[3] = (byte) (i >> 8);
            bArr[4] = (byte) i;
            length = 5;
        } else {
            if (i <= 6911) {
                this.intBytes[0] = (byte) ((i >> 8) + 81);
            } else {
                if (i <= 1179647) {
                    this.intBytes[0] = (byte) ((i >> 16) + 108);
                } else {
                    byte[] bArr2 = this.intBytes;
                    bArr2[0] = 126;
                    bArr2[1] = (byte) (i >> 16);
                    length2 = 2;
                }
                this.intBytes[length2] = (byte) (i >> 8);
                length2++;
            }
            length = length2 + 1;
            this.intBytes[length2] = (byte) i;
        }
        byte[] bArr3 = this.intBytes;
        bArr3[0] = (byte) ((bArr3[0] << 1) | (isFinal ? 1 : 0));
        return write(bArr3, length);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int writeValueAndType(boolean hasValue, int value, int node) {
        int offset = write(node);
        if (hasValue) {
            return writeValueAndFinal(value, false);
        }
        return offset;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int writeDeltaTo(int jumpTarget) {
        int length;
        int i = this.bytesLength - jumpTarget;
        if (i <= 191) {
            return write(i);
        }
        if (i <= 12287) {
            this.intBytes[0] = (byte) ((i >> 8) + 192);
            length = 1;
        } else {
            if (i <= 917503) {
                this.intBytes[0] = (byte) ((i >> 16) + 240);
                length = 2;
            } else {
                if (i <= 16777215) {
                    this.intBytes[0] = -2;
                    length = 3;
                } else {
                    byte[] bArr = this.intBytes;
                    bArr[0] = -1;
                    bArr[1] = (byte) (i >> 24);
                    length = 4;
                }
                this.intBytes[1] = (byte) (i >> 16);
            }
            this.intBytes[1] = (byte) (i >> 8);
        }
        byte[] bArr2 = this.intBytes;
        bArr2[length] = (byte) i;
        return write(bArr2, length + 1);
    }
}
