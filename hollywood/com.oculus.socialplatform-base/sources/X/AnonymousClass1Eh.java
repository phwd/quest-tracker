package X;

import android.view.View;
import android.view.ViewPropertyAnimator;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.1Eh  reason: invalid class name */
public class AnonymousClass1Eh implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.DefaultItemAnimator$1";
    public final /* synthetic */ AnonymousClass1Ef A00;
    public final /* synthetic */ ArrayList A01;

    public AnonymousClass1Eh(AnonymousClass1Ef r1, ArrayList arrayList) {
        this.A00 = r1;
        this.A01 = arrayList;
    }

    public final void run() {
        ArrayList arrayList = this.A01;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AnonymousClass1El r3 = (AnonymousClass1El) it.next();
            AnonymousClass1Ef r6 = this.A00;
            AnonymousClass1Ah r7 = r3.A04;
            int i = r3.A00;
            int i2 = r3.A01;
            int i3 = r3.A02;
            int i4 = r3.A03;
            View view = r7.itemView;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (i5 != 0) {
                view.animate().translationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            }
            if (i6 != 0) {
                view.animate().translationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            }
            ViewPropertyAnimator animate = view.animate();
            r6.A04.add(r7);
            animate.setDuration(((AnonymousClass1Al) r6).A02).setListener(new C05971Ed(r6, r7, i5, view, i6, animate)).start();
        }
        arrayList.clear();
        this.A00.A05.remove(arrayList);
    }
}
