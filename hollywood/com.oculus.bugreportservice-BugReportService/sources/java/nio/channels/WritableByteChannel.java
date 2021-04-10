package java.nio.channels;

import java.nio.ByteBuffer;

public interface WritableByteChannel extends Channel {
    int write(ByteBuffer byteBuffer);
}
