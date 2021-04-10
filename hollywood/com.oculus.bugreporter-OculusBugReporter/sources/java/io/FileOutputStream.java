package java.io;

import android.system.OsConstants;
import dalvik.system.CloseGuard;
import java.nio.channels.FileChannel;
import libcore.io.IoBridge;
import libcore.io.IoTracker;
import libcore.io.IoUtils;
import sun.nio.ch.FileChannelImpl;

public class FileOutputStream extends OutputStream {
    private final boolean append;
    private FileChannel channel;
    private final Object closeLock;
    private volatile boolean closed;
    private final FileDescriptor fd;
    private final CloseGuard guard;
    private final boolean isFdOwner;
    private final String path;
    private final IoTracker tracker;

    private native void open0(String str, boolean z) throws FileNotFoundException;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileOutputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileOutputStream(String name, boolean append2) throws FileNotFoundException {
        this(name != null ? new File(name) : null, append2);
    }

    public FileOutputStream(File file) throws FileNotFoundException {
        this(file, false);
    }

    public FileOutputStream(File file, boolean append2) throws FileNotFoundException {
        this.closeLock = new Object();
        this.closed = false;
        this.guard = CloseGuard.get();
        this.tracker = new IoTracker();
        String name = file != null ? file.getPath() : null;
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(name);
        }
        if (name == null) {
            throw new NullPointerException();
        } else if (!file.isInvalid()) {
            this.fd = IoBridge.open(name, OsConstants.O_WRONLY | OsConstants.O_CREAT | (append2 ? OsConstants.O_APPEND : OsConstants.O_TRUNC));
            this.isFdOwner = true;
            this.append = append2;
            this.path = name;
            IoUtils.setFdOwner(this.fd, this);
            this.guard.open("close");
        } else {
            throw new FileNotFoundException("Invalid file path");
        }
    }

    public FileOutputStream(FileDescriptor fdObj) {
        this(fdObj, false);
    }

    public FileOutputStream(FileDescriptor fdObj, boolean isFdOwner2) {
        this.closeLock = new Object();
        this.closed = false;
        this.guard = CloseGuard.get();
        this.tracker = new IoTracker();
        if (fdObj != null) {
            this.fd = fdObj;
            this.append = false;
            this.path = null;
            this.isFdOwner = isFdOwner2;
            return;
        }
        throw new NullPointerException("fdObj == null");
    }

    private void open(String name, boolean append2) throws FileNotFoundException {
        open0(name, append2);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.closed || len <= 0) {
            this.tracker.trackIo(len);
            IoBridge.write(this.fd, b, off, len);
            return;
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
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
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
        throw new UnsupportedOperationException("Method not decompiled: java.io.FileOutputStream.close():void");
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
                this.channel = FileChannelImpl.open(this.fd, this.path, false, true, this.append, this);
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
        if (fileDescriptor == null) {
            return;
        }
        if (fileDescriptor == FileDescriptor.out || this.fd == FileDescriptor.err) {
            flush();
        } else {
            close();
        }
    }
}
