package defpackage;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

/* renamed from: PL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PL extends DialogFragment {
    public Dialog F = null;
    public DialogInterface.OnCancelListener G = null;

    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.G;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.F == null) {
            setShowsDialog(false);
        }
        return this.F;
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
