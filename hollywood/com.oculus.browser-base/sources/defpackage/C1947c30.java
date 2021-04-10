package defpackage;

import J.N;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content_public.browser.WebContents;

/* renamed from: c30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1947c30 implements InterceptNavigationDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0003Ab f9581a;
    public Y20 b;
    public int c = 3;
    public WebContents d;
    public C3198jN e;
    public boolean f;
    public boolean g;

    public C1947c30(Y20 y20) {
        this.b = y20;
        this.f9581a = AppHooks.get().b();
        a(((C1596a30) this.b).f9405a.L);
    }

    public void a(WebContents webContents) {
        if (this.d != webContents) {
            this.d = webContents;
            if (webContents != null) {
                if (this.e == null) {
                    TabImpl tabImpl = ((C1596a30) this.b).f9405a;
                    this.e = tabImpl.e0.c(tabImpl);
                }
                N.Mjjyc5BV(this, this.d);
            }
        }
    }

    public final int b() {
        Y20 y20 = this.b;
        if (((C1596a30) y20).f9405a.L == null) {
            return -1;
        }
        return ((C1596a30) y20).f9405a.L.f().m();
    }

    public final void c(boolean z) {
        int i;
        C1596a30 a30 = (C1596a30) this.b;
        TabImpl tabImpl = a30.f9405a;
        if (tabImpl.L != null) {
            boolean z2 = true;
            boolean z3 = false;
            if (z) {
                if (tabImpl.F() != 1) {
                    z2 = false;
                }
                if (z2) {
                    ((C1596a30) this.b).f9405a.W().moveTaskToBack(false);
                }
                PostTask.b(Zo1.f9374a, new RunnableC1776b30(this), 0);
                return;
            }
            if (a30.a().g != 0) {
                z3 = true;
            }
            if (z3 && b() > (i = ((C1596a30) this.b).a().h)) {
                this.f = true;
                ((C1596a30) this.b).f9405a.L.f().v(i);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006d, code lost:
        if (((defpackage.C1596a30) r24.b).a().h == -1) goto L_0x006f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0126  */
    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldIgnoreNavigation(org.chromium.components.navigation_interception.NavigationParams r25) {
        /*
        // Method dump skipped, instructions count: 303
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1947c30.shouldIgnoreNavigation(org.chromium.components.navigation_interception.NavigationParams):boolean");
    }
}
