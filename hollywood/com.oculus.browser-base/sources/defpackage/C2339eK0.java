package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* renamed from: eK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2339eK0 extends AbstractC6022zx1 {
    public boolean G;
    public int H;
    public final /* synthetic */ C2510fK0 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2339eK0(C2510fK0 fk0, WebContents webContents) {
        super(webContents);
        this.I = fk0;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f && navigationHandle.f10940a && !navigationHandle.c) {
            if (this.G) {
                this.G = false;
                NavigationController f = ((WebContents) this.F.get()).f();
                if (f.l(this.H) != null) {
                    f.i(this.H);
                }
            }
            C2510fK0 fk0 = this.I;
            if (!fk0.P) {
                fk0.I = 0;
                if (!TextUtils.equals(navigationHandle.e.f11029a, HG.a(fk0.F))) {
                    C2510fK0 fk02 = this.I;
                    fk02.I = 1;
                    fk02.G = false;
                }
                C2510fK0 fk03 = this.I;
                fk03.F = null;
                if (fk03.I == 0) {
                    fk03.Y();
                }
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !navigationHandle.c) {
            NavigationController f = ((WebContents) this.F.get()).f();
            int m = f.m();
            NavigationEntry l = f.l(m);
            if (l != null && HG.c(l.b.h())) {
                this.G = true;
                this.H = m;
            }
            C2510fK0 fk0 = this.I;
            if (!fk0.P) {
                String str = navigationHandle.e.f11029a;
                fk0.K = str;
                if (HG.c(str)) {
                    C2510fK0 fk02 = this.I;
                    fk02.I = 2;
                    fk02.F = navigationHandle.e.f11029a;
                }
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        C2510fK0 fk0 = this.I;
        if (!fk0.P) {
            fk0.f9915J = false;
            Tab tab = fk0.Q;
            if (tab != null && !tab.isNativePage() && !this.I.Q.J()) {
                Objects.requireNonNull(this.I);
                AbstractC3100ip1.f10165a.a("DomDistiller.ReaderShownForPageLoad", false);
            }
            C2510fK0 fk02 = this.I;
            fk02.L = false;
            Tab tab2 = fk02.Q;
            if (tab2 != null && !HG.c(tab2.s())) {
                C2510fK0 fk03 = this.I;
                if (fk03.M) {
                    long W = fk03.W();
                    Objects.requireNonNull(this.I);
                    AbstractC3364kK0.i("DomDistiller.Time.ViewingReaderModePage", W);
                }
            }
        }
    }
}
