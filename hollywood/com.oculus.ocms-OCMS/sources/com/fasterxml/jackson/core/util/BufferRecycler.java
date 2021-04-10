package com.fasterxml.jackson.core.util;

public class BufferRecycler {
    public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
    protected final byte[][] _byteBuffers = new byte[ByteBufferType.values().length][];
    protected final char[][] _charBuffers = new char[CharBufferType.values().length][];

    public enum ByteBufferType {
        READ_IO_BUFFER(4000),
        WRITE_ENCODING_BUFFER(4000),
        WRITE_CONCAT_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN),
        BASE64_CODEC_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
        
        protected final int size;

        private ByteBufferType(int i) {
            this.size = i;
        }
    }

    public enum CharBufferType {
        TOKEN_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN),
        CONCAT_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN),
        TEXT_BUFFER(200),
        NAME_COPY_BUFFER(200);
        
        protected final int size;

        private CharBufferType(int i) {
            this.size = i;
        }
    }

    public final byte[] allocByteBuffer(ByteBufferType byteBufferType) {
        int ordinal = byteBufferType.ordinal();
        byte[][] bArr = this._byteBuffers;
        byte[] bArr2 = bArr[ordinal];
        if (bArr2 == null) {
            return balloc(byteBufferType.size);
        }
        bArr[ordinal] = null;
        return bArr2;
    }

    public final void releaseByteBuffer(ByteBufferType byteBufferType, byte[] bArr) {
        this._byteBuffers[byteBufferType.ordinal()] = bArr;
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType) {
        return allocCharBuffer(charBufferType, 0);
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType, int i) {
        if (charBufferType.size > i) {
            i = charBufferType.size;
        }
        int ordinal = charBufferType.ordinal();
        char[][] cArr = this._charBuffers;
        char[] cArr2 = cArr[ordinal];
        if (cArr2 == null || cArr2.length < i) {
            return calloc(i);
        }
        cArr[ordinal] = null;
        return cArr2;
    }

    public final void releaseCharBuffer(CharBufferType charBufferType, char[] cArr) {
        this._charBuffers[charBufferType.ordinal()] = cArr;
    }

    private byte[] balloc(int i) {
        return new byte[i];
    }

    private char[] calloc(int i) {
        return new char[i];
    }
}
