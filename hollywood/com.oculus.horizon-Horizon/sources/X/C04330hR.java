package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0hR  reason: invalid class name and case insensitive filesystem */
public final class C04330hR extends AbstractC03230cU {
    public int A00;
    public final /* synthetic */ C04320hN A01;

    public C04330hR(C04320hN r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC03230cU
    public final AnonymousClass0cT A00() throws IOException {
        C04320hN r3 = this.A01;
        r3.A03();
        C04340hS[] r2 = r3.A00;
        int i = this.A00;
        this.A00 = i + 1;
        C04340hS r22 = r2[i];
        InputStream inputStream = r3.A01.getInputStream(r22.A01);
        try {
            return new AnonymousClass0cT(r22, inputStream);
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // X.AbstractC03230cU
    public final boolean A01() {
        C04320hN r0 = this.A01;
        r0.A03();
        if (this.A00 < r0.A00.length) {
            return true;
        }
        return false;
    }
}
