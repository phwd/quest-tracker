package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: lc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3572lc implements Runnable {
    public final /* synthetic */ View$OnTouchListenerC5038u90 F;

    public RunnableC3572lc(View$OnTouchListenerC5038u90 u90) {
        this.F = u90;
    }

    public void run() {
        View$OnTouchListenerC5038u90 u90 = this.F;
        if (u90.U) {
            if (u90.S) {
                u90.S = false;
                C3401kc kcVar = u90.G;
                Objects.requireNonNull(kcVar);
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                kcVar.e = currentAnimationTimeMillis;
                kcVar.i = -1;
                kcVar.f = currentAnimationTimeMillis;
                kcVar.j = 0.5f;
                kcVar.g = 0;
                kcVar.h = 0;
            }
            C3401kc kcVar2 = this.F.G;
            if ((kcVar2.i > 0 && AnimationUtils.currentAnimationTimeMillis() > kcVar2.i + ((long) kcVar2.k)) || !this.F.f()) {
                this.F.U = false;
                return;
            }
            View$OnTouchListenerC5038u90 u902 = this.F;
            if (u902.T) {
                u902.T = false;
                Objects.requireNonNull(u902);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                u902.I.onTouchEvent(obtain);
                obtain.recycle();
            }
            if (kcVar2.f != 0) {
                long currentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                float a2 = kcVar2.a(currentAnimationTimeMillis2);
                kcVar2.f = currentAnimationTimeMillis2;
                float f = ((float) (currentAnimationTimeMillis2 - kcVar2.f)) * ((a2 * 4.0f) + (-4.0f * a2 * a2));
                kcVar2.g = (int) (kcVar2.c * f);
                int i = (int) (f * kcVar2.d);
                kcVar2.h = i;
                this.F.W.scrollListBy(i);
                View view = this.F.I;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                view.postOnAnimation(this);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }
    }
}
