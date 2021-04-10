package java.net;

import dalvik.system.BlockGuard;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import sun.net.ConnectionResetException;

class SocketOutputStream extends FileOutputStream {
    private boolean closing = false;
    private AbstractPlainSocketImpl impl = null;
    private Socket socket = null;
    private byte[] temp = new byte[1];

    private native void socketWrite0(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) throws IOException;

    SocketOutputStream(AbstractPlainSocketImpl impl2) throws IOException {
        super(impl2.getFileDescriptor());
        this.impl = impl2;
        this.socket = impl2.getSocket();
    }

    @Override // java.io.FileOutputStream
    public final FileChannel getChannel() {
        return null;
    }

    private void socketWrite(byte[] b, int off, int len) throws IOException {
        if (len > 0 && off >= 0 && len <= b.length - off) {
            FileDescriptor fd = this.impl.acquireFD();
            try {
                BlockGuard.getThreadPolicy().onNetwork();
                socketWrite0(fd, b, off, len);
                this.impl.releaseFD();
            } catch (SocketException e) {
                se = e;
                if (se instanceof ConnectionResetException) {
                    this.impl.setConnectionResetPending();
                    se = new SocketException("Connection reset");
                }
                if (this.impl.isClosedOrPending()) {
                    throw new SocketException("Socket closed");
                }
                throw se;
            } catch (Throwable th) {
                this.impl.releaseFD();
                throw th;
            }
        } else if (len != 0) {
            throw new ArrayIndexOutOfBoundsException("len == " + len + " off == " + off + " buffer length == " + b.length);
        }
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.temp;
        bArr[0] = (byte) b;
        socketWrite(bArr, 0, 1);
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public void write(byte[] b) throws IOException {
        socketWrite(b, 0, b.length);
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        socketWrite(b, off, len);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closing) {
            this.closing = true;
            Socket socket2 = this.socket;
            if (socket2 == null) {
                this.impl.close();
            } else if (!socket2.isClosed()) {
                this.socket.close();
            }
            this.closing = false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.io.FileOutputStream
    public void finalize() {
    }
}
