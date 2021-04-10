package java.net;

import java.io.Closeable;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public class ServerSocket implements Closeable {
    private static SocketImplFactory factory;
    private boolean bound = false;
    private Object closeLock = new Object();
    private boolean closed = false;
    private boolean created = false;
    private SocketImpl impl;
    private boolean oldImpl = false;

    public ServerSocket(int i, int i2, InetAddress inetAddress) {
        setImpl();
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("Port value out of range: " + i);
        }
        try {
            bind(new InetSocketAddress(inetAddress, i), i2 < 1 ? 50 : i2);
        } catch (SecurityException e) {
            close();
            throw e;
        } catch (IOException e2) {
            close();
            throw e2;
        }
    }

    public SocketImpl getImpl() {
        if (!this.created) {
            createImpl();
        }
        return this.impl;
    }

    private void checkOldImpl() {
        if (this.impl != null) {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction() {
                    /* class java.net.ServerSocket.AnonymousClass1 */

                    @Override // java.security.PrivilegedExceptionAction
                    public Void run() {
                        ServerSocket.this.impl.getClass().getDeclaredMethod("connect", SocketAddress.class, Integer.TYPE);
                        return null;
                    }
                });
            } catch (PrivilegedActionException unused) {
                this.oldImpl = true;
            }
        }
    }

    private void setImpl() {
        SocketImplFactory socketImplFactory = factory;
        if (socketImplFactory != null) {
            this.impl = socketImplFactory.createSocketImpl();
            checkOldImpl();
        } else {
            this.impl = new SocksSocketImpl();
        }
        SocketImpl socketImpl = this.impl;
        if (socketImpl != null) {
            socketImpl.setServerSocket(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void createImpl() {
        if (this.impl == null) {
            setImpl();
        }
        try {
            this.impl.create(true);
            this.created = true;
        } catch (IOException e) {
            throw new SocketException(e.getMessage());
        }
    }

    public void bind(SocketAddress socketAddress, int i) {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (this.oldImpl || !isBound()) {
            if (socketAddress == null) {
                socketAddress = new InetSocketAddress(0);
            }
            if (socketAddress instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
                if (!inetSocketAddress.isUnresolved()) {
                    if (i < 1) {
                        i = 50;
                    }
                    try {
                        SecurityManager securityManager = System.getSecurityManager();
                        if (securityManager == null) {
                            getImpl().bind(inetSocketAddress.getAddress(), inetSocketAddress.getPort());
                            getImpl().listen(i);
                            this.bound = true;
                            return;
                        }
                        securityManager.checkListen(inetSocketAddress.getPort());
                        throw null;
                    } catch (SecurityException e) {
                        this.bound = false;
                        throw e;
                    } catch (IOException e2) {
                        this.bound = false;
                        throw e2;
                    }
                } else {
                    throw new SocketException("Unresolved address");
                }
            } else {
                throw new IllegalArgumentException("Unsupported address type");
            }
        } else {
            throw new SocketException("Already bound");
        }
    }

    public InetAddress getInetAddress() {
        if (!isBound()) {
            return null;
        }
        try {
            InetAddress inetAddress = getImpl().getInetAddress();
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager == null) {
                return inetAddress;
            }
            securityManager.checkConnect(inetAddress.getHostAddress(), -1);
            throw null;
        } catch (SecurityException unused) {
            return InetAddress.getLoopbackAddress();
        } catch (SocketException unused2) {
            return null;
        }
    }

    public int getLocalPort() {
        if (!isBound()) {
            return -1;
        }
        try {
            return getImpl().getLocalPort();
        } catch (SocketException unused) {
            return -1;
        }
    }

    public Socket accept() {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (isBound()) {
            Socket socket = new Socket((SocketImpl) null);
            implAccept(socket);
            return socket;
        } else {
            throw new SocketException("Socket is not bound yet");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void implAccept(java.net.Socket r5) {
        /*
            r4 = this;
            r0 = 0
            java.net.SocketImpl r1 = r5.impl     // Catch:{ IOException -> 0x0051, SecurityException -> 0x0047 }
            if (r1 != 0) goto L_0x0009
            r5.setImpl()     // Catch:{ IOException -> 0x0051, SecurityException -> 0x0047 }
            goto L_0x000e
        L_0x0009:
            java.net.SocketImpl r1 = r5.impl     // Catch:{ IOException -> 0x0051, SecurityException -> 0x0047 }
            r1.reset()     // Catch:{ IOException -> 0x0051, SecurityException -> 0x0047 }
        L_0x000e:
            java.net.SocketImpl r1 = r5.impl     // Catch:{ IOException -> 0x0051, SecurityException -> 0x0047 }
            r5.impl = r0     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            java.net.InetAddress r2 = new java.net.InetAddress     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            r2.<init>()     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            r1.address = r2     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            java.io.FileDescriptor r2 = new java.io.FileDescriptor     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            r2.<init>()     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            r1.fd = r2     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            java.net.SocketImpl r4 = r4.getImpl()     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            r4.accept(r1)     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            java.lang.SecurityManager r4 = java.lang.System.getSecurityManager()     // Catch:{ IOException -> 0x0045, SecurityException -> 0x0043 }
            if (r4 != 0) goto L_0x0033
            r5.impl = r1
            r5.postAccept()
            return
        L_0x0033:
            java.net.InetAddress r2 = r1.getInetAddress()
            java.lang.String r2 = r2.getHostAddress()
            int r3 = r1.getPort()
            r4.checkAccept(r2, r3)
            throw r0
        L_0x0043:
            r4 = move-exception
            goto L_0x0049
        L_0x0045:
            r4 = move-exception
            goto L_0x0053
        L_0x0047:
            r4 = move-exception
            r1 = r0
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.reset()
        L_0x004e:
            r5.impl = r1
            throw r4
        L_0x0051:
            r4 = move-exception
            r1 = r0
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.reset()
        L_0x0058:
            r5.impl = r1
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.ServerSocket.implAccept(java.net.Socket):void");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.closeLock) {
            if (!isClosed()) {
                if (this.created) {
                    this.impl.close();
                }
                this.closed = true;
            }
        }
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

    public synchronized void setSoTimeout(int i) {
        if (!isClosed()) {
            getImpl().setOption(4102, new Integer(i));
        } else {
            throw new SocketException("Socket is closed");
        }
    }

    public String toString() {
        InetAddress inetAddress;
        if (!isBound()) {
            return "ServerSocket[unbound]";
        }
        if (System.getSecurityManager() != null) {
            inetAddress = InetAddress.getLoopbackAddress();
        } else {
            inetAddress = this.impl.getInetAddress();
        }
        return "ServerSocket[addr=" + inetAddress + ",localport=" + this.impl.getLocalPort() + "]";
    }

    /* access modifiers changed from: package-private */
    public void setBound() {
        this.bound = true;
    }

    /* access modifiers changed from: package-private */
    public void setCreated() {
        this.created = true;
    }
}
