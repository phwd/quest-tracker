package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import sun.net.NetHooks;

abstract class AsynchronousServerSocketChannelImpl extends AsynchronousServerSocketChannel implements Cancellable, Groupable {
    private volatile boolean acceptKilled;
    private ReadWriteLock closeLock = new ReentrantReadWriteLock();
    protected final FileDescriptor fd = Net.serverSocket(true);
    private boolean isReuseAddress;
    protected volatile InetSocketAddress localAddress = null;
    private volatile boolean open = true;
    private final Object stateLock = new Object();

    /* access modifiers changed from: package-private */
    public abstract Future<AsynchronousSocketChannel> implAccept(Object obj, CompletionHandler<AsynchronousSocketChannel, Object> completionHandler);

    /* access modifiers changed from: package-private */
    public abstract void implClose() throws IOException;

    AsynchronousServerSocketChannelImpl(AsynchronousChannelGroupImpl group) {
        super(group.provider());
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return this.open;
    }

    /* access modifiers changed from: package-private */
    public final void begin() throws IOException {
        this.closeLock.readLock().lock();
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    /* access modifiers changed from: package-private */
    public final void end() {
        this.closeLock.readLock().unlock();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.AsynchronousChannel, java.nio.channels.Channel
    public final void close() throws IOException {
        this.closeLock.writeLock().lock();
        try {
            if (this.open) {
                this.open = false;
                this.closeLock.writeLock().unlock();
                implClose();
            }
        } finally {
            this.closeLock.writeLock().unlock();
        }
    }

    @Override // java.nio.channels.AsynchronousServerSocketChannel
    public final Future<AsynchronousSocketChannel> accept() {
        return implAccept(null, null);
    }

    @Override // java.nio.channels.AsynchronousServerSocketChannel
    public final <A> void accept(A attachment, CompletionHandler<AsynchronousSocketChannel, ? super A> handler) {
        if (handler != null) {
            implAccept(attachment, handler);
            return;
        }
        throw new NullPointerException("'handler' is null");
    }

    /* access modifiers changed from: package-private */
    public final boolean isAcceptKilled() {
        return this.acceptKilled;
    }

    @Override // sun.nio.ch.Cancellable
    public final void onCancel(PendingFuture<?, ?> pendingFuture) {
        this.acceptKilled = true;
    }

    @Override // java.nio.channels.AsynchronousServerSocketChannel
    public final AsynchronousServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {
        InetSocketAddress isa;
        if (local == null) {
            isa = new InetSocketAddress(0);
        } else {
            isa = Net.checkAddress(local);
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkListen(isa.getPort());
        }
        try {
            begin();
            synchronized (this.stateLock) {
                if (this.localAddress == null) {
                    NetHooks.beforeTcpBind(this.fd, isa.getAddress(), isa.getPort());
                    Net.bind(this.fd, isa.getAddress(), isa.getPort());
                    Net.listen(this.fd, backlog < 1 ? 50 : backlog);
                    this.localAddress = Net.localAddress(this.fd);
                } else {
                    throw new AlreadyBoundException();
                }
            }
            return this;
        } finally {
            end();
        }
    }

    @Override // java.nio.channels.AsynchronousServerSocketChannel, java.nio.channels.NetworkChannel
    public final SocketAddress getLocalAddress() throws IOException {
        if (isOpen()) {
            return Net.getRevealedLocalAddress(this.localAddress);
        }
        throw new ClosedChannelException();
    }

    @Override // java.nio.channels.AsynchronousServerSocketChannel, java.nio.channels.AsynchronousServerSocketChannel, java.nio.channels.NetworkChannel
    public final <T> AsynchronousServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        } else if (supportedOptions().contains(name)) {
            try {
                begin();
                if (name != StandardSocketOptions.SO_REUSEADDR || !Net.useExclusiveBind()) {
                    Net.setSocketOption(this.fd, Net.UNSPEC, name, value);
                } else {
                    this.isReuseAddress = value.booleanValue();
                }
                return this;
            } finally {
                end();
            }
        } else {
            throw new UnsupportedOperationException("'" + ((Object) name) + "' not supported");
        }
    }

    @Override // java.nio.channels.NetworkChannel
    public final <T> T getOption(SocketOption<T> name) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        } else if (supportedOptions().contains(name)) {
            try {
                begin();
                if (name == StandardSocketOptions.SO_REUSEADDR && Net.useExclusiveBind()) {
                    return (T) Boolean.valueOf(this.isReuseAddress);
                }
                T t = (T) Net.getSocketOption(this.fd, Net.UNSPEC, name);
                end();
                return t;
            } finally {
                end();
            }
        } else {
            throw new UnsupportedOperationException("'" + ((Object) name) + "' not supported");
        }
    }

    /* access modifiers changed from: private */
    public static class DefaultOptionsHolder {
        static final Set<SocketOption<?>> defaultOptions = defaultOptions();

        private DefaultOptionsHolder() {
        }

        private static Set<SocketOption<?>> defaultOptions() {
            HashSet<SocketOption<?>> set = new HashSet<>(2);
            set.add(StandardSocketOptions.SO_RCVBUF);
            set.add(StandardSocketOptions.SO_REUSEADDR);
            return Collections.unmodifiableSet(set);
        }
    }

    @Override // java.nio.channels.NetworkChannel
    public final Set<SocketOption<?>> supportedOptions() {
        return DefaultOptionsHolder.defaultOptions;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append('[');
        if (!isOpen()) {
            sb.append("closed");
        } else if (this.localAddress == null) {
            sb.append("unbound");
        } else {
            sb.append(Net.getRevealedLocalAddressAsString(this.localAddress));
        }
        sb.append(']');
        return sb.toString();
    }
}
