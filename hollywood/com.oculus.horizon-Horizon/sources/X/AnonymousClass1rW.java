package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1rW  reason: invalid class name */
public abstract class AnonymousClass1rW extends OutputStream {
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            AnonymousClass0KY.A00(e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
