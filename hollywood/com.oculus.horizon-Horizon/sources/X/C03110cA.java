package X;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import javax.annotation.Nullable;

/* renamed from: X.0cA  reason: invalid class name and case insensitive filesystem */
public final class C03110cA implements Closeable {
    public final FileOutputStream A00;
    @Nullable
    public final FileLock A01;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            FileLock fileLock = this.A01;
            if (fileLock != null) {
                fileLock.release();
            }
        } finally {
            this.A00.close();
        }
    }

    public C03110cA(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        this.A00 = fileOutputStream;
        try {
            FileLock lock = fileOutputStream.getChannel().lock();
            if (lock == null) {
            }
            this.A01 = lock;
        } finally {
            this.A00.close();
        }
    }
}
