package X;

import java.io.IOException;
import retrofit.mime.TypedOutput;

/* renamed from: X.0UL  reason: invalid class name */
public class AnonymousClass0UL extends AbstractC08320wM {
    public final /* synthetic */ C08370wR A00;
    public final /* synthetic */ TypedOutput A01;

    public AnonymousClass0UL(C08370wR r1, TypedOutput typedOutput) {
        this.A00 = r1;
        this.A01 = typedOutput;
    }

    @Override // X.AbstractC08320wM
    public final long A00() {
        return this.A01.length();
    }

    @Override // X.AbstractC08320wM
    public final void A02(AnonymousClass0Lx r3) throws IOException {
        this.A01.writeTo(r3.A7F());
    }

    @Override // X.AbstractC08320wM
    public final C08370wR A01() {
        return this.A00;
    }
}
