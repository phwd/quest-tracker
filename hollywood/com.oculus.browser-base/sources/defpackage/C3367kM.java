package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: kM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3367kM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3538lM f10274a;
    public final List b;
    public final C5232vH0 c;

    public C3367kM(C3538lM lMVar, List list, C5232vH0 vh0) {
        this.f10274a = lMVar;
        this.b = list;
        this.c = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3538lM lMVar = this.f10274a;
        List list = this.b;
        C5232vH0 vh0 = this.c;
        UsageStatsBridge usageStatsBridge = lMVar.f10340a;
        ZL zl = new ZL(list, (List) obj, vh0);
        N.M67g7Hwt(usageStatsBridge.b, usageStatsBridge, (String[]) list.toArray(new String[list.size()]), zl);
    }
}
