package sun.nio.ch;

import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileChannelImpl extends FileChannel {
    private static final long allocationGranularity = initIDs();
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

    private native long map0(int i, long j, long j2);

    /* access modifiers changed from: private */
    public static native int unmap0(long j, long j2);

    private FileChannelImpl(FileDescriptor fileDescriptor, String str, boolean z, boolean z2, boolean z3, Object obj) {
        this.fd = fileDescriptor;
        this.readable = z;
        this.writable = z2;
        this.append = z3;
        this.parent = obj;
        this.path = str;
        this.nd = new FileDispatcherImpl(z3);
        if (fileDescriptor != null && fileDescriptor.valid()) {
            this.guard.open("close");
        }
    }

    public static FileChannel open(FileDescriptor fileDescriptor, String str, boolean z, boolean z2, Object obj) {
        return new FileChannelImpl(fileDescriptor, str, z, z2, false, obj);
    }

    private void ensureOpen() {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public void implCloseChannel() {
        this.guard.close();
        if (this.fileLockTable != null) {
            for (FileLock fileLock : this.fileLockTable.removeAll()) {
                synchronized (fileLock) {
                    if (fileLock.isValid()) {
                        this.nd.release(this.fd, fileLock.position(), fileLock.size());
                        ((FileLockImpl) fileLock).invalidate();
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

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0058  */
    @Override // java.nio.channels.ReadableByteChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(java.nio.ByteBuffer r10) {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.read(java.nio.ByteBuffer):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0058  */
    @Override // java.nio.channels.WritableByteChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int write(java.nio.ByteBuffer r10) {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.write(java.nio.ByteBuffer):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b  */
    @Override // java.nio.channels.FileChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long size() {
        /*
            r11 = this;
            r11.ensureOpen()
            java.lang.Object r0 = r11.positionLock
            monitor-enter(r0)
            r1 = -1
            r2 = 1
            r3 = 0
            r4 = -1
            r11.begin()     // Catch:{ all -> 0x004f }
            sun.nio.ch.NativeThreadSet r6 = r11.threads     // Catch:{ all -> 0x004f }
            int r1 = r6.add()     // Catch:{ all -> 0x004f }
            boolean r6 = r11.isOpen()     // Catch:{ all -> 0x004f }
            if (r6 != 0) goto L_0x0024
            sun.nio.ch.NativeThreadSet r2 = r11.threads     // Catch:{ all -> 0x0060 }
            r2.remove(r1)     // Catch:{ all -> 0x0060 }
            r11.end(r3)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return r4
        L_0x0024:
            r6 = r4
        L_0x0025:
            sun.nio.ch.FileDispatcher r8 = r11.nd     // Catch:{ all -> 0x004d }
            java.io.FileDescriptor r9 = r11.fd     // Catch:{ all -> 0x004d }
            long r6 = r8.size(r9)     // Catch:{ all -> 0x004d }
            r8 = -3
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x0039
            boolean r8 = r11.isOpen()     // Catch:{ all -> 0x004d }
            if (r8 != 0) goto L_0x0025
        L_0x0039:
            long r8 = sun.nio.ch.IOStatus.normalize(r6)     // Catch:{ all -> 0x004d }
            sun.nio.ch.NativeThreadSet r10 = r11.threads
            r10.remove(r1)
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r2 = r3
        L_0x0048:
            r11.end(r2)
            monitor-exit(r0)
            return r8
        L_0x004d:
            r8 = move-exception
            goto L_0x0051
        L_0x004f:
            r8 = move-exception
            r6 = r4
        L_0x0051:
            sun.nio.ch.NativeThreadSet r9 = r11.threads
            r9.remove(r1)
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x005b
            goto L_0x005c
        L_0x005b:
            r2 = r3
        L_0x005c:
            r11.end(r2)
            throw r8
        L_0x0060:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.size():long");
    }

    private static class Unmapper implements Runnable {
        static volatile int count;
        private static final NativeDispatcher nd = new FileDispatcherImpl();
        static volatile long totalCapacity;
        static volatile long totalSize;
        private volatile long address;
        private final int cap;
        private final FileDescriptor fd;
        private final long size;

        private Unmapper(long j, long j2, int i, FileDescriptor fileDescriptor) {
            this.address = j;
            this.size = j2;
            this.cap = i;
            this.fd = fileDescriptor;
            synchronized (Unmapper.class) {
                count++;
                totalSize += j2;
                totalCapacity += (long) i;
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
                    } catch (IOException unused) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a2, code lost:
        if (isOpen() == false) goto L_0x005d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008d A[LOOP:1: B:45:0x008d->B:48:0x009c, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0142 A[ADDED_TO_REGION] */
    @Override // java.nio.channels.FileChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.nio.MappedByteBuffer map(java.nio.channels.FileChannel.MapMode r30, long r31, long r33) {
        /*
        // Method dump skipped, instructions count: 431
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.FileChannelImpl.map(java.nio.channels.FileChannel$MapMode, long, long):java.nio.MappedByteBuffer");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void release(FileLockImpl fileLockImpl) {
        int add = this.threads.add();
        try {
            ensureOpen();
            this.nd.release(this.fd, fileLockImpl.position(), fileLockImpl.size());
            this.threads.remove(add);
            this.fileLockTable.remove(fileLockImpl);
        } catch (Throwable th) {
            this.threads.remove(add);
            throw th;
        }
    }
}
