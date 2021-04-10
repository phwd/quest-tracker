package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.nio.charset.Charset;

@Beta
public interface Hasher extends PrimitiveSink {
    HashCode hash();

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBoolean(boolean z);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putByte(byte b);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBytes(byte[] bArr);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBytes(byte[] bArr, int i, int i2);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putChar(char c);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putDouble(double d);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putFloat(float f);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putInt(int i);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putLong(long j);

    <T> Hasher putObject(T t, Funnel<? super T> funnel);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putShort(short s);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putString(CharSequence charSequence, Charset charset);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putUnencodedChars(CharSequence charSequence);
}
