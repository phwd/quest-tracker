package libcore.io;

import android.system.ErrnoException;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.net.DatagramSocketImpl;
import java.net.Socket;
import java.net.SocketImpl;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class IoUtils {
    private IoUtils() {
    }

    public static int acquireRawFd(FileDescriptor fd) {
        Objects.requireNonNull(fd);
        FileDescriptor copy = fd.release$();
        int rawFd = copy.getInt$();
        long previousOwnerId = copy.getOwnerId$();
        if (!(rawFd == -1 || previousOwnerId == 0)) {
            Libcore.os.android_fdsan_exchange_owner_tag(copy, previousOwnerId, 0);
        }
        return rawFd;
    }

    private static boolean isParcelFileDescriptor(Object object) {
        try {
            if (Class.forName("android.os.ParcelFileDescriptor").isInstance(object)) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static long generateFdOwnerId(Object owner) {
        long tagType;
        if (owner == null) {
            return 0;
        }
        if (owner instanceof FileInputStream) {
            tagType = 5;
        } else if (owner instanceof FileOutputStream) {
            tagType = 6;
        } else if (owner instanceof RandomAccessFile) {
            tagType = 7;
        } else if (owner instanceof DatagramSocketImpl) {
            tagType = 10;
        } else if (owner instanceof SocketImpl) {
            tagType = 11;
        } else if (isParcelFileDescriptor(owner)) {
            tagType = 8;
        } else {
            tagType = 255;
        }
        return (tagType << 56) | ((long) System.identityHashCode(owner));
    }

    public static void setFdOwner(FileDescriptor fd, Object owner) {
        Objects.requireNonNull(fd);
        Objects.requireNonNull(owner);
        long previousOwnerId = fd.getOwnerId$();
        if (previousOwnerId == 0) {
            long ownerId = generateFdOwnerId(owner);
            fd.setOwnerId$(ownerId);
            Libcore.os.android_fdsan_exchange_owner_tag(fd, previousOwnerId, ownerId);
            return;
        }
        throw new IllegalStateException("Attempted to take ownership of already-owned FileDescriptor");
    }

    public static void close(FileDescriptor fd) throws IOException {
        IoBridge.closeAndSignalBlockedThreads(fd);
    }

    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static void closeQuietly(FileDescriptor fd) {
        try {
            close(fd);
        } catch (IOException e) {
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }

    public static void setBlocking(FileDescriptor fd, boolean blocking) throws IOException {
        int flags;
        try {
            int flags2 = Libcore.os.fcntlVoid(fd, OsConstants.F_GETFL);
            if (!blocking) {
                flags = flags2 | OsConstants.O_NONBLOCK;
            } else {
                flags = flags2 & (~OsConstants.O_NONBLOCK);
            }
            Libcore.os.fcntlInt(fd, OsConstants.F_SETFL, flags);
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
    }

    public static byte[] readFileAsByteArray(String absolutePath) throws IOException {
        return new FileReader(absolutePath).readFully().toByteArray();
    }

    public static String readFileAsString(String absolutePath) throws IOException {
        return new FileReader(absolutePath).readFully().toString(StandardCharsets.UTF_8);
    }

    @Deprecated
    public static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                file.delete();
            }
        }
    }

    @Deprecated
    public static File createTemporaryDirectory(String prefix) {
        File result;
        do {
            result = new File(System.getProperty("java.io.tmpdir"), prefix + Math.randomIntInternal());
        } while (!result.mkdir());
        return result;
    }

    public static boolean canOpenReadOnly(String path) {
        try {
            Libcore.os.close(Libcore.os.open(path, OsConstants.O_RDONLY, 0));
            return true;
        } catch (ErrnoException e) {
            return false;
        }
    }

    public static void throwInterruptedIoException() throws InterruptedIOException {
        Thread.currentThread().interrupt();
        throw new InterruptedIOException();
    }

    /* access modifiers changed from: private */
    public static class FileReader {
        private byte[] bytes;
        private int count;
        private FileDescriptor fd;
        private boolean unknownLength;

        public FileReader(String absolutePath) throws IOException {
            try {
                this.fd = IoBridge.open(absolutePath, OsConstants.O_RDONLY);
                try {
                    int capacity = (int) Libcore.os.fstat(this.fd).st_size;
                    if (capacity == 0) {
                        this.unknownLength = true;
                        capacity = 8192;
                    }
                    this.bytes = new byte[capacity];
                } catch (ErrnoException exception) {
                    IoUtils.closeQuietly(this.fd);
                    throw exception.rethrowAsIOException();
                }
            } catch (FileNotFoundException fnfe) {
                throw fnfe;
            }
        }

        public FileReader readFully() throws IOException {
            int capacity = this.bytes.length;
            while (true) {
                try {
                    int read = Libcore.os.read(this.fd, this.bytes, this.count, capacity - this.count);
                    if (read == 0) {
                        break;
                    }
                    this.count += read;
                    if (this.count == capacity) {
                        if (!this.unknownLength) {
                            break;
                        }
                        int newCapacity = capacity * 2;
                        byte[] newBytes = new byte[newCapacity];
                        System.arraycopy(this.bytes, 0, newBytes, 0, capacity);
                        this.bytes = newBytes;
                        capacity = newCapacity;
                    }
                } catch (ErrnoException e) {
                    throw e.rethrowAsIOException();
                } catch (Throwable th) {
                    IoUtils.closeQuietly(this.fd);
                    throw th;
                }
            }
            IoUtils.closeQuietly(this.fd);
            return this;
        }

        @FindBugsSuppressWarnings({"EI_EXPOSE_REP"})
        public byte[] toByteArray() {
            int i = this.count;
            byte[] bArr = this.bytes;
            if (i == bArr.length) {
                return bArr;
            }
            byte[] result = new byte[i];
            System.arraycopy(bArr, 0, result, 0, i);
            return result;
        }

        public String toString(Charset cs) {
            return new String(this.bytes, 0, this.count, cs);
        }
    }
}
