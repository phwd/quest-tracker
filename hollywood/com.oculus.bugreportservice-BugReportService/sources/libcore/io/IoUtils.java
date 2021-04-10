package libcore.io;

import android.system.ErrnoException;
import android.system.OsConstants;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketImpl;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class IoUtils {
    private static boolean isParcelFileDescriptor(Object obj) {
        try {
            if (Class.forName("android.os.ParcelFileDescriptor").isInstance(obj)) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException unused) {
        }
    }

    private static long generateFdOwnerId(Object obj) {
        long j;
        if (obj == null) {
            return 0;
        }
        if (obj instanceof FileInputStream) {
            j = 5;
        } else if (obj instanceof FileOutputStream) {
            j = 6;
        } else if (obj instanceof SocketImpl) {
            j = 11;
        } else {
            j = isParcelFileDescriptor(obj) ? 8 : 255;
        }
        return (j << 56) | ((long) System.identityHashCode(obj));
    }

    public static void setFdOwner(FileDescriptor fileDescriptor, Object obj) {
        Objects.requireNonNull(fileDescriptor);
        Objects.requireNonNull(obj);
        long ownerId$ = fileDescriptor.getOwnerId$();
        if (ownerId$ == 0) {
            long generateFdOwnerId = generateFdOwnerId(obj);
            fileDescriptor.setOwnerId$(generateFdOwnerId);
            Libcore.os.android_fdsan_exchange_owner_tag(fileDescriptor, ownerId$, generateFdOwnerId);
            return;
        }
        throw new IllegalStateException("Attempted to take ownership of already-owned FileDescriptor");
    }

    public static void close(FileDescriptor fileDescriptor) {
        IoBridge.closeAndSignalBlockedThreads(fileDescriptor);
    }

    public static void closeQuietly(FileDescriptor fileDescriptor) {
        try {
            close(fileDescriptor);
        } catch (IOException unused) {
        }
    }

    public static void setBlocking(FileDescriptor fileDescriptor, boolean z) {
        int i;
        try {
            int fcntlVoid = Libcore.os.fcntlVoid(fileDescriptor, OsConstants.F_GETFL);
            if (!z) {
                i = OsConstants.O_NONBLOCK | fcntlVoid;
            } else {
                i = (~OsConstants.O_NONBLOCK) & fcntlVoid;
            }
            Libcore.os.fcntlInt(fileDescriptor, OsConstants.F_SETFL, i);
        } catch (ErrnoException e) {
            throw e.rethrowAsIOException();
        }
    }

    public static String readFileAsString(String str) {
        FileReader fileReader = new FileReader(str);
        fileReader.readFully();
        fileReader.toString(StandardCharsets.UTF_8);
        throw null;
    }

    /* access modifiers changed from: private */
    public static class FileReader {
        private byte[] bytes;
        private int count;
        private FileDescriptor fd;
        private boolean unknownLength;

        public FileReader(String str) {
            try {
                this.fd = IoBridge.open(str, OsConstants.O_RDONLY);
                try {
                    int i = (int) Libcore.os.fstat(this.fd).st_size;
                    if (i == 0) {
                        this.unknownLength = true;
                        i = 8192;
                    }
                    this.bytes = new byte[i];
                } catch (ErrnoException e) {
                    IoUtils.closeQuietly(this.fd);
                    throw e.rethrowAsIOException();
                }
            } catch (FileNotFoundException e2) {
                throw e2;
            }
        }

        public FileReader readFully() {
            int length = this.bytes.length;
            while (true) {
                try {
                    int read = Libcore.os.read(this.fd, this.bytes, this.count, length - this.count);
                    if (read == 0) {
                        break;
                    }
                    this.count += read;
                    if (this.count == length) {
                        if (!this.unknownLength) {
                            break;
                        }
                        int i = length * 2;
                        byte[] bArr = new byte[i];
                        System.arraycopy(this.bytes, 0, bArr, 0, length);
                        this.bytes = bArr;
                        length = i;
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

        public String toString(Charset charset) {
            new String(this.bytes, 0, this.count, charset);
            throw null;
        }
    }
}
