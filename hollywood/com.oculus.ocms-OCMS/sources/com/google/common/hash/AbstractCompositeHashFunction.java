package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Immutable
abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
    private static final long serialVersionUID = 0;
    final HashFunction[] functions;

    /* access modifiers changed from: package-private */
    public abstract HashCode makeHash(Hasher[] hasherArr);

    AbstractCompositeHashFunction(HashFunction... hashFunctionArr) {
        for (HashFunction hashFunction : hashFunctionArr) {
            Preconditions.checkNotNull(hashFunction);
        }
        this.functions = hashFunctionArr;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        Hasher[] hasherArr = new Hasher[this.functions.length];
        for (int i = 0; i < hasherArr.length; i++) {
            hasherArr[i] = this.functions[i].newHasher();
        }
        return fromHashers(hasherArr);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int i) {
        Preconditions.checkArgument(i >= 0);
        Hasher[] hasherArr = new Hasher[this.functions.length];
        for (int i2 = 0; i2 < hasherArr.length; i2++) {
            hasherArr[i2] = this.functions[i2].newHasher(i);
        }
        return fromHashers(hasherArr);
    }

    private Hasher fromHashers(final Hasher[] hasherArr) {
        return new Hasher() {
            /* class com.google.common.hash.AbstractCompositeHashFunction.AnonymousClass1 */

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putByte(byte b) {
                for (Hasher hasher : hasherArr) {
                    hasher.putByte(b);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBytes(byte[] bArr) {
                for (Hasher hasher : hasherArr) {
                    hasher.putBytes(bArr);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBytes(byte[] bArr, int i, int i2) {
                for (Hasher hasher : hasherArr) {
                    hasher.putBytes(bArr, i, i2);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBytes(ByteBuffer byteBuffer) {
                int position = byteBuffer.position();
                Hasher[] hasherArr = hasherArr;
                for (Hasher hasher : hasherArr) {
                    byteBuffer.position(position);
                    hasher.putBytes(byteBuffer);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putShort(short s) {
                for (Hasher hasher : hasherArr) {
                    hasher.putShort(s);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putInt(int i) {
                for (Hasher hasher : hasherArr) {
                    hasher.putInt(i);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putLong(long j) {
                for (Hasher hasher : hasherArr) {
                    hasher.putLong(j);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putFloat(float f) {
                for (Hasher hasher : hasherArr) {
                    hasher.putFloat(f);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putDouble(double d) {
                for (Hasher hasher : hasherArr) {
                    hasher.putDouble(d);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBoolean(boolean z) {
                for (Hasher hasher : hasherArr) {
                    hasher.putBoolean(z);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putChar(char c) {
                for (Hasher hasher : hasherArr) {
                    hasher.putChar(c);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putUnencodedChars(CharSequence charSequence) {
                for (Hasher hasher : hasherArr) {
                    hasher.putUnencodedChars(charSequence);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putString(CharSequence charSequence, Charset charset) {
                for (Hasher hasher : hasherArr) {
                    hasher.putString(charSequence, charset);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher
            public <T> Hasher putObject(T t, Funnel<? super T> funnel) {
                for (Hasher hasher : hasherArr) {
                    hasher.putObject(t, funnel);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher
            public HashCode hash() {
                return AbstractCompositeHashFunction.this.makeHash(hasherArr);
            }
        };
    }
}
