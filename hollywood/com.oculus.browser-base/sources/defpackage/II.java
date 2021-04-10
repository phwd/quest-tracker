package defpackage;

import android.view.View;

/* renamed from: II  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class II implements View.OnClickListener {
    public final KI F;

    public II(KI ki) {
        this.F = ki;
    }

    public void onClick(View view) {
        KI ki = this.F;
        ki.I = true;
        ((C2329eH) ki.H).b();
        ki.dismiss();
    }
}
