package defpackage;

import android.content.DialogInterface;

/* renamed from: s90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnClickListenerC4698s90 implements DialogInterface.OnClickListener {
    public final /* synthetic */ C4868t90 F;

    public DialogInterface$OnClickListenerC4698s90(C4868t90 t90) {
        this.F = t90;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C4868t90 t90 = this.F;
        t90.U0 = i;
        t90.T0 = -1;
        dialogInterface.dismiss();
    }
}
