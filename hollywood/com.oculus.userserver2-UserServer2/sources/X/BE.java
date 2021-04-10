package X;

import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public final class BE {
    public static final BN A00 = new Th();
    public static final BN A01;
    public static final int[] A02 = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    static {
        BN bn;
        try {
            bn = (BN) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            bn = null;
        }
        A01 = bn;
    }

    public static BN A00(Fragment fragment, Fragment fragment2) {
        AX ax;
        Object obj;
        AX ax2;
        ArrayList arrayList = new ArrayList();
        if (!(fragment == null || (ax2 = fragment.A0C) == null)) {
            Object obj2 = ax2.A03;
            Object obj3 = Fragment.A0f;
            if (!(obj2 == obj3 || obj2 == null)) {
                arrayList.add(obj2);
            }
            Object obj4 = ax2.A04;
            if (!(obj4 == obj3 || obj4 == null)) {
                arrayList.add(obj4);
            }
        }
        if (!(fragment2 == null || (ax = fragment2.A0C) == null || (obj = ax.A02) == Fragment.A0f || obj == null)) {
            arrayList.add(obj);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        BN bn = A00;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (!(arrayList.get(i) instanceof Transition)) {
                bn = A01;
                if (bn != null) {
                    int size2 = arrayList.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (arrayList.get(i2) instanceof Transition) {
                        }
                    }
                }
                throw new IllegalArgumentException("Invalid Transition types");
            } else {
                i++;
            }
        }
        return bn;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/BN;Ljava/lang/Object;Landroidx/fragment/app/Fragment;Ljava/util/ArrayList<Landroid/view/View;>;Landroid/view/View;)Ljava/util/ArrayList<Landroid/view/View;>; */
    public static ArrayList A01(BN bn, Object obj, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (!arrayList2.isEmpty()) {
            arrayList2.add(view);
            bn.A01(obj, arrayList2);
        }
        return arrayList2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0065, code lost:
        if (r6.A02 == null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0083, code lost:
        if (r13 != false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x008d, code lost:
        if (r3.A0V == false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x009c, code lost:
        if (r3.A0V != false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x009e, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00aa, code lost:
        if (r3.A0V == false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00bb, code lost:
        if (r3.A0Q != false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00bd, code lost:
        r0 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(X.Ts r9, X.B5 r10, android.util.SparseArray<X.BD> r11, boolean r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: X.BE.A03(X.Ts, X.B5, android.util.SparseArray, boolean, boolean):void");
    }

    public static void A04(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            int size = arrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    arrayList.get(size).setVisibility(i);
                } else {
                    return;
                }
            }
        }
    }

    public static void A02(@NonNull Context context, @NonNull Ai ai, ArrayList<Ts> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z, BC bc) {
        ViewGroup viewGroup;
        Transition transition;
        Transition transition2;
        TransitionSet transitionSet;
        AX ax;
        Object obj;
        Transition clone;
        AX ax2;
        Object obj2;
        AX ax3;
        Object obj3;
        Transition transition3;
        Transition transition4;
        AX ax4;
        Object obj4;
        AX ax5;
        Object obj5;
        int i3;
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        SparseArray sparseArray = new SparseArray();
        for (int i4 = i; i4 < i2; i4++) {
            Ts ts = arrayList.get(i4);
            if (!arrayList2.get(i4).booleanValue()) {
                int size = ts.A0A.size();
                for (int i5 = 0; i5 < size; i5++) {
                    A03(ts, ts.A0A.get(i5), sparseArray, false, z);
                }
            } else if (ts.A01.A04.A01()) {
                for (int size2 = ts.A0A.size() - 1; size2 >= 0; size2--) {
                    A03(ts, ts.A0A.get(size2), sparseArray, true, z);
                }
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size3 = sparseArray.size();
            for (int i6 = 0; i6 < size3; i6++) {
                int keyAt = sparseArray.keyAt(i6);
                UB ub = new UB();
                for (int i7 = i2 - 1; i7 >= i; i7--) {
                    Ts ts2 = arrayList.get(i7);
                    ArrayList<B5> arrayList5 = ts2.A0A;
                    int size4 = arrayList5.size();
                    int i8 = 0;
                    while (true) {
                        if (i8 >= size4) {
                            break;
                        }
                        B5 b5 = arrayList5.get(i8);
                        if (b5.A05 == null || (i3 = b5.A05.A03) == 0 || i3 != keyAt) {
                            i8++;
                        } else {
                            boolean booleanValue = arrayList2.get(i7).booleanValue();
                            if (ts2.A0B != null) {
                                int size5 = ts2.A0B.size();
                                if (booleanValue) {
                                    arrayList4 = ts2.A0B;
                                    arrayList3 = ts2.A0C;
                                } else {
                                    arrayList3 = ts2.A0B;
                                    arrayList4 = ts2.A0C;
                                }
                                for (int i9 = 0; i9 < size5; i9++) {
                                    String str = arrayList3.get(i9);
                                    String str2 = arrayList4.get(i9);
                                    Object remove = ub.remove(str2);
                                    if (remove != null) {
                                        ub.put(str, remove);
                                    } else {
                                        ub.put(str, str2);
                                    }
                                }
                            }
                        }
                    }
                }
                BD bd = (BD) sparseArray.valueAt(i6);
                if (ai.A01() && (viewGroup = (ViewGroup) ai.A00(keyAt)) != null) {
                    if (z) {
                        Fragment fragment = bd.A03;
                        Fragment fragment2 = bd.A02;
                        BN A002 = A00(fragment2, fragment);
                        if (A002 != null) {
                            boolean z2 = bd.A05;
                            boolean z3 = bd.A04;
                            ArrayList arrayList6 = new ArrayList();
                            ArrayList arrayList7 = new ArrayList();
                            if (fragment == null || !z2 || (ax5 = fragment.A0C) == null || (obj5 = ax5.A02) == Fragment.A0f || obj5 == null) {
                                transition3 = null;
                            } else {
                                transition3 = ((Transition) obj5).clone();
                            }
                            if (fragment2 == null || !z3 || (ax4 = fragment2.A0C) == null || (obj4 = ax4.A03) == Fragment.A0f || obj4 == null) {
                                transition4 = null;
                            } else {
                                transition4 = ((Transition) obj4).clone();
                            }
                            Fragment fragment3 = bd.A03;
                            if (fragment3 != null) {
                                fragment3.A04();
                            } else if (transition3 != null || transition4 != null) {
                                ArrayList A012 = A01(A002, transition4, arrayList7, view);
                                ArrayList A013 = A01(A002, transition3, arrayList6, view);
                                A04(A013, 4);
                                TransitionSet transitionSet2 = new TransitionSet();
                                if (transition4 != null) {
                                    transitionSet2.addTransition(transition4);
                                }
                                if (transition3 != null) {
                                    transitionSet2.addTransition(transition3);
                                }
                                if (fragment2 != null) {
                                    if (A012 != null && (A012.size() > 0 || arrayList7.size() > 0)) {
                                        AnonymousClass6N r0 = new AnonymousClass6N();
                                        bc.A2h(fragment2, r0);
                                        B7 b7 = new B7(bc, fragment2, r0);
                                        if (!(A002 instanceof Th)) {
                                            b7.run();
                                        } else {
                                            transitionSet2.addListener((Transition.TransitionListener) new BI((Th) A002, b7));
                                        }
                                    }
                                    if (transition4 != null && fragment2.A0Q && fragment2.A0V && fragment2.A0W) {
                                        Fragment.A00(fragment2);
                                        transition4.addListener(new BG((Th) A002, null, A012));
                                        AnonymousClass85.A00(fragment2.A0B, new B8(A012));
                                    }
                                }
                                ArrayList arrayList8 = new ArrayList();
                                int size6 = arrayList6.size();
                                for (int i10 = 0; i10 < size6; i10++) {
                                    View view2 = (View) arrayList6.get(i10);
                                    arrayList8.add(view2.getTransitionName());
                                    view2.setTransitionName(null);
                                }
                                TransitionSet transitionSet3 = transitionSet2;
                                transitionSet3.addListener((Transition.TransitionListener) new BH((Th) A002, transition3, A013, transition4, A012, null, arrayList6));
                                TransitionManager.beginDelayedTransition(viewGroup, transitionSet3);
                                int size7 = arrayList6.size();
                                ArrayList arrayList9 = new ArrayList();
                                for (int i11 = 0; i11 < size7; i11++) {
                                    View view3 = (View) arrayList7.get(i11);
                                    String transitionName = view3.getTransitionName();
                                    arrayList9.add(transitionName);
                                    if (transitionName != null) {
                                        view3.setTransitionName(null);
                                        String str3 = (String) ub.get(transitionName);
                                        int i12 = 0;
                                        while (true) {
                                            if (i12 >= size7) {
                                                break;
                                            } else if (str3.equals(arrayList8.get(i12))) {
                                                ((View) arrayList6.get(i12)).setTransitionName(transitionName);
                                                break;
                                            } else {
                                                i12++;
                                            }
                                        }
                                    }
                                }
                                AnonymousClass85.A00(viewGroup, new BK(A002, size7, arrayList6, arrayList8, arrayList7, arrayList9));
                                A04(A013, 0);
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Fragment fragment4 = bd.A03;
                        Fragment fragment5 = bd.A02;
                        BN A003 = A00(fragment5, fragment4);
                        if (A003 != null) {
                            boolean z4 = bd.A05;
                            boolean z5 = bd.A04;
                            if (fragment4 == null || !z4 || (ax3 = fragment4.A0C) == null || (obj3 = ax3.A02) == Fragment.A0f || obj3 == null) {
                                transition = null;
                            } else {
                                transition = ((Transition) obj3).clone();
                            }
                            if (fragment5 == null || !z5 || (ax2 = fragment5.A0C) == null || (obj2 = ax2.A03) == Fragment.A0f || obj2 == null) {
                                transition2 = null;
                            } else {
                                transition2 = ((Transition) obj2).clone();
                            }
                            ArrayList arrayList10 = new ArrayList();
                            ArrayList arrayList11 = new ArrayList();
                            Fragment fragment6 = bd.A03;
                            Fragment fragment7 = bd.A02;
                            if (!(fragment6 == null || fragment7 == null)) {
                                boolean z6 = bd.A05;
                                if (ub.isEmpty() || !z6 || (ax = fragment7.A0C) == null || (obj = ax.A04) == Fragment.A0f || obj == null || (clone = ((Transition) obj).clone()) == null) {
                                    transitionSet = null;
                                } else {
                                    transitionSet = new TransitionSet();
                                    transitionSet.addTransition(clone);
                                }
                                if (ub.isEmpty() || transitionSet == null) {
                                    ub.clear();
                                    if (!ub.isEmpty()) {
                                        throw null;
                                    } else if (transition != null || transition2 != null) {
                                        AnonymousClass85.A00(viewGroup, new BB(A003, ub, null, bd, arrayList11, view, fragment6, fragment7, z6, arrayList10, transition, null));
                                    }
                                } else {
                                    Fragment fragment8 = bd.A02;
                                    new UB();
                                    fragment8.A04();
                                }
                            }
                            if (transition != null || transition2 != null) {
                                ArrayList A014 = A01(A003, transition2, arrayList10, view);
                                if (A014 == null || A014.isEmpty()) {
                                    transition2 = null;
                                }
                                if (transition != null) {
                                    transition.addTarget(view);
                                }
                                TransitionSet transitionSet4 = new TransitionSet();
                                if (transition2 != null) {
                                    transitionSet4.addTransition(transition2);
                                }
                                if (transition != null) {
                                    transitionSet4.addTransition(transition);
                                }
                                if (!(fragment5 == null || A014 == null || (A014.size() <= 0 && arrayList10.size() <= 0))) {
                                    AnonymousClass6N r02 = new AnonymousClass6N();
                                    bc.A2h(fragment5, r02);
                                    B9 b9 = new B9(bc, fragment5, r02);
                                    if (!(A003 instanceof Th)) {
                                        b9.run();
                                    } else {
                                        transitionSet4.addListener((Transition.TransitionListener) new BI((Th) A003, b9));
                                    }
                                }
                                ArrayList arrayList12 = new ArrayList();
                                TransitionSet transitionSet5 = transitionSet4;
                                transitionSet5.addListener((Transition.TransitionListener) new BH((Th) A003, transition, arrayList12, transition2, A014, null, arrayList11));
                                AnonymousClass85.A00(viewGroup, new BA(transition, A003, view, fragment4, arrayList11, arrayList12, A014, transition2));
                                AnonymousClass85.A00(viewGroup, new BL(A003, arrayList11, ub));
                                TransitionManager.beginDelayedTransition(viewGroup, transitionSet5);
                                AnonymousClass85.A00(viewGroup, new BM(A003, arrayList11, ub));
                            }
                        } else {
                            continue;
                        }
                    }
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
    }
}
