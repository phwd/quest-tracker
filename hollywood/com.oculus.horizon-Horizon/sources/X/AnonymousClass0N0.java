package X;

import java.io.IOException;

/* renamed from: X.0N0  reason: invalid class name */
public final class AnonymousClass0N0 extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.RealCall$AsyncCall";
    public final AbstractC08570wn A00;
    public final /* synthetic */ AnonymousClass0Mz A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0N0(AnonymousClass0Mz r4, AbstractC08570wn r5) {
        super("OkHttp %s", r4.A00());
        this.A01 = r4;
        this.A00 = r5;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        AnonymousClass0Mz r0;
        String str;
        String str2;
        try {
            AnonymousClass0Mz r9 = this.A01;
            r0 = r9;
            C08220wC A012 = r9.A01();
            C01200Md r4 = r9.A03;
            if (r4.A04) {
                try {
                    this.A00.onFailure(r9, new IOException("Canceled"));
                } catch (IOException e) {
                    C07790vM r7 = C07790vM.A00;
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
                    C08450wb r2 = this.A01.A01.A0J;
                    C08450wb.A01(r2, r2.A02, this, true);
                    throw th;
                }
            } else {
                this.A00.onResponse(r9, A012);
            }
        } catch (IOException e2) {
            AbstractC08570wn r1 = this.A00;
            r0 = this.A01;
            r1.onFailure(r0, e2);
        }
        C08450wb r22 = r0.A01.A0J;
        C08450wb.A01(r22, r22.A02, this, true);
    }
}
