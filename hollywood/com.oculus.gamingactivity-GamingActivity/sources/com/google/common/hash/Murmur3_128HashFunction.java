package com.google.common.hash;

import androidx.core.view.MotionEventCompat;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@Immutable
public final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
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

    public boolean equals(@NullableDecl Object object) {
        if (!(object instanceof Murmur3_128HashFunction) || this.seed != ((Murmur3_128HashFunction) object).seed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    private static final class Murmur3_128Hasher extends AbstractStreamingHasher {
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
        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer bb) {
            bmix64(bb.getLong(), bb.getLong());
            this.length += 16;
        }

        private void bmix64(long k1, long k2) {
            this.h1 ^= mixK1(k1);
            this.h1 = Long.rotateLeft(this.h1, 27);
            this.h1 += this.h2;
            this.h1 = (this.h1 * 5) + 1390208809;
            this.h2 ^= mixK2(k2);
            this.h2 = Long.rotateLeft(this.h2, 31);
            this.h2 += this.h1;
            this.h2 = (this.h2 * 5) + 944331445;
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractStreamingHasher
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
                case MotionEventCompat.AXIS_RZ:
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(13))) << 40;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(12))) << 32;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(11))) << 24;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(10))) << 16;
                    k2 ^= ((long) UnsignedBytes.toInt(bb.get(9))) << 8;
                    k2 ^= (long) UnsignedBytes.toInt(bb.get(8));
                    k1 = 0 ^ bb.getLong();
                    break;
                case MotionEventCompat.AXIS_HAT_X:
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

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            this.h1 ^= (long) this.length;
            this.h2 ^= (long) this.length;
            this.h1 += this.h2;
            this.h2 += this.h1;
            this.h1 = fmix64(this.h1);
            this.h2 = fmix64(this.h2);
            this.h1 += this.h2;
            this.h2 += this.h1;
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
