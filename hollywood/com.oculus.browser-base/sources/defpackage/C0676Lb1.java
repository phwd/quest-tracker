package defpackage;

import android.content.Context;
import android.view.View;
import java.util.HashSet;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Lb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0676Lb1 implements AbstractC0249Eb1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8427a;
    public final AbstractC0124Ca1 b;
    public final C0127Cb1 c;
    public final UH0 d;
    public final C3209jS0 e;
    public final AbstractC0855Oa1 f;
    public final AbstractC0612Ka1 g;
    public C5616xb1 h = new C5616xb1(this, 1);
    public C0310Fb1 i = new C0310Fb1(this);
    public final View.OnClickListener j;
    public final View.OnClickListener k;

    public C0676Lb1(Context context, AbstractC0124Ca1 ca1, C0127Cb1 cb1, UH0 uh0, C3209jS0 js0) {
        View$OnClickListenerC0432Hb1 hb1 = new View$OnClickListenerC0432Hb1(this);
        this.j = hb1;
        View$OnClickListenerC0493Ib1 ib1 = new View$OnClickListenerC0493Ib1(this);
        this.k = ib1;
        this.f8427a = context;
        this.b = ca1;
        this.c = cb1;
        this.d = uh0;
        this.e = js0;
        uh0.m(AbstractC0736Mb1.e, hb1);
        uh0.m(AbstractC0736Mb1.b, ib1);
        this.f = new C0554Jb1(this, ca1);
        C0615Kb1 kb1 = new C0615Kb1(this);
        this.g = kb1;
        ((AbstractC0246Ea1) ca1).c(kb1);
    }

    public void a(String str, Integer num, C5616xb1 xb1, int i2, C0310Fb1 fb1) {
        if (str != null) {
            this.d.m(AbstractC0736Mb1.c, str);
        }
        if (xb1 != null) {
            this.h = xb1;
        }
        if (i2 != -1) {
            this.d.l(AbstractC0736Mb1.d, i2);
        }
        if (fb1 != null) {
            this.i = fb1;
        }
        if (num != null) {
            this.d.l(AbstractC0736Mb1.j, num.intValue());
        }
    }

    public void b() {
        this.c.a(null, 0);
        this.d.j(AbstractC0736Mb1.f8486a, false);
    }

    public void c(List list, int i2) {
        C3209jS0 js0 = this.e;
        js0.b = true;
        js0.e();
        if (i2 > 0) {
            HashSet hashSet = new HashSet();
            for (int i3 = 0; i3 < i2; i3++) {
                hashSet.add(Integer.valueOf(((Tab) list.get(i3)).getId()));
            }
            C3209jS0 js02 = this.e;
            js02.c = hashSet;
            js02.e();
        }
        this.c.a(list, i2);
        this.d.j(AbstractC0736Mb1.f8486a, true);
    }
}
