package X;

import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: X.0j4  reason: invalid class name and case insensitive filesystem */
public final class C02540j4 extends AbstractC03090lA {
    public int A00;
    public final /* synthetic */ C02530iv A01;

    public C02540j4(C02530iv r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC03090lA
    public final AnonymousClass0l9 A00() throws IOException {
        C02520iu[] r2 = this.A01.A00;
        int i = this.A00;
        this.A00 = i + 1;
        C02520iu r22 = r2[i];
        FileInputStream fileInputStream = new FileInputStream(r22.A00);
        try {
            return new C02460io(r22, fileInputStream);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @Override // X.AbstractC03090lA
    public final boolean A01() {
        if (this.A00 < this.A01.A00.length) {
            return true;
        }
        return false;
    }
}
