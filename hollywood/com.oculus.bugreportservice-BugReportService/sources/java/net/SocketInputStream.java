package java.net;

import dalvik.system.BlockGuard;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import sun.net.ConnectionResetException;

class SocketInputStream extends FileInputStream {
    private boolean closing = false;
    private boolean eof;
    private AbstractPlainSocketImpl impl = null;
    private Socket socket = null;
    private byte[] temp;

    private native int socketRead0(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2, int i3);

    @Override // java.io.FileInputStream
    public final FileChannel getChannel() {
        return null;
    }

    SocketInputStream(AbstractPlainSocketImpl abstractPlainSocketImpl) {
        super(abstractPlainSocketImpl.getFileDescriptor());
        this.impl = abstractPlainSocketImpl;
        this.socket = abstractPlainSocketImpl.getSocket();
    }

    private int socketRead(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2, int i3) {
        return socketRead0(fileDescriptor, bArr, i, i2, i3);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return read(bArr, i, i2, this.impl.getTimeout());
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public int read(byte[] bArr, int i, int i2, int i3) {
        if (this.eof) {
            return -1;
        }
        if (!this.impl.isConnectionReset()) {
            boolean z = false;
            if (i2 > 0 && i >= 0 && i2 <= bArr.length - i) {
                FileDescriptor acquireFD = this.impl.acquireFD();
                try {
                    BlockGuard.getThreadPolicy().onNetwork();
                    int socketRead = socketRead(acquireFD, bArr, i, i2, i3);
                    if (socketRead > 0) {
                        this.impl.releaseFD();
                        return socketRead;
                    }
                    this.impl.releaseFD();
                    if (z) {
                        this.impl.setConnectionResetPending();
                        this.impl.acquireFD();
                        try {
                            int socketRead2 = socketRead(acquireFD, bArr, i, i2, i3);
                            if (socketRead2 > 0) {
                                this.impl.releaseFD();
                                return socketRead2;
                            }
                        } catch (ConnectionResetException unused) {
                        } catch (Throwable th) {
                            this.impl.releaseFD();
                            throw th;
                        }
                        this.impl.releaseFD();
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
                } catch (ConnectionResetException unused2) {
                    this.impl.releaseFD();
                    z = true;
                } catch (Throwable th2) {
                    this.impl.releaseFD();
                    throw th2;
                }
            } else if (i2 == 0) {
                return 0;
            } else {
                throw new ArrayIndexOutOfBoundsException("length == " + i2 + " off == " + i + " buffer length == " + bArr.length);
            }
        } else {
            throw new SocketException("Connection reset");
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() {
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
    public long skip(long j) {
        int read;
        if (j <= 0) {
            return 0;
        }
        int min = (int) Math.min(1024L, j);
        byte[] bArr = new byte[min];
        long j2 = j;
        while (j2 > 0 && (read = read(bArr, 0, (int) Math.min((long) min, j2))) >= 0) {
            j2 -= (long) read;
        }
        return j - j2;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int available() {
        if (this.eof) {
            return 0;
        }
        return this.impl.available();
    }

    @Override // java.io.Closeable, java.io.FileInputStream, java.lang.AutoCloseable, java.io.InputStream
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
