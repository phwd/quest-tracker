package X;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1Al  reason: invalid class name */
public abstract class AnonymousClass1Al {
    public long A00 = 120;
    public long A01 = 250;
    public long A02 = 250;
    public long A03 = 120;
    public AnonymousClass1BQ A04 = null;
    public ArrayList<RecyclerView.ItemAnimator.ItemAnimatorFinishedListener> A05 = new ArrayList<>();

    public final void A06() {
        AnonymousClass1Ef r5 = (AnonymousClass1Ef) this;
        ArrayList<AnonymousClass1El> arrayList = r5.A08;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            AnonymousClass1El r1 = arrayList.get(size);
            View view = r1.A04.itemView;
            view.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            view.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            AnonymousClass1Ah r12 = r1.A04;
            AnonymousClass1BQ r0 = ((AnonymousClass1Al) r5).A04;
            if (r0 != null) {
                r0.A6j(r12);
            }
            arrayList = r5.A08;
            arrayList.remove(size);
        }
        ArrayList<AnonymousClass1Ah> arrayList2 = r5.A09;
        int size2 = arrayList2.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            AnonymousClass1Ah r13 = arrayList2.get(size2);
            AnonymousClass1BQ r02 = ((AnonymousClass1Al) r5).A04;
            if (r02 != null) {
                r02.A6j(r13);
            }
            arrayList2 = r5.A09;
            arrayList2.remove(size2);
        }
        ArrayList<AnonymousClass1Ah> arrayList3 = r5.A06;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            AnonymousClass1Ah r14 = arrayList3.get(size3);
            r14.itemView.setAlpha(1.0f);
            AnonymousClass1BQ r03 = ((AnonymousClass1Al) r5).A04;
            if (r03 != null) {
                r03.A6j(r14);
            }
            arrayList3 = r5.A06;
            arrayList3.remove(size3);
        }
        int size4 = r5.A07.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            AnonymousClass1Ek r15 = r5.A07.get(size4);
            AnonymousClass1Ah r04 = r15.A05;
            if (r04 != null) {
                AnonymousClass1Ef.A04(r5, r15, r04);
            }
            AnonymousClass1Ah r05 = r15.A04;
            if (r05 != null) {
                AnonymousClass1Ef.A04(r5, r15, r05);
            }
        }
        r5.A07.clear();
        if (r5.A08()) {
            int size5 = r5.A05.size();
            while (true) {
                size5--;
                if (size5 < 0) {
                    break;
                }
                ArrayList<AnonymousClass1El> arrayList4 = r5.A05.get(size5);
                int size6 = arrayList4.size();
                while (true) {
                    size6--;
                    if (size6 >= 0) {
                        AnonymousClass1El r16 = arrayList4.get(size6);
                        View view2 = r16.A04.itemView;
                        view2.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                        view2.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                        AnonymousClass1Ah r17 = r16.A04;
                        AnonymousClass1BQ r06 = ((AnonymousClass1Al) r5).A04;
                        if (r06 != null) {
                            r06.A6j(r17);
                        }
                        arrayList4.remove(size6);
                        if (arrayList4.isEmpty()) {
                            r5.A05.remove(arrayList4);
                        }
                    }
                }
            }
            int size7 = r5.A01.size();
            while (true) {
                size7--;
                if (size7 < 0) {
                    break;
                }
                ArrayList<AnonymousClass1Ah> arrayList5 = r5.A01.get(size7);
                int size8 = arrayList5.size();
                while (true) {
                    size8--;
                    if (size8 >= 0) {
                        AnonymousClass1Ah r18 = arrayList5.get(size8);
                        r18.itemView.setAlpha(1.0f);
                        AnonymousClass1BQ r07 = ((AnonymousClass1Al) r5).A04;
                        if (r07 != null) {
                            r07.A6j(r18);
                        }
                        arrayList5.remove(size8);
                        if (arrayList5.isEmpty()) {
                            r5.A01.remove(arrayList5);
                        }
                    }
                }
            }
            int size9 = r5.A03.size();
            while (true) {
                size9--;
                if (size9 < 0) {
                    break;
                }
                ArrayList<AnonymousClass1Ek> arrayList6 = r5.A03.get(size9);
                int size10 = arrayList6.size();
                while (true) {
                    size10--;
                    if (size10 >= 0) {
                        AnonymousClass1Ek r19 = arrayList6.get(size10);
                        AnonymousClass1Ah r08 = r19.A05;
                        if (r08 != null) {
                            AnonymousClass1Ef.A04(r5, r19, r08);
                        }
                        AnonymousClass1Ah r09 = r19.A04;
                        if (r09 != null) {
                            AnonymousClass1Ef.A04(r5, r19, r09);
                        }
                        if (arrayList6.isEmpty()) {
                            r5.A03.remove(arrayList6);
                        }
                    }
                }
            }
            AnonymousClass1Ef.A03(r5.A0A);
            AnonymousClass1Ef.A03(r5.A04);
            AnonymousClass1Ef.A03(r5.A00);
            AnonymousClass1Ef.A03(r5.A02);
            ArrayList<RecyclerView.ItemAnimator.ItemAnimatorFinishedListener> arrayList7 = ((AnonymousClass1Al) r5).A05;
            if (0 < arrayList7.size()) {
                arrayList7.get(0);
                throw new NullPointerException("onAnimationsFinished");
            } else {
                arrayList7.clear();
            }
        }
    }

    public final void A07(@NonNull AnonymousClass1Ah r9) {
        AnonymousClass1Ef r3 = (AnonymousClass1Ef) this;
        View view = r9.itemView;
        view.animate().cancel();
        ArrayList<AnonymousClass1El> arrayList = r3.A08;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (arrayList.get(size).A04 == r9) {
                view.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                view.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                AnonymousClass1BQ r0 = ((AnonymousClass1Al) r3).A04;
                if (r0 != null) {
                    r0.A6j(r9);
                }
                arrayList = r3.A08;
                arrayList.remove(size);
            }
        }
        AnonymousClass1Ef.A02(r3, r3.A07, r9);
        if (r3.A09.remove(r9)) {
            view.setAlpha(1.0f);
            AnonymousClass1BQ r02 = ((AnonymousClass1Al) r3).A04;
            if (r02 != null) {
                r02.A6j(r9);
            }
        }
        if (r3.A06.remove(r9)) {
            view.setAlpha(1.0f);
            AnonymousClass1BQ r03 = ((AnonymousClass1Al) r3).A04;
            if (r03 != null) {
                r03.A6j(r9);
            }
        }
        int size2 = r3.A03.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            ArrayList<AnonymousClass1Ek> arrayList2 = r3.A03.get(size2);
            AnonymousClass1Ef.A02(r3, arrayList2, r9);
            if (arrayList2.isEmpty()) {
                r3.A03.remove(size2);
            }
        }
        int size3 = r3.A05.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            ArrayList<AnonymousClass1El> arrayList3 = r3.A05.get(size3);
            int size4 = arrayList3.size();
            while (true) {
                size4--;
                if (size4 < 0) {
                    break;
                } else if (arrayList3.get(size4).A04 == r9) {
                    view.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    view.setTranslationX(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    AnonymousClass1BQ r04 = ((AnonymousClass1Al) r3).A04;
                    if (r04 != null) {
                        r04.A6j(r9);
                    }
                    arrayList3.remove(size4);
                    if (arrayList3.isEmpty()) {
                        r3.A05.remove(size3);
                    }
                }
            }
        }
        int size5 = r3.A01.size();
        while (true) {
            size5--;
            if (size5 >= 0) {
                ArrayList<AnonymousClass1Ah> arrayList4 = r3.A01.get(size5);
                if (arrayList4.remove(r9)) {
                    view.setAlpha(1.0f);
                    AnonymousClass1BQ r05 = ((AnonymousClass1Al) r3).A04;
                    if (r05 != null) {
                        r05.A6j(r9);
                    }
                    if (arrayList4.isEmpty()) {
                        r3.A01.remove(size5);
                    }
                }
            } else {
                r3.A0A.remove(r9);
                r3.A00.remove(r9);
                r3.A02.remove(r9);
                r3.A04.remove(r9);
                r3.A0C();
                return;
            }
        }
    }

    public final boolean A08() {
        AnonymousClass1Ef r1 = (AnonymousClass1Ef) this;
        if (!r1.A06.isEmpty() || !r1.A07.isEmpty() || !r1.A08.isEmpty() || !r1.A09.isEmpty() || !r1.A04.isEmpty() || !r1.A0A.isEmpty() || !r1.A00.isEmpty() || !r1.A02.isEmpty() || !r1.A05.isEmpty() || !r1.A01.isEmpty() || !r1.A03.isEmpty()) {
            return true;
        }
        return false;
    }

    public static int A00(AnonymousClass1Ah r4) {
        int i = r4.mFlags & 14;
        if (r4.isInvalid()) {
            return 4;
        }
        if ((i & 4) != 0) {
            return i;
        }
        int i2 = r4.mOldPosition;
        int absoluteAdapterPosition = r4.getAbsoluteAdapterPosition();
        if (i2 == -1 || absoluteAdapterPosition == -1 || i2 == absoluteAdapterPosition) {
            return i;
        }
        return i | 2048;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1As;LX/1Ah;ILjava/util/List<Ljava/lang/Object;>;)LX/1BS; */
    @NonNull
    public final AnonymousClass1BS A05(@NonNull AnonymousClass1Ah r4) {
        AnonymousClass1BS r2 = new AnonymousClass1BS();
        View view = r4.itemView;
        r2.A00 = view.getLeft();
        r2.A01 = view.getTop();
        view.getRight();
        view.getBottom();
        return r2;
    }

    public final boolean A09(@NonNull AnonymousClass1Ah r19, @NonNull AnonymousClass1Ah r20, @NonNull AnonymousClass1BS r21, @NonNull AnonymousClass1BS r22) {
        int i;
        int i2;
        AnonymousClass1Az r12 = (AnonymousClass1Az) this;
        int i3 = r21.A00;
        int i4 = r21.A01;
        if (r20.shouldIgnore()) {
            i2 = i4;
            i = i3;
        } else {
            i = r22.A00;
            i2 = r22.A01;
        }
        AnonymousClass1Ef r122 = (AnonymousClass1Ef) r12;
        if (r19 == r20) {
            return r122.A0B(r19, i3, i4, i, i2);
        }
        float translationX = r19.itemView.getTranslationX();
        float translationY = r19.itemView.getTranslationY();
        float alpha = r19.itemView.getAlpha();
        AnonymousClass1Ef.A01(r122, r19);
        r19.itemView.setTranslationX(translationX);
        r19.itemView.setTranslationY(translationY);
        r19.itemView.setAlpha(alpha);
        AnonymousClass1Ef.A01(r122, r20);
        r20.itemView.setTranslationX((float) (-((int) (((float) (i - i3)) - translationX))));
        r20.itemView.setTranslationY((float) (-((int) (((float) (i2 - i4)) - translationY))));
        r20.itemView.setAlpha(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        r122.A07.add(new AnonymousClass1Ek(r19, r20, i3, i4, i, i2));
        return true;
    }

    public boolean A0A(@NonNull AnonymousClass1Ah r3, @NonNull List<Object> list) {
        if (!(this instanceof AnonymousClass1Az) || r3.isInvalid()) {
            return true;
        }
        return false;
    }
}
