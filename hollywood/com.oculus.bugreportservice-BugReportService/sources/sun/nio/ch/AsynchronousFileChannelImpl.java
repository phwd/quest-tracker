package sun.nio.ch;

import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.locks.ReadWriteLock;

abstract class AsynchronousFileChannelImpl extends AsynchronousFileChannel {
    protected final ReadWriteLock closeLock;
    protected volatile boolean closed;
    private volatile FileLockTable fileLockTable;

    /* access modifiers changed from: protected */
    public abstract void implRelease(FileLockImpl fileLockImpl);

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.closed;
    }

    /* access modifiers changed from: protected */
    public final void begin() {
        this.closeLock.readLock().lock();
        if (this.closed) {
            throw new ClosedChannelException();
        }
    }

    /* access modifiers changed from: protected */
    public final void end() {
        this.closeLock.readLock().unlock();
    }

    /* access modifiers changed from: protected */
    public final void removeFromFileLockTable(FileLockImpl fileLockImpl) {
        this.fileLockTable.remove(fileLockImpl);
    }

    /* access modifiers changed from: package-private */
    public final void release(FileLockImpl fileLockImpl) {
        try {
            begin();
            implRelease(fileLockImpl);
            removeFromFileLockTable(fileLockImpl);
        } finally {
            end();
        }
    }
}
