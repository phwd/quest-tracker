package X;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: X.1iY  reason: invalid class name */
public interface AnonymousClass1iY {
    void close();

    void copy(int i, AnonymousClass1iY v, int i2, int i3);

    @Nullable
    ByteBuffer getByteBuffer();

    long getNativePtr() throws UnsupportedOperationException;

    int getSize();

    long getUniqueId();

    boolean isClosed();

    byte read(int i);

    int read(int i, byte[] bArr, int i2, int i3);

    int write(int i, byte[] bArr, int i2, int i3);
}
