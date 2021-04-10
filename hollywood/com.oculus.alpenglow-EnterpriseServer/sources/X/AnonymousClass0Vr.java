package X;

import java.io.IOException;
import retrofit.mime.TypedOutput;

/* renamed from: X.0Vr  reason: invalid class name */
public class AnonymousClass0Vr extends AbstractC05690kc {
    public final /* synthetic */ C05820lT A00;
    public final /* synthetic */ TypedOutput A01;

    public AnonymousClass0Vr(C05820lT r1, TypedOutput typedOutput) {
        this.A00 = r1;
        this.A01 = typedOutput;
    }

    @Override // X.AbstractC05690kc
    public final long A00() {
        return this.A01.length();
    }

    @Override // X.AbstractC05690kc
    public final C05820lT A01() {
        return this.A00;
    }

    @Override // X.AbstractC05690kc
    public final void A02(AnonymousClass0Oe r3) throws IOException {
        this.A01.writeTo(r3.A6l());
    }
}
