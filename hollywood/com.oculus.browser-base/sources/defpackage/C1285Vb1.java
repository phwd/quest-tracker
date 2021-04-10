package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* renamed from: Vb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1285Vb1 extends MO0 {
    public long G;
    public final float H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f9094J;

    public C1285Vb1(Context context) {
        this.H = context.getResources().getDisplayMetrics().density;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public void b() {
        if (this.G == 0) {
            this.G = N.M8m15MW0(this);
        }
    }

    @Override // defpackage.MO0
    public void d(SceneLayer sceneLayer) {
        N.Mm9evJzJ(this.G, this, sceneLayer);
    }
}
