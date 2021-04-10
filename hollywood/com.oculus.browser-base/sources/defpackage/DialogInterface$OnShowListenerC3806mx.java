package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.signin.ui.ConfirmImportSyncDataDialog;

/* renamed from: mx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnShowListenerC3806mx implements DialogInterface.OnShowListener {
    public final DialogC2461f4 F;
    public final boolean G;

    public DialogInterface$OnShowListenerC3806mx(DialogC2461f4 f4Var, boolean z) {
        this.F = f4Var;
        this.G = z;
    }

    public void onShow(DialogInterface dialogInterface) {
        DialogC2461f4 f4Var = this.F;
        boolean z = this.G;
        int i = ConfirmImportSyncDataDialog.M0;
        f4Var.c(-1).setEnabled(z);
    }
}
