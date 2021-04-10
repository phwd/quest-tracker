package java.nio.file;

import java.io.File;

public interface Path extends Comparable, Iterable, Watchable {
    FileSystem getFileSystem();

    Path getParent();

    Path resolve(Path path);

    File toFile();
}
