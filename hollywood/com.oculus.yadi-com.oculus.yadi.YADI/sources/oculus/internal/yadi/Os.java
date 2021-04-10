package oculus.internal.yadi;

import android.system.ErrnoException;
import android.system.StructStat;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class Os {
    private static native void fallocateNative(FileDescriptor fileDescriptor, long j, long j2) throws ErrnoException;

    private static native long makedevNative(long j, long j2);

    public static void fallocate(FileDescriptor fileDescriptor, long j, long j2) throws IOException {
        try {
            fallocateNative(fileDescriptor, j, j2);
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static long makedev(long j, long j2) {
        return makedevNative(j, j2);
    }

    public static void mkdir_dashp(File file, int i, int i2, int i3) throws IOException {
        if (file != null) {
            mkdir_dashpInternal(file.getCanonicalFile(), i, i2, i3);
            return;
        }
        throw new NullPointerException("null directory");
    }

    private static void mkdir_dashpInternal(File file, int i, int i2, int i3) throws IOException {
        if (file != null && !file.exists()) {
            mkdir_dashpInternal(file.getParentFile(), i, i2, i3);
            try {
                String file2 = file.toString();
                android.system.Os.mkdir(file2, i);
                android.system.Os.chmod(file2, i);
                android.system.Os.chown(file2, i2, i3);
            } catch (ErrnoException e) {
                throw new IOException(e.getMessage() + ": " + file, e);
            }
        }
    }

    public static void chmod(File file, int i, int i2, int i3) throws IOException {
        String file2 = file.toString();
        try {
            android.system.Os.chmod(file2, i);
            android.system.Os.chown(file2, i2, i3);
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static void rename(File file, File file2) throws IOException {
        try {
            File directFile = YadiPath.directFile(file);
            File directFile2 = YadiPath.directFile(file2);
            if (YadiPath.mountPointForFile(directFile).devId == YadiPath.mountPointForFile(directFile2).devId) {
                android.system.Os.rename(directFile.toString(), directFile2.toString());
                return;
            }
            throw new UnsupportedOperationException("Cross-device rename");
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static long lseek(FileDescriptor fileDescriptor, long j, int i) throws IOException {
        try {
            return android.system.Os.lseek(fileDescriptor, j, i);
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static void ftruncate(FileDescriptor fileDescriptor, long j) throws IOException {
        try {
            android.system.Os.ftruncate(fileDescriptor, j);
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static StructStat fstat(FileDescriptor fileDescriptor) throws IOException {
        try {
            return android.system.Os.fstat(fileDescriptor);
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static StructStat stat(String str) throws IOException {
        try {
            return android.system.Os.stat(str);
        } catch (ErrnoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    static {
        try {
            System.loadLibrary("yadi");
        } catch (Exception e) {
            Log.wtf("YadiOs", "Unable to load JNI library", e);
        }
    }
}
