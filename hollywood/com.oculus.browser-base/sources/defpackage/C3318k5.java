package defpackage;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketImpl;

/* renamed from: k5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3318k5 extends SocketImpl {
    public C3318k5(FileDescriptor fileDescriptor) {
        ((SocketImpl) this).fd = fileDescriptor;
    }

    @Override // java.net.SocketImpl
    public void accept(SocketImpl socketImpl) {
        throw new RuntimeException("accept not implemented");
    }

    @Override // java.net.SocketImpl
    public int available() {
        throw new RuntimeException("accept not implemented");
    }

    @Override // java.net.SocketImpl
    public void bind(InetAddress inetAddress, int i) {
        throw new RuntimeException("accept not implemented");
    }

    @Override // java.net.SocketImpl
    public void close() {
    }

    @Override // java.net.SocketImpl
    public void connect(InetAddress inetAddress, int i) {
        throw new RuntimeException("connect not implemented");
    }

    @Override // java.net.SocketImpl
    public void create(boolean z) {
    }

    @Override // java.net.SocketImpl
    public InputStream getInputStream() {
        throw new RuntimeException("getInputStream not implemented");
    }

    @Override // java.net.SocketOptions
    public Object getOption(int i) {
        throw new RuntimeException("getOption not implemented");
    }

    @Override // java.net.SocketImpl
    public OutputStream getOutputStream() {
        throw new RuntimeException("getOutputStream not implemented");
    }

    @Override // java.net.SocketImpl
    public void listen(int i) {
        throw new RuntimeException("listen not implemented");
    }

    @Override // java.net.SocketImpl
    public void sendUrgentData(int i) {
        throw new RuntimeException("sendUrgentData not implemented");
    }

    @Override // java.net.SocketOptions
    public void setOption(int i, Object obj) {
        throw new RuntimeException("setOption not implemented");
    }

    @Override // java.net.SocketImpl
    public void connect(SocketAddress socketAddress, int i) {
        throw new RuntimeException("connect not implemented");
    }

    @Override // java.net.SocketImpl
    public void connect(String str, int i) {
        throw new RuntimeException("connect not implemented");
    }
}
