package X;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1Ef  reason: invalid class name */
public final class AnonymousClass1Ef extends AnonymousClass1Az {
    public static TimeInterpolator A0B;
    public ArrayList<AnonymousClass1Ah> A00 = new ArrayList<>();
    public ArrayList<ArrayList<AnonymousClass1Ah>> A01 = new ArrayList<>();
    public ArrayList<AnonymousClass1Ah> A02 = new ArrayList<>();
    public ArrayList<ArrayList<AnonymousClass1Ek>> A03 = new ArrayList<>();
    public ArrayList<AnonymousClass1Ah> A04 = new ArrayList<>();
    public ArrayList<ArrayList<AnonymousClass1El>> A05 = new ArrayList<>();
    public ArrayList<AnonymousClass1Ah> A06 = new ArrayList<>();
    public ArrayList<AnonymousClass1Ek> A07 = new ArrayList<>();
    public ArrayList<AnonymousClass1El> A08 = new ArrayList<>();
    public ArrayList<AnonymousClass1Ah> A09 = new ArrayList<>();
    public ArrayList<AnonymousClass1Ah> A0A = new ArrayList<>();

    public static void A01(AnonymousClass1Ef r2, AnonymousClass1Ah r3) {
        if (A0B == null) {
            A0B = new ValueAnimator().getInterpolator();
        }
        r3.itemView.animate().setInterpolator(A0B);
        r2.A07(r3);
    }

    public static boolean A04(AnonymousClass1Ef r4, AnonymousClass1Ek r5, AnonymousClass1Ah r6) {
        if (r5.A04 == r6) {
            r5.A04 = null;
        } else if (r5.A05 != r6) {
            return false;
        } else {
            r5.A05 = null;
        }
        r6.itemView.setAlpha(1.0f);
        r6.itemView.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        r6.itemView.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        AnonymousClass1BQ r0 = ((AnonymousClass1Al) r4).A04;
        if (r0 != null) {
            r0.A6j(r6);
        }
        return true;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/List<LX/1Ek;>;LX/1Ah;)V */
    public static void A02(AnonymousClass1Ef r3, List list, AnonymousClass1Ah r5) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass1Ek r1 = (AnonymousClass1Ek) list.get(size);
                if (A04(r3, r1, r5) && r1.A05 == null && r1.A04 == null) {
                    list.remove(r1);
                }
            } else {
                return;
            }
        }
    }

    public static final void A03(List<AnonymousClass1Ah> list) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                list.get(size).itemView.animate().cancel();
            } else {
                return;
            }
        }
    }

    @Override // X.AnonymousClass1Al
    public final boolean A0A(@NonNull AnonymousClass1Ah r3, @NonNull List<Object> list) {
        if (!list.isEmpty() || super.A0A(r3, list)) {
            return true;
        }
        return false;
    }

    public final void A0C() {
        if (!A08()) {
            ArrayList<RecyclerView.ItemAnimator.ItemAnimatorFinishedListener> arrayList = super.A05;
            if (0 < arrayList.size()) {
                arrayList.get(0);
                throw new NullPointerException("onAnimationsFinished");
            } else {
                arrayList.clear();
            }
        }
    }
}
