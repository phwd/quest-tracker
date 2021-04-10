package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.charset.Charset;

abstract class AbstractCompositeHashFunction extends AbstractStreamingHashFunction {
    private static final long serialVersionUID = 0;
    final HashFunction[] functions;

    /* access modifiers changed from: package-private */
    public abstract HashCode makeHash(Hasher[] hasherArr);

    AbstractCompositeHashFunction(HashFunction... functions2) {
        for (HashFunction function : functions2) {
            Preconditions.checkNotNull(function);
        }
        this.functions = functions2;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        final Hasher[] hashers = new Hasher[this.functions.length];
        for (int i = 0; i < hashers.length; i++) {
            hashers[i] = this.functions[i].newHasher();
        }
        return new Hasher() {
            /* class com.google.common.hash.AbstractCompositeHashFunction.AnonymousClass1 */

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putByte(byte b) {
                for (Hasher hasher : hashers) {
                    hasher.putByte(b);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBytes(byte[] bytes) {
                for (Hasher hasher : hashers) {
                    hasher.putBytes(bytes);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBytes(byte[] bytes, int off, int len) {
                for (Hasher hasher : hashers) {
                    hasher.putBytes(bytes, off, len);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putShort(short s) {
                for (Hasher hasher : hashers) {
                    hasher.putShort(s);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putInt(int i) {
                for (Hasher hasher : hashers) {
                    hasher.putInt(i);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putLong(long l) {
                for (Hasher hasher : hashers) {
                    hasher.putLong(l);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putFloat(float f) {
                for (Hasher hasher : hashers) {
                    hasher.putFloat(f);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putDouble(double d) {
                for (Hasher hasher : hashers) {
                    hasher.putDouble(d);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putBoolean(boolean b) {
                for (Hasher hasher : hashers) {
                    hasher.putBoolean(b);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putChar(char c) {
                for (Hasher hasher : hashers) {
                    hasher.putChar(c);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putUnencodedChars(CharSequence chars) {
                for (Hasher hasher : hashers) {
                    hasher.putUnencodedChars(chars);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
            public Hasher putString(CharSequence chars, Charset charset) {
                for (Hasher hasher : hashers) {
                    hasher.putString(chars, charset);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher
            public <T> Hasher putObject(T instance, Funnel<? super T> funnel) {
                for (Hasher hasher : hashers) {
                    hasher.putObject(instance, funnel);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher
            public HashCode hash() {
                return AbstractCompositeHashFunction.this.makeHash(hashers);
            }
        };
    }
}
