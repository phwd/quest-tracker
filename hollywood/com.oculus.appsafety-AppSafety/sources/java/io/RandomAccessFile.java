package java.io;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import dalvik.system.CloseGuard;
import java.nio.channels.FileChannel;
import libcore.io.IoBridge;
import libcore.io.IoTracker;
import libcore.io.IoUtils;
import libcore.io.Libcore;
import sun.nio.ch.FileChannelImpl;

public class RandomAccessFile implements DataOutput, DataInput, Closeable {
    private static final int FLUSH_FDATASYNC = 2;
    private static final int FLUSH_FSYNC = 1;
    private static final int FLUSH_NONE = 0;
    private FileChannel channel;
    private Object closeLock;
    private volatile boolean closed;
    private FileDescriptor fd;
    private int flushAfterWrite;
    private final CloseGuard guard;
    private final IoTracker ioTracker;
    private int mode;
    private final String path;
    private boolean rw;
    private final byte[] scratch;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RandomAccessFile(String name, String mode2) throws FileNotFoundException {
        this(name != null ? new File(name) : null, mode2);
    }

    public RandomAccessFile(File file, String mode2) throws FileNotFoundException {
        this.guard = CloseGuard.get();
        this.scratch = new byte[8];
        this.flushAfterWrite = 0;
        String name = null;
        this.channel = null;
        this.closeLock = new Object();
        this.closed = false;
        this.ioTracker = new IoTracker();
        name = file != null ? file.getPath() : name;
        int imode = -1;
        if (mode2.equals("r")) {
            imode = OsConstants.O_RDONLY;
        } else if (mode2.startsWith("rw")) {
            imode = OsConstants.O_RDWR | OsConstants.O_CREAT;
            this.rw = true;
            if (mode2.length() > 2) {
                if (mode2.equals("rws")) {
                    this.flushAfterWrite = 1;
                } else if (mode2.equals("rwd")) {
                    this.flushAfterWrite = 2;
                } else {
                    imode = -1;
                }
            }
        }
        if (imode < 0) {
            throw new IllegalArgumentException("Illegal mode \"" + mode2 + "\" must be one of \"r\", \"rw\", \"rws\", or \"rwd\"");
        } else if (name == null) {
            throw new NullPointerException("file == null");
        } else if (!file.isInvalid()) {
            this.path = name;
            this.mode = imode;
            this.fd = IoBridge.open(name, imode);
            IoUtils.setFdOwner(this.fd, this);
            maybeSync();
            this.guard.open("close");
        } else {
            throw new FileNotFoundException("Invalid file path");
        }
    }

    private void maybeSync() {
        int i = this.flushAfterWrite;
        if (i == 1) {
            try {
                this.fd.sync();
            } catch (IOException e) {
            }
        } else if (i == 2) {
            try {
                Os.fdatasync(this.fd);
            } catch (ErrnoException e2) {
            }
        }
    }

