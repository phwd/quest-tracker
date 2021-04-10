package java.net;

import android.support.v4.media.session.PlaybackStateCompat;
import dalvik.system.BlockGuard;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import sun.net.ConnectionResetException;

class SocketInputStream extends FileInputStream {
    private boolean closing = false;
    private boolean eof;
    private AbstractPlainSocketImpl impl = null;
    private Socket socket = null;
    private byte[] temp;

    private native int socketRead0(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2, int i3) throws IOException;

    SocketInputStream(AbstractPlainSocketImpl impl2) throws IOException {
        super(impl2.getFileDescriptor());
        this.impl = impl2;
        this.socket = impl2.getSocket();
    }

    @Override // java.io.FileInputStream
    public final FileChannel getChannel() {
        return null;
    }

    private int socketRead(FileDescriptor fd, byte[] b, int off, int len, int timeout) throws IOException {
        return socketRead0(fd, b, off, len, timeout);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] b, int off, int length) throws IOException {
        return read(b, off, length, this.impl.getTimeout());
    }

    /* access modifiers changed from: package-private */
    public int read(byte[] b, int off, int length, int timeout) throws IOException {
        if (this.eof) {
            return -1;
        }
        if (this.impl.isConnectionReset()) {
            throw new SocketException("Connection reset");
        } else if (length > 0 && off >= 0 && length <= b.length - off) {
            boolean gotReset = false;
            FileDescriptor fd = this.impl.acquireFD();
            try {
                BlockGuard.getThreadPolicy().onNetwork();
                int n = socketRead(fd, b, off, length, timeout);
                if (n > 0) {
                    return n;
                }
                this.impl.releaseFD();
                if (gotReset) {
                    this.impl.setConnectionResetPending();
                    this.impl.acquireFD();
                    try {
                        int n2 = socketRead(fd, b, off, length, timeout);
                        if (n2 > 0) {
                            return n2;
                        }
                        this.impl.releaseFD();
                    } catch (ConnectionResetException e) {
                    } finally {
                        this.impl.releaseFD();
                    }
                }
                if (!this.impl.isClosedOrPending()) {
                    if (this.impl.isConnectionResetPending()) {
                        this.impl.setConnectionReset();
                    }
                    if (!this.impl.isConnectionReset()) {
                        this.eof = true;
                        return -1;
                    }
                    throw new SocketException("Connection reset");
                }
                throw new SocketException("Socket closed");
            } catch (ConnectionResetException e2) {
                gotReset = true;
            } finally {
                this.impl.releaseFD();
            }
        } else if (length == 0) {
            return 0;
        } else {
            throw new ArrayIndexOutOfBoundsException("length == " + length + " off == " + off + " buffer length == " + b.length);
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.eof) {
            return -1;
        }
        this.temp = new byte[1];
        if (read(this.temp, 0, 1) <= 0) {
            return -1;
        }
        return this.temp[0] & 255;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public long skip(long numbytes) throws IOException {
        int r;
        if (numbytes <= 0) {
            return 0;
        }
        long n = numbytes;
        int buflen = (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, n);
        byte[] data = new byte[buflen];
        while (n > 0 && (r = read(data, 0, (int) Math.min((long) buflen, n))) >= 0) {
            n -= (long) r;
        }
        return numbytes - n;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.eof) {
            return 0;
        }
        return this.impl.available();
    }

    @Override // java.io.Closeable, java.io.FileInputStream, java.lang.AutoCloseable, java.io.InputStream
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

    /* access modifiers changed from: package-private */
    public void setEOF(boolean eof2) {
        this.eof = eof2;
    }

    /* access modifiers changed from: protected */
    @Override // java.io.FileInputStream
    public void finalize() {
    }
}
