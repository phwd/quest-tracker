package X;

import android.media.MediaDataSource;
import java.io.IOException;

/* renamed from: X.1hR  reason: invalid class name and case insensitive filesystem */
public class C09251hR extends MediaDataSource {
    public long A00;
    public final /* synthetic */ AnonymousClass1hQ A01;
    public final /* synthetic */ AnonymousClass1hO A02;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    @Override // android.media.MediaDataSource
    public final long getSize() throws IOException {
        return -1;
    }

    public C09251hR(AnonymousClass1hO r1, AnonymousClass1hQ r2) {
        this.A02 = r1;
        this.A01 = r2;
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j, byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (j < 0) {
            return -1;
        }
        try {
            long j2 = this.A00;
            if (j2 != j) {
                if (j2 >= 0 && j >= j2 + ((long) this.A01.available())) {
                    return -1;
                }
                this.A01.A00(j);
                this.A00 = j;
            }
            AnonymousClass1hQ r1 = this.A01;
            if (i2 > r1.available()) {
                i2 = r1.available();
            }
            int read = r1.read(bArr, i, i2);
            if (read >= 0) {
                this.A00 += (long) read;
                return read;
            }
        } catch (IOException unused) {
        }
        this.A00 = -1;
        return -1;
    }
}
