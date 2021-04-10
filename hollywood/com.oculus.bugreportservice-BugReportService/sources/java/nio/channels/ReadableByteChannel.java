package java.nio.channels;

import java.nio.ByteBuffer;

public interface ReadableByteChannel extends Channel {
    int read(ByteBuffer byteBuffer);
}
