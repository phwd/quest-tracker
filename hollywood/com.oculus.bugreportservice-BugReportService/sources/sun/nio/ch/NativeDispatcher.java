package sun.nio.ch;

import java.io.FileDescriptor;

/* access modifiers changed from: package-private */
public abstract class NativeDispatcher {
    /* access modifiers changed from: package-private */
    public abstract void close(FileDescriptor fileDescriptor);

    /* access modifiers changed from: package-private */
    public abstract int pread(FileDescriptor fileDescriptor, long j, int i, long j2);

    /* access modifiers changed from: package-private */
    public abstract int pwrite(FileDescriptor fileDescriptor, long j, int i, long j2);

    /* access modifiers changed from: package-private */
    public abstract int read(FileDescriptor fileDescriptor, long j, int i);

    /* access modifiers changed from: package-private */
    public abstract int write(FileDescriptor fileDescriptor, long j, int i);

    NativeDispatcher() {
    }
}
