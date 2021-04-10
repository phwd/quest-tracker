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
    public abstract void socketAccept(SocketImpl socketImpl);

    /* access modifiers changed from: package-private */
    public abstract int socketAvailable();

    /* access modifiers changed from: package-private */
    public abstract void socketBind(InetAddress inetAddress, int i);

    /* access modifiers changed from: package-private */
    public abstract void socketClose0(boolean z);

    /* access modifiers changed from: package-private */
    public abstract void socketConnect(InetAddress inetAddress, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void socketCreate(boolean z);

    /* access modifiers changed from: package-private */
    public abstract Object socketGetOption(int i);

    /* access modifiers changed from: package-private */
    public abstract void socketListen(int i);

    /* access modifiers changed from: package-private */
    public abstract void socketSetOption(int i, Object obj);

    AbstractPlainSocketImpl() {
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized void create(boolean z) {
        this.stream = z;
        if (!z) {
            ResourceManager.beforeUdpCreate();
            try {
                socketCreate(false);
            } catch (IOException e) {
                ResourceManager.afterUdpClose();
                throw e;
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
    public void connect(String str, int i) {
        try {
            InetAddress byName = InetAddress.getByName(str);
            this.port = i;
            this.address = byName;
            connectToAddress(byName, i, this.timeout);
        } catch (Throwable th) {
            try {
                close();
            } catch (IOException unused) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void connect(InetAddress inetAddress, int i) {
        this.port = i;
        this.address = inetAddress;
        try {
            connectToAddress(inetAddress, i, this.timeout);
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void connect(SocketAddress socketAddress, int i) {
        if (socketAddress != null) {
            try {
                if (socketAddress instanceof InetSocketAddress) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
                    if (!inetSocketAddress.isUnresolved()) {
                        this.port = inetSocketAddress.getPort();
                        this.address = inetSocketAddress.getAddress();
                        connectToAddress(this.address, this.port, i);
                        return;
                    }
                    throw new UnknownHostException(inetSocketAddress.getHostName());
                }
            } catch (Throwable th) {
                try {
                    close();
                } catch (IOException unused) {
                }
                throw th;
            }
        }
        throw new IllegalArgumentException("unsupported address type");
    }

    private void connectToAddress(InetAddress inetAddress, int i, int i2) {
        if (inetAddress.isAnyLocalAddress()) {
            doConnect(InetAddress.getLocalHost(), i, i2);
        } else {
            doConnect(inetAddress, i, i2);
        }
    }

    @Override // java.net.SocketOptions
    public void setOption(int i, Object obj) {
        if (!isClosedOrPending()) {
            if (i == 4102) {
                this.timeout = ((Integer) obj).intValue();
            }
            socketSetOption(i, obj);
            return;
        }
        throw new SocketException("Socket Closed");
    }

    @Override // java.net.SocketOptions
    public Object getOption(int i) {
        if (!isClosedOrPending()) {
            return i == 4102 ? new Integer(this.timeout) : socketGetOption(i);
        }
        throw new SocketException("Socket Closed");
    }

    /* access modifiers changed from: package-private */
    public synchronized void doConnect(InetAddress inetAddress, int i, int i2) {
        synchronized (this.fdLock) {
            if (!this.closePending && this.socket != null) {
                this.socket.isBound();
            }
        }
        try {
            acquireFD();
            try {
                BlockGuard.getThreadPolicy().onNetwork();
                socketConnect(inetAddress, i, i2);
                synchronized (this.fdLock) {
                    if (this.closePending) {
                        throw new SocketException("Socket closed");
                    }
                }
                if (this.socket != null) {
                    this.socket.setBound();
                    this.socket.setConnected();
                }
            } finally {
                releaseFD();
            }
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized void bind(InetAddress inetAddress, int i) {
        synchronized (this.fdLock) {
            if (!this.closePending && this.socket != null) {
                this.socket.isBound();
            }
        }
        socketBind(inetAddress, i);
        if (this.socket != null) {
            this.socket.setBound();
        }
        if (this.serverSocket != null) {
            this.serverSocket.setBound();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized void listen(int i) {
        socketListen(i);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void accept(SocketImpl socketImpl) {
        acquireFD();
        try {
            BlockGuard.getThreadPolicy().onNetwork();
            socketAccept(socketImpl);
        } finally {
            releaseFD();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized InputStream getInputStream() {
        synchronized (this.fdLock) {
            if (isClosedOrPending()) {
                throw new IOException("Socket Closed");
            } else if (this.shut_rd) {
                throw new IOException("Socket input is shutdown");
            } else if (this.socketInputStream == null) {
                this.socketInputStream = new SocketInputStream(this);
            }
        }
        return this.socketInputStream;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public synchronized OutputStream getOutputStream() {
        synchronized (this.fdLock) {
            if (isClosedOrPending()) {
                throw new IOException("Socket Closed");
            } else if (this.shut_wr) {
                throw new IOException("Socket output is shutdown");
            } else if (this.socketOutputStream == null) {
                this.socketOutputStream = new SocketOutputStream(this);
            }
        }
        return this.socketOutputStream;
    }

    /* access modifiers changed from: protected */
    public synchronized int available() {
        if (!isClosedOrPending()) {
            int i = 0;
            if (isConnectionReset() || this.shut_rd) {
                return 0;
            }
            try {
                i = socketAvailable();
                if (i == 0 && isConnectionResetPending()) {
                    setConnectionReset();
                }
            } catch (ConnectionResetException unused) {
                setConnectionResetPending();
                try {
                    i = socketAvailable();
                    if (i == 0) {
                        setConnectionReset();
                    }
                } catch (ConnectionResetException unused2) {
                }
            }
            return i;
        }
        throw new IOException("Stream closed.");
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void close() {
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
    public void reset() {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null && fileDescriptor.valid()) {
            socketClose();
            this.guard.close();
        }
        super.reset();
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
                } catch (IOException unused) {
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

    private void socketPreClose() {
        socketClose0(true);
    }

    /* access modifiers changed from: protected */
    public void socketClose() {
        socketClose0(false);
    }
}
