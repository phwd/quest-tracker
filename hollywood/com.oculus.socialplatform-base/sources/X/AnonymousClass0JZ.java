package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0JZ  reason: invalid class name */
public final class AnonymousClass0JZ {
    public final int A00 = 16384;
    public final AnonymousClass0VT A01;

    public AnonymousClass0JZ(AnonymousClass0VT r3) {
        C00740Ii.A01(true);
        this.A01 = r3;
    }

    public final void A00(InputStream inputStream, OutputStream outputStream) throws IOException {
        AnonymousClass0VT r5 = this.A01;
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
                r5.A8y(bArr);
            }
        }
    }
}
