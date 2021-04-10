package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* renamed from: zK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnKeyListenerC5919zK implements View.OnKeyListener {
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 23 || keyEvent.getAction() != 1) {
            return false;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        inputMethodManager.viewClicked(view);
        inputMethodManager.showSoftInput(view, 0);
        return true;
    }
}
