package defpackage;

import android.view.View;

/* renamed from: g40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC2633g40 implements View.OnClickListener {
    public final /* synthetic */ C3316k40 F;

    public View$OnClickListenerC2633g40(C3316k40 k40) {
        this.F = k40;
    }

    public void onClick(View view) {
        String str;
        BE be;
        C3316k40 k40 = this.F;
        AbstractC3145j40 j40 = k40.c;
        AE ae = k40.k;
        int i = ae.f7662J;
        if (i == -1 || (be = (BE) ae.getItem(i)) == null) {
            str = "";
        } else {
            str = be.f7724a;
        }
        j40.a(str);
        this.F.b.setOnDismissListener(null);
        this.F.b.dismiss();
    }
}
