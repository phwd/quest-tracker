package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ww  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1394Ww extends VK {
    public final /* synthetic */ CompositorViewHolder F;

    public C1394Ww(CompositorViewHolder compositorViewHolder) {
        this.F = compositorViewHolder;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void e() {
        this.F.A();
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void f(Tab tab, int i) {
        CompositorViewHolder compositorViewHolder = this.F;
        int i2 = CompositorViewHolder.F;
        compositorViewHolder.v(tab);
    }
}
