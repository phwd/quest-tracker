package defpackage;

import android.view.View;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryModernView;

/* renamed from: y50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5706y50 implements View.OnClickListener {
    public final Runnable F;

    public View$OnClickListenerC5706y50(Runnable runnable) {
        this.F = runnable;
    }

    public void onClick(View view) {
        Runnable runnable = this.F;
        int i = KeyboardAccessoryModernView.L;
        runnable.run();
    }
}
