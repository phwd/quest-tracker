package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import sun.misc.Unsafe;

public class Random implements Serializable {
    private static final long seedOffset;
    private static final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("seed", Long.TYPE), new ObjectStreamField("nextNextGaussian", Double.TYPE), new ObjectStreamField("haveNextNextGaussian", Boolean.TYPE)};
    static final long serialVersionUID = 3905348978240129619L;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private boolean haveNextNextGaussian;
    private double nextNextGaussian;
    private final AtomicLong seed;

    private static long initialScramble(long j) {
        return (j ^ 25214903917L) & 281474976710655L;
    }

    public Random() {
        this(seedUniquifier() ^ System.nanoTime());
    }

    private static long seedUniquifier() {
        long j;
        long j2;
        do {
            j = seedUniquifier.get();
            j2 = 181783497276652981L * j;
        } while (!seedUniquifier.compareAndSet(j, j2));
        return j2;
    }

    static {
        try {
            seedOffset = unsafe.objectFieldOffset(Random.class.getDeclaredField("seed"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public Random(long j) {
        this.haveNextNextGaussian = false;
        if (getClass() == Random.class) {
            this.seed = new AtomicLong(initialScramble(j));
            return;
        }
        this.seed = new AtomicLong();
        setSeed(j);
    }

    public synchronized void setSeed(long j) {
        this.seed.set(initialScramble(j));
        this.haveNextNextGaussian = false;
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        long j;
        long j2;
        AtomicLong atomicLong = this.seed;
        do {
            j = atomicLong.get();
            j2 = ((25214903917L * j) + 11) & 281474976710655L;
        } while (!atomicLong.compareAndSet(j, j2));
        return (int) (j2 >>> (48 - i));
    }

    public long nextLong() {
        return (((long) next(32)) << 32) + ((long) next(32));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }
}
