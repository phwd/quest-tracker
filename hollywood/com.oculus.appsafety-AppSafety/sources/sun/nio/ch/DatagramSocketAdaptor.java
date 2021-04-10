package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.net.SocketTimeoutException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.IllegalBlockingModeException;

public class DatagramSocketAdaptor extends DatagramSocket {
    private static final DatagramSocketImpl dummyDatagramSocket = new DatagramSocketImpl() {
        /* class sun.nio.ch.DatagramSocketAdaptor.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void create() throws SocketException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void bind(int lport, InetAddress laddr) throws SocketException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void send(DatagramPacket p) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public int peek(InetAddress i) throws IOException {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public int peekData(DatagramPacket p) throws IOException {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void receive(DatagramPacket p) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        @Deprecated
        public void setTTL(byte ttl) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        @Deprecated
        public byte getTTL() throws IOException {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void setTimeToLive(int ttl) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public int getTimeToLive() throws IOException {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void join(InetAddress inetaddr) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void leave(InetAddress inetaddr) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void joinGroup(SocketAddress mcastaddr, NetworkInterface netIf) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void leaveGroup(SocketAddress mcastaddr, NetworkInterface netIf) throws IOException {
        }

        /* access modifiers changed from: protected */
        @Override // java.net.DatagramSocketImpl
        public void close() {
        }

        @Override // java.net.SocketOptions
        public Object getOption(int optID) throws SocketException {
            return null;
        }

        @Override // java.net.SocketOptions
        public void setOption(int optID, Object value) throws SocketException {
        }
    };
    private final DatagramChannelImpl dc;
    private volatile int timeout = 0;

    private DatagramSocketAdaptor(DatagramChannelImpl dc2) throws IOException {
        super(dummyDatagramSocket);
        this.dc = dc2;
    }

    public static DatagramSocket create(DatagramChannelImpl dc2) {
        try {
            return new DatagramSocketAdaptor(dc2);
        } catch (IOException x) {
            throw new Error(x);
        }
    }

