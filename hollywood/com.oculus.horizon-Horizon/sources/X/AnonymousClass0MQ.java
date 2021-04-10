package X;

import java.io.IOException;

/* renamed from: X.0MQ  reason: invalid class name */
public class AnonymousClass0MQ extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$3";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C07920vb A02;
    public final /* synthetic */ boolean A03 = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MQ(C07920vb r3, Object[] objArr, int i, int i2) {
        super("OkHttp %s ping %08x%08x", objArr);
        this.A02 = r3;
        this.A00 = i;
        this.A01 = i2;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        try {
            C07920vb r0 = this.A02;
            boolean z = this.A03;
            int i = this.A00;
            int i2 = this.A01;
            C07890vY r4 = r0.A0F;
            synchronized (r4) {
                if (!r4.A01) {
                    byte b = 0;
                    if (z) {
                        b = 1;
                    }
                    C07890vY.A00(r4, 0, 8, (byte) 6, b);
                    AnonymousClass0Lx r02 = r4.A04;
                    r02.AAF(i);
                    r02.AAF(i2);
                    r02.flush();
                } else {
                    throw new IOException("closed");
                }
            }
        } catch (IOException unused) {
        }
    }
}
