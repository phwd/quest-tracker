package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: m41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3659m41 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4172p41 f10395a;
    public final boolean b;
    public final List c;
    public final C5232vH0 d;

    public C3659m41(C4172p41 p41, boolean z, List list, C5232vH0 vh0) {
        this.f10395a = p41;
        this.b = z;
        this.c = list;
        this.d = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4172p41 p41 = this.f10395a;
        boolean z = this.b;
        List list = this.c;
        C5232vH0 vh0 = this.d;
        List list2 = (List) obj;
        Objects.requireNonNull(p41);
        ArrayList arrayList = new ArrayList(list2);
        if (z) {
            AbstractC6004zr1.a(4);
            arrayList.addAll(list);
        } else {
            AbstractC6004zr1.a(5);
            arrayList.removeAll(list);
        }
        UsageStatsBridge usageStatsBridge = p41.f11047a;
        C4001o41 o41 = new C4001o41(p41, z, list2, list, vh0);
        N.M2UQ4Zbr(usageStatsBridge.b, usageStatsBridge, (String[]) arrayList.toArray(new String[arrayList.size()]), o41);
    }
}
