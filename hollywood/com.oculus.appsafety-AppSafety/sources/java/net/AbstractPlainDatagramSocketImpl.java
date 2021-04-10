package java.net;

import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.security.AccessController;
import java.util.Enumeration;
import libcore.io.IoBridge;
import libcore.io.IoUtils;
import sun.net.ResourceManager;
import sun.security.action.GetPropertyAction;

/* access modifiers changed from: package-private */
public abstract class AbstractPlainDatagramSocketImpl extends DatagramSocketImpl {
    private static final boolean connectDisabled = os.contains("OS X");
    private static final String os = ((String) AccessController.doPrivileged(new GetPropertyAction("os.name")));
    boolean connected = false;
    protected InetAddress connectedAddress = null;
    private int connectedPort = -1;
    private final CloseGuard guard = CloseGuard.get();
    int timeout = 0;
    private int trafficClass = 0;

    /* access modifiers changed from: protected */
    public abstract void bind0(int i, InetAddress inetAddress) throws SocketException;

    /* access modifiers changed from: protected */
    public abstract void connect0(InetAddress inetAddress, int i) throws SocketException;

    /* access modifiers changed from: protected */
    public abstract void datagramSocketClose();

    /* access modifiers changed from: protected */
    public abstract void datagramSocketCreate() throws SocketException;

    /* access modifiers changed from: protected */
    public abstract void disconnect0(int i);

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    @Deprecated
    public abstract byte getTTL() throws IOException;

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public abstract int getTimeToLive() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void join(InetAddress inetAddress, NetworkInterface networkInterface) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void leave(InetAddress inetAddress, NetworkInterface networkInterface) throws IOException;

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public abstract int peek(InetAddress inetAddress) throws IOException;

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public abstract int peekData(DatagramPacket datagramPacket) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void receive0(DatagramPacket datagramPacket) throws IOException;

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public abstract void send(DatagramPacket datagramPacket) throws IOException;

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    @Deprecated
    public abstract void setTTL(byte b) throws IOException;

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public abstract void setTimeToLive(int i) throws IOException;

    /* access modifiers changed from: protected */
    public abstract Object socketGetOption(int i) throws SocketException;

    /* access modifiers changed from: protected */
    public abstract void socketSetOption(int i, Object obj) throws SocketException;

