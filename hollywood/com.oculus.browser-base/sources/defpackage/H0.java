package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;

/* renamed from: H0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AccountChooserDialog F;

    public H0(AccountChooserDialog accountChooserDialog) {
        this.F = accountChooserDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AccountChooserDialog accountChooserDialog = this.F;
        accountChooserDialog.Q = accountChooserDialog.H[i];
    }
}
