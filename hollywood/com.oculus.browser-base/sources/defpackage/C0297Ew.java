package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;

/* renamed from: Ew  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0297Ew {

    /* renamed from: a  reason: collision with root package name */
    public final SceneLayer f7988a;
    public final UH0 b;
    public final YH0 c;
    public final C0236Dw d;
    public final AbstractC1641aI0 e;
    public final Callback f;

    public C0297Ew(UH0 uh0, SceneLayer sceneLayer, YH0 yh0, C0236Dw dw, boolean z) {
        this.b = uh0;
        this.f7988a = sceneLayer;
        this.c = yh0;
        this.d = dw;
        C0114Bw bw = new C0114Bw(this);
        this.f = bw;
        dw.l(bw);
        if (z) {
            dw.f7925J.run();
        }
        C0175Cw cw = new C0175Cw(this);
        this.e = cw;
        uh0.f9530a.b(cw);
    }
}
