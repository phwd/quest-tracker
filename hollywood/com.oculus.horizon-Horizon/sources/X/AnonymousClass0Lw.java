package X;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* renamed from: X.0Lw  reason: invalid class name */
public interface AnonymousClass0Lw extends AbstractC07630v6, ReadableByteChannel {
    AnonymousClass0B3 A1V();

    boolean A2a() throws IOException;

    long A4n(byte b) throws IOException;

    InputStream A4q();

    boolean A7n(long j, C07700vD v) throws IOException;

    byte[] A7r() throws IOException;

    byte[] A7s(long j) throws IOException;

    C07700vD A7t(long j) throws IOException;

    long A7u() throws IOException;

    long A7v() throws IOException;

    int A7w() throws IOException;

    short A7z() throws IOException;

    String A80(Charset charset) throws IOException;

    String A81() throws IOException;

    void A8I(long j) throws IOException;

    void A94(long j) throws IOException;

    byte readByte() throws IOException;

    int readInt() throws IOException;

    short readShort() throws IOException;
}
