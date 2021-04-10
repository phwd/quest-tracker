package java.io;

import android.system.OsConstants;
import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.nio.channels.FileChannel;
import libcore.io.IoBridge;
import libcore.io.IoTracker;
import libcore.io.IoUtils;
import sun.nio.ch.FileChannelImpl;

public class FileInputStream extends InputStream {
    private FileChannel channel;
    private final Object closeLock;
    private volatile boolean closed;
    private final FileDescriptor fd;
    private final CloseGuard guard;
    private final boolean isFdOwner;
    private final String path;
    private final IoTracker tracker;

    private native int available0();

    private native long skip0(long j);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileInputStream(String str) {
        this(str != null ? new File(str) : null);
    }

    public FileInputStream(File file) {
        this.channel = null;
        this.closeLock = new Object();
        this.closed = false;
        this.guard = CloseGuard.get();
        this.tracker = new IoTracker();
        String path2 = file != null ? file.getPath() : null;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(path2);
            throw null;
        } else if (path2 == null) {
            throw new NullPointerException();
        } else if (!file.isInvalid()) {
            this.fd = IoBridge.open(path2, OsConstants.O_RDONLY);
            this.isFdOwner = true;
            this.path = path2;
            IoUtils.setFdOwner(this.fd, this);
            this.guard.open("close");
        } else {
            throw new FileNotFoundException("Invalid file path");
        }
    }

    public FileInputStream(FileDescriptor fileDescriptor) {
        this(fileDescriptor, false);
    }

    public FileInputStream(FileDescriptor fileDescriptor, boolean z) {
        this.channel = null;
        this.closeLock = new Object();
        this.closed = false;
        this.guard = CloseGuard.get();
        this.tracker = new IoTracker();
        if (fileDescriptor != null) {
            this.fd = fileDescriptor;
            this.path = null;
            this.isFdOwner = z;
            return;
        }
        throw new NullPointerException("fdObj == null");
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) != -1) {
            return bArr[0] & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (!this.closed || i2 <= 0) {
            this.tracker.trackIo(i2);
            return IoBridge.read(this.fd, bArr, i, i2);
        }
        throw new IOException("Stream Closed");
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        if (!this.closed) {
            try {
                BlockGuard.getThreadPolicy().onReadFromDisk();
                return skip0(j);
            } catch (UseManualSkipException unused) {
                return super.skip(j);
            }
        } else {
            throw new IOException("Stream Closed");
        }
    }

    private static class UseManualSkipException extends Exception {
        private UseManualSkipException() {
        }
    }

    @Override // java.io.InputStream
    public int available() {
        if (!this.closed) {
            return available0();
        }
        throw new IOException("Stream Closed");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r0 == null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r2.isFdOwner == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        libcore.io.IoBridge.closeAndSignalBlockedThreads(r2.fd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        r2.guard.close();
        r0 = r2.channel;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.closeLock
            monitor-enter(r0)
            boolean r1 = r2.closed     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return
        L_0x0009:
            r1 = 1
            r2.closed = r1     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            dalvik.system.CloseGuard r0 = r2.guard
            r0.close()
            java.nio.channels.FileChannel r0 = r2.channel
            if (r0 == 0) goto L_0x0019
            r0.close()
        L_0x0019:
            boolean r0 = r2.isFdOwner
            if (r0 == 0) goto L_0x0022
            java.io.FileDescriptor r2 = r2.fd
            libcore.io.IoBridge.closeAndSignalBlockedThreads(r2)
        L_0x0022:
            return
        L_0x0023:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.FileInputStream.close():void");
    }

    public FileChannel getChannel() {
        FileChannel fileChannel;
        synchronized (this) {
            if (this.channel == null) {
                this.channel = FileChannelImpl.open(this.fd, this.path, true, false, this);
            }
            fileChannel = this.channel;
        }
        return fileChannel;
    }
}
