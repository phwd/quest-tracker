package java.net;

import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.security.util.SecurityConstants;

public class DatagramSocket implements Closeable {
    static final int ST_CONNECTED = 1;
    static final int ST_CONNECTED_NO_IMPL = 2;
    static final int ST_NOT_CONNECTED = 0;
    static DatagramSocketImplFactory factory;
    static Class<?> implClass = null;
    private boolean bound;
    private int bytesLeftToFilter;
    private Object closeLock;
    private boolean closed;
    int connectState;
    InetAddress connectedAddress;
    int connectedPort;
    private boolean created;
    private boolean explicitFilter;
    DatagramSocketImpl impl;
    boolean oldImpl;
    private SocketException pendingConnectException;

    private synchronized void connectInternal(InetAddress address, int port) throws SocketException {
        SocketException se;
        SocketException se2;
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("connect: " + port);
        } else if (address != null) {
            checkAddress(address, SecurityConstants.SOCKET_CONNECT_ACTION);
            if (!isClosed()) {
                SecurityManager security = System.getSecurityManager();
                if (security != null) {
                    if (address.isMulticastAddress()) {
                        security.checkMulticast(address);
                    } else {
                        security.checkConnect(address.getHostAddress(), port);
                        security.checkAccept(address.getHostAddress(), port);
                    }
                }
                if (!isBound()) {
                    bind(new InetSocketAddress(0));
                }
                try {
                    if (!this.oldImpl) {
                        try {
                            if (!(this.impl instanceof AbstractPlainDatagramSocketImpl) || !((AbstractPlainDatagramSocketImpl) this.impl).nativeConnectDisabled()) {
                                try {
                                    getImpl().connect(address, port);
                                    boolean z = true;
                                    this.connectState = 1;
                                    int avail = getImpl().dataAvailable();
                                    if (avail != -1) {
                                        if (avail <= 0) {
                                            z = false;
                                        }
                                        try {
                                            this.explicitFilter = z;
                                            if (this.explicitFilter) {
                                                this.bytesLeftToFilter = getReceiveBufferSize();
                                            }
                                            this.connectedAddress = address;
                                            this.connectedPort = port;
                                        } catch (SocketException e) {
                                            se2 = e;
                                            this.connectState = 2;
                                            throw se2;
                                        }
                                    } else {
                                        throw new SocketException();
                                    }
                                } catch (SocketException e2) {
                                    se2 = e2;
                                    this.connectState = 2;
                                    throw se2;
                                }
                            }
                        } catch (Throwable th) {
                            se = th;
                            this.connectedAddress = address;
                            this.connectedPort = port;
                            throw se;
                        }
                    }
                    this.connectState = 2;
                    this.connectedAddress = address;
                    this.connectedPort = port;
                } catch (Throwable th2) {
                    se = th2;
                    this.connectedAddress = address;
                    this.connectedPort = port;
                    throw se;
                }
            }
        } else {
            throw new IllegalArgumentException("connect: null address");
        }
    }

    public DatagramSocket() throws SocketException {
        this(new InetSocketAddress(0));
    }

    protected DatagramSocket(DatagramSocketImpl impl2) {
        this.created = false;
        this.bound = false;
        this.closed = false;
        this.closeLock = new Object();
        this.oldImpl = false;
        this.explicitFilter = false;
        this.connectState = 0;
        this.connectedAddress = null;
        this.connectedPort = -1;
        if (impl2 != null) {
            this.impl = impl2;
            checkOldImpl();
            return;
        }
        throw new NullPointerException();
    }

    public DatagramSocket(SocketAddress bindaddr) throws SocketException {
        this.created = false;
        this.bound = false;
        this.closed = false;
        this.closeLock = new Object();
        this.oldImpl = false;
        this.explicitFilter = false;
        this.connectState = 0;
        this.connectedAddress = null;
        this.connectedPort = -1;
        createImpl();
        if (bindaddr != null) {
            try {
                bind(bindaddr);
            } finally {
                if (!isBound()) {
                    close();
                }
            }
        }
    }

    public DatagramSocket(int port) throws SocketException {
        this(port, null);
    }

    public DatagramSocket(int port, InetAddress laddr) throws SocketException {
        this(new InetSocketAddress(laddr, port));
    }

    private void checkOldImpl() {
        if (this.impl != null) {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                    /* class java.net.DatagramSocket.AnonymousClass1 */

                    @Override // java.security.PrivilegedExceptionAction
                    public Void run() throws NoSuchMethodException {
                        DatagramSocket.this.impl.getClass().getDeclaredMethod("peekData", DatagramPacket.class);
                        return null;
                    }
                });
            } catch (PrivilegedActionException e) {
                this.oldImpl = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void createImpl() throws SocketException {
        if (this.impl == null) {
            DatagramSocketImplFactory datagramSocketImplFactory = factory;
            if (datagramSocketImplFactory != null) {
                this.impl = datagramSocketImplFactory.createDatagramSocketImpl();
                checkOldImpl();
            } else {
                this.impl = DefaultDatagramSocketImplFactory.createDatagramSocketImpl(this instanceof MulticastSocket);
                checkOldImpl();
            }
        }
        this.impl.create();
        this.impl.setDatagramSocket(this);
        this.created = true;
    }

    /* access modifiers changed from: package-private */
    public DatagramSocketImpl getImpl() throws SocketException {
        if (!this.created) {
            createImpl();
        }
        return this.impl;
    }

    public synchronized void bind(SocketAddress addr) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (!isBound()) {
            if (addr == null) {
                addr = new InetSocketAddress(0);
            }
            if (addr instanceof InetSocketAddress) {
                InetSocketAddress epoint = (InetSocketAddress) addr;
                if (!epoint.isUnresolved()) {
                    InetAddress iaddr = epoint.getAddress();
                    int port = epoint.getPort();
                    checkAddress(iaddr, "bind");
                    SecurityManager sec = System.getSecurityManager();
                    if (sec != null) {
                        sec.checkListen(port);
                    }
                    try {
                        getImpl().bind(port, iaddr);
                        this.bound = true;
                    } catch (SocketException e) {
                        getImpl().close();
                        throw e;
                    }
                } else {
                    throw new SocketException("Unresolved address");
                }
            } else {
                throw new IllegalArgumentException("Unsupported address type!");
            }
        } else {
            throw new SocketException("already bound");
        }
    }

    /* access modifiers changed from: package-private */
    public void checkAddress(InetAddress addr, String op) {
        if (addr != null && !(addr instanceof Inet4Address) && !(addr instanceof Inet6Address)) {
            throw new IllegalArgumentException(op + ": invalid address type");
        }
    }

    public void connect(InetAddress address, int port) {
        try {
            connectInternal(address, port);
        } catch (SocketException se) {
            this.pendingConnectException = se;
        }
    }

    public void connect(SocketAddress addr) throws SocketException {
        if (addr == null) {
            throw new IllegalArgumentException("Address can't be null");
        } else if (addr instanceof InetSocketAddress) {
            InetSocketAddress epoint = (InetSocketAddress) addr;
            if (!epoint.isUnresolved()) {
                connectInternal(epoint.getAddress(), epoint.getPort());
                return;
            }
            throw new SocketException("Unresolved address");
        } else {
            throw new IllegalArgumentException("Unsupported address type");
        }
    }

    public void disconnect() {
        synchronized (this) {
            if (!isClosed()) {
                if (this.connectState == 1) {
                    this.impl.disconnect();
                }
                this.connectedAddress = null;
                this.connectedPort = -1;
                this.connectState = 0;
                this.explicitFilter = false;
            }
        }
    }

    public boolean isBound() {
        return this.bound;
    }

    public boolean isConnected() {
        return this.connectState != 0;
    }

    public InetAddress getInetAddress() {
        return this.connectedAddress;
    }

    public int getPort() {
        return this.connectedPort;
    }

    public SocketAddress getRemoteSocketAddress() {
        if (!isConnected()) {
            return null;
        }
        return new InetSocketAddress(getInetAddress(), getPort());
    }

    public SocketAddress getLocalSocketAddress() {
        if (!isClosed() && isBound()) {
            return new InetSocketAddress(getLocalAddress(), getLocalPort());
        }
        return null;
    }

    public void send(DatagramPacket p) throws IOException {
        synchronized (p) {
            if (this.pendingConnectException != null) {
                throw new SocketException("Pending connect failure", this.pendingConnectException);
            } else if (!isClosed()) {
                checkAddress(p.getAddress(), "send");
                if (this.connectState == 0) {
                    SecurityManager security = System.getSecurityManager();
                    if (security != null) {
                        if (p.getAddress().isMulticastAddress()) {
                            security.checkMulticast(p.getAddress());
                        } else {
                            security.checkConnect(p.getAddress().getHostAddress(), p.getPort());
                        }
                    }
                } else {
                    InetAddress packetAddress = p.getAddress();
                    if (packetAddress == null) {
                        p.setAddress(this.connectedAddress);
                        p.setPort(this.connectedPort);
                    } else if (!packetAddress.equals(this.connectedAddress) || p.getPort() != this.connectedPort) {
                        throw new IllegalArgumentException("connected address and packet address differ");
                    }
                }
                if (!isBound()) {
                    bind(new InetSocketAddress(0));
                }
                getImpl().send(p);
            } else {
                throw new SocketException("Socket is closed");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00eb, code lost:
        r0 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void receive(java.net.DatagramPacket r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.DatagramSocket.receive(java.net.DatagramPacket):void");
    }

    private boolean checkFiltering(DatagramPacket p) throws SocketException {
        this.bytesLeftToFilter -= p.getLength();
        if (this.bytesLeftToFilter > 0 && getImpl().dataAvailable() > 0) {
            return false;
        }
        this.explicitFilter = false;
        return true;
    }

    public InetAddress getLocalAddress() {
        if (isClosed()) {
            return null;
        }
        try {
            InetAddress in = (InetAddress) getImpl().getOption(15);
            if (in.isAnyLocalAddress()) {
                in = InetAddress.anyLocalAddress();
            }
            SecurityManager s = System.getSecurityManager();
            if (s == null) {
                return in;
            }
            s.checkConnect(in.getHostAddress(), -1);
            return in;
        } catch (Exception e) {
            return InetAddress.anyLocalAddress();
        }
    }

    public int getLocalPort() {
        if (isClosed()) {
            return -1;
        }
        try {
            return getImpl().getLocalPort();
        } catch (Exception e) {
            return 0;
        }
    }

    public synchronized void setSoTimeout(int timeout) throws SocketException {
        if (!isClosed()) {
            getImpl().setOption(4102, new Integer(timeout));
        } else {
            throw new SocketException("Socket is closed");
        }
    }

    public synchronized int getSoTimeout() throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (getImpl() == null) {
            return 0;
        } else {
            Object o = getImpl().getOption(4102);
            if (!(o instanceof Integer)) {
                return 0;
            }
            return ((Integer) o).intValue();
        }
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

    public synchronized void setReuseAddress(boolean on) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        } else if (this.oldImpl) {
            getImpl().setOption(4, new Integer(on ? -1 : 0));
        } else {
            getImpl().setOption(4, Boolean.valueOf(on));
        }
    }

    public synchronized boolean getReuseAddress() throws SocketException {
        if (!isClosed()) {
        } else {
            throw new SocketException("Socket is closed");
        }
        return ((Boolean) getImpl().getOption(4)).booleanValue();
    }

    public synchronized void setBroadcast(boolean on) throws SocketException {
        if (!isClosed()) {
            getImpl().setOption(32, Boolean.valueOf(on));
        } else {
            throw new SocketException("Socket is closed");
        }
    }

    public synchronized boolean getBroadcast() throws SocketException {
        if (!isClosed()) {
        } else {
            throw new SocketException("Socket is closed");
        }
        return ((Boolean) getImpl().getOption(32)).booleanValue();
    }

    public synchronized void setTrafficClass(int tc) throws SocketException {
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

    public synchronized int getTrafficClass() throws SocketException {
        if (!isClosed()) {
        } else {
            throw new SocketException("Socket is closed");
        }
        return ((Integer) getImpl().getOption(3)).intValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.closeLock) {
            if (!isClosed()) {
                this.impl.close();
                this.closed = true;
            }
        }
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.closeLock) {
            z = this.closed;
        }
        return z;
    }

    public DatagramChannel getChannel() {
        return null;
    }

    public static synchronized void setDatagramSocketImplFactory(DatagramSocketImplFactory fac) throws IOException {
        synchronized (DatagramSocket.class) {
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

    public FileDescriptor getFileDescriptor$() {
        return this.impl.fd;
    }
}
