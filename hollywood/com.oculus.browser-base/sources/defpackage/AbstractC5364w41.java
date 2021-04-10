package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;

/* renamed from: w41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5364w41 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final GestureDetector f11517a;
    public final PointF b = new PointF();
    public int c = 0;
    public final AbstractC5194v41 d;
    public final int e;
    public final int f;

    public AbstractC5364w41(Context context, AbstractC5194v41 v41) {
        this.f11517a = new GestureDetector(context, this, ThreadUtils.b());
        this.e = context.getResources().getDimensionPixelOffset(R.dimen.f25310_resource_name_obfuscated_RES_2131166150);
        this.f = context.getResources().getDimensionPixelOffset(R.dimen.f25290_resource_name_obfuscated_RES_2131166148);
        this.d = v41;
    }

    public boolean a(MotionEvent motionEvent) {
        boolean onTouchEvent = this.f11517a.onTouchEvent(motionEvent);
        if (this.d == null) {
            return onTouchEvent;
        }
        int action = motionEvent.getAction();
        if ((action != 1 && action != 3) || this.c == 0) {
            return onTouchEvent;
        }
        this.d.h();
        this.c = 0;
        return true;
    }

    public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        int i;
        AbstractC5194v41 v41 = this.d;
        if (v41 == null || (i = this.c) == 0) {
            return false;
        }
        v41.d(i, motionEvent2, motionEvent2.getRawX() - this.b.x, motionEvent2.getRawY() - this.b.y, f2, f3);
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (!(this.d == null || motionEvent == null || motionEvent2 == null)) {
            if (this.c == 0 && b(motionEvent, motionEvent2)) {
                float rawX = motionEvent2.getRawX() - motionEvent.getRawX();
                float rawY = motionEvent2.getRawY() - motionEvent.getRawY();
                if (Math.abs(rawX) < ((float) this.f) && Math.abs(rawY) < ((float) this.e)) {
                    return false;
                }
                int i = Math.abs(rawX) > Math.abs(rawY) ? rawX > 0.0f ? 2 : 1 : rawY > 0.0f ? 4 : 3;
                if (i != 0 && this.d.i(i)) {
                    this.c = i;
                    this.d.j(i, motionEvent2);
                    this.b.set(motionEvent2.getRawX(), motionEvent2.getRawY());
                }
            }
            if (this.c != 0) {
                this.d.f(motionEvent2, motionEvent2.getRawX() - this.b.x, motionEvent2.getRawY() - this.b.y, -f2, -f3);
                return true;
            }
        }
        return false;
    }
}
