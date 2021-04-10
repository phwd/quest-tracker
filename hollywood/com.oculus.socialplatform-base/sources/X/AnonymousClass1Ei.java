package X;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.1Ei  reason: invalid class name */
public class AnonymousClass1Ei implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.DefaultItemAnimator$3";
    public final /* synthetic */ AnonymousClass1Ef A00;
    public final /* synthetic */ ArrayList A01;

    public AnonymousClass1Ei(AnonymousClass1Ef r1, ArrayList arrayList) {
        this.A00 = r1;
        this.A01 = arrayList;
    }

    public final void run() {
        ArrayList arrayList = this.A01;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AnonymousClass1Ah r6 = (AnonymousClass1Ah) it.next();
            AnonymousClass1Ef r5 = this.A00;
            View view = r6.itemView;
            ViewPropertyAnimator animate = view.animate();
            r5.A00.add(r6);
            animate.alpha(1.0f).setDuration(((AnonymousClass1Al) r5).A00).setListener(new C05961Ec(r5, r6, view, animate)).start();
        }
        arrayList.clear();
        this.A00.A01.remove(arrayList);
    }
}
