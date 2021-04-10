package defpackage;

import android.view.View;
import java.util.ArrayList;

/* renamed from: Ib1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0493Ib1 implements View.OnClickListener {
    public final /* synthetic */ C0676Lb1 F;

    public View$OnClickListenerC0493Ib1(C0676Lb1 lb1) {
        this.F = lb1;
    }

    public void onClick(View view) {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.F.e.c) {
            arrayList.add(AbstractC1160Ta1.d(((AbstractC0246Ea1) this.F.b).i(), num.intValue()));
        }
        C0676Lb1 lb1 = this.F;
        C5616xb1 xb1 = lb1.h;
        if (xb1 != null) {
            xb1.a(arrayList, lb1.b);
        }
    }
}
