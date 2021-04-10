package defpackage;

import android.content.DialogInterface;

/* renamed from: IM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class IM0 implements DialogInterface.OnShowListener {
    public final Runnable F;

    public IM0(Runnable runnable) {
        this.F = runnable;
    }

    public void onShow(DialogInterface dialogInterface) {
        this.F.run();
    }
}
