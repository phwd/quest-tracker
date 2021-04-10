package X;

import java.io.IOException;

/* renamed from: X.0MM  reason: invalid class name */
public class AnonymousClass0MM extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C07920vb A02;
    public final /* synthetic */ AnonymousClass0B3 A03;
    public final /* synthetic */ boolean A04;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MM(C07920vb r2, Object[] objArr, int i, AnonymousClass0B3 r5, int i2, boolean z) {
        super("OkHttp %s Push Data[%s]", objArr);
        this.A02 = r2;
        this.A01 = i;
        this.A03 = r5;
        this.A00 = i2;
        this.A04 = z;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        try {
            C07920vb r5 = this.A02;
            AbstractC07860vU r4 = r5.A09;
            int i = this.A01;
            AnonymousClass0B3 r2 = this.A03;
            int i2 = this.A00;
            boolean z = this.A04;
            if (r4.onData(i, r2, i2, z)) {
                r5.A0F.A03(i, EnumC08000vj.CANCEL);
            } else if (!z) {
                return;
            }
            synchronized (r5) {
                r5.A0C.remove(Integer.valueOf(i));
            }
        } catch (IOException unused) {
        }
    }
}
