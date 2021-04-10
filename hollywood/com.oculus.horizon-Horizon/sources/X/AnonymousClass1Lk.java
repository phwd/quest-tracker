package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.appmanager.downloader.OculusFileDownloader;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Lk  reason: invalid class name */
public final class AnonymousClass1Lk extends AbstractC08320wM {
    public static final C08370wR A02 = C08370wR.A00(OculusFileDownloader.ACCEPT_BINARY_STREAM);
    public final C08370wR A00;
    public final byte[] A01;

    @Override // X.AbstractC08320wM
    public final long A00() {
        return (long) this.A01.length;
    }

    @Override // X.AbstractC08320wM
    public final void A02(AnonymousClass0Lx r2) throws IOException {
        r2.AA7(this.A01);
    }

    public AnonymousClass1Lk(byte[] bArr, @Nullable String str) {
        C08370wR r0;
        this.A01 = bArr;
        if (str != null) {
            r0 = C08370wR.A00(str);
        } else {
            r0 = A02;
        }
        this.A00 = r0;
    }

    @Override // X.AbstractC08320wM
    public final C08370wR A01() {
        return this.A00;
    }
}
