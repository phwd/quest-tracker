package X;

/* renamed from: X.0P2  reason: invalid class name */
public class AnonymousClass0P2 extends AbstractRunnableC05610k6 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$7";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C04780hU A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0P2(C04780hU r2, Object[] objArr, int i) {
        super("OkHttp %s Push Reset[%s]", objArr);
        this.A01 = r2;
        this.A00 = i;
    }

    @Override // X.AbstractRunnableC05610k6
    public final void A00() {
        C04780hU r2 = this.A01;
        synchronized (r2) {
            r2.A0C.remove(Integer.valueOf(this.A00));
        }
    }
}
