package sun.nio.ch;

import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import sun.misc.Cleaner;
import sun.misc.Unsafe;
import sun.misc.VM;
import sun.security.action.GetPropertyAction;

public class Util {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long MAX_CACHED_BUFFER_SIZE = getMaxCachedBufferSize();
    private static final int TEMP_BUF_POOL_SIZE = IOUtil.IOV_MAX;
    private static ThreadLocal<BufferCache> bufferCache = new ThreadLocal<BufferCache>() {
        /* class sun.nio.ch.Util.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public BufferCache initialValue() {
            return new BufferCache();
        }
    };
    private static volatile String bugLevel = null;
    private static Unsafe unsafe = Unsafe.getUnsafe();

    private static long getMaxCachedBufferSize() {
        String s = (String) AccessController.doPrivileged(new PrivilegedAction<String>() {
            /* class sun.nio.ch.Util.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty("jdk.nio.maxCachedBufferSize");
            }
        });
        if (s == null) {
            return Long.MAX_VALUE;
        }
        try {
            long m = Long.parseLong(s);
            if (m >= 0) {
                return m;
            }
            return Long.MAX_VALUE;
        } catch (NumberFormatException e) {
            return Long.MAX_VALUE;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isBufferTooLarge(int size) {
        return ((long) size) > MAX_CACHED_BUFFER_SIZE;
    }

    /* access modifiers changed from: private */
    public static boolean isBufferTooLarge(ByteBuffer buf) {
        return isBufferTooLarge(buf.capacity());
    }

    /* access modifiers changed from: private */
    public static class BufferCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private ByteBuffer[] buffers = new ByteBuffer[Util.TEMP_BUF_POOL_SIZE];
        private int count;
        private int start;

        private int next(int i) {
            return (i + 1) % Util.TEMP_BUF_POOL_SIZE;
        }

        BufferCache() {
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer get(int size) {
            ByteBuffer bb;
            if (this.count == 0) {
                return null;
            }
            ByteBuffer[] buffers2 = this.buffers;
            ByteBuffer buf = buffers2[this.start];
            if (buf.capacity() < size) {
                buf = null;
                int i = this.start;
                while (true) {
                    int next = next(i);
                    i = next;
                    if (next != this.start && (bb = buffers2[i]) != null) {
                        if (bb.capacity() >= size) {
                            buf = bb;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (buf == null) {
                    return null;
                }
                buffers2[i] = buffers2[this.start];
            }
            int i2 = this.start;
            buffers2[i2] = null;
            this.start = next(i2);
            this.count--;
            buf.rewind();
            buf.limit(size);
            return buf;
        }

        /* access modifiers changed from: package-private */
        public boolean offerFirst(ByteBuffer buf) {
            if (this.count >= Util.TEMP_BUF_POOL_SIZE) {
                return false;
            }
            this.start = ((this.start + Util.TEMP_BUF_POOL_SIZE) - 1) % Util.TEMP_BUF_POOL_SIZE;
            this.buffers[this.start] = buf;
            this.count++;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean offerLast(ByteBuffer buf) {
            if (this.count >= Util.TEMP_BUF_POOL_SIZE) {
                return false;
            }
            this.buffers[(this.start + this.count) % Util.TEMP_BUF_POOL_SIZE] = buf;
            this.count++;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.count == 0;
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer removeFirst() {
            ByteBuffer[] byteBufferArr = this.buffers;
            int i = this.start;
            ByteBuffer buf = byteBufferArr[i];
            byteBufferArr[i] = null;
            this.start = next(i);
            this.count--;
            return buf;
        }
    }

    public static ByteBuffer getTemporaryDirectBuffer(int size) {
        if (isBufferTooLarge(size)) {
            return ByteBuffer.allocateDirect(size);
        }
        BufferCache cache = bufferCache.get();
        ByteBuffer buf = cache.get(size);
        if (buf != null) {
            return buf;
        }
        if (!cache.isEmpty()) {
            free(cache.removeFirst());
        }
        return ByteBuffer.allocateDirect(size);
    }

    public static void releaseTemporaryDirectBuffer(ByteBuffer buf) {
        offerFirstTemporaryDirectBuffer(buf);
    }

    static void offerFirstTemporaryDirectBuffer(ByteBuffer buf) {
        if (isBufferTooLarge(buf)) {
            free(buf);
        } else if (!bufferCache.get().offerFirst(buf)) {
            free(buf);
        }
    }

    static void offerLastTemporaryDirectBuffer(ByteBuffer buf) {
        if (isBufferTooLarge(buf)) {
            free(buf);
        } else if (!bufferCache.get().offerLast(buf)) {
            free(buf);
        }
    }

    private static void free(ByteBuffer buf) {
        Cleaner cleaner = ((DirectBuffer) buf).cleaner();
        if (cleaner != null) {
            cleaner.clean();
        }
    }

    static ByteBuffer[] subsequence(ByteBuffer[] bs, int offset, int length) {
        if (offset == 0 && length == bs.length) {
            return bs;
        }
        ByteBuffer[] bs2 = new ByteBuffer[length];
        for (int i = 0; i < length; i++) {
            bs2[i] = bs[offset + i];
        }
        return bs2;
    }

    static <E> Set<E> ungrowableSet(final Set<E> s) {
        return new Set<E>() {
            /* class sun.nio.ch.Util.AnonymousClass3 */

            @Override // java.util.Collection, java.util.Set
            public int size() {
                return Set.this.size();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return Set.this.isEmpty();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean contains(Object o) {
                return Set.this.contains(o);
            }

            @Override // java.util.Collection, java.util.Set
            public Object[] toArray() {
                return Set.this.toArray();
            }

            @Override // java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] a) {
                return (T[]) Set.this.toArray(a);
            }

            public String toString() {
                return Set.this.toString();
            }

            @Override // java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<E> iterator() {
                return Set.this.iterator();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean equals(Object o) {
                return Set.this.equals(o);
            }

            @Override // java.util.Collection, java.util.Set
            public int hashCode() {
                return Set.this.hashCode();
            }

            @Override // java.util.Collection, java.util.Set
            public void clear() {
                Set.this.clear();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean remove(Object o) {
                return Set.this.remove(o);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> coll) {
                return Set.this.containsAll(coll);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> coll) {
                return Set.this.removeAll(coll);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> coll) {
                return Set.this.retainAll(coll);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static byte _get(long a) {
        return unsafe.getByte(a);
    }

    private static void _put(long a, byte b) {
        unsafe.putByte(a, b);
    }

    static void erase(ByteBuffer bb) {
        unsafe.setMemory(((DirectBuffer) bb).address(), (long) bb.capacity(), (byte) 0);
    }

    static Unsafe unsafe() {
        return unsafe;
    }

    static boolean atBugLevel(String bl) {
        if (bugLevel == null) {
            if (!VM.isBooted()) {
                return false;
            }
            String value = (String) AccessController.doPrivileged(new GetPropertyAction("sun.nio.ch.bugLevel"));
            bugLevel = value != null ? value : "";
        }
        return bugLevel.equals(bl);
    }
}
