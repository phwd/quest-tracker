package defpackage;

import android.os.SystemClock;
import android.view.MenuItem;

/* renamed from: Mm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0764Mm implements AbstractC0696Li0 {
    public final /* synthetic */ View$OnKeyListenerC0886Om F;

    public C0764Mm(View$OnKeyListenerC0886Om om) {
        this.F = om;
    }

    @Override // defpackage.AbstractC0696Li0
    public void a(C4616ri0 ri0, MenuItem menuItem) {
        this.F.L.removeCallbacksAndMessages(ri0);
    }

    @Override // defpackage.AbstractC0696Li0
    public void n(C4616ri0 ri0, MenuItem menuItem) {
        C0825Nm nm = null;
        this.F.L.removeCallbacksAndMessages(null);
        int size = this.F.N.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (ri0 == ((C0825Nm) this.F.N.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            int i2 = i + 1;
            if (i2 < this.F.N.size()) {
                nm = (C0825Nm) this.F.N.get(i2);
            }
            this.F.L.postAtTime(new RunnableC0704Lm(this, nm, menuItem, ri0), ri0, SystemClock.uptimeMillis() + 200);
        }
    }
}
