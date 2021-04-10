package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: lT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3552lT {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10346a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    public static final AbstractC5257vT b = new C4577rT();
    public static final AbstractC5257vT c;

    static {
        AbstractC5257vT vTVar;
        try {
            vTVar = (AbstractC5257vT) Class.forName("CT").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            vTVar = null;
        }
        c = vTVar;
    }

    public static void a(ArrayList arrayList, C4931ta taVar, Collection collection) {
        int i = taVar.L;
        while (true) {
            i--;
            if (i >= 0) {
                View view = (View) taVar.k(i);
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                if (collection.contains(view.getTransitionName())) {
                    arrayList.add(view);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0039, code lost:
        if (r0.P != false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0077, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0089, code lost:
        if (r0.d0 == false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x008b, code lost:
        r9 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00e7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:99:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(defpackage.C0317Fe r8, defpackage.C2186dT r9, android.util.SparseArray r10, boolean r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC3552lT.b(Fe, dT, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(AbstractComponentCallbacksC3550lS lSVar, AbstractComponentCallbacksC3550lS lSVar2, boolean z, C4931ta taVar, boolean z2) {
        if (z) {
            lSVar2.z();
        } else {
            lSVar.z();
        }
    }

    public static boolean d(AbstractC5257vT vTVar, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!vTVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static C4931ta e(AbstractC5257vT vTVar, C4931ta taVar, Object obj, C3381kT kTVar) {
        ArrayList arrayList;
        AbstractComponentCallbacksC3550lS lSVar = kTVar.f10280a;
        View view = lSVar.k0;
        if (taVar.isEmpty() || obj == null || view == null) {
            taVar.clear();
            return null;
        }
        C4931ta taVar2 = new C4931ta();
        vTVar.i(taVar2, view);
        C0317Fe fe = kTVar.c;
        if (kTVar.b) {
            lSVar.C();
            arrayList = fe.n;
        } else {
            lSVar.z();
            arrayList = fe.o;
        }
        if (arrayList != null) {
            taVar2.m(arrayList);
            taVar2.m(taVar.values());
        }
        int i = taVar.L;
        while (true) {
            i--;
            if (i < 0) {
                return taVar2;
            }
            if (!taVar2.containsKey((String) taVar.k(i))) {
                taVar.i(i);
            }
        }
    }

    public static C4931ta f(AbstractC5257vT vTVar, C4931ta taVar, Object obj, C3381kT kTVar) {
        ArrayList arrayList;
        if (taVar.isEmpty() || obj == null) {
            taVar.clear();
            return null;
        }
        AbstractComponentCallbacksC3550lS lSVar = kTVar.d;
        C4931ta taVar2 = new C4931ta();
        vTVar.i(taVar2, lSVar.Q0());
        C0317Fe fe = kTVar.f;
        if (kTVar.e) {
            lSVar.z();
            arrayList = fe.o;
        } else {
            lSVar.C();
            arrayList = fe.n;
        }
        if (arrayList != null) {
            taVar2.m(arrayList);
        }
        taVar.m(taVar2.keySet());
        return taVar2;
    }

    public static AbstractC5257vT g(AbstractComponentCallbacksC3550lS lSVar, AbstractComponentCallbacksC3550lS lSVar2) {
        ArrayList arrayList = new ArrayList();
        if (lSVar != null) {
            lSVar.B();
            Object J2 = lSVar.J();
            if (J2 != null) {
                arrayList.add(J2);
            }
            Object M = lSVar.M();
            if (M != null) {
                arrayList.add(M);
            }
        }
        if (lSVar2 != null) {
            lSVar2.y();
            Object H = lSVar2.H();
            if (H != null) {
                arrayList.add(H);
            }
            lSVar2.K();
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        AbstractC5257vT vTVar = b;
        if (d(vTVar, arrayList)) {
            return vTVar;
        }
        AbstractC5257vT vTVar2 = c;
        if (vTVar2 != null && d(vTVar2, arrayList)) {
            return vTVar2;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList h(AbstractC5257vT vTVar, Object obj, AbstractComponentCallbacksC3550lS lSVar, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        View view2 = lSVar.k0;
        if (view2 != null) {
            vTVar.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        vTVar.b(obj, arrayList2);
        return arrayList2;
    }

    public static Object i(AbstractC5257vT vTVar, AbstractComponentCallbacksC3550lS lSVar, boolean z) {
        Object obj = null;
        if (lSVar == null) {
            return null;
        }
        if (z) {
            obj = lSVar.H();
        } else {
            lSVar.y();
        }
        return vTVar.g(obj);
    }

    public static Object j(AbstractC5257vT vTVar, AbstractComponentCallbacksC3550lS lSVar, boolean z) {
        Object obj = null;
        if (lSVar == null) {
            return null;
        }
        if (z) {
            obj = lSVar.J();
        } else {
            lSVar.B();
        }
        return vTVar.g(obj);
    }

    public static View k(C4931ta taVar, C3381kT kTVar, Object obj, boolean z) {
        ArrayList arrayList;
        String str;
        C0317Fe fe = kTVar.c;
        if (obj == null || taVar == null || (arrayList = fe.n) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = (String) fe.n.get(0);
        } else {
            str = (String) fe.o.get(0);
        }
        return (View) taVar.get(str);
    }

    public static Object l(AbstractC5257vT vTVar, AbstractComponentCallbacksC3550lS lSVar, AbstractComponentCallbacksC3550lS lSVar2, boolean z) {
        Object obj;
        if (z) {
            obj = lSVar2.M();
        } else {
            lSVar.K();
            obj = null;
        }
        return vTVar.v(vTVar.g(obj));
    }

    public static void m(AbstractC5257vT vTVar, Object obj, Object obj2, C4931ta taVar, boolean z, C0317Fe fe) {
        String str;
        ArrayList arrayList = fe.n;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (z) {
                str = (String) fe.o.get(0);
            } else {
                str = (String) fe.n.get(0);
            }
            View view = (View) taVar.get(str);
            vTVar.r(obj, view);
            if (obj2 != null) {
                vTVar.r(obj2, view);
            }
        }
    }

    public static void n(ArrayList arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((View) arrayList.get(size)).setVisibility(i);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:171:0x0443  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0481 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x023d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void o(defpackage.KS r38, java.util.ArrayList r39, java.util.ArrayList r40, int r41, int r42, boolean r43, defpackage.DS r44) {
        /*
        // Method dump skipped, instructions count: 1175
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC3552lT.o(KS, java.util.ArrayList, java.util.ArrayList, int, int, boolean, DS):void");
    }
}
