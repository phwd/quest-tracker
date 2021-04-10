package X;

import java.io.IOException;

/* renamed from: X.0Qo  reason: invalid class name */
public final class AnonymousClass0Qo extends AbstractRunnableC05610k6 {
    public static final String __redex_internal_original_name = "okhttp3.RealCall$AsyncCall";
    public final AbstractC06530n3 A00;
    public final /* synthetic */ AnonymousClass0Qd A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Qo(AnonymousClass0Qd r3, AbstractC06530n3 r4) {
        super("OkHttp %s", r3.A00());
        this.A01 = r3;
        this.A00 = r4;
    }

    @Override // X.AbstractRunnableC05610k6
    public final void A00() {
        AnonymousClass0Qd r0;
        String str;
        String str2;
        try {
            AnonymousClass0Qd r9 = this.A01;
            r0 = r9;
            C05660kD A012 = r9.A01();
            AnonymousClass0PV r4 = r9.A03;
            if (r4.A04) {
                try {
                    this.A00.onFailure(r9, new IOException("Canceled"));
                } catch (IOException e) {
                    C04670hG r7 = C04670hG.A00;
                    if (r4.A04) {
                        str = "canceled ";
                    } else {
                        str = "";
                    }
                    if (r9.A04) {
                        str2 = "web socket";
                    } else {
                        str2 = "call";
                    }
                    r7.A04(4, AnonymousClass006.A05("Callback failure for ", AnonymousClass006.A08(str, str2, " to ", r9.A00())), e);
                } catch (Throwable th) {
                    C06110ly r2 = this.A01.A01.A0H;
                    C06110ly.A02(r2, r2.A02, this, true);
                    throw th;
                }
            } else {
                this.A00.onResponse(r9, A012);
            }
        } catch (IOException e2) {
            AbstractC06530n3 r1 = this.A00;
            r0 = this.A01;
            r1.onFailure(r0, e2);
        }
        C06110ly r22 = r0.A01.A0H;
        C06110ly.A02(r22, r22.A02, this, true);
    }
}
