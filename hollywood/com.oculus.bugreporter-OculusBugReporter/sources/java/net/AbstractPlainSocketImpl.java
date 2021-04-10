package java.net;

import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import sun.net.ConnectionResetException;
import sun.net.ResourceManager;

/* access modifiers changed from: package-private */
public abstract class AbstractPlainSocketImpl extends SocketImpl {
    public static final int SHUT_RD = 0;
    public static final int SHUT_WR = 1;
    private int CONNECTION_NOT_RESET = 0;
    private int CONNECTION_RESET = 2;
    private int CONNECTION_RESET_PENDING = 1;
    protected boolean closePending = false;
    protected final Object fdLock = new Object();
    protected int fdUseCount = 0;
    private final CloseGuard guard = CloseGuard.get();
    private final Object resetLock = new Object();
    private int resetState;
    private boolean shut_rd = false;
    private boolean shut_wr = false;
    private SocketInputStream socketInputStream = null;
    private SocketOutputStream socketOutputStream = null;
    protected boolean stream;
    int timeout;

    /* access modifiers changed from: package-private */
    public abstract void socketAccept(SocketImpl socketImpl) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract int socketAvailable() throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void socketBind(InetAddress inetAddress, int i) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void socketClose0(boolean z) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void socketConnect(InetAddress inetAddress, int i, int i2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void socketCreate(boolean z) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract Object socketGetOption(int i) throws SocketException;

    /* access modifiers changed from: package-private */
    public abstract void socketListen(int i) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void socketSendUrgentData(int i) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void socketSetOption(int i, Object obj) throws SocketException;

    /* access modifiers changed from: package-private */
    public abstract void socketShutdown(int i) throws IOException;

    AbstractPlainSocketImpl() {
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized void create(boolean stream2) throws IOException {
        this.stream = stream2;
        if (!stream2) {
            ResourceManager.beforeUdpCreate();
            try {
                socketCreate(false);
            } catch (IOException ioe) {
                ResourceManager.afterUdpClose();
                throw ioe;
            }
        } else {
            socketCreate(true);
        }
        if (this.socket != null) {
            this.socket.setCreated();
        }
        if (this.serverSocket != null) {
            this.serverSocket.setCreated();
        }
        if (this.fd != null && this.fd.valid()) {
            this.guard.open("close");
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void connect(String host, int port) throws UnknownHostException, IOException {
        boolean connected = false;
        try {
            InetAddress address = InetAddress.getByName(host);
            this.port = port;
            this.address = address;
            connectToAddress(address, port, this.timeout);
            connected = true;
        } finally {
            if (!connected) {
                try {
                    close();
                } catch (IOException e) {
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void connect(InetAddress address, int port) throws IOException {
        this.port = port;
        this.address = address;
        try {
            connectToAddress(address, port, this.timeout);
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void connect(SocketAddress address, int timeout2) throws IOException {
        boolean connected = false;
        if (address != null) {
            try {
                if (address instanceof InetSocketAddress) {
                    InetSocketAddress addr = (InetSocketAddress) address;
                    if (!addr.isUnresolved()) {
                        this.port = addr.getPort();
                        this.address = addr.getAddress();
                        connectToAddress(this.address, this.port, timeout2);
                        connected = true;
                        if (!connected) {
                            try {
                                return;
                            } catch (IOException e) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        throw new UnknownHostException(addr.getHostName());
                    }
                }
            } finally {
                if (!connected) {
                    try {
                        close();
                    } catch (IOException e2) {
                    }
                }
            }
        }
        throw new IllegalArgumentException("unsupported address type");
    }

    private void connectToAddress(InetAddress address, int port, int timeout2) throws IOException {
        if (address.isAnyLocalAddress()) {
            doConnect(InetAddress.getLocalHost(), port, timeout2);
        } else {
            doConnect(address, port, timeout2);
        }
    }

    @Override // java.net.SocketOptions
    public void setOption(int opt, Object val) throws SocketException {
        if (!isClosedOrPending()) {
            if (opt == 4102) {
                this.timeout = ((Integer) val).intValue();
            }
            socketSetOption(opt, val);
            return;
        }
        throw new SocketException("Socket Closed");
    }

    @Override // java.net.SocketOptions
    public Object getOption(int opt) throws SocketException {
        if (!isClosedOrPending()) {
            return opt == 4102 ? new Integer(this.timeout) : socketGetOption(opt);
        }
        throw new SocketException("Socket Closed");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        acquireFD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        dalvik.system.BlockGuard.getThreadPolicy().onNetwork();
        socketConnect(r4, r5, r6);
        r0 = r3.fdLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002f, code lost:
        if (r3.closePending != false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0034, code lost:
        if (r3.socket == null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0036, code lost:
        r3.socket.setBound();
        r3.socket.setConnected();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0040, code lost:
        releaseFD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0047, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0049, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        throw new java.net.SocketException("Socket closed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0053, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0054, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0055, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0056, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0057, code lost:
        releaseFD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x005c, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x005f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void doConnect(java.net.InetAddress r4, int r5, int r6) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.AbstractPlainSocketImpl.doConnect(java.net.InetAddress, int, int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        socketBind(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        if (r2.socket == null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r2.socket.setBound();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        if (r2.serverSocket == null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        r2.serverSocket.setBound();
     */
    @Override // java.net.SocketImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void bind(java.net.InetAddress r3, int r4) throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.Object r0 = r2.fdLock     // Catch:{ all -> 0x0037 }
            monitor-enter(r0)     // Catch:{ all -> 0x0037 }
            boolean r1 = r2.closePending     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x001c
            java.net.Socket r1 = r2.socket     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0014
            java.net.Socket r1 = r2.socket     // Catch:{ all -> 0x001a }
            boolean r1 = r1.isBound()     // Catch:{ all -> 0x001a }
            if (r1 != 0) goto L_0x001c
        L_0x0014:
            java.io.FileDescriptor r1 = r2.fd     // Catch:{ all -> 0x001a }
            sun.net.NetHooks.beforeTcpBind(r1, r3, r4)     // Catch:{ all -> 0x001a }
            goto L_0x001c
        L_0x001a:
            r1 = move-exception
            goto L_0x0035
        L_0x001c:
            monitor-exit(r0)
            r2.socketBind(r3, r4)
            java.net.Socket r0 = r2.socket
            if (r0 == 0) goto L_0x0029
            java.net.Socket r0 = r2.socket
            r0.setBound()
        L_0x0029:
            java.net.ServerSocket r0 = r2.serverSocket
            if (r0 == 0) goto L_0x0032
            java.net.ServerSocket r0 = r2.serverSocket
            r0.setBound()
        L_0x0032:
            monitor-exit(r2)
            return
        L_0x0034:
            r1 = move-exception
        L_0x0035:
            monitor-exit(r0)
            throw r1
        L_0x0037:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.AbstractPlainSocketImpl.bind(java.net.InetAddress, int):void");
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized void listen(int count) throws IOException {
        socketListen(count);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void accept(SocketImpl s) throws IOException {
        acquireFD();
        try {
            BlockGuard.getThreadPolicy().onNetwork();
            socketAccept(s);
        } finally {
            releaseFD();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized InputStream getInputStream() throws IOException {
        Throwable th;
        synchronized (this.fdLock) {
            try {
                if (isClosedOrPending()) {
                    throw new IOException("Socket Closed");
                } else if (!this.shut_rd) {
                    try {
                        if (this.socketInputStream == null) {
                            this.socketInputStream = new SocketInputStream(this);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else {
                    throw new IOException("Socket input is shutdown");
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
        return this.socketInputStream;
    }

    /* access modifiers changed from: package-private */
    public void setInputStream(SocketInputStream in) {
        this.socketInputStream = in;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized OutputStream getOutputStream() throws IOException {
        Throwable th;
        synchronized (this.fdLock) {
            try {
                if (isClosedOrPending()) {
                    throw new IOException("Socket Closed");
                } else if (!this.shut_wr) {
                    try {
                        if (this.socketOutputStream == null) {
                            this.socketOutputStream = new SocketOutputStream(this);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else {
                    throw new IOException("Socket output is shutdown");
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
        return this.socketOutputStream;
    }

    /* access modifiers changed from: package-private */
    public void setAddress(InetAddress address) {
        this.address = address;
    }

    /* access modifiers changed from: package-private */
    public void setPort(int port) {
        this.port = port;
    }

    /* access modifiers changed from: package-private */
    public void setLocalPort(int localport) {
        this.localport = localport;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized int available() throws IOException {
        if (isClosedOrPending()) {
            throw new IOException("Stream closed.");
        } else if (isConnectionReset() || this.shut_rd) {
            return 0;
        } else {
            int n = 0;
            try {
                n = socketAvailable();
                if (n == 0 && isConnectionResetPending()) {
                    setConnectionReset();
                }
            } catch (ConnectionResetException e) {
                setConnectionResetPending();
                try {
                    n = socketAvailable();
                    if (n == 0) {
                        setConnectionReset();
                    }
                } catch (ConnectionResetException e2) {
                }
            }
            return n;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void close() throws IOException {
        synchronized (this.fdLock) {
            if (this.fd != null && this.fd.valid()) {
                if (!this.stream) {
                    ResourceManager.afterUdpClose();
                }
                if (!this.closePending) {
                    this.closePending = true;
                    this.guard.close();
                    if (this.fdUseCount == 0) {
                        try {
                            socketPreClose();
                        } finally {
                            socketClose();
                        }
                    } else {
                        this.fdUseCount--;
                        socketPreClose();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.SocketImpl
    public void reset() throws IOException {
        if (this.fd != null && this.fd.valid()) {
            socketClose();
            this.guard.close();
        }
        super.reset();
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void shutdownInput() throws IOException {
        if (this.fd != null && this.fd.valid()) {
            socketShutdown(0);
            SocketInputStream socketInputStream2 = this.socketInputStream;
            if (socketInputStream2 != null) {
                socketInputStream2.setEOF(true);
            }
            this.shut_rd = true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void shutdownOutput() throws IOException {
        if (this.fd != null && this.fd.valid()) {
            socketShutdown(1);
            this.shut_wr = true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public boolean supportsUrgentData() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void sendUrgentData(int data) throws IOException {
        if (this.fd == null || !this.fd.valid()) {
            throw new IOException("Socket Closed");
        }
        socketSendUrgentData(data);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws IOException {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
    }

    /* access modifiers changed from: package-private */
    public FileDescriptor acquireFD() {
        FileDescriptor fileDescriptor;
        synchronized (this.fdLock) {
            this.fdUseCount++;
            fileDescriptor = this.fd;
        }
        return fileDescriptor;
    }

    /* access modifiers changed from: package-private */
    public void releaseFD() {
        synchronized (this.fdLock) {
            this.fdUseCount--;
            if (this.fdUseCount == -1 && this.fd != null) {
                try {
                    socketClose();
                } catch (IOException e) {
                }
            }
        }
    }

    public boolean isConnectionReset() {
        boolean z;
        synchronized (this.resetLock) {
            z = this.resetState == this.CONNECTION_RESET;
        }
        return z;
    }

    public boolean isConnectionResetPending() {
        boolean z;
        synchronized (this.resetLock) {
            z = this.resetState == this.CONNECTION_RESET_PENDING;
        }
        return z;
    }

    public void setConnectionReset() {
        synchronized (this.resetLock) {
            this.resetState = this.CONNECTION_RESET;
        }
    }

    public void setConnectionResetPending() {
        synchronized (this.resetLock) {
            if (this.resetState == this.CONNECTION_NOT_RESET) {
                this.resetState = this.CONNECTION_RESET_PENDING;
            }
        }
    }

    public boolean isClosedOrPending() {
        synchronized (this.fdLock) {
            if (!this.closePending && this.fd != null) {
                if (this.fd.valid()) {
                    return false;
                }
            }
            return true;
        }
    }

    public int getTimeout() {
        return this.timeout;
    }

    private void socketPreClose() throws IOException {
        socketClose0(true);
    }

    /* access modifiers changed from: protected */
    public void socketClose() throws IOException {
        socketClose0(false);
    }
}
