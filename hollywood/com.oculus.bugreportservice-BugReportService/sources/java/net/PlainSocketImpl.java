package java.net;

import android.system.ErrnoException;
import android.system.OsConstants;
import java.io.FileDescriptor;
import libcore.io.AsynchronousCloseMonitor;
import libcore.io.IoBridge;
import libcore.io.IoUtils;
import libcore.io.Libcore;

/* access modifiers changed from: package-private */
public class PlainSocketImpl extends AbstractPlainSocketImpl {
    PlainSocketImpl() {
        this.fd = new FileDescriptor();
    }

    /* access modifiers changed from: protected */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketSetOption(int i, Object obj) {
        try {
            socketSetOption0(i, obj);
        } catch (SocketException e) {
            Socket socket = this.socket;
            if (socket == null || !socket.isConnected()) {
                throw e;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketCreate(boolean z) {
        this.fd.setInt$(IoBridge.socket(OsConstants.AF_INET6, z ? OsConstants.SOCK_STREAM : OsConstants.SOCK_DGRAM, 0).getInt$());
        IoUtils.setFdOwner(this.fd, this);
        if (this.serverSocket != null) {
            IoUtils.setBlocking(this.fd, false);
            IoBridge.setSocketOption(this.fd, 4, true);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketConnect(InetAddress inetAddress, int i, int i2) {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor == null || !fileDescriptor.valid()) {
            throw new SocketException("Socket closed");
        }
        IoBridge.connect(this.fd, inetAddress, i, i2);
        this.address = inetAddress;
        this.port = i;
        if (this.localport == 0 && !isClosedOrPending()) {
            this.localport = IoBridge.getLocalInetSocketAddress(this.fd).getPort();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketBind(InetAddress inetAddress, int i) {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor == null || !fileDescriptor.valid()) {
            throw new SocketException("Socket closed");
        }
        IoBridge.bind(this.fd, inetAddress, i);
        this.address = inetAddress;
        if (i == 0) {
            this.localport = IoBridge.getLocalInetSocketAddress(this.fd).getPort();
        } else {
            this.localport = i;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketListen(int i) {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor == null || !fileDescriptor.valid()) {
            throw new SocketException("Socket closed");
        }
        try {
            Libcore.os.listen(this.fd, i);
        } catch (ErrnoException e) {
            throw e.rethrowAsSocketException();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketAccept(SocketImpl socketImpl) {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor == null || !fileDescriptor.valid()) {
            throw new SocketException("Socket closed");
        }
        int i = this.timeout;
        if (i <= 0) {
            IoBridge.poll(this.fd, OsConstants.POLLIN | OsConstants.POLLERR, -1);
        } else {
            IoBridge.poll(this.fd, OsConstants.POLLIN | OsConstants.POLLERR, i);
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress();
        try {
            socketImpl.fd.setInt$(Libcore.os.accept(this.fd, inetSocketAddress).getInt$());
            IoUtils.setFdOwner(socketImpl.fd, socketImpl);
            socketImpl.address = inetSocketAddress.getAddress();
            socketImpl.port = inetSocketAddress.getPort();
        } catch (ErrnoException e) {
            int i2 = e.errno;
            if (i2 == OsConstants.EAGAIN) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException();
                socketTimeoutException.initCause(e);
                throw socketTimeoutException;
            } else if (i2 == OsConstants.EINVAL || i2 == OsConstants.EBADF) {
                throw new SocketException("Socket closed");
            } else {
                e.rethrowAsSocketException();
            }
        }
        socketImpl.localport = IoBridge.getLocalInetSocketAddress(socketImpl.fd).getPort();
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public int socketAvailable() {
        return IoBridge.available(this.fd);
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public void socketClose0(boolean z) {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor == null || !fileDescriptor.valid()) {
            throw new SocketException("socket already closed");
        }
        FileDescriptor fileDescriptor2 = null;
        if (z) {
            fileDescriptor2 = getMarkerFD();
        }
        if (!z || fileDescriptor2 == null) {
            IoBridge.closeAndSignalBlockedThreads(this.fd);
            return;
        }
        try {
            Libcore.os.dup2(fileDescriptor2, this.fd.getInt$());
            Libcore.os.close(fileDescriptor2);
            AsynchronousCloseMonitor.signalBlockedThreads(this.fd);
        } catch (ErrnoException unused) {
        }
    }

    private FileDescriptor getMarkerFD() {
        FileDescriptor fileDescriptor = new FileDescriptor();
        FileDescriptor fileDescriptor2 = new FileDescriptor();
        try {
            Libcore.os.socketpair(OsConstants.AF_UNIX, OsConstants.SOCK_STREAM, 0, fileDescriptor, fileDescriptor2);
            Libcore.os.shutdown(fileDescriptor, OsConstants.SHUT_RDWR);
            Libcore.os.close(fileDescriptor2);
            return fileDescriptor;
        } catch (ErrnoException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void socketSetOption0(int i, Object obj) {
        if (i != 4102) {
            IoBridge.setSocketOption(this.fd, i, obj);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.net.AbstractPlainSocketImpl
    public Object socketGetOption(int i) {
        return IoBridge.getSocketOption(this.fd, i);
    }
}
