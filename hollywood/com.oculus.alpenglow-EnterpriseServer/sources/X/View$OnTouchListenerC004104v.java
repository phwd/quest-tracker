package X;

import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

/* renamed from: X.04v  reason: invalid class name and case insensitive filesystem */
public class View$OnTouchListenerC004104v implements View.OnTouchListener {
    public final /* synthetic */ C04080dy A00;

    public View$OnTouchListenerC004104v(C04080dy r1) {
        this.A00 = r1;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (action == 0) {
            C04080dy r1 = this.A00;
            PopupWindow popupWindow = r1.A0A;
            if (popupWindow == null || !popupWindow.isShowing() || x < 0 || x >= r1.A0A.getWidth() || y < 0 || y >= r1.A0A.getHeight()) {
                return false;
            }
            r1.A0K.postDelayed(r1.A0L, 250);
            return false;
        } else if (action != 1) {
            return false;
        } else {
            C04080dy r0 = this.A00;
            r0.A0K.removeCallbacks(r0.A0L);
            return false;
        }
    }
}
