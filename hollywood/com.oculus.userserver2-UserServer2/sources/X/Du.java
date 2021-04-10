package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

public interface Du extends WG, WritableByteChannel {
    AnonymousClass8k A16();

    Du A1S() throws IOException;

    Du A1T() throws IOException;

    OutputStream A2l();

    Du A3u(WM wm) throws IOException;

    Du A3v(byte[] bArr) throws IOException;

    Du A3w(byte[] bArr, int i, int i2) throws IOException;

    long A3x(WF wf) throws IOException;

    Du A3y(int i) throws IOException;

    Du A3z(long j) throws IOException;

    Du A40(long j) throws IOException;

    Du A41(int i) throws IOException;

    Du A43(int i) throws IOException;

    Du A44(String str) throws IOException;

    @Override // X.WG, java.io.Flushable
    void flush() throws IOException;
}
