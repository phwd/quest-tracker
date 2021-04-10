package defpackage;

import android.content.DialogInterface;

/* renamed from: Yr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yr1 implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public boolean F;

    public void onCancel(DialogInterface dialogInterface) {
        this.F = true;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.F) {
            AbstractC3535lK0.a("Signin_Android_GmsUserRecoverableDialogAccepted");
        }
    }
}
