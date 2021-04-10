package defpackage;

import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: d70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2130d70 {

    /* renamed from: a  reason: collision with root package name */
    public final C0204Dh f9749a;
    public final C0204Dh b;
    public boolean c;
    public final /* synthetic */ LayerTitleCache d;

    public C2130d70(LayerTitleCache layerTitleCache) {
        this.d = layerTitleCache;
        int i = LayerTitleCache.f10634a;
        LayerTitleCache.f10634a = i + 1;
        this.f9749a = new C0204Dh(i);
        int i2 = LayerTitleCache.f10634a;
        LayerTitleCache.f10634a = i2 + 1;
        this.b = new C0204Dh(i2);
    }

    public void a() {
        ResourceManager resourceManager = this.d.g;
        if (resourceManager != null) {
            IJ ij = (IJ) resourceManager.f11027a.get(2);
            ij.d(this.f9749a.F);
            ij.d(this.b.F);
        }
    }
}
