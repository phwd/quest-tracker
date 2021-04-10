package defpackage;

import android.view.View;

/* renamed from: kG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnAttachStateChangeListenerC3351kG implements AbstractC3180jG, View.OnAttachStateChangeListener {
    public final AbstractC3180jG F;
    public Xo1 G;
    public boolean H;
    public final Yo1 I;

    public View$OnAttachStateChangeListenerC3351kG(View view, Yo1 yo1, AbstractC3180jG jGVar) {
        this.I = yo1;
        this.F = jGVar;
        this.H = view.getParent() != null;
        view.addOnAttachStateChangeListener(this);
    }

    @Override // defpackage.AbstractC3180jG
    public void a(Xo1 xo1) {
        this.G = xo1;
        if (this.H) {
            this.F.a(xo1);
        }
    }

    public void onViewAttachedToWindow(View view) {
        this.H = true;
        a(this.G);
    }

    public void onViewDetachedFromWindow(View view) {
        this.H = false;
    }
}
