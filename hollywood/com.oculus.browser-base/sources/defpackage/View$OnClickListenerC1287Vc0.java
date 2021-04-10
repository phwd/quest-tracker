package defpackage;

import android.view.View;
import java.util.Iterator;

/* renamed from: Vc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC1287Vc0 implements View.OnClickListener {
    public final /* synthetic */ C1531Zc0 F;

    public View$OnClickListenerC1287Vc0(C1531Zc0 zc0) {
        this.F = zc0;
    }

    public void onClick(View view) {
        Iterator it = this.F.M0.iterator();
        while (it.hasNext()) {
            ((AbstractC1688ad0) it.next()).a(this.F.R0.T());
        }
        this.F.f1(false, false);
    }
}
