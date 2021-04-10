package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;
import sun.misc.Unsafe;

public class ThreadLocalRandom extends Random {
    static final String BAD_BOUND = "bound must be positive";
    static final String BAD_RANGE = "bound must be greater than origin";
    static final String BAD_SIZE = "size must be non-negative";
    private static final double DOUBLE_UNIT = 1.1102230246251565E-16d;
    private static final float FLOAT_UNIT = 5.9604645E-8f;
    private static final long GAMMA = -7046029254386353131L;
    private static final long PROBE;
    private static final int PROBE_INCREMENT = -1640531527;
    private static final long SECONDARY;
    private static final long SEED;
    private static final long SEEDER_INCREMENT = -4942790177534073029L;
    private static final Unsafe U = Unsafe.getUnsafe();
    static final ThreadLocalRandom instance = new ThreadLocalRandom();
    private static final ThreadLocal<Double> nextLocalGaussian = new ThreadLocal<>();
    private static final AtomicInteger probeGenerator = new AtomicInteger();
    private static final AtomicLong seeder = new AtomicLong(mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime()));
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("rnd", Long.TYPE), new ObjectStreamField("initialized", Boolean.TYPE)};
    private static final long serialVersionUID = -5851777807851030925L;
    boolean initialized = true;

    private static long mix64(long z) {
        long z2 = ((z >>> 33) ^ z) * -49064778989728563L;
        long z3 = ((z2 >>> 33) ^ z2) * -4265267296055464877L;
        return (z3 >>> 33) ^ z3;
    }

    private static int mix32(long z) {
        long z2 = ((z >>> 33) ^ z) * -49064778989728563L;
        return (int) ((((z2 >>> 33) ^ z2) * -4265267296055464877L) >>> 32);
    }

    private ThreadLocalRandom() {
    }

    static final void localInit() {
        int p = probeGenerator.addAndGet(PROBE_INCREMENT);
        int probe = p == 0 ? 1 : p;
        long seed = mix64(seeder.getAndAdd(SEEDER_INCREMENT));
        Thread t = Thread.currentThread();
        U.putLong(t, SEED, seed);
        U.putInt(t, PROBE, probe);
    }

    public static ThreadLocalRandom current() {
        if (U.getInt(Thread.currentThread(), PROBE) == 0) {
            localInit();
        }
        return instance;
    }

    @Override // java.util.Random
    public void setSeed(long seed) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public final long nextSeed() {
        Unsafe unsafe = U;
        Thread t = Thread.currentThread();
        long j = SEED;
        long r = U.getLong(t, j) + GAMMA;
        unsafe.putLong(t, j, r);
        return r;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Random
    public int next(int bits) {
        return (int) (mix64(nextSeed()) >>> (64 - bits));
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.Random
    public final long internalNextLong(long origin, long bound) {
        long r = mix64(nextSeed());
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
                u = mix64(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (r >= origin && r < bound) {
                    return r;
                }
                r = mix64(nextSeed());
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.Random
    public final int internalNextInt(int origin, int bound) {
        int r = mix32(nextSeed());
        if (origin >= bound) {
            return r;
        }
        int n = bound - origin;
        int m = n - 1;
        if ((n & m) == 0) {
            return (r & m) + origin;
        }
        if (n > 0) {
            int u = r >>> 1;
            while (true) {
                int r2 = u % n;
                if ((u + m) - r2 >= 0) {
                    return r2 + origin;
                }
                u = mix32(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (r >= origin && r < bound) {
                    return r;
                }
                r = mix32(nextSeed());
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.Random
    public final double internalNextDouble(double origin, double bound) {
        double r = ((double) (nextLong() >>> 11)) * DOUBLE_UNIT;
        if (origin >= bound) {
            return r;
        }
        double r2 = ((bound - origin) * r) + origin;
        if (r2 >= bound) {
            return Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
        }
        return r2;
    }

    @Override // java.util.Random
    public int nextInt() {
        return mix32(nextSeed());
    }

    @Override // java.util.Random
    public int nextInt(int bound) {
        if (bound > 0) {
            int r = mix32(nextSeed());
            int m = bound - 1;
            if ((bound & m) == 0) {
                return r & m;
            }
            int u = r >>> 1;
            while (true) {
                int r2 = u % bound;
                if ((u + m) - r2 >= 0) {
                    return r2;
                }
                u = mix32(nextSeed()) >>> 1;
            }
        } else {
            throw new IllegalArgumentException(BAD_BOUND);
        }
    }

    public int nextInt(int origin, int bound) {
        if (origin < bound) {
            return internalNextInt(origin, bound);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public long nextLong() {
        return mix64(nextSeed());
    }

    public long nextLong(long bound) {
        if (bound > 0) {
            long r = mix64(nextSeed());
            long m = bound - 1;
            if ((bound & m) == 0) {
                return r & m;
            }
            long u = r >>> 1;
            while (true) {
                long r2 = u % bound;
                if ((u + m) - r2 >= 0) {
                    return r2;
                }
                u = mix64(nextSeed()) >>> 1;
            }
        } else {
            throw new IllegalArgumentException(BAD_BOUND);
        }
    }

    public long nextLong(long origin, long bound) {
        if (origin < bound) {
            return internalNextLong(origin, bound);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public double nextDouble() {
        return ((double) (mix64(nextSeed()) >>> 11)) * DOUBLE_UNIT;
    }

    public double nextDouble(double bound) {
        if (bound > 0.0d) {
            double result = ((double) (mix64(nextSeed()) >>> 11)) * DOUBLE_UNIT * bound;
            if (result < bound) {
                return result;
            }
            return Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
        }
        throw new IllegalArgumentException(BAD_BOUND);
    }

    public double nextDouble(double origin, double bound) {
        if (origin < bound) {
            return internalNextDouble(origin, bound);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return mix32(nextSeed()) < 0;
    }

    @Override // java.util.Random
    public float nextFloat() {
        return ((float) (mix32(nextSeed()) >>> 8)) * FLOAT_UNIT;
    }

    @Override // java.util.Random
    public double nextGaussian() {
        Double d = nextLocalGaussian.get();
        if (d != null) {
            nextLocalGaussian.set(null);
            return d.doubleValue();
        }
        while (true) {
            double v1 = (nextDouble() * 2.0d) - 1.0d;
            double v2 = (nextDouble() * 2.0d) - 1.0d;
            double s = (v1 * v1) + (v2 * v2);
            if (s < 1.0d && s != 0.0d) {
                double multiplier = StrictMath.sqrt((StrictMath.log(s) * -2.0d) / s);
                nextLocalGaussian.set(new Double(v2 * multiplier));
                return v1 * multiplier;
            }
        }
    }

    @Override // java.util.Random
    public IntStream ints(long streamSize) {
        if (streamSize >= 0) {
            return StreamSupport.intStream(new RandomIntsSpliterator(0, streamSize, Integer.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    @Override // java.util.Random
    public IntStream ints() {
        return StreamSupport.intStream(new RandomIntsSpliterator(0, Long.MAX_VALUE, Integer.MAX_VALUE, 0), false);
    }

    @Override // java.util.Random
    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
        if (streamSize < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        } else if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.intStream(new RandomIntsSpliterator(0, streamSize, randomNumberOrigin, randomNumberBound), false);
        } else {
            throw new IllegalArgumentException(BAD_RANGE);
        }
    }

    @Override // java.util.Random
    public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.intStream(new RandomIntsSpliterator(0, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public LongStream longs(long streamSize) {
        if (streamSize >= 0) {
            return StreamSupport.longStream(new RandomLongsSpliterator(0, streamSize, Long.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    @Override // java.util.Random
    public LongStream longs() {
        return StreamSupport.longStream(new RandomLongsSpliterator(0, Long.MAX_VALUE, Long.MAX_VALUE, 0), false);
    }

    @Override // java.util.Random
    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
        if (streamSize < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        } else if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.longStream(new RandomLongsSpliterator(0, streamSize, randomNumberOrigin, randomNumberBound), false);
        } else {
            throw new IllegalArgumentException(BAD_RANGE);
        }
    }

    @Override // java.util.Random
    public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.longStream(new RandomLongsSpliterator(0, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public DoubleStream doubles(long streamSize) {
        if (streamSize >= 0) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, streamSize, Double.MAX_VALUE, 0.0d), false);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    @Override // java.util.Random
    public DoubleStream doubles() {
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, Long.MAX_VALUE, Double.MAX_VALUE, 0.0d), false);
    }

    @Override // java.util.Random
    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
        if (streamSize < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        } else if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, streamSize, randomNumberOrigin, randomNumberBound), false);
        } else {
            throw new IllegalArgumentException(BAD_RANGE);
        }
    }

    @Override // java.util.Random
    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        if (randomNumberOrigin < randomNumberBound) {
            return StreamSupport.doubleStream(new RandomDoublesSpliterator(0, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    private static final class RandomIntsSpliterator implements Spliterator.OfInt {
        final int bound;
        final long fence;
        long index;
        final int origin;

        RandomIntsSpliterator(long index2, long fence2, int origin2, int bound2) {
            this.index = index2;
            this.fence = fence2;
            this.origin = origin2;
            this.bound = bound2;
        }

        /* Return type fixed from 'java.util.concurrent.ThreadLocalRandom$RandomIntsSpliterator' to match base method */
        @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfInt trySplit() {
            long i = this.index;
            long m = (this.fence + i) >>> 1;
            if (m <= i) {
                return null;
            }
            this.index = m;
            return new RandomIntsSpliterator(i, m, this.origin, this.bound);
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
                consumer.accept(ThreadLocalRandom.current().internalNextInt(this.origin, this.bound));
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
                    int o = this.origin;
                    int b = this.bound;
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    do {
                        consumer.accept(rng.internalNextInt(o, b));
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

    private static final class RandomLongsSpliterator implements Spliterator.OfLong {
        final long bound;
        final long fence;
        long index;
        final long origin;

        RandomLongsSpliterator(long index2, long fence2, long origin2, long bound2) {
            this.index = index2;
            this.fence = fence2;
            this.origin = origin2;
            this.bound = bound2;
        }

        /* Return type fixed from 'java.util.concurrent.ThreadLocalRandom$RandomLongsSpliterator' to match base method */
        @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfLong trySplit() {
            long i = this.index;
            long m = (this.fence + i) >>> 1;
            if (m <= i) {
                return null;
            }
            this.index = m;
            return new RandomLongsSpliterator(i, m, this.origin, this.bound);
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
                consumer.accept(ThreadLocalRandom.current().internalNextLong(this.origin, this.bound));
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
                    long o = this.origin;
                    long b = this.bound;
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    do {
                        consumer.accept(rng.internalNextLong(o, b));
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

    private static final class RandomDoublesSpliterator implements Spliterator.OfDouble {
        final double bound;
        final long fence;
        long index;
        final double origin;

        RandomDoublesSpliterator(long index2, long fence2, double origin2, double bound2) {
            this.index = index2;
            this.fence = fence2;
            this.origin = origin2;
            this.bound = bound2;
        }

        /* Return type fixed from 'java.util.concurrent.ThreadLocalRandom$RandomDoublesSpliterator' to match base method */
        @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            long i = this.index;
            long m = (this.fence + i) >>> 1;
            if (m <= i) {
                return null;
            }
            this.index = m;
            return new RandomDoublesSpliterator(i, m, this.origin, this.bound);
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
                consumer.accept(ThreadLocalRandom.current().internalNextDouble(this.origin, this.bound));
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
                    double o = this.origin;
                    double b = this.bound;
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    do {
                        consumer.accept(rng.internalNextDouble(o, b));
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

    static final int getProbe() {
        return U.getInt(Thread.currentThread(), PROBE);
    }

    static final int advanceProbe(int probe) {
        int probe2 = probe ^ (probe << 13);
        int probe3 = probe2 ^ (probe2 >>> 17);
        int probe4 = probe3 ^ (probe3 << 5);
        U.putInt(Thread.currentThread(), PROBE, probe4);
        return probe4;
    }

    static final int nextSecondarySeed() {
        int r;
        Thread t = Thread.currentThread();
        int r2 = U.getInt(t, SECONDARY);
        if (r2 != 0) {
            int r3 = (r2 << 13) ^ r2;
            int r4 = r3 ^ (r3 >>> 17);
            r = r4 ^ (r4 << 5);
        } else {
            int r5 = mix32(seeder.getAndAdd(SEEDER_INCREMENT));
            if (r5 == 0) {
                r = 1;
            } else {
                r = r5;
            }
        }
        U.putInt(t, SECONDARY, r);
        return r;
    }

    static {
        try {
            SEED = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomSeed"));
            PROBE = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomProbe"));
            SECONDARY = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomSecondarySeed"));
            if (((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
                /* class java.util.concurrent.ThreadLocalRandom.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Boolean run() {
                    return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
                }
            })).booleanValue()) {
                byte[] seedBytes = SecureRandom.getSeed(8);
                long s = ((long) seedBytes[0]) & 255;
                for (int i = 1; i < 8; i++) {
                    s = (s << 8) | (((long) seedBytes[i]) & 255);
                }
                seeder.set(s);
            }
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("rnd", U.getLong(Thread.currentThread(), SEED));
        fields.put("initialized", true);
        s.writeFields();
    }

    private Object readResolve() {
        return current();
    }
}
