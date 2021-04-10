package defpackage;

import android.view.MotionEvent;
import android.view.View;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;

/* renamed from: V50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V50 implements View.OnTouchListener {
    public final KeyboardAccessoryView F;

    public V50(KeyboardAccessoryView keyboardAccessoryView) {
        this.F = keyboardAccessoryView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.F.a();
    }
}
