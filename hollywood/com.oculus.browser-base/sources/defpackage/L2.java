package defpackage;

import android.app.Activity;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* renamed from: L2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L2 extends C3493l60 implements View.OnLayoutChangeListener {
    public boolean H;
    public WeakReference I;

    public L2(WeakReference weakReference) {
        this.I = weakReference;
    }

    @Override // defpackage.C3493l60
    public void g() {
        Activity k = k();
        if (k != null) {
            View findViewById = k.findViewById(16908290);
            this.H = f(k, findViewById);
            findViewById.addOnLayoutChangeListener(this);
        }
    }

    @Override // defpackage.C3493l60
    public void j() {
        Activity k = k();
        if (k != null) {
            k.findViewById(16908290).removeOnLayoutChangeListener(this);
        }
    }

    public Activity k() {
        return (Activity) this.I.get();
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean f;
        Activity k = k();
        if (k != null && this.H != (f = f(k, view))) {
            this.H = f;
            Iterator it = this.G.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC3322k60) uq0.next()).b(f);
                } else {
                    return;
                }
            }
        }
    }
}