    public final FileDescriptor getFD() throws IOException {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null) {
            return fileDescriptor;
        }
        throw new IOException();
    }

    public final FileChannel getChannel() {
        FileChannel fileChannel;
        synchronized (this) {
            if (this.channel == null) {
                this.channel = FileChannelImpl.open(this.fd, this.path, true, this.rw, this);
            }
            fileChannel = this.channel;
        }
        return fileChannel;
    }

    public int read() throws IOException {
        if (read(this.scratch, 0, 1) != -1) {
            return this.scratch[0] & 255;
        }
        return -1;
    }

    private int readBytes(byte[] b, int off, int len) throws IOException {
        this.ioTracker.trackIo(len, IoTracker.Mode.READ);
        return IoBridge.read(this.fd, b, off, len);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return readBytes(b, off, len);
    }

    public int read(byte[] b) throws IOException {
        return readBytes(b, 0, b.length);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] b, int off, int len) throws IOException {
        int n = 0;
        do {
            int count = read(b, off + n, len - n);
            if (count >= 0) {
                n += count;
            } else {
                throw new EOFException();
            }
        } while (n < len);
    }

    @Override // java.io.DataInput
    public int skipBytes(int n) throws IOException {
        if (n <= 0) {
            return 0;
        }
        long pos = getFilePointer();
        long len = length();
        long newpos = ((long) n) + pos;
        if (newpos > len) {
            newpos = len;
        }
        seek(newpos);
        return (int) (newpos - pos);
    }

    @Override // java.io.DataOutput
    public void write(int b) throws IOException {
        byte[] bArr = this.scratch;
        bArr[0] = (byte) (b & 255);
        write(bArr, 0, 1);
    }

    private void writeBytes(byte[] b, int off, int len) throws IOException {
        this.ioTracker.trackIo(len, IoTracker.Mode.WRITE);
        IoBridge.write(this.fd, b, off, len);
        maybeSync();
    }

    @Override // java.io.DataOutput
    public void write(byte[] b) throws IOException {
        writeBytes(b, 0, b.length);
    }

    @Override // java.io.DataOutput
    public void write(byte[] b, int off, int len) throws IOException {
        writeBytes(b, off, len);
    }

    public long getFilePointer() throws IOException {
        try {
            return Libcore.os.lseek(this.fd, 0, OsConstants.SEEK_CUR);
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
    }

    public void seek(long pos) throws IOException {
        if (pos >= 0) {
            try {
                Libcore.os.lseek(this.fd, pos, OsConstants.SEEK_SET);
                this.ioTracker.reset();
            } catch (ErrnoException errnoException) {
                throw errnoException.rethrowAsIOException();
            }
        } else {
            throw new IOException("offset < 0: " + pos);
        }
    }

    public long length() throws IOException {
        try {
            return Libcore.os.fstat(this.fd).st_size;
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
    }

    public void setLength(long newLength) throws IOException {
        if (newLength >= 0) {
            try {
                Libcore.os.ftruncate(this.fd, newLength);
                if (getFilePointer() > newLength) {
                    seek(newLength);
                }
                maybeSync();
            } catch (ErrnoException errnoException) {
                throw errnoException.rethrowAsIOException();
            }
        } else {
            throw new IllegalArgumentException("newLength < 0");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r0 == null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r0.isOpen() == false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r2.channel.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        libcore.io.IoBridge.closeAndSignalBlockedThreads(r2.fd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r0 = r2.channel;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r2 = this;
            dalvik.system.CloseGuard r0 = r2.guard
            r0.close()
            java.lang.Object r0 = r2.closeLock
            monitor-enter(r0)
            boolean r1 = r2.closed     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return
        L_0x000e:
            r1 = 1
            r2.closed = r1     // Catch:{ all -> 0x0027 }
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            java.nio.channels.FileChannel r0 = r2.channel
            if (r0 == 0) goto L_0x0021
            boolean r0 = r0.isOpen()
            if (r0 == 0) goto L_0x0021
            java.nio.channels.FileChannel r0 = r2.channel
            r0.close()
        L_0x0021:
            java.io.FileDescriptor r0 = r2.fd
            libcore.io.IoBridge.closeAndSignalBlockedThreads(r0)
            return
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.RandomAccessFile.close():void");
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() throws IOException {
        int ch = read();
        if (ch >= 0) {
            return ch != 0;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        int ch = read();
        if (ch >= 0) {
            return (byte) ch;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws IOException {
        int ch = read();
        if (ch >= 0) {
            return ch;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) >= 0) {
            return (short) ((ch1 << 8) + (ch2 << 0));
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) >= 0) {
            return (ch1 << 8) + (ch2 << 0);
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final char readChar() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) >= 0) {
            return (char) ((ch1 << 8) + (ch2 << 0));
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        int ch1 = read();
        int ch2 = read();
        int ch3 = read();
        int ch4 = read();
        if ((ch1 | ch2 | ch3 | ch4) >= 0) {
            return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        return (((long) readInt()) << 32) + (((long) readInt()) & 4294967295L);
    }

    @Override // java.io.DataInput
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final String readLine() throws IOException {
        StringBuffer input = new StringBuffer();
        int c = -1;
        boolean eol = false;
        while (!eol) {
            int read = read();
            c = read;
            if (read == -1 || read == 10) {
                eol = true;
            } else if (read != 13) {
                input.append((char) c);
            } else {
                eol = true;
                long cur = getFilePointer();
                if (read() != 10) {
                    seek(cur);
                }
            }
        }
        if (c == -1 && input.length() == 0) {
            return null;
        }
        return input.toString();
    }

    @Override // java.io.DataInput
    public final String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }

    @Override // java.io.DataOutput
    public final void writeBoolean(boolean v) throws IOException {
        write(v ? 1 : 0);
    }

    @Override // java.io.DataOutput
    public final void writeByte(int v) throws IOException {
        write(v);
    }

    @Override // java.io.DataOutput
    public final void writeShort(int v) throws IOException {
        write((v >>> 8) & 255);
        write((v >>> 0) & 255);
    }

    @Override // java.io.DataOutput
    public final void writeChar(int v) throws IOException {
        write((v >>> 8) & 255);
        write((v >>> 0) & 255);
    }

    @Override // java.io.DataOutput
    public final void writeInt(int v) throws IOException {
        write((v >>> 24) & 255);
        write((v >>> 16) & 255);
        write((v >>> 8) & 255);
        write((v >>> 0) & 255);
    }

    @Override // java.io.DataOutput
    public final void writeLong(long v) throws IOException {
        write(((int) (v >>> 56)) & 255);
        write(((int) (v >>> 48)) & 255);
        write(((int) (v >>> 40)) & 255);
        write(((int) (v >>> 32)) & 255);
        write(((int) (v >>> 24)) & 255);
        write(((int) (v >>> 16)) & 255);
        write(((int) (v >>> 8)) & 255);
        write(((int) (v >>> 0)) & 255);
    }

    @Override // java.io.DataOutput
    public final void writeFloat(float v) throws IOException {
        writeInt(Float.floatToIntBits(v));
    }

    @Override // java.io.DataOutput
    public final void writeDouble(double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }

    @Override // java.io.DataOutput
    public final void writeBytes(String s) throws IOException {
        int len = s.length();
        byte[] b = new byte[len];
        s.getBytes(0, len, b, 0);
        writeBytes(b, 0, len);
    }

    @Override // java.io.DataOutput
    public final void writeChars(String s) throws IOException {
        int clen = s.length();
        int blen = clen * 2;
        byte[] b = new byte[blen];
        char[] c = new char[clen];
        s.getChars(0, clen, c, 0);
        int j = 0;
        for (int i = 0; i < clen; i++) {
            int j2 = j + 1;
            b[j] = (byte) (c[i] >>> '\b');
            j = j2 + 1;
            b[j2] = (byte) (c[i] >>> 0);
        }
        writeBytes(b, 0, blen);
    }

    @Override // java.io.DataOutput
    public final void writeUTF(String str) throws IOException {
        DataOutputStream.writeUTF(str, this);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.guard != null) {
                this.guard.warnIfOpen();
            }
            close();
        } finally {
            super.finalize();
        }
    }
}
