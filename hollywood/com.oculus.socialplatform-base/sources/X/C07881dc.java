package X;

import androidx.annotation.NonNull;
import com.adobe.xmp.impl.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: X.1dc  reason: invalid class name and case insensitive filesystem */
public class C07881dc extends InputStream {
    public int A00 = -1;
    @NonNull
    public final ByteBuffer A01;

    public final synchronized void mark(int i) {
        this.A00 = this.A01.position();
    }

    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public final synchronized void reset() throws IOException {
        int i = this.A00;
        if (i != -1) {
            this.A01.position(i);
        } else {
            throw new IOException("Cannot reset to unset mark position");
        }
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.A01.remaining();
    }

    @Override // java.io.InputStream
    public final long skip(long j) throws IOException {
        ByteBuffer byteBuffer = this.A01;
        if (!byteBuffer.hasRemaining()) {
            return -1;
        }
        long min = Math.min(j, (long) available());
        byteBuffer.position((int) (((long) byteBuffer.position()) + min));
        return min;
    }

    public C07881dc(@NonNull ByteBuffer byteBuffer) {
        this.A01 = byteBuffer;
    }

    @Override // java.io.InputStream
    public final int read() {
        ByteBuffer byteBuffer = this.A01;
        if (!byteBuffer.hasRemaining()) {
            return -1;
        }
        return byteBuffer.get() & Base64.INVALID;
    }

    @Override // java.io.InputStream
    public final int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        ByteBuffer byteBuffer = this.A01;
        if (!byteBuffer.hasRemaining()) {
            return -1;
        }
        int min = Math.min(i2, available());
        byteBuffer.get(bArr, i, min);
        return min;
    }
}
