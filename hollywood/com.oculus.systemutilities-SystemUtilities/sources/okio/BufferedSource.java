package okio;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public interface BufferedSource extends ReadableByteChannel, Source {
    Buffer buffer();

    boolean exhausted() throws IOException;

    long indexOf(byte b) throws IOException;

    boolean rangeEquals(long j, ByteString byteString) throws IOException;

    byte readByte() throws IOException;

    byte[] readByteArray(long j) throws IOException;

    ByteString readByteString(long j) throws IOException;

    long readHexadecimalUnsignedLong() throws IOException;

    int readInt() throws IOException;

    int readIntLe() throws IOException;

    short readShort() throws IOException;

    short readShortLe() throws IOException;

    String readString(Charset charset) throws IOException;

    String readUtf8LineStrict() throws IOException;

    void require(long j) throws IOException;

    void skip(long j) throws IOException;
}
