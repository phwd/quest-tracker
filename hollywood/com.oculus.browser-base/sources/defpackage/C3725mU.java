package defpackage;

import android.os.Bundle;

/* renamed from: mU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3725mU extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4067oU f10424a;

    public C3725mU(C4067oU oUVar) {
        this.f10424a = oUVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4067oU oUVar;
        C5089uU uUVar;
        boolean z = ((Bundle) obj).getBoolean("ssb_service:ssb_broadcasts_account_change_to_chrome");
        if (z && C4067oU.a() && (uUVar = (oUVar = this.f10424a).c) != null) {
            uUVar.b();
            oUVar.c = null;
        }
        if (!this.f10424a.d) {
            AbstractC3100ip1.f10165a.a("Search.GsaBroadcastsAccountChanges", z);
            this.f10424a.d = true;
        }
    }
}
