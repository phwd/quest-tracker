package defpackage;

import J.N;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.PersonalizedSigninPromoView;

/* renamed from: XO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XO {
    public ScrollView A;
    public C2777gv1 B;
    public Vr1 C;
    public final Handler D;

    /* renamed from: a  reason: collision with root package name */
    public QO f9205a = new QO();
    public final Activity b;
    public final View$OnClickListenerC5098uY0 c;
    public final View d;
    public final boolean e;
    public final boolean f;
    public final YO g;
    public final int h;
    public final int i;
    public final C2861hP j;
    public final AbstractC4448qj k;
    public final C4399qP l;
    public final Q31 m;
    public Yo1 n;
    public FrameLayout o;
    public Tm1 p;
    public long q;
    public View r;
    public C3536lL s;
    public K21 t;
    public HM u;
    public SectionHeaderView v;
    public PersonalizedSigninPromoView w;
    public C2777gv1 x;
    public AbstractC0095Bm0 y;
    public Profile z;

    public XO(Activity activity, View$OnClickListenerC5098uY0 uy0, Q31 q31, View view, SectionHeaderView sectionHeaderView, C4399qP qPVar, boolean z2, YO yo, AbstractC0095Bm0 bm0, Profile profile, boolean z3, AbstractC4448qj qjVar) {
        Handler handler = new Handler();
        this.D = handler;
        this.b = activity;
        this.c = uy0;
        this.d = null;
        this.v = sectionHeaderView;
        this.e = z2;
        this.f = z3;
        this.g = yo;
        this.y = bm0;
        this.k = qjVar;
        this.z = profile;
        this.l = qPVar;
        this.m = q31;
        Resources resources = activity.getResources();
        Objects.requireNonNull(this.f9205a);
        this.h = activity.getResources().getDimensionPixelSize(R.dimen.f17680_resource_name_obfuscated_RES_2131165387);
        Objects.requireNonNull(this.f9205a);
        this.i = activity.getResources().getDimensionPixelSize(R.dimen.f23150_resource_name_obfuscated_RES_2131165934);
        VO vo = new VO(this, activity);
        this.o = vo;
        boolean z4 = false;
        vo.setPadding(0, resources.getDimensionPixelOffset(R.dimen.f25730_resource_name_obfuscated_RES_2131166192), 0, 0);
        this.n = new Yo1(this.o);
        this.p = Um1.a(profile);
        if (N.M09VlOh_("EnhancedProtectionPromoCard") && N.M09VlOh_("SafeBrowsingEnhancedProtection")) {
            z4 = true;
        }
        if (z4) {
            this.s = new C3536lL(activity, this.z);
        }
        this.j = new C2861hP(this, this.y);
        this.C = new Vr1(activity, handler, new RO());
    }

    public PersonalizedSigninPromoView a() {
        if (this.w == null) {
            this.w = (PersonalizedSigninPromoView) LayoutInflater.from(this.o.getContext()).inflate(R.layout.f40610_resource_name_obfuscated_RES_2131624370, (ViewGroup) this.o, false);
            b();
        }
        return this.w;
    }

    public boolean b() {
        return ((FO) this.f9205a.f8758a).f8014a.s;
    }

    public void c(boolean z2, View view) {
        if (this.t != null) {
            ArrayList arrayList = new ArrayList();
            View view2 = this.d;
            if (view2 != null) {
                arrayList.add(new C4637rp0(view2));
            }
            if (view != null) {
                this.r = view;
                arrayList.add(new TO(this, null));
            }
            SectionHeaderView sectionHeaderView = this.v;
            if (sectionHeaderView != null) {
                arrayList.add(new C4637rp0(sectionHeaderView));
            }
            if (z2) {
                arrayList.add(new WO(this, null));
            }
            this.t.a(arrayList);
        }
    }
}
