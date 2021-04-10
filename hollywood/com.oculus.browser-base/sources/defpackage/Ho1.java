package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.oculus.browser.R;

/* renamed from: Ho1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Ho1 extends AlertDialog implements DialogInterface.OnClickListener, Fo1 {
    public final Go1 F;
    public final N10 G;

    public Ho1(Context context, int i, N10 n10, int i2, int i3, double d, double d2) {
        super(context, i);
        this.G = n10;
        setButton(-1, context.getText(R.string.f50710_resource_name_obfuscated_RES_2131952388), this);
        setButton(-2, context.getText(17039360), (DialogInterface.OnClickListener) null);
        setIcon(0);
        Go1 a2 = a(context, d, d2);
        this.F = a2;
        setView(a2);
        a2.i(i2, i3);
        a2.j();
        a2.H = this;
    }

    public abstract Go1 a(Context context, double d, double d2);

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.G != null) {
            this.F.clearFocus();
            N10 n10 = this.G;
            int g = this.F.g();
            int f = this.F.f();
            int i2 = n10.f8520a;
            if (i2 == 11) {
                n10.b.b(i2, g, f, 0, 0, 0, 0, 0, 0);
            } else {
                n10.b.b(i2, g, 0, 0, 0, 0, 0, 0, f);
            }
        }
    }
}
