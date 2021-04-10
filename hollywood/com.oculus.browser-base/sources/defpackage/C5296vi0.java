package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: vi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5296vi0 extends AnimatorListenerAdapter {
    public final /* synthetic */ View F;
    public final /* synthetic */ View G;

    public C5296vi0(View view, View view2) {
        this.F = view;
        this.G = view2;
    }

    public void onAnimationCancel(Animator animator) {
        this.G.setAlpha(1.0f);
        this.F.setVisibility(8);
    }

    public void onAnimationEnd(Animator animator) {
        this.F.setVisibility(8);
    }
}
