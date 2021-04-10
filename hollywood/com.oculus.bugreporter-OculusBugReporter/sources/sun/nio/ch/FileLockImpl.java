package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.Channel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockImpl extends FileLock {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private volatile boolean valid = true;

    FileLockImpl(FileChannel channel, long position, long size, boolean shared) {
        super(channel, position, size, shared);
    }

    FileLockImpl(AsynchronousFileChannel channel, long position, long size, boolean shared) {
        super(channel, position, size, shared);
    }

    @Override // java.nio.channels.FileLock
    public boolean isValid() {
        return this.valid;
    }

    /* access modifiers changed from: package-private */
    public void invalidate() {
        this.valid = false;
    }

    @Override // java.nio.channels.FileLock
    public synchronized void release() throws IOException {
        Channel ch = acquiredBy();
        if (!ch.isOpen()) {
            throw new ClosedChannelException();
        } else if (this.valid) {
            if (ch instanceof FileChannelImpl) {
                ((FileChannelImpl) ch).release(this);
            } else if (ch instanceof AsynchronousFileChannelImpl) {
                ((AsynchronousFileChannelImpl) ch).release(this);
            } else {
                throw new AssertionError();
            }
            this.valid = false;
        }
    }
}
