package defpackage;

import J.N;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: hj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2912hj1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3766mj1 f10096a;
    public final String b;
    public final C5232vH0 c;

    public C2912hj1(C3766mj1 mj1, String str, C5232vH0 vh0) {
        this.f10096a = mj1;
        this.b = str;
        this.c = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3766mj1 mj1 = this.f10096a;
        String str = this.b;
        C5232vH0 vh0 = this.c;
        Map map = (Map) obj;
        Objects.requireNonNull(mj1);
        if (!map.containsKey(str)) {
            vh0.b(null);
            return;
        }
        AbstractC6004zr1.a(3);
        HashMap hashMap = new HashMap(map);
        hashMap.remove(str);
        UsageStatsBridge usageStatsBridge = mj1.b;
        C3595lj1 lj1 = new C3595lj1(map, str, vh0);
        Objects.requireNonNull(usageStatsBridge);
        String[] strArr = new String[hashMap.size()];
        String[] strArr2 = new String[hashMap.size()];
        int i = 0;
        for (Map.Entry entry : hashMap.entrySet()) {
            strArr[i] = (String) entry.getKey();
            strArr2[i] = (String) entry.getValue();
            i++;
        }
        N.Mz1N0m$q(usageStatsBridge.b, usageStatsBridge, strArr, strArr2, lj1);
    }
}