    private void connectInternal(SocketAddress remote) throws SocketException {
        int port = Net.asInetSocketAddress(remote).getPort();
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("connect: " + port);
        } else if (remote == null) {
            throw new IllegalArgumentException("connect: null address");
        } else if (!isClosed()) {
            try {
                this.dc.connect(remote);
            } catch (Exception x) {
                Net.translateToSocketException(x);
            }
        }
    }

    @Override // java.net.DatagramSocket
    public void bind(SocketAddress local) throws SocketException {
        if (local == null) {
            try {
                local = new InetSocketAddress(0);
            } catch (Exception x) {
                Net.translateToSocketException(x);
                return;
            }
        }
        this.dc.bind(local);
    }

    @Override // java.net.DatagramSocket
    public void connect(InetAddress address, int port) {
        try {
            connectInternal(new InetSocketAddress(address, port));
        } catch (SocketException e) {
        }
    }

    @Override // java.net.DatagramSocket
    public void connect(SocketAddress remote) throws SocketException {
        if (remote != null) {
            connectInternal(remote);
            return;
        }
        throw new IllegalArgumentException("Address can't be null");
    }

    @Override // java.net.DatagramSocket
    public void disconnect() {
        try {
            this.dc.disconnect();
        } catch (IOException x) {
            throw new Error(x);
        }
    }

    @Override // java.net.DatagramSocket
    public boolean isBound() {
        return this.dc.localAddress() != null;
    }

    @Override // java.net.DatagramSocket
    public boolean isConnected() {
        return this.dc.remoteAddress() != null;
    }

    @Override // java.net.DatagramSocket
    public InetAddress getInetAddress() {
        if (isConnected()) {
            return Net.asInetSocketAddress(this.dc.remoteAddress()).getAddress();
        }
        return null;
    }

    @Override // java.net.DatagramSocket
    public int getPort() {
        if (isConnected()) {
            return Net.asInetSocketAddress(this.dc.remoteAddress()).getPort();
        }
        return -1;
    }

    @Override // java.net.DatagramSocket
    public void send(DatagramPacket p) throws IOException {
        synchronized (this.dc.blockingLock()) {
            if (this.dc.isBlocking()) {
                try {
                    synchronized (p) {
                        ByteBuffer bb = ByteBuffer.wrap(p.getData(), p.getOffset(), p.getLength());
                        if (!this.dc.isConnected()) {
                            this.dc.send(bb, p.getSocketAddress());
                        } else if (p.getAddress() == null) {
                            InetSocketAddress isa = (InetSocketAddress) this.dc.remoteAddress();
                            p.setPort(isa.getPort());
                            p.setAddress(isa.getAddress());
                            this.dc.write(bb);
                        } else {
                            this.dc.send(bb, p.getSocketAddress());
                        }
                    }
                } catch (IOException x) {
                    Net.translateException(x);
                }
            } else {
                throw new IllegalBlockingModeException();
            }
        }
    }

    private SocketAddress receive(ByteBuffer bb) throws IOException {
        SocketAddress sender;
        if (this.timeout == 0) {
            return this.dc.receive(bb);
        }
        this.dc.configureBlocking(false);
        try {
            SocketAddress sender2 = this.dc.receive(bb);
            if (sender2 != null) {
                return sender2;
            }
            long to = (long) this.timeout;
            while (this.dc.isOpen()) {
                long st = System.currentTimeMillis();
                int result = this.dc.poll(Net.POLLIN, to);
                if (result <= 0 || (Net.POLLIN & result) == 0 || (sender = this.dc.receive(bb)) == null) {
                    to -= System.currentTimeMillis() - st;
                    if (to <= 0) {
                        throw new SocketTimeoutException();
                    }
                } else {
                    if (this.dc.isOpen()) {
                        this.dc.configureBlocking(true);
                    }
                    return sender;
                }
            }
            throw new ClosedChannelException();
        } finally {
            if (this.dc.isOpen()) {
                this.dc.configureBlocking(true);
            }
        }
    }

    @Override // java.net.DatagramSocket
    public void receive(DatagramPacket p) throws IOException {
        synchronized (this.dc.blockingLock()) {
            if (this.dc.isBlocking()) {
                try {
                    synchronized (p) {
                        ByteBuffer bb = ByteBuffer.wrap(p.getData(), p.getOffset(), p.getLength());
                        p.setSocketAddress(receive(bb));
                        p.setLength(bb.position() - p.getOffset());
                    }
                } catch (IOException x) {
                    Net.translateException(x);
                }
            } else {
                throw new IllegalBlockingModeException();
            }
        }
    }

    @Override // java.net.DatagramSocket
    public InetAddress getLocalAddress() {
        if (isClosed()) {
            return null;
        }
        SocketAddress local = this.dc.localAddress();
        if (local == null) {
            local = new InetSocketAddress(0);
        }
        InetAddress result = ((InetSocketAddress) local).getAddress();
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkConnect(result.getHostAddress(), -1);
            } catch (SecurityException e) {
                return new InetSocketAddress(0).getAddress();
            }
        }
        return result;
    }

    @Override // java.net.DatagramSocket
    public int getLocalPort() {
        if (isClosed()) {
            return -1;
        }
        try {
            SocketAddress local = this.dc.getLocalAddress();
            if (local != null) {
                return ((InetSocketAddress) local).getPort();
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override // java.net.DatagramSocket
    public void setSoTimeout(int timeout2) throws SocketException {
        this.timeout = timeout2;
    }

    @Override // java.net.DatagramSocket
    public int getSoTimeout() throws SocketException {
        return this.timeout;
    }

    private void setBooleanOption(SocketOption<Boolean> name, boolean value) throws SocketException {
        try {
            this.dc.setOption((SocketOption) name, (Object) Boolean.valueOf(value));
        } catch (IOException x) {
            Net.translateToSocketException(x);
        }
    }

    private void setIntOption(SocketOption<Integer> name, int value) throws SocketException {
        try {
            this.dc.setOption((SocketOption) name, (Object) Integer.valueOf(value));
        } catch (IOException x) {
            Net.translateToSocketException(x);
        }
    }

    private boolean getBooleanOption(SocketOption<Boolean> name) throws SocketException {
        try {
            return ((Boolean) this.dc.getOption(name)).booleanValue();
        } catch (IOException x) {
            Net.translateToSocketException(x);
            return false;
        }
    }

    private int getIntOption(SocketOption<Integer> name) throws SocketException {
        try {
            return ((Integer) this.dc.getOption(name)).intValue();
        } catch (IOException x) {
            Net.translateToSocketException(x);
            return -1;
        }
    }

    @Override // java.net.DatagramSocket
    public void setSendBufferSize(int size) throws SocketException {
        if (size > 0) {
            setIntOption(StandardSocketOptions.SO_SNDBUF, size);
            return;
        }
        throw new IllegalArgumentException("Invalid send size");
    }

    @Override // java.net.DatagramSocket
    public int getSendBufferSize() throws SocketException {
        return getIntOption(StandardSocketOptions.SO_SNDBUF);
    }

    @Override // java.net.DatagramSocket
    public void setReceiveBufferSize(int size) throws SocketException {
        if (size > 0) {
            setIntOption(StandardSocketOptions.SO_RCVBUF, size);
            return;
        }
        throw new IllegalArgumentException("Invalid receive size");
    }

    @Override // java.net.DatagramSocket
    public int getReceiveBufferSize() throws SocketException {
        return getIntOption(StandardSocketOptions.SO_RCVBUF);
    }

    @Override // java.net.DatagramSocket
    public void setReuseAddress(boolean on) throws SocketException {
        setBooleanOption(StandardSocketOptions.SO_REUSEADDR, on);
    }

    @Override // java.net.DatagramSocket
    public boolean getReuseAddress() throws SocketException {
        return getBooleanOption(StandardSocketOptions.SO_REUSEADDR);
    }

    @Override // java.net.DatagramSocket
    public void setBroadcast(boolean on) throws SocketException {
        setBooleanOption(StandardSocketOptions.SO_BROADCAST, on);
    }

    @Override // java.net.DatagramSocket
    public boolean getBroadcast() throws SocketException {
        return getBooleanOption(StandardSocketOptions.SO_BROADCAST);
    }

    @Override // java.net.DatagramSocket
    public void setTrafficClass(int tc) throws SocketException {
        setIntOption(StandardSocketOptions.IP_TOS, tc);
    }

    @Override // java.net.DatagramSocket
    public int getTrafficClass() throws SocketException {
        return getIntOption(StandardSocketOptions.IP_TOS);
    }

    @Override // java.net.DatagramSocket, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.dc.close();
        } catch (IOException x) {
            throw new Error(x);
        }
    }

    @Override // java.net.DatagramSocket
    public boolean isClosed() {
        return !this.dc.isOpen();
    }

    @Override // java.net.DatagramSocket
    public DatagramChannel getChannel() {
        return this.dc;
    }

    @Override // java.net.DatagramSocket
    public final FileDescriptor getFileDescriptor$() {
        return this.dc.fd;
    }
}
