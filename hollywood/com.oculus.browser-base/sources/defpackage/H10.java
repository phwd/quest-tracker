package defpackage;

import J.N;
import android.content.DialogInterface;
import org.chromium.content.browser.input.DateTimeChooserAndroid;

/* renamed from: H10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H10 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ P10 F;

    public H10(P10 p10) {
        this.F = p10;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        P10 p10 = this.F;
        if (p10.c == dialogInterface && !p10.b) {
            p10.b = true;
            DateTimeChooserAndroid dateTimeChooserAndroid = p10.d.f10265a;
            N.MCtaZNwj(dateTimeChooserAndroid.f10926a, dateTimeChooserAndroid);
        }
    }
}
