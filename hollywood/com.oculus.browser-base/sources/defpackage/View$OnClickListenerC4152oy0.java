package defpackage;

import android.view.View;
import org.chromium.chrome.browser.password_manager.PasswordManagerDialogView;

/* renamed from: oy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4152oy0 implements View.OnClickListener {
    public final Runnable F;

    public View$OnClickListenerC4152oy0(Runnable runnable) {
        this.F = runnable;
    }

    public void onClick(View view) {
        Runnable runnable = this.F;
        int i = PasswordManagerDialogView.F;
        runnable.run();
    }
}
