package defpackage;

import J.N;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CompoundButton;
import com.google.android.material.appbar.AppBarLayout;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.ntp.IncognitoDescriptionView;
import org.chromium.chrome.browser.tasks.TasksView;

/* renamed from: pf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4267pf1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        AppBarLayout appBarLayout;
        int i;
        UH0 uh0 = (UH0) obj;
        TasksView tasksView = (TasksView) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5798yf1.o;
        if (kh0 == th0) {
            C3889nQ0 nq0 = tasksView.k0.c;
            nq0.G.m(AbstractC4060oQ0.j, new View$OnClickListenerC3547lQ0(nq0, (View.OnClickListener) uh0.g(th0)));
            return;
        }
        TH0 th02 = AbstractC5798yf1.p;
        if (kh0 == th02) {
            tasksView.k0.f10204a.m(AbstractC4060oQ0.k, (TextWatcher) uh0.g(th02));
            return;
        }
        QH0 qh0 = AbstractC5798yf1.h;
        if (kh0 == qh0) {
            tasksView.B(uh0.h(qh0));
            return;
        }
        TH0 th03 = AbstractC5798yf1.i;
        if (kh0 == th03) {
            View.OnClickListener onClickListener = (View.OnClickListener) uh0.g(th03);
            tasksView.r0 = onClickListener;
            IncognitoDescriptionView incognitoDescriptionView = tasksView.l0;
            if (incognitoDescriptionView != null) {
                incognitoDescriptionView.Q.setOnClickListener(onClickListener);
                tasksView.r0 = null;
                return;
            }
            return;
        }
        QH0 qh02 = AbstractC5798yf1.j;
        if (kh0 == qh02) {
            boolean h = uh0.h(qh02);
            tasksView.o0 = h;
            IncognitoDescriptionView incognitoDescriptionView2 = tasksView.l0;
            if (incognitoDescriptionView2 != null) {
                incognitoDescriptionView2.P.setChecked(h);
                return;
            }
            return;
        }
        TH0 th04 = AbstractC5798yf1.k;
        if (kh0 == th04) {
            CompoundButton.OnCheckedChangeListener onCheckedChangeListener = (CompoundButton.OnCheckedChangeListener) uh0.g(th04);
            tasksView.p0 = onCheckedChangeListener;
            IncognitoDescriptionView incognitoDescriptionView3 = tasksView.l0;
            if (incognitoDescriptionView3 != null) {
                incognitoDescriptionView3.P.setOnCheckedChangeListener(onCheckedChangeListener);
                tasksView.p0 = null;
                return;
            }
            return;
        }
        SH0 sh0 = AbstractC5798yf1.l;
        if (kh0 == sh0) {
            tasksView.C(uh0.f(sh0));
            return;
        }
        TH0 th05 = AbstractC5798yf1.n;
        if (kh0 == th05) {
            View.OnClickListener onClickListener2 = (View.OnClickListener) uh0.g(th05);
            tasksView.m0 = onClickListener2;
            IncognitoDescriptionView incognitoDescriptionView4 = tasksView.l0;
            if (incognitoDescriptionView4 != null) {
                incognitoDescriptionView4.findViewById(R.id.learn_more).setOnClickListener(onClickListener2);
                tasksView.m0 = null;
                return;
            }
            return;
        }
        QH0 qh03 = AbstractC5798yf1.f11692a;
        if (kh0 == qh03) {
            tasksView.k0.f10204a.j(AbstractC4060oQ0.c, uh0.h(qh03));
            return;
        }
        QH0 qh04 = AbstractC5798yf1.b;
        if (kh0 == qh04) {
            boolean h2 = uh0.h(qh04);
            Resources resources = tasksView.g0.getResources();
            int b = AbstractC2934hr.b(resources, h2);
            tasksView.setBackgroundColor(b);
            tasksView.i0.setBackgroundColor(b);
            tasksView.k0.f10204a.m(AbstractC4060oQ0.b, AbstractC5544x8.a(tasksView.g0, h2 ? R.drawable.f29240_resource_name_obfuscated_RES_2131230964 : R.drawable.f34320_resource_name_obfuscated_RES_2131231472));
            if (h2) {
                i = resources.getColor(R.color.f12920_resource_name_obfuscated_RES_2131099982);
            } else {
                i = resources.getColor(R.color.f12910_resource_name_obfuscated_RES_2131099981);
            }
            tasksView.k0.f10204a.l(AbstractC4060oQ0.l, i);
            return;
        }
        QH0 qh05 = AbstractC5798yf1.c;
        if (kh0 != qh05) {
            QH0 qh06 = AbstractC5798yf1.d;
            int i2 = 0;
            if (kh0 == qh06) {
                boolean h3 = uh0.h(qh06);
                if (h3) {
                    View$OnClickListenerC2109d00 d00 = (View$OnClickListenerC2109d00) uh0.g(AbstractC5798yf1.m);
                    if (d00.I) {
                        N.Ml$8f4xR(d00.F.f10767a);
                    }
                }
                IncognitoDescriptionView incognitoDescriptionView5 = tasksView.l0;
                if (!h3) {
                    i2 = 8;
                }
                incognitoDescriptionView5.setVisibility(i2);
                return;
            }
            QH0 qh07 = AbstractC5798yf1.e;
            if (kh0 == qh07) {
                boolean h4 = uh0.h(qh07);
                ViewGroup z = tasksView.z();
                if (!h4) {
                    i2 = 8;
                }
                z.setVisibility(i2);
                return;
            }
            QH0 qh08 = AbstractC5798yf1.f;
            if (kh0 == qh08) {
                boolean h5 = uh0.h(qh08);
                tasksView.h0.setVisibility(h5 ? 0 : 8);
                View findViewById = tasksView.findViewById(R.id.tab_switcher_title);
                if (!h5) {
                    i2 = 8;
                }
                findViewById.setVisibility(i2);
                return;
            }
            QH0 qh09 = AbstractC5798yf1.g;
            if (kh0 == qh09) {
                tasksView.k0.f10204a.j(AbstractC4060oQ0.d, uh0.h(qh09));
                return;
            }
            TH0 th06 = AbstractC5798yf1.q;
            if (kh0 == th06) {
                tasksView.findViewById(R.id.more_tabs).setOnClickListener((View.OnClickListener) uh0.g(th06));
                return;
            }
            QH0 qh010 = AbstractC5798yf1.f11692a;
            QH0 qh011 = AbstractC0703Ll0.f8436a;
            if (kh0 == qh011) {
                if (!uh0.h(qh011)) {
                    i2 = 8;
                }
                tasksView.findViewById(R.id.mv_tiles_container).setVisibility(i2);
                return;
            }
            TH0 th07 = AbstractC5798yf1.r;
            if (kh0 == th07) {
                C3889nQ0 nq02 = tasksView.k0.c;
                boolean z2 = !nq02.I.isEmpty();
                nq02.I.add((View.OnClickListener) uh0.g(th07));
                if (!z2) {
                    nq02.G.m(AbstractC4060oQ0.g, new View$OnClickListenerC3718mQ0(nq02));
                    return;
                }
                return;
            }
            SH0 sh02 = AbstractC5798yf1.s;
            if (kh0 == sh02) {
                ((ViewGroup.MarginLayoutParams) tasksView.z().getLayoutParams()).topMargin = uh0.f(sh02);
                return;
            }
            SH0 sh03 = AbstractC5798yf1.t;
            if (kh0 == sh03) {
                ((ViewGroup.MarginLayoutParams) tasksView.i0.findViewById(R.id.mv_tiles_container).getLayoutParams()).topMargin = uh0.f(sh03);
                return;
            }
            SH0 sh04 = AbstractC5798yf1.u;
            if (kh0 == sh04) {
                ((ViewGroup.MarginLayoutParams) tasksView.i0.findViewById(R.id.tab_switcher_title).getLayoutParams()).topMargin = uh0.f(sh04);
                return;
            }
            QH0 qh012 = AbstractC5798yf1.v;
            if (kh0 == qh012) {
                boolean h6 = uh0.h(qh012);
                View findViewById2 = tasksView.findViewById(R.id.trendy_terms_recycler_view);
                if (!h6) {
                    i2 = 8;
                }
                findViewById2.setVisibility(i2);
            } else if (kh0 == AbstractC5798yf1.w && (appBarLayout = tasksView.i0) != null && appBarLayout.getHeight() != tasksView.i0.getBottom()) {
                AppBarLayout appBarLayout2 = tasksView.i0;
                Objects.requireNonNull(appBarLayout2);
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                appBarLayout2.h(true, appBarLayout2.isLaidOut(), true);
            }
        } else if (uh0.h(qh05)) {
            View inflate = ((ViewStub) tasksView.findViewById(R.id.incognito_description_container_layout_stub)).inflate();
            if (Build.VERSION.SDK_INT < 28) {
                inflate.setFocusable(true);
                inflate.setFocusableInTouchMode(true);
            }
            IncognitoDescriptionView incognitoDescriptionView6 = (IncognitoDescriptionView) inflate.findViewById(R.id.new_tab_incognito_container);
            tasksView.l0 = incognitoDescriptionView6;
            View.OnClickListener onClickListener3 = tasksView.m0;
            if (onClickListener3 != null) {
                tasksView.m0 = onClickListener3;
                if (incognitoDescriptionView6 != null) {
                    incognitoDescriptionView6.findViewById(R.id.learn_more).setOnClickListener(onClickListener3);
                    tasksView.m0 = null;
                }
            }
            tasksView.B(tasksView.n0);
            boolean z3 = tasksView.o0;
            tasksView.o0 = z3;
            IncognitoDescriptionView incognitoDescriptionView7 = tasksView.l0;
            if (incognitoDescriptionView7 != null) {
                incognitoDescriptionView7.P.setChecked(z3);
            }
            CompoundButton.OnCheckedChangeListener onCheckedChangeListener2 = tasksView.p0;
            if (onCheckedChangeListener2 != null) {
                tasksView.p0 = onCheckedChangeListener2;
                IncognitoDescriptionView incognitoDescriptionView8 = tasksView.l0;
                if (incognitoDescriptionView8 != null) {
                    incognitoDescriptionView8.P.setOnCheckedChangeListener(onCheckedChangeListener2);
                    tasksView.p0 = null;
                }
            }
            tasksView.C(tasksView.q0);
            View.OnClickListener onClickListener4 = tasksView.r0;
            if (onClickListener4 != null) {
                tasksView.r0 = onClickListener4;
                IncognitoDescriptionView incognitoDescriptionView9 = tasksView.l0;
                if (incognitoDescriptionView9 != null) {
                    incognitoDescriptionView9.Q.setOnClickListener(onClickListener4);
                    tasksView.r0 = null;
                }
            }
        }
    }
}
