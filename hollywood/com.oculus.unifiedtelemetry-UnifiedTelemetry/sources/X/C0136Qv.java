package X;

import java.io.IOException;
import retrofit.mime.TypedOutput;

/* renamed from: X.Qv  reason: case insensitive filesystem */
public class C0136Qv extends AbstractC0361di {
    public final /* synthetic */ C0366dn A00;
    public final /* synthetic */ TypedOutput A01;

    public C0136Qv(C0366dn dnVar, TypedOutput typedOutput) {
        this.A00 = dnVar;
        this.A01 = typedOutput;
    }

    @Override // X.AbstractC0361di
    public final long A00() {
        return this.A01.length();
    }

    @Override // X.AbstractC0361di
    public final void A02(KJ kj) throws IOException {
        this.A01.writeTo(kj.A48());
    }

    @Override // X.AbstractC0361di
    public final C0366dn A01() {
        return this.A00;
    }
}
