package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class X8 extends AbstractC0361di {
    public static final C0366dn A02 = C0366dn.A00("application/octet-stream");
    public final C0366dn A00;
    public final byte[] A01;

    @Override // X.AbstractC0361di
    public final long A00() {
        return (long) this.A01.length;
    }

    @Override // X.AbstractC0361di
    public final void A02(KJ kj) throws IOException {
        kj.A5n(this.A01);
    }

    public X8(byte[] bArr, @Nullable String str) {
        C0366dn dnVar;
        this.A01 = bArr;
        if (str != null) {
            dnVar = C0366dn.A00(str);
        } else {
            dnVar = A02;
        }
        this.A00 = dnVar;
    }

    @Override // X.AbstractC0361di
    public final C0366dn A01() {
        return this.A00;
    }
}
