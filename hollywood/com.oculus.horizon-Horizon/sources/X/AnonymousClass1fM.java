package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.1fM  reason: invalid class name */
public class AnonymousClass1fM extends FilterOutputStream {
    public final void A00() throws IOException {
        this.out.write("0\r\n\r\n".getBytes());
    }

    public AnonymousClass1fM(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public final void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 != 0) {
            this.out.write(String.format("%x\r\n", Integer.valueOf(i2)).getBytes());
            this.out.write(bArr, i, i2);
            this.out.write(HttpRequestMultipart.LINE_FEED.getBytes());
        }
    }
}
