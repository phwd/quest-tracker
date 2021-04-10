package defpackage;

import android.view.KeyEvent;

/* renamed from: R40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class R40 {
    public static boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160;
        }
        return false;
    }

    public static boolean b(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 20) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 146) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 21) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 148) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 22) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 150) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 19) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 152) {
                return true;
            }
        }
        return false;
    }
}
