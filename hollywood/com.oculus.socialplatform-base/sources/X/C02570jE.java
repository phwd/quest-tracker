package X;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* renamed from: X.0jE  reason: invalid class name and case insensitive filesystem */
public final class C02570jE implements AbstractC03030kr {
    public FileChannel A00;

    @Override // X.AbstractC03030kr
    public final AbstractC03030kr A8R(long j) throws IOException {
        this.A00.position(j);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        this.A00.close();
    }

    public final boolean isOpen() {
        return this.A00.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel, X.AbstractC03030kr
    public final int read(ByteBuffer byteBuffer) throws IOException {
        return this.A00.read(byteBuffer);
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        return this.A00.write(byteBuffer);
    }

    public C02570jE(FileChannel fileChannel) {
        if (fileChannel != null) {
            this.A00 = fileChannel;
            return;
        }
        throw new IllegalArgumentException("FileChannel cannot be null");
    }
}
