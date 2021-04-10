package sun.nio.ch;

import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jdk.net.ExtendedSocketOptions;
import sun.net.ExtendedOptionsImpl;
import sun.net.NetHooks;

/* access modifiers changed from: package-private */
public class SocketChannelImpl extends SocketChannel implements SelChImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ST_CONNECTED = 2;
    private static final int ST_KILLED = 4;
    private static final int ST_KILLPENDING = 3;
    private static final int ST_PENDING = 1;
    private static final int ST_UNCONNECTED = 0;
    private static final int ST_UNINITIALIZED = -1;
    private static NativeDispatcher nd = new SocketDispatcher();
    private final FileDescriptor fd;
    private final int fdVal;
    private final CloseGuard guard;
    private boolean isInputOpen;
    private boolean isOutputOpen;
    private boolean isReuseAddress;
    private InetSocketAddress localAddress;
    private final Object readLock;
    private volatile long readerThread;
    private boolean readyToConnect;
    private InetSocketAddress remoteAddress;
    private Socket socket;
    private int state;
    private final Object stateLock;
    private final Object writeLock;
    private volatile long writerThread;

    private static native int checkConnect(FileDescriptor fileDescriptor, boolean z, boolean z2) throws IOException;

    private static native int sendOutOfBandData(FileDescriptor fileDescriptor, byte b) throws IOException;

    SocketChannelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        this.readerThread = 0;
        this.writerThread = 0;
        this.readLock = new Object();
        this.writeLock = new Object();
        this.stateLock = new Object();
        this.state = -1;
        this.isInputOpen = true;
        this.isOutputOpen = true;
        this.readyToConnect = false;
        this.guard = CloseGuard.get();
        this.fd = Net.socket(true);
        this.fdVal = IOUtil.fdVal(this.fd);
        this.state = 0;
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null && fileDescriptor.valid()) {
            this.guard.open("close");
        }
    }

    SocketChannelImpl(SelectorProvider sp, FileDescriptor fd2, boolean bound) throws IOException {
        super(sp);
        this.readerThread = 0;
        this.writerThread = 0;
        this.readLock = new Object();
        this.writeLock = new Object();
        this.stateLock = new Object();
        this.state = -1;
        this.isInputOpen = true;
        this.isOutputOpen = true;
        this.readyToConnect = false;
        this.guard = CloseGuard.get();
        this.fd = fd2;
        this.fdVal = IOUtil.fdVal(fd2);
        this.state = 0;
        if (fd2 != null && fd2.valid()) {
            this.guard.open("close");
        }
        if (bound) {
            this.localAddress = Net.localAddress(fd2);
        }
    }

    SocketChannelImpl(SelectorProvider sp, FileDescriptor fd2, InetSocketAddress remote) throws IOException {
        super(sp);
        this.readerThread = 0;
        this.writerThread = 0;
        this.readLock = new Object();
        this.writeLock = new Object();
        this.stateLock = new Object();
        this.state = -1;
        this.isInputOpen = true;
        this.isOutputOpen = true;
        this.readyToConnect = false;
        this.guard = CloseGuard.get();
        this.fd = fd2;
        this.fdVal = IOUtil.fdVal(fd2);
        this.state = 2;
        this.localAddress = Net.localAddress(fd2);
        this.remoteAddress = remote;
        if (fd2 != null && fd2.valid()) {
            this.guard.open("close");
        }
    }

    @Override // java.nio.channels.SocketChannel
    public Socket socket() {
        Socket socket2;
        synchronized (this.stateLock) {
            if (this.socket == null) {
                this.socket = SocketAdaptor.create(this);
            }
            socket2 = this.socket;
        }
        return socket2;
    }

    @Override // java.nio.channels.SocketChannel, java.nio.channels.NetworkChannel
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

    @Override // java.nio.channels.SocketChannel
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

    @Override // java.nio.channels.SocketChannel, java.nio.channels.SocketChannel, java.nio.channels.NetworkChannel
    public <T> SocketChannel setOption(SocketOption<T> name, T value) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        } else if (supportedOptions().contains(name)) {
            synchronized (this.stateLock) {
                if (!isOpen()) {
                    throw new ClosedChannelException();
                } else if (name == StandardSocketOptions.IP_TOS) {
                    Net.setSocketOption(this.fd, Net.isIPv6Available() ? StandardProtocolFamily.INET6 : StandardProtocolFamily.INET, name, value);
                    return this;
                } else if (name != StandardSocketOptions.SO_REUSEADDR || !Net.useExclusiveBind()) {
                    Net.setSocketOption(this.fd, Net.UNSPEC, name, value);
                    return this;
                } else {
                    this.isReuseAddress = value.booleanValue();
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
                } else if (name == StandardSocketOptions.SO_REUSEADDR && Net.useExclusiveBind()) {
                    return (T) Boolean.valueOf(this.isReuseAddress);
                } else if (name == StandardSocketOptions.IP_TOS) {
                    return (T) Net.getSocketOption(this.fd, Net.isIPv6Available() ? StandardProtocolFamily.INET6 : StandardProtocolFamily.INET, name);
                } else {
                    return (T) Net.getSocketOption(this.fd, Net.UNSPEC, name);
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
            HashSet<SocketOption<?>> set = new HashSet<>(8);
            set.add(StandardSocketOptions.SO_SNDBUF);
            set.add(StandardSocketOptions.SO_RCVBUF);
            set.add(StandardSocketOptions.SO_KEEPALIVE);
            set.add(StandardSocketOptions.SO_REUSEADDR);
            set.add(StandardSocketOptions.SO_LINGER);
            set.add(StandardSocketOptions.TCP_NODELAY);
            set.add(StandardSocketOptions.IP_TOS);
            set.add(ExtendedSocketOption.SO_OOBINLINE);
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

    private boolean ensureReadOpen() throws ClosedChannelException {
        synchronized (this.stateLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (!isConnected()) {
                throw new NotYetConnectedException();
            } else if (!this.isInputOpen) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void ensureWriteOpen() throws ClosedChannelException {
        synchronized (this.stateLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (!this.isOutputOpen) {
                throw new ClosedChannelException();
            } else if (!isConnected()) {
                throw new NotYetConnectedException();
            }
        }
    }

    private void readerCleanup() throws IOException {
        synchronized (this.stateLock) {
            this.readerThread = 0;
            if (this.state == 3) {
                kill();
            }
        }
    }

    private void writerCleanup() throws IOException {
        synchronized (this.stateLock) {
            this.writerThread = 0;
            if (this.state == 3) {
                kill();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001f, code lost:
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0022, code lost:
        if (0 > 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0024, code lost:
        if (0 != -2) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        end(r4);
        r3 = r10.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002d, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002e, code lost:
        if (0 > 0) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0032, code lost:
        if (r10.isInputOpen != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0034, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0036, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0037, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0039, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003a, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0044, code lost:
        r6 = sun.nio.ch.IOUtil.read(r10.fd, r11, -1, sun.nio.ch.SocketChannelImpl.nd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0050, code lost:
        if (r6 != -3) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0056, code lost:
        if (isOpen() == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0059, code lost:
        r6 = sun.nio.ch.IOStatus.normalize(r6);
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0060, code lost:
        if (r6 > 0) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0062, code lost:
        if (r6 != -2) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0065, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0066, code lost:
        end(r4);
        r3 = r10.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006b, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x006c, code lost:
        if (r6 > 0) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0070, code lost:
        if (r10.isInputOpen != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0072, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0074, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0075, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0077, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0078, code lost:
        r2 = th;
     */
    @Override // java.nio.channels.ReadableByteChannel, java.nio.channels.SocketChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(java.nio.ByteBuffer r11) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 166
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.read(java.nio.ByteBuffer):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        if (0 > 0) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        if (0 != -2) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        end(r13);
        r10 = r16.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0046, code lost:
        if (0 > 0) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004a, code lost:
        if (r16.isInputOpen != false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004c, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004e, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004f, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0051, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0052, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x005c, code lost:
        r6 = sun.nio.ch.IOUtil.read(r16.fd, r17, r18, r19, sun.nio.ch.SocketChannelImpl.nd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0069, code lost:
        if (r6 != -3) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006f, code lost:
        if (isOpen() == false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0072, code lost:
        r6 = sun.nio.ch.IOStatus.normalize(r6);
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x007b, code lost:
        if (r6 > 0) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x007f, code lost:
        if (r6 != -2) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0082, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0083, code lost:
        end(r13);
        r10 = r16.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0088, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x008b, code lost:
        if (r6 > 0) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x008f, code lost:
        if (r16.isInputOpen != false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0091, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0093, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0096, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0098, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0099, code lost:
        r0 = th;
     */
    @Override // java.nio.channels.ScatteringByteChannel, java.nio.channels.SocketChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long read(java.nio.ByteBuffer[] r17, int r18, int r19) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.read(java.nio.ByteBuffer[], int, int):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        writerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        if (0 > 0) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
        if (0 != -2) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0022, code lost:
        end(r3);
        r2 = r9.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0027, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0028, code lost:
        if (0 > 0) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002c, code lost:
        if (r9.isOutputOpen == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0034, code lost:
        throw new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0035, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0037, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0038, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0042, code lost:
        r5 = sun.nio.ch.IOUtil.write(r9.fd, r10, -1, sun.nio.ch.SocketChannelImpl.nd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004e, code lost:
        if (r5 != -3) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0054, code lost:
        if (isOpen() == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0057, code lost:
        r5 = sun.nio.ch.IOStatus.normalize(r5);
        writerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005e, code lost:
        if (r5 > 0) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0060, code lost:
        if (r5 != -2) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0063, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0064, code lost:
        end(r3);
        r2 = r9.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0069, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x006a, code lost:
        if (r5 > 0) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x006e, code lost:
        if (r9.isOutputOpen == false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0076, code lost:
        throw new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0077, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0079, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x007a, code lost:
        r3 = th;
     */
    @Override // java.nio.channels.SocketChannel, java.nio.channels.WritableByteChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int write(java.nio.ByteBuffer r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 172
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.write(java.nio.ByteBuffer):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        writerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        if (0 > 0) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        if (0 != -2) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002e, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002f, code lost:
        end(r3);
        r3 = r12.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0037, code lost:
        if (0 > 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003b, code lost:
        if (r12.isOutputOpen == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0043, code lost:
        throw new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0044, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0046, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0047, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0051, code lost:
        r9 = sun.nio.ch.IOUtil.write(r12.fd, r13, r14, r15, sun.nio.ch.SocketChannelImpl.nd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x005e, code lost:
        if (r9 != -3) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0064, code lost:
        if (isOpen() == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0067, code lost:
        r9 = sun.nio.ch.IOStatus.normalize(r9);
        writerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0070, code lost:
        if (r9 > 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0074, code lost:
        if (r9 != -2) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0076, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0077, code lost:
        end(r3);
        r3 = r12.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x007c, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x007f, code lost:
        if (r9 > 0) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0083, code lost:
        if (r12.isOutputOpen == false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x008b, code lost:
        throw new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x008c, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x008e, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x008f, code lost:
        r4 = th;
     */
    @Override // java.nio.channels.SocketChannel, java.nio.channels.GatheringByteChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long write(java.nio.ByteBuffer[] r13, int r14, int r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.write(java.nio.ByteBuffer[], int, int):long");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        writerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        if (0 > 0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        if (0 != -2) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        end(r3);
        r2 = r8.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        if (0 > 0) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002a, code lost:
        if (r8.isOutputOpen == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0032, code lost:
        throw new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0033, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0035, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0036, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0040, code lost:
        r5 = sendOutOfBandData(r8.fd, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0048, code lost:
        if (r5 != -3) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x004e, code lost:
        if (isOpen() == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0051, code lost:
        r5 = sun.nio.ch.IOStatus.normalize(r5);
        writerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0058, code lost:
        if (r5 > 0) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005a, code lost:
        if (r5 != -2) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x005d, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005e, code lost:
        end(r3);
        r2 = r8.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0063, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0064, code lost:
        if (r5 > 0) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0068, code lost:
        if (r8.isOutputOpen == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0070, code lost:
        throw new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0071, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0073, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0074, code lost:
        r3 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int sendOutOfBandData(byte r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.sendOutOfBandData(byte):int");
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implConfigureBlocking(boolean block) throws IOException {
        IOUtil.configureBlocking(this.fd, block);
    }

    public InetSocketAddress localAddress() {
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

    @Override // java.nio.channels.SocketChannel, java.nio.channels.SocketChannel, java.nio.channels.NetworkChannel
    public SocketChannel bind(SocketAddress local) throws IOException {
        synchronized (this.readLock) {
            synchronized (this.writeLock) {
                synchronized (this.stateLock) {
                    if (!isOpen()) {
                        throw new ClosedChannelException();
                    } else if (this.state == 1) {
                        throw new ConnectionPendingException();
                    } else if (this.localAddress == null) {
                        InetSocketAddress isa = local == null ? new InetSocketAddress(0) : Net.checkAddress(local);
                        SecurityManager sm = System.getSecurityManager();
                        if (sm != null) {
                            sm.checkListen(isa.getPort());
                        }
                        NetHooks.beforeTcpBind(this.fd, isa.getAddress(), isa.getPort());
                        Net.bind(this.fd, isa.getAddress(), isa.getPort());
                        this.localAddress = Net.localAddress(this.fd);
                    } else {
                        throw new AlreadyBoundException();
                    }
                }
            }
        }
        return this;
    }

    @Override // java.nio.channels.SocketChannel
    public boolean isConnected() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.state == 2;
        }
        return z;
    }

    @Override // java.nio.channels.SocketChannel
    public boolean isConnectionPending() {
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
            } else if (this.state == 2) {
                throw new AlreadyConnectedException();
            } else if (this.state == 1) {
                throw new ConnectionPendingException();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
        if (0 > 0) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        if (0 != -2) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0042, code lost:
        end(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0048, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0061, code lost:
        r10 = r3.getAddress();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0069, code lost:
        if (r10.isAnyLocalAddress() == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006b, code lost:
        r10 = java.net.InetAddress.getLocalHost();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0070, code lost:
        r11 = sun.nio.ch.Net.connect(r14.fd, r10, r3.getPort());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007c, code lost:
        if (r11 != -3) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0082, code lost:
        if (isOpen() == false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0085, code lost:
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0088, code lost:
        if (r11 > 0) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008a, code lost:
        if (r11 != -2) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008d, code lost:
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008f, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0090, code lost:
        end(r7);
        r7 = r14.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0097, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r14.remoteAddress = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009a, code lost:
        if (r11 <= 0) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x009c, code lost:
        r14.state = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a3, code lost:
        if (isOpen() == false) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a5, code lost:
        r14.localAddress = sun.nio.ch.Net.localAddress(r14.fd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ad, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00b1, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00b6, code lost:
        if (isBlocking() != false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00b8, code lost:
        r14.state = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00be, code lost:
        if (isOpen() == false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c0, code lost:
        r14.localAddress = sun.nio.ch.Net.localAddress(r14.fd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c8, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00cc, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00e3, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00e4, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00e8, code lost:
        throw r7;
     */
    @Override // java.nio.channels.SocketChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean connect(java.net.SocketAddress r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 241
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.connect(java.net.SocketAddress):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x00d4, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x00d7, code lost:
        monitor-enter(r14.stateLock);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r14.readerThread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x00dc, code lost:
        if (r14.state == 3) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x00de, code lost:
        kill();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x00e8, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x00e9, code lost:
        end(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x00ed, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x00ee, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x00f2, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x00f3, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x00f7, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001e, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        begin();
        r10 = blockingLock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002b, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r11 = r14.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002e, code lost:
        monitor-enter(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0033, code lost:
        if (isOpen() != false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0035, code lost:
        monitor-exit(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0036, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r4 = r14.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0039, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r14.readerThread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x003e, code lost:
        if (r14.state != 3) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0040, code lost:
        kill();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0044, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0045, code lost:
        if (r2 > 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0047, code lost:
        if (r2 != -2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x004a, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x004b, code lost:
        end(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0050, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0051, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0053, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0054, code lost:
        r14.readerThread = sun.nio.ch.NativeThread.current();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x005a, code lost:
        monitor-exit(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x005b, code lost:
        dalvik.system.BlockGuard.getThreadPolicy().onNetwork();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0067, code lost:
        if (isBlocking() != false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0069, code lost:
        r2 = checkConnect(r14.fd, false, r14.readyToConnect);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0072, code lost:
        if (r2 != -3) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0078, code lost:
        if (isOpen() == false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x007b, code lost:
        r2 = checkConnect(r14.fd, true, r14.readyToConnect);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0084, code lost:
        if (r2 != 0) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0087, code lost:
        if (r2 != -3) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x008d, code lost:
        if (isOpen() == false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0090, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0091, code lost:
        r10 = r14.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0093, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r14.readerThread = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0098, code lost:
        if (r14.state != 3) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x009a, code lost:
        kill();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x009e, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x009f, code lost:
        if (r2 > 0) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00a1, code lost:
        if (r2 != -2) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00a4, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00a6, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00a7, code lost:
        end(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00ac, code lost:
        if (r2 <= 0) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00ae, code lost:
        r3 = r14.stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00b0, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        r14.state = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00b7, code lost:
        if (isOpen() == false) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00b9, code lost:
        r14.localAddress = sun.nio.ch.Net.localAddress(r14.fd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00c1, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00c4, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x00ca, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x00cb, code lost:
        r3 = th;
     */
    @Override // java.nio.channels.SocketChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean finishConnect() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.finishConnect():boolean");
    }

    @Override // java.nio.channels.SocketChannel
    public SocketChannel shutdownInput() throws IOException {
        synchronized (this.stateLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (!isConnected()) {
                throw new NotYetConnectedException();
            } else if (this.isInputOpen) {
                Net.shutdown(this.fd, 0);
                if (this.readerThread != 0) {
                    NativeThread.signal(this.readerThread);
                }
                this.isInputOpen = false;
            }
        }
        return this;
    }

    @Override // java.nio.channels.SocketChannel
    public SocketChannel shutdownOutput() throws IOException {
        synchronized (this.stateLock) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            } else if (!isConnected()) {
                throw new NotYetConnectedException();
            } else if (this.isOutputOpen) {
                Net.shutdown(this.fd, 1);
                if (this.writerThread != 0) {
                    NativeThread.signal(this.writerThread);
                }
                this.isOutputOpen = false;
            }
        }
        return this;
    }

    public boolean isInputOpen() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.isInputOpen;
        }
        return z;
    }

    public boolean isOutputOpen() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.isOutputOpen;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @Override // java.nio.channels.spi.AbstractSelectableChannel
    public void implCloseSelectableChannel() throws IOException {
        synchronized (this.stateLock) {
            this.isInputOpen = false;
            this.isOutputOpen = false;
            if (this.state != 4) {
                this.guard.close();
                nd.preClose(this.fd);
            }
            if (this.readerThread != 0) {
                NativeThread.signal(this.readerThread);
            }
            if (this.writerThread != 0) {
                NativeThread.signal(this.writerThread);
            }
            if (!isRegistered()) {
                kill();
            }
        }
    }

    @Override // sun.nio.ch.SelChImpl
    public void kill() throws IOException {
        synchronized (this.stateLock) {
            if (this.state != 4) {
                if (this.state == -1) {
                    this.state = 4;
                    return;
                }
                if (this.readerThread == 0 && this.writerThread == 0) {
                    nd.close(this.fd);
                    this.state = 4;
                } else {
                    this.state = 3;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws IOException {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        if (this.fd != null) {
            close();
        }
    }

    public boolean translateReadyOps(int ops, int initialOps, SelectionKeyImpl sk) {
        int i;
        int intOps = sk.nioInterestOps();
        int oldOps = sk.nioReadyOps();
        int newOps = initialOps;
        if ((Net.POLLNVAL & ops) != 0) {
            return false;
        }
        if (((Net.POLLERR | Net.POLLHUP) & ops) != 0) {
            sk.nioReadyOps(intOps);
            this.readyToConnect = true;
            if (((~oldOps) & intOps) != 0) {
                return true;
            }
            return false;
        }
        if (!((Net.POLLIN & ops) == 0 || (intOps & 1) == 0 || this.state != 2)) {
            newOps |= 1;
        }
        if (!((Net.POLLCONN & ops) == 0 || (intOps & 8) == 0 || ((i = this.state) != 0 && i != 1))) {
            newOps |= 8;
            this.readyToConnect = true;
        }
        if (!((Net.POLLOUT & ops) == 0 || (intOps & 4) == 0 || this.state != 2)) {
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
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r1 <= 0) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        end(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0027, code lost:
        r4 = sun.nio.ch.Net.poll(r7.fd, r8, r9);
        readerCleanup();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int poll(int r8, long r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.Object r0 = r7.readLock
            monitor-enter(r0)
            r1 = 0
            r2 = 1
            r3 = 0
            r7.begin()     // Catch:{ all -> 0x003e }
            java.lang.Object r4 = r7.stateLock     // Catch:{ all -> 0x003e }
            monitor-enter(r4)     // Catch:{ all -> 0x003e }
            boolean r5 = r7.isOpen()     // Catch:{ all -> 0x003b }
            if (r5 != 0) goto L_0x0020
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            r7.readerCleanup()     // Catch:{ all -> 0x004a }
            if (r1 <= 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r2 = r3
        L_0x001b:
            r7.end(r2)     // Catch:{ all -> 0x004a }
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r3
        L_0x0020:
            long r5 = sun.nio.ch.NativeThread.current()
            r7.readerThread = r5
            monitor-exit(r4)
            java.io.FileDescriptor r4 = r7.fd
            int r4 = sun.nio.ch.Net.poll(r4, r8, r9)
            r1 = r4
            r7.readerCleanup()
            if (r1 <= 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r2 = r3
        L_0x0035:
            r7.end(r2)
            monitor-exit(r0)
            return r1
        L_0x003b:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x003e:
            r4 = move-exception
            r7.readerCleanup()
            if (r1 <= 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r2 = r3
        L_0x0046:
            r7.end(r2)
            throw r4
        L_0x004a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SocketChannelImpl.poll(int, long):int");
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
            newOps |= Net.POLLCONN;
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
        sb.append(getClass().getSuperclass().getName());
        sb.append('[');
        if (!isOpen()) {
            sb.append("closed");
        } else {
            synchronized (this.stateLock) {
                int i = this.state;
                if (i == 0) {
                    sb.append("unconnected");
                } else if (i == 1) {
                    sb.append("connection-pending");
                } else if (i == 2) {
                    sb.append("connected");
                    if (!this.isInputOpen) {
                        sb.append(" ishut");
                    }
                    if (!this.isOutputOpen) {
                        sb.append(" oshut");
                    }
                }
                InetSocketAddress addr = localAddress();
                if (addr != null) {
                    sb.append(" local=");
                    sb.append(Net.getRevealedLocalAddressAsString(addr));
                }
                if (remoteAddress() != null) {
                    sb.append(" remote=");
                    sb.append(remoteAddress().toString());
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
