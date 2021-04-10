package java.net;

import dalvik.system.BlockGuard;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import sun.net.ConnectionResetException;

class SocketOutputStream extends FileOutputStream {
    private boolean closing = false;
    private AbstractPlainSocketImpl impl = null;
    private Socket socket = null;
    private byte[] temp = new byte[1];

    private native void socketWrite0(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2);

    SocketOutputStream(AbstractPlainSocketImpl abstractPlainSocketImpl) {
        super(abstractPlainSocketImpl.getFileDescriptor());
        this.impl = abstractPlainSocketImpl;
        this.socket = abstractPlainSocketImpl.getSocket();
    }

    private void socketWrite(byte[] bArr, int i, int i2) {
        if (i2 > 0 && i >= 0 && i2 <= bArr.length - i) {
            FileDescriptor acquireFD = this.impl.acquireFD();
            try {
                BlockGuard.getThreadPolicy().onNetwork();
                socketWrite0(acquireFD, bArr, i, i2);
                this.impl.releaseFD();
            } catch (SocketException e) {
                e = e;
                if (e instanceof ConnectionResetException) {
                    this.impl.setConnectionResetPending();
                    e = new SocketException("Connection reset");
                }
                if (this.impl.isClosedOrPending()) {
                    throw new SocketException("Socket closed");
                }
                throw e;
            } catch (Throwable th) {
                this.impl.releaseFD();
                throw th;
            }
        } else if (i2 != 0) {
            throw new ArrayIndexOutOfBoundsException("len == " + i2 + " off == " + i + " buffer length == " + bArr.length);
        }
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public void write(int i) {
        byte[] bArr = this.temp;
        bArr[0] = (byte) i;
        socketWrite(bArr, 0, 1);
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public void write(byte[] bArr) {
        socketWrite(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public void write(byte[] bArr, int i, int i2) {
        socketWrite(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream, java.lang.AutoCloseable
    public void close() {
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
}
