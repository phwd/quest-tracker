package defpackage;

import java.util.Map;
import java.util.Objects;

/* renamed from: fj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2570fj1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3766mj1 f9945a;

    public C2570fj1(C3766mj1 mj1) {
        this.f9945a = mj1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3766mj1 mj1 = this.f9945a;
        Map map = (Map) obj;
        Objects.requireNonNull(mj1);
        long j = 0;
        for (Map.Entry entry : map.entrySet()) {
            j = Math.max(j, Long.valueOf((String) entry.getKey()).longValue());
        }
        mj1.f10443a.b(map);
    }
}
