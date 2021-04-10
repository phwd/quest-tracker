package X;

import android.view.View;
import android.view.ViewPropertyAnimator;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.1Eg  reason: invalid class name */
public class AnonymousClass1Eg implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.DefaultItemAnimator$2";
    public final /* synthetic */ AnonymousClass1Ef A00;
    public final /* synthetic */ ArrayList A01;

    public AnonymousClass1Eg(AnonymousClass1Ef r1, ArrayList arrayList) {
        this.A00 = r1;
        this.A01 = arrayList;
    }

    public final void run() {
        View view;
        ArrayList arrayList = this.A01;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AnonymousClass1Ek r6 = (AnonymousClass1Ek) it.next();
            AnonymousClass1Ef r5 = this.A00;
            AnonymousClass1Ah r0 = r6.A05;
            View view2 = null;
            if (r0 == null) {
                view = null;
            } else {
                view = r0.itemView;
            }
            AnonymousClass1Ah r02 = r6.A04;
            if (r02 != null) {
                view2 = r02.itemView;
            }
            if (view != null) {
                ViewPropertyAnimator duration = view.animate().setDuration(((AnonymousClass1Al) r5).A01);
                r5.A02.add(r6.A05);
                duration.translationX((float) (r6.A02 - r6.A00));
                duration.translationY((float) (r6.A03 - r6.A01));
                duration.alpha(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z).setListener(new C05981Ee(r5, r6, duration, view)).start();
            }
            if (view2 != null) {
                ViewPropertyAnimator animate = view2.animate();
                r5.A02.add(r6.A04);
                animate.translationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z).translationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z).setDuration(((AnonymousClass1Al) r5).A01).alpha(1.0f).setListener(new AnonymousClass1Ej(r5, r6, animate, view2)).start();
            }
        }
        arrayList.clear();
        this.A00.A03.remove(arrayList);
    }
}
