package sun.nio.ch;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.SelectableChannel;

public class ChannelInputStream extends InputStream {
    private byte[] b1 = null;
    private ByteBuffer bb = null;
    private byte[] bs = null;
    protected final ReadableByteChannel ch;

    public static int read(ReadableByteChannel ch2, ByteBuffer bb2, boolean block) throws IOException {
        int n;
        if (!(ch2 instanceof SelectableChannel)) {
            return ch2.read(bb2);
        }
        SelectableChannel sc = (SelectableChannel) ch2;
        synchronized (sc.blockingLock()) {
            boolean bm = sc.isBlocking();
            if (bm) {
                if (bm != block) {
                    sc.configureBlocking(block);
                }
                n = ch2.read(bb2);
                if (bm != block) {
                    sc.configureBlocking(bm);
                }
            } else {
                throw new IllegalBlockingModeException();
            }
        }
        return n;
    }

    public ChannelInputStream(ReadableByteChannel ch2) {
        this.ch = ch2;
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (this.b1 == null) {
            this.b1 = new byte[1];
        }
        if (read(this.b1) != 1) {
            return -1;
        }
        return this.b1[0] & 255;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bs2, int off, int len) throws IOException {
        ByteBuffer bb2;
        if (off >= 0) {
            if (off <= bs2.length && len >= 0 && off + len <= bs2.length && off + len >= 0) {
                if (len == 0) {
                    return 0;
                }
                if (this.bs == bs2) {
                    bb2 = this.bb;
                } else {
                    bb2 = ByteBuffer.wrap(bs2);
                }
                bb2.limit(Math.min(off + len, bb2.capacity()));
                bb2.position(off);
                this.bb = bb2;
                this.bs = bs2;
                return read(bb2);
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: protected */
    public int read(ByteBuffer bb2) throws IOException {
        return read(this.ch, bb2, true);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        ReadableByteChannel readableByteChannel = this.ch;
        if (!(readableByteChannel instanceof SeekableByteChannel)) {
            return 0;
        }
        SeekableByteChannel sbc = (SeekableByteChannel) readableByteChannel;
        long rem = Math.max(0L, sbc.size() - sbc.position());
        if (rem > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) rem;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        this.ch.close();
    }
}
