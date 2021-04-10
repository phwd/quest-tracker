package defpackage;

import android.content.DialogInterface;

/* renamed from: I10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I10 implements DialogInterface.OnClickListener {
    public final /* synthetic */ P10 F;

    public I10(P10 p10) {
        this.F = p10;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        P10 p10 = this.F;
        p10.b = true;
        p10.d.a(Double.NaN);
    }
}
