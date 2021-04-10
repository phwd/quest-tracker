package sun.nio.ch;

import dalvik.system.BlockGuard;
import java.io.FileDescriptor;

/* access modifiers changed from: package-private */
public class FileDispatcherImpl extends FileDispatcher {
    static native void close0(FileDescriptor fileDescriptor);

    static native int pread0(FileDescriptor fileDescriptor, long j, int i, long j2);

    static native int pwrite0(FileDescriptor fileDescriptor, long j, int i, long j2);

    static native int read0(FileDescriptor fileDescriptor, long j, int i);

    static native void release0(FileDescriptor fileDescriptor, long j, long j2);

    static native long size0(FileDescriptor fileDescriptor);

    static native int truncate0(FileDescriptor fileDescriptor, long j);

    static native int write0(FileDescriptor fileDescriptor, long j, int i);

    FileDispatcherImpl(boolean z) {
    }

    FileDispatcherImpl() {
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public int read(FileDescriptor fileDescriptor, long j, int i) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return read0(fileDescriptor, j, i);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public int pread(FileDescriptor fileDescriptor, long j, int i, long j2) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return pread0(fileDescriptor, j, i, j2);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public int write(FileDescriptor fileDescriptor, long j, int i) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return write0(fileDescriptor, j, i);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public int pwrite(FileDescriptor fileDescriptor, long j, int i, long j2) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return pwrite0(fileDescriptor, j, i, j2);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.FileDispatcher
    public int truncate(FileDescriptor fileDescriptor, long j) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return truncate0(fileDescriptor, j);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.FileDispatcher
    public long size(FileDescriptor fileDescriptor) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return size0(fileDescriptor);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.FileDispatcher
    public void release(FileDescriptor fileDescriptor, long j, long j2) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        release0(fileDescriptor, j, j2);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.NativeDispatcher
    public void close(FileDescriptor fileDescriptor) {
        close0(fileDescriptor);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.FileDispatcher
    public FileDescriptor duplicateForMapping(FileDescriptor fileDescriptor) {
        return new FileDescriptor();
    }
}
