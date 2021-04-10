package defpackage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

/* renamed from: n90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnTouchListenerC3844n90 implements View.OnTouchListener {
    public final /* synthetic */ AbstractC4186p90 F;

    public View$OnTouchListenerC3844n90(AbstractC4186p90 p90) {
        this.F = p90;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        PopupWindow popupWindow;
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (action == 0 && (popupWindow = this.F.g0) != null && popupWindow.isShowing() && x >= 0 && x < this.F.g0.getWidth() && y >= 0 && y < this.F.g0.getHeight()) {
            AbstractC4186p90 p90 = this.F;
            p90.c0.postDelayed(p90.Y, 250);
            return false;
        } else if (action != 1) {
            return false;
        } else {
            AbstractC4186p90 p902 = this.F;
            p902.c0.removeCallbacks(p902.Y);
            return false;
        }
    }
}
