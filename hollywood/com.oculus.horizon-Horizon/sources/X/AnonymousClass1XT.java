package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1XT  reason: invalid class name */
public final class AnonymousClass1XT extends FileOutputStream {
    public long A00;
    public final AnonymousClass1XS A01 = new AnonymousClass1XS();

    public AnonymousClass1XT(File file) throws FileNotFoundException {
        super(file, false);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            super.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        try {
            super.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public final void write(int i) throws IOException {
        try {
            super.write(i);
            this.A00++;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public final void write(byte[] bArr) throws IOException {
        try {
            super.write(bArr);
            this.A00 += (long) bArr.length;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.FileOutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            super.write(bArr, i, i2);
            this.A00 += (long) i2;
        } catch (IOException e) {
            throw e;
        }
    }
}
