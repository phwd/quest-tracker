package X;

import java.util.concurrent.Executor;

/* renamed from: X.0bz  reason: invalid class name and case insensitive filesystem */
public class C03300bz implements AnonymousClass0Hn<TResult, Void> {
    public final /* synthetic */ AnonymousClass0Hn A00;
    public final /* synthetic */ AnonymousClass0Hv A01;
    public final /* synthetic */ C01520Hw A02;
    public final /* synthetic */ Executor A03;

    public C03300bz(AnonymousClass0Hv r1, C01520Hw r2, AnonymousClass0Hn r3, Executor executor) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = r3;
        this.A03 = executor;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Hn
    public final Void A8Z(AnonymousClass0Hv r5) throws Exception {
        AnonymousClass0Hv.A02(this.A02, this.A00, r5, this.A03);
        return null;
    }
}
