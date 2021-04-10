package X;

import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: X.09x  reason: invalid class name */
public final class AnonymousClass09x {
    public static final AnonymousClass0A6 A00 = new C07360rz();
    public static final AnonymousClass0A6 A01;
    public static final int[] A02 = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    static {
        AnonymousClass0A6 r0;
        try {
            r0 = (AnonymousClass0A6) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            r0 = null;
        }
        A01 = r0;
    }

    public static View A00(C07490ss<String, View> r4, C004809w r5, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        AnonymousClass0sD r3 = r5.A01;
        if (obj == null || r4 == null || (arrayList = r3.A0B) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = arrayList.get(0);
        } else {
            str = r3.A0C.get(0);
        }
        return r4.get(str);
    }

    public static C07490ss<String, View> A01(AnonymousClass0A6 r4, C07490ss<String, String> r5, Object obj, C004809w r7) {
        ArrayList<String> arrayList;
        View view = r7.A03.mView;
        if (r5.isEmpty() || obj == null || view == null) {
            r5.clear();
            return null;
        }
        C07490ss<String, View> r3 = new C07490ss<>();
        r4.A03(r3, view);
        AnonymousClass0sD r1 = r7.A01;
        if (r7.A05) {
            arrayList = r1.A0B;
        } else {
            arrayList = r1.A0C;
        }
        if (arrayList != null) {
            AnonymousClass02n.A00(r3, arrayList);
            AnonymousClass02n.A00(r3, r5.values());
        }
        int size = r5.size();
        while (true) {
            size--;
            if (size < 0) {
                return r3;
            }
            if (!r3.containsKey(r5.A02[(size << 1) + 1])) {
                r5.A06(size);
            }
        }
    }

    public static AnonymousClass0A6 A03(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        AnonymousClass0A6 r3 = A00;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (!r3.A0K(arrayList.get(i))) {
                r3 = A01;
                if (r3 != null) {
                    int size2 = arrayList.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (r3.A0K(arrayList.get(i2))) {
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

    public static ArrayList<View> A04(AnonymousClass0A6 r2, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.mView;
        if (view2 != null) {
            r2.A02(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (!arrayList2.isEmpty()) {
            arrayList2.add(view);
            r2.A0H(obj, arrayList2);
        }
        return arrayList2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0065, code lost:
        if (r6.A02 == null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0098, code lost:
        if (r3.mPostponedAlpha >= 0.0f) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00a1, code lost:
        if (r3.mHidden == false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00b2, code lost:
        if (r3.mHidden != false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00b4, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00c4, code lost:
        if (r3.mHidden == false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00d5, code lost:
        if (r3.mAdded != false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00d7, code lost:
        r0 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:95:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.AnonymousClass0sD r9, X.C004009n r10, android.util.SparseArray<X.C004809w> r11, boolean r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 226
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09x.A06(X.0sD, X.09n, android.util.SparseArray, boolean, boolean):void");
    }

    public static void A07(ArrayList<View> arrayList, int i) {
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

    public static C07490ss<String, View> A02(AnonymousClass0A6 r3, C07490ss<String, String> r4, Object obj, C004809w r6) {
        ArrayList<String> arrayList;
        if (r4.isEmpty() || obj == null) {
            r4.clear();
            return null;
        }
        Fragment fragment = r6.A02;
        C07490ss<String, View> r2 = new C07490ss<>();
        r3.A03(r2, fragment.requireView());
        AnonymousClass0sD r1 = r6.A00;
        if (r6.A04) {
            arrayList = r1.A0C;
        } else {
            arrayList = r1.A0B;
        }
        if (arrayList != null) {
            AnonymousClass02n.A00(r2, arrayList);
        }
        AnonymousClass02n.A00(r4, r2.keySet());
        return r2;
    }

    public static void A08(ArrayList<View> arrayList, C07490ss<String, View> r4, Collection<String> collection) {
        int size = r4.size();
        while (true) {
            size--;
            if (size >= 0) {
                View view = (View) r4.A02[(size << 1) + 1];
                if (collection.contains(view.getTransitionName())) {
                    arrayList.add(view);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03c2, code lost:
        if (r13 != null) goto L_0x03c4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x024e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0473  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0165 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0165 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d2  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A05(@androidx.annotation.NonNull android.content.Context r43, @androidx.annotation.NonNull X.AnonymousClass09N r44, java.util.ArrayList<X.AnonymousClass0sD> r45, java.util.ArrayList<java.lang.Boolean> r46, int r47, int r48, boolean r49, X.AbstractC004709v r50) {
        /*
        // Method dump skipped, instructions count: 1214
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09x.A05(android.content.Context, X.09N, java.util.ArrayList, java.util.ArrayList, int, int, boolean, X.09v):void");
    }
}
