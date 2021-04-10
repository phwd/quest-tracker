package X;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

/* renamed from: X.0hB  reason: invalid class name and case insensitive filesystem */
public final class C04240hB extends AbstractC03230cU {
    public int A00;
    public final InputStream A01;
    public final /* synthetic */ C04230hA A02;

    @Override // X.AbstractC03230cU
    @Nullable
    public final AnonymousClass0cT A00() throws IOException {
        AnonymousClass0cT r1;
        while (true) {
            r1 = null;
            int i = this.A00;
            C04250hC[] r2 = this.A02.A02;
            if (i >= r2.length) {
                break;
            }
            this.A00 = i + 1;
            C04250hC r3 = r2[i];
            C03270cZ r22 = new C03270cZ(this, r3.A02);
            try {
                r1 = new AnonymousClass0cT(r3, r22);
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

    public C04240hB(C04230hA r3) throws IOException {
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

    @Override // X.AbstractC03230cU
    public final boolean A01() {
        int length;
        int i = this.A00;
        while (true) {
            C04250hC[] r0 = this.A02.A02;
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

    @Override // X.AbstractC03230cU, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }
}
