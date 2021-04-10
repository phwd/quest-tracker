package X;

import android.view.View;

/* renamed from: X.1s4  reason: invalid class name and case insensitive filesystem */
public class C11491s4 implements AbstractC003207l {
    public final /* synthetic */ C11201rK A00;

    public C11491s4(C11201rK r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC003207l
    public final void A6l(View view) {
        ((View) this.A00.A08.getParent()).invalidate();
    }
}
