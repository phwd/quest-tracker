package X;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public interface KC extends AbstractC0312cb, ReadableByteChannel {
    AnonymousClass98 A1V();

    boolean A1z() throws IOException;

    long A34(byte b) throws IOException;

    InputStream A37();

    boolean A4P(long j, ci ciVar) throws IOException;

    byte[] A4S() throws IOException;

    byte[] A4T(long j) throws IOException;

    ci A4U(long j) throws IOException;

    long A4V() throws IOException;

    long A4W() throws IOException;

    int A4X() throws IOException;

    short A4a() throws IOException;

    String A4b(Charset charset) throws IOException;

    String A4c() throws IOException;

    void A4l(long j) throws IOException;

    void A5F(long j) throws IOException;

    byte readByte() throws IOException;

    int readInt() throws IOException;

    short readShort() throws IOException;
}
