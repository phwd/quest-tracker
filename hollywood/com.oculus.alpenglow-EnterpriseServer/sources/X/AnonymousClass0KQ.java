package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0KQ  reason: invalid class name */
public final class AnonymousClass0KQ implements Closeable {
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

    public AnonymousClass0KQ(File file) throws IOException {
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
