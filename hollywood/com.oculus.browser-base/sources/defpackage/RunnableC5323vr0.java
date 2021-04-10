package defpackage;

import J.N;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* renamed from: vr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5323vr0 implements Runnable {
    public final C6003zr0 F;

    public RunnableC5323vr0(C6003zr0 zr0) {
        this.F = zr0;
    }

    public void run() {
        C6003zr0 zr0 = this.F;
        Objects.requireNonNull(zr0);
        AbstractC3535lK0.a("OfflineIndicator.Hidden");
        AbstractC3364kK0.j("OfflineIndicator.ShownDuration", TimeUnit.MICROSECONDS.toMillis(N.MklbOJun()) - zr0.o);
        zr0.m = SystemClock.elapsedRealtime();
        int color = zr0.f11774a.getResources().getColor(R.color.f14400_resource_name_obfuscated_RES_2131100130);
        int color2 = zr0.f11774a.getResources().getColor(R.color.f11550_resource_name_obfuscated_RES_2131099845);
        Drawable drawable = zr0.f11774a.getDrawable(R.drawable.f30310_resource_name_obfuscated_RES_2131231071);
        int color3 = zr0.f11774a.getResources().getColor(R.color.f11310_resource_name_obfuscated_RES_2131099821);
        Z11 z11 = zr0.b;
        String string = zr0.f11774a.getString(R.string.f56480_resource_name_obfuscated_RES_2131952965);
        Runnable runnable = zr0.j;
        if (z11.c) {
            View$OnLayoutChangeListenerC4337q21 q21 = z11.f9314a;
            if (string.equals(q21.F.g(AbstractC4507r21.f11179a)) && drawable == q21.F.g(AbstractC4507r21.b) && color == q21.F.f(AbstractC4507r21.e) && color2 == q21.F.f(AbstractC4507r21.g)) {
                q21.F.f(AbstractC4507r21.h);
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            animation.InterpolatorC2176dO dOVar = G30.c;
            ofFloat.setInterpolator(dOVar);
            ofFloat.setDuration(150L);
            ofFloat.addUpdateListener(new C2116d21(q21));
            ofFloat.addListener(new C3482l21(q21, string, drawable, color2, color3));
            ValueAnimator ofInt = ValueAnimator.ofInt(q21.F.f(AbstractC4507r21.e), color);
            ofInt.setEvaluator(new ArgbEvaluator());
            ofInt.setInterpolator(dOVar);
            ofInt.setDuration(400L);
            ofInt.addUpdateListener(new C2286e21(q21));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat2.setInterpolator(dOVar);
            ofFloat2.setDuration(150L);
            ofFloat2.addUpdateListener(new C2457f21(q21));
            AnimatorSet animatorSet = new AnimatorSet();
            q21.R = animatorSet;
            animatorSet.play(ofFloat).with(ofInt);
            q21.R.play(ofFloat2).after(ofInt);
            q21.R.addListener(new C3653m21(q21, runnable));
            q21.R.start();
        }
    }
}
