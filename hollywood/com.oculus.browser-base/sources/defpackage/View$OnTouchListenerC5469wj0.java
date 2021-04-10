package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: wj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnTouchListenerC5469wj0 implements View.OnTouchListener {
    public final Runnable F;

    public View$OnTouchListenerC5469wj0(Runnable runnable) {
        this.F = runnable;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.F.run();
        return false;
    }
}
