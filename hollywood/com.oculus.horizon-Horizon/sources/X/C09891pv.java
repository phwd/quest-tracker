package X;

import com.facebook.infer.annotation.Nullsafe;
import com.squareup.okhttp.internal.framed.Http2;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1pv  reason: invalid class name and case insensitive filesystem */
public final class C09891pv {
    public final int A00 = Http2.INITIAL_MAX_FRAME_SIZE;
    public final AnonymousClass0Km A01;

    public C09891pv(AnonymousClass0Km r3) {
        AnonymousClass0KU.A01(true);
        this.A01 = r3;
    }

    public final void A00(InputStream inputStream, OutputStream outputStream) throws IOException {
        AnonymousClass0Km r5 = this.A01;
        int i = this.A00;
        byte[] bArr = (byte[]) r5.get(i);
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, i);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            } finally {
                r5.A86(bArr);
            }
        }
    }
}
