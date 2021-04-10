package defpackage;

import android.content.res.Resources;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: gv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2777gv1 implements AbstractC3180jG {
    public int F;
    public int G;
    public final View H;
    public final View$OnAttachStateChangeListenerC3351kG I;

    /* renamed from: J  reason: collision with root package name */
    public final Yo1 f10033J;
    public int K;

    public C2777gv1(View view, Yo1 yo1, int i, int i2) {
        this.H = view;
        this.F = i;
        this.G = i2;
        this.f10033J = yo1;
        this.I = new View$OnAttachStateChangeListenerC3351kG(view, yo1, this);
    }

    public static C2777gv1 b(View view, Yo1 yo1, int i, int i2) {
        C2777gv1 gv1 = new C2777gv1(view, yo1, i, i2);
        View$OnAttachStateChangeListenerC3351kG kGVar = gv1.I;
        Yo1 yo12 = kGVar.I;
        yo12.b.add(kGVar);
        kGVar.a(yo12.f9298a);
        return gv1;
    }

    @Override // defpackage.AbstractC3180jG
    public void a(Xo1 xo1) {
        int i;
        int i2 = xo1.f9235a;
        this.K = i2;
        if (i2 == 2) {
            Resources resources = this.f10033J.c.getResources();
            i = Math.max(this.G, (int) ((((float) (resources.getConfiguration().screenWidthDp - 600)) / 2.0f) * resources.getDisplayMetrics().density));
        } else {
            i = this.F;
        }
        View view = this.H;
        int paddingTop = view.getPaddingTop();
        int paddingBottom = this.H.getPaddingBottom();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.setPaddingRelative(i, paddingTop, i, paddingBottom);
    }
}
