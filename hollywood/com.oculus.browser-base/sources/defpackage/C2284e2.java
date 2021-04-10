package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: e2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2284e2 extends AnimatorListenerAdapter {
    public final /* synthetic */ ActionBarOverlayLayout F;

    public C2284e2(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.F = actionBarOverlayLayout;
    }

    public void onAnimationCancel(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.F;
        actionBarOverlayLayout.j0 = null;
        actionBarOverlayLayout.Q = false;
    }

    public void onAnimationEnd(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.F;
        actionBarOverlayLayout.j0 = null;
        actionBarOverlayLayout.Q = false;
    }
}
