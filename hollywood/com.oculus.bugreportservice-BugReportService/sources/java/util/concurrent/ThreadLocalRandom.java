package java.util.concurrent;

import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import sun.misc.Unsafe;

public class ThreadLocalRandom extends Random {
    private static final long PROBE;
    private static final long SECONDARY;
    private static final long SEED;
    private static final Unsafe U = Unsafe.getUnsafe();
    static final ThreadLocalRandom instance = new ThreadLocalRandom();
    private static final ThreadLocal nextLocalGaussian = new ThreadLocal();
    private static final AtomicInteger probeGenerator = new AtomicInteger();
    private static final AtomicLong seeder = new AtomicLong(mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime()));
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("rnd", Long.TYPE), new ObjectStreamField("initialized", Boolean.TYPE)};
    private static final long serialVersionUID = -5851777807851030925L;
    boolean initialized = true;

    private static long mix64(long j) {
        long j2 = (j ^ (j >>> 33)) * -49064778989728563L;
        long j3 = (j2 ^ (j2 >>> 33)) * -4265267296055464877L;
        return j3 ^ (j3 >>> 33);
    }

    private ThreadLocalRandom() {
    }

    static final void localInit() {
        int addAndGet = probeGenerator.addAndGet(-1640531527);
        if (addAndGet == 0) {
            addAndGet = 1;
        }
        long mix64 = mix64(seeder.getAndAdd(-4942790177534073029L));
        Thread currentThread = Thread.currentThread();
        U.putLong(currentThread, SEED, mix64);
        U.putInt(currentThread, PROBE, addAndGet);
    }

    public static ThreadLocalRandom current() {
        if (U.getInt(Thread.currentThread(), PROBE) == 0) {
            localInit();
        }
        return instance;
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public final long nextSeed() {
        Unsafe unsafe = U;
        Thread currentThread = Thread.currentThread();
        long j = SEED;
        long j2 = -7046029254386353131L + U.getLong(currentThread, j);
        unsafe.putLong(currentThread, j, j2);
        return j2;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Random
    public int next(int i) {
        return (int) (mix64(nextSeed()) >>> (64 - i));
    }

    static final int getProbe() {
        return U.getInt(Thread.currentThread(), PROBE);
    }

    static final int advanceProbe(int i) {
        int i2 = i ^ (i << 13);
        int i3 = i2 ^ (i2 >>> 17);
        int i4 = i3 ^ (i3 << 5);
        U.putInt(Thread.currentThread(), PROBE, i4);
        return i4;
    }

    static {
        try {
            SEED = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomSeed"));
            PROBE = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomProbe"));
            SECONDARY = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomSecondarySeed"));
            if (((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.concurrent.ThreadLocalRandom.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Boolean run() {
                    return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
                }
            })).booleanValue()) {
                byte[] seed = SecureRandom.getSeed(8);
                long j = ((long) seed[0]) & 255;
                for (int i = 1; i < 8; i++) {
                    j = (j << 8) | (((long) seed[i]) & 255);
                }
                seeder.set(j);
            }
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }

    private Object readResolve() {
        return current();
    }
}
