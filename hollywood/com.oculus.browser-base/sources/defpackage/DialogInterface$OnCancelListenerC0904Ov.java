package defpackage;

import android.content.DialogInterface;

/* renamed from: Ov  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnCancelListenerC0904Ov implements DialogInterface.OnCancelListener {
    public final /* synthetic */ AlertDialogC1026Qv F;

    public DialogInterface$OnCancelListenerC0904Ov(AlertDialogC1026Qv qv) {
        this.F = qv;
    }

    public void onCancel(DialogInterface dialogInterface) {
        AlertDialogC1026Qv qv = this.F;
        int i = qv.L;
        AbstractC0656Ks0 ks0 = qv.K;
        if (ks0 != null) {
            ks0.a(i);
        }
    }
}
