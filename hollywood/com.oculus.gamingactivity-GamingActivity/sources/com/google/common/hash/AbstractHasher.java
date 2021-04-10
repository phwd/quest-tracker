package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@CanIgnoreReturnValue
abstract class AbstractHasher implements Hasher {
    AbstractHasher() {
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public final Hasher putBoolean(boolean b) {
        return putByte(b ? (byte) 1 : 0);
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public final Hasher putDouble(double d) {
        return putLong(Double.doubleToRawLongBits(d));
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public final Hasher putFloat(float f) {
        return putInt(Float.floatToRawIntBits(f));
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putUnencodedChars(CharSequence charSequence) {
        int len = charSequence.length();
        for (int i = 0; i < len; i++) {
            putChar(charSequence.charAt(i));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putBytes(byte[] bytes) {
        return putBytes(bytes, 0, bytes.length);
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putBytes(byte[] bytes, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        for (int i = 0; i < len; i++) {
            putByte(bytes[off + i]);
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putBytes(ByteBuffer b) {
        if (b.hasArray()) {
            putBytes(b.array(), b.arrayOffset() + b.position(), b.remaining());
            b.position(b.limit());
        } else {
            for (int remaining = b.remaining(); remaining > 0; remaining--) {
                putByte(b.get());
            }
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putShort(short s) {
        putByte((byte) s);
        putByte((byte) (s >>> 8));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putInt(int i) {
        putByte((byte) i);
        putByte((byte) (i >>> 8));
        putByte((byte) (i >>> 16));
        putByte((byte) (i >>> 24));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putLong(long l) {
        for (int i = 0; i < 64; i += 8) {
            putByte((byte) ((int) (l >>> i)));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
    public Hasher putChar(char c) {
        putByte((byte) c);
        putByte((byte) (c >>> '\b'));
        return this;
    }

    @Override // com.google.common.hash.Hasher
    public <T> Hasher putObject(T instance, Funnel<? super T> funnel) {
        funnel.funnel(instance, this);
        return this;
    }
}
