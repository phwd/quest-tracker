package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import sun.net.NetHooks;

/* access modifiers changed from: package-private */
public class ServerSocketChannelImpl extends ServerSocketChannel implements SelChImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ST_INUSE = 0;
    private static final int ST_KILLED = 1;
    private static final int ST_UNINITIALIZED = -1;
    private static NativeDispatcher nd = new SocketDispatcher();
    private final FileDescriptor fd;
    private int fdVal;
    private boolean isReuseAddress;
    private InetSocketAddress localAddress;
    private final Object lock;
    ServerSocket socket;
    private int state;
    private final Object stateLock;
    private volatile long thread;

    private native int accept0(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, InetSocketAddress[] inetSocketAddressArr) throws IOException;

    private static native void initIDs();

    static {
        initIDs();
    }

    ServerSocketChannelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        this.thread = 0;
        this.lock = new Object();
        this.stateLock = new Object();
        this.state = -1;
        this.fd = Net.serverSocket(true);
        this.fdVal = IOUtil.fdVal(this.fd);
        this.state = 0;
    }

    ServerSocketChannelImpl(SelectorProvider sp, FileDescriptor fd2, boolean bound) throws IOException {
        super(sp);
        this.thread = 0;
        this.lock = new Object();
        this.stateLock = new Object();
        this.state = -1;
        this.fd = fd2;
        this.fdVal = IOUtil.fdVal(fd2);
        this.state = 0;
        if (bound) {
            this.localAddress = Net.localAddress(fd2);
        }
    }

    @Override // java.nio.channels.ServerSocketChannel
    public ServerSocket socket() {
        ServerSocket serverSocket;
        synchronized (this.stateLock) {
            if (this.socket == null) {
                this.socket = ServerSocketAdaptor.create(this);
            }
            serverSocket = this.socket;
        }
        return serverSocket;
    }

    @Override // java.nio.channels.NetworkChannel, java.nio.channels.ServerSocketChannel
    public SocketAddress getLocalAddress() throws IOException {
        InetSocketAddress inetSocketAddress;
        synchronized (this.stateLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (this.localAddress == null) {
                inetSocketAddress = this.localAddress;
            } else {
                inetSocketAddress = Net.getRevealedLocalAddress(Net.asInetSocketAddress(this.localAddress));
            }
        }
        return inetSocketAddress;
    }

    @Override // java.nio.channels.NetworkChannel, java.nio.channels.ServerSocketChannel, java.nio.channels.ServerSocketChannel
    public <T> ServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        } else if (supportedOptions().contains(name)) {
            synchronized (this.stateLock) {
                if (!isOpen()) {
                    throw new ClosedChannelException();
                } else if (name == StandardSocketOptions.IP_TOS) {
                    Net.setSocketOption(this.fd, Net.isIPv6Available() ? StandardProtocolFamily.INET6 : StandardProtocolFamily.INET, name, value);
                    return this;
                } else {
                    if (name != StandardSocketOptions.SO_REUSEADDR || !Net.useExclusiveBind()) {
                        Net.setSocketOption(this.fd, Net.UNSPEC, name, value);
                    } else {
                        this.isReuseAddress = value.booleanValue();
                    }
                    return this;
                }
            }
        } else {
            throw new UnsupportedOperationException("'" + ((Object) name) + "' not supported");
        }
    }

    @Override // java.nio.channels.NetworkChannel
    public <T> T getOption(SocketOption<T> name) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        } else if (supportedOptions().contains(name)) {
            synchronized (this.stateLock) {
                if (!isOpen()) {
                    throw new ClosedChannelException();
                } else if (name != StandardSocketOptions.SO_REUSEADDR || !Net.useExclusiveBind()) {
                    return (T) Net.getSocketOption(this.fd, Net.UNSPEC, name);
                } else {
                    return (T) Boolean.valueOf(this.isReuseAddress);
                }
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
            set.add(StandardSocketOptions.IP_TOS);
            return Collections.unmodifiableSet(set);
        }
    }

    @Override // java.nio.channels.NetworkChannel
    public final Set<SocketOption<?>> supportedOptions() {
        return DefaultOptionsHolder.defaultOptions;
    }

    public boolean isBound() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.localAddress != null;
        }
        return z;
    }

    public InetSocketAddress localAddress() {
        InetSocketAddress inetSocketAddress;
        synchronized (this.stateLock) {
            inetSocketAddress = this.localAddress;
        }
        return inetSocketAddress;
    }

    @Override // java.nio.channels.ServerSocketChannel
    public ServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {
        InetSocketAddress isa;
        synchronized (this.lock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (!isBound()) {
                if (local == null) {
                    isa = new InetSocketAddress(0);
                } else {
                    isa = Net.checkAddress(local);
                }
                SecurityManager sm = System.getSecurityManager();
                if (sm != null) {
                    sm.checkListen(isa.getPort());
                }
                NetHooks.beforeTcpBind(this.fd, isa.getAddress(), isa.getPort());
                Net.bind(this.fd, isa.getAddress(), isa.getPort());
                Net.listen(this.fd, backlog < 1 ? 50 : backlog);
                synchronized (this.stateLock) {
                    this.localAddress = Net.localAddress(this.fd);
                }
            } else {
                throw new AlreadyBoundException();
            }
        }
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r4v4, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5 */
    @Override // java.nio.channels.ServerSocketChannel
    public SocketChannel accept() throws IOException {
        int n;
        synchronized (this.lock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (isBound()) {
                FileDescriptor newfd = new FileDescriptor();
                int i = 1;
                InetSocketAddress[] isaa = new InetSocketAddress[i];
                try {
                    begin();
                    if (!isOpen()) {
                        return null;
                    }
                    this.thread = NativeThread.current();
                    do {
                        n = accept(this.fd, newfd, isaa);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    this.thread = 0;
                    end(n > 0 ? i : false);
                    if (n < i) {
                        return null;
                    }
                    IOUtil.configureBlocking(newfd, i);
                    InetSocketAddress isa = isaa[0];
                    SocketChannel sc = new SocketChannelImpl(provider(), newfd, isa);
                    SecurityManager sm = System.getSecurityManager();
                    if (sm != null) {
                        try {
                            sm.checkAccept(isa.getAddress().getHostAddress(), isa.getPort());
                        } catch (SecurityException x) {
                            sc.close();
                            throw x;
                        }
                    }
                    return sc;
                } finally {
                    this.thread = 0;
                    if (0 <= 0) {
                        i = 0;
                    }
                    end(i);
                }
            } else {
                throw new NotYetBoundException();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implConfigureBlocking(boolean block) throws IOException {
        IOUtil.configureBlocking(this.fd, block);
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

    public boolean translateReadyOps(int ops, int initialOps, SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps();
        int oldOps = sk.nioReadyOps();
        int newOps = initialOps;
        if ((Net.POLLNVAL & ops) != 0) {
            return false;
        }
        if (((Net.POLLERR | Net.POLLHUP) & ops) != 0) {
            sk.nioReadyOps(intOps);
            if (((~oldOps) & intOps) != 0) {
                return true;
            }
            return false;
        }
        if (!((Net.POLLIN & ops) == 0 || (intOps & 16) == 0)) {
            newOps |= 16;
        }
        sk.nioReadyOps(newOps);
        if (((~oldOps) & newOps) != 0) {
            return true;
        }
        return false;
    }

    @Override // sun.nio.ch.SelChImpl
    public boolean translateAndUpdateReadyOps(int ops, SelectionKeyImpl sk) {
        return translateReadyOps(ops, sk.nioReadyOps(), sk);
    }

    @Override // sun.nio.ch.SelChImpl
    public boolean translateAndSetReadyOps(int ops, SelectionKeyImpl sk) {
        return translateReadyOps(ops, 0, sk);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r9.thread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        if (r1 <= 0) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        end(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        r6 = sun.nio.ch.Net.poll(r9.fd, r10, r11);
        r9.thread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int poll(int r10, long r11) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.Object r0 = r9.lock
            monitor-enter(r0)
            r1 = 0
            r2 = 1
            r3 = 0
            r5 = 0
            r9.begin()     // Catch:{ all -> 0x003e }
            java.lang.Object r6 = r9.stateLock     // Catch:{ all -> 0x003e }
            monitor-enter(r6)     // Catch:{ all -> 0x003e }
            boolean r7 = r9.isOpen()     // Catch:{ all -> 0x003b }
            if (r7 != 0) goto L_0x0021
            monitor-exit(r6)     // Catch:{ all -> 0x003b }
            r9.thread = r3     // Catch:{ all -> 0x0049 }
            if (r1 <= 0) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r2 = r5
        L_0x001c:
            r9.end(r2)     // Catch:{ all -> 0x0049 }
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            return r5
        L_0x0021:
            long r7 = sun.nio.ch.NativeThread.current()
            r9.thread = r7
            monitor-exit(r6)
            java.io.FileDescriptor r6 = r9.fd
            int r6 = sun.nio.ch.Net.poll(r6, r10, r11)
            r1 = r6
            r9.thread = r3
            if (r1 <= 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r2 = r5
        L_0x0035:
            r9.end(r2)
            monitor-exit(r0)
            return r1
        L_0x003b:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x003e:
            r6 = move-exception
            r9.thread = r3
            if (r1 <= 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r2 = r5
        L_0x0045:
            r9.end(r2)
            throw r6
        L_0x0049:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.ServerSocketChannelImpl.poll(int, long):int");
    }

    @Override // sun.nio.ch.SelChImpl
    public void translateAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        int newOps = 0;
        if ((ops & 16) != 0) {
            newOps = 0 | Net.POLLIN;
        }
        sk.selector.putEventOps(sk, newOps);
    }

    @Override // sun.nio.ch.SelChImpl
    public FileDescriptor getFD() {
        return this.fd;
    }

    @Override // sun.nio.ch.SelChImpl
    public int getFDVal() {
        return this.fdVal;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append('[');
        if (!isOpen()) {
            sb.append("closed");
        } else {
            synchronized (this.stateLock) {
                InetSocketAddress addr = localAddress();
                if (addr == null) {
                    sb.append("unbound");
                } else {
                    sb.append(Net.getRevealedLocalAddressAsString(addr));
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private int accept(FileDescriptor ssfd, FileDescriptor newfd, InetSocketAddress[] isaa) throws IOException {
        return accept0(ssfd, newfd, isaa);
    }
}
