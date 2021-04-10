package java.net;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class SocketImpl implements SocketOptions {
    protected InetAddress address;
    protected FileDescriptor fd;
    protected int localport;
    protected int port;
    ServerSocket serverSocket = null;
    Socket socket = null;

    /* access modifiers changed from: protected */
    public abstract void accept(SocketImpl socketImpl);

    /* access modifiers changed from: protected */
    public abstract void bind(InetAddress inetAddress, int i);

    /* access modifiers changed from: protected */
    public abstract void close();

    /* access modifiers changed from: protected */
    public abstract void connect(String str, int i);

    /* access modifiers changed from: protected */
    public abstract void connect(InetAddress inetAddress, int i);

    /* access modifiers changed from: protected */
    public abstract void connect(SocketAddress socketAddress, int i);

    /* access modifiers changed from: protected */
    public abstract void create(boolean z);

    /* access modifiers changed from: protected */
    public abstract InputStream getInputStream();

    /* access modifiers changed from: protected */
    public abstract OutputStream getOutputStream();

    /* access modifiers changed from: protected */
    public abstract void listen(int i);

    /* access modifiers changed from: protected */
    public FileDescriptor getFileDescriptor() {
        return this.fd;
    }

    /* access modifiers changed from: protected */
    public InetAddress getInetAddress() {
        return this.address;
    }

    /* access modifiers changed from: protected */
    public int getPort() {
        return this.port;
    }

    /* access modifiers changed from: protected */
    public int getLocalPort() {
        return this.localport;
    }

    /* access modifiers changed from: package-private */
    public void setSocket(Socket socket2) {
        this.socket = socket2;
    }

    /* access modifiers changed from: package-private */
    public Socket getSocket() {
        return this.socket;
    }

    /* access modifiers changed from: package-private */
    public void setServerSocket(ServerSocket serverSocket2) {
        this.serverSocket = serverSocket2;
    }

    public String toString() {
        return "Socket[addr=" + getInetAddress() + ",port=" + getPort() + ",localport=" + getLocalPort() + "]";
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.address = null;
        this.port = 0;
        this.localport = 0;
    }
}
