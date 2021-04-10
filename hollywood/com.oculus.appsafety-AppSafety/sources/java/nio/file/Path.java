package java.nio.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.WatchEvent;
import java.util.Iterator;

public interface Path extends Comparable<Path>, Iterable<Path>, Watchable {
    int compareTo(Path path);

    boolean endsWith(String str);

    boolean endsWith(Path path);

    boolean equals(Object obj);

    Path getFileName();

    FileSystem getFileSystem();

    Path getName(int i);

    int getNameCount();

    Path getParent();

    Path getRoot();

    int hashCode();

    boolean isAbsolute();

    @Override // java.lang.Iterable
    Iterator<Path> iterator();

    Path normalize();

    @Override // java.nio.file.Watchable
    WatchKey register(WatchService watchService, WatchEvent.Kind<?>... kindArr) throws IOException;

    @Override // java.nio.file.Watchable
    WatchKey register(WatchService watchService, WatchEvent.Kind<?>[] kindArr, WatchEvent.Modifier... modifierArr) throws IOException;

    Path relativize(Path path);

    Path resolve(String str);

    Path resolve(Path path);

    Path resolveSibling(String str);

    Path resolveSibling(Path path);

    boolean startsWith(String str);

    boolean startsWith(Path path);

    Path subpath(int i, int i2);

    Path toAbsolutePath();

    File toFile();

    Path toRealPath(LinkOption... linkOptionArr) throws IOException;

    String toString();

    URI toUri();
}
