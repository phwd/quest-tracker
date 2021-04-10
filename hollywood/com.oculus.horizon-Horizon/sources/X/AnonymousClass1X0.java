package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1X0  reason: invalid class name */
public final class AnonymousClass1X0 extends FileInputStream {
    public long A00 = 0;
    public final AnonymousClass1XS A01 = new AnonymousClass1XS();

    public AnonymousClass1X0(File file) throws FileNotFoundException {
        super(file);
    }

    @Override // java.io.Closeable, java.io.FileInputStream, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        try {
            super.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final int read() throws IOException {
        try {
            int read = super.read();
            if (read != -1) {
                this.A00++;
            }
            return read;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        try {
            int read = super.read(bArr);
            if (read != -1) {
                this.A00 += (long) read;
            }
            return read;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.A00 += (long) read;
            }
            return read;
        } catch (IOException e) {
            throw e;
        }
    }
}
