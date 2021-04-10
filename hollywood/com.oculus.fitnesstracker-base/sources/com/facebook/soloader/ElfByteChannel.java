package com.facebook.soloader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public interface ElfByteChannel extends ByteChannel {
    int read(ByteBuffer byteBuffer, long j) throws IOException;
}
