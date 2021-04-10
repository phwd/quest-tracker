package X;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: X.1eD  reason: invalid class name and case insensitive filesystem */
public class C08211eD implements AbstractC08271eJ {
    public final /* synthetic */ AnonymousClass1e9 A00;
    public final /* synthetic */ AnonymousClass1hX A01;

    public C08211eD(AnonymousClass1e9 r1, AnonymousClass1hX r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC08271eJ
    public final ImageHeaderParser$ImageType A56(AbstractC08251eH r5) throws IOException {
        Throwable th;
        try {
            AnonymousClass1e9 r3 = this.A00;
            C06741ax r2 = new C06741ax(new FileInputStream(r3.A9Q().getFileDescriptor()), this.A01);
            try {
                ImageHeaderParser$ImageType A57 = r5.A57(r2);
                try {
                    r2.close();
                } catch (IOException unused) {
                }
                r3.A9Q();
                return A57;
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
