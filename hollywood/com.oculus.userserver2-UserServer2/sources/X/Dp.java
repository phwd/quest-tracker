package X;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public interface Dp extends WF, ReadableByteChannel {
    AnonymousClass8k A16();

    boolean A1V() throws IOException;

    long A27(byte b) throws IOException;

    InputStream A28();

    boolean A2y(long j, WM wm) throws IOException;

    byte[] A31() throws IOException;

    byte[] A32(long j) throws IOException;

    WM A33(long j) throws IOException;

    long A34() throws IOException;

    long A35() throws IOException;

    int A36() throws IOException;

    short A39() throws IOException;

    String A3A(Charset charset) throws IOException;

    String A3B() throws IOException;

    void A3N(long j) throws IOException;

    void A3i(long j) throws IOException;

    byte readByte() throws IOException;

    int readInt() throws IOException;

    short readShort() throws IOException;
}
