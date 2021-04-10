package com.google.common.hash;

import java.nio.charset.Charset;

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
}
