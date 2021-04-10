package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: gM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2684gM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3538lM f9990a;
    public final C5232vH0 b;

    public C2684gM(C3538lM lMVar, C5232vH0 vh0) {
        this.f9990a = lMVar;
        this.b = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3538lM lMVar = this.f9990a;
        C5232vH0 vh0 = this.b;
        UsageStatsBridge usageStatsBridge = lMVar.f10340a;
        N.Mot8dCyk(usageStatsBridge.b, usageStatsBridge, new C1830bM((List) obj, vh0));
    }
}
