package X;

import android.media.MediaDataSource;
import java.nio.ByteBuffer;

/* renamed from: X.1db  reason: invalid class name and case insensitive filesystem */
public class C07871db extends MediaDataSource {
    public final /* synthetic */ C07861da A00;
    public final /* synthetic */ ByteBuffer A01;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    public C07871db(C07861da r1, ByteBuffer byteBuffer) {
        this.A00 = r1;
        this.A01 = byteBuffer;
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return (long) this.A01.limit();
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j, byte[] bArr, int i, int i2) {
        ByteBuffer byteBuffer = this.A01;
        if (j >= ((long) byteBuffer.limit())) {
            return -1;
        }
        byteBuffer.position((int) j);
        int min = Math.min(i2, byteBuffer.remaining());
        byteBuffer.get(bArr, i, min);
        return min;
    }
}
