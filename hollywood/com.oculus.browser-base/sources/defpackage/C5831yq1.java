package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.Objects;

/* renamed from: yq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5831yq1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C6001zq1 f11704a;

    public C5831yq1(C6001zq1 zq1) {
        this.f11704a = zq1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        long j;
        C6001zq1 zq1 = this.f11704a;
        C4300pq1 pq1 = (C4300pq1) obj;
        Objects.requireNonNull(zq1);
        if (pq1 != null) {
            long currentTimeMillis = System.currentTimeMillis() - pq1.h;
            try {
                j = Long.parseLong(N.MMltG$kc("InlineUpdateFlow", "update_attribution_window_days")) * 86400000;
            } catch (NumberFormatException unused) {
                j = 172800000;
            }
            boolean z = currentTimeMillis > j;
            boolean z2 = !TextUtils.equals(pq1.i, "89.0.4389.105");
            if (!pq1.l) {
                AbstractC3100ip1.f10165a.a(AbstractC4073oX.a(0, null), z2);
                AbstractC3100ip1.f10165a.a(AbstractC4073oX.a(0, pq1), z2);
            }
            if (z2 || z) {
                AbstractC3100ip1.f10165a.a(AbstractC4073oX.a(1, null), z2);
                AbstractC3100ip1.f10165a.a(AbstractC4073oX.a(1, pq1), z2);
            }
            if (z2 || z) {
                zq1.f11773a.f9370a.b(new Wm1());
            } else if (!pq1.l) {
                Zm1 zm1 = zq1.f11773a;
                C4300pq1 pq12 = C4300pq1.e;
                C4300pq1 pq13 = C4300pq1.e;
                C4300pq1 pq14 = new C4300pq1();
                C2163dI0 di0 = C2163dI0.f9768a;
                di0.b(pq14).b(pq14, pq1);
                pq14.g |= 16;
                pq14.l = true;
                di0.b(pq14).c(pq14);
                if (pq14.i()) {
                    zm1.f9370a.b(new Xm1(pq14));
                    return;
                }
                throw new C5488wp1();
            }
        }
    }
}
