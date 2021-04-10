package X;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

/* renamed from: X.0il  reason: invalid class name and case insensitive filesystem */
public final class C02430il extends AbstractC03090lA {
    public int A00;
    public final InputStream A01;
    public final /* synthetic */ C02420ik A02;

    @Override // X.AbstractC03090lA
    @Nullable
    public final AnonymousClass0l9 A00() throws IOException {
        C02460io r1;
        while (true) {
            r1 = null;
            int i = this.A00;
            C02440im[] r2 = this.A02.A02;
            if (i >= r2.length) {
                break;
            }
            this.A00 = i + 1;
            C02440im r3 = r2[i];
            C03100lF r22 = new C03100lF(this, r3.A02);
            try {
                r1 = new C02460io(r3, r22);
                if (r3.A00) {
                    break;
                }
                r1.close();
            } catch (Throwable th) {
                r22.close();
                throw th;
            }
        }
        return r1;
    }

    public C02430il(C02420ik r3) throws IOException {
        this.A02 = r3;
        InputStream inputStream = r3.A01.getInputStream(r3.A00);
        try {
            this.A01 = r3.A03.A00.getCompressedInputStream(inputStream);
            if (!A01()) {
                close();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // X.AbstractC03090lA
    public final boolean A01() {
        int length;
        int i = this.A00;
        while (true) {
            C02440im[] r0 = this.A02.A02;
            length = r0.length;
            if (i < length && !r0[i].A00) {
                i++;
            }
        }
        if (i < length) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC03090lA, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }
}
