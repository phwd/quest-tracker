package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.10x  reason: invalid class name */
public final class AnonymousClass10x extends AbstractC05690kc {
    public static final C05820lT A02 = C05820lT.A00("application/octet-stream");
    public final C05820lT A00;
    public final byte[] A01;

    @Override // X.AbstractC05690kc
    public final long A00() {
        return (long) this.A01.length;
    }

    @Override // X.AbstractC05690kc
    public final void A02(AnonymousClass0Oe r2) throws IOException {
        r2.A8w(this.A01);
    }

    public AnonymousClass10x(byte[] bArr, @Nullable String str) {
        C05820lT r0;
        this.A01 = bArr;
        if (str != null) {
            r0 = C05820lT.A00(str);
        } else {
            r0 = A02;
        }
        this.A00 = r0;
    }

    @Override // X.AbstractC05690kc
    public final C05820lT A01() {
        return this.A00;
    }
}
