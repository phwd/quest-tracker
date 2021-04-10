package com.google.common.hash;

import com.android.org.bouncycastle.iana.AEADAlgorithm;
import com.google.common.hash.AbstractStreamingHashFunction;
import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class Murmur3_128HashFunction extends AbstractStreamingHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int seed;

    Murmur3_128HashFunction(int seed2) {
        this.seed = seed2;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 128;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Murmur3_128HashFunction) || this.seed != ((Murmur3_128HashFunction) object).seed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    private static final class Murmur3_128Hasher extends AbstractStreamingHashFunction.AbstractStreamingHasher {
        private static final long C1 = -8663945395140668459L;
        private static final long C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;
        private long h1;
        private long h2;
        private int length = 0;

        Murmur3_128Hasher(int seed) {
            super(16);
            this.h1 = (long) seed;
            this.h2 = (long) seed;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractStreamingHashFunction.AbstractStreamingHasher
        public void process(ByteBuffer bb) {
            bmix64(bb.getLong(), bb.getLong());
            this.length += 16;
        }

        private void bmix64(long k1, long k2) {
            this.h1 ^= mixK1(k1);
            this.h1 = Long.rotateLeft(this.h1, 27);
            long j = this.h1;
            long j2 = this.h2;
            this.h1 = j + j2;
            this.h1 = (this.h1 * 5) + 1390208809;
            this.h2 = mixK2(k2) ^ j2;
            this.h2 = Long.rotateLeft(this.h2, 31);
            this.h2 += this.h1;
            this.h2 = (this.h2 * 5) + 944331445;
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractStreamingHashFunction.AbstractStreamingHasher
        public void processRemaining(ByteBuffer bb) {
            long k1;
            long k12 = 0;
            long k2 = 0;
            this.length += bb.remaining();
            switch (bb.remaining()) {
                case 1:
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 2:
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(1))) << 8;
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 3:
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(2))) << 16;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(1))) << 8;
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 4:
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(3))) << 24;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(2))) << 16;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(1))) << 8;
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 5:
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(4))) << 32;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(3))) << 24;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(2))) << 16;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(1))) << 8;
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 6:
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(5))) << 40;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(4))) << 32;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(3))) << 24;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(2))) << 16;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(1))) << 8;
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 7:
                    k12 = 0 ^ (((long) UnsignedBytes.toInt(bb.get(6))) << 48);
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(5))) << 40;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(4))) << 32;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(3))) << 24;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(2))) << 16;
                    k12 ^= ((long) UnsignedBytes.toInt(bb.get(1))) << 8;
                    k1 = k12 ^ ((long) UnsignedBytes.toInt(bb.get(0)));
                    break;
                case 8:
                    k1 = 0 ^ bb.getLong();
                    break;
                case 9:
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case 10:
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case 11:
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(10))) << 16;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case 12:
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(11))) << 24;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(10))) << 16;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case 13:
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(12))) << 32;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(11))) << 24;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(10))) << 16;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case AEADAlgorithm.AEAD_AES_256_CCM_SHORT_12:
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(13))) << 40;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(12))) << 32;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(11))) << 24;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(10))) << 16;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case AEADAlgorithm.AEAD_AES_SIV_CMAC_256:
                    k2 = 0 ^ (((long) UnsignedBytes.toInt(bb.get(14))) << 48);
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(13))) << 40;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(12))) << 32;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(11))) << 24;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(10))) << 16;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                default:
                    throw new AssertionError("Should never get here.");
            }
            this.h1 ^= mixK1(k1);
            this.h2 ^= mixK2(k2);
        }

        @Override // com.google.common.hash.AbstractStreamingHashFunction.AbstractStreamingHasher
        public HashCode makeHash() {
            long j = this.h1;
            int i = this.length;
            this.h1 = j ^ ((long) i);
            this.h2 ^= (long) i;
            long j2 = this.h1;
            long j3 = this.h2;
            this.h1 = j2 + j3;
            long j4 = this.h1;
            this.h2 = j3 + j4;
            this.h1 = fmix64(j4);
            this.h2 = fmix64(this.h2);
            long j5 = this.h1;
            long j6 = this.h2;
            this.h1 = j5 + j6;
            this.h2 = j6 + this.h1;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array());
        }

        private static long fmix64(long k) {
            long k2 = (k ^ (k >>> 33)) * -49064778989728563L;
            long k3 = (k2 ^ (k2 >>> 33)) * -4265267296055464877L;
            return k3 ^ (k3 >>> 33);
        }

        private static long mixK1(long k1) {
            return Long.rotateLeft(k1 * C1, 31) * C2;
        }

        private static long mixK2(long k2) {
            return Long.rotateLeft(k2 * C2, 33) * C1;
        }
    }
}
