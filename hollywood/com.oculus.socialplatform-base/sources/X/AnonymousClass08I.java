package X;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.08I  reason: invalid class name */
public class AnonymousClass08I implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable";
    public final /* synthetic */ AnonymousClass08J A00;

    public AnonymousClass08I(AnonymousClass08J r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass08J r4 = this.A00;
        if (r4.A05) {
            if (r4.A07) {
                r4.A07 = false;
                AnonymousClass08H r5 = r4.A0F;
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                r5.A07 = currentAnimationTimeMillis;
                r5.A08 = -1;
                r5.A06 = currentAnimationTimeMillis;
                r5.A00 = 0.5f;
            }
            AnonymousClass08H r52 = r4.A0F;
            if ((r52.A08 <= 0 || AnimationUtils.currentAnimationTimeMillis() <= r52.A08 + ((long) r52.A03)) && r4.A04()) {
                if (r4.A00) {
                    r4.A00 = false;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 0);
                    r4.A0D.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (r52.A06 != 0) {
                    long currentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float A002 = AnonymousClass08H.A00(r52, currentAnimationTimeMillis2);
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
