package X;

/* renamed from: X.0ML  reason: invalid class name */
public class AnonymousClass0ML extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$7";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C07920vb A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0ML(C07920vb r2, Object[] objArr, int i) {
        super("OkHttp %s Push Reset[%s]", objArr);
        this.A01 = r2;
        this.A00 = i;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        C07920vb r2 = this.A01;
        synchronized (r2) {
            r2.A0C.remove(Integer.valueOf(this.A00));
        }
    }
}
