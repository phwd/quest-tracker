package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: NT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NT extends AbstractC1099Sa1 {
    public final /* synthetic */ AbstractC0124Ca1 I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ ST f8548J;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NT(ST st, AbstractC0124Ca1 ca1, AbstractC0124Ca1 ca12) {
        super(ca1);
        this.f8548J = st;
        this.I = ca12;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.f8548J.c();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void C(Tab tab, boolean z) {
        if (z && tab == ((AbstractC0246Ea1) this.I).j()) {
            Objects.requireNonNull(this.f8548J);
            Runnable runnable = (Runnable) Y51.c(tab).e("EnterFullscreen");
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.f8548J.j(tab.u());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !navigationHandle.c && tab == ((AbstractC0246Ea1) this.I).j()) {
            this.f8548J.c();
        }
    }
}
