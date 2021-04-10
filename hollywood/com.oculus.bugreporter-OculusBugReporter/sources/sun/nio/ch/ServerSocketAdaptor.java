package sun.nio.ch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.net.SocketTimeoutException;
import java.net.StandardSocketOptions;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketAdaptor extends ServerSocket {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ServerSocketChannelImpl ssc;
    private volatile int timeout = 0;

    public static ServerSocket create(ServerSocketChannelImpl ssc2) {
        try {
            return new ServerSocketAdaptor(ssc2);
        } catch (IOException x) {
            throw new Error(x);
        }
    }

    private ServerSocketAdaptor(ServerSocketChannelImpl ssc2) throws IOException {
        this.ssc = ssc2;
    }

    @Override // java.net.ServerSocket
    public void bind(SocketAddress local) throws IOException {
        bind(local, 50);
    }

    @Override // java.net.ServerSocket
    public void bind(SocketAddress local, int backlog) throws IOException {
        if (local == null) {
            local = new InetSocketAddress(0);
        }
        try {
            this.ssc.bind(local, backlog);
        } catch (Exception x) {
            Net.translateException(x);
        }
    }

    @Override // java.net.ServerSocket
    public InetAddress getInetAddress() {
        if (!this.ssc.isBound()) {
            return null;
        }
        return Net.getRevealedLocalAddress(this.ssc.localAddress()).getAddress();
    }

    @Override // java.net.ServerSocket
    public int getLocalPort() {
        if (!this.ssc.isBound()) {
            return -1;
        }
        return Net.asInetSocketAddress(this.ssc.localAddress()).getPort();
    }

    @Override // java.net.ServerSocket
    public Socket accept() throws IOException {
        SocketChannel sc;
        synchronized (this.ssc.blockingLock()) {
            if (this.ssc.isBound()) {
                try {
                    if (this.timeout == 0) {
                        SocketChannel sc2 = this.ssc.accept();
                        if (sc2 == null) {
                            if (!this.ssc.isBlocking()) {
                                throw new IllegalBlockingModeException();
                            }
                        }
                        return sc2.socket();
                    }
                    this.ssc.configureBlocking(false);
                    try {
                        SocketChannel sc3 = this.ssc.accept();
                        if (sc3 != null) {
                            return sc3.socket();
                        }
                        long to = (long) this.timeout;
                        while (this.ssc.isOpen()) {
                            long st = System.currentTimeMillis();
                            if (this.ssc.poll(Net.POLLIN, to) <= 0 || (sc = this.ssc.accept()) == null) {
                                to -= System.currentTimeMillis() - st;
                                if (to <= 0) {
                                    throw new SocketTimeoutException();
                                }
                            } else {
                                Socket socket = sc.socket();
                                if (this.ssc.isOpen()) {
                                    this.ssc.configureBlocking(true);
                                }
                                return socket;
                            }
                        }
                        throw new ClosedChannelException();
                    } finally {
                        if (this.ssc.isOpen()) {
                            this.ssc.configureBlocking(true);
                        }
                    }
                } catch (Exception x) {
                    Net.translateException(x);
                    return null;
                }
            } else {
                throw new IllegalBlockingModeException();
            }
        }
    }

    @Override // java.net.ServerSocket, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ssc.close();
    }

    @Override // java.net.ServerSocket
    public ServerSocketChannel getChannel() {
        return this.ssc;
    }

    @Override // java.net.ServerSocket
    public boolean isBound() {
        return this.ssc.isBound();
    }

    @Override // java.net.ServerSocket
    public boolean isClosed() {
        return !this.ssc.isOpen();
    }

    @Override // java.net.ServerSocket
    public void setSoTimeout(int timeout2) throws SocketException {
        this.timeout = timeout2;
    }

    @Override // java.net.ServerSocket
    public int getSoTimeout() throws SocketException {
        return this.timeout;
    }

    @Override // java.net.ServerSocket
    public void setReuseAddress(boolean on) throws SocketException {
        try {
            this.ssc.setOption((SocketOption) StandardSocketOptions.SO_REUSEADDR, (Object) Boolean.valueOf(on));
        } catch (IOException x) {
            Net.translateToSocketException(x);
        }
    }

    @Override // java.net.ServerSocket
    public boolean getReuseAddress() throws SocketException {
        try {
            return ((Boolean) this.ssc.getOption(StandardSocketOptions.SO_REUSEADDR)).booleanValue();
        } catch (IOException x) {
            Net.translateToSocketException(x);
            return false;
        }
    }

    @Override // java.net.ServerSocket
    public String toString() {
        if (!isBound()) {
            return "ServerSocket[unbound]";
        }
        return "ServerSocket[addr=" + ((Object) getInetAddress()) + ",localport=" + getLocalPort() + "]";
    }

    @Override // java.net.ServerSocket
    public void setReceiveBufferSize(int size) throws SocketException {
        if (size > 0) {
            try {
                this.ssc.setOption((SocketOption) StandardSocketOptions.SO_RCVBUF, (Object) Integer.valueOf(size));
            } catch (IOException x) {
                Net.translateToSocketException(x);
            }
        } else {
            throw new IllegalArgumentException("size cannot be 0 or negative");
        }
    }

    @Override // java.net.ServerSocket
    public int getReceiveBufferSize() throws SocketException {
        try {
            return ((Integer) this.ssc.getOption(StandardSocketOptions.SO_RCVBUF)).intValue();
        } catch (IOException x) {
            Net.translateToSocketException(x);
            return -1;
        }
    }
}
