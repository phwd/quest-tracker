package defpackage;

import android.content.DialogInterface;

/* renamed from: Nv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC0843Nv implements DialogInterface.OnClickListener {
    public final /* synthetic */ AlertDialogC1026Qv F;

    public DialogInterface$OnClickListenerC0843Nv(AlertDialogC1026Qv qv) {
        this.F = qv;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AlertDialogC1026Qv qv = this.F;
        int i2 = qv.L;
        AbstractC0656Ks0 ks0 = qv.K;
        if (ks0 != null) {
            ks0.a(i2);
        }
    }
}
