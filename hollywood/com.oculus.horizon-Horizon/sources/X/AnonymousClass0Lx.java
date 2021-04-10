package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

/* renamed from: X.0Lx  reason: invalid class name */
public interface AnonymousClass0Lx extends AbstractC07640v7, WritableByteChannel {
    AnonymousClass0B3 A1V();

    AnonymousClass0Lx A2O() throws IOException;

    AnonymousClass0Lx A2P() throws IOException;

    OutputStream A7F();

    AnonymousClass0Lx AA6(C07700vD v) throws IOException;

    AnonymousClass0Lx AA7(byte[] bArr) throws IOException;

    AnonymousClass0Lx AA8(byte[] bArr, int i, int i2) throws IOException;

    long AA9(AbstractC07630v6 v) throws IOException;

    AnonymousClass0Lx AAC(int i) throws IOException;

    AnonymousClass0Lx AAD(long j) throws IOException;

    AnonymousClass0Lx AAE(long j) throws IOException;

    AnonymousClass0Lx AAF(int i) throws IOException;

    AnonymousClass0Lx AAH(int i) throws IOException;

    AnonymousClass0Lx AAI(String str) throws IOException;

    @Override // X.AbstractC07640v7, java.io.Flushable
    void flush() throws IOException;
}
