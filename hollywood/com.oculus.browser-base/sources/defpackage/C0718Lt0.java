package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;

/* renamed from: Lt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0718Lt0 implements InterceptNavigationDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final C3198jN f8443a;
    public final /* synthetic */ OverlayPanelContent b;

    public C0718Lt0(OverlayPanelContent overlayPanelContent) {
        this.b = overlayPanelContent;
        Tab K0 = overlayPanelContent.e.K0();
        this.f8443a = (K0 == null || K0.l() == null) ? null : new C3198jN(new C2003cN(K0));
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public boolean shouldIgnoreNavigation(NavigationParams navigationParams) {
        C3198jN jNVar = this.f8443a;
        if (jNVar == null || navigationParams == null) {
            return true;
        }
        C1461Xz xz = (C1461Xz) this.b.l;
        ContextualSearchManager contextualSearchManager = xz.f9246a;
        C2171dL0 dl0 = contextualSearchManager.W;
        int i = navigationParams.d;
        boolean z = navigationParams.e;
        boolean z2 = false;
        boolean z3 = navigationParams.c || navigationParams.g;
        long j = contextualSearchManager.G.g0;
        long j2 = dl0.e;
        dl0.e = SystemClock.elapsedRealtime();
        int i2 = i & 255;
        boolean z4 = i2 == 0 && (134217728 & i) != 0;
        if (!z && ((i & 16777216) != 0 || (!(i2 == 0 || i2 == 7) || j2 == -1 || z4 || j > j2))) {
            if (!z4 || dl0.f9773a == null) {
                dl0.c = false;
                dl0.d = false;
                dl0.f9773a = null;
                dl0.b.clear();
                if (i2 == 1) {
                    dl0.g = 2;
                } else if (i2 == 8 || (i & 16777216) != 0) {
                    dl0.g = 4;
                } else if (i2 != 0 || z3) {
                    dl0.g = 5;
                } else {
                    dl0.g = 3;
                }
            } else {
                dl0.g = 1;
            }
            dl0.f = false;
            dl0.h = -1;
            dl0.i = false;
        } else if (dl0.g != 0) {
            dl0.f = true;
        }
        if (jNVar.o(new C3540lN(navigationParams.f10856a, false, navigationParams.b, navigationParams.d, navigationParams.e, true, xz.f9246a.W, false, false, navigationParams.h, null, false, false, false, null, null)).f10134a == 3) {
            z2 = !navigationParams.f;
        }
        return !z2;
    }
}
