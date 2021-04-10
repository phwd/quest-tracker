package defpackage;

import J.N;
import android.graphics.RectF;
import java.util.List;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: s21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4678s21 extends MO0 implements LO0 {
    public long G;
    public int H;
    public AbstractC2400ek I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f11247J;

    public C4678s21(AbstractC2400ek ekVar) {
        this.I = ekVar;
    }

    @Override // defpackage.LO0
    public boolean a() {
        return false;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public void b() {
        if (this.G == 0) {
            this.G = N.MvcFT3Dn(this);
        }
    }

    @Override // defpackage.LO0
    public VL c() {
        return null;
    }

    @Override // defpackage.MO0
    public void d(SceneLayer sceneLayer) {
        N.MqEZWVhE(this.G, this, sceneLayer);
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
        N.MsJYu3I7(this.G, this, resourceManager, this.H, ((C1551Zj) this.I).U);
        return this;
    }

    @Override // defpackage.LO0
    public void s(List list) {
    }

    @Override // defpackage.LO0
    public boolean u() {
        return this.f11247J;
    }

    @Override // defpackage.LO0
    public boolean v() {
        return false;
    }
}
