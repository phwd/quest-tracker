package X;

import java.io.IOException;

/* renamed from: X.0MH  reason: invalid class name */
public class AnonymousClass0MH extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$ReaderRunnable$1";
    public final /* synthetic */ AnonymousClass0ME A00;
    public final /* synthetic */ C07900vZ A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MH(AnonymousClass0ME r2, Object[] objArr, C07900vZ r4) {
        super("OkHttp %s stream %d", objArr);
        this.A00 = r2;
        this.A01 = r4;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        try {
            this.A00.A01.A08.A02(this.A01);
        } catch (IOException e) {
            C07790vM.A00.A04(4, AnonymousClass006.A05("Http2Connection.Listener failure for ", this.A00.A01.A0A), e);
            try {
                this.A01.A05(EnumC08000vj.PROTOCOL_ERROR);
            } catch (IOException unused) {
            }
        }
    }
}
