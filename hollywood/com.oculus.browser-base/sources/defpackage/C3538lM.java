package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: lM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3538lM {

    /* renamed from: a  reason: collision with root package name */
    public final UsageStatsBridge f10340a;
    public C5232vH0 b;

    public C3538lM(UsageStatsBridge usageStatsBridge) {
        this.f10340a = usageStatsBridge;
        C5232vH0 vh0 = new C5232vH0();
        this.b = vh0;
        vh0.a(new XL());
        N.M6Rdk6FF(usageStatsBridge.b, usageStatsBridge, new C2172dM(this));
    }

    public static int b(long j, List list) {
        for (int i = 0; i < list.size(); i++) {
            if (j <= ((C3811my1) list.get(i)).f10463a) {
                return i;
            }
        }
        return list.size();
    }

    public C5232vH0 a(long j, long j2) {
        C5232vH0 vh0 = new C5232vH0();
        C5232vH0 vh02 = this.b;
        C3026iM iMVar = new C3026iM(this, j, j2, vh0);
        C3196jM jMVar = new C3196jM();
        vh02.h(iMVar);
        vh02.a(jMVar);
        return vh0;
    }
}
