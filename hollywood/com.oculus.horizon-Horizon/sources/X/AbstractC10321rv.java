package X;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: X.1rv  reason: invalid class name and case insensitive filesystem */
public interface AbstractC10321rv {
    void close();

    void copy(int i, AbstractC10321rv v, int i2, int i3);

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
