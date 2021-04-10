package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* renamed from: NE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NE implements DialogInterface.OnDismissListener {
    public final /* synthetic */ OE F;

    public NE(OE oe) {
        this.F = oe;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        OE oe = this.F;
        Dialog dialog = oe.I0;
        if (dialog != null) {
            oe.onDismiss(dialog);
        }
    }
}
