package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

public interface KJ extends AbstractC0313cc, WritableByteChannel {
    AnonymousClass98 A1V();

    KJ A1u() throws IOException;

    KJ A1v() throws IOException;

    OutputStream A48();

    KJ A5m(ci ciVar) throws IOException;

    KJ A5n(byte[] bArr) throws IOException;

    KJ A5o(byte[] bArr, int i, int i2) throws IOException;

    long A5p(AbstractC0312cb cbVar) throws IOException;

    KJ A5q(int i) throws IOException;

    KJ A5r(long j) throws IOException;

    KJ A5s(long j) throws IOException;

    KJ A5t(int i) throws IOException;

    KJ A5u(int i) throws IOException;

    KJ A5x(int i) throws IOException;

    KJ A5y(String str) throws IOException;

    @Override // X.AbstractC0313cc, java.io.Flushable
    void flush() throws IOException;
}
