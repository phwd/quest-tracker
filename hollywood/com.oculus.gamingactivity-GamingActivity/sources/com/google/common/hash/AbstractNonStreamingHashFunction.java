package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

@Immutable
abstract class AbstractNonStreamingHashFunction extends AbstractHashFunction {
    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public abstract HashCode hashBytes(byte[] bArr, int i, int i2);

    AbstractNonStreamingHashFunction() {
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return newHasher(32);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int expectedInputSize) {
        Preconditions.checkArgument(expectedInputSize >= 0);
        return new BufferingHasher(expectedInputSize);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int input) {
        return hashBytes(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(input).array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long input) {
        return hashBytes(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(input).array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence input) {
        int len = input.length();
        ByteBuffer buffer = ByteBuffer.allocate(len * 2).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < len; i++) {
            buffer.putChar(input.charAt(i));
        }
        return hashBytes(buffer.array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence input, Charset charset) {
        return hashBytes(input.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(ByteBuffer input) {
        return newHasher(input.remaining()).putBytes(input).hash();
    }

    /* access modifiers changed from: private */
    public final class BufferingHasher extends AbstractHasher {
        final ExposedByteArrayOutputStream stream;

        BufferingHasher(int expectedInputSize) {
            this.stream = new ExposedByteArrayOutputStream(expectedInputSize);
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public Hasher putByte(byte b) {
            this.stream.write(b);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public Hasher putBytes(byte[] bytes, int off, int len) {
            this.stream.write(bytes, off, len);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public Hasher putBytes(ByteBuffer bytes) {
            this.stream.write(bytes);
            return this;
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return AbstractNonStreamingHashFunction.this.hashBytes(this.stream.byteArray(), 0, this.stream.length());
        }
    }

    /* access modifiers changed from: private */
    public static final class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        ExposedByteArrayOutputStream(int expectedInputSize) {
            super(expectedInputSize);
        }

        /* access modifiers changed from: package-private */
        public void write(ByteBuffer input) {
            int remaining = input.remaining();
            if (this.count + remaining > this.buf.length) {
                this.buf = Arrays.copyOf(this.buf, this.count + remaining);
            }
            input.get(this.buf, this.count, remaining);
            this.count += remaining;
        }

        /* access modifiers changed from: package-private */
        public byte[] byteArray() {
            return this.buf;
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.count;
        }
    }
}
