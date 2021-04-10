package X;

import com.facebook.assistant.oacr.OacrClientListener;
import com.facebook.common.time.RealtimeSinceBootClock;

/* renamed from: X.g2  reason: case insensitive filesystem */
public final class C0720g2 implements OacrClientListener {
    public final /* synthetic */ C0740gP A00;

    public C0720g2(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    private C0104Ai A00() {
        C0113Ar ar = new C0113Ar();
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.A00;
        long now = realtimeSinceBootClock.now();
        ar.A03 = now;
        long now2 = realtimeSinceBootClock.now();
        ar.A01 = now2;
        ar.A00 = 1;
        ar.A02 = now2 - now;
        return new C0104Ai(this.A00.A0h, ar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onDeviceTts(C0823iT iTVar) {
        this.A00.A0a.A47(A00(), iTVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onError(C0813iG iGVar) {
        C0740gP.A04(this.A00, iGVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onResponse(iK iKVar) {
        C0740gP.A06(this.A00, iKVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onTranscription(C0817iM iMVar) {
        C0740gP.A07(this.A00, iMVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onTtsAudioData(C0818iN iNVar) {
        C0740gP gPVar = this.A00;
        gPVar.A0d.A47(A00(), iNVar);
        gPVar.A0h.A01(iNVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onTtsEnd(C0819iO iOVar) {
        C0740gP gPVar = this.A00;
        gPVar.A0f.A47(A00(), iOVar);
        gPVar.A0h.A01(iOVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onTtsStart(C0820iQ iQVar) {
        C0740gP gPVar = this.A00;
        gPVar.A0e.A47(A00(), iQVar);
        gPVar.A0h.A01(iQVar);
    }

    @Override // com.facebook.assistant.oacr.OacrClientListener
    public final void onWakewordVerification(C0814iH iHVar) {
        C0740gP.A05(this.A00, iHVar);
    }
}
