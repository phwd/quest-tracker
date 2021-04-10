package sun.nio.ch;

import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.PortUnreachableException;
import java.net.ProtocolFamily;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jdk.net.ExtendedSocketOptions;
import sun.net.ExtendedOptionsImpl;
import sun.net.ResourceManager;
import sun.nio.ch.MembershipKeyImpl;

/* access modifiers changed from: package-private */
public class DatagramChannelImpl extends DatagramChannel implements SelChImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ST_CONNECTED = 1;
    private static final int ST_KILLED = 2;
    private static final int ST_UNCONNECTED = 0;
    private static final int ST_UNINITIALIZED = -1;
    private static NativeDispatcher nd = new DatagramDispatcher();
    private InetAddress cachedSenderInetAddress;
    private int cachedSenderPort;
    private final ProtocolFamily family;
    final FileDescriptor fd;
    private final int fdVal;
    private final CloseGuard guard = CloseGuard.get();
    private boolean isReuseAddress;
    private InetSocketAddress localAddress;
    private final Object readLock = new Object();
    private volatile long readerThread = 0;
    private MembershipRegistry registry;
    private InetSocketAddress remoteAddress;
    private boolean reuseAddressEmulated;
    private SocketAddress sender;
    private DatagramSocket socket;
    private int state = -1;
    private final Object stateLock = new Object();
    private final Object writeLock = new Object();
    private volatile long writerThread = 0;

    private static native void disconnect0(FileDescriptor fileDescriptor, boolean z) throws IOException;

    private static native void initIDs();

    private native int receive0(FileDescriptor fileDescriptor, long j, int i, boolean z) throws IOException;

    private native int send0(boolean z, FileDescriptor fileDescriptor, long j, int i, InetAddress inetAddress, int i2) throws IOException;

    static {
        initIDs();
    }

    public DatagramChannelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        ResourceManager.beforeUdpCreate();
        try {
            this.family = Net.isIPv6Available() ? StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;
            this.fd = Net.socket(this.family, false);
            this.fdVal = IOUtil.fdVal(this.fd);
            this.state = 0;
            if (this.fd != null && this.fd.valid()) {
                this.guard.open("close");
            }
        } catch (IOException ioe) {
            ResourceManager.afterUdpClose();
            throw ioe;
        }
    }

    public DatagramChannelImpl(SelectorProvider sp, ProtocolFamily family2) throws IOException {
        super(sp);
        if (family2 == StandardProtocolFamily.INET || family2 == StandardProtocolFamily.INET6) {
            if (family2 != StandardProtocolFamily.INET6 || Net.isIPv6Available()) {
                this.family = family2;
                this.fd = Net.socket(family2, false);
                this.fdVal = IOUtil.fdVal(this.fd);
                this.state = 0;
                FileDescriptor fileDescriptor = this.fd;
                if (fileDescriptor != null && fileDescriptor.valid()) {
                    this.guard.open("close");
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("IPv6 not available");
        } else if (family2 == null) {
            throw new NullPointerException("'family' is null");
        } else {
            throw new UnsupportedOperationException("Protocol family not supported");
        }
    }

    public DatagramChannelImpl(SelectorProvider sp, FileDescriptor fd2) throws IOException {
        super(sp);
        this.family = Net.isIPv6Available() ? StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;
        this.fd = fd2;
        this.fdVal = IOUtil.fdVal(fd2);
        this.state = 0;
        this.localAddress = Net.localAddress(fd2);
        if (fd2 != null && fd2.valid()) {
            this.guard.open("close");
        }
    }

    @Override // java.nio.channels.DatagramChannel
    public DatagramSocket socket() {
        DatagramSocket datagramSocket;
        synchronized (this.stateLock) {
            if (this.socket == null) {
                this.socket = DatagramSocketAdaptor.create(this);
            }
            datagramSocket = this.socket;
        }
        return datagramSocket;
    }

    @Override // java.nio.channels.DatagramChannel, java.nio.channels.NetworkChannel
    public SocketAddress getLocalAddress() throws IOException {
        InetSocketAddress revealedLocalAddress;
        synchronized (this.stateLock) {
            if (isOpen()) {
                revealedLocalAddress = Net.getRevealedLocalAddress(this.localAddress);
            } else {
                throw new ClosedChannelException();
            }
        }
        return revealedLocalAddress;
    }

    @Override // java.nio.channels.DatagramChannel
    public SocketAddress getRemoteAddress() throws IOException {
        InetSocketAddress inetSocketAddress;
        synchronized (this.stateLock) {
            if (isOpen()) {
                inetSocketAddress = this.remoteAddress;
            } else {
                throw new ClosedChannelException();
            }
        }
        return inetSocketAddress;
    }

    @Override // java.nio.channels.DatagramChannel, java.nio.channels.DatagramChannel, java.nio.channels.NetworkChannel
    public <T> DatagramChannel setOption(SocketOption<T> name, T value) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        } else if (supportedOptions().contains(name)) {
            synchronized (this.stateLock) {
                ensureOpen();
                if (!(name == StandardSocketOptions.IP_TOS || name == StandardSocketOptions.IP_MULTICAST_TTL)) {
                    if (name != StandardSocketOptions.IP_MULTICAST_LOOP) {
                        if (name != StandardSocketOptions.IP_MULTICAST_IF) {
                            if (name == StandardSocketOptions.SO_REUSEADDR && Net.useExclusiveBind() && this.localAddress != null) {
                                this.reuseAddressEmulated = true;
                                this.isReuseAddress = value.booleanValue();
                            }
                            Net.setSocketOption(this.fd, Net.UNSPEC, name, value);
                            return this;
                        } else if (value != null) {
                            T interf = value;
                            if (this.family == StandardProtocolFamily.INET6) {
                                int index = interf.getIndex();
                                if (index != -1) {
                                    Net.setInterface6(this.fd, index);
                                } else {
                                    throw new IOException("Network interface cannot be identified");
                                }
                            } else {
                                Inet4Address target = Net.anyInet4Address(interf);
                                if (target != null) {
                                    Net.setInterface4(this.fd, Net.inet4AsInt(target));
                                } else {
                                    throw new IOException("Network interface not configured for IPv4");
                                }
                            }
                            return this;
                        } else {
                            throw new IllegalArgumentException("Cannot set IP_MULTICAST_IF to 'null'");
                        }
                    }
                }
                Net.setSocketOption(this.fd, this.family, name, value);
                return this;
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
                ensureOpen();
                if (!(name == StandardSocketOptions.IP_TOS || name == StandardSocketOptions.IP_MULTICAST_TTL)) {
                    if (name != StandardSocketOptions.IP_MULTICAST_LOOP) {
                        if (name == StandardSocketOptions.IP_MULTICAST_IF) {
                            if (this.family == StandardProtocolFamily.INET) {
                                int address = Net.getInterface4(this.fd);
                                if (address == 0) {
                                    return null;
                                }
                                T t = (T) NetworkInterface.getByInetAddress(Net.inet4FromInt(address));
                                if (t != null) {
                                    return t;
                                }
                                throw new IOException("Unable to map address to interface");
                            }
                            int index = Net.getInterface6(this.fd);
                            if (index == 0) {
                                return null;
                            }
                            T t2 = (T) NetworkInterface.getByIndex(index);
                            if (t2 != null) {
                                return t2;
                            }
                            throw new IOException("Unable to map index to interface");
                        } else if (name != StandardSocketOptions.SO_REUSEADDR || !this.reuseAddressEmulated) {
                            return (T) Net.getSocketOption(this.fd, Net.UNSPEC, name);
                        } else {
                            return (T) Boolean.valueOf(this.isReuseAddress);
                        }
                    }
                }
                return (T) Net.getSocketOption(this.fd, this.family, name);
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
            HashSet<SocketOption<?>> set = new HashSet<>(8);
            set.add(StandardSocketOptions.SO_SNDBUF);
            set.add(StandardSocketOptions.SO_RCVBUF);
            set.add(StandardSocketOptions.SO_REUSEADDR);
            set.add(StandardSocketOptions.SO_BROADCAST);
            set.add(StandardSocketOptions.IP_TOS);
            set.add(StandardSocketOptions.IP_MULTICAST_IF);
            set.add(StandardSocketOptions.IP_MULTICAST_TTL);
            set.add(StandardSocketOptions.IP_MULTICAST_LOOP);
            if (ExtendedOptionsImpl.flowSupported()) {
                set.add(ExtendedSocketOptions.SO_FLOW_SLA);
            }
            return Collections.unmodifiableSet(set);
        }
    }

    @Override // java.nio.channels.NetworkChannel
    public final Set<SocketOption<?>> supportedOptions() {
        return DefaultOptionsHolder.defaultOptions;
    }

    private void ensureOpen() throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    @Override // java.nio.channels.DatagramChannel
    public SocketAddress receive(ByteBuffer dst) throws IOException {
        int n;
        if (!dst.isReadOnly()) {
            synchronized (this.readLock) {
                ensureOpen();
                if (localAddress() == null) {
                    return null;
                }
                ByteBuffer bb = null;
                boolean z = false;
                try {
                    begin();
                    if (!isOpen()) {
                        return null;
                    }
                    SecurityManager security = System.getSecurityManager();
                    this.readerThread = NativeThread.current();
                    if (isConnected() || security == null) {
                        do {
                            n = receive(this.fd, dst);
                            if (n != -3) {
                                break;
                            }
                        } while (isOpen());
                        if (n == -2) {
                            if (bb != null) {
                                Util.releaseTemporaryDirectBuffer(bb);
                            }
                            this.readerThread = 0;
                            if (n > 0 || n == -2) {
                                z = true;
                            }
                            end(z);
                            return null;
                        }
                    } else {
                        bb = Util.getTemporaryDirectBuffer(dst.remaining());
                        while (true) {
                            n = receive(this.fd, bb);
                            if (n != -3 || !isOpen()) {
                                if (n == -2) {
                                    if (bb != null) {
                                        Util.releaseTemporaryDirectBuffer(bb);
                                    }
                                    this.readerThread = 0;
                                    if (n > 0 || n == -2) {
                                        z = true;
                                    }
                                    end(z);
                                    return null;
                                }
                                InetSocketAddress isa = (InetSocketAddress) this.sender;
                                try {
                                    security.checkAccept(isa.getAddress().getHostAddress(), isa.getPort());
                                    bb.flip();
                                    dst.put(bb);
                                    break;
                                } catch (SecurityException e) {
                                    bb.clear();
                                }
                            }
                        }
                    }
                    SocketAddress socketAddress = this.sender;
                    if (bb != null) {
                        Util.releaseTemporaryDirectBuffer(bb);
                    }
                    this.readerThread = 0;
                    if (n > 0 || n == -2) {
                        z = true;
                    }
                    end(z);
                    return socketAddress;
                } finally {
                    if (bb != null) {
                        Util.releaseTemporaryDirectBuffer(bb);
                    }
                    this.readerThread = 0;
                    if (0 > 0 || 0 == -2) {
                        z = true;
                    }
                    end(z);
                }
            }
        } else {
            throw new IllegalArgumentException("Read-only buffer");
        }
    }

    private int receive(FileDescriptor fd2, ByteBuffer dst) throws IOException {
        int pos = dst.position();
        int lim = dst.limit();
        int rem = pos <= lim ? lim - pos : 0;
        if ((dst instanceof DirectBuffer) && rem > 0) {
            return receiveIntoNativeBuffer(fd2, dst, rem, pos);
        }
        int newSize = Math.max(rem, 1);
        ByteBuffer bb = Util.getTemporaryDirectBuffer(newSize);
        try {
            BlockGuard.getThreadPolicy().onNetwork();
            int n = receiveIntoNativeBuffer(fd2, bb, newSize, 0);
            bb.flip();
            if (n > 0 && rem > 0) {
                dst.put(bb);
            }
            return n;
        } finally {
            Util.releaseTemporaryDirectBuffer(bb);
        }
    }

    private int receiveIntoNativeBuffer(FileDescriptor fd2, ByteBuffer bb, int rem, int pos) throws IOException {
        int n = receive0(fd2, ((DirectBuffer) bb).address() + ((long) pos), rem, isConnected());
        if (n > 0) {
            bb.position(pos + n);
        }
        return n;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        begin();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (isOpen() != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0056, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        r11.writerThread = sun.nio.ch.NativeThread.current();
        dalvik.system.BlockGuard.getThreadPolicy().onNetwork();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        r9 = send(r11.fd, r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006c, code lost:
        if (r9 != -3) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0072, code lost:
        if (isOpen() != false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0074, code lost:
        r9 = r11.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0076, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007b, code lost:
        if (isOpen() == false) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007f, code lost:
        if (r11.localAddress != null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0081, code lost:
        r11.localAddress = sun.nio.ch.Net.localAddress(r11.fd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0089, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008a, code lost:
        r9 = sun.nio.ch.IOStatus.normalize(r9);
        r11.writerThread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0090, code lost:
        if (r9 > 0) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0092, code lost:
        if (r9 != -2) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0095, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0096, code lost:
        end(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009a, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x009e, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x009f, code lost:
        r11.writerThread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a6, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00a7, code lost:
        end(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ab, code lost:
        throw r9;
     */
    @Override // java.nio.channels.DatagramChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int send(java.nio.ByteBuffer r12, java.net.SocketAddress r13) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 221
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.DatagramChannelImpl.send(java.nio.ByteBuffer, java.net.SocketAddress):int");
    }

    private int send(FileDescriptor fd2, ByteBuffer src, InetSocketAddress target) throws IOException {
        if (src instanceof DirectBuffer) {
            return sendFromNativeBuffer(fd2, src, target);
        }
        int pos = src.position();
        int lim = src.limit();
        ByteBuffer bb = Util.getTemporaryDirectBuffer(pos <= lim ? lim - pos : 0);
        try {
            bb.put(src);
            bb.flip();
            src.position(pos);
            int n = sendFromNativeBuffer(fd2, bb, target);
            if (n > 0) {
                src.position(pos + n);
            }
            return n;
        } finally {
            Util.releaseTemporaryDirectBuffer(bb);
        }
    }

    private int sendFromNativeBuffer(FileDescriptor fd2, ByteBuffer bb, InetSocketAddress target) throws IOException {
        int written;
        int pos = bb.position();
        int lim = bb.limit();
        boolean preferIPv6 = false;
        int rem = pos <= lim ? lim - pos : 0;
        if (this.family != StandardProtocolFamily.INET) {
            preferIPv6 = true;
        }
        try {
            written = send0(preferIPv6, fd2, ((DirectBuffer) bb).address() + ((long) pos), rem, target.getAddress(), target.getPort());
        } catch (PortUnreachableException pue) {
            if (!isConnected()) {
                written = rem;
            } else {
                throw pue;
            }
        }
        if (written > 0) {
            bb.position(pos + written);
        }
        return written;
    }

    @Override // java.nio.channels.ReadableByteChannel, java.nio.channels.DatagramChannel
    public int read(ByteBuffer buf) throws IOException {
        int n;
        if (buf != null) {
            synchronized (this.readLock) {
                synchronized (this.stateLock) {
                    ensureOpen();
                    if (!isConnected()) {
                        throw new NotYetConnectedException();
                    }
                }
                boolean z = true;
                try {
                    begin();
                    if (!isOpen()) {
                        return 0;
                    }
                    this.readerThread = NativeThread.current();
                    do {
                        n = IOUtil.read(this.fd, buf, -1, nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    int normalize = IOStatus.normalize(n);
                    this.readerThread = 0;
                    if (n <= 0 && n != -2) {
                        z = false;
                    }
                    end(z);
                    return normalize;
                } finally {
                    this.readerThread = 0;
                    if (0 <= 0 && 0 != -2) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.nio.channels.ScatteringByteChannel, java.nio.channels.DatagramChannel
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        long n;
        if (offset < 0 || length < 0 || offset > dsts.length - length) {
            throw new IndexOutOfBoundsException();
        }
        synchronized (this.readLock) {
            synchronized (this.stateLock) {
                ensureOpen();
                if (!isConnected()) {
                    throw new NotYetConnectedException();
                }
            }
            boolean z = false;
            try {
                begin();
                if (!isOpen()) {
                    return 0;
                }
                this.readerThread = NativeThread.current();
                do {
                    n = IOUtil.read(this.fd, dsts, offset, length, nd);
                    if (n != -3) {
                        break;
                    }
                } while (isOpen());
                long normalize = IOStatus.normalize(n);
                this.readerThread = 0;
                if (n > 0 || n == -2) {
                    z = true;
                }
                end(z);
                return normalize;
            } finally {
                this.readerThread = 0;
                if (0 > 0 || 0 == -2) {
                    z = true;
                }
                end(z);
            }
        }
    }

    @Override // java.nio.channels.DatagramChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer buf) throws IOException {
        int n;
        if (buf != null) {
            synchronized (this.writeLock) {
                synchronized (this.stateLock) {
                    ensureOpen();
                    if (!isConnected()) {
                        throw new NotYetConnectedException();
                    }
                }
                boolean z = true;
                try {
                    begin();
                    if (!isOpen()) {
                        return 0;
                    }
                    this.writerThread = NativeThread.current();
                    do {
                        n = IOUtil.write(this.fd, buf, -1, nd);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                    int normalize = IOStatus.normalize(n);
                    this.writerThread = 0;
                    if (n <= 0 && n != -2) {
                        z = false;
                    }
                    end(z);
                    return normalize;
                } finally {
                    this.writerThread = 0;
                    if (0 <= 0 && 0 != -2) {
                        z = false;
                    }
                    end(z);
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.nio.channels.DatagramChannel, java.nio.channels.GatheringByteChannel
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        long n;
        if (offset < 0 || length < 0 || offset > srcs.length - length) {
            throw new IndexOutOfBoundsException();
        }
        synchronized (this.writeLock) {
            synchronized (this.stateLock) {
                ensureOpen();
                if (!isConnected()) {
                    throw new NotYetConnectedException();
                }
            }
            boolean z = false;
            try {
                begin();
                if (!isOpen()) {
                    return 0;
                }
                this.writerThread = NativeThread.current();
                do {
                    n = IOUtil.write(this.fd, srcs, offset, length, nd);
                    if (n != -3) {
                        break;
                    }
                } while (isOpen());
                long normalize = IOStatus.normalize(n);
                this.writerThread = 0;
                if (n > 0 || n == -2) {
                    z = true;
                }
                end(z);
                return normalize;
            } finally {
                this.writerThread = 0;
                if (0 > 0 || 0 == -2) {
                    z = true;
                }
                end(z);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implConfigureBlocking(boolean block) throws IOException {
        IOUtil.configureBlocking(this.fd, block);
    }

    public SocketAddress localAddress() {
        InetSocketAddress inetSocketAddress;
        synchronized (this.stateLock) {
            inetSocketAddress = this.localAddress;
        }
        return inetSocketAddress;
    }

    public SocketAddress remoteAddress() {
        InetSocketAddress inetSocketAddress;
        synchronized (this.stateLock) {
            inetSocketAddress = this.remoteAddress;
        }
        return inetSocketAddress;
    }

    @Override // java.nio.channels.DatagramChannel, java.nio.channels.DatagramChannel, java.nio.channels.NetworkChannel
    public DatagramChannel bind(SocketAddress local) throws IOException {
        InetSocketAddress isa;
        synchronized (this.readLock) {
            synchronized (this.writeLock) {
                synchronized (this.stateLock) {
                    ensureOpen();
                    if (this.localAddress == null) {
                        if (local == null) {
                            isa = this.family == StandardProtocolFamily.INET ? new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0) : new InetSocketAddress(0);
                        } else {
                            isa = Net.checkAddress(local);
                            if (this.family == StandardProtocolFamily.INET) {
                                if (!(isa.getAddress() instanceof Inet4Address)) {
                                    throw new UnsupportedAddressTypeException();
                                }
                            }
                        }
                        SecurityManager sm = System.getSecurityManager();
                        if (sm != null) {
                            sm.checkListen(isa.getPort());
                        }
                        Net.bind(this.family, this.fd, isa.getAddress(), isa.getPort());
                        this.localAddress = Net.localAddress(this.fd);
                    } else {
                        throw new AlreadyBoundException();
                    }
                }
            }
        }
        return this;
    }

    @Override // java.nio.channels.DatagramChannel
    public boolean isConnected() {
        boolean z;
        synchronized (this.stateLock) {
            z = true;
            if (this.state != 1) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void ensureOpenAndUnconnected() throws IOException {
        synchronized (this.stateLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (this.state != 0) {
                throw new IllegalStateException("Connect already invoked");
            }
        }
    }

    @Override // java.nio.channels.DatagramChannel
    public DatagramChannel connect(SocketAddress sa) throws IOException {
        synchronized (this.readLock) {
            synchronized (this.writeLock) {
                synchronized (this.stateLock) {
                    ensureOpenAndUnconnected();
                    InetSocketAddress isa = Net.checkAddress(sa);
                    SecurityManager sm = System.getSecurityManager();
                    if (sm != null) {
                        sm.checkConnect(isa.getAddress().getHostAddress(), isa.getPort());
                    }
                    if (Net.connect(this.family, this.fd, isa.getAddress(), isa.getPort()) > 0) {
                        this.state = 1;
                        this.remoteAddress = isa;
                        this.sender = isa;
                        this.cachedSenderInetAddress = isa.getAddress();
                        this.cachedSenderPort = isa.getPort();
                        this.localAddress = Net.localAddress(this.fd);
                        synchronized (blockingLock()) {
                            try {
                                boolean blocking = isBlocking();
                                ByteBuffer tmpBuf = ByteBuffer.allocate(1);
                                if (blocking) {
                                    configureBlocking(false);
                                }
                                do {
                                    tmpBuf.clear();
                                } while (receive(tmpBuf) != null);
                                if (blocking) {
                                    configureBlocking(true);
                                }
                            } catch (Throwable th) {
                                if (0 != 0) {
                                    configureBlocking(true);
                                }
                                throw th;
                            }
                        }
                    } else {
                        throw new Error();
                    }
                }
            }
        }
        return this;
    }

    @Override // java.nio.channels.DatagramChannel
    public DatagramChannel disconnect() throws IOException {
        synchronized (this.readLock) {
            synchronized (this.writeLock) {
                synchronized (this.stateLock) {
                    if (isConnected()) {
                        if (isOpen()) {
                            InetSocketAddress isa = this.remoteAddress;
                            SecurityManager sm = System.getSecurityManager();
                            if (sm != null) {
                                sm.checkConnect(isa.getAddress().getHostAddress(), isa.getPort());
                            }
                            disconnect0(this.fd, this.family == StandardProtocolFamily.INET6);
                            this.remoteAddress = null;
                            this.state = 0;
                            this.localAddress = Net.localAddress(this.fd);
                            return this;
                        }
                    }
                    return this;
                }
            }
        }
    }

    private MembershipKey innerJoin(InetAddress group, NetworkInterface interf, InetAddress source) throws IOException {
        MembershipKeyImpl.Type6 key;
        byte[] sourceAddress;
        if (group.isMulticastAddress()) {
            if (group instanceof Inet4Address) {
                if (this.family == StandardProtocolFamily.INET6 && !Net.canIPv6SocketJoinIPv4Group()) {
                    throw new IllegalArgumentException("IPv6 socket cannot join IPv4 multicast group");
                }
            } else if (!(group instanceof Inet6Address)) {
                throw new IllegalArgumentException("Address type not supported");
            } else if (this.family != StandardProtocolFamily.INET6) {
                throw new IllegalArgumentException("Only IPv6 sockets can join IPv6 multicast group");
            }
            if (source != null) {
                if (source.isAnyLocalAddress()) {
                    throw new IllegalArgumentException("Source address is a wildcard address");
                } else if (source.isMulticastAddress()) {
                    throw new IllegalArgumentException("Source address is multicast address");
                } else if (source.getClass() != group.getClass()) {
                    throw new IllegalArgumentException("Source address is different type to group");
                }
            }
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkMulticast(group);
            }
            synchronized (this.stateLock) {
                try {
                    if (isOpen()) {
                        if (this.registry == null) {
                            this.registry = new MembershipRegistry();
                        } else {
                            MembershipKey key2 = this.registry.checkMembership(group, interf, source);
                            if (key2 != null) {
                                return key2;
                            }
                        }
                        if (this.family != StandardProtocolFamily.INET6 || (!(group instanceof Inet6Address) && !Net.canJoin6WithIPv4Group())) {
                            Inet4Address target = Net.anyInet4Address(interf);
                            if (target != null) {
                                int groupAddress = Net.inet4AsInt(group);
                                int targetAddress = Net.inet4AsInt(target);
                                int sourceAddress2 = source == null ? 0 : Net.inet4AsInt(source);
                                if (Net.join4(this.fd, groupAddress, targetAddress, sourceAddress2) != -2) {
                                    key = new MembershipKeyImpl.Type4(this, group, interf, source, groupAddress, targetAddress, sourceAddress2);
                                } else {
                                    throw new UnsupportedOperationException();
                                }
                            } else {
                                throw new IOException("Network interface not configured for IPv4");
                            }
                        } else {
                            int index = interf.getIndex();
                            if (index != -1) {
                                byte[] groupAddress2 = Net.inet6AsByteArray(group);
                                if (source == null) {
                                    sourceAddress = null;
                                } else {
                                    sourceAddress = Net.inet6AsByteArray(source);
                                }
                                if (Net.join6(this.fd, groupAddress2, index, sourceAddress) != -2) {
                                    key = new MembershipKeyImpl.Type6(this, group, interf, source, groupAddress2, index, sourceAddress);
                                } else {
                                    throw new UnsupportedOperationException();
                                }
                            } else {
                                throw new IOException("Network interface cannot be identified");
                            }
                        }
                        this.registry.add(key);
                        return key;
                    }
                    throw new ClosedChannelException();
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            }
        } else {
            throw new IllegalArgumentException("Group not a multicast address");
        }
    }

    @Override // java.nio.channels.MulticastChannel
    public MembershipKey join(InetAddress group, NetworkInterface interf) throws IOException {
        return innerJoin(group, interf, null);
    }

    @Override // java.nio.channels.MulticastChannel
    public MembershipKey join(InetAddress group, NetworkInterface interf, InetAddress source) throws IOException {
        if (source != null) {
            return innerJoin(group, interf, source);
        }
        throw new NullPointerException("source address is null");
    }

    /* access modifiers changed from: package-private */
    public void drop(MembershipKeyImpl key) {
        synchronized (this.stateLock) {
            if (key.isValid()) {
                try {
                    if (key instanceof MembershipKeyImpl.Type6) {
                        MembershipKeyImpl.Type6 key6 = (MembershipKeyImpl.Type6) key;
                        Net.drop6(this.fd, key6.groupAddress(), key6.index(), key6.source());
                    } else {
                        MembershipKeyImpl.Type4 key4 = (MembershipKeyImpl.Type4) key;
                        Net.drop4(this.fd, key4.groupAddress(), key4.interfaceAddress(), key4.source());
                    }
                    key.invalidate();
                    this.registry.remove(key);
                } catch (IOException ioe) {
                    throw new AssertionError(ioe);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void block(MembershipKeyImpl key, InetAddress source) throws IOException {
        int n;
        synchronized (this.stateLock) {
            if (!key.isValid()) {
                throw new IllegalStateException("key is no longer valid");
            } else if (source.isAnyLocalAddress()) {
                throw new IllegalArgumentException("Source address is a wildcard address");
            } else if (source.isMulticastAddress()) {
                throw new IllegalArgumentException("Source address is multicast address");
            } else if (source.getClass() == key.group().getClass()) {
                if (key instanceof MembershipKeyImpl.Type6) {
                    MembershipKeyImpl.Type6 key6 = (MembershipKeyImpl.Type6) key;
                    n = Net.block6(this.fd, key6.groupAddress(), key6.index(), Net.inet6AsByteArray(source));
                } else {
                    MembershipKeyImpl.Type4 key4 = (MembershipKeyImpl.Type4) key;
                    n = Net.block4(this.fd, key4.groupAddress(), key4.interfaceAddress(), Net.inet4AsInt(source));
                }
                if (n == -2) {
                    throw new UnsupportedOperationException();
                }
            } else {
                throw new IllegalArgumentException("Source address is different type to group");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unblock(MembershipKeyImpl key, InetAddress source) {
        synchronized (this.stateLock) {
            if (key.isValid()) {
                try {
                    if (key instanceof MembershipKeyImpl.Type6) {
                        MembershipKeyImpl.Type6 key6 = (MembershipKeyImpl.Type6) key;
                        Net.unblock6(this.fd, key6.groupAddress(), key6.index(), Net.inet6AsByteArray(source));
                    } else {
                        MembershipKeyImpl.Type4 key4 = (MembershipKeyImpl.Type4) key;
                        Net.unblock4(this.fd, key4.groupAddress(), key4.interfaceAddress(), Net.inet4AsInt(source));
                    }
                } catch (IOException ioe) {
                    throw new AssertionError(ioe);
                }
            } else {
                throw new IllegalStateException("key is no longer valid");
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implCloseSelectableChannel() throws IOException {
        synchronized (this.stateLock) {
            this.guard.close();
            if (this.state != 2) {
                nd.preClose(this.fd);
            }
            ResourceManager.afterUdpClose();
            if (this.registry != null) {
                this.registry.invalidateAll();
            }
            long th = this.readerThread;
            if (th != 0) {
                NativeThread.signal(th);
            }
            long th2 = this.writerThread;
            if (th2 != 0) {
                NativeThread.signal(th2);
            }
            if (!isRegistered()) {
                kill();
            }
        }
    }

    @Override // sun.nio.ch.SelChImpl
    public void kill() throws IOException {
        synchronized (this.stateLock) {
            if (this.state != 2) {
                if (this.state == -1) {
                    this.state = 2;
                    return;
                }
                nd.close(this.fd);
                this.state = 2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.guard != null) {
                this.guard.warnIfOpen();
            }
            if (this.fd != null) {
                close();
            }
        } finally {
            super.finalize();
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
        if (!((Net.POLLIN & ops) == 0 || (intOps & 1) == 0)) {
            newOps |= 1;
        }
        if (!((Net.POLLOUT & ops) == 0 || (intOps & 4) == 0)) {
            newOps |= 4;
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
        r9.readerThread = 0;
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
        r9.readerThread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int poll(int r10, long r11) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.Object r0 = r9.readLock
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
            r9.readerThread = r3     // Catch:{ all -> 0x0049 }
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
            r9.readerThread = r7
            monitor-exit(r6)
            java.io.FileDescriptor r6 = r9.fd
            int r6 = sun.nio.ch.Net.poll(r6, r10, r11)
            r1 = r6
            r9.readerThread = r3
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
            r9.readerThread = r3
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
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.DatagramChannelImpl.poll(int, long):int");
    }

    @Override // sun.nio.ch.SelChImpl
    public void translateAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        int newOps = 0;
        if ((ops & 1) != 0) {
            newOps = 0 | Net.POLLIN;
        }
        if ((ops & 4) != 0) {
            newOps |= Net.POLLOUT;
        }
        if ((ops & 8) != 0) {
            newOps |= Net.POLLIN;
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
}
