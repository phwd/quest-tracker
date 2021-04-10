package defpackage;

import android.animation.AnimatorSet;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: d9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2135d9 implements PopupWindow.OnDismissListener {
    public final View$OnKeyListenerC2476f9 F;
    public final View G;

    public C2135d9(View$OnKeyListenerC2476f9 f9Var, View view) {
        this.F = f9Var;
        this.G = view;
    }

    public void onDismiss() {
        View$OnKeyListenerC2476f9 f9Var = this.F;
        View view = this.G;
        Objects.requireNonNull(f9Var);
        StringBuilder sb = new StringBuilder();
        sb.append("Mobile.AppMenu.TimeToTakeAction.");
        sb.append(f9Var.V ? "SelectedItem" : "Abandoned");
        AbstractC3364kK0.j(sb.toString(), SystemClock.elapsedRealtime() - f9Var.U);
        if (view instanceof ImageButton) {
            ((ImageButton) view).setSelected(false);
        }
        AnimatorSet animatorSet = f9Var.T;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        C5887z9 z9Var = f9Var.P;
        z9Var.G.a();
        F9 f9 = (F9) z9Var.M;
        f9.d = null;
        if (f9.o) {
            C2249dq1 a2 = C2249dq1.a();
            Objects.requireNonNull(a2);
            PostTask.b(C3070if1.g, new Zp1(a2), 0);
            C2249dq1.a().c.c(f9.u);
            f9.o = false;
            f9.u = null;
        }
        f9Var.P.h(false);
        f9Var.M = null;
        f9Var.O = null;
        f9Var.N = null;
        f9Var.Q = null;
        f9Var.T = null;
    }
}
