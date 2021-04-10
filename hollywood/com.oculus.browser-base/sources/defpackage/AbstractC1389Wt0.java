package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: Wt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1389Wt0 extends AbstractC1145St0 {
    public final float R;
    public final float S;
    public int T;
    public int U;
    public int V;
    public boolean W;
    public boolean X;

    public AbstractC1389Wt0(AbstractC0292Et0 et0, int i, int i2, Context context, ViewGroup viewGroup, IJ ij, int i3, int i4) {
        super(et0, i, i2, context, viewGroup, ij);
        float f;
        float f2 = 0.0f;
        if (i3 == 0) {
            f = 0.0f;
        } else {
            f = context.getResources().getDimension(i3);
        }
        this.R = f;
        this.S = i4 != 0 ? context.getResources().getDimension(i4) : f2;
    }

    @Override // defpackage.AbstractC3631lv1
    public void j() {
        View view = this.L;
        this.T = view.getPaddingStart();
        this.U = view.getPaddingTop();
        this.V = view.getPaddingBottom();
    }

    public void m(boolean z) {
        float f;
        View view = this.L;
        if (view == null) {
            return;
        }
        if (z || this.W != this.X) {
            boolean z2 = this.W;
            this.X = z2;
            if (z2) {
                f = this.S;
            } else {
                f = this.R;
            }
            view.setPaddingRelative(this.T, this.U, (int) f, this.V);
            f(false);
        }
    }

    public void n(float f) {
        this.W = f > 0.5f;
        m(false);
    }
}
