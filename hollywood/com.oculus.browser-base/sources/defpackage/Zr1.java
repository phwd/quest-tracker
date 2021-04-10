package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

/* renamed from: Zr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Zr1 extends AbstractC1743as1 {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f9378a;
    public Dialog b;
    public final boolean c;
    public int d;

    public Zr1(Activity activity, boolean z) {
        this.f9378a = activity;
        this.c = z;
    }

    public final void a(Context context, int i) {
        Dialog dialog;
        if (!(this.d == i || (dialog = this.b) == null)) {
            dialog.cancel();
            this.b = null;
        }
        if (this.b == null) {
            Object obj = SV.c;
            Dialog d2 = SV.d.d(this.f9378a, i, -1);
            this.b = d2;
            this.d = i;
            Yr1 yr1 = new Yr1();
            d2.setOnDismissListener(yr1);
            d2.setOnCancelListener(yr1);
        }
        Dialog dialog2 = this.b;
        if (dialog2 != null && !dialog2.isShowing()) {
            this.b.setCancelable(this.c);
            this.b.show();
            AbstractC3535lK0.a("Signin_Android_GmsUserRecoverableDialogShown");
        }
    }
}
