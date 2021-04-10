package java.nio.channels;

import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class Channels {
    private static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException("\"" + str + "\" is null!");
        }
    }

    private static void writeFullyImpl(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer) {
        while (byteBuffer.remaining() > 0) {
            if (writableByteChannel.write(byteBuffer) <= 0) {
                throw new RuntimeException("no bytes written");
            }
        }
    }

    /* access modifiers changed from: private */
    public static void writeFully(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer) {
        if (writableByteChannel instanceof SelectableChannel) {
            SelectableChannel selectableChannel = (SelectableChannel) writableByteChannel;
            synchronized (selectableChannel.blockingLock()) {
                if (selectableChannel.isBlocking()) {
                    writeFullyImpl(writableByteChannel, byteBuffer);
                } else {
                    throw new IllegalBlockingModeException();
                }
            }
            return;
        }
        writeFullyImpl(writableByteChannel, byteBuffer);
    }

    public static OutputStream newOutputStream(final WritableByteChannel writableByteChannel) {
        checkNotNull(writableByteChannel, "ch");
        return new OutputStream() {
            /* class java.nio.channels.Channels.AnonymousClass1 */
            private byte[] b1 = null;
            private ByteBuffer bb = null;
            private byte[] bs = null;

            @Override // java.io.OutputStream
            public synchronized void write(int i) {
                if (this.b1 == null) {
                    this.b1 = new byte[1];
                }
                this.b1[0] = (byte) i;
                write(this.b1);
            }

            @Override // java.io.OutputStream
            public synchronized void write(byte[] bArr, int i, int i2) {
                int i3;
                ByteBuffer byteBuffer;
                if (i >= 0) {
                    if (i <= bArr.length && i2 >= 0 && (i3 = i + i2) <= bArr.length && i3 >= 0) {
                        if (i2 != 0) {
                            if (this.bs == bArr) {
                                byteBuffer = this.bb;
                            } else {
                                byteBuffer = ByteBuffer.wrap(bArr);
                            }
                            byteBuffer.limit(Math.min(i3, byteBuffer.capacity()));
                            byteBuffer.position(i);
                            this.bb = byteBuffer;
                            this.bs = bArr;
                            Channels.writeFully(WritableByteChannel.this, byteBuffer);
                            return;
                        }
                        return;
                    }
                }
                throw new IndexOutOfBoundsException();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                WritableByteChannel.this.close();
            }
        };
    }
}
