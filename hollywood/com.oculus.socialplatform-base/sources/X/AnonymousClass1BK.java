package X;

import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.1BK  reason: invalid class name */
public class AnonymousClass1BK implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.RecyclerView$2";
    public final /* synthetic */ RecyclerView A00;

    public AnonymousClass1BK(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }

    public final void run() {
        long j;
        long j2;
        RecyclerView recyclerView = this.A00;
        AnonymousClass1Al r10 = recyclerView.mItemAnimator;
        if (r10 != null) {
            AnonymousClass1Ef r102 = (AnonymousClass1Ef) r10;
            ArrayList<AnonymousClass1Ah> arrayList = r102.A09;
            boolean z = !arrayList.isEmpty();
            boolean z2 = !r102.A08.isEmpty();
            boolean z3 = !r102.A07.isEmpty();
            boolean z4 = !r102.A06.isEmpty();
            if (z || z2 || z4 || z3) {
                Iterator<AnonymousClass1Ah> it = arrayList.iterator();
                while (it.hasNext()) {
                    AnonymousClass1Ah next = it.next();
                    View view = next.itemView;
                    ViewPropertyAnimator animate = view.animate();
                    r102.A0A.add(next);
                    animate.setDuration(((AnonymousClass1Al) r102).A03).alpha(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z).setListener(new C05951Eb(r102, next, animate, view)).start();
                }
                r102.A09.clear();
                if (z2) {
                    ArrayList<AnonymousClass1El> arrayList2 = new ArrayList<>();
                    arrayList2.addAll(r102.A08);
                    r102.A05.add(arrayList2);
                    r102.A08.clear();
                    AnonymousClass1Eh r3 = new AnonymousClass1Eh(r102, arrayList2);
                    if (z) {
                        arrayList2.get(0).A04.itemView.postOnAnimationDelayed(r3, ((AnonymousClass1Al) r102).A03);
                    } else {
                        r3.run();
                    }
                }
                if (z3) {
                    ArrayList<AnonymousClass1Ek> arrayList3 = new ArrayList<>();
                    arrayList3.addAll(r102.A07);
                    r102.A03.add(arrayList3);
                    r102.A07.clear();
                    AnonymousClass1Eg r32 = new AnonymousClass1Eg(r102, arrayList3);
                    if (z) {
                        arrayList3.get(0).A05.itemView.postOnAnimationDelayed(r32, ((AnonymousClass1Al) r102).A03);
                    } else {
                        r32.run();
                    }
                }
                if (z4) {
                    ArrayList<AnonymousClass1Ah> arrayList4 = new ArrayList<>();
                    arrayList4.addAll(r102.A06);
                    r102.A01.add(arrayList4);
                    r102.A06.clear();
                    AnonymousClass1Ei r6 = new AnonymousClass1Ei(r102, arrayList4);
                    if (z || z2 || z3) {
                        long j3 = 0;
                        if (z) {
                            j = ((AnonymousClass1Al) r102).A03;
                        } else {
                            j = 0;
                        }
                        if (z2) {
                            j2 = ((AnonymousClass1Al) r102).A02;
                        } else {
                            j2 = 0;
                        }
                        if (z3) {
                            j3 = ((AnonymousClass1Al) r102).A01;
                        }
                        arrayList4.get(0).itemView.postOnAnimationDelayed(r6, j + Math.max(j2, j3));
                    } else {
                        r6.run();
                    }
                }
            }
        }
        recyclerView.mPostedAnimatorRunner = false;
    }
}