    AbstractPlainDatagramSocketImpl() {
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public synchronized void create() throws SocketException {
        ResourceManager.beforeUdpCreate();
        this.fd = new FileDescriptor();
        try {
            datagramSocketCreate();
            if (this.fd != null && this.fd.valid()) {
                this.guard.open("close");
                IoUtils.setFdOwner(this.fd, this);
            }
        } catch (SocketException ioe) {
            ResourceManager.afterUdpClose();
            this.fd = null;
            throw ioe;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public synchronized void bind(int lport, InetAddress laddr) throws SocketException {
        bind0(lport, laddr);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void connect(InetAddress address, int port) throws SocketException {
        BlockGuard.getThreadPolicy().onNetwork();
        connect0(address, port);
        this.connectedAddress = address;
        this.connectedPort = port;
        this.connected = true;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void disconnect() {
        disconnect0(this.connectedAddress.holder().getFamily());
        this.connected = false;
        this.connectedAddress = null;
        this.connectedPort = -1;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public synchronized void receive(DatagramPacket p) throws IOException {
        receive0(p);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void join(InetAddress inetaddr) throws IOException {
        join(inetaddr, null);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void leave(InetAddress inetaddr) throws IOException {
        leave(inetaddr, null);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void joinGroup(SocketAddress mcastaddr, NetworkInterface netIf) throws IOException {
        if (mcastaddr == null || !(mcastaddr instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Unsupported address type");
        }
        join(((InetSocketAddress) mcastaddr).getAddress(), netIf);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void leaveGroup(SocketAddress mcastaddr, NetworkInterface netIf) throws IOException {
        if (mcastaddr == null || !(mcastaddr instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Unsupported address type");
        }
        leave(((InetSocketAddress) mcastaddr).getAddress(), netIf);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.DatagramSocketImpl
    public void close() {
        this.guard.close();
        if (this.fd != null) {
            datagramSocketClose();
            ResourceManager.afterUdpClose();
            this.fd = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isClosed() {
        return this.fd == null;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
    }

    @Override // java.net.SocketOptions
    public void setOption(int optID, Object o) throws SocketException {
        if (!isClosed()) {
            if (optID != 3) {
                if (optID != 4) {
                    if (optID == 15) {
                        throw new SocketException("Cannot re-bind Socket");
                    } else if (optID != 16) {
                        if (optID != 18) {
                            if (optID != 4102) {
                                if (optID != 31) {
                                    if (optID != 32) {
                                        if (optID != 4097 && optID != 4098) {
                                            throw new SocketException("invalid option: " + optID);
                                        } else if (o == null || !(o instanceof Integer) || ((Integer) o).intValue() < 0) {
                                            throw new SocketException("bad argument for SO_SNDBUF or SO_RCVBUF");
                                        }
                                    } else if (o == null || !(o instanceof Boolean)) {
                                        throw new SocketException("bad argument for SO_BROADCAST");
                                    }
                                } else if (o == null || (!(o instanceof Integer) && !(o instanceof NetworkInterface))) {
                                    throw new SocketException("bad argument for IP_MULTICAST_IF2");
                                } else if (o instanceof NetworkInterface) {
                                    o = new Integer(((NetworkInterface) o).getIndex());
                                }
                            } else if (o == null || !(o instanceof Integer)) {
                                throw new SocketException("bad argument for SO_TIMEOUT");
                            } else {
                                int tmp = ((Integer) o).intValue();
                                if (tmp >= 0) {
                                    this.timeout = tmp;
                                    return;
                                }
                                throw new IllegalArgumentException("timeout < 0");
                            }
                        } else if (o == null || !(o instanceof Boolean)) {
                            throw new SocketException("bad argument for IP_MULTICAST_LOOP");
                        }
                    } else if (o == null || !(o instanceof InetAddress)) {
                        throw new SocketException("bad argument for IP_MULTICAST_IF");
                    }
                } else if (o == null || !(o instanceof Boolean)) {
                    throw new SocketException("bad argument for SO_REUSEADDR");
                }
            } else if (o == null || !(o instanceof Integer)) {
                throw new SocketException("bad argument for IP_TOS");
            } else {
                this.trafficClass = ((Integer) o).intValue();
            }
            socketSetOption(optID, o);
            return;
        }
        throw new SocketException("Socket Closed");
    }

    @Override // java.net.SocketOptions
    public Object getOption(int optID) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket Closed");
        } else if (optID != 3) {
            if (!(optID == 4 || optID == 15 || optID == 16 || optID == 18)) {
                if (optID == 4102) {
                    return new Integer(this.timeout);
                }
                if (!(optID == 31 || optID == 32 || optID == 4097 || optID == 4098)) {
                    throw new SocketException("invalid option: " + optID);
                }
            }
            Object result = socketGetOption(optID);
            if (optID == 16) {
                return getNIFirstAddress(((Integer) result).intValue());
            }
            return result;
        } else {
            Object result2 = socketGetOption(optID);
            return ((Integer) result2).intValue() == -1 ? new Integer(this.trafficClass) : result2;
        }
    }

    static InetAddress getNIFirstAddress(int niIndex) throws SocketException {
        if (niIndex > 0) {
            Enumeration<InetAddress> addressesEnum = NetworkInterface.getByIndex(niIndex).getInetAddresses();
            if (addressesEnum.hasMoreElements()) {
                return addressesEnum.nextElement();
            }
        }
        return InetAddress.anyLocalAddress();
    }

    /* access modifiers changed from: protected */
    public boolean nativeConnectDisabled() {
        return connectDisabled;
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.DatagramSocketImpl
    public int dataAvailable() {
        try {
            return IoBridge.available(this.fd);
        } catch (IOException e) {
            return -1;
        }
    }
}
