package X;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.ArrayList;

/* renamed from: X.0D6  reason: invalid class name */
public final class AnonymousClass0D6 {
    public static final AnonymousClass0DF A00 = new C03580ck();
    public static final AnonymousClass0DF A01;
    public static final int[] A02 = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    static {
        AnonymousClass0DF r0;
        try {
            r0 = (AnonymousClass0DF) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            r0 = null;
        }
        A01 = r0;
    }

    public static AnonymousClass0DF A00(AnonymousClass0MN r5, AnonymousClass0MN r6) {
        AnonymousClass0CM r0;
        Object obj;
        AnonymousClass0CM r2;
        ArrayList arrayList = new ArrayList();
        if (!(r5 == null || (r2 = r5.A0C) == null)) {
            Object obj2 = r2.A03;
            Object obj3 = AnonymousClass0MN.A0f;
            if (!(obj2 == obj3 || obj2 == null)) {
                arrayList.add(obj2);
            }
            Object obj4 = r2.A04;
            if (!(obj4 == obj3 || obj4 == null)) {
                arrayList.add(obj4);
            }
        }
        if (!(r6 == null || (r0 = r6.A0C) == null || (obj = r0.A02) == AnonymousClass0MN.A0f || obj == null)) {
            arrayList.add(obj);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        AnonymousClass0DF r3 = A00;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (!r3.A0D(arrayList.get(i))) {
                r3 = A01;
                if (r3 != null) {
                    int size2 = arrayList.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (r3.A0D(arrayList.get(i2))) {
                        }
                    }
                }
                throw new IllegalArgumentException("Invalid Transition types");
            } else {
                i++;
            }
        }
        return r3;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0DF;Ljava/lang/Object;LX/0MN;Ljava/util/ArrayList<Landroid/view/View;>;Landroid/view/View;)Ljava/util/ArrayList<Landroid/view/View;>; */
    public static ArrayList A01(AnonymousClass0DF r2, Object obj, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (!arrayList2.isEmpty()) {
            arrayList2.add(view);
            r2.A0A(obj, arrayList2);
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
    public static void A03(X.C03670cv r9, X.C01000Cx r10, android.util.SparseArray<X.AnonymousClass0D5> r11, boolean r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0D6.A03(X.0cv, X.0Cx, android.util.SparseArray, boolean, boolean):void");
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

    public static void A02(@NonNull Context context, @NonNull AnonymousClass0CX r43, ArrayList<C03670cv> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z, AnonymousClass0D4 r49) {
        ViewGroup viewGroup;
        Object obj;
        Object A012;
        AnonymousClass0CM r0;
        Object obj2;
        Object A013;
        AnonymousClass0CM r02;
        Object obj3;
        Object A022;
        AnonymousClass0CM r03;
        Object obj4;
        Object A014;
        AnonymousClass0CM r04;
        Object obj5;
        Object A015;
        AnonymousClass0CM r05;
        int i3;
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        SparseArray sparseArray = new SparseArray();
        for (int i4 = i; i4 < i2; i4++) {
            C03670cv r7 = arrayList.get(i4);
            if (!arrayList2.get(i4).booleanValue()) {
                int size = r7.A0A.size();
                for (int i5 = 0; i5 < size; i5++) {
                    A03(r7, r7.A0A.get(i5), sparseArray, false, z);
                }
            } else if (r7.A01.A04.A01()) {
                for (int size2 = r7.A0A.size() - 1; size2 >= 0; size2--) {
                    A03(r7, r7.A0A.get(size2), sparseArray, true, z);
                }
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size3 = sparseArray.size();
            for (int i6 = 0; i6 < size3; i6++) {
                int keyAt = sparseArray.keyAt(i6);
                C03960dj r3 = new C03960dj();
                for (int i7 = i2 - 1; i7 >= i; i7--) {
                    C03670cv r5 = arrayList.get(i7);
                    ArrayList<C01000Cx> arrayList5 = r5.A0A;
                    int size4 = arrayList5.size();
                    int i8 = 0;
                    while (true) {
                        if (i8 >= size4) {
                            break;
                        }
                        C01000Cx r4 = arrayList5.get(i8);
                        if (r4.A05 == null || (i3 = r4.A05.A03) == 0 || i3 != keyAt) {
                            i8++;
                        } else {
                            boolean booleanValue = arrayList2.get(i7).booleanValue();
                            if (r5.A0B != null) {
                                int size5 = r5.A0B.size();
                                if (booleanValue) {
                                    arrayList4 = r5.A0B;
                                    arrayList3 = r5.A0C;
                                } else {
                                    arrayList3 = r5.A0B;
                                    arrayList4 = r5.A0C;
                                }
                                for (int i9 = 0; i9 < size5; i9++) {
                                    String str = arrayList3.get(i9);
                                    String str2 = arrayList4.get(i9);
                                    Object remove = r3.remove(str2);
                                    if (remove != null) {
                                        r3.put(str, remove);
                                    } else {
                                        r3.put(str, str2);
                                    }
                                }
                            }
                        }
                    }
                }
                AnonymousClass0D5 r13 = (AnonymousClass0D5) sparseArray.valueAt(i6);
                if (r43.A01() && (viewGroup = (ViewGroup) r43.A00(keyAt)) != null) {
                    if (z) {
                        AnonymousClass0MN r8 = r13.A03;
                        AnonymousClass0MN r12 = r13.A02;
                        AnonymousClass0DF A002 = A00(r12, r8);
                        if (A002 != null) {
                            boolean z2 = r13.A05;
                            boolean z3 = r13.A04;
                            ArrayList<View> arrayList6 = new ArrayList<>();
                            ArrayList<View> arrayList7 = new ArrayList<>();
                            if (r8 == null) {
                                A014 = null;
                            } else {
                                if (!z2 || (r04 = r8.A0C) == null || (obj4 = r04.A02) == AnonymousClass0MN.A0f) {
                                    obj4 = null;
                                }
                                A014 = A002.A01(obj4);
                            }
                            if (r12 == null) {
                                A015 = null;
                            } else {
                                if (!z3 || (r05 = r12.A0C) == null || (obj5 = r05.A03) == AnonymousClass0MN.A0f) {
                                    obj5 = null;
                                }
                                A015 = A002.A01(obj5);
                            }
                            AnonymousClass0MN r06 = r13.A03;
                            if (r06 != null) {
                                r06.A04();
                            } else if (A014 != null || A015 != null) {
                                ArrayList<View> A016 = A01(A002, A015, arrayList7, view);
                                ArrayList<View> A017 = A01(A002, A014, arrayList6, view);
                                A04(A017, 4);
                                Object A03 = A002.A03(A015, A014, null);
                                if (!(r12 == null || A016 == null || (A016.size() <= 0 && arrayList7.size() <= 0))) {
                                    AnonymousClass091 r15 = new AnonymousClass091();
                                    r49.A6Z(r12, r15);
                                    A002.A05(r12, A03, r15, new RunnableC01020Cz(r49, r12, r15));
                                }
                                if (A03 != null) {
                                    if (r12 != null && A015 != null && r12.A0Q && r12.A0V && r12.A0W) {
                                        AnonymousClass0MN.A00(r12);
                                        A002.A08(A015, null, A016);
                                        View$OnAttachStateChangeListenerC00850Ad.A00(r12.A0B, new AnonymousClass0D0(A016));
                                    }
                                    ArrayList arrayList8 = new ArrayList();
                                    int size6 = arrayList6.size();
                                    for (int i10 = 0; i10 < size6; i10++) {
                                        View view2 = arrayList6.get(i10);
                                        arrayList8.add(view2.getTransitionName());
                                        view2.setTransitionName(null);
                                    }
                                    A002.A09(A03, A014, A017, A015, A016, null, arrayList6);
                                    A002.A04(viewGroup, A03);
                                    int size7 = arrayList6.size();
                                    ArrayList arrayList9 = new ArrayList();
                                    for (int i11 = 0; i11 < size7; i11++) {
                                        View view3 = arrayList7.get(i11);
                                        String transitionName = view3.getTransitionName();
                                        arrayList9.add(transitionName);
                                        if (transitionName != null) {
                                            view3.setTransitionName(null);
                                            String str3 = (String) r3.get(transitionName);
                                            int i12 = 0;
                                            while (true) {
                                                if (i12 >= size7) {
                                                    break;
                                                } else if (str3.equals(arrayList8.get(i12))) {
                                                    arrayList6.get(i12).setTransitionName(transitionName);
                                                    break;
                                                } else {
                                                    i12++;
                                                }
                                            }
                                        }
                                    }
                                    View$OnAttachStateChangeListenerC00850Ad.A00(viewGroup, new AnonymousClass0DC(A002, size7, arrayList6, arrayList8, arrayList7, arrayList9));
                                    A04(A017, 0);
                                    A002.A0C(null, arrayList7, arrayList6);
                                }
                            }
                        } else {
                            continue;
                        }
                    } else {
                        AnonymousClass0MN r10 = r13.A03;
                        AnonymousClass0MN r122 = r13.A02;
                        AnonymousClass0DF A003 = A00(r122, r10);
                        if (A003 != null) {
                            boolean z4 = r13.A05;
                            boolean z5 = r13.A04;
                            if (r10 == null) {
                                A012 = null;
                            } else {
                                if (!z4 || (r0 = r10.A0C) == null || (obj = r0.A02) == AnonymousClass0MN.A0f) {
                                    obj = null;
                                }
                                A012 = A003.A01(obj);
                            }
                            if (r122 == null) {
                                A013 = null;
                            } else {
                                if (!z5 || (r02 = r122.A0C) == null || (obj2 = r02.A03) == AnonymousClass0MN.A0f) {
                                    obj2 = null;
                                }
                                A013 = A003.A01(obj2);
                            }
                            ArrayList arrayList10 = new ArrayList();
                            ArrayList<View> arrayList11 = new ArrayList<>();
                            AnonymousClass0MN r07 = r13.A03;
                            AnonymousClass0MN r14 = r13.A02;
                            if (!(r07 == null || r14 == null)) {
                                boolean z6 = r13.A05;
                                if (r3.isEmpty()) {
                                    A022 = null;
                                } else {
                                    if (!z6 || (r03 = r14.A0C) == null || (obj3 = r03.A04) == AnonymousClass0MN.A0f) {
                                        obj3 = null;
                                    }
                                    A022 = A003.A02(A003.A01(obj3));
                                }
                                if (r3.isEmpty() || A022 == null) {
                                    r3.clear();
                                    if (!r3.isEmpty()) {
                                        throw null;
                                    } else if (A012 != null || A013 != null) {
                                        View$OnAttachStateChangeListenerC00850Ad.A00(viewGroup, new AnonymousClass0D3(A003, r3, null, r13, arrayList11, view, r07, r14, z6, arrayList10, A012, null));
                                    }
                                } else {
                                    AnonymousClass0MN r1 = r13.A02;
                                    new C03960dj();
                                    r1.A04();
                                }
                            }
                            if (A012 != null || A013 != null) {
                                ArrayList<View> A018 = A01(A003, A013, arrayList10, view);
                                if (A018 == null || A018.isEmpty()) {
                                    A013 = null;
                                }
                                A003.A06(A012, view);
                                Object A032 = A003.A03(A013, A012, null);
                                if (!(r122 == null || A018 == null || (A018.size() <= 0 && arrayList10.size() <= 0))) {
                                    AnonymousClass091 r142 = new AnonymousClass091();
                                    r49.A6Z(r122, r142);
                                    A003.A05(r122, A032, r142, new AnonymousClass0D1(r49, r122, r142));
                                }
                                if (A032 != null) {
                                    ArrayList<View> arrayList12 = new ArrayList<>();
                                    A003.A09(A032, A012, arrayList12, A013, A018, null, arrayList11);
                                    View$OnAttachStateChangeListenerC00850Ad.A00(viewGroup, new AnonymousClass0D2(A012, A003, view, r10, arrayList11, arrayList12, A018, A013));
                                    View$OnAttachStateChangeListenerC00850Ad.A00(viewGroup, new AnonymousClass0DD(A003, arrayList11, r3));
                                    A003.A04(viewGroup, A032);
                                    View$OnAttachStateChangeListenerC00850Ad.A00(viewGroup, new AnonymousClass0DE(A003, arrayList11, r3));
                                }
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
