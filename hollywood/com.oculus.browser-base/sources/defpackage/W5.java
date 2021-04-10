package defpackage;

import android.content.DialogInterface;
import org.chromium.base.Callback;

/* renamed from: W5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W5 implements DialogInterface.OnClickListener {
    public final Callback F;

    public W5(Callback callback) {
        this.F = callback;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.onResult("User closed the Payment Request UI.");
    }
}
