package X;

import java.util.concurrent.Executor;

/* renamed from: X.0c0  reason: invalid class name and case insensitive filesystem */
public class C03310c0 implements AnonymousClass0Hn<TResult, Void> {
    public final /* synthetic */ AnonymousClass0Hn A00;
    public final /* synthetic */ AnonymousClass0Hv A01;
    public final /* synthetic */ C01520Hw A02;
    public final /* synthetic */ Executor A03;

    public C03310c0(AnonymousClass0Hv r1, C01520Hw r2, AnonymousClass0Hn r3, Executor executor) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = r3;
        this.A03 = executor;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Hn
    public final Void A8Z(AnonymousClass0Hv r6) throws Exception {
        C01520Hw r4 = this.A02;
        AnonymousClass0Hn r3 = this.A00;
        try {
            this.A03.execute(new RunnableC01480Hp(r4, r3, r6));
            return null;
        } catch (Exception e) {
            r4.A01(new C01470Ho(e));
            return null;
        }
    }
}
