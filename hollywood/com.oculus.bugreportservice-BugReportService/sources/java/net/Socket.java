package java.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.net.ApplicationProxy;

public class Socket implements Closeable {
    private static SocketImplFactory factory;
    private boolean bound = false;
    private Object closeLock = new Object();
    private boolean closed = false;
    private boolean connected = false;
    private boolean created = false;
    SocketImpl impl;
    private boolean oldImpl = false;
    private boolean shutIn = false;
    private boolean shutOut = false;

    public Socket() {
        setImpl();
    }

    public Socket(Proxy proxy) {
        if (proxy != null) {
            Proxy proxy2 = Proxy.NO_PROXY;
            proxy2 = proxy != proxy2 ? ApplicationProxy.create(proxy) : proxy2;
            if (proxy2.type() == Proxy.Type.SOCKS) {
                SecurityManager securityManager = System.getSecurityManager();
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy2.address();
                if (inetSocketAddress.getAddress() != null) {
                    checkAddress(inetSocketAddress.getAddress(), "Socket");
                }
                if (securityManager != null) {
                    InetSocketAddress inetSocketAddress2 = inetSocketAddress.isUnresolved() ? new InetSocketAddress(inetSocketAddress.getHostName(), inetSocketAddress.getPort()) : inetSocketAddress;
                    if (inetSocketAddress2.isUnresolved()) {
                        securityManager.checkConnect(inetSocketAddress2.getHostName(), inetSocketAddress2.getPort());
                        throw null;
                    } else {
                        securityManager.checkConnect(inetSocketAddress2.getAddress().getHostAddress(), inetSocketAddress2.getPort());
                        throw null;
                    }
                } else {
                    this.impl = new SocksSocketImpl(proxy2);
                    this.impl.setSocket(this);
                }
            } else if (proxy2 != Proxy.NO_PROXY) {
                throw new IllegalArgumentException("Invalid Proxy");
            } else if (factory == null) {
                this.impl = new PlainSocketImpl();
                this.impl.setSocket(this);
            } else {
                setImpl();
            }
        } else {
            throw new IllegalArgumentException("Invalid Proxy");
        }
    }

