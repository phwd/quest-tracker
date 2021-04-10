package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.base.TraceEvent;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.url.GURL;

/* renamed from: nE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3859nE0 {

    /* renamed from: a  reason: collision with root package name */
    public static BD0 f10478a = new C3517lE0();
    public Context b;
    public AbstractC5900zD0 c;
    public PD0 d;
    public FrameLayout e = new FrameLayout(this.b);
    public C4372qE0 f;
    public C2151dE0 g;
    public boolean h;
    public AbstractC3688mE0 i;

    public C3859nE0(GURL gurl, Context context, AbstractC0156Cm0 cm0, String str, AbstractC3688mE0 me0, int i2, boolean z) {
        TraceEvent.l0("paint_preview PlayerManager init", (long) hashCode());
        this.b = context;
        this.i = me0;
        C2321eE0 ee0 = new C2321eE0(this);
        me0.getClass();
        this.c = new PlayerCompositorDelegateImpl(cm0, null, gurl, str, false, ee0, new C2492fE0(me0));
        Context context2 = this.b;
        AbstractC3688mE0 me02 = this.i;
        me02.getClass();
        this.f = new C4372qE0(context2, new RunnableC2663gE0(me02));
        AbstractC3688mE0 me03 = this.i;
        me03.getClass();
        C2834hE0 he0 = new C2834hE0(me03);
        AbstractC3688mE0 me04 = this.i;
        me04.getClass();
        RunnableC3005iE0 ie0 = new RunnableC3005iE0(me04);
        AbstractC3688mE0 me05 = this.i;
        me05.getClass();
        this.g = new C2151dE0(he0, ie0, new RunnableC3175jE0(me05));
        this.e.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.e.setBackgroundColor(i2);
        this.h = z;
    }

    public final void a(PD0 pd0, C1210Tv0 tv0) {
        C1210Tv0[] tv0Arr = tv0.d;
        if (tv0Arr != null && tv0Arr.length != 0) {
            int i2 = 0;
            while (true) {
                C1210Tv0[] tv0Arr2 = tv0.d;
                if (i2 < tv0Arr2.length) {
                    C1210Tv0 tv02 = tv0Arr2[i2];
                    PD0 pd02 = new PD0(this.b, this.c, tv02.f8994a, tv02.b, tv02.c, tv02.f, tv02.g, false, null, this.g, null);
                    a(pd02, tv02);
                    Rect rect = tv0.e[i2];
                    pd0.e.add(pd02);
                    SD0 sd0 = pd0.f8676a;
                    C1629aE0 ae0 = pd02.d;
                    SD0 sd02 = pd02.f8676a;
                    sd0.c.add(ae0);
                    sd0.d.add(rect);
                    sd0.e.add(sd02);
                    sd0.f.add(new Rect());
                    sd02.j = true;
                    sd02.c(sd0.k);
                    sd0.g.m(UD0.d, sd0.c);
                    sd0.g.m(UD0.e, sd0.f);
                    pd02.d.G.e = pd0.d.G;
                    i2++;
                } else {
                    return;
                }
            }
        }
    }
}
