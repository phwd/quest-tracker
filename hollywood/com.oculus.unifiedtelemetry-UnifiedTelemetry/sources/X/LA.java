package X;

import java.io.IOException;

public class LA extends AbstractC0361di {
    public final /* synthetic */ int A00;
    public final /* synthetic */ byte[] A01;

    public LA(int i, byte[] bArr) {
        this.A00 = i;
        this.A01 = bArr;
    }

    @Override // X.AbstractC0361di
    public final long A00() {
        return (long) this.A00;
    }

    @Override // X.AbstractC0361di
    public final void A02(KJ kj) throws IOException {
        kj.A5o(this.A01, 0, this.A00);
    }

    @Override // X.AbstractC0361di
    public final C0366dn A01() {
        return null;
    }
}
