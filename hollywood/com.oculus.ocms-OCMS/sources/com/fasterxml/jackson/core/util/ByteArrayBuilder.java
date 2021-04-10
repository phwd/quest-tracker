package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.util.BufferRecycler;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public final class ByteArrayBuilder extends OutputStream {
    static final int DEFAULT_BLOCK_ARRAY_SIZE = 40;
    private static final int INITIAL_BLOCK_SIZE = 500;
    private static final int MAX_BLOCK_SIZE = 262144;
    private static final byte[] NO_BYTES = new byte[0];
    private final BufferRecycler _bufferRecycler;
    private byte[] _currBlock;
    private int _currBlockPtr;
    private final LinkedList<byte[]> _pastBlocks;
    private int _pastLen;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public ByteArrayBuilder() {
        this((BufferRecycler) null);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler) {
        this(bufferRecycler, 500);
    }

    public ByteArrayBuilder(int i) {
        this(null, i);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler, int i) {
        this._pastBlocks = new LinkedList<>();
        this._bufferRecycler = bufferRecycler;
        if (bufferRecycler == null) {
            this._currBlock = new byte[i];
        } else {
            this._currBlock = bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_CONCAT_BUFFER);
        }
    }

    public void reset() {
        this._pastLen = 0;
        this._currBlockPtr = 0;
        if (!this._pastBlocks.isEmpty()) {
            this._pastBlocks.clear();
        }
    }

    public void release() {
        reset();
        BufferRecycler bufferRecycler = this._bufferRecycler;
        if (bufferRecycler != null && this._currBlock != null) {
            bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_CONCAT_BUFFER, this._currBlock);
            this._currBlock = null;
        }
    }

    public void append(int i) {
        if (this._currBlockPtr >= this._currBlock.length) {
            _allocMore();
        }
        byte[] bArr = this._currBlock;
        int i2 = this._currBlockPtr;
        this._currBlockPtr = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void appendTwoBytes(int i) {
        int i2 = this._currBlockPtr;
        int i3 = i2 + 1;
        byte[] bArr = this._currBlock;
        if (i3 < bArr.length) {
            this._currBlockPtr = i2 + 1;
            bArr[i2] = (byte) (i >> 8);
            int i4 = this._currBlockPtr;
            this._currBlockPtr = i4 + 1;
            bArr[i4] = (byte) i;
            return;
        }
        append(i >> 8);
        append(i);
    }

    public void appendThreeBytes(int i) {
        int i2 = this._currBlockPtr;
        int i3 = i2 + 2;
        byte[] bArr = this._currBlock;
        if (i3 < bArr.length) {
            this._currBlockPtr = i2 + 1;
            bArr[i2] = (byte) (i >> 16);
            int i4 = this._currBlockPtr;
            this._currBlockPtr = i4 + 1;
            bArr[i4] = (byte) (i >> 8);
            int i5 = this._currBlockPtr;
            this._currBlockPtr = i5 + 1;
            bArr[i5] = (byte) i;
            return;
        }
        append(i >> 16);
        append(i >> 8);
        append(i);
    }

    public byte[] toByteArray() {
        int i = this._pastLen + this._currBlockPtr;
        if (i == 0) {
            return NO_BYTES;
        }
        byte[] bArr = new byte[i];
        Iterator<byte[]> it = this._pastBlocks.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            int length = next.length;
            System.arraycopy(next, 0, bArr, i2, length);
            i2 += length;
        }
        System.arraycopy(this._currBlock, 0, bArr, i2, this._currBlockPtr);
        int i3 = i2 + this._currBlockPtr;
        if (i3 == i) {
            if (!this._pastBlocks.isEmpty()) {
                reset();
            }
            return bArr;
        }
        throw new RuntimeException("Internal error: total len assumed to be " + i + ", copied " + i3 + " bytes");
    }

    public byte[] resetAndGetFirstSegment() {
        reset();
        return this._currBlock;
    }

    public byte[] finishCurrentSegment() {
        _allocMore();
        return this._currBlock;
    }

    public byte[] completeAndCoalesce(int i) {
        this._currBlockPtr = i;
        return toByteArray();
    }

    public byte[] getCurrentSegment() {
        return this._currBlock;
    }

    public void setCurrentSegmentLength(int i) {
        this._currBlockPtr = i;
    }

    public int getCurrentSegmentLength() {
        return this._currBlockPtr;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        while (true) {
            int min = Math.min(this._currBlock.length - this._currBlockPtr, i2);
            if (min > 0) {
                System.arraycopy(bArr, i, this._currBlock, this._currBlockPtr, min);
                i += min;
                this._currBlockPtr += min;
                i2 -= min;
            }
            if (i2 > 0) {
                _allocMore();
            } else {
                return;
            }
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        append(i);
    }

    private void _allocMore() {
        this._pastLen += this._currBlock.length;
        int max = Math.max(this._pastLen >> 1, 1000);
        if (max > 262144) {
            max = 262144;
        }
        this._pastBlocks.add(this._currBlock);
        this._currBlock = new byte[max];
        this._currBlockPtr = 0;
    }
}
