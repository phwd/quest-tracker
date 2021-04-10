package defpackage;

import android.content.DialogInterface;

/* renamed from: dH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnDismissListenerC2159dH implements DialogInterface.OnDismissListener {
    public final C2329eH F;

    public DialogInterface$OnDismissListenerC2159dH(C2329eH eHVar) {
        this.F = eHVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        C2329eH eHVar = this.F;
        if (!eHVar.b) {
            eHVar.b();
        }
    }
}
