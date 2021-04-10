package java.nio.file.spi;

import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystem;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.Set;

public abstract class FileSystemProvider {
    private static boolean loadingProviders = false;
    private static final Object lock = new Object();

    public abstract void createDirectory(Path path, FileAttribute... fileAttributeArr);

    public abstract void delete(Path path);

    public abstract FileSystem getFileSystem(URI uri);

    public abstract String getScheme();

    public abstract SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute... fileAttributeArr);

    private static Void checkPermission() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            return null;
        }
        securityManager.checkPermission(new RuntimePermission("fileSystemProvider"));
        throw null;
    }

    private FileSystemProvider(Void r1) {
    }

    protected FileSystemProvider() {
        this(checkPermission());
    }

    public OutputStream newOutputStream(Path path, OpenOption... openOptionArr) {
        int length = openOptionArr.length;
        HashSet hashSet = new HashSet(length + 3);
        if (length == 0) {
            hashSet.add(StandardOpenOption.CREATE);
            hashSet.add(StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            for (OpenOption openOption : openOptionArr) {
                if (openOption != StandardOpenOption.READ) {
                    hashSet.add(openOption);
                } else {
                    throw new IllegalArgumentException("READ not allowed");
                }
            }
        }
        hashSet.add(StandardOpenOption.WRITE);
        return Channels.newOutputStream(newByteChannel(path, hashSet, new FileAttribute[0]));
    }

    public boolean deleteIfExists(Path path) {
        try {
            delete(path);
            return true;
        } catch (NoSuchFileException unused) {
            return false;
        }
    }
}
