package defpackage;

import android.view.View;
import org.chromium.chrome.browser.password_manager.PasswordManagerDialogView;

/* renamed from: ny0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC3981ny0 implements View.OnClickListener {
    public final Runnable F;

    public View$OnClickListenerC3981ny0(Runnable runnable) {
        this.F = runnable;
    }

    public void onClick(View view) {
        Runnable runnable = this.F;
        int i = PasswordManagerDialogView.F;
        runnable.run();
    }
}
