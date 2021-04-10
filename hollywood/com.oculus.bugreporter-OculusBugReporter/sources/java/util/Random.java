package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;
import sun.misc.Unsafe;

public class Random implements Serializable {
    static final String BadBound = "bound must be positive";
    static final String BadRange = "bound must be greater than origin";
    static final String BadSize = "size must be non-negative";
    private static final double DOUBLE_UNIT = 1.1102230246251565E-16d;
    private static final long addend = 11;
    private static final long mask = 281474976710655L;
    private static final long multiplier = 25214903917L;
    private static final long seedOffset;
    private static final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("seed", Long.TYPE), new ObjectStreamField("nextNextGaussian", Double.TYPE), new ObjectStreamField("haveNextNextGaussian", Boolean.TYPE)};
    static final long serialVersionUID = 3905348978240129619L;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private boolean haveNextNextGaussian;
    private double nextNextGaussian;
    private final AtomicLong seed;

    public Random() {
        this(seedUniquifier() ^ System.nanoTime());
    }

    private static long seedUniquifier() {
        long current;
        long next;
        do {
            current = seedUniquifier.get();
            next = 181783497276652981L * current;
        } while (!seedUniquifier.compareAndSet(current, next));
        return next;
    }

    static {
        try {
            seedOffset = unsafe.objectFieldOffset(Random.class.getDeclaredField("seed"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public Random(long seed2) {
        this.haveNextNextGaussian = false;
        if (getClass() == Random.class) {
            this.seed = new AtomicLong(initialScramble(seed2));
            return;
        }
        this.seed = new AtomicLong();
        setSeed(seed2);
    }

    private static long initialScramble(long seed2) {
        return (multiplier ^ seed2) & mask;
    }

    public synchronized void setSeed(long seed2) {
        this.seed.set(initialScramble(seed2));
        this.haveNextNextGaussian = false;
    }

    /* access modifiers changed from: protected */
    public int next(int bits) {
        long oldseed;
        long nextseed;
        AtomicLong seed2 = this.seed;
        do {
            oldseed = seed2.get();
            nextseed = ((multiplier * oldseed) + addend) & mask;
        } while (!seed2.compareAndSet(oldseed, nextseed));
        return (int) (nextseed >>> (48 - bits));
    }

    public void nextBytes(byte[] bytes) {
        int i = 0;
        int len = bytes.length;
        while (i < len) {
            int rnd = nextInt();
            int i2 = Math.min(len - i, 4);
            while (true) {
                int n = i2 - 1;
                if (i2 > 0) {
                    bytes[i] = (byte) rnd;
                    rnd >>= 8;
                    i++;
                    i2 = n;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final long internalNextLong(long origin, long bound) {
        long r = nextLong();
        if (origin >= bound) {
            return r;
        }
        long n = bound - origin;
        long m = n - 1;
        if ((n & m) == 0) {
            return (r & m) + origin;
        }
        if (n > 0) {
            long u = r >>> 1;
            while (true) {
                long r2 = u % n;
                if ((u + m) - r2 >= 0) {
                    return r2 + origin;
                }
                u = nextLong() >>> 1;
            }
        } else {
            while (true) {
                if (r >= origin && r < bound) {
                    return r;
                }
                r = nextLong();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final int internalNextInt(int origin, int bound) {
        if (origin >= bound) {
            return nextInt();
        }
        int n = bound - origin;
        if (n > 0) {
            return nextInt(n) + origin;
        }
        while (true) {
            int r = nextInt();
            if (r >= origin && r < bound) {
                return r;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final double internalNextDouble(double origin, double bound) {
        double r = nextDouble();
        if (origin >= bound) {
            return r;
        }
        double r2 = ((bound - origin) * r) + origin;
        if (r2 >= bound) {
            return Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
        }
        return r2;
    }

    public int nextInt() {
        return next(32);
    }

    public int nextInt(int bound) {
        if (bound > 0) {
            int r = next(31);
            int m = bound - 1;
            if ((bound & m) == 0) {
                return (int) ((((long) bound) * ((long) r)) >> 31);
            }
            int u = r;
            while (true) {
                int r2 = u % bound;
                if ((u - r2) + m >= 0) {
                    return r2;
                }
                u = next(31);
            }
        } else {
            throw new IllegalArgumentException(BadBound);
        }
    }

    public long nextLong() {
        return (((long) next(32)) << 32) + ((long) next(32));
    }

    public boolean nextBoolean() {
        return next(1) != 0;
    }

    public float nextFloat() {
        return ((float) next(24)) / 1.6777216E7f;
    }

    public double nextDouble() {
        return ((double) ((((long) next(26)) << 27) + ((long) next(27)))) * DOUBLE_UNIT;
    }

    public synchronized double nextGaussian() {
        if (this.haveNextNextGaussian) {
            this.haveNextNextGaussian = false;
            return this.nextNextGaussian;
        }
        while (true) {
            double v1 = (nextDouble() * 2.0d) - 1.0d;
            double v2 = (nextDouble() * 2.0d) - 1.0d;
            double s = (v1 * v1) + (v2 * v2);
            if (s < 1.0d && s != 0.0d) {
                double multiplier2 = StrictMath.sqrt((StrictMath.log(s) * -2.0d) / s);
                this.nextNextGaussian = v2 * multiplier2;
                this.haveNextNextGaussian = true;
                return v1 * multiplier2;
            }
        }
    }

    public IntStream ints(long streamSize) {
        if (streamSize >= 0) {
            return StreamSupport.intStream(new RandomIntsSpliterator(this, 0, streamSize, Integer.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException(BadSize);
    }

    public IntStream ints() {
        return StreamSupport.intStream(new RandomIntsSpliterator(this, 0, Long.MAX_VALUE, Integer.MAX_VALUE, 0), false);
    }

    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
        if (streamSize < 0) {
            throw new IllegalArgumentException(BadSize);
        } else if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.intStream(new RandomIntsSpliterator(this, 0, streamSize, randomNumberOrigin, randomNumberBound), false);
        } else {
            throw new IllegalArgumentException(BadRange);
        }
    }

    public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.intStream(new RandomIntsSpliterator(this, 0, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound), false);
        }
        throw new IllegalArgumentException(BadRange);
    }

    public LongStream longs(long streamSize) {
        if (streamSize >= 0) {
            return StreamSupport.longStream(new RandomLongsSpliterator(this, 0, streamSize, Long.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException(BadSize);
    }

    public LongStream longs() {
        return StreamSupport.longStream(new RandomLongsSpliterator(this, 0, Long.MAX_VALUE, Long.MAX_VALUE, 0), false);
    }

    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
        if (streamSize < 0) {
            throw new IllegalArgumentException(BadSize);
        } else if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.longStream(new RandomLongsSpliterator(this, 0, streamSize, randomNumberOrigin, randomNumberBound), false);
        } else {
            throw new IllegalArgumentException(BadRange);
        }
    }

    public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.longStream(new RandomLongsSpliterator(this, 0, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound), false);
        }
        throw new IllegalArgumentException(BadRange);
    }

    public DoubleStream doubles(long streamSize) {
        if (streamSize >= 0) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0, streamSize, Double.MAX_VALUE, 0.0d), false);
        }
        throw new IllegalArgumentException(BadSize);
    }

    public DoubleStream doubles() {
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0, Long.MAX_VALUE, Double.MAX_VALUE, 0.0d), false);
    }

    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
        if (streamSize < 0) {
            throw new IllegalArgumentException(BadSize);
        } else if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0, streamSize, randomNumberOrigin, randomNumberBound), false);
        } else {
            throw new IllegalArgumentException(BadRange);
        }
    }

    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound), false);
        }
        throw new IllegalArgumentException(BadRange);
    }

    static final class RandomIntsSpliterator implements Spliterator.OfInt {
        final int bound;
        final long fence;
        long index;
        final int origin;
        final Random rng;

        RandomIntsSpliterator(Random rng2, long index2, long fence2, int origin2, int bound2) {
            this.rng = rng2;
            this.index = index2;
            this.fence = fence2;
            this.origin = origin2;
            this.bound = bound2;
        }

        /* Return type fixed from 'java.util.Random$RandomIntsSpliterator' to match base method */
        @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfInt trySplit() {
            long i = this.index;
            long m = (this.fence + i) >>> 1;
            if (m <= i) {
                return null;
            }
            Random random = this.rng;
            this.index = m;
            return new RandomIntsSpliterator(random, i, m, this.origin, this.bound);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        @Override // java.util.Spliterator.OfInt
        public boolean tryAdvance(IntConsumer consumer) {
            if (consumer != null) {
                long i = this.index;
                if (i >= this.fence) {
                    return false;
                }
                consumer.accept(this.rng.internalNextInt(this.origin, this.bound));
                this.index = 1 + i;
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfInt
        public void forEachRemaining(IntConsumer consumer) {
            long j;
            if (consumer != null) {
                long i = this.index;
                long f = this.fence;
                if (i < f) {
                    this.index = f;
                    Random r = this.rng;
                    int o = this.origin;
                    int b = this.bound;
                    do {
                        consumer.accept(r.internalNextInt(o, b));
                        j = 1 + i;
                        i = j;
                    } while (j < f);
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    static final class RandomLongsSpliterator implements Spliterator.OfLong {
        final long bound;
        final long fence;
        long index;
        final long origin;
        final Random rng;

        RandomLongsSpliterator(Random rng2, long index2, long fence2, long origin2, long bound2) {
            this.rng = rng2;
            this.index = index2;
            this.fence = fence2;
            this.origin = origin2;
            this.bound = bound2;
        }

        /* Return type fixed from 'java.util.Random$RandomLongsSpliterator' to match base method */
        @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfLong trySplit() {
            long i = this.index;
            long m = (this.fence + i) >>> 1;
            if (m <= i) {
                return null;
            }
            Random random = this.rng;
            this.index = m;
            return new RandomLongsSpliterator(random, i, m, this.origin, this.bound);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        @Override // java.util.Spliterator.OfLong
        public boolean tryAdvance(LongConsumer consumer) {
            if (consumer != null) {
                long i = this.index;
                if (i >= this.fence) {
                    return false;
                }
                consumer.accept(this.rng.internalNextLong(this.origin, this.bound));
                this.index = 1 + i;
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfLong
        public void forEachRemaining(LongConsumer consumer) {
            long j;
            if (consumer != null) {
                long i = this.index;
                long f = this.fence;
                if (i < f) {
                    this.index = f;
                    Random r = this.rng;
                    long o = this.origin;
                    long b = this.bound;
                    do {
                        consumer.accept(r.internalNextLong(o, b));
                        j = 1 + i;
                        i = j;
                    } while (j < f);
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    static final class RandomDoublesSpliterator implements Spliterator.OfDouble {
        final double bound;
        final long fence;
        long index;
        final double origin;
        final Random rng;

        RandomDoublesSpliterator(Random rng2, long index2, long fence2, double origin2, double bound2) {
            this.rng = rng2;
            this.index = index2;
            this.fence = fence2;
            this.origin = origin2;
            this.bound = bound2;
        }

        /* Return type fixed from 'java.util.Random$RandomDoublesSpliterator' to match base method */
        @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            long i = this.index;
            long m = (this.fence + i) >>> 1;
            if (m <= i) {
                return null;
            }
            Random random = this.rng;
            this.index = m;
            return new RandomDoublesSpliterator(random, i, m, this.origin, this.bound);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        @Override // java.util.Spliterator.OfDouble
        public boolean tryAdvance(DoubleConsumer consumer) {
            if (consumer != null) {
                long i = this.index;
                if (i >= this.fence) {
                    return false;
                }
                consumer.accept(this.rng.internalNextDouble(this.origin, this.bound));
                this.index = 1 + i;
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfDouble
        public void forEachRemaining(DoubleConsumer consumer) {
            long j;
            if (consumer != null) {
                long i = this.index;
                long f = this.fence;
                if (i < f) {
                    this.index = f;
                    Random r = this.rng;
                    double o = this.origin;
                    double b = this.bound;
                    do {
                        consumer.accept(r.internalNextDouble(o, b));
                        j = 1 + i;
                        i = j;
                    } while (j < f);
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = s.readFields();
        long seedVal = fields.get("seed", -1L);
        if (seedVal >= 0) {
            resetSeed(seedVal);
            this.nextNextGaussian = fields.get("nextNextGaussian", 0.0d);
            this.haveNextNextGaussian = fields.get("haveNextNextGaussian", false);
            return;
        }
        throw new StreamCorruptedException("Random: invalid seed");
    }

    private synchronized void writeObject(ObjectOutputStream s) throws IOException {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("seed", this.seed.get());
        fields.put("nextNextGaussian", this.nextNextGaussian);
        fields.put("haveNextNextGaussian", this.haveNextNextGaussian);
        s.writeFields();
    }

    private void resetSeed(long seedVal) {
        unsafe.putObjectVolatile(this, seedOffset, new AtomicLong(seedVal));
    }
}
