package defpackage;

import J.N;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: iM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3026iM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3538lM f10132a;
    public final long b;
    public final long c;
    public final C5232vH0 d;

    public C3026iM(C3538lM lMVar, long j, long j2, C5232vH0 vh0) {
        this.f10132a = lMVar;
        this.b = j;
        this.c = j2;
        this.d = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3538lM lMVar = this.f10132a;
        long j = this.b;
        long j2 = this.c;
        C5232vH0 vh0 = this.d;
        UsageStatsBridge usageStatsBridge = lMVar.f10340a;
        C1650aM aMVar = new C1650aM(j, j2, (List) obj, vh0);
        Objects.requireNonNull(usageStatsBridge);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        N.Mr1dopkU(usageStatsBridge.b, usageStatsBridge, timeUnit.toSeconds(j), timeUnit.toSeconds(j2), aMVar);
    }
}
