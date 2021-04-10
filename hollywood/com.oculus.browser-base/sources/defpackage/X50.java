package defpackage;

import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;

/* renamed from: X50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class X50 implements Runnable {
    public final KeyboardAccessoryView F;

    public X50(KeyboardAccessoryView keyboardAccessoryView) {
        this.F = keyboardAccessoryView;
    }

    public void run() {
        this.F.setVisibility(0);
    }
}
