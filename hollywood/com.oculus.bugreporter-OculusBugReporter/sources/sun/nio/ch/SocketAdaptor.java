package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.net.SocketTimeoutException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public class SocketAdaptor extends Socket {
    private final SocketChannelImpl sc;
    private InputStream socketInputStream = null;
    private volatile int timeout = 0;

    private SocketAdaptor(SocketChannelImpl sc2) throws SocketException {
        super(new FileDescriptorHolderSocketImpl(sc2.getFD()));
        this.sc = sc2;
    }

    public static Socket create(SocketChannelImpl sc2) {
        try {
            return new SocketAdaptor(sc2);
        } catch (SocketException e) {
            throw new InternalError("Should not reach here");
        }
    }

    @Override // java.net.Socket
    public SocketChannel getChannel() {
        return this.sc;
    }

    @Override // java.net.Socket
    public void connect(SocketAddress remote) throws IOException {
        connect(remote, 0);
    }

    @Override // java.net.Socket
    public void connect(SocketAddress remote, int timeout2) throws IOException {
        if (remote == null) {
            throw new IllegalArgumentException("connect: The address can't be null");
        } else if (timeout2 >= 0) {
            synchronized (this.sc.blockingLock()) {
                if (!this.sc.isBlocking()) {
                    throw new IllegalBlockingModeException();
                } else if (timeout2 == 0) {
                    try {
                        this.sc.connect(remote);
                    } catch (Exception ex) {
                        try {
                            Net.translateException(ex);
                        } catch (Exception x) {
                            Net.translateException(x, true);
                        }
                    }
                } else {
                    this.sc.configureBlocking(false);
                    try {
                        if (!this.sc.connect(remote)) {
                            long to = (long) timeout2;
                            while (this.sc.isOpen()) {
                                long st = System.currentTimeMillis();
                                if (this.sc.poll(Net.POLLCONN, to) <= 0 || !this.sc.finishConnect()) {
                                    to -= System.currentTimeMillis() - st;
                                    if (to <= 0) {
                                        try {
                                            this.sc.close();
                                        } catch (IOException e) {
                                        }
                                        throw new SocketTimeoutException();
                                    }
                                } else {
                                    if (this.sc.isOpen()) {
                                        this.sc.configureBlocking(true);
                                    }
                                    return;
                                }
                            }
                            throw new ClosedChannelException();
                        }
                    } finally {
                        if (this.sc.isOpen()) {
                            this.sc.configureBlocking(true);
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("connect: timeout can't be negative");
        }
    }

    @Override // java.net.Socket
    public void bind(SocketAddress local) throws IOException {
        try {
            this.sc.bind(local);
        } catch (Exception x) {
            Net.translateException(x);
        }
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        SocketAddress remote;
        if (isConnected() && (remote = this.sc.remoteAddress()) != null) {
            return ((InetSocketAddress) remote).getAddress();
        }
        return null;
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        InetSocketAddress local;
        if (!this.sc.isOpen() || (local = this.sc.localAddress()) == null) {
            return new InetSocketAddress(0).getAddress();
        }
        return Net.getRevealedLocalAddress(local).getAddress();
    }

    @Override // java.net.Socket
    public int getPort() {
        SocketAddress remote;
        if (isConnected() && (remote = this.sc.remoteAddress()) != null) {
            return ((InetSocketAddress) remote).getPort();
        }
        return 0;
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        SocketAddress local = this.sc.localAddress();
        if (local == null) {
            return -1;
        }
        return ((InetSocketAddress) local).getPort();
    }

    /* access modifiers changed from: private */
    public class SocketInputStream extends ChannelInputStream {
        private SocketInputStream() {
            super(SocketAdaptor.this.sc);
        }

        /* access modifiers changed from: protected */
        @Override // sun.nio.ch.ChannelInputStream
        public int read(ByteBuffer bb) throws IOException {
            int n;
            synchronized (SocketAdaptor.this.sc.blockingLock()) {
                if (!SocketAdaptor.this.sc.isBlocking()) {
                    throw new IllegalBlockingModeException();
                } else if (SocketAdaptor.this.timeout == 0) {
                    return SocketAdaptor.this.sc.read(bb);
                } else {
                    SocketAdaptor.this.sc.configureBlocking(false);
                    try {
                        int n2 = SocketAdaptor.this.sc.read(bb);
                        if (n2 != 0) {
                            return n2;
                        }
                        long to = (long) SocketAdaptor.this.timeout;
                        while (SocketAdaptor.this.sc.isOpen()) {
                            long st = System.currentTimeMillis();
                            if (SocketAdaptor.this.sc.poll(Net.POLLIN, to) <= 0 || (n = SocketAdaptor.this.sc.read(bb)) == 0) {
                                to -= System.currentTimeMillis() - st;
                                if (to <= 0) {
                                    throw new SocketTimeoutException();
                                }
                            } else {
                                if (SocketAdaptor.this.sc.isOpen()) {
                                    SocketAdaptor.this.sc.configureBlocking(true);
                                }
                                return n;
                            }
                        }
                        throw new ClosedChannelException();
                    } finally {
                        if (SocketAdaptor.this.sc.isOpen()) {
                            SocketAdaptor.this.sc.configureBlocking(true);
                        }
                    }
                }
            }
        }
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        if (!this.sc.isOpen()) {
            throw new SocketException("Socket is closed");
        } else if (!this.sc.isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (this.sc.isInputOpen()) {
            if (this.socketInputStream == null) {
                try {
                    this.socketInputStream = (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
                        /* class sun.nio.ch.SocketAdaptor.AnonymousClass1 */

                        @Override // java.security.PrivilegedExceptionAction
                        public InputStream run() throws IOException {
                            return new SocketInputStream();
                        }
                    });
                } catch (PrivilegedActionException e) {
                    throw ((IOException) e.getException());
                }
            }
            return this.socketInputStream;
        } else {
            throw new SocketException("Socket input is shutdown");
        }
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        if (!this.sc.isOpen()) {
            throw new SocketException("Socket is closed");
        } else if (!this.sc.isConnected()) {
            throw new SocketException("Socket is not connected");
        } else if (this.sc.isOutputOpen()) {
            try {
                return (OutputStream) AccessController.doPrivileged(new PrivilegedExceptionAction<OutputStream>() {
                    /* class sun.nio.ch.SocketAdaptor.AnonymousClass2 */

                    @Override // java.security.PrivilegedExceptionAction
                    public OutputStream run() throws IOException {
                        return Channels.newOutputStream(SocketAdaptor.this.sc);
                    }
                });
            } catch (PrivilegedActionException e) {
                throw ((IOException) e.getException());
            }
        } else {
            throw new SocketException("Socket output is shutdown");
        }
    }

    private void setBooleanOption(SocketOption<Boolean> name, boolean value) throws SocketException {
        try {
            this.sc.setOption((SocketOption) name, (Object) Boolean.valueOf(value));
        } catch (IOException x) {
            Net.translateToSocketException(x);
        }
    }

    private void setIntOption(SocketOption<Integer> name, int value) throws SocketException {
        try {
            this.sc.setOption((SocketOption) name, (Object) Integer.valueOf(value));
        } catch (IOException x) {
            Net.translateToSocketException(x);
        }
    }

    private boolean getBooleanOption(SocketOption<Boolean> name) throws SocketException {
        try {
            return ((Boolean) this.sc.getOption(name)).booleanValue();
        } catch (IOException x) {
            Net.translateToSocketException(x);
            return false;
        }
    }

    private int getIntOption(SocketOption<Integer> name) throws SocketException {
        try {
            return ((Integer) this.sc.getOption(name)).intValue();
        } catch (IOException x) {
            Net.translateToSocketException(x);
            return -1;
        }
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean on) throws SocketException {
        setBooleanOption(StandardSocketOptions.TCP_NODELAY, on);
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() throws SocketException {
        return getBooleanOption(StandardSocketOptions.TCP_NODELAY);
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean on, int linger) throws SocketException {
        if (!on) {
            linger = -1;
        }
        setIntOption(StandardSocketOptions.SO_LINGER, linger);
    }

    @Override // java.net.Socket
    public int getSoLinger() throws SocketException {
        return getIntOption(StandardSocketOptions.SO_LINGER);
    }

    @Override // java.net.Socket
    public void sendUrgentData(int data) throws IOException {
        if (this.sc.sendOutOfBandData((byte) data) == 0) {
            throw new IOException("Socket buffer full");
        }
    }

    @Override // java.net.Socket
    public void setOOBInline(boolean on) throws SocketException {
        setBooleanOption(ExtendedSocketOption.SO_OOBINLINE, on);
    }

    @Override // java.net.Socket
    public boolean getOOBInline() throws SocketException {
        return getBooleanOption(ExtendedSocketOption.SO_OOBINLINE);
    }

    @Override // java.net.Socket
    public void setSoTimeout(int timeout2) throws SocketException {
        if (timeout2 >= 0) {
            this.timeout = timeout2;
            return;
        }
        throw new IllegalArgumentException("timeout can't be negative");
    }

    @Override // java.net.Socket
    public int getSoTimeout() throws SocketException {
        return this.timeout;
    }

    @Override // java.net.Socket
    public void setSendBufferSize(int size) throws SocketException {
        if (size > 0) {
            setIntOption(StandardSocketOptions.SO_SNDBUF, size);
            return;
        }
        throw new IllegalArgumentException("Invalid send size");
    }

    @Override // java.net.Socket
    public int getSendBufferSize() throws SocketException {
        return getIntOption(StandardSocketOptions.SO_SNDBUF);
    }

    @Override // java.net.Socket
    public void setReceiveBufferSize(int size) throws SocketException {
        if (size > 0) {
            setIntOption(StandardSocketOptions.SO_RCVBUF, size);
            return;
        }
        throw new IllegalArgumentException("Invalid receive size");
    }

    @Override // java.net.Socket
    public int getReceiveBufferSize() throws SocketException {
        return getIntOption(StandardSocketOptions.SO_RCVBUF);
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean on) throws SocketException {
        setBooleanOption(StandardSocketOptions.SO_KEEPALIVE, on);
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() throws SocketException {
        return getBooleanOption(StandardSocketOptions.SO_KEEPALIVE);
    }

    @Override // java.net.Socket
    public void setTrafficClass(int tc) throws SocketException {
        setIntOption(StandardSocketOptions.IP_TOS, tc);
    }

    @Override // java.net.Socket
    public int getTrafficClass() throws SocketException {
        return getIntOption(StandardSocketOptions.IP_TOS);
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean on) throws SocketException {
        setBooleanOption(StandardSocketOptions.SO_REUSEADDR, on);
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() throws SocketException {
        return getBooleanOption(StandardSocketOptions.SO_REUSEADDR);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sc.close();
    }

    @Override // java.net.Socket
    public void shutdownInput() throws IOException {
        try {
            this.sc.shutdownInput();
        } catch (Exception x) {
            Net.translateException(x);
        }
    }

    @Override // java.net.Socket
    public void shutdownOutput() throws IOException {
        try {
            this.sc.shutdownOutput();
        } catch (Exception x) {
            Net.translateException(x);
        }
    }

    @Override // java.net.Socket
    public String toString() {
        if (!this.sc.isConnected()) {
            return "Socket[unconnected]";
        }
        return "Socket[addr=" + ((Object) getInetAddress()) + ",port=" + getPort() + ",localport=" + getLocalPort() + "]";
    }

    @Override // java.net.Socket
    public boolean isConnected() {
        return this.sc.isConnected();
    }

    @Override // java.net.Socket
    public boolean isBound() {
        return this.sc.localAddress() != null;
    }

    @Override // java.net.Socket
    public boolean isClosed() {
        return !this.sc.isOpen();
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return !this.sc.isInputOpen();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return !this.sc.isOutputOpen();
    }

    @Override // java.net.Socket
    public FileDescriptor getFileDescriptor$() {
        return this.sc.getFD();
    }
}
