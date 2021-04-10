package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;

public class IOUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int IOV_MAX = iovMax();

    public static native void configureBlocking(FileDescriptor fileDescriptor, boolean z) throws IOException;

    static native boolean drain(int i) throws IOException;

    static native int fdLimit();

    public static native int fdVal(FileDescriptor fileDescriptor);

    static native int iovMax();

    static native long makePipe(boolean z);

    static native boolean randomBytes(byte[] bArr);

    static native void setfdVal(FileDescriptor fileDescriptor, int i);

    private IOUtil() {
    }

    static int write(FileDescriptor fd, ByteBuffer src, long position, NativeDispatcher nd) throws IOException {
        if (src instanceof DirectBuffer) {
            return writeFromNativeBuffer(fd, src, position, nd);
        }
        int pos = src.position();
        int lim = src.limit();
        ByteBuffer bb = Util.getTemporaryDirectBuffer(pos <= lim ? lim - pos : 0);
        try {
            bb.put(src);
            bb.flip();
            src.position(pos);
            int n = writeFromNativeBuffer(fd, bb, position, nd);
            if (n > 0) {
                src.position(pos + n);
            }
            return n;
        } finally {
            Util.offerFirstTemporaryDirectBuffer(bb);
        }
    }

    private static int writeFromNativeBuffer(FileDescriptor fd, ByteBuffer bb, long position, NativeDispatcher nd) throws IOException {
        int written;
        int pos = bb.position();
        int lim = bb.limit();
        int rem = pos <= lim ? lim - pos : 0;
        if (rem == 0) {
            return 0;
        }
        if (position != -1) {
            written = nd.pwrite(fd, ((long) pos) + ((DirectBuffer) bb).address(), rem, position);
        } else {
            written = nd.write(fd, ((DirectBuffer) bb).address() + ((long) pos), rem);
        }
        if (written > 0) {
            bb.position(pos + written);
        }
        return written;
    }

    static long write(FileDescriptor fd, ByteBuffer[] bufs, NativeDispatcher nd) throws IOException {
        return write(fd, bufs, 0, bufs.length, nd);
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static long write(java.io.FileDescriptor r18, java.nio.ByteBuffer[] r19, int r20, int r21, sun.nio.ch.NativeDispatcher r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 250
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.IOUtil.write(java.io.FileDescriptor, java.nio.ByteBuffer[], int, int, sun.nio.ch.NativeDispatcher):long");
    }

    static int read(FileDescriptor fd, ByteBuffer dst, long position, NativeDispatcher nd) throws IOException {
        if (dst.isReadOnly()) {
            throw new IllegalArgumentException("Read-only buffer");
        } else if (dst instanceof DirectBuffer) {
            return readIntoNativeBuffer(fd, dst, position, nd);
        } else {
            ByteBuffer bb = Util.getTemporaryDirectBuffer(dst.remaining());
            try {
                int n = readIntoNativeBuffer(fd, bb, position, nd);
                bb.flip();
                if (n > 0) {
                    dst.put(bb);
                }
                return n;
            } finally {
                Util.offerFirstTemporaryDirectBuffer(bb);
            }
        }
    }

    private static int readIntoNativeBuffer(FileDescriptor fd, ByteBuffer bb, long position, NativeDispatcher nd) throws IOException {
        int n;
        int pos = bb.position();
        int lim = bb.limit();
        int rem = pos <= lim ? lim - pos : 0;
        if (rem == 0) {
            return 0;
        }
        if (position != -1) {
            n = nd.pread(fd, ((long) pos) + ((DirectBuffer) bb).address(), rem, position);
        } else {
            n = nd.read(fd, ((DirectBuffer) bb).address() + ((long) pos), rem);
        }
        if (n > 0) {
            bb.position(pos + n);
        }
        return n;
    }

    static long read(FileDescriptor fd, ByteBuffer[] bufs, NativeDispatcher nd) throws IOException {
        return read(fd, bufs, 0, bufs.length, nd);
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static long read(java.io.FileDescriptor r18, java.nio.ByteBuffer[] r19, int r20, int r21, sun.nio.ch.NativeDispatcher r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.IOUtil.read(java.io.FileDescriptor, java.nio.ByteBuffer[], int, int, sun.nio.ch.NativeDispatcher):long");
    }

    public static FileDescriptor newFD(int i) {
        FileDescriptor fd = new FileDescriptor();
        setfdVal(fd, i);
        return fd;
    }
}
