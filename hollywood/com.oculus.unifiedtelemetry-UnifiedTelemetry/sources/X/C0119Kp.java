package X;

/* renamed from: X.Kp  reason: case insensitive filesystem */
public class C0119Kp extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$7";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C0337d5 A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0119Kp(C0337d5 d5Var, Object[] objArr, int i) {
        super("OkHttp %s Push Reset[%s]", objArr);
        this.A01 = d5Var;
        this.A00 = i;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        C0337d5 d5Var = this.A01;
        synchronized (d5Var) {
            d5Var.A0C.remove(Integer.valueOf(this.A00));
        }
    }
}
