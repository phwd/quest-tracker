package defpackage;

import J.N;
import android.graphics.RectF;
import java.util.List;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: VP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VP0 extends MO0 implements LO0 {
    public long G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f9081J;
    public boolean K = true;
    public ViewResourceFrameLayout L;

    public VP0(ViewResourceFrameLayout viewResourceFrameLayout, int i) {
        this.L = viewResourceFrameLayout;
        this.H = viewResourceFrameLayout.getId();
        this.I = i;
    }

    @Override // defpackage.LO0
    public boolean a() {
        return false;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public void b() {
        if (this.G == 0) {
            this.G = N.MSESypSx(this);
        }
    }

    @Override // defpackage.LO0
    public VL c() {
        return null;
    }

    @Override // defpackage.MO0
    public void d(SceneLayer sceneLayer) {
        N.MwebgKpL(this.G, this, sceneLayer);
    }

    @Override // defpackage.LO0
    public boolean e(long j, long j2) {
        return false;
    }

    @Override // defpackage.LO0
    public boolean k() {
        return false;
    }

    @Override // defpackage.LO0
    public void m(float f, float f2, float f3, int i) {
    }

    @Override // defpackage.LO0
    public MO0 q(RectF rectF, RectF rectF2, ResourceManager resourceManager, float f) {
        N.MpLDHW_D(this.G, this, resourceManager, this.H, this.I, (float) 0, rectF.height() + ((float) this.f9081J), this.L.getVisibility() != 0);
        return this;
    }

    @Override // defpackage.LO0
    public void s(List list) {
    }

    @Override // defpackage.LO0
    public boolean u() {
        return this.K && this.f9081J < this.L.getHeight() - this.I;
    }

    @Override // defpackage.LO0
    public boolean v() {
        return false;
    }
}
