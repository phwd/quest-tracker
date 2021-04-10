package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

/* renamed from: X.0Oe  reason: invalid class name */
public interface AnonymousClass0Oe extends AnonymousClass0h1, WritableByteChannel {
    AnonymousClass0HR A1Z();

    AnonymousClass0Oe A2H() throws IOException;

    AnonymousClass0Oe A2I() throws IOException;

    OutputStream A6l();

    AnonymousClass0Oe A8v(C04610h7 v) throws IOException;

    AnonymousClass0Oe A8w(byte[] bArr) throws IOException;

    AnonymousClass0Oe A8x(byte[] bArr, int i, int i2) throws IOException;

    long A8y(AbstractC04550h0 v) throws IOException;

    AnonymousClass0Oe A91(int i) throws IOException;

    AnonymousClass0Oe A92(long j) throws IOException;

    AnonymousClass0Oe A95(long j) throws IOException;

    AnonymousClass0Oe A97(int i) throws IOException;

    AnonymousClass0Oe A9C(int i) throws IOException;

    AnonymousClass0Oe A9F(String str) throws IOException;

    @Override // X.AnonymousClass0h1, java.io.Flushable
    void flush() throws IOException;
}
