package defpackage;

import android.content.Context;
import android.os.Handler;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: f81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2475f81 extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9901a;
    public final UH0 b;
    public final AbstractC5783ya1 c;
    public final AbstractC2304e81 d;
    public final AbstractC0124Ca1 e;
    public final AbstractC3226ja1 f;
    public final C2397ej g;
    public final AbstractC1117Sg1 h;
    public final I61 i;
    public final AbstractC0995Qg1 j;
    public final AbstractC1056Rg1 k;
    public final AbstractC0612Ka1 l;
    public final M2 m;
    public final AbstractC4928tY0 n;
    public final C4076oY0 o;
    public final AbstractC0956Pq0 p;
    public C1128Sl q = new C1128Sl();
    public final QK r;
    public AbstractC2260du0 s;
    public RK t;
    public AbstractC0850Ny0 u;
    public AbstractC1099Sa1 v;
    public Callback w;
    public boolean x;
    public boolean y;
    public boolean z;

    public C2475f81(Context context, C2397ej ejVar, AbstractC2304e81 e81, UH0 uh0, AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1, AbstractC1509Ys0 ys0, AbstractC1117Sg1 sg1, I61 i61, M2 m2, AbstractC4928tY0 ty0, AbstractC0956Pq0 pq0) {
        Object obj;
        String str;
        String str2;
        this.f9901a = context;
        this.d = e81;
        this.b = uh0;
        this.e = ca1;
        this.f = ja1;
        this.g = ejVar;
        this.h = sg1;
        this.i = i61;
        this.m = m2;
        this.n = ty0;
        this.p = pq0;
        C4076oY0 c2 = C4076oY0.c(context.getString(R.string.f64010_resource_name_obfuscated_RES_2131953718), this, 0, 35);
        c2.d = context.getString(R.string.f63930_resource_name_obfuscated_RES_2131953710);
        c2.e = null;
        c2.j = 5000;
        this.o = c2;
        X71 x71 = new X71(this);
        this.c = x71;
        this.r = new Y71(this);
        this.v = new Z71(this, ca1);
        C1612a81 a81 = new C1612a81(this);
        this.l = a81;
        if (AbstractC4772sd1.g()) {
            this.t = new C1792b81(this);
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
            ((I71) ea1.c.g(false)).g.b(this.t);
            ((I71) ea1.c.g(true)).g.b(this.t);
        }
        if (AbstractC4772sd1.a()) {
            C1963c81 c81 = new C1963c81(this);
            this.u = c81;
            m2.a(c81);
        }
        if (AbstractC4772sd1.c()) {
            P71 p71 = new P71(this);
            this.w = p71;
            ((C1078Rq0) pq0).l(p71);
        }
        Q71 q71 = new Q71(this);
        this.j = q71;
        R71 r71 = new R71(this);
        this.k = r71;
        AbstractC0246Ea1 ea12 = (AbstractC0246Ea1) ca1;
        ea12.c.a(x71);
        ea12.c(a81);
        ys0.g(this.q.b(new S71(this)));
        sg1.f8908J.b(q71);
        sg1.K.b(r71);
        if (AbstractC4772sd1.g()) {
            obj = new T71(this);
        } else {
            obj = new U71(this);
            uh0.l(AbstractC2646g81.f, R.drawable.f28430_resource_name_obfuscated_RES_2131230883);
        }
        uh0.m(AbstractC2646g81.f9979a, obj);
        uh0.m(AbstractC2646g81.b, new V71(this));
        if (AbstractC4772sd1.a()) {
            str = context.getString(R.string.f45250_resource_name_obfuscated_RES_2131951842);
            str2 = context.getString(R.string.f46280_resource_name_obfuscated_RES_2131951945);
        } else {
            str = context.getString(R.string.f45260_resource_name_obfuscated_RES_2131951843);
            str2 = context.getString(R.string.f48320_resource_name_obfuscated_RES_2131952149);
        }
        uh0.m(AbstractC2646g81.h, str);
        uh0.m(AbstractC2646g81.i, str2);
        uh0.j(AbstractC2646g81.c, true);
        Tab j2 = ea12.j();
        if (j2 != null) {
            g(j2.getId());
        }
    }

    public static void e(C2475f81 f81, int i2) {
        Objects.requireNonNull(f81);
        if (AbstractC3293jx.a() == 2 && AbstractC4772sd1.a()) {
            AbstractC3293jx.e(1);
            f81.z = true;
            AbstractC3364kK0.g("TabStrip.ReasonToShow", i2, 3);
        }
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        AbstractC3293jx.e(1);
        g(((AbstractC0246Ea1) this.e).k());
        AbstractC3535lK0.a("TabStrip.UndoDismiss");
    }

    public final List f(int i2) {
        if (!AbstractC4772sd1.a()) {
            return ((AbstractC0246Ea1) this.e).c.d().N(i2);
        }
        ArrayList arrayList = new ArrayList();
        if (i2 == -1) {
            return arrayList;
        }
        Tab o2 = ((AbstractC0246Ea1) this.e).o(i2);
        TabModel l2 = ((AbstractC0246Ea1) this.e).l(o2.a());
        for (int i3 = 0; i3 < l2.getCount(); i3++) {
            arrayList.add(l2.getTabAt(i3));
        }
        return arrayList;
    }

    public final void g(int i2) {
        if (this.y || (AbstractC4772sd1.a() && AbstractC3293jx.a() != 1)) {
            i2 = -1;
        }
        List f2 = f(i2);
        if (f2.size() < 2) {
            ((O71) this.d).h(null);
            this.x = false;
        } else {
            ((O71) this.d).h(f2);
            this.x = true;
            if (this.z) {
                this.z = false;
                AbstractC3535lK0.a("TabStrip.ShownOnTabUse");
                NU0.f8549a.o("Chrome.ConditionalTabStrip.LastShownTimeStamp", System.currentTimeMillis());
            }
        }
        if (this.x) {
            new Handler().post(new W71(this, f2));
        }
        C2397ej ejVar = this.g;
        boolean z2 = this.x;
        C2910hj hjVar = ejVar.f9874a;
        hjVar.L = z2;
        hjVar.i();
        hjVar.g();
    }
}
