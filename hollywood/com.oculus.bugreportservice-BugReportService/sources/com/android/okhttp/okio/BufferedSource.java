package com.android.okhttp.okio;

import java.io.InputStream;

public interface BufferedSource extends Source {
    Buffer buffer();

    boolean exhausted();

    long indexOf(byte b);

    InputStream inputStream();

    byte readByte();

    byte[] readByteArray(long j);

    ByteString readByteString(long j);

    long readHexadecimalUnsignedLong();

    int readInt();

    int readIntLe();

    short readShort();

    short readShortLe();

    String readUtf8LineStrict();

    void require(long j);

    void skip(long j);
}
