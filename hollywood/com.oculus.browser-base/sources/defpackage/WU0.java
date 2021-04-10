package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: WU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class WU0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f9150a;

    public WU0(Tab tab) {
        this.f9150a = tab;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tab tab = this.f9150a;
        Callback callback = (Callback) obj;
        if (System.currentTimeMillis() - C5383wB.q(tab).S > TimeUnit.SECONDS.toMillis((long) C2361eV0.Q.c())) {
            callback.onResult(null);
            return;
        }
        GURL url = tab.getUrl();
        YU0 yu0 = new YU0(callback, tab, (C2361eV0) ((AbstractC2145dC0) tab.M().c(C2361eV0.class)));
        C2257dt0 dt0 = AbstractC1849bV0.f9543a;
        Objects.requireNonNull(dt0);
        Profile b = Profile.b();
        OptimizationGuideBridge optimizationGuideBridge = (OptimizationGuideBridge) dt0.f9816a.get(b);
        if (optimizationGuideBridge == null) {
            optimizationGuideBridge = new OptimizationGuideBridge();
            List list = dt0.b;
            Object obj2 = ThreadUtils.f10596a;
            if (optimizationGuideBridge.f10727a != 0) {
                if (list == null) {
                    list = new ArrayList();
                }
                int[] iArr = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    iArr[i] = ((EnumC3731mX) list.get(i)).W;
                }
                N.MqYUgADF(optimizationGuideBridge.f10727a, iArr);
            }
            dt0.f9816a.put(b, optimizationGuideBridge);
        }
        XU0 xu0 = new XU0(yu0);
        Object obj3 = ThreadUtils.f10596a;
        long j = optimizationGuideBridge.f10727a;
        if (j == 0) {
            yu0.onResult(true);
        } else {
            N.MqwRdGjQ(j, url, 15, xu0);
        }
    }
}
