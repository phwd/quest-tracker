package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.spi.SelectorProvider;

class SourceChannelImpl extends Pipe.SourceChannel implements SelChImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ST_INUSE = 0;
    private static final int ST_KILLED = 1;
    private static final int ST_UNINITIALIZED = -1;
    private static final NativeDispatcher nd = new FileDispatcherImpl();
    FileDescriptor fd;
    int fdVal;
    private final Object lock = new Object();
    private volatile int state = -1;
    private final Object stateLock = new Object();
    private volatile long thread = 0;

    @Override // sun.nio.ch.SelChImpl
    public FileDescriptor getFD() {
        return this.fd;
    }

    @Override // sun.nio.ch.SelChImpl
    public int getFDVal() {
        return this.fdVal;
    }

    SourceChannelImpl(SelectorProvider sp, FileDescriptor fd2) {
        super(sp);
        this.fd = fd2;
        this.fdVal = IOUtil.fdVal(fd2);
        this.state = 0;
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implCloseSelectableChannel() throws IOException {
        synchronized (this.stateLock) {
            if (this.state != 1) {
                nd.preClose(this.fd);
            }
            long th = this.thread;
            if (th != 0) {
                NativeThread.signal(th);
            }
            if (!isRegistered()) {
                kill();
            }
        }
    }

    @Override // sun.nio.ch.SelChImpl
    public void kill() throws IOException {
        synchronized (this.stateLock) {
            if (this.state != 1) {
                if (this.state == -1) {
                    this.state = 1;
                    return;
                }
                nd.close(this.fd);
                this.state = 1;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implConfigureBlocking(boolean block) throws IOException {
        IOUtil.configureBlocking(this.fd, block);
    }

    public boolean translateReadyOps(int ops, int initialOps, SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps();
        int oldOps = sk.nioReadyOps();
        int newOps = initialOps;
        if ((Net.POLLNVAL & ops) != 0) {
            throw new Error("POLLNVAL detected");
        } else if (((Net.POLLERR | Net.POLLHUP) & ops) != 0) {
            sk.nioReadyOps(intOps);
            return ((~oldOps) & intOps) != 0;
        } else {
            if (!((Net.POLLIN & ops) == 0 || (intOps & 1) == 0)) {
                newOps |= 1;
            }
            sk.nioReadyOps(newOps);
            return ((~oldOps) & newOps) != 0;
        }
    }

    @Override // sun.nio.ch.SelChImpl
    public boolean translateAndUpdateReadyOps(int ops, SelectionKeyImpl sk) {
        return translateReadyOps(ops, sk.nioReadyOps(), sk);
    }

    @Override // sun.nio.ch.SelChImpl
    public boolean translateAndSetReadyOps(int ops, SelectionKeyImpl sk) {
        return translateReadyOps(ops, 0, sk);
    }

    @Override // sun.nio.ch.SelChImpl
    public void translateAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        if (ops == 1) {
            ops = Net.POLLIN;
        }
        sk.selector.putEventOps(sk, ops);
    }

    private void ensureOpen() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer dst) throws IOException {
        if (dst != null) {
            ensureOpen();
            synchronized (this.lock) {
                int n = 0;
                boolean z = true;
                try {
                    begin();
                    if (!isOpen()) {
                        this.thread = 0;
                        if (n <= 0) {
                            if (n != -2) {
                                z = false;
                            }
                        }
                        end(z);
                        return 0;
                    }
                    this.thread = NativeThread.current();
                    do {
                        n = IOUtil.read(this.fd, dst, -1, nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    return IOStatus.normalize(n);
                } finally {
                    this.thread = 0;
                    if (n <= 0 && n != -2) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.nio.channels.ScatteringByteChannel
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        if (offset >= 0 && length >= 0 && offset <= dsts.length - length) {
            return read(Util.subsequence(dsts, offset, length));
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.nio.channels.ScatteringByteChannel
    public long read(ByteBuffer[] dsts) throws IOException {
        long n;
        if (dsts != null) {
            ensureOpen();
            synchronized (this.lock) {
                boolean z = false;
                try {
                    begin();
                    if (!isOpen()) {
                        return 0;
                    }
                    this.thread = NativeThread.current();
                    do {
                        n = IOUtil.read(this.fd, dsts, nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    long normalize = IOStatus.normalize(n);
                    this.thread = 0;
                    if (n > 0 || n == -2) {
                        z = true;
                    }
                    end(z);
                    return normalize;
                } finally {
                    this.thread = 0;
                    if (0 > 0 || 0 == -2) {
                        z = true;
                    }
                    end(z);
                }
            }
        } else {
            throw new NullPointerException();
        }
    }
}
