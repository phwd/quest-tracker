package defpackage;

import android.content.DialogInterface;

/* renamed from: JM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JM0 implements DialogInterface.OnDismissListener {
    public final SM0 F;

    public JM0(SM0 sm0) {
        this.F = sm0;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.F.h.run();
    }
}
