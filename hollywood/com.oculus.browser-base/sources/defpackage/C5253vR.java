package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: vR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5253vR extends AnimatorListenerAdapter {
    public final /* synthetic */ View F;
    public final /* synthetic */ int G;

    public C5253vR(C5763yR yRVar, View view, int i) {
        this.F = view;
        this.G = i;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.setTranslationY(0.0f);
        View view = this.F;
        view.setBottom(view.getTop() + this.G);
    }
}
