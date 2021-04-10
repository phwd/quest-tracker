package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

/* renamed from: b10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1770b10 extends AnimatorListenerAdapter {
    public final /* synthetic */ int F;
    public final /* synthetic */ TextView G;
    public final /* synthetic */ int H;
    public final /* synthetic */ TextView I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C1941c10 f9508J;

    public C1770b10(C1941c10 c10, int i, TextView textView, int i2, TextView textView2) {
        this.f9508J = c10;
        this.F = i;
        this.G = textView;
        this.H = i2;
        this.I = textView2;
    }

    public void onAnimationEnd(Animator animator) {
        TextView textView;
        C1941c10 c10 = this.f9508J;
        c10.i = this.F;
        c10.g = null;
        TextView textView2 = this.G;
        if (textView2 != null) {
            textView2.setVisibility(4);
            if (this.H == 1 && (textView = this.f9508J.m) != null) {
                textView.setText((CharSequence) null);
            }
        }
        TextView textView3 = this.I;
        if (textView3 != null) {
            textView3.setTranslationY(0.0f);
            this.I.setAlpha(1.0f);
        }
    }

    public void onAnimationStart(Animator animator) {
        TextView textView = this.I;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }
}
