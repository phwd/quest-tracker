package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0JV  reason: invalid class name */
public interface AnonymousClass0JV extends Closeable {
    boolean isClosed();

    byte read(int i);

    int read(int i, byte[] bArr, int i2, int i3);

    int size();
}
