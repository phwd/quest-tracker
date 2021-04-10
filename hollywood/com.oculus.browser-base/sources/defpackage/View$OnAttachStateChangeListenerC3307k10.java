package defpackage;

import android.view.View;
import java.util.Iterator;

/* renamed from: k10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnAttachStateChangeListenerC3307k10 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ C3649m10 F;

    public View$OnAttachStateChangeListenerC3307k10(C3649m10 m10) {
        this.F = m10;
    }

    public void onViewAttachedToWindow(View view) {
        C3649m10 m10 = this.F;
        if (m10.T == null) {
            m10.T = new C3136j10(this);
            m10.U = AbstractC5978zj.a(m10.M.i());
            C3649m10 m102 = this.F;
            ((C5638xj) m102.U).j(m102.T);
        }
        Iterator it = this.F.f10392J.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3478l10) uq0.next()).a(!this.F.I.isEmpty());
            } else {
                return;
            }
        }
    }

    public void onViewDetachedFromWindow(View view) {
    }
}
