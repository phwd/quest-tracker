package sun.nio.ch;

import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class Util {
    private static final long MAX_CACHED_BUFFER_SIZE = getMaxCachedBufferSize();
    private static final int TEMP_BUF_POOL_SIZE = IOUtil.IOV_MAX;
    private static ThreadLocal bufferCache = new ThreadLocal() {
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
        String str = (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.nio.ch.Util.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty("jdk.nio.maxCachedBufferSize");
            }
        });
        if (str == null) {
            return Long.MAX_VALUE;
        }
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong >= 0) {
                return parseLong;
            }
            return Long.MAX_VALUE;
        } catch (NumberFormatException unused) {
            return Long.MAX_VALUE;
        }
    }

    private static boolean isBufferTooLarge(int i) {
        return ((long) i) > MAX_CACHED_BUFFER_SIZE;
    }

    private static boolean isBufferTooLarge(ByteBuffer byteBuffer) {
        return isBufferTooLarge(byteBuffer.capacity());
    }

    /* access modifiers changed from: private */
    public static class BufferCache {
        private ByteBuffer[] buffers = new ByteBuffer[Util.TEMP_BUF_POOL_SIZE];
        private int count;
        private int start;

        private int next(int i) {
            return (i + 1) % Util.TEMP_BUF_POOL_SIZE;
        }

        BufferCache() {
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer get(int i) {
            ByteBuffer byteBuffer;
            if (this.count == 0) {
                return null;
            }
            ByteBuffer[] byteBufferArr = this.buffers;
            ByteBuffer byteBuffer2 = byteBufferArr[this.start];
            if (byteBuffer2.capacity() < i) {
                int i2 = this.start;
                while (true) {
                    i2 = next(i2);
                    if (i2 != this.start && (byteBuffer = byteBufferArr[i2]) != null) {
                        if (byteBuffer.capacity() >= i) {
                            break;
                        }
                    } else {
                        byteBuffer = null;
                    }
                }
                byteBuffer = null;
                if (byteBuffer == null) {
                    return null;
                }
                byteBufferArr[i2] = byteBufferArr[this.start];
                byteBuffer2 = byteBuffer;
            }
            int i3 = this.start;
            byteBufferArr[i3] = null;
            this.start = next(i3);
            this.count--;
            byteBuffer2.rewind();
            byteBuffer2.limit(i);
            return byteBuffer2;
        }

        /* access modifiers changed from: package-private */
        public boolean offerFirst(ByteBuffer byteBuffer) {
            if (this.count >= Util.TEMP_BUF_POOL_SIZE) {
                return false;
            }
            this.start = ((this.start + Util.TEMP_BUF_POOL_SIZE) - 1) % Util.TEMP_BUF_POOL_SIZE;
            this.buffers[this.start] = byteBuffer;
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
            ByteBuffer byteBuffer = byteBufferArr[i];
            byteBufferArr[i] = null;
            this.start = next(i);
            this.count--;
            return byteBuffer;
        }
    }

    public static ByteBuffer getTemporaryDirectBuffer(int i) {
        if (isBufferTooLarge(i)) {
            return ByteBuffer.allocateDirect(i);
        }
        BufferCache bufferCache2 = (BufferCache) bufferCache.get();
        ByteBuffer byteBuffer = bufferCache2.get(i);
        if (byteBuffer != null) {
            return byteBuffer;
        }
        if (!bufferCache2.isEmpty()) {
            free(bufferCache2.removeFirst());
        }
        return ByteBuffer.allocateDirect(i);
    }

    static void offerFirstTemporaryDirectBuffer(ByteBuffer byteBuffer) {
        if (isBufferTooLarge(byteBuffer)) {
            free(byteBuffer);
        } else if (!((BufferCache) bufferCache.get()).offerFirst(byteBuffer)) {
            free(byteBuffer);
        }
    }

    private static void free(ByteBuffer byteBuffer) {
        Cleaner cleaner = ((DirectBuffer) byteBuffer).cleaner();
        if (cleaner != null) {
            cleaner.clean();
        }
    }
}
