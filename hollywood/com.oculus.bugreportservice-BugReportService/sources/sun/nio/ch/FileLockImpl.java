package sun.nio.ch;

import java.nio.channels.Channel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLock;

public class FileLockImpl extends FileLock {
    private volatile boolean valid;

    @Override // java.nio.channels.FileLock
    public boolean isValid() {
        return this.valid;
    }

    /* access modifiers changed from: package-private */
    public void invalidate() {
        this.valid = false;
    }

    @Override // java.nio.channels.FileLock
    public synchronized void release() {
        Channel acquiredBy = acquiredBy();
        if (!acquiredBy.isOpen()) {
            throw new ClosedChannelException();
        } else if (this.valid) {
            if (acquiredBy instanceof FileChannelImpl) {
                ((FileChannelImpl) acquiredBy).release(this);
            } else if (acquiredBy instanceof AsynchronousFileChannelImpl) {
                ((AsynchronousFileChannelImpl) acquiredBy).release(this);
            } else {
                throw new AssertionError();
            }
            this.valid = false;
        }
    }
}
