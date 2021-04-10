package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* renamed from: ME  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ME implements DialogInterface.OnCancelListener {
    public final /* synthetic */ OE F;

    public ME(OE oe) {
        this.F = oe;
    }

    public void onCancel(DialogInterface dialogInterface) {
        OE oe = this.F;
        Dialog dialog = oe.I0;
        if (dialog != null) {
            oe.onCancel(dialog);
        }
    }
}
