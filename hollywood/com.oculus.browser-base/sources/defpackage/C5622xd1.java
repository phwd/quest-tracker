package defpackage;

import J.N;
import android.view.ViewGroup;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.ui.base.ViewAndroidDelegate;

/* renamed from: xd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5622xd1 extends ViewAndroidDelegate {
    public final TabImpl f;
    public int g;
    public AbstractC0956Pq0 h;

    public C5622xd1(Tab tab, ViewGroup viewGroup) {
        super(viewGroup);
        TabImpl tabImpl = (TabImpl) tab;
        this.f = tabImpl;
        C5282vd1 vd1 = new C5282vd1(this);
        C2712ga gaVar = tabImpl.f10773J.O;
        this.h = gaVar;
        gaVar.l(vd1);
        tabImpl.P.b(new C5452wd1(this, vd1));
    }

    public final void b() {
        AbstractC0956Pq0 pq0;
        int intValue = (this.f.isHidden() || (pq0 = this.h) == null) ? 0 : ((Integer) ((C1078Rq0) pq0).H).intValue();
        if (intValue != this.g) {
            this.g = intValue;
            UL0 s = this.f.L.s();
            if (s != null) {
                RenderWidgetHostViewImpl renderWidgetHostViewImpl = (RenderWidgetHostViewImpl) s;
                long j = renderWidgetHostViewImpl.f10916a;
                if (j != 0) {
                    N.Myd8R_Wn(j, renderWidgetHostViewImpl);
                    return;
                }
                throw new IllegalStateException("Native RenderWidgetHostViewAndroid already destroyed", renderWidgetHostViewImpl.b);
            }
        }
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public int getViewportInsetBottom() {
        return this.g;
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void onBackgroundColorChanged(int i) {
        TabImpl tabImpl = this.f;
        Iterator it = tabImpl.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).j(tabImpl, i);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void onBottomControlsChanged(int i, int i2) {
        C1957c61 V = C1957c61.V(this.f);
        if (!V.L || V.H != i || V.K != i2) {
            V.H = i;
            V.K = i2;
            V.W();
        }
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void onTopControlsChanged(int i, int i2, int i3) {
        C1957c61 V = C1957c61.V(this.f);
        if (!V.L || i != V.G || V.I != i2 || V.f9583J != i3) {
            V.G = i;
            V.I = i2;
            V.f9583J = i3;
            V.W();
        }
    }
}
