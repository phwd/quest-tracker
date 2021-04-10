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

    private native int available0() throws IOException;

    private native void open0(String str) throws FileNotFoundException;

    private native long skip0(long j) throws IOException, UseManualSkipException;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileInputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null);
    }

    public FileInputStream(File file) throws FileNotFoundException {
        String name = null;
        this.channel = null;
        this.closeLock = new Object();
        this.closed = false;
        this.guard = CloseGuard.get();
        this.tracker = new IoTracker();
        name = file != null ? file.getPath() : name;
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
        }
        if (name == null) {
            throw new NullPointerException();
        } else if (!file.isInvalid()) {
            this.fd = IoBridge.open(name, OsConstants.O_RDONLY);
            this.isFdOwner = true;
            this.path = name;
            IoUtils.setFdOwner(this.fd, this);
            this.guard.open("close");
        } else {
            throw new FileNotFoundException("Invalid file path");
        }
    }

    public FileInputStream(FileDescriptor fdObj) {
        this(fdObj, false);
    }

    public FileInputStream(FileDescriptor fdObj, boolean isFdOwner2) {
        this.channel = null;
        this.closeLock = new Object();
        this.closed = false;
        this.guard = CloseGuard.get();
        this.tracker = new IoTracker();
        if (fdObj != null) {
            this.fd = fdObj;
            this.path = null;
            this.isFdOwner = isFdOwner2;
            return;
        }
        throw new NullPointerException("fdObj == null");
    }

    private void open(String name) throws FileNotFoundException {
        open0(name);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] b = new byte[1];
        if (read(b, 0, 1) != -1) {
            return b[0] & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (!this.closed || len <= 0) {
            this.tracker.trackIo(len);
            return IoBridge.read(this.fd, b, off, len);
        }
        throw new IOException("Stream Closed");
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        if (!this.closed) {
            try {
                BlockGuard.getThreadPolicy().onReadFromDisk();
                return skip0(n);
            } catch (UseManualSkipException e) {
                return super.skip(n);
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
    public int available() throws IOException {
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
    public void close() throws java.io.IOException {
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
            java.io.FileDescriptor r0 = r2.fd
            libcore.io.IoBridge.closeAndSignalBlockedThreads(r0)
        L_0x0022:
            return
        L_0x0023:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.FileInputStream.close():void");
    }

    public final FileDescriptor getFD() throws IOException {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null) {
            return fileDescriptor;
        }
        throw new IOException();
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

    /* access modifiers changed from: protected */
    public void finalize() throws IOException {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null && fileDescriptor != FileDescriptor.in) {
            close();
        }
    }
}
