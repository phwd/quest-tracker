package org.chromium.chrome.browser.compositor.scene_layer;

import J.N;
import android.text.TextUtils;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextualSearchSceneLayer extends MO0 {
    public long G;
    public boolean H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public C0242Dz f10637J;

    public ContextualSearchSceneLayer(float f) {
        this.I = f;
    }

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public void b() {
        if (this.G == 0) {
            this.G = N.MPHuAHE_(this);
        }
    }

    @Override // defpackage.MO0
    public void d(SceneLayer sceneLayer) {
        N.MhNzVlSH(this.G, this, sceneLayer);
    }

    public void onThumbnailFetched(boolean z) {
        C0242Dz dz = this.f10637J;
        if (dz != null) {
            boolean z2 = z && !TextUtils.isEmpty(dz.e);
            dz.f = z2;
            if (z2) {
                dz.a(true);
            }
        }
    }
}
