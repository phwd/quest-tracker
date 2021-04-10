package java.net;

import android.icu.text.DateTimePatternGenerator;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.net.ApplicationProxy;
import sun.security.util.SecurityConstants;

public class Socket implements Closeable {
    private static SocketImplFactory factory = null;
    private boolean bound;
    private Object closeLock;
    private boolean closed;
    private boolean connected;
    private boolean created;
    SocketImpl impl;
    private boolean oldImpl;
    private boolean shutIn;
    private boolean shutOut;

    public Socket() {
        this.created = false;
        this.bound = false;
        this.connected = false;
        this.closed = false;
        this.closeLock = new Object();
        this.shutIn = false;
        this.shutOut = false;
        this.oldImpl = false;
        setImpl();
    }

    public Socket(Proxy proxy) {
        Proxy p;
        this.created = false;
        this.bound = false;
        this.connected = false;
        this.closed = false;
        this.closeLock = new Object();
        this.shutIn = false;
        this.shutOut = false;
        this.oldImpl = false;
        if (proxy != null) {
            if (proxy == Proxy.NO_PROXY) {
                p = Proxy.NO_PROXY;
            } else {
                p = ApplicationProxy.create(proxy);
            }
            if (p.type() == Proxy.Type.SOCKS) {
                SecurityManager security = System.getSecurityManager();
                InetSocketAddress epoint = (InetSocketAddress) p.address();
                if (epoint.getAddress() != null) {
                    checkAddress(epoint.getAddress(), "Socket");
                }
                if (security != null) {
                    epoint = epoint.isUnresolved() ? new InetSocketAddress(epoint.getHostName(), epoint.getPort()) : epoint;
                    if (epoint.isUnresolved()) {
                        security.checkConnect(epoint.getHostName(), epoint.getPort());
                    } else {
                        security.checkConnect(epoint.getAddress().getHostAddress(), epoint.getPort());
                    }
                }
                this.impl = new SocksSocketImpl(p);
                this.impl.setSocket(this);
            } else if (p != Proxy.NO_PROXY) {
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

    protected Socket(SocketImpl impl2) throws SocketException {
        this.created = false;
        this.bound = false;
        this.connected = false;
        this.closed = false;
        this.closeLock = new Object();
        this.shutIn = false;
        this.shutOut = false;
        this.oldImpl = false;
        this.impl = impl2;
        if (impl2 != null) {
            checkOldImpl();
            this.impl.setSocket(this);
        }
    }

    public Socket(String host, int port) throws UnknownHostException, IOException {
        this(InetAddress.getAllByName(host), port, (SocketAddress) null, true);
    }

    public Socket(InetAddress address, int port) throws IOException {
        this(nonNullAddress(address), port, (SocketAddress) null, true);
    }

    public Socket(String host, int port, InetAddress localAddr, int localPort) throws IOException {
        this(InetAddress.getAllByName(host), port, (SocketAddress) new InetSocketAddress(localAddr, localPort), true);
    }

    public Socket(InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException {
        this(nonNullAddress(address), port, (SocketAddress) new InetSocketAddress(localAddr, localPort), true);
    }

    @Deprecated
    public Socket(String host, int port, boolean stream) throws IOException {
        this(InetAddress.getAllByName(host), port, (SocketAddress) null, stream);
    }

    @Deprecated
    public Socket(InetAddress host, int port, boolean stream) throws IOException {
        this(nonNullAddress(host), port, new InetSocketAddress(0), stream);
    }

    private static InetAddress[] nonNullAddress(InetAddress address) {
        if (address != null) {
            return new InetAddress[]{address};
        }
        throw new NullPointerException();
    }

    private Socket(InetAddress[] addresses, int port, SocketAddress localAddr, boolean stream) throws IOException {
        this.created = false;
        this.bound = false;
        this.connected = false;
        this.closed = false;
        this.closeLock = new Object();
        this.shutIn = false;
        this.shutOut = false;
        this.oldImpl = false;
        if (addresses == null || addresses.length == 0) {
            throw new SocketException("Impossible: empty address list");
        }
        for (int i = 0; i < addresses.length; i++) {
            setImpl();
            try {
                InetSocketAddress address = new InetSocketAddress(addresses[i], port);
                createImpl(stream);
                if (localAddr != null) {
                    bind(localAddr);
                }
                connect(address);
                return;
            } catch (IOException | IllegalArgumentException | SecurityException e) {
                try {
                    this.impl.close();
                    this.closed = true;
                } catch (IOException ce) {
                    e.addSuppressed(ce);
                }
                if (i != addresses.length - 1) {
                    this.impl = null;
                    this.created = false;
                    this.bound = false;
                    this.closed = false;
                } else {
                    throw e;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void createImpl(boolean stream) throws SocketException {
        if (this.impl == null) {
            setImpl();
        }
        try {
            this.impl.create(stream);
            this.created = true;
        } catch (IOException e) {
            throw new SocketException(e.getMessage());
        }
    }

    private void checkOldImpl() {
        if (this.impl != null) {
            this.oldImpl = ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
                /* class java.net.Socket.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Boolean run() {
                    Class<?> clazz = Socket.this.impl.getClass();
                    do {
                        try {
                            clazz.getDeclaredMethod(SecurityConstants.SOCKET_CONNECT_ACTION, SocketAddress.class, Integer.TYPE);
                            return Boolean.FALSE;
                        } catch (NoSuchMethodException e) {
                            clazz = clazz.getSuperclass();
                            if (clazz.equals(SocketImpl.class)) {
                                return Boolean.TRUE;
                            }
                        }
                    } while (clazz.equals(SocketImpl.class));
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
    public SocketImpl getImpl() throws SocketException {
        if (!this.created) {
            createImpl(true);
        }
        return this.impl;
    }

    public void connect(SocketAddress endpoint) throws IOException {
        connect(endpoint, 0);
    }

    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        if (endpoint == null) {
            throw new IllegalArgumentException("connect: The address can't be null");
        } else if (timeout < 0) {
            throw new IllegalArgumentException("connect: timeout can't be negative");
        } else if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!this.oldImpl && isConnected()) {
            throw new SocketException("already connected");
        } else if (endpoint instanceof InetSocketAddress) {
            InetSocketAddress epoint = (InetSocketAddress) endpoint;
            InetAddress addr = epoint.getAddress();
            int port = epoint.getPort();
            checkAddress(addr, SecurityConstants.SOCKET_CONNECT_ACTION);
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                if (epoint.isUnresolved()) {
                    security.checkConnect(epoint.getHostName(), port);
                } else {
                    security.checkConnect(addr.getHostAddress(), port);
                }
            }
            if (!this.created) {
                createImpl(true);
            }
            if (!this.oldImpl) {
                this.impl.connect(epoint, timeout);
            } else if (timeout != 0) {
                throw new UnsupportedOperationException("SocketImpl.connect(addr, timeout)");
            } else if (epoint.isUnresolved()) {
                this.impl.connect(addr.getHostName(), port);
            } else {
                this.impl.connect(addr, port);
            }
            this.connected = true;
            this.bound = true;
        } else {
            throw new IllegalArgumentException("Unsupported address type");
        }
    }

    public void bind(SocketAddress bindpoint) throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!this.oldImpl && isBound()) {
            throw new SocketException("Already bound");
        } else if (bindpoint == null || (bindpoint instanceof InetSocketAddress)) {
            InetSocketAddress epoint = (InetSocketAddress) bindpoint;
            if (epoint == null || !epoint.isUnresolved()) {
                if (epoint == null) {
                    epoint = new InetSocketAddress(0);
                }
                InetAddress addr = epoint.getAddress();
                int port = epoint.getPort();
                checkAddress(addr, "bind");
                SecurityManager security = System.getSecurityManager();
                if (security != null) {
                    security.checkListen(port);
                }
                getImpl().bind(addr, port);
                this.bound = true;
                return;
            }
            throw new SocketException("Unresolved address");
        } else {
            throw new IllegalArgumentException("Unsupported address type");
        }
    }

    private void checkAddress(InetAddress addr, String op) {
        if (addr != null && !(addr instanceof Inet4Address) && !(addr instanceof Inet6Address)) {
            throw new IllegalArgumentException(op + ": invalid address type");
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
        } catch (SocketException e) {
            return null;
        }
    }

    public InetAddress getLocalAddress() {
        if (!isBound()) {
            return InetAddress.anyLocalAddress();
        }
        try {
            InetAddress in = (InetAddress) getImpl().getOption(15);
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkConnect(in.getHostAddress(), -1);
            }
            if (in.isAnyLocalAddress()) {
                return InetAddress.anyLocalAddress();
            }
            return in;
        } catch (SecurityException e) {
            return InetAddress.getLoopbackAddress();
        } catch (Exception e2) {
            return InetAddress.anyLocalAddress();
        }
    }

    public int getPort() {
        if (!isConnected()) {
            return 0;
        }
        try {
            return getImpl().getPort();
        } catch (SocketException e) {
            return -1;
        }
    }

    public int getLocalPort() {
        if (!isBound()) {
            return -1;
        }
        try {
            return getImpl().getLocalPort();
        } catch (SocketException e) {
            return -1;
        }
    }

    public SocketAddress getRemoteSocketAddress() {
        if (!isConnected()) {
            return null;
        }
        return new InetSocketAddress(getInetAddress(), getPort());
    }

    public SocketAddress getLocalSocketAddress() {
        if (!isBound()) {
            return null;
        }
        return new InetSocketAddress(getLocalAddress(), getLocalPort());
    }

    public SocketChannel getChannel() {
        return null;
    }

    public InputStream getInputStream() throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (!isInputShutdown()) {
            try {
                return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
                    /* class java.net.Socket.AnonymousClass2 */

                    @Override // java.security.PrivilegedExceptionAction
                    public InputStream run() throws IOException {
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

    public OutputStream getOutputStream() throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (!isOutputShutdown()) {
            try {
                return (OutputStream) AccessController.doPrivileged(new PrivilegedExceptionAction<OutputStream>() {
                    /* class java.net.Socket.AnonymousClass3 */

                    @Override // java.security.PrivilegedExceptionAction
                    public OutputStream run() throws IOException {
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

    public void setTcpNoDelay(boolean on) throws SocketException {
        if (!isClosed()) {
            getImpl().setOption(1, Boolean.valueOf(on));
            return;
        }
        throw new SocketException("Socket is closed");
    }

    public boolean getTcpNoDelay() throws SocketException {
        if (!isClosed()) {
            return ((Boolean) getImpl().getOption(1)).booleanValue();
        }
        throw new SocketException("Socket is closed");
    }

    public void setSoLinger(boolean on, int linger) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!on) {
            getImpl().setOption(128, new Boolean(on));
        } else if (linger >= 0) {
            if (linger > 65535) {
                linger = DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH;
            }
            getImpl().setOption(128, new Integer(linger));
        } else {
            throw new IllegalArgumentException("invalid value for SO_LINGER");
        }
    }

    public int getSoLinger() throws SocketException {
        if (!isClosed()) {
            Object o = getImpl().getOption(128);
            if (o instanceof Integer) {
                return ((Integer) o).intValue();
            }
            return -1;
        }
        throw new SocketException("Socket is closed");
    }

    public void sendUrgentData(int data) throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (getImpl().supportsUrgentData()) {
            getImpl().sendUrgentData(data);
        } else {
            throw new SocketException("Urgent data not supported");
        }
    }

    public void setOOBInline(boolean on) throws SocketException {
        if (!isClosed()) {
            getImpl().setOption(4099, Boolean.valueOf(on));
            return;
        }
        throw new SocketException("Socket is closed");
    }

    public boolean getOOBInline() throws SocketException {
        if (!isClosed()) {
            return ((Boolean) getImpl().getOption(4099)).booleanValue();
        }
        throw new SocketException("Socket is closed");
    }

    public synchronized void setSoTimeout(int timeout) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (timeout >= 0) {
            getImpl().setOption(4102, new Integer(timeout));
        } else {
            throw new IllegalArgumentException("timeout can't be negative");
        }
    }

    public synchronized int getSoTimeout() throws SocketException {
        if (!isClosed()) {
            Object o = getImpl().getOption(4102);
            if (!(o instanceof Integer)) {
                return 0;
            }
            return ((Integer) o).intValue();
        }
        throw new SocketException("Socket is closed");
    }

    public synchronized void setSendBufferSize(int size) throws SocketException {
        if (size <= 0) {
            throw new IllegalArgumentException("negative send size");
        } else if (!isClosed()) {
            getImpl().setOption(4097, new Integer(size));
        } else {
            throw new SocketException("Socket is closed");
        }
    }

    public synchronized int getSendBufferSize() throws SocketException {
        int result;
        if (!isClosed()) {
            result = 0;
            Object o = getImpl().getOption(4097);
            if (o instanceof Integer) {
                result = ((Integer) o).intValue();
            }
        } else {
            throw new SocketException("Socket is closed");
        }
        return result;
    }

    public synchronized void setReceiveBufferSize(int size) throws SocketException {
        if (size <= 0) {
            throw new IllegalArgumentException("invalid receive size");
        } else if (!isClosed()) {
            getImpl().setOption(4098, new Integer(size));
        } else {
            throw new SocketException("Socket is closed");
        }
    }

    public synchronized int getReceiveBufferSize() throws SocketException {
        int result;
        if (!isClosed()) {
            result = 0;
            Object o = getImpl().getOption(4098);
            if (o instanceof Integer) {
                result = ((Integer) o).intValue();
            }
        } else {
            throw new SocketException("Socket is closed");
        }
        return result;
    }

    public void setKeepAlive(boolean on) throws SocketException {
        if (!isClosed()) {
            getImpl().setOption(8, Boolean.valueOf(on));
            return;
        }
        throw new SocketException("Socket is closed");
    }

    public boolean getKeepAlive() throws SocketException {
        if (!isClosed()) {
            return ((Boolean) getImpl().getOption(8)).booleanValue();
        }
        throw new SocketException("Socket is closed");
    }

    public void setTrafficClass(int tc) throws SocketException {
        if (tc < 0 || tc > 255) {
            throw new IllegalArgumentException("tc is not in range 0 -- 255");
        } else if (!isClosed()) {
            try {
                getImpl().setOption(3, Integer.valueOf(tc));
            } catch (SocketException se) {
                if (!isConnected()) {
                    throw se;
                }
            }
        } else {
            throw new SocketException("Socket is closed");
        }
    }

    public int getTrafficClass() throws SocketException {
        if (!isClosed()) {
            return ((Integer) getImpl().getOption(3)).intValue();
        }
        throw new SocketException("Socket is closed");
    }

    public void setReuseAddress(boolean on) throws SocketException {
        if (!isClosed()) {
            getImpl().setOption(4, Boolean.valueOf(on));
            return;
        }
        throw new SocketException("Socket is closed");
    }

    public boolean getReuseAddress() throws SocketException {
        if (!isClosed()) {
            return ((Boolean) getImpl().getOption(4)).booleanValue();
        }
        throw new SocketException("Socket is closed");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x001f, code lost:
        r1 = th;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void close() throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.Object r0 = r2.closeLock     // Catch:{ all -> 0x0021 }
            monitor-enter(r0)     // Catch:{ all -> 0x0021 }
            boolean r1 = r2.isClosed()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            monitor-exit(r2)
            return
        L_0x000d:
            boolean r1 = r2.created
            if (r1 == 0) goto L_0x0016
            java.net.SocketImpl r1 = r2.impl
            r1.close()
        L_0x0016:
            r1 = 1
            r2.closed = r1
            monitor-exit(r0)
            monitor-exit(r2)
            return
        L_0x001c:
            r1 = move-exception
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r1
        L_0x001f:
            r1 = move-exception
            goto L_0x001d
        L_0x0021:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.Socket.close():void");
    }

    public void shutdownInput() throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (!isInputShutdown()) {
            getImpl().shutdownInput();
            this.shutIn = true;
        } else {
            throw new SocketException("Socket input is already shutdown");
        }
    }

    public void shutdownOutput() throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (!isOutputShutdown()) {
            getImpl().shutdownOutput();
            this.shutOut = true;
        } else {
            throw new SocketException("Socket output is already shutdown");
        }
    }

    public String toString() {
        try {
            if (!isConnected()) {
                return "Socket[unconnected]";
            }
            return "Socket[address=" + ((Object) getImpl().getInetAddress()) + ",port=" + getImpl().getPort() + ",localPort=" + getImpl().getLocalPort() + "]";
        } catch (SocketException e) {
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

    public static synchronized void setSocketImplFactory(SocketImplFactory fac) throws IOException {
        synchronized (Socket.class) {
            if (factory == null) {
                SecurityManager security = System.getSecurityManager();
                if (security != null) {
                    security.checkSetFactory();
                }
                factory = fac;
            } else {
                throw new SocketException("factory already defined");
            }
        }
    }

    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
    }

    public FileDescriptor getFileDescriptor$() {
        return this.impl.getFileDescriptor();
    }
}
