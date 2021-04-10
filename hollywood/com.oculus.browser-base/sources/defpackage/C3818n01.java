package defpackage;

import J.N;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tasks.TasksView;
import org.chromium.chrome.features.start_surface.BottomBarView;
import org.chromium.chrome.features.start_surface.FeedLoadingLayout;
import org.chromium.components.prefs.PrefService;

/* renamed from: n01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3818n01 implements AbstractC2451f01 {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f10468a;
    public final GP0 b;
    public final M01 c;
    public final int d;
    public final AbstractC4448qj e;
    public final Q31 f;
    public AbstractC4096of1 g;
    public AbstractC4096of1 h;
    public AbstractC5959zc1 i;
    public FM j;
    public UH0 k;
    public AbstractC5449wc1 l;
    public boolean m;
    public boolean n;
    public boolean o;
    public C5927zO p;

    public C3818n01(ChromeActivity chromeActivity, GP0 gp0, AbstractC4448qj qjVar, C1570Zs0 zs0, Q31 q31, boolean z) {
        int i2;
        int i3;
        AbstractC5279vc1 vc1;
        this.f10468a = chromeActivity;
        this.b = gp0;
        if (!AbstractC2793h01.b()) {
            i2 = 0;
        } else {
            String c2 = AbstractC2793h01.f10042a.c();
            if (c2.equals("twopanes")) {
                i2 = 2;
            } else if (c2.equals("single") || AbstractC2793h01.c()) {
                i2 = 3;
            } else {
                if (!c2.equals("tasksonly")) {
                    if (c2.equals("omniboxonly")) {
                        i2 = 4;
                    } else if (c2.equals("trendyterms")) {
                        i2 = 5;
                    }
                }
                i2 = 1;
            }
        }
        this.d = i2;
        this.e = qjVar;
        this.f = q31;
        boolean z2 = AbstractC2793h01.b.c() || i2 == 4 || i2 == 5 || i2 == 0;
        boolean z3 = i2 == 5;
        if (i2 == 0) {
            this.i = AbstractC1680aa1.a().a(chromeActivity, chromeActivity.I0, gp0);
            i3 = i2;
        } else {
            ArrayList arrayList = new ArrayList(Arrays.asList(AbstractC5798yf1.x));
            arrayList.addAll(Arrays.asList(N01.l));
            this.k = new UH0(arrayList);
            i3 = i2;
            AbstractC4096of1 b2 = AbstractC1680aa1.a().b(chromeActivity, gp0, this.k, AbstractC2793h01.e.c() ? 2 : i2 == 3 ? 1 : 0, q31, !z2, z3);
            this.g = b2;
            ((C4778sf1) b2).b.setId(R.id.primary_tasks_surface_view);
            TasksView tasksView = ((C4778sf1) this.g).b;
            if (tasksView.i0 != null) {
                if (tasksView.j0 == null) {
                    int dimensionPixelSize = tasksView.getResources().getDimensionPixelSize(R.dimen.f23120_resource_name_obfuscated_RES_2131165931);
                    int dimensionPixelSize2 = tasksView.getResources().getDimensionPixelSize(R.dimen.f20540_resource_name_obfuscated_RES_2131165673);
                    View findViewById = tasksView.findViewById(R.id.search_box);
                    if (findViewById != null) {
                        tasksView.j0 = new C0139Cf1(tasksView, dimensionPixelSize, dimensionPixelSize2, findViewById);
                    }
                }
                tasksView.i0.a(tasksView.j0);
            }
            UH0 uh0 = this.k;
            CompositorViewHolder compositorViewHolder = chromeActivity.I0;
            TasksView tasksView2 = ((C4778sf1) this.g).b;
            ZH0.a(uh0, new C5968zf1(compositorViewHolder, tasksView2, tasksView2 != null ? tasksView2.findViewById(R.id.top_toolbar_placeholder) : null), new C3476l01());
            if (!(i3 == 1 || i3 == 4 || i3 == 5 || i3 != 2)) {
                ZH0.a(this.k, (BottomBarView) LayoutInflater.from(chromeActivity).inflate(R.layout.f41540_resource_name_obfuscated_RES_2131624463, (ViewGroup) chromeActivity.I0, true).findViewById(R.id.ss_bottom_bar), new C1548Zi());
            }
        }
        AbstractC5959zc1 zc1 = this.i;
        if (zc1 != null) {
            vc1 = zc1.h();
        } else {
            AbstractC5959zc1 zc12 = ((C4778sf1) this.g).f11290a;
            vc1 = zc12 != null ? zc12.h() : null;
        }
        M01 m01 = new M01(vc1, chromeActivity.P(), this.k, i3 == 3 ? new C2964i01(this) : null, i3, chromeActivity, chromeActivity.M0(), new C3134j01(this), z2, AbstractC2793h01.f.c(), zs0, z);
        this.c = m01;
        if (m01.p()) {
            ViewGroup z4 = ((C4778sf1) this.g).b.z();
            C5927zO zOVar = new C5927zO(chromeActivity, z4, false);
            this.p = zOVar;
            FeedLoadingLayout feedLoadingLayout = (FeedLoadingLayout) LayoutInflater.from(zOVar.f11742a).inflate(R.layout.f38530_resource_name_obfuscated_RES_2131624162, (ViewGroup) null, false);
            zOVar.c = feedLoadingLayout;
            z4.addView(feedLoadingLayout);
        }
        Objects.requireNonNull(zs0.G);
        zs0.F.b(this);
    }

    public AbstractC5789yc1 a() {
        AbstractC4096of1 of1 = this.g;
        if (of1 == null) {
            return this.i.n();
        }
        AbstractC5959zc1 zc1 = ((C4778sf1) of1).f11290a;
        if (zc1 != null) {
            return zc1.n();
        }
        return null;
    }

    public void b() {
        K21 k21;
        ViewGroup viewGroup;
        if (!this.m) {
            this.m = true;
            int i2 = this.d;
            if (i2 == 3 || i2 == 2) {
                ChromeActivity chromeActivity = this.f10468a;
                if (i2 == 3) {
                    viewGroup = ((C4778sf1) this.g).b.z();
                } else {
                    viewGroup = chromeActivity.I0;
                }
                this.j = new FM(chromeActivity, viewGroup, this.k, this.d == 3, this.e, this.f);
            }
            M01 m01 = this.c;
            EM em = null;
            AbstractC1834bO j2 = this.d != 0 ? this.f10468a.b1.V.j() : null;
            FM fm = this.j;
            if (fm != null) {
                em = fm.b;
            }
            PrefService a2 = Wr1.a(Profile.b());
            m01.Q = j2;
            m01.M = em;
            UH0 uh0 = m01.I;
            if (uh0 != null) {
                uh0.j(AbstractC5798yf1.g, ((View$OnKeyListenerC0001Aa0) j2).C().b());
                if (m01.G.f()) {
                    ((View$OnKeyListenerC0001Aa0) m01.Q).p(m01.S);
                    if (m01.T == 1 && m01.M != null) {
                        m01.i(true ^ m01.P);
                    }
                }
                XO xo = (XO) m01.I.g(N01.i);
                if (!(xo == null || (k21 = xo.t) == null)) {
                    ((FO) k21).f8014a.k.b(new D01(k21));
                }
            }
            m01.f0 = Boolean.valueOf(N.MzIXnlkD(a2.f10883a, "ntp_snippets.list_visible"));
            AbstractC5959zc1 zc1 = this.i;
            if (zc1 != null) {
                ChromeActivity chromeActivity2 = this.f10468a;
                TabContentManager tabContentManager = chromeActivity2.x0;
                IJ i3 = chromeActivity2.I0.i();
                ChromeActivity chromeActivity3 = this.f10468a;
                zc1.o(chromeActivity2, tabContentManager, i3, chromeActivity3, chromeActivity3.l0());
            }
            AbstractC4096of1 of1 = this.g;
            if (of1 != null) {
                ChromeActivity chromeActivity4 = this.f10468a;
                ((C4778sf1) of1).c(chromeActivity4, chromeActivity4.b1.V.j());
            }
            if (this.n) {
                c();
            }
            if (this.o) {
                this.o = false;
                AbstractC4096of1 of12 = this.h;
                ChromeActivity chromeActivity5 = this.f10468a;
                ((C4778sf1) of12).c(chromeActivity5, chromeActivity5.b1.V.j());
                ((C4778sf1) this.h).b();
            }
        }
    }

    public void c() {
        if (!this.m) {
            this.n = true;
            return;
        }
        this.n = false;
        AbstractC4096of1 of1 = this.g;
        if (of1 != null) {
            ((C4778sf1) of1).b();
        }
    }

    public boolean d() {
        int i2 = this.c.T;
        return i2 == 8 || i2 == 2;
    }

    public void e(AbstractC2110d01 d01) {
        AbstractC4096of1 of1 = this.g;
        if (of1 != null) {
            AbstractC5959zc1 zc1 = ((C4778sf1) of1).f11290a;
            if (zc1 != null) {
                zc1.i(d01);
            }
        } else {
            this.i.i(d01);
        }
        if (this.d == 3) {
            AbstractC4096of1 of12 = this.h;
            if (of12 == null) {
                this.l = d01;
                return;
            }
            AbstractC5959zc1 zc12 = ((C4778sf1) of12).f11290a;
            if (zc12 != null) {
                zc12.i(d01);
            }
        }
    }
}
