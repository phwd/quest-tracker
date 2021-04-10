package sun.nio.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* access modifiers changed from: package-private */
public abstract class AbstractPath implements Path {
    protected AbstractPath() {
    }

    @Override // java.nio.file.Path
    public final boolean startsWith(String other) {
        return startsWith(getFileSystem().getPath(other, new String[0]));
    }

    @Override // java.nio.file.Path
    public final boolean endsWith(String other) {
        return endsWith(getFileSystem().getPath(other, new String[0]));
    }

    @Override // java.nio.file.Path
    public final Path resolve(String other) {
        return resolve(getFileSystem().getPath(other, new String[0]));
    }

    @Override // java.nio.file.Path
    public final Path resolveSibling(Path other) {
        if (other != null) {
            Path parent = getParent();
            return parent == null ? other : parent.resolve(other);
        }
        throw new NullPointerException();
    }

    @Override // java.nio.file.Path
    public final Path resolveSibling(String other) {
        return resolveSibling(getFileSystem().getPath(other, new String[0]));
    }

    @Override // java.nio.file.Path, java.lang.Iterable
    public final Iterator<Path> iterator() {
        return new Iterator<Path>() {
            /* class sun.nio.fs.AbstractPath.AnonymousClass1 */
            private int i = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < AbstractPath.this.getNameCount();
            }

            @Override // java.util.Iterator
            public Path next() {
                if (this.i < AbstractPath.this.getNameCount()) {
                    Path result = AbstractPath.this.getName(this.i);
                    this.i++;
                    return result;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.nio.file.Path
    public final File toFile() {
        return new File(toString());
    }

    @Override // java.nio.file.Watchable, java.nio.file.Path
    public final WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException {
        return register(watcher, events, new WatchEvent.Modifier[0]);
    }
}
