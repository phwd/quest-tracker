package X;

import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: X.0hr  reason: invalid class name and case insensitive filesystem */
public final class C04510hr extends AbstractC03230cU {
    public int A00;
    public final /* synthetic */ C04500hq A01;

    public C04510hr(C04500hq r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC03230cU
    public final AnonymousClass0cT A00() throws IOException {
        C04490hp[] r2 = this.A01.A00;
        int i = this.A00;
        this.A00 = i + 1;
        C04490hp r22 = r2[i];
        FileInputStream fileInputStream = new FileInputStream(r22.A00);
        try {
            return new AnonymousClass0cT(r22, fileInputStream);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @Override // X.AbstractC03230cU
    public final boolean A01() {
        if (this.A00 < this.A01.A00.length) {
            return true;
        }
        return false;
    }
}
