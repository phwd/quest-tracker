package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import javax.annotation.Nullable;

/* renamed from: X.Gv  reason: case insensitive filesystem */
public final class C0087Gv extends Reader {
    @Nullable
    public InputStream A00;
    @Nullable
    public CharsetDecoder A01;
    public boolean A02 = false;
    public final ByteBuffer A03;

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this.lock) {
            CharsetDecoder charsetDecoder = this.A01;
            if (charsetDecoder != null) {
                charsetDecoder.reset();
            }
            this.A01 = null;
            InputStream inputStream = this.A00;
            if (inputStream != null) {
                inputStream.close();
                this.A00 = null;
            }
        }
    }

    @Override // java.io.Reader
    public final boolean ready() throws IOException {
        InputStream inputStream;
        boolean z;
        synchronized (this.lock) {
            synchronized (this.lock) {
                inputStream = this.A00;
                if (inputStream == null) {
                    throw new IOException("InputStreamReader is closed");
                }
            }
            z = false;
            try {
                if (this.A03.hasRemaining() || inputStream.available() > 0) {
                    z = true;
                }
            } catch (IOException unused) {
                return false;
            }
        }
        return z;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0087Gv(InputStream inputStream, ByteBuffer byteBuffer) {
        super(inputStream);
        Charset defaultCharset = Charset.defaultCharset();
        this.A03 = byteBuffer;
        this.A00 = inputStream;
        CharsetDecoder newDecoder = defaultCharset.newDecoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        this.A01 = newDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        this.A03.limit(0);
    }

    @Override // java.io.Reader
    public final int read() throws IOException {
        char c;
        synchronized (this.lock) {
            if (this.A00 != null) {
                char[] cArr = new char[1];
                c = 65535;
                if (read(cArr, 0, 1) != -1) {
                    c = cArr[0];
                }
            } else {
                throw new IOException("InputStreamReader is closed");
            }
        }
        return c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        if (r8.hasRemaining() == false) goto L_0x00c1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00aa  */
    @Override // java.io.Reader
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int read(char[] r12, int r13, int r14) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 211
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0087Gv.read(char[], int, int):int");
    }
}
