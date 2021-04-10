package X;

import com.facebook.assistant.oacr.Oacr;

/* renamed from: X.fq  reason: case insensitive filesystem */
public final class C0710fq implements Oacr.ReadinessStatusCallback {
    public final /* synthetic */ C0740gP A00;

    public C0710fq(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.Oacr.ReadinessStatusCallback
    public final void onFailure(String str) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "mOacrReadinessStatusCallbackListener, onFailure: reason: %s", str);
        C0740gP.A04(this.A00, new C0813iG(AnonymousClass09.A01, AnonymousClass08.A04("Unable to initialize oacr because ", str)));
    }

    @Override // com.facebook.assistant.oacr.Oacr.ReadinessStatusCallback
    public final void onOnDeviceReady() {
        C0139Dd.A09("AssistantClientPlatform", "mOacrReadinessStatusCallbackListener, onOnDeviceReady");
        C00919v.A00.A07("oacr");
        this.A00.A0h.A01(new C0788hb());
        C00799i.A00.logStateChanged("ready");
    }

    @Override // com.facebook.assistant.oacr.Oacr.ReadinessStatusCallback
    public final void onPassThroughReady() {
        C0139Dd.A09("AssistantClientPlatform", "mOacrReadinessStatusCallbackListener, onPassThroughReady");
        C0740gP gPVar = this.A00;
        if (!((Boolean) gPVar.A0H.A04.A00(3)).booleanValue()) {
            C00919v.A00.A07("oacr");
        }
        gPVar.A0h.A01(new C0788hb());
        C00799i.A00.logStateChanged("ready");
    }
}
