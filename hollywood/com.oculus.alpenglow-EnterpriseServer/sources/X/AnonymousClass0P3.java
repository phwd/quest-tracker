package X;

import java.io.IOException;

/* renamed from: X.0P3  reason: invalid class name */
public class AnonymousClass0P3 extends AbstractRunnableC05610k6 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C04780hU A02;
    public final /* synthetic */ AnonymousClass0HR A03;
    public final /* synthetic */ boolean A04;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0P3(C04780hU r2, Object[] objArr, int i, AnonymousClass0HR r5, int i2, boolean z) {
        super("OkHttp %s Push Data[%s]", objArr);
        this.A02 = r2;
        this.A01 = i;
        this.A03 = r5;
        this.A00 = i2;
        this.A04 = z;
    }

    @Override // X.AbstractRunnableC05610k6
    public final void A00() {
        try {
            C04780hU r5 = this.A02;
            AbstractC04720hN r4 = r5.A09;
            int i = this.A01;
            AnonymousClass0HR r2 = this.A03;
            int i2 = this.A00;
            boolean z = this.A04;
            if (r4.onData(i, r2, i2, z)) {
                r5.A0F.A03(i, EnumC04880hs.CANCEL);
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
