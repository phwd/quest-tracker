package sun.nio.ch;

import java.nio.channels.FileLock;
import java.util.List;

/* access modifiers changed from: package-private */
public abstract class FileLockTable {
    public abstract void remove(FileLock fileLock);

    public abstract List removeAll();
}
