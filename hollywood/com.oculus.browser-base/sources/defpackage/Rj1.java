package defpackage;

import J.N;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.View;
import java.util.Objects;

/* renamed from: Rj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Rj1 extends View$OnLayoutChangeListenerC2948hv1 {
    public final int[] L = new int[2];
    public final Rect M = new Rect();
    public final Rect N = new Rect();
    public final View O;
    public AbstractC5130uj1 P;
    public int Q;

    public Rj1(View view) {
        super(view);
        this.O = view;
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1, defpackage.AbstractC3197jM0
    public long d() {
        AbstractC5130uj1 uj1 = this.P;
        View view = this.O;
        int[] iArr = this.L;
        Wj1 wj1 = ((Vl1) uj1).f9104a;
        Objects.requireNonNull(wj1);
        AbstractC4656rv1.c(view, wj1, iArr);
        Rect rect = this.N;
        int[] iArr2 = this.L;
        rect.set(iArr2[0], iArr2[1], this.O.getWidth(), ((Vl1) this.P).a() + this.L[1]);
        AbstractC5130uj1 uj12 = this.P;
        ((Vl1) uj12).f9104a.i(this.M);
        Rect rect2 = this.M;
        int[] iArr3 = this.L;
        rect2.offset(iArr3[0], iArr3[1]);
        int height = (this.O.getHeight() - ((Vl1) this.P).a()) - this.Q;
        Rect rect3 = this.N;
        Rect rect4 = this.M;
        return N.MLbFZktb(rect3.left, rect3.top, rect3.right, rect3.bottom, rect4.left, rect4.top, rect4.right, rect4.bottom, height);
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1, defpackage.HJ
    public boolean e() {
        AbstractC5130uj1 uj1 = this.P;
        return uj1 != null && ((Vl1) uj1).f9104a.q() && super.e();
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1
    public void g() {
        ((Vl1) this.P).f9104a.Q(false);
        ((Vl1) this.P).f9104a.I(false);
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1
    public void h(Canvas canvas, Rect rect) {
        canvas.save();
        canvas.clipRect(0, 0, this.O.getWidth(), this.O.getHeight());
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.restore();
        rect.set(0, 0, this.O.getWidth(), this.O.getHeight());
        ((Vl1) this.P).f9104a.Q(true);
    }
}
