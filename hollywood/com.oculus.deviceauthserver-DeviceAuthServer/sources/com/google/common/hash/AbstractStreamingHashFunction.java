package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

abstract class AbstractStreamingHashFunction implements HashFunction {
    AbstractStreamingHashFunction() {
    }

    @Override // com.google.common.hash.HashFunction
    public <T> HashCode hashObject(T instance, Funnel<? super T> funnel) {
        return newHasher().putObject(instance, funnel).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence input) {
        return newHasher().putUnencodedChars(input).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence input, Charset charset) {
        return newHasher().putString(input, charset).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashInt(int input) {
        return newHasher().putInt(input).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashLong(long input) {
        return newHasher().putLong(input).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] input) {
        return newHasher().putBytes(input).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] input, int off, int len) {
        return newHasher().putBytes(input, off, len).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher(int expectedInputSize) {
        Preconditions.checkArgument(expectedInputSize >= 0);
        return newHasher();
    }

    protected static abstract class AbstractStreamingHasher extends AbstractHasher {
        private final ByteBuffer buffer;
        private final int bufferSize;
        private final int chunkSize;

        /* access modifiers changed from: package-private */
        public abstract HashCode makeHash();

        /* access modifiers changed from: protected */
        public abstract void process(ByteBuffer byteBuffer);

        protected AbstractStreamingHasher(int chunkSize2) {
            this(chunkSize2, chunkSize2);
        }

        protected AbstractStreamingHasher(int chunkSize2, int bufferSize2) {
            Preconditions.checkArgument(bufferSize2 % chunkSize2 == 0);
            this.buffer = ByteBuffer.allocate(bufferSize2 + 7).order(ByteOrder.LITTLE_ENDIAN);
            this.bufferSize = bufferSize2;
            this.chunkSize = chunkSize2;
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer bb) {
            bb.position(bb.limit());
            bb.limit(this.chunkSize + 7);
            while (true) {
                int position = bb.position();
                int i = this.chunkSize;
                if (position < i) {
                    bb.putLong(0);
                } else {
                    bb.limit(i);
                    bb.flip();
                    process(bb);
                    return;
                }
            }
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putBytes(byte[] bytes) {
            return putBytes(bytes, 0, bytes.length);
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putBytes(byte[] bytes, int off, int len) {
            return putBytes(ByteBuffer.wrap(bytes, off, len).order(ByteOrder.LITTLE_ENDIAN));
        }

        private Hasher putBytes(ByteBuffer readBuffer) {
            if (readBuffer.remaining() <= this.buffer.remaining()) {
                this.buffer.put(readBuffer);
                munchIfFull();
                return this;
            }
            int bytesToCopy = this.bufferSize - this.buffer.position();
            for (int i = 0; i < bytesToCopy; i++) {
                this.buffer.put(readBuffer.get());
            }
            munch();
            while (readBuffer.remaining() >= this.chunkSize) {
                process(readBuffer);
            }
            this.buffer.put(readBuffer);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putUnencodedChars(CharSequence charSequence) {
            for (int i = 0; i < charSequence.length(); i++) {
                putChar(charSequence.charAt(i));
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putByte(byte b) {
            this.buffer.put(b);
            munchIfFull();
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putShort(short s) {
            this.buffer.putShort(s);
            munchIfFull();
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putChar(char c) {
            this.buffer.putChar(c);
            munchIfFull();
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putInt(int i) {
            this.buffer.putInt(i);
            munchIfFull();
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink, com.google.common.hash.Hasher
        public final Hasher putLong(long l) {
            this.buffer.putLong(l);
            munchIfFull();
            return this;
        }

        @Override // com.google.common.hash.Hasher
        public final <T> Hasher putObject(T instance, Funnel<? super T> funnel) {
            funnel.funnel(instance, this);
            return this;
        }

        @Override // com.google.common.hash.Hasher
        public final HashCode hash() {
            munch();
            this.buffer.flip();
            if (this.buffer.remaining() > 0) {
                processRemaining(this.buffer);
            }
            return makeHash();
        }

        private void munchIfFull() {
            if (this.buffer.remaining() < 8) {
                munch();
            }
        }

        private void munch() {
            this.buffer.flip();
            while (this.buffer.remaining() >= this.chunkSize) {
                process(this.buffer);
            }
            this.buffer.compact();
        }
    }
}