    protected Socket(SocketImpl socketImpl) {
        this.impl = socketImpl;
        if (socketImpl != null) {
            checkOldImpl();
            this.impl.setSocket(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void createImpl(boolean z) {
        if (this.impl == null) {
            setImpl();
        }
        try {
            this.impl.create(z);
            this.created = true;
        } catch (IOException e) {
            throw new SocketException(e.getMessage());
        }
    }

    private void checkOldImpl() {
        if (this.impl != null) {
            this.oldImpl = ((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.net.Socket.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Boolean run() {
                    Class cls = Socket.this.impl.getClass();
                    do {
                        try {
                            cls.getDeclaredMethod("connect", SocketAddress.class, Integer.TYPE);
                            return Boolean.FALSE;
                        } catch (NoSuchMethodException unused) {
                            cls = cls.getSuperclass();
                            if (cls.equals(SocketImpl.class)) {
                                return Boolean.TRUE;
                            }
                        }
                    } while (cls.equals(SocketImpl.class));
                    return Boolean.TRUE;
                }
            })).booleanValue();
        }
    }

    /* access modifiers changed from: package-private */
    public void setImpl() {
        SocketImplFactory socketImplFactory = factory;
        if (socketImplFactory != null) {
            this.impl = socketImplFactory.createSocketImpl();
            checkOldImpl();
        } else {
            this.impl = new SocksSocketImpl();
        }
        SocketImpl socketImpl = this.impl;
        if (socketImpl != null) {
            socketImpl.setSocket(this);
        }
    }

    /* access modifiers changed from: package-private */
    public SocketImpl getImpl() {
        if (!this.created) {
            createImpl(true);
        }
        return this.impl;
    }

    public void connect(SocketAddress socketAddress) {
        connect(socketAddress, 0);
    }

    public void connect(SocketAddress socketAddress, int i) {
        if (socketAddress == null) {
            throw new IllegalArgumentException("connect: The address can't be null");
        } else if (i < 0) {
            throw new IllegalArgumentException("connect: timeout can't be negative");
        } else if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!this.oldImpl && isConnected()) {
            throw new SocketException("already connected");
        } else if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            InetAddress address = inetSocketAddress.getAddress();
            int port = inetSocketAddress.getPort();
            checkAddress(address, "connect");
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager == null) {
                if (!this.created) {
                    createImpl(true);
                }
                if (!this.oldImpl) {
                    this.impl.connect(inetSocketAddress, i);
                } else if (i != 0) {
                    throw new UnsupportedOperationException("SocketImpl.connect(addr, timeout)");
                } else if (inetSocketAddress.isUnresolved()) {
                    this.impl.connect(address.getHostName(), port);
                } else {
                    this.impl.connect(address, port);
                }
                this.connected = true;
                this.bound = true;
            } else if (inetSocketAddress.isUnresolved()) {
                securityManager.checkConnect(inetSocketAddress.getHostName(), port);
                throw null;
            } else {
                securityManager.checkConnect(address.getHostAddress(), port);
                throw null;
            }
        } else {
            throw new IllegalArgumentException("Unsupported address type");
        }
    }

    public void bind(SocketAddress socketAddress) {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!this.oldImpl && isBound()) {
            throw new SocketException("Already bound");
        } else if (socketAddress == null || (socketAddress instanceof InetSocketAddress)) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            if (inetSocketAddress == null || !inetSocketAddress.isUnresolved()) {
                if (inetSocketAddress == null) {
                    inetSocketAddress = new InetSocketAddress(0);
                }
                InetAddress address = inetSocketAddress.getAddress();
                int port = inetSocketAddress.getPort();
                checkAddress(address, "bind");
                SecurityManager securityManager = System.getSecurityManager();
                if (securityManager == null) {
                    getImpl().bind(address, port);
                    this.bound = true;
                    return;
                }
                securityManager.checkListen(port);
                throw null;
            }
            throw new SocketException("Unresolved address");
        } else {
            throw new IllegalArgumentException("Unsupported address type");
        }
    }

    private void checkAddress(InetAddress inetAddress, String str) {
        if (inetAddress != null && !(inetAddress instanceof Inet4Address) && !(inetAddress instanceof Inet6Address)) {
            throw new IllegalArgumentException(str + ": invalid address type");
        }
    }

    /* access modifiers changed from: package-private */
    public final void postAccept() {
        this.connected = true;
        this.created = true;
        this.bound = true;
    }

    /* access modifiers changed from: package-private */
    public void setCreated() {
        this.created = true;
    }

    /* access modifiers changed from: package-private */
    public void setBound() {
        this.bound = true;
    }

    /* access modifiers changed from: package-private */
    public void setConnected() {
        this.connected = true;
    }

    public InetAddress getInetAddress() {
        if (!isConnected()) {
            return null;
        }
        try {
            return getImpl().getInetAddress();
        } catch (SocketException unused) {
            return null;
        }
    }

    public InetAddress getLocalAddress() {
        if (!isBound()) {
            return InetAddress.anyLocalAddress();
        }
        try {
            InetAddress inetAddress = (InetAddress) getImpl().getOption(15);
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                securityManager.checkConnect(inetAddress.getHostAddress(), -1);
                throw null;
            } else if (inetAddress.isAnyLocalAddress()) {
                return InetAddress.anyLocalAddress();
            } else {
                return inetAddress;
            }
        } catch (SecurityException unused) {
            return InetAddress.getLoopbackAddress();
        } catch (Exception unused2) {
            return InetAddress.anyLocalAddress();
        }
    }

    public InputStream getInputStream() {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (!isInputShutdown()) {
            try {
                return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                    /* class java.net.Socket.AnonymousClass2 */

                    @Override // java.security.PrivilegedExceptionAction
                    public InputStream run() {
                        return Socket.this.impl.getInputStream();
                    }
                });
            } catch (PrivilegedActionException e) {
                throw ((IOException) e.getException());
            }
        } else {
            throw new SocketException("Socket input is shutdown");
        }
    }

    public OutputStream getOutputStream() {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (!isOutputShutdown()) {
            try {
                return (OutputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                    /* class java.net.Socket.AnonymousClass3 */

                    @Override // java.security.PrivilegedExceptionAction
                    public OutputStream run() {
                        return Socket.this.impl.getOutputStream();
                    }
                });
            } catch (PrivilegedActionException e) {
                throw ((IOException) e.getException());
            }
        } else {
            throw new SocketException("Socket output is shutdown");
        }
    }

    public synchronized void setSoTimeout(int i) {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (i >= 0) {
            getImpl().setOption(4102, new Integer(i));
        } else {
            throw new IllegalArgumentException("timeout can't be negative");
        }
    }

    public synchronized int getSoTimeout() {
        if (!isClosed()) {
            Object option = getImpl().getOption(4102);
            if (!(option instanceof Integer)) {
                return 0;
            }
            return ((Integer) option).intValue();
        }
        throw new SocketException("Socket is closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        synchronized (this.closeLock) {
            if (!isClosed()) {
                if (this.created) {
                    this.impl.close();
                }
                this.closed = true;
            }
        }
    }

    public String toString() {
        try {
            if (!isConnected()) {
                return "Socket[unconnected]";
            }
            return "Socket[address=" + getImpl().getInetAddress() + ",port=" + getImpl().getPort() + ",localPort=" + getImpl().getLocalPort() + "]";
        } catch (SocketException unused) {
            return "Socket[unconnected]";
        }
    }

    public boolean isConnected() {
        return this.connected || this.oldImpl;
    }

    public boolean isBound() {
        return this.bound || this.oldImpl;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.closeLock) {
            z = this.closed;
        }
        return z;
    }

    public boolean isInputShutdown() {
        return this.shutIn;
    }

    public boolean isOutputShutdown() {
        return this.shutOut;
    }
}
