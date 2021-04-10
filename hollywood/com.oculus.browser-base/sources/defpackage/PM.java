package defpackage;

import android.content.DialogInterface;

/* renamed from: PM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PM implements DialogInterface.OnClickListener {
    public final /* synthetic */ SM F;

    public PM(SM sm) {
        this.F = sm;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            SM sm = this.F;
            sm.f8891a = 2;
            if (sm.e != null) {
                sm.c();
            } else {
                sm.d();
            }
        }
    }
}
