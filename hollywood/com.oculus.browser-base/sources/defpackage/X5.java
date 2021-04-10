package defpackage;

import android.content.DialogInterface;
import org.chromium.base.Callback;

/* renamed from: X5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class X5 implements DialogInterface.OnCancelListener {
    public final Callback F;

    public X5(Callback callback) {
        this.F = callback;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.F.onResult("User closed the Payment Request UI.");
    }
}
