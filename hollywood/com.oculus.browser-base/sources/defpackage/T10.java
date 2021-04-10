package defpackage;

import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: T10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T10 implements View.OnTouchListener {
    public final Dialog F;
    public final int G;
    public final int H;
    public final int I;

    public T10(Dialog dialog, Rect rect) {
        this.F = dialog;
        this.G = rect.left;
        this.H = rect.top;
        this.I = ViewConfiguration.get(dialog.getContext()).getScaledWindowTouchSlop();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        View findViewById = view.findViewById(16908290);
        int left = findViewById.getLeft() + this.G;
        int width = findViewById.getWidth() + left;
        int top = findViewById.getTop() + this.H;
        if (new RectF((float) left, (float) top, (float) width, (float) (findViewById.getHeight() + top)).contains(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        if (motionEvent.getAction() == 1) {
            obtain.setAction(4);
        }
        if (Build.VERSION.SDK_INT < 28) {
            obtain.setAction(0);
            int i = this.I;
            obtain.setLocation((float) ((-i) - 1), (float) ((-i) - 1));
        }
        view.performClick();
        return this.F.onTouchEvent(obtain);
    }
}
