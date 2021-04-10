package java.io;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import sun.misc.JavaIOFileDescriptorAccess;
import sun.misc.SharedSecrets;

public final class FileDescriptor {
    public static final long NO_OWNER = 0;
    public static final FileDescriptor err = dupFd(2);
    public static final FileDescriptor in = dupFd(0);
    public static final FileDescriptor out = dupFd(1);
    private int descriptor;
    private long ownerId;

    private static native boolean isSocket(int i);

    public native void sync() throws SyncFailedException;

    public FileDescriptor() {
        this.ownerId = 0;
        this.descriptor = -1;
    }

    private FileDescriptor(int descriptor2) {
        this.ownerId = 0;
        this.descriptor = descriptor2;
    }

    static {
        SharedSecrets.setJavaIOFileDescriptorAccess(new JavaIOFileDescriptorAccess() {
            /* class java.io.FileDescriptor.AnonymousClass1 */

            @Override // sun.misc.JavaIOFileDescriptorAccess
            public void set(FileDescriptor obj, int fd) {
                obj.descriptor = fd;
            }

            @Override // sun.misc.JavaIOFileDescriptorAccess
            public int get(FileDescriptor obj) {
                return obj.descriptor;
            }

            @Override // sun.misc.JavaIOFileDescriptorAccess
            public void setHandle(FileDescriptor obj, long handle) {
                throw new UnsupportedOperationException();
            }

            @Override // sun.misc.JavaIOFileDescriptorAccess
            public long getHandle(FileDescriptor obj) {
                throw new UnsupportedOperationException();
            }
        });
    }

    public boolean valid() {
        return this.descriptor != -1;
    }

    public final int getInt$() {
        return this.descriptor;
    }

    public final void setInt$(int fd) {
        this.descriptor = fd;
    }

    public long getOwnerId$() {
        return this.ownerId;
    }

    public void setOwnerId$(long newOwnerId) {
        this.ownerId = newOwnerId;
    }

    public FileDescriptor release$() {
        FileDescriptor result = new FileDescriptor();
        result.descriptor = this.descriptor;
        result.ownerId = this.ownerId;
        this.descriptor = -1;
        this.ownerId = 0;
        return result;
    }

    public boolean isSocket$() {
        return isSocket(this.descriptor);
    }

    private static FileDescriptor dupFd(int fd) {
        try {
            return new FileDescriptor(Os.fcntlInt(new FileDescriptor(fd), OsConstants.F_DUPFD_CLOEXEC, 0));
        } catch (ErrnoException e) {
            throw new RuntimeException(e);
        }
    }
}
