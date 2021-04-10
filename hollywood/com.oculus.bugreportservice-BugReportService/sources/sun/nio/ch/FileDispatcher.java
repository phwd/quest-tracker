package sun.nio.ch;

import java.io.FileDescriptor;

/* access modifiers changed from: package-private */
public abstract class FileDispatcher extends NativeDispatcher {
    /* access modifiers changed from: package-private */
    public abstract FileDescriptor duplicateForMapping(FileDescriptor fileDescriptor);

    /* access modifiers changed from: package-private */
    public abstract void release(FileDescriptor fileDescriptor, long j, long j2);

    /* access modifiers changed from: package-private */
    public abstract long size(FileDescriptor fileDescriptor);

    /* access modifiers changed from: package-private */
    public abstract int truncate(FileDescriptor fileDescriptor, long j);

    FileDispatcher() {
    }
}
