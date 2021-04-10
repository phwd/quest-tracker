package X;

import java.io.IOException;

/* renamed from: X.0P0  reason: invalid class name */
public class AnonymousClass0P0 extends AbstractRunnableC05610k6 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$ReaderRunnable$1";
    public final /* synthetic */ AnonymousClass0Ox A00;
    public final /* synthetic */ C04760hS A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0P0(AnonymousClass0Ox r2, Object[] objArr, C04760hS r4) {
        super("OkHttp %s stream %d", objArr);
        this.A00 = r2;
        this.A01 = r4;
    }

    @Override // X.AbstractRunnableC05610k6
    public final void A00() {
        try {
            this.A00.A01.A08.A02(this.A01);
        } catch (IOException e) {
            C04670hG.A00.A04(4, AnonymousClass006.A05("Http2Connection.Listener failure for ", this.A00.A01.A0A), e);
            try {
                this.A01.A05(EnumC04880hs.PROTOCOL_ERROR);
            } catch (IOException unused) {
            }
        }
    }
}
