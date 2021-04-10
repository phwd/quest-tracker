package sun.nio.ch;

import dalvik.system.BlockGuard;
import java.io.FileDescriptor;
import java.io.IOException;

class DatagramDispatcher extends NativeDispatcher {
    static native int read0(FileDescriptor fileDescriptor, long j, int i) throws IOException;

    static native long readv0(FileDescriptor fileDescriptor, long j, int i) throws IOException;

    static native int write0(FileDescriptor fileDescriptor, long j, int i) throws IOException;

    static native long writev0(FileDescriptor fileDescriptor, long j, int i) throws IOException;

    DatagramDispatcher() {
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public int read(FileDescriptor fd, long address, int len) throws IOException {
        BlockGuard.getThreadPolicy().onNetwork();
        return read0(fd, address, len);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public long readv(FileDescriptor fd, long address, int len) throws IOException {
        BlockGuard.getThreadPolicy().onNetwork();
        return readv0(fd, address, len);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public int write(FileDescriptor fd, long address, int len) throws IOException {
        BlockGuard.getThreadPolicy().onNetwork();
        return write0(fd, address, len);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public long writev(FileDescriptor fd, long address, int len) throws IOException {
        BlockGuard.getThreadPolicy().onNetwork();
        return writev0(fd, address, len);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public void close(FileDescriptor fd) throws IOException {
        FileDispatcherImpl.close0(fd);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public void preClose(FileDescriptor fd) throws IOException {
        FileDispatcherImpl.preClose0(fd);
    }
}
