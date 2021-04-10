package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Sw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1150Sw extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CompositorViewHolder f8926a;

    public C1150Sw(CompositorViewHolder compositorViewHolder) {
        this.f8926a = compositorViewHolder;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        this.f8926a.g0.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC1089Rw(this));
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.f8926a.A();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void o(boolean z) {
        CompositorViewHolder compositorViewHolder = this.f8926a;
        compositorViewHolder.o0 = z;
        if (!z) {
            compositorViewHolder.H();
        }
    }
}
