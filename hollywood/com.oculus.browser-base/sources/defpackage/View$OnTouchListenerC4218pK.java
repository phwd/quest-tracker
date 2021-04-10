package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: pK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnTouchListenerC4218pK implements View.OnTouchListener {
    public final /* synthetic */ C4389qK F;

    public View$OnTouchListenerC4218pK(C4389qK qKVar) {
        this.F = qKVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.F.e();
        return false;
    }
}
