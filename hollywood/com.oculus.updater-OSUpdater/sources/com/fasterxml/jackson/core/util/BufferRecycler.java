package com.fasterxml.jackson.core.util;

public class BufferRecycler {
    protected final byte[][] _byteBuffers = new byte[ByteBufferType.values().length][];
    protected final char[][] _charBuffers = new char[CharBufferType.values().length][];

    public enum ByteBufferType {
        READ_IO_BUFFER(4000),
        WRITE_ENCODING_BUFFER(4000),
        WRITE_CONCAT_BUFFER(2000),
        BASE64_CODEC_BUFFER(2000);
        
        protected final int size;

        private ByteBufferType(int i) {
            this.size = i;
        }
    }

    public enum CharBufferType {
        TOKEN_BUFFER(2000),
        CONCAT_BUFFER(2000),
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

    private byte[] balloc(int i) {
        return new byte[i];
    }
}
