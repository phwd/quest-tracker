package defpackage;

import com.google.android.gms.common.ConnectionResult;

/* renamed from: NA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NA1 extends AbstractC2143dB1 {
    public final /* synthetic */ ConnectionResult b;
    public final /* synthetic */ KA1 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NA1(KA1 ka1, AbstractC1801bB1 bb1, ConnectionResult connectionResult) {
        super(bb1);
        this.c = ka1;
        this.b = connectionResult;
    }

    @Override // defpackage.AbstractC2143dB1
    public final void a() {
        this.c.H.o(this.b);
    }
}
