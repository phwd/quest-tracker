package defpackage;

import android.content.DialogInterface;

/* renamed from: V5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V5 implements DialogInterface.OnClickListener {
    public final Runnable F;

    public V5(Runnable runnable) {
        this.F = runnable;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.run();
    }
}
