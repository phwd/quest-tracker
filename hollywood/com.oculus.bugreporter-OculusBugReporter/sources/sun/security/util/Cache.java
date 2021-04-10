package sun.security.util;

import java.util.Arrays;
import java.util.Map;

public abstract class Cache<K, V> {

    public interface CacheVisitor<K, V> {
        void visit(Map<K, V> map);
    }

    public abstract void accept(CacheVisitor<K, V> cacheVisitor);

    public abstract void clear();

    public abstract V get(Object obj);

    public abstract void put(K k, V v);

    public abstract void remove(Object obj);

    public abstract void setCapacity(int i);

    public abstract void setTimeout(int i);

    public abstract int size();

    protected Cache() {
    }

    public static <K, V> Cache<K, V> newSoftMemoryCache(int size) {
        return new MemoryCache(true, size);
    }

    public static <K, V> Cache<K, V> newSoftMemoryCache(int size, int timeout) {
        return new MemoryCache(true, size, timeout);
    }

    public static <K, V> Cache<K, V> newHardMemoryCache(int size) {
        return new MemoryCache(false, size);
    }

    public static <K, V> Cache<K, V> newNullCache() {
        return (Cache<K, V>) NullCache.INSTANCE;
    }

    public static <K, V> Cache<K, V> newHardMemoryCache(int size, int timeout) {
        return new MemoryCache(false, size, timeout);
    }

    public static class EqualByteArray {
        private final byte[] b;
        private volatile int hash;

        public EqualByteArray(byte[] b2) {
            this.b = b2;
        }

        public int hashCode() {
            int h = this.hash;
            if (h != 0) {
                return h;
            }
            int h2 = this.b.length + 1;
            int i = 0;
            while (true) {
                byte[] bArr = this.b;
                if (i < bArr.length) {
                    h2 += (bArr[i] & 255) * 37;
                    i++;
                } else {
                    this.hash = h2;
                    return h2;
                }
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EqualByteArray)) {
                return false;
            }
            return Arrays.equals(this.b, ((EqualByteArray) obj).b);
        }
    }
}
