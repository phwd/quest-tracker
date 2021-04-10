package X;

/* renamed from: X.Zb  reason: case insensitive filesystem */
public class C0276Zb implements D3<TContinuationResult, Void> {
    public final /* synthetic */ D6 A00;

    public C0276Zb(D6 d6) {
        this.A00 = d6;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.D3
    public final Void A5U(DB db) throws Exception {
        boolean z;
        TResult tresult;
        D6 d6 = this.A00;
        Object obj = db.A05;
        synchronized (obj) {
            z = db.A03;
        }
        if (z) {
            d6.A02.A00();
            return null;
        } else if (db.A06()) {
            d6.A02.A01(db.A03());
            return null;
        } else {
            DC dc = d6.A02;
            synchronized (obj) {
                tresult = db.A01;
            }
            dc.A02(tresult);
            return null;
        }
    }
}
