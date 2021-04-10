package defpackage;

import android.content.DialogInterface;

/* renamed from: GI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class GI implements DialogInterface.OnDismissListener {
    public final KI F;

    public GI(KI ki) {
        this.F = ki;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        KI ki = this.F;
        if (!ki.I) {
            ((C2329eH) ki.H).b();
        }
    }
}
