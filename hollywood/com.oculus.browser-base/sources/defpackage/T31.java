package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* renamed from: T31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T31 extends OE {
    public Dialog M0 = null;
    public DialogInterface.OnCancelListener N0 = null;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        Dialog dialog = this.M0;
        if (dialog == null) {
            this.F0 = false;
        }
        return dialog;
    }

    @Override // defpackage.OE
    public void k1(KS ks, String str) {
        super.k1(ks, str);
    }

    @Override // defpackage.OE
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.N0;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }
}
