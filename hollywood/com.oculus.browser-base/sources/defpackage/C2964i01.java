package defpackage;

import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tasks.TasksView;
import org.chromium.chrome.browser.tasks.tab_management.TabManagementDelegateImpl;

/* renamed from: i01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2964i01 {

    /* renamed from: a  reason: collision with root package name */
    public final C3818n01 f10117a;

    public C2964i01(C3818n01 n01) {
        this.f10117a = n01;
    }

    public AbstractC5279vc1 a() {
        C3818n01 n01 = this.f10117a;
        Objects.requireNonNull(n01);
        UH0 uh0 = new UH0(AbstractC5798yf1.x);
        M01 m01 = n01.c;
        m01.O = uh0;
        uh0.j(AbstractC5798yf1.b, m01.P);
        UH0 uh02 = m01.O;
        QH0 qh0 = AbstractC5798yf1.f11692a;
        int i = 0;
        uh02.j(AbstractC0703Ll0.f8436a, false);
        m01.O.j(AbstractC5798yf1.g, false);
        TabManagementDelegateImpl a2 = AbstractC1680aa1.a();
        ChromeActivity chromeActivity = n01.f10468a;
        GP0 gp0 = n01.b;
        if (AbstractC2793h01.d()) {
            i = 3;
        }
        AbstractC4096of1 b = a2.b(chromeActivity, gp0, uh0, i, n01.f, false, false);
        n01.h = b;
        if (n01.m) {
            ChromeActivity chromeActivity2 = n01.f10468a;
            ((C4778sf1) b).c(chromeActivity2, chromeActivity2.b1.V.j());
            ((C4778sf1) n01.h).b();
        } else {
            n01.o = true;
        }
        ((C4778sf1) n01.h).b.setId(R.id.secondary_tasks_surface_view);
        UH0 uh03 = n01.k;
        CompositorViewHolder compositorViewHolder = n01.f10468a.I0;
        TasksView tasksView = ((C4778sf1) n01.h).b;
        ZH0.a(uh03, new C5968zf1(compositorViewHolder, tasksView, tasksView != null ? tasksView.findViewById(R.id.top_toolbar_placeholder) : null), new C3647m01());
        AbstractC5449wc1 wc1 = n01.l;
        if (wc1 != null) {
            AbstractC5959zc1 zc1 = ((C4778sf1) n01.h).f11290a;
            if (zc1 != null) {
                zc1.i(wc1);
            }
            n01.l = null;
        }
        AbstractC5959zc1 zc12 = ((C4778sf1) n01.h).f11290a;
        if (zc12 != null) {
            return zc12.h();
        }
        return null;
    }
}
