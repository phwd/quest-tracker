package X;

import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0io  reason: invalid class name and case insensitive filesystem */
public class C02460io implements AnonymousClass0l9 {
    public final AnonymousClass0l7 A00;
    public final InputStream A01;

    @Override // X.AnonymousClass0l9
    public final void ABA(DataOutput dataOutput, byte[] bArr) throws IOException {
        InputStream inputStream = this.A01;
        int i = 0;
        do {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, Integer.MAX_VALUE - i));
            if (read != -1) {
                dataOutput.write(bArr, 0, read);
                i += read;
            } else {
                return;
            }
        } while (i < Integer.MAX_VALUE);
    }

    @Override // X.AnonymousClass0l9
    public final int available() throws IOException {
        return this.A01.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    @Override // X.AnonymousClass0l9
    public final String getFileName() {
        return this.A00.A01;
    }

    public C02460io(AnonymousClass0l7 r1, InputStream inputStream) {
        this.A00 = r1;
        this.A01 = inputStream;
    }

    @Override // X.AnonymousClass0l9
    public final AnonymousClass0l7 A3q() {
        return this.A00;
    }
}
