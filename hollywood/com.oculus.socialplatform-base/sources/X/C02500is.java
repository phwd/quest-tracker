package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0is  reason: invalid class name and case insensitive filesystem */
public final class C02500is extends AbstractC03090lA {
    public int A00;
    public final /* synthetic */ C02490ir A01;

    public C02500is(C02490ir r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC03090lA
    public final AnonymousClass0l9 A00() throws IOException {
        C02490ir r3 = this.A01;
        r3.A03();
        C02510it[] r2 = r3.A00;
        int i = this.A00;
        this.A00 = i + 1;
        C02510it r22 = r2[i];
        InputStream inputStream = r3.A01.getInputStream(r22.A01);
        try {
            return new C02460io(r22, inputStream);
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // X.AbstractC03090lA
    public final boolean A01() {
        C02490ir r0 = this.A01;
        r0.A03();
        if (this.A00 < r0.A00.length) {
            return true;
        }
        return false;
    }
}
