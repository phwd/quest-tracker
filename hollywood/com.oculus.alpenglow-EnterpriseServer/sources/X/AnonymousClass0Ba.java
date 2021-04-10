package X;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;

/* renamed from: X.0Ba  reason: invalid class name */
public class AnonymousClass0Ba implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable";
    public final /* synthetic */ AnonymousClass0Bb A00;

    public AnonymousClass0Ba(AnonymousClass0Bb r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass0Bb r4 = this.A00;
        if (r4.A05) {
            if (r4.A07) {
                r4.A07 = false;
                AnonymousClass0BZ r5 = r4.A0F;
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                r5.A07 = currentAnimationTimeMillis;
                r5.A08 = -1;
                r5.A06 = currentAnimationTimeMillis;
                r5.A00 = 0.5f;
            }
            AnonymousClass0BZ r52 = r4.A0F;
            if ((r52.A08 <= 0 || AnimationUtils.currentAnimationTimeMillis() <= r52.A08 + ((long) r52.A03)) && r4.A04()) {
                if (r4.A00) {
                    r4.A00 = false;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    r4.A0D.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (r52.A06 != 0) {
                    long currentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float A002 = AnonymousClass0BZ.A00(r52, currentAnimationTimeMillis2);
                    r52.A06 = currentAnimationTimeMillis2;
                    float f = ((float) (currentAnimationTimeMillis2 - r52.A06)) * ((-4.0f * A002 * A002) + (A002 * 4.0f));
                    r4.A03((int) (r52.A01 * f), (int) (f * r52.A02));
                    r4.A0D.postOnAnimation(this);
                    return;
                }
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            r4.A05 = false;
        }
    }
}
