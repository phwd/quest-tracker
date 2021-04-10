package X;

/* renamed from: X.0by  reason: invalid class name and case insensitive filesystem */
public class C03290by implements AnonymousClass0Hn<TContinuationResult, Void> {
    public final /* synthetic */ RunnableC01490Hq A00;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Hn
    public final Void A8Z(AnonymousClass0Hv r5) throws Exception {
        boolean z;
        TResult tresult;
        Object obj = r5.A05;
        synchronized (obj) {
            z = r5.A03;
        }
        if (z) {
            this.A00.A02.A00();
            return null;
        } else if (r5.A05()) {
            this.A00.A02.A01(r5.A03());
            return null;
        } else {
            C01520Hw r1 = this.A00.A02;
            synchronized (obj) {
                tresult = r5.A01;
            }
            r1.A02(tresult);
            return null;
        }
    }

    public C03290by(RunnableC01490Hq r1) {
        this.A00 = r1;
    }
}
