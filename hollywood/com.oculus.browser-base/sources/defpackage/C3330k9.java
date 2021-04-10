package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: k9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3330k9 extends AnimatorListenerAdapter {
    public final /* synthetic */ View F;

    public C3330k9(C4185p9 p9Var, View view) {
        this.F = view;
    }

    public void onAnimationStart(Animator animator) {
        this.F.setAlpha(0.0f);
    }
}
