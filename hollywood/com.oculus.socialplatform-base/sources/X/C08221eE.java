package X;

import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: X.1eE  reason: invalid class name and case insensitive filesystem */
public class C08221eE implements AbstractC08281eK {
    public final /* synthetic */ AnonymousClass1e9 A00;
    public final /* synthetic */ AnonymousClass1hX A01;

    public C08221eE(AnonymousClass1e9 r1, AnonymousClass1hX r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC08281eK
    public final int A4Z(AbstractC08251eH r5) throws IOException {
        Throwable th;
        try {
            AnonymousClass1e9 r3 = this.A00;
            FileInputStream fileInputStream = new FileInputStream(r3.A9Q().getFileDescriptor());
            AnonymousClass1hX r0 = this.A01;
            C06741ax r2 = new C06741ax(fileInputStream, r0);
            try {
                int A4a = r5.A4a(r2, r0);
                try {
                    r2.close();
                } catch (IOException unused) {
                }
                r3.A9Q();
                return A4a;
            } catch (Throwable th2) {
                th = th2;
                try {
                    r2.close();
                } catch (IOException unused2) {
                }
                this.A00.A9Q();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            this.A00.A9Q();
            throw th;
        }
    }
}
