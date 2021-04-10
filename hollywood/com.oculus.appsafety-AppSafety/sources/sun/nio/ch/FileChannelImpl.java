package sun.nio.ch;

import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.OverlappingFileLockException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;
import sun.misc.Cleaner;
import sun.security.action.GetPropertyAction;

public class FileChannelImpl extends FileChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long MAPPED_TRANSFER_SIZE = 8388608;
    private static final int MAP_PV = 2;
    private static final int MAP_RO = 0;
    private static final int MAP_RW = 1;
    private static final int TRANSFER_SIZE = 8192;
    private static final long allocationGranularity = initIDs();
    private static volatile boolean fileSupported = true;
    private static boolean isSharedFileLockTable;
    private static volatile boolean pipeSupported = true;
    private static volatile boolean propertyChecked;
    private static volatile boolean transferSupported = true;
    private final boolean append;
    public final FileDescriptor fd;
    private volatile FileLockTable fileLockTable;
    private final CloseGuard guard = CloseGuard.get();
    private final FileDispatcher nd;
    private final Object parent;
    private final String path;
    private final Object positionLock = new Object();
    private final boolean readable;
    private final NativeThreadSet threads = new NativeThreadSet(2);
    private final boolean writable;

    private static native long initIDs();

    private native long map0(int i, long j, long j2) throws IOException;

    private native long position0(FileDescriptor fileDescriptor, long j);

    private native long transferTo0(FileDescriptor fileDescriptor, long j, long j2, FileDescriptor fileDescriptor2);

    /* access modifiers changed from: private */
    public static native int unmap0(long j, long j2);

    private FileChannelImpl(FileDescriptor fd2, String path2, boolean readable2, boolean writable2, boolean append2, Object parent2) {
        this.fd = fd2;
        this.readable = readable2;
        this.writable = writable2;
        this.append = append2;
        this.parent = parent2;
        this.path = path2;
        this.nd = new FileDispatcherImpl(append2);
        if (fd2 != null && fd2.valid()) {
            this.guard.open("close");
        }
    }

    public static FileChannel open(FileDescriptor fd2, String path2, boolean readable2, boolean writable2, Object parent2) {
        return new FileChannelImpl(fd2, path2, readable2, writable2, false, parent2);
    }

    public static FileChannel open(FileDescriptor fd2, String path2, boolean readable2, boolean writable2, boolean append2, Object parent2) {
        return new FileChannelImpl(fd2, path2, readable2, writable2, append2, parent2);
    }

    private void ensureOpen() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public void implCloseChannel() throws IOException {
        this.guard.close();
        if (this.fileLockTable != null) {
            for (FileLock fl : this.fileLockTable.removeAll()) {
                synchronized (fl) {
                    if (fl.isValid()) {
                        this.nd.release(this.fd, fl.position(), fl.size());
                        ((FileLockImpl) fl).invalidate();
                    }
                }
            }
        }
        this.threads.signalAndWait();
        Object obj = this.parent;
        if (obj != null) {
            ((Closeable) obj).close();
        } else {
            this.nd.close(this.fd);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.guard != null) {
                this.guard.warnIfOpen();
            }
            close();
        } finally {
            super.finalize();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel, java.nio.channels.FileChannel
    public int read(ByteBuffer dst) throws IOException {
        ensureOpen();
        if (this.readable) {
            synchronized (this.positionLock) {
                int n = 0;
                int ti = -1;
                boolean z = true;
                try {
                    begin();
                    ti = this.threads.add();
                    if (!isOpen()) {
                        this.threads.remove(ti);
                        if (n <= 0) {
                            z = false;
                        }
                        end(z);
                        return 0;
                    }
                    do {
                        n = IOUtil.read(this.fd, dst, -1, this.nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    return IOStatus.normalize(n);
                } finally {
                    this.threads.remove(ti);
                    if (n <= 0) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NonReadableChannelException();
        }
    }

    @Override // java.nio.channels.ScatteringByteChannel, java.nio.channels.FileChannel
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        if (offset < 0 || length < 0 || offset > dsts.length - length) {
            throw new IndexOutOfBoundsException();
        }
        ensureOpen();
        if (this.readable) {
            synchronized (this.positionLock) {
                long n = 0;
                int ti = -1;
                boolean z = true;
                try {
                    begin();
                    ti = this.threads.add();
                    if (!isOpen()) {
                        this.threads.remove(ti);
                        if (n <= 0) {
                            z = false;
                        }
                        end(z);
                        return 0;
                    }
                    do {
                        n = IOUtil.read(this.fd, dsts, offset, length, this.nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    return IOStatus.normalize(n);
                } finally {
                    this.threads.remove(ti);
                    if (n <= 0) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NonReadableChannelException();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel, java.nio.channels.FileChannel
    public int write(ByteBuffer src) throws IOException {
        ensureOpen();
        if (this.writable) {
            synchronized (this.positionLock) {
                int n = 0;
                int ti = -1;
                boolean z = true;
                try {
                    begin();
                    ti = this.threads.add();
                    if (!isOpen()) {
                        this.threads.remove(ti);
                        if (n <= 0) {
                            z = false;
                        }
                        end(z);
                        return 0;
                    }
                    do {
                        n = IOUtil.write(this.fd, src, -1, this.nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    return IOStatus.normalize(n);
                } finally {
                    this.threads.remove(ti);
                    if (n <= 0) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NonWritableChannelException();
        }
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        if (offset < 0 || length < 0 || offset > srcs.length - length) {
            throw new IndexOutOfBoundsException();
        }
        ensureOpen();
        if (this.writable) {
            synchronized (this.positionLock) {
                long n = 0;
                int ti = -1;
                boolean z = true;
                try {
                    begin();
                    ti = this.threads.add();
                    if (!isOpen()) {
                        this.threads.remove(ti);
                        if (n <= 0) {
                            z = false;
                        }
                        end(z);
                        return 0;
                    }
                    do {
                        n = IOUtil.write(this.fd, srcs, offset, length, this.nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    return IOStatus.normalize(n);
                } finally {
                    this.threads.remove(ti);
                    if (n <= 0) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NonWritableChannelException();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.FileChannel
    public long position() throws IOException {
        ensureOpen();
        synchronized (this.positionLock) {
            long p = -1;
            int ti = -1;
            boolean z = true;
            try {
                begin();
                ti = this.threads.add();
                if (!isOpen()) {
                    this.threads.remove(ti);
                    if (p <= -1) {
                        z = false;
                    }
                    end(z);
                    return 0;
                }
                if (this.append) {
                    BlockGuard.getThreadPolicy().onWriteToDisk();
                }
                do {
                    p = this.append ? this.nd.size(this.fd) : position0(this.fd, -1);
                    if (p != -3) {
                        break;
                    }
                } while (isOpen());
                return IOStatus.normalize(p);
            } finally {
                this.threads.remove(ti);
                if (p <= -1) {
                    z = false;
                }
                end(z);
            }
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.FileChannel, java.nio.channels.FileChannel
    public FileChannel position(long newPosition) throws IOException {
        ensureOpen();
        if (newPosition >= 0) {
            synchronized (this.positionLock) {
                long p = -1;
                int ti = -1;
                boolean z = true;
                try {
                    begin();
                    ti = this.threads.add();
                    if (!isOpen()) {
                        this.threads.remove(ti);
                        if (p <= -1) {
                            z = false;
                        }
                        end(z);
                        return null;
                    }
                    BlockGuard.getThreadPolicy().onReadFromDisk();
                    do {
                        p = position0(this.fd, newPosition);
                        if (p != -3) {
                            break;
                        }
                    } while (isOpen());
                    return this;
                } finally {
                    this.threads.remove(ti);
                    if (p <= -1) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.FileChannel
    public long size() throws IOException {
        ensureOpen();
        synchronized (this.positionLock) {
            long s = -1;
            int ti = -1;
            boolean z = true;
            try {
                begin();
                ti = this.threads.add();
                if (!isOpen()) {
                    this.threads.remove(ti);
                    if (s <= -1) {
                        z = false;
                    }
                    end(z);
                    return -1;
                }
                do {
                    s = this.nd.size(this.fd);
                    if (s != -3) {
                        break;
                    }
                } while (isOpen());
                return IOStatus.normalize(s);
            } finally {
                this.threads.remove(ti);
                if (s <= -1) {
                    z = false;
                }
                end(z);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00cd  */
    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.FileChannel, java.nio.channels.FileChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.nio.channels.FileChannel truncate(long r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 260
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.truncate(long):java.nio.channels.FileChannel");
    }

    @Override // java.nio.channels.FileChannel
    public void force(boolean metaData) throws IOException {
        int rv;
        ensureOpen();
        int ti = -1;
        boolean z = true;
        try {
            begin();
            ti = this.threads.add();
            if (isOpen()) {
                do {
                    rv = this.nd.force(this.fd, metaData);
                    if (rv != -3) {
                        break;
                    }
                } while (isOpen());
                this.threads.remove(ti);
                if (rv <= -1) {
                    z = false;
                }
                end(z);
            }
        } finally {
            this.threads.remove(ti);
            if (-1 <= -1) {
                z = false;
            }
            end(z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long transferToDirectlyInternal(long r18, int r20, java.nio.channels.WritableByteChannel r21, java.io.FileDescriptor r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 179
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.transferToDirectlyInternal(long, int, java.nio.channels.WritableByteChannel, java.io.FileDescriptor):long");
    }

    private long transferToDirectly(long position, int icount, WritableByteChannel target) throws IOException {
        FileDescriptor targetFD;
        long transferToDirectlyInternal;
        if (!transferSupported) {
            return -4;
        }
        if (target instanceof FileChannelImpl) {
            if (!fileSupported) {
                return -6;
            }
            targetFD = ((FileChannelImpl) target).fd;
        } else if (!(target instanceof SelChImpl)) {
            targetFD = null;
        } else if ((target instanceof SinkChannelImpl) && !pipeSupported) {
            return -6;
        } else {
            if (!this.nd.canTransferToDirectly((SelectableChannel) target)) {
                return -6;
            }
            targetFD = ((SelChImpl) target).getFD();
        }
        if (targetFD == null || IOUtil.fdVal(this.fd) == IOUtil.fdVal(targetFD)) {
            return -4;
        }
        if (!this.nd.transferToDirectlyNeedsPositionLock()) {
            return transferToDirectlyInternal(position, icount, target, targetFD);
        }
        synchronized (this.positionLock) {
            long pos = position();
            try {
                transferToDirectlyInternal = transferToDirectlyInternal(position, icount, target, targetFD);
            } finally {
                position(pos);
            }
        }
        return transferToDirectlyInternal;
    }

    private long transferToTrustedChannel(long position, long count, WritableByteChannel target) throws IOException {
        boolean isSelChImpl = target instanceof SelChImpl;
        if (!(target instanceof FileChannelImpl) && !isSelChImpl) {
            return -4;
        }
        long remaining = count;
        long position2 = position;
        while (true) {
            if (remaining <= 0) {
                break;
            }
            try {
                MappedByteBuffer dbb = map(FileChannel.MapMode.READ_ONLY, position2, Math.min(remaining, (long) MAPPED_TRANSFER_SIZE));
                try {
                    int n = target.write(dbb);
                    remaining -= (long) n;
                    if (isSelChImpl) {
                        unmap(dbb);
                        break;
                    }
                    position2 += (long) n;
                } finally {
                    unmap(dbb);
                }
            } catch (ClosedByInterruptException e) {
                close();
            } catch (IOException ioe) {
                if (remaining == count) {
                    throw ioe;
                }
            } catch (Throwable suppressed) {
                e.addSuppressed(suppressed);
            }
        }
        return count - remaining;
        throw e;
    }

    private long transferToArbitraryChannel(long position, int icount, WritableByteChannel target) throws IOException {
        ByteBuffer bb = Util.getTemporaryDirectBuffer(Math.min(icount, 8192));
        long tw = 0;
        long pos = position;
        try {
            Util.erase(bb);
            while (true) {
                if (tw >= ((long) icount)) {
                    break;
                }
                bb.limit(Math.min((int) (((long) icount) - tw), 8192));
                int nr = read(bb, pos);
                if (nr <= 0) {
                    break;
                }
                bb.flip();
                int nw = target.write(bb);
                tw += (long) nw;
                if (nw != nr) {
                    break;
                }
                pos += (long) nw;
                bb.clear();
            }
            return tw;
        } catch (IOException x) {
            if (0 > 0) {
                return 0;
            }
            throw x;
        } finally {
            Util.releaseTemporaryDirectBuffer(bb);
        }
    }

    @Override // java.nio.channels.FileChannel
    public long transferTo(long position, long count, WritableByteChannel target) throws IOException {
        int icount;
        ensureOpen();
        if (!target.isOpen()) {
            throw new ClosedChannelException();
        } else if (!this.readable) {
            throw new NonReadableChannelException();
        } else if ((target instanceof FileChannelImpl) && !((FileChannelImpl) target).writable) {
            throw new NonWritableChannelException();
        } else if (position < 0 || count < 0) {
            throw new IllegalArgumentException();
        } else {
            long sz = size();
            if (position > sz) {
                return 0;
            }
            int icount2 = (int) Math.min(count, 2147483647L);
            if (sz - position < ((long) icount2)) {
                icount = (int) (sz - position);
            } else {
                icount = icount2;
            }
            long n = transferToDirectly(position, icount, target);
            if (n >= 0) {
                return n;
            }
            long n2 = transferToTrustedChannel(position, (long) icount, target);
            if (n2 >= 0) {
                return n2;
            }
            return transferToArbitraryChannel(position, icount, target);
        }
    }

    /* JADX INFO: Multiple debug info for r0v7 long: [D('remaining' long), D('max' long)] */
    private long transferFromFileChannel(FileChannelImpl src, long position, long count) throws IOException {
        Throwable th;
        long remaining;
        MappedByteBuffer bb;
        if (src.readable) {
            synchronized (src.positionLock) {
                try {
                    long pos = src.position();
                    long max = Math.min(count, src.size() - pos);
                    long p = pos;
                    long n = max;
                    long position2 = position;
                    while (n > 0) {
                        try {
                            remaining = n;
                            try {
                                bb = src.map(FileChannel.MapMode.READ_ONLY, p, Math.min(n, (long) MAPPED_TRANSFER_SIZE));
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                            try {
                                long n2 = (long) write(bb, position2);
                                p += n2;
                                long position3 = position2 + n2;
                                n = remaining - n2;
                                unmap(bb);
                                position2 = position3;
                            } catch (IOException ioe) {
                                if (remaining != max) {
                                    unmap(bb);
                                } else {
                                    throw ioe;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            throw th;
                        }
                    }
                    remaining = n;
                    long nwritten = max - remaining;
                    src.position(pos + nwritten);
                    return nwritten;
                } catch (Throwable th5) {
                    th = th5;
                    throw th;
                }
            }
        } else {
            throw new NonReadableChannelException();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A[SYNTHETIC, Splitter:B:34:0x006c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long transferFromArbitraryChannel(java.nio.channels.ReadableByteChannel r17, long r18, long r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.transferFromArbitraryChannel(java.nio.channels.ReadableByteChannel, long, long):long");
    }

    @Override // java.nio.channels.FileChannel
    public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException {
        ensureOpen();
        if (!src.isOpen()) {
            throw new ClosedChannelException();
        } else if (!this.writable) {
            throw new NonWritableChannelException();
        } else if (position < 0 || count < 0) {
            throw new IllegalArgumentException();
        } else if (position > size()) {
            return 0;
        } else {
            if (src instanceof FileChannelImpl) {
                return transferFromFileChannel((FileChannelImpl) src, position, count);
            }
            return transferFromArbitraryChannel(src, position, count);
        }
    }

    @Override // java.nio.channels.FileChannel
    public int read(ByteBuffer dst, long position) throws IOException {
        int readInternal;
        if (dst == null) {
            throw new NullPointerException();
        } else if (position < 0) {
            throw new IllegalArgumentException("Negative position");
        } else if (this.readable) {
            ensureOpen();
            if (!this.nd.needsPositionLock()) {
                return readInternal(dst, position);
            }
            synchronized (this.positionLock) {
                readInternal = readInternal(dst, position);
            }
            return readInternal;
        } else {
            throw new NonReadableChannelException();
        }
    }

    private int readInternal(ByteBuffer dst, long position) throws IOException {
        int n;
        int ti = -1;
        boolean z = true;
        try {
            begin();
            ti = this.threads.add();
            if (!isOpen()) {
                return -1;
            }
            do {
                n = IOUtil.read(this.fd, dst, position, this.nd);
                if (n != -3) {
                    break;
                }
            } while (isOpen());
            int normalize = IOStatus.normalize(n);
            this.threads.remove(ti);
            if (n <= 0) {
                z = false;
            }
            end(z);
            return normalize;
        } finally {
            this.threads.remove(ti);
            if (0 <= 0) {
                z = false;
            }
            end(z);
        }
    }

    @Override // java.nio.channels.FileChannel
    public int write(ByteBuffer src, long position) throws IOException {
        int writeInternal;
        if (src == null) {
            throw new NullPointerException();
        } else if (position < 0) {
            throw new IllegalArgumentException("Negative position");
        } else if (this.writable) {
            ensureOpen();
            if (!this.nd.needsPositionLock()) {
                return writeInternal(src, position);
            }
            synchronized (this.positionLock) {
                writeInternal = writeInternal(src, position);
            }
            return writeInternal;
        } else {
            throw new NonWritableChannelException();
        }
    }

    private int writeInternal(ByteBuffer src, long position) throws IOException {
        int n;
        int ti = -1;
        boolean z = true;
        try {
            begin();
            ti = this.threads.add();
            if (!isOpen()) {
                return -1;
            }
            do {
                n = IOUtil.write(this.fd, src, position, this.nd);
                if (n != -3) {
                    break;
                }
            } while (isOpen());
            int normalize = IOStatus.normalize(n);
            this.threads.remove(ti);
            if (n <= 0) {
                z = false;
            }
            end(z);
            return normalize;
        } finally {
            this.threads.remove(ti);
            if (0 <= 0) {
                z = false;
            }
            end(z);
        }
    }

    /* access modifiers changed from: private */
    public static class Unmapper implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static volatile int count;
        private static final NativeDispatcher nd = new FileDispatcherImpl();
        static volatile long totalCapacity;
        static volatile long totalSize;
        private volatile long address;
        private final int cap;
        private final FileDescriptor fd;
        private final long size;

        private Unmapper(long address2, long size2, int cap2, FileDescriptor fd2) {
            this.address = address2;
            this.size = size2;
            this.cap = cap2;
            this.fd = fd2;
            synchronized (Unmapper.class) {
                count++;
                totalSize += size2;
                totalCapacity += (long) cap2;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.address != 0) {
                FileChannelImpl.unmap0(this.address, this.size);
                this.address = 0;
                if (this.fd.valid()) {
                    try {
                        nd.close(this.fd);
                    } catch (IOException e) {
                    }
                }
                synchronized (Unmapper.class) {
                    count--;
                    totalSize -= this.size;
                    totalCapacity -= (long) this.cap;
                }
            }
        }
    }

    private static void unmap(MappedByteBuffer bb) {
        Cleaner cl = ((DirectBuffer) bb).cleaner();
        if (cl != null) {
            cl.clean();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a2 A[LOOP:1: B:50:0x00a2->B:53:0x00b3, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0171 A[Catch:{ all -> 0x018f }] */
    @Override // java.nio.channels.FileChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.nio.MappedByteBuffer map(java.nio.channels.FileChannel.MapMode r39, long r40, long r42) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 486
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.map(java.nio.channels.FileChannel$MapMode, long, long):java.nio.MappedByteBuffer");
    }

    private static boolean isSharedFileLockTable() {
        boolean z;
        if (!propertyChecked) {
            synchronized (FileChannelImpl.class) {
                if (!propertyChecked) {
                    String value = (String) AccessController.doPrivileged(new GetPropertyAction("sun.nio.ch.disableSystemWideOverlappingFileLockCheck"));
                    if (value != null) {
                        if (!value.equals("false")) {
                            z = false;
                            isSharedFileLockTable = z;
                            propertyChecked = true;
                        }
                    }
                    z = true;
                    isSharedFileLockTable = z;
                    propertyChecked = true;
                }
            }
        }
        return isSharedFileLockTable;
    }

    private FileLockTable fileLockTable() throws IOException {
        if (this.fileLockTable == null) {
            synchronized (this) {
                if (this.fileLockTable == null) {
                    if (isSharedFileLockTable()) {
                        int ti = this.threads.add();
                        try {
                            ensureOpen();
                            this.fileLockTable = FileLockTable.newSharedFileLockTable(this, this.fd);
                        } finally {
                            this.threads.remove(ti);
                        }
                    } else {
                        this.fileLockTable = new SimpleFileLockTable();
                    }
                }
            }
        }
        return this.fileLockTable;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d6  */
    @Override // java.nio.channels.FileChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.nio.channels.FileLock lock(long r18, long r20, boolean r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 236
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.lock(long, long, boolean):java.nio.channels.FileLock");
    }

    @Override // java.nio.channels.FileChannel
    public FileLock tryLock(long position, long size, boolean shared) throws IOException {
        int ti;
        IOException e;
        ensureOpen();
        if (shared && !this.readable) {
            throw new NonReadableChannelException();
        } else if (shared || this.writable) {
            FileLockImpl fli = new FileLockImpl(this, position, size, shared);
            FileLockTable flt = fileLockTable();
            flt.add(fli);
            int ti2 = this.threads.add();
            try {
                ensureOpen();
                int result = this.nd.lock(this.fd, false, position, size, shared);
                if (result == -1) {
                    try {
                        flt.remove(fli);
                        this.threads.remove(ti2);
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        ti = ti2;
                        this.threads.remove(ti);
                        throw e;
                    }
                } else if (result == 1) {
                    try {
                        ti = ti2;
                    } catch (Throwable th2) {
                        e = th2;
                        ti = ti2;
                        this.threads.remove(ti);
                        throw e;
                    }
                    try {
                        FileLockImpl fli2 = new FileLockImpl((FileChannel) this, position, size, false);
                        flt.replace(fli, fli2);
                        this.threads.remove(ti);
                        return fli2;
                    } catch (Throwable th3) {
                        e = th3;
                        this.threads.remove(ti);
                        throw e;
                    }
                } else {
                    this.threads.remove(ti2);
                    return fli;
                }
            } catch (IOException e2) {
                flt.remove(fli);
                throw e2;
            }
        } else {
            throw new NonWritableChannelException();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void release(FileLockImpl fli) throws IOException {
        int ti = this.threads.add();
        try {
            ensureOpen();
            this.nd.release(this.fd, fli.position(), fli.size());
            this.threads.remove(ti);
            this.fileLockTable.remove(fli);
        } catch (Throwable th) {
            this.threads.remove(ti);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static class SimpleFileLockTable extends FileLockTable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<FileLock> lockList = new ArrayList(2);

        private void checkList(long position, long size) throws OverlappingFileLockException {
            for (FileLock fl : this.lockList) {
                if (fl.overlaps(position, size)) {
                    throw new OverlappingFileLockException();
                }
            }
        }

        @Override // sun.nio.ch.FileLockTable
        public void add(FileLock fl) throws OverlappingFileLockException {
            synchronized (this.lockList) {
                checkList(fl.position(), fl.size());
                this.lockList.add(fl);
            }
        }

        @Override // sun.nio.ch.FileLockTable
        public void remove(FileLock fl) {
            synchronized (this.lockList) {
                this.lockList.remove(fl);
            }
        }

        @Override // sun.nio.ch.FileLockTable
        public List<FileLock> removeAll() {
            List<FileLock> result;
            synchronized (this.lockList) {
                result = new ArrayList<>(this.lockList);
                this.lockList.clear();
            }
            return result;
        }

        @Override // sun.nio.ch.FileLockTable
        public void replace(FileLock fl1, FileLock fl2) {
            synchronized (this.lockList) {
                this.lockList.remove(fl1);
                this.lockList.add(fl2);
            }
        }
    }
}
