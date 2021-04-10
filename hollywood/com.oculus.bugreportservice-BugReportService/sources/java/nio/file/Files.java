package java.nio.file;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.util.EnumSet;
import java.util.Set;

public final class Files {
    private static FileSystemProvider provider(Path path) {
        return path.getFileSystem().provider();
    }

    public static OutputStream newOutputStream(Path path, OpenOption... openOptionArr) {
        return provider(path).newOutputStream(path, openOptionArr);
    }

    public static SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute... fileAttributeArr) {
        return provider(path).newByteChannel(path, set, fileAttributeArr);
    }

    public static Path createFile(Path path, FileAttribute... fileAttributeArr) {
        newByteChannel(path, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE), fileAttributeArr).close();
        return path;
    }

    public static Path createDirectory(Path path, FileAttribute... fileAttributeArr) {
        provider(path).createDirectory(path, fileAttributeArr);
        return path;
    }

    public static Path createTempFile(String str, String str2, FileAttribute... fileAttributeArr) {
        return TempFileHelper.createTempFile(null, str, str2, fileAttributeArr);
    }

    public static void delete(Path path) {
        provider(path).delete(path);
    }

    public static boolean deleteIfExists(Path path) {
        return provider(path).deleteIfExists(path);
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    private static long copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        if (r7 != null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        $closeResource(r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0060, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long copy(java.io.InputStream r6, java.nio.file.Path r7, java.nio.file.CopyOption... r8) {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.copy(java.io.InputStream, java.nio.file.Path, java.nio.file.CopyOption[]):long");
    }
}
