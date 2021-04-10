package defpackage;

import android.view.KeyEvent;
import android.view.View;

/* renamed from: h60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractView$OnKeyListenerC2810h60 implements View.OnKeyListener {
    public abstract View a();

    public abstract View b();

    public boolean c() {
        return false;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        View a2;
        if (i == 61 && keyEvent.getAction() == 0) {
            if (keyEvent.hasNoModifiers()) {
                View b = b();
                if (b != null) {
                    return b.requestFocus();
                }
                return false;
            } else if (!keyEvent.isShiftPressed() || (a2 = a()) == null) {
                return false;
            } else {
                return a2.requestFocus();
            }
        } else if (i == 66 && keyEvent.getAction() == 1) {
            return c();
        } else {
            return false;
        }
    }
}
