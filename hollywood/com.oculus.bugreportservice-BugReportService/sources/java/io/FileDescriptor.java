package java.io;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import sun.misc.JavaIOFileDescriptorAccess;
import sun.misc.SharedSecrets;

public final class FileDescriptor {
    public static final FileDescriptor err = dupFd(2);
    public static final FileDescriptor in = dupFd(0);
    public static final FileDescriptor out = dupFd(1);
    private int descriptor;
    private long ownerId;

    private static native boolean isSocket(int i);

    public FileDescriptor() {
        this.ownerId = 0;
        this.descriptor = -1;
    }

    private FileDescriptor(int i) {
        this.ownerId = 0;
        this.descriptor = i;
    }

    static {
        SharedSecrets.setJavaIOFileDescriptorAccess(new JavaIOFileDescriptorAccess() {
            /* class java.io.FileDescriptor.AnonymousClass1 */
        });
    }

    public boolean valid() {
        return this.descriptor != -1;
    }

    public final int getInt$() {
        return this.descriptor;
    }

    public final void setInt$(int i) {
        this.descriptor = i;
    }

    public long getOwnerId$() {
        return this.ownerId;
    }

    public void setOwnerId$(long j) {
        this.ownerId = j;
    }

    public FileDescriptor release$() {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fileDescriptor.descriptor = this.descriptor;
        fileDescriptor.ownerId = this.ownerId;
        this.descriptor = -1;
        this.ownerId = 0;
        return fileDescriptor;
    }

    public boolean isSocket$() {
        return isSocket(this.descriptor);
    }

    private static FileDescriptor dupFd(int i) {
        try {
            return new FileDescriptor(Os.fcntlInt(new FileDescriptor(i), OsConstants.F_DUPFD_CLOEXEC, 0));
        } catch (ErrnoException e) {
            throw new RuntimeException(e);
        }
    }
}
