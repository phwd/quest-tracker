package defpackage;

import android.text.Editable;
import android.view.View;

/* renamed from: Xu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC1451Xu implements View.OnClickListener {
    public final /* synthetic */ C2092cv F;

    public View$OnClickListenerC1451Xu(C2092cv cvVar) {
        this.F = cvVar;
    }

    public void onClick(View view) {
        Editable text = this.F.f9772a.f9696J.getText();
        if (text != null) {
            text.clear();
        }
    }
}
