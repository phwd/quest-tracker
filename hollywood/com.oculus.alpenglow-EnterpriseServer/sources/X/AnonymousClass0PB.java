package X;

import java.io.IOException;

/* renamed from: X.0PB  reason: invalid class name */
public class AnonymousClass0PB extends AbstractRunnableC05610k6 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$3";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C04780hU A02;
    public final /* synthetic */ boolean A03 = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0PB(C04780hU r3, Object[] objArr, int i, int i2) {
        super("OkHttp %s ping %08x%08x", objArr);
        this.A02 = r3;
        this.A00 = i;
        this.A01 = i2;
    }

    @Override // X.AbstractRunnableC05610k6
    public final void A00() {
        try {
            C04780hU r0 = this.A02;
            boolean z = this.A03;
            int i = this.A00;
            int i2 = this.A01;
            C04750hR r4 = r0.A0F;
            synchronized (r4) {
                if (!r4.A01) {
                    byte b = 0;
                    if (z) {
                        b = 1;
                    }
                    C04750hR.A00(r4, 0, 8, (byte) 6, b);
                    AnonymousClass0Oe r02 = r4.A04;
                    r02.A97(i);
                    r02.A97(i2);
                    r02.flush();
                } else {
                    throw new IOException("closed");
                }
            }
        } catch (IOException unused) {
        }
    }
}
