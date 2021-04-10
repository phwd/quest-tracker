package X;

import java.util.concurrent.Executor;

/* renamed from: X.Zc  reason: case insensitive filesystem */
public class C0277Zc implements D3<TResult, Void> {
    public final /* synthetic */ D3 A00;
    public final /* synthetic */ DB A01;
    public final /* synthetic */ DC A02;
    public final /* synthetic */ Executor A03;

    public C0277Zc(DB db, DC dc, D3 d3, Executor executor) {
        this.A01 = db;
        this.A02 = dc;
        this.A00 = d3;
        this.A03 = executor;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.D3
    public final Void A5U(DB db) throws Exception {
        DC dc = this.A02;
        D3 d3 = this.A00;
        try {
            this.A03.execute(new D6(dc, d3, db));
            return null;
        } catch (Exception e) {
            dc.A01(new D4(e));
            return null;
        }
    }
}
