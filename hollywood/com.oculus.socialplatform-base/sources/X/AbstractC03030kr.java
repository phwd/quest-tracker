package X;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* renamed from: X.0kr  reason: invalid class name and case insensitive filesystem */
public interface AbstractC03030kr extends ByteChannel {
    AbstractC03030kr A8R(long j) throws IOException;

    @Override // java.nio.channels.ReadableByteChannel
    int read(ByteBuffer byteBuffer) throws IOException;
}
