package X;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/* renamed from: X.1mA  reason: invalid class name */
public final class AnonymousClass1mA extends AnonymousClass1vC {
    public final Socket A00;

    @Override // java.net.Socket
    public final void bind(SocketAddress socketAddress) throws IOException {
        throw new IOException("Underlying tls13 is already connected.");
    }

    public final InetAddress getInetAddress() {
        return this.A00.getInetAddress();
    }

    @Override // java.net.Socket
    public final boolean getKeepAlive() throws SocketException {
        return this.A00.getKeepAlive();
    }

    public final InetAddress getLocalAddress() {
        return this.A00.getLocalAddress();
    }

    public final int getLocalPort() {
        return this.A00.getLocalPort();
    }

    public final SocketAddress getLocalSocketAddress() {
        return this.A00.getLocalSocketAddress();
    }

    @Override // java.net.Socket
    public final boolean getOOBInline() throws SocketException {
        return this.A00.getOOBInline();
    }

    public final int getPort() {
        return this.A00.getPort();
    }

    @Override // java.net.Socket
    public final int getReceiveBufferSize() throws SocketException {
        return this.A00.getReceiveBufferSize();
    }

    public final SocketAddress getRemoteSocketAddress() {
        return this.A00.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public final boolean getReuseAddress() throws SocketException {
        return this.A00.getReuseAddress();
    }

    @Override // java.net.Socket
    public final int getSendBufferSize() throws SocketException {
        return this.A00.getSendBufferSize();
    }

    @Override // java.net.Socket
    public final int getSoLinger() throws SocketException {
        return this.A00.getSoLinger();
    }

    @Override // java.net.Socket
    public final int getSoTimeout() throws SocketException {
        return this.A00.getSoTimeout();
    }

    @Override // java.net.Socket
    public final boolean getTcpNoDelay() throws SocketException {
        return this.A00.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public final int getTrafficClass() throws SocketException {
        return this.A00.getTrafficClass();
    }

    public final boolean isBound() {
        return this.A00.isBound();
    }

    @Override // X.AnonymousClass1vC
    public final boolean isClosed() {
        return this.A00.isClosed();
    }

    public final boolean isConnected() {
        return this.A00.isConnected();
    }

    public final boolean isInputShutdown() {
        return this.A00.isInputShutdown();
    }

    public final boolean isOutputShutdown() {
        return this.A00.isOutputShutdown();
    }

    @Override // java.net.Socket
    public final void setKeepAlive(boolean z) throws SocketException {
        this.A00.setKeepAlive(z);
    }

    @Override // java.net.Socket
    public final void setReceiveBufferSize(int i) throws SocketException {
        this.A00.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setReuseAddress(boolean z) throws SocketException {
        this.A00.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public final void setSendBufferSize(int i) throws SocketException {
        this.A00.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setSoLinger(boolean z, int i) throws SocketException {
        this.A00.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public final void setSoTimeout(int i) throws SocketException {
        this.A00.setSoTimeout(i);
    }

    @Override // java.net.Socket
    public final void setTcpNoDelay(boolean z) throws SocketException {
        this.A00.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public final void setTrafficClass(int i) throws SocketException {
        this.A00.setTrafficClass(i);
    }

    public AnonymousClass1mA(Socket socket, String str, int i, AbstractC10791ve r6) throws IOException {
        if (socket.isConnected()) {
            this.A00 = socket;
            this.A04 = r6;
            this.A0A = str;
            super.A00 = i;
            AnonymousClass1lI A05 = r6.A05();
            if (A05 != null) {
                AnonymousClass1lH.A00 = A05;
            }
            A07();
            this.A01 = new AnonymousClass1mD(this);
            this.A02 = new AnonymousClass1mB(this);
            AnonymousClass1vF r1 = new AnonymousClass1vF();
            this.A03 = r1;
            try {
                this.A07 = new AnonymousClass1vJ(r1);
            } catch (AnonymousClass1v5 e) {
                throw new IOException(e);
            }
        } else {
            throw new SocketException("Socket is not connected.");
        }
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress) throws IOException {
        throw new IOException("Underlying tls13 is already connected.");
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress, int i) throws IOException {
        throw new IOException("Underlying tls13 is already connected.");
    }
}
