package defpackage;

import android.view.KeyEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Qb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0980Qb1 extends AbstractC2742gk implements WZ {

    /* renamed from: J  reason: collision with root package name */
    public final TabImpl f8771J;
    public WebContents K;
    public boolean L;

    public C0980Qb1(Tab tab) {
        super(3);
        TabImpl tabImpl = (TabImpl) tab;
        this.f8771J = tabImpl;
        tabImpl.P.b(new C0919Pb1(this));
        q(tabImpl.L);
    }

    public static void o(C0980Qb1 qb1, boolean z) {
        if (qb1.L != z) {
            qb1.L = z;
            qb1.r();
        }
    }

    @Override // defpackage.WZ
    public void a() {
    }

    @Override // defpackage.WZ
    public void d(KeyEvent keyEvent) {
    }

    @Override // defpackage.WZ
    public void i(boolean z, boolean z2) {
        r();
    }

    public int p() {
        WebContents webContents = this.f8771J.L;
        boolean z = false;
        if (webContents != null && !webContents.g()) {
            String s = this.f8771J.s();
            boolean z2 = (!s.startsWith("chrome-native://")) & (s != null) & (!s.startsWith("chrome://"));
            if (LR0.a(this.f8771J.L) == 5) {
                z = true;
            }
            boolean z3 = (!SelectionPopupControllerImpl.r(webContents).U) & (!z) & z2;
            TabImpl tabImpl = this.f8771J;
            z = C5052uE.c().g & z3 & (!tabImpl.T) & (!tabImpl.c0) & (!tabImpl.isHidden()) & (!this.L) & (!C0283Ep.h().d());
        }
        if (z) {
            return 3;
        }
        return 1;
    }

    public final void q(WebContents webContents) {
        if (this.K != webContents) {
            this.K = webContents;
            if (webContents != null) {
                ImeAdapterImpl.s0(webContents).N.add(this);
            }
        }
    }

    public void r() {
        m(Integer.valueOf(p()));
    }
}
