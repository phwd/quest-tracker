package defpackage;

import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* renamed from: Tl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Tl1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ToolbarControlContainer f8981a;

    public Tl1(ToolbarControlContainer toolbarControlContainer) {
        this.f8981a = toolbarControlContainer;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4456ql1 ql1;
        C3971nv nvVar = (C3971nv) obj;
        AbstractC5130uj1 uj1 = this.f8981a.I;
        if (uj1 != null && (ql1 = ((Vl1) uj1).f9104a.L) != null) {
            int color = ql1.F.getColor();
            float alpha = ql1.getVisibility() == 0 ? ql1.getAlpha() : 0.0f;
            nvVar.c = (color & 16777215) | (Math.round(((float) (color >>> 24)) * alpha) << 24);
            int i = ql1.G;
            nvVar.d = (i & 16777215) | (Math.round(alpha * ((float) (i >>> 24))) << 24);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (ql1.getLayoutDirection() == 0) {
                nvVar.f10520a.set(ql1.getLeft(), ql1.getTop(), Math.round(ql1.H * ((float) ql1.getWidth())) + ql1.getLeft(), ql1.getBottom());
                nvVar.b.set(nvVar.f10520a.right, ql1.getTop(), ql1.getRight(), ql1.getBottom());
                return;
            }
            nvVar.f10520a.set(ql1.getRight() - Math.round(ql1.H * ((float) ql1.getWidth())), ql1.getTop(), ql1.getRight(), ql1.getBottom());
            nvVar.b.set(ql1.getLeft(), ql1.getTop(), nvVar.f10520a.left, ql1.getBottom());
        }
    }
}
