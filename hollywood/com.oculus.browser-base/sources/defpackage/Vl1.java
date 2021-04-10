package defpackage;

import android.view.View;
import android.view.ViewStub;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.StartSurfaceToolbarView;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: Vl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Vl1 implements AbstractC5130uj1 {

    /* renamed from: a  reason: collision with root package name */
    public final Wj1 f9104a;
    public C4261pd1 b;
    public T01 c;
    public C2770gt0 d;
    public C5976zi0 e;
    public AbstractC0956Pq0 f;
    public AbstractC0956Pq0 g;
    public ToolbarControlContainer h;
    public Q31 i;
    public Xl1 j;

    public Vl1(ToolbarControlContainer toolbarControlContainer, Wj1 wj1, Sj1 sj1, C5476wl1 wl1, Vr1 vr1, List list, AbstractC1509Ys0 ys0, AbstractC1117Sg1 sg1, AbstractC1117Sg1 sg12, C5976zi0 zi0, C5976zi0 zi02, AbstractC0956Pq0 pq0, AbstractC0956Pq0 pq02, AbstractC0956Pq0 pq03, AbstractC0956Pq0 pq04, Callback callback, Q31 q31, Q31 q312) {
        this.h = toolbarControlContainer;
        this.f9104a = wj1;
        this.e = zi0;
        this.d = new C2770gt0(list, vr1, wj1, new Ql1(sj1));
        this.i = q312;
        this.g = pq02;
        if (wj1 instanceof ToolbarPhone) {
            if (AbstractC2793h01.b()) {
                this.c = new T01((ViewStub) toolbarControlContainer.getRootView().findViewById(R.id.tab_switcher_toolbar_stub), vr1, ys0, pq04, sg12, zi02, q31);
            } else {
                this.b = new C4261pd1((ViewStub) toolbarControlContainer.getRootView().findViewById(R.id.tab_switcher_toolbar_stub), zi02);
            }
        }
        toolbarControlContainer.I = this;
        Rj1 rj1 = (Rj1) toolbarControlContainer.f10790J.G;
        rj1.P = this;
        rj1.Q = wj1.k();
        if (toolbarControlContainer.findViewById(R.id.toolbar) instanceof ToolbarTablet) {
            toolbarControlContainer.setBackgroundResource(R.drawable.f35360_resource_name_obfuscated);
        }
        wj1.o(sj1, wl1, this.e);
        wj1.Q = sg1;
        sg1.K.b(wj1);
        wj1.Q.f8908J.b(wj1);
        this.f = pq0;
        new C1326Vs0(pq0, new Rl1(this));
        ((C1078Rq0) pq03).l(new Sl1(this));
        wj1.F = callback;
    }

    public int a() {
        return this.f9104a.getHeight();
    }

    public void b(boolean z, boolean z2, boolean z3) {
        this.f9104a.P(z, z2, z3, this.e);
        C4261pd1 pd1 = this.b;
        if (pd1 == null) {
            T01 t01 = this.c;
            if (t01 != null) {
                if (!(t01.d != null)) {
                    t01.b.setLayoutResource(R.layout.f41580_resource_name_obfuscated_RES_2131624467);
                    StartSurfaceToolbarView startSurfaceToolbarView = (StartSurfaceToolbarView) t01.b.inflate();
                    t01.d = startSurfaceToolbarView;
                    C5976zi0 zi0 = t01.m;
                    MenuButton menuButton = (MenuButton) startSurfaceToolbarView.findViewById(R.id.menu_button_wrapper);
                    zi0.e = menuButton;
                    zi0.f = ZH0.a(zi0.b, menuButton, new C0574Ji0());
                    t01.m.e(t01.c.h(Z01.l));
                    ZH0.a(t01.c, t01.d, new S01());
                    if (C2474f80.f9900a.f()) {
                        t01.a();
                    }
                    if (AbstractC2793h01.f.c()) {
                        TabSwitcherButtonView tabSwitcherButtonView = (TabSwitcherButtonView) t01.d.findViewById(R.id.start_tab_switcher_button);
                        t01.h = tabSwitcherButtonView;
                        View.OnLongClickListener onLongClickListener = t01.l;
                        if (onLongClickListener != null) {
                            tabSwitcherButtonView.setOnLongClickListener(onLongClickListener);
                            t01.l = null;
                        }
                        C0435Hc1 hc1 = new C0435Hc1(t01.h);
                        t01.g = hc1;
                        AbstractC1117Sg1 sg1 = t01.j;
                        hc1.b = sg1;
                        C0313Fc1 fc1 = new C0313Fc1(hc1);
                        hc1.c = fc1;
                        sg1.K.b(fc1);
                        hc1.f8165a.m(AbstractC0496Ic1.d, hc1.b.a());
                        t01.h.setVisibility(0);
                        C5880z61 z61 = t01.i;
                        if (z61 != null) {
                            C0435Hc1 hc12 = t01.g;
                            hc12.d = z61;
                            hc12.a();
                            C0374Gc1 gc1 = new C0374Gc1(hc12);
                            hc12.e = gc1;
                            hc12.d.a(gc1);
                            t01.i = null;
                        }
                        View.OnClickListener onClickListener = t01.k;
                        if (onClickListener != null) {
                            t01.g.f8165a.m(AbstractC0496Ic1.b, onClickListener);
                            t01.k = null;
                        }
                    }
                }
                t01.f8931a.f9248a.j(Z01.h, z);
            }
        } else if (z) {
            if (pd1.i == null) {
                TabSwitcherModeTTPhone tabSwitcherModeTTPhone = (TabSwitcherModeTTPhone) pd1.f11077a.inflate();
                pd1.i = tabSwitcherModeTTPhone;
                C5976zi0 zi02 = pd1.g;
                MenuButton menuButton2 = (MenuButton) tabSwitcherModeTTPhone.findViewById(R.id.menu_button_wrapper);
                zi02.e = menuButton2;
                zi02.f = ZH0.a(zi02.b, menuButton2, new C0574Ji0());
                TabSwitcherModeTTPhone tabSwitcherModeTTPhone2 = pd1.i;
                View.OnClickListener onClickListener2 = pd1.b;
                ToggleTabStackButton toggleTabStackButton = tabSwitcherModeTTPhone2.N;
                if (toggleTabStackButton != null) {
                    toggleTabStackButton.S = onClickListener2;
                }
                tabSwitcherModeTTPhone2.G = pd1.c;
                C5880z61 z612 = pd1.d;
                tabSwitcherModeTTPhone2.H = z612;
                if (toggleTabStackButton != null) {
                    toggleTabStackButton.R = z612;
                    z612.a(toggleTabStackButton);
                }
                IncognitoToggleTabLayout incognitoToggleTabLayout = tabSwitcherModeTTPhone2.K;
                if (incognitoToggleTabLayout != null) {
                    incognitoToggleTabLayout.H0 = z612;
                    z612.a(incognitoToggleTabLayout);
                }
                TabSwitcherModeTTPhone tabSwitcherModeTTPhone3 = pd1.i;
                AbstractC0124Ca1 ca1 = pd1.e;
                tabSwitcherModeTTPhone3.I = ca1;
                IncognitoToggleTabLayout incognitoToggleTabLayout2 = tabSwitcherModeTTPhone3.K;
                if (incognitoToggleTabLayout2 != null) {
                    incognitoToggleTabLayout2.x(ca1);
                }
                pd1.i.f(pd1.f);
                pd1.b();
                pd1.c();
                boolean z4 = pd1.h;
                if (z4) {
                    pd1.i.d(z4);
                }
            }
            pd1.i.g(z);
        } else {
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone4 = pd1.i;
            if (tabSwitcherModeTTPhone4 != null) {
                tabSwitcherModeTTPhone4.g(z);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        if ((-r0.f9248a.e(r5)) < ((float) r1)) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r7 = this;
            T01 r0 = r7.c
            if (r0 == 0) goto L_0x005c
            int r1 = r7.a()
            Y01 r0 = r0.f8931a
            int r2 = r0.m
            r3 = 1
            r4 = 0
            if (r2 != r3) goto L_0x003f
            UH0 r2 = r0.f9248a
            QH0 r5 = defpackage.Z01.j
            boolean r2 = r2.h(r5)
            if (r2 != 0) goto L_0x003f
            UH0 r2 = r0.f9248a
            QH0 r5 = defpackage.Z01.g
            boolean r2 = r2.h(r5)
            if (r2 == 0) goto L_0x003f
            UH0 r2 = r0.f9248a
            RH0 r5 = defpackage.Z01.s
            float r2 = r2.e(r5)
            float r2 = -r2
            r6 = 0
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x003f
            UH0 r0 = r0.f9248a
            float r0 = r0.e(r5)
            float r0 = -r0
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r3 = r4
        L_0x0040:
            org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer r0 = r7.h
            if (r3 == 0) goto L_0x0045
            r4 = 4
        L_0x0045:
            org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer$ToolbarViewResourceFrameLayout r1 = r0.f10790J
            r1.setVisibility(r4)
            org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer$ToolbarViewResourceFrameLayout r1 = r0.f10790J
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x005c
            org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer$ToolbarViewResourceFrameLayout r0 = r0.f10790J
            hv1 r0 = r0.G
            Rj1 r0 = (defpackage.Rj1) r0
            r1 = 0
            r0.f(r1)
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Vl1.c():void");
    }
}
