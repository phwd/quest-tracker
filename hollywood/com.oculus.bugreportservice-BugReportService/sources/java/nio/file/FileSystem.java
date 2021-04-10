package java.nio.file;

import java.io.Closeable;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;

public abstract class FileSystem implements Closeable {
    public abstract Path getPath(String str, String... strArr);

    public abstract FileSystemProvider provider();

    public abstract Set supportedFileAttributeViews();
}
