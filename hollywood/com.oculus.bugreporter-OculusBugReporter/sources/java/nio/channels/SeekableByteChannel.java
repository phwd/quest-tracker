package java.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface SeekableByteChannel extends ByteChannel {
    long position() throws IOException;

    SeekableByteChannel position(long j) throws IOException;

    @Override // java.nio.channels.ReadableByteChannel
    int read(ByteBuffer byteBuffer) throws IOException;

    long size() throws IOException;

    SeekableByteChannel truncate(long j) throws IOException;

    @Override // java.nio.channels.WritableByteChannel
    int write(ByteBuffer byteBuffer) throws IOException;
}
