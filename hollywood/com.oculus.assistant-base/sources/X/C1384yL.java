package X;

import android.os.Bundle;

/* renamed from: X.yL  reason: case insensitive filesystem */
public final class C1384yL implements AbstractC0444Yo {
    public final /* synthetic */ YJ A00;

    public C1384yL(YJ yj) {
        this.A00 = yj;
    }

    @Override // X.AbstractC0444Yo
    public final void A3s(byte[] bArr) {
        C0514bB.A02(bArr, "copyOf");
        YJ yj = this.A00;
        C1212vS vSVar = new C1212vS(bArr);
        Bundle bundle = new Bundle();
        bundle.putByteArray("audioData", vSVar.A00);
        yj.A04.A01(new C1256wC("TTSSpeechAudioMessage", bundle, yj.A06));
    }

    @Override // X.AbstractC0444Yo
    public final void A44() {
        YJ.A00(this.A00, AnonymousClass09.A0J);
    }

    @Override // X.AbstractC0444Yo
    public final void A45(Throwable th) {
        C0514bB.A02(th, "throwable");
        YJ.A00(this.A00, AnonymousClass09.A0J);
    }

    @Override // X.AbstractC0444Yo
    public final void A4M() {
        YJ.A00(this.A00, AnonymousClass09.A0C);
    }
}
