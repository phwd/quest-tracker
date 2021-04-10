package defpackage;

import android.view.View;
import java.util.Iterator;

/* renamed from: Wc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC1348Wc0 implements View.OnClickListener {
    public final /* synthetic */ C1531Zc0 F;

    public View$OnClickListenerC1348Wc0(C1531Zc0 zc0) {
        this.F = zc0;
    }

    public void onClick(View view) {
        Iterator it = this.F.N0.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
        this.F.f1(false, false);
    }
}
