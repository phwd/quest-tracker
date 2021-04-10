package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0JY  reason: invalid class name */
public abstract class AnonymousClass0JY extends OutputStream {
    public abstract int A00();

    public abstract AnonymousClass0JV A01();

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            C00770Im.A00(e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
