package X;

import android.content.res.Configuration;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedDispatcher$LifecycleOnBackPressedCancellable;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import com.oculus.socialplatform.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.09b  reason: invalid class name */
public abstract class AnonymousClass09b {
    public int A00 = -1;
    public AnonymousClass01Q A01;
    public Fragment A02;
    @Nullable
    public Fragment A03;
    public AnonymousClass09P A04;
    public AbstractC05340vL<?> A05;
    public C05280vB A06;
    public Runnable A07 = new AnonymousClass09W(this);
    public ArrayList<AnonymousClass0vO> A08;
    public ArrayList<Boolean> A09;
    public ArrayList<AnonymousClass0vO> A0A;
    public ConcurrentHashMap<Fragment, HashSet<AnonymousClass05j>> A0B = new ConcurrentHashMap<>();
    public boolean A0C;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public boolean A0H;
    public AnonymousClass09S A0I = new C05310vF(this);
    public ArrayList<Fragment> A0J;
    public ArrayList<Fragment> A0K;
    public final AnonymousClass01P A0L = new C05330vK(this);
    public final AnonymousClass09T A0M = new AnonymousClass09T(this);
    public final AnonymousClass09V A0N = new AnonymousClass09V(this);
    public final AnonymousClass09j A0O = new AnonymousClass09j();
    public final AbstractC004309w A0P = new C05320vI(this);
    public final ArrayList<AnonymousClass09a> A0Q = new ArrayList<>();
    public final AtomicInteger A0R = new AtomicInteger();

    public static void A02(AnonymousClass09b r1) {
        r1.A0D = false;
        r1.A09.clear();
        r1.A0A.clear();
    }

    /* JADX INFO: finally extract failed */
    public static void A05(AnonymousClass09b r6, int i) {
        try {
            r6.A0D = true;
            AnonymousClass09j r5 = r6.A0O;
            Iterator<Fragment> it = r5.A00.iterator();
            while (it.hasNext()) {
                AnonymousClass09i r0 = r5.A01.get(it.next().A0P);
                if (r0 != null) {
                    r0.A00 = i;
                }
            }
            for (AnonymousClass09i r02 : r5.A01.values()) {
                if (r02 != null) {
                    r02.A00 = i;
                }
            }
            r6.A0N(i, false);
            r6.A0D = false;
            r6.A0f(true);
        } catch (Throwable th) {
            r6.A0D = false;
            throw th;
        }
    }

    public static final boolean A0D(AnonymousClass09b r7) {
        boolean z;
        int size;
        r7.A0f(false);
        A0A(r7, true);
        Fragment fragment = r7.A03;
        if (fragment != null && A0D(fragment.A03())) {
            return true;
        }
        ArrayList<AnonymousClass0vO> arrayList = r7.A0A;
        ArrayList<Boolean> arrayList2 = r7.A09;
        ArrayList<AnonymousClass0vO> arrayList3 = r7.A08;
        if (arrayList3 == null || (size = arrayList3.size() - 1) < 0) {
            z = false;
        } else {
            arrayList.add(arrayList3.remove(size));
            arrayList2.add(true);
            z = true;
            r7.A0D = true;
            try {
                A09(r7, arrayList, arrayList2);
            } finally {
                A02(r7);
            }
        }
        A04(r7);
        if (r7.A0E) {
            r7.A0E = false;
            A03(r7);
        }
        r7.A0O.A01.values().removeAll(Collections.singleton(null));
        return z;
    }

    private final boolean A0k(@Nullable Fragment fragment) {
        if (fragment != null) {
            AnonymousClass09b r1 = fragment.A0H;
            if (!fragment.equals(r1.A03) || !A0k(r1.A02)) {
                return false;
            }
        }
        return true;
    }

    public final void A0K() {
        this.A0C = true;
        A0f(true);
        A00();
        A05(this, -1);
        this.A05 = null;
        this.A04 = null;
        this.A02 = null;
        if (this.A01 != null) {
            Iterator<AnonymousClass01M> it = this.A0L.A00.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.A01 = null;
        }
    }

    public final void A0M() {
        this.A0G = false;
        this.A0H = false;
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.A0G.A0M();
            }
        }
    }

    private void A00() {
        int i;
        if (!this.A0B.isEmpty()) {
            for (Fragment fragment : this.A0B.keySet()) {
                A01(fragment);
                AnonymousClass09E r0 = fragment.A0C;
                if (r0 == null) {
                    i = 0;
                } else {
                    i = r0.A01;
                }
                A0Y(fragment, i);
            }
        }
    }

    private void A01(@NonNull Fragment fragment) {
        HashSet<AnonymousClass05j> hashSet = this.A0B.get(fragment);
        if (hashSet != null) {
            Iterator<AnonymousClass05j> it = hashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass05j next = it.next();
                synchronized (next) {
                    if (!next.A00) {
                        next.A00 = true;
                        synchronized (next) {
                            next.notifyAll();
                        }
                    }
                }
            }
            hashSet.clear();
            A06(this, fragment);
            this.A0B.remove(fragment);
        }
    }

    public static void A03(AnonymousClass09b r3) {
        for (Fragment fragment : r3.A0O.A00()) {
            if (fragment != null && fragment.A0S) {
                if (r3.A0D) {
                    r3.A0E = true;
                } else {
                    fragment.A0S = false;
                    r3.A0Y(fragment, r3.A00);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        if (r0 == null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0.size() <= 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r3.A0k(r3.A02) == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r1.A01 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        r1 = r3.A0L;
        r0 = r3.A08;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A04(X.AnonymousClass09b r3) {
        /*
            java.util.ArrayList<X.09a> r1 = r3.A0Q
            monitor-enter(r1)
            boolean r0 = r1.isEmpty()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r0 != 0) goto L_0x0010
            X.01P r0 = r3.A0L     // Catch:{ all -> 0x002a }
            r0.A01 = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x0010:
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            X.01P r1 = r3.A0L
            java.util.ArrayList<X.0vO> r0 = r3.A08
            if (r0 == 0) goto L_0x0028
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0028
            androidx.fragment.app.Fragment r0 = r3.A02
            boolean r0 = r3.A0k(r0)
            if (r0 == 0) goto L_0x0028
        L_0x0025:
            r1.A01 = r2
            return
        L_0x0028:
            r2 = 0
            goto L_0x0025
        L_0x002a:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09b.A04(X.09b):void");
    }

    public static void A06(@NonNull AnonymousClass09b r4, Fragment fragment) {
        A05(fragment.A0G, 1);
        fragment.A05 = 1;
        fragment.A0R = false;
        fragment.A0R = true;
        new AnonymousClass0ul(fragment, fragment.getViewModelStore()).A00();
        fragment.A0a = false;
        r4.A0N.A06(fragment, false);
        fragment.A0B = null;
        fragment.A0I = null;
        AnonymousClass0uu<AnonymousClass0AS> r1 = fragment.A00;
        AnonymousClass0AY.A01("setValue");
        r1.A01++;
        r1.A08 = null;
        r1.A03(null);
        fragment.A0X = false;
    }

    public static void A07(@Nullable AnonymousClass09b r1, Fragment fragment) {
        if (fragment != null && fragment.equals(r1.A0G(fragment.A0P))) {
            boolean A0k = fragment.A0H.A0k(fragment);
            Boolean bool = fragment.A0M;
            if (bool == null || bool.booleanValue() != A0k) {
                fragment.A0M = Boolean.valueOf(A0k);
                AnonymousClass09b r12 = fragment.A0G;
                A04(r12);
                A07(r12, r12.A03);
            }
        }
    }

    public static void A08(@NonNull AnonymousClass09b r1, Fragment fragment) {
        ViewGroup viewGroup;
        int i;
        if (fragment.A03 > 0 && r1.A04.A01()) {
            View A002 = r1.A04.A00(fragment.A03);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null) {
                if (viewGroup.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    viewGroup.setTag(R.id.visible_removing_fragment_view_tag, fragment);
                }
                Fragment fragment2 = (Fragment) viewGroup.getTag(R.id.visible_removing_fragment_view_tag);
                AnonymousClass09E r0 = fragment.A0C;
                if (r0 == null) {
                    i = 0;
                } else {
                    i = r0.A00;
                }
                fragment2.A08(i);
            }
        }
    }

    public static void A0A(AnonymousClass09b r2, boolean z) {
        if (r2.A0D) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (r2.A05 == null) {
            if (r2.A0C) {
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            throw new IllegalStateException("FragmentManager has not been attached to a host.");
        } else if (Looper.myLooper() != r2.A05.A02.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else if (z || (!r2.A0G && !r2.A0H)) {
            if (r2.A0A == null) {
                r2.A0A = new ArrayList<>();
                r2.A09 = new ArrayList<>();
            }
            r2.A0D = true;
            r2.A0D = false;
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void A0B(@NonNull ArrayList<AnonymousClass0vO> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i, int i2) {
        AnonymousClass09b r1;
        int i3 = i;
        boolean z = arrayList.get(i3).A0E;
        ArrayList<Fragment> arrayList3 = this.A0K;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            this.A0K = arrayList3;
        } else {
            arrayList3.clear();
        }
        AnonymousClass09j r0 = this.A0O;
        arrayList3.addAll(r0.A01());
        Fragment fragment = this.A03;
        boolean z2 = false;
        for (int i4 = i3; i4 < i2; i4++) {
            AnonymousClass0vO r6 = arrayList.get(i4);
            if (!arrayList2.get(i4).booleanValue()) {
                ArrayList<Fragment> arrayList4 = this.A0K;
                int i5 = 0;
                while (true) {
                    ArrayList<C003709p> arrayList5 = r6.A0A;
                    if (i5 < arrayList5.size()) {
                        C003709p r13 = arrayList5.get(i5);
                        int i6 = r13.A00;
                        if (i6 != 1) {
                            if (i6 == 2) {
                                Fragment fragment2 = r13.A05;
                                int i7 = fragment2.A03;
                                boolean z3 = false;
                                for (int size = arrayList4.size() - 1; size >= 0; size--) {
                                    Fragment fragment3 = arrayList4.get(size);
                                    if (fragment3.A03 == i7) {
                                        if (fragment3 == fragment2) {
                                            z3 = true;
                                        } else {
                                            if (fragment3 == fragment) {
                                                r6.A0A.add(i5, new C003709p(9, fragment3));
                                                i5++;
                                                fragment = null;
                                            }
                                            C003709p r15 = new C003709p(3, fragment3);
                                            r15.A01 = r13.A01;
                                            r15.A03 = r13.A03;
                                            r15.A02 = r13.A02;
                                            r15.A04 = r13.A04;
                                            r6.A0A.add(i5, r15);
                                            arrayList4.remove(fragment3);
                                            i5++;
                                        }
                                    }
                                }
                                if (z3) {
                                    r6.A0A.remove(i5);
                                    i5--;
                                } else {
                                    r13.A00 = 1;
                                    arrayList4.add(fragment2);
                                }
                            } else if (i6 == 3 || i6 == 6) {
                                arrayList4.remove(r13.A05);
                                if (r13.A05 == fragment) {
                                    r6.A0A.add(i5, new C003709p(9, r13.A05));
                                    i5++;
                                    fragment = null;
                                }
                            } else if (i6 != 7) {
                                if (i6 == 8) {
                                    arrayList5.add(i5, new C003709p(9, fragment));
                                    i5++;
                                    fragment = r13.A05;
                                }
                            }
                            i5++;
                        }
                        arrayList4.add(r13.A05);
                        i5++;
                    }
                }
            } else {
                ArrayList<Fragment> arrayList6 = this.A0K;
                for (int size2 = r6.A0A.size() - 1; size2 >= 0; size2--) {
                    C003709p r2 = r6.A0A.get(size2);
                    int i8 = r2.A00;
                    if (i8 != 1) {
                        if (i8 != 3) {
                            switch (i8) {
                                case 8:
                                    fragment = null;
                                    break;
                                case 9:
                                    fragment = r2.A05;
                                    break;
                                case 10:
                                    r2.A06 = r2.A07;
                                    break;
                            }
                        }
                        arrayList6.add(r2.A05);
                    }
                    arrayList6.remove(r2.A05);
                }
            }
            if (!z2) {
                z2 = false;
                if (!r6.A0D) {
                }
            }
            z2 = true;
        }
        this.A0K.clear();
        if (!z && this.A00 >= 1) {
            AnonymousClass09y.A02(this.A05.A01, this.A04, arrayList, arrayList2, i3, i2, false, this.A0P);
        }
        for (int i9 = i3; i9 < i2; i9++) {
            AnonymousClass0vO r132 = arrayList.get(i9);
            boolean z4 = true;
            if (arrayList2.get(i9).booleanValue()) {
                r132.A03(-1);
                if (i9 != i2 - 1) {
                    z4 = false;
                }
                for (int size3 = r132.A0A.size() - 1; size3 >= 0; size3--) {
                    C003709p r12 = r132.A0A.get(size3);
                    Fragment fragment4 = r12.A05;
                    if (fragment4 != null) {
                        int i10 = r132.A06;
                        char c = 8194;
                        if (i10 != 4097) {
                            if (i10 != 4099) {
                                c = 4097;
                                if (i10 != 8194) {
                                    c = 0;
                                }
                            } else {
                                c = 4099;
                            }
                        }
                        if (!(fragment4.A0C == null && c == 0)) {
                            Fragment.A00(fragment4);
                        }
                    }
                    int i11 = r12.A00;
                    switch (i11) {
                        case 1:
                            fragment4.A08(r12.A04);
                            AnonymousClass09b r02 = r132.A02;
                            r02.A0a(fragment4, true);
                            r02.A0W(fragment4);
                            break;
                        case 2:
                        default:
                            throw new IllegalArgumentException(AnonymousClass006.A03("Unknown cmd: ", i11));
                        case 3:
                            fragment4.A08(r12.A03);
                            r132.A02.A0R(fragment4);
                            break;
                        case 4:
                            fragment4.A08(r12.A03);
                            if (fragment4.A0V) {
                                fragment4.A0V = false;
                                fragment4.A0W = !fragment4.A0W;
                                break;
                            }
                            break;
                        case 5:
                            fragment4.A08(r12.A04);
                            AnonymousClass09b r14 = r132.A02;
                            r14.A0a(fragment4, true);
                            if (!fragment4.A0V) {
                                fragment4.A0V = true;
                                fragment4.A0W = !fragment4.A0W;
                                A08(r14, fragment4);
                                break;
                            }
                            break;
                        case 6:
                            fragment4.A08(r12.A03);
                            r132.A02.A0S(fragment4);
                            break;
                        case 7:
                            fragment4.A08(r12.A04);
                            AnonymousClass09b r03 = r132.A02;
                            r03.A0a(fragment4, true);
                            r03.A0T(fragment4);
                            break;
                        case 8:
                            r132.A02.A0X(null);
                            break;
                        case 9:
                            r132.A02.A0X(fragment4);
                            break;
                        case 10:
                            r132.A02.A0Z(fragment4, r12.A07);
                            break;
                    }
                    if (!(r132.A0E || r12.A00 == 3 || fragment4 == null)) {
                        r132.A02.A0V(fragment4);
                    }
                }
                if (!r132.A0E) {
                    if (!z4) {
                    }
                }
            } else {
                r132.A03(1);
                int size4 = r132.A0A.size();
                for (int i12 = 0; i12 < size4; i12++) {
                    C003709p r152 = r132.A0A.get(i12);
                    Fragment fragment5 = r152.A05;
                    if (fragment5 != null) {
                        int i13 = r132.A06;
                        if (!(fragment5.A0C == null && i13 == 0)) {
                            Fragment.A00(fragment5);
                        }
                    }
                    int i14 = r152.A00;
                    switch (i14) {
                        case 1:
                            fragment5.A08(r152.A01);
                            r1 = r132.A02;
                            r1.A0a(fragment5, false);
                            r1.A0R(fragment5);
                            break;
                        case 2:
                        default:
                            throw new IllegalArgumentException(AnonymousClass006.A03("Unknown cmd: ", i14));
                        case 3:
                            fragment5.A08(r152.A02);
                            r1 = r132.A02;
                            r1.A0W(fragment5);
                            break;
                        case 4:
                            fragment5.A08(r152.A02);
                            r1 = r132.A02;
                            if (!fragment5.A0V) {
                                fragment5.A0V = true;
                                fragment5.A0W = !fragment5.A0W;
                                A08(r1, fragment5);
                                break;
                            }
                            break;
                        case 5:
                            fragment5.A08(r152.A01);
                            r1 = r132.A02;
                            r1.A0a(fragment5, false);
                            if (fragment5.A0V) {
                                fragment5.A0V = false;
                                fragment5.A0W = !fragment5.A0W;
                                break;
                            }
                            break;
                        case 6:
                            fragment5.A08(r152.A02);
                            r1 = r132.A02;
                            r1.A0T(fragment5);
                            break;
                        case 7:
                            fragment5.A08(r152.A01);
                            r1 = r132.A02;
                            r1.A0a(fragment5, false);
                            r1.A0S(fragment5);
                            break;
                        case 8:
                            r1 = r132.A02;
                            r1.A0X(fragment5);
                            break;
                        case 9:
                            r1 = r132.A02;
                            r1.A0X(null);
                            break;
                        case 10:
                            r1 = r132.A02;
                            r1.A0Z(fragment5, r152.A06);
                            break;
                    }
                    if (!(r132.A0E || r152.A00 == 1 || fragment5 == null)) {
                        r1.A0V(fragment5);
                    }
                }
                if (r132.A0E) {
                }
            }
            AnonymousClass09b r16 = r132.A02;
            r16.A0N(r16.A00, true);
        }
        if (z) {
            AnonymousClass02j r62 = new AnonymousClass02j();
            int i15 = this.A00;
            if (i15 >= 1) {
                int min = Math.min(i15, 3);
                for (Fragment fragment6 : r0.A01()) {
                    if (fragment6.A05 < min) {
                        A0Y(fragment6, min);
                    }
                }
            }
            for (int i16 = i2 - 1; i16 >= i3; i16--) {
                AnonymousClass0vO r5 = arrayList.get(i16);
                arrayList2.get(i16);
                int i17 = 0;
                while (true) {
                    ArrayList<C003709p> arrayList7 = r5.A0A;
                    if (i17 < arrayList7.size()) {
                        arrayList7.get(i17);
                        i17++;
                    }
                }
            }
            int size5 = r62.size();
            for (int i18 = 0; i18 < size5; i18++) {
                Fragment fragment7 = (Fragment) r62.A03[i18];
                if (!fragment7.A0Q) {
                    fragment7.A07();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else {
            z = false;
        }
        if (i2 != i3 && z) {
            if (this.A00 >= 1) {
                AnonymousClass09y.A02(this.A05.A01, this.A04, arrayList, arrayList2, i3, i2, true, this.A0P);
            }
            A0N(this.A00, true);
        }
        while (i3 < i2) {
            AnonymousClass0vO r17 = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue() && r17.A00 >= 0) {
                r17.A00 = -1;
            }
            i3++;
        }
    }

    public static boolean A0C(@NonNull Fragment fragment) {
        for (Fragment fragment2 : fragment.A0G.A0O.A00()) {
            if (fragment2 != null && A0C(fragment2)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private final Fragment A0G(@NonNull String str) {
        AnonymousClass09i r0 = this.A0O.A01.get(str);
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    private final void A0N(int i, boolean z) {
        AbstractC05340vL<?> r2;
        if (this.A05 == null && i != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.A00) {
            this.A00 = i;
            AnonymousClass09j r22 = this.A0O;
            for (Fragment fragment : r22.A01()) {
                A0V(fragment);
            }
            for (Fragment fragment2 : r22.A00()) {
                if (fragment2 != null) {
                    A0V(fragment2);
                }
            }
            A03(this);
            if (this.A0F && (r2 = this.A05) != null && this.A00 == 4) {
                r2.A04();
                this.A0F = false;
            }
        }
    }

    private final void A0S(@NonNull Fragment fragment) {
        if (fragment.A0T) {
            fragment.A0T = false;
            if (!fragment.A0Q) {
                this.A0O.A02(fragment);
                if (A0C(fragment)) {
                    this.A0F = true;
                }
            }
        }
    }

    private final void A0T(@NonNull Fragment fragment) {
        if (!fragment.A0T) {
            fragment.A0T = true;
            if (fragment.A0Q) {
                ArrayList<Fragment> arrayList = this.A0O.A00;
                synchronized (arrayList) {
                    arrayList.remove(fragment);
                }
                fragment.A0Q = false;
                if (A0C(fragment)) {
                    this.A0F = true;
                }
                A08(this, fragment);
            }
        }
    }

    private final void A0V(@NonNull Fragment fragment) {
        AnonymousClass09j r0 = this.A0O;
        if (r0.A01.containsKey(fragment.A0P)) {
            A0Y(fragment, this.A00);
            if (fragment.A0W) {
                if (fragment.A0Q && A0C(fragment)) {
                    this.A0F = true;
                }
                fragment.A0W = false;
            }
        }
    }

    private final void A0W(@NonNull Fragment fragment) {
        boolean z = false;
        if (fragment.A02 > 0) {
            z = true;
        }
        boolean z2 = !z;
        if (!fragment.A0T || z2) {
            ArrayList<Fragment> arrayList = this.A0O.A00;
            synchronized (arrayList) {
                arrayList.remove(fragment);
            }
            fragment.A0Q = false;
            if (A0C(fragment)) {
                this.A0F = true;
            }
            fragment.A0b = true;
            A08(this, fragment);
        }
    }

    private final void A0X(@Nullable Fragment fragment) {
        if (fragment == null || (fragment.equals(A0G(fragment.A0P)) && (fragment.A0F == null || fragment.A0H == this))) {
            Fragment fragment2 = this.A03;
            this.A03 = fragment;
            A07(this, fragment2);
            A07(this, this.A03);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(fragment);
        sb.append(" is not an active fragment of FragmentManager ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    private final void A0Z(@NonNull Fragment fragment, @NonNull AnonymousClass0AP r4) {
        if (!fragment.equals(A0G(fragment.A0P)) || !(fragment.A0F == null || fragment.A0H == this)) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(fragment);
            sb.append(" is not an active fragment of FragmentManager ");
            sb.append(this);
            throw new IllegalArgumentException(sb.toString());
        }
        fragment.A0J = r4;
    }

    private final void A0a(@NonNull Fragment fragment, boolean z) {
        ViewGroup viewGroup;
        if (fragment.A03 > 0 && this.A04.A01()) {
            View A002 = this.A04.A00(fragment.A03);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null && (viewGroup instanceof AnonymousClass09Q)) {
                ((AnonymousClass09Q) viewGroup).A00 = !z;
            }
        }
    }

    @Nullable
    public final Fragment A0F(@IdRes int i) {
        AnonymousClass09j r4 = this.A0O;
        ArrayList<Fragment> arrayList = r4.A00;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = arrayList.get(size);
                if (fragment != null && fragment.A04 == i) {
                    return fragment;
                }
            } else {
                for (AnonymousClass09i r0 : r4.A01.values()) {
                    if (r0 != null) {
                        Fragment fragment2 = r0.A01;
                        if (fragment2.A04 == i) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    @Nullable
    public final Fragment A0H(@Nullable String str) {
        AnonymousClass09j r4 = this.A0O;
        if (str == null) {
            return null;
        }
        ArrayList<Fragment> arrayList = r4.A00;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = arrayList.get(size);
                if (fragment != null && str.equals(fragment.A0N)) {
                    return fragment;
                }
            } else {
                for (AnonymousClass09i r0 : r4.A01.values()) {
                    if (r0 != null) {
                        Fragment fragment2 = r0.A01;
                        if (str.equals(fragment2.A0N)) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public final Fragment A0I(@NonNull String str) {
        for (AnonymousClass09i r0 : this.A0O.A01.values()) {
            if (r0 != null) {
                Fragment fragment = r0.A01;
                if (str.equals(fragment.A0P) || (fragment = fragment.A0G.A0I(str)) != null) {
                    return fragment;
                }
            }
        }
        return null;
    }

    @NonNull
    public final AnonymousClass09S A0J() {
        Fragment fragment = this.A02;
        if (fragment != null) {
            return fragment.A0H.A0J();
        }
        return this.A0I;
    }

    public final void A0L() {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.onLowMemory();
                fragment.A0G.A0L();
            }
        }
    }

    public final void A0O(@NonNull Configuration configuration) {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.onConfigurationChanged(configuration);
                fragment.A0G.A0O(configuration);
            }
        }
    }

    public final void A0P(@Nullable Parcelable parcelable) {
        Fragment fragment;
        Fragment fragment2;
        AnonymousClass09i r5;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.A02 != null) {
                AnonymousClass09j r4 = this.A0O;
                HashMap<String, AnonymousClass09i> hashMap = r4.A01;
                hashMap.clear();
                Iterator<FragmentState> it = fragmentManagerState.A02.iterator();
                while (it.hasNext()) {
                    FragmentState next = it.next();
                    if (next != null) {
                        Fragment fragment3 = this.A06.A02.get(next.A07);
                        if (fragment3 != null) {
                            r5 = new AnonymousClass09i(this.A0N, fragment3, next);
                        } else {
                            r5 = new AnonymousClass09i(this.A0N, this.A05.A01.getClassLoader(), A0J(), next);
                        }
                        Fragment fragment4 = r5.A01;
                        fragment4.A0H = this;
                        r5.A00(this.A05.A01.getClassLoader());
                        hashMap.put(fragment4.A0P, r5);
                        r5.A00 = this.A00;
                    }
                }
                for (Fragment fragment5 : this.A06.A02.values()) {
                    if (!hashMap.containsKey(fragment5.A0P)) {
                        A0Y(fragment5, 1);
                        fragment5.A0b = true;
                        A0Y(fragment5, -1);
                    }
                }
                ArrayList<String> arrayList = fragmentManagerState.A03;
                r4.A00.clear();
                if (arrayList != null) {
                    for (String str : arrayList) {
                        AnonymousClass09i r0 = hashMap.get(str);
                        if (r0 == null || (fragment2 = r0.A01) == null) {
                            throw new IllegalStateException(AnonymousClass006.A09("No instantiated fragment for (", str, ")"));
                        }
                        r4.A02(fragment2);
                    }
                }
                BackStackState[] backStackStateArr = fragmentManagerState.A04;
                if (backStackStateArr != null) {
                    this.A08 = new ArrayList<>(backStackStateArr.length);
                    int i = 0;
                    while (true) {
                        BackStackState[] backStackStateArr2 = fragmentManagerState.A04;
                        if (i >= backStackStateArr2.length) {
                            break;
                        }
                        BackStackState backStackState = backStackStateArr2[i];
                        AnonymousClass0vO r2 = new AnonymousClass0vO(this);
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            int[] iArr = backStackState.A0D;
                            if (i2 >= iArr.length) {
                                break;
                            }
                            C003709p r7 = new C003709p();
                            int i4 = i2 + 1;
                            r7.A00 = iArr[i2];
                            String str2 = backStackState.A07.get(i3);
                            if (str2 != null) {
                                fragment = A0G(str2);
                            } else {
                                fragment = null;
                            }
                            r7.A05 = fragment;
                            r7.A07 = AnonymousClass0AP.values()[backStackState.A0C[i3]];
                            r7.A06 = AnonymousClass0AP.values()[backStackState.A0B[i3]];
                            int i5 = i4 + 1;
                            int i6 = iArr[i4];
                            r7.A01 = i6;
                            int i7 = i5 + 1;
                            int i8 = iArr[i5];
                            r7.A02 = i8;
                            int i9 = i7 + 1;
                            int i10 = iArr[i7];
                            r7.A03 = i10;
                            i2 = i9 + 1;
                            int i11 = iArr[i9];
                            r7.A04 = i11;
                            ((AbstractC003809q) r2).A02 = i6;
                            r2.A03 = i8;
                            r2.A04 = i10;
                            r2.A05 = i11;
                            r2.A0A.add(r7);
                            r7.A01 = ((AbstractC003809q) r2).A02;
                            r7.A02 = r2.A03;
                            r7.A03 = r2.A04;
                            r7.A04 = r2.A05;
                            i3++;
                        }
                        r2.A06 = backStackState.A03;
                        r2.A09 = backStackState.A06;
                        r2.A00 = backStackState.A02;
                        r2.A0D = true;
                        ((AbstractC003809q) r2).A01 = backStackState.A01;
                        r2.A08 = backStackState.A05;
                        ((AbstractC003809q) r2).A00 = backStackState.A00;
                        r2.A07 = backStackState.A04;
                        r2.A0B = backStackState.A08;
                        r2.A0C = backStackState.A09;
                        r2.A0E = backStackState.A0A;
                        r2.A03(1);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            PrintWriter printWriter = new PrintWriter(new AnonymousClass0AA());
                            r2.A04("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.A08.add(r2);
                        i++;
                    }
                } else {
                    this.A08 = null;
                }
                this.A0R.set(fragmentManagerState.A00);
                String str3 = fragmentManagerState.A01;
                if (str3 != null) {
                    Fragment A0G2 = A0G(str3);
                    this.A03 = A0G2;
                    A07(this, A0G2);
                }
            }
        }
    }

    public final void A0Q(@NonNull Menu menu) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (fragment != null && !fragment.A0V) {
                    fragment.A0G.A0Q(menu);
                }
            }
        }
    }

    public final void A0U(@NonNull Fragment fragment) {
        AnonymousClass09j r1 = this.A0O;
        String str = fragment.A0P;
        HashMap<String, AnonymousClass09i> hashMap = r1.A01;
        if (!hashMap.containsKey(str)) {
            AnonymousClass09i r12 = new AnonymousClass09i(this.A0N, fragment);
            r12.A00(this.A05.A01.getClassLoader());
            hashMap.put(r12.A01.A0P, r12);
            r12.A00 = this.A00;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v15, resolved type: X.0wo */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A0b(@NonNull AbstractC05340vL<?> r6, @NonNull AnonymousClass09P r7, @Nullable Fragment fragment) {
        C05280vB r0;
        if (this.A05 == null) {
            this.A05 = r6;
            this.A04 = r7;
            this.A02 = fragment;
            if (fragment != null) {
                A04(this);
            }
            if (r6 instanceof AnonymousClass0wo) {
                AnonymousClass0wo r02 = (AnonymousClass0wo) r6;
                AnonymousClass01Q onBackPressedDispatcher = r02.getOnBackPressedDispatcher();
                this.A01 = onBackPressedDispatcher;
                Fragment fragment2 = r02;
                if (fragment != null) {
                    fragment2 = fragment;
                }
                AnonymousClass01P r3 = this.A0L;
                AnonymousClass0AQ lifecycle = fragment2.getLifecycle();
                if (lifecycle.A05() != AnonymousClass0AP.DESTROYED) {
                    r3.A00.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(onBackPressedDispatcher, lifecycle, r3));
                }
            }
            if (fragment != null) {
                C05280vB r32 = fragment.A0H.A06;
                HashMap<String, C05280vB> hashMap = r32.A01;
                C05280vB r1 = hashMap.get(fragment.A0P);
                if (r1 == null) {
                    r1 = new C05280vB(r32.A04);
                    hashMap.put(fragment.A0P, r1);
                }
                this.A06 = r1;
                return;
            }
            if (r6 instanceof AbstractC00480Al) {
                r0 = (C05280vB) new C00460Aj(((AbstractC00480Al) r6).getViewModelStore(), C05280vB.A05).A00(C05280vB.class);
            } else {
                r0 = new C05280vB(false);
            }
            this.A06 = r0;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x02a1 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0c(@androidx.annotation.NonNull java.lang.String r8, @androidx.annotation.Nullable java.io.FileDescriptor r9, @androidx.annotation.NonNull java.io.PrintWriter r10, @androidx.annotation.Nullable java.lang.String[] r11) {
        /*
        // Method dump skipped, instructions count: 833
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09b.A0c(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    public final void A0d(boolean z) {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.A0G.A0d(z);
            }
        }
    }

    public final void A0e(boolean z) {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.A0G.A0e(z);
            }
        }
    }

    public final boolean A0g(@NonNull Menu menu) {
        boolean z = false;
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (fragment != null && !fragment.A0V && (false || fragment.A0G.A0g(menu))) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final boolean A0h(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        int i = 0;
        if (this.A00 < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null && !fragment.A0V && (fragment.A0G.A0h(menu, menuInflater) || false)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.A0J != null) {
            while (true) {
                ArrayList<Fragment> arrayList2 = this.A0J;
                if (i >= arrayList2.size()) {
                    break;
                }
                Fragment fragment2 = arrayList2.get(i);
                if (arrayList != null) {
                    arrayList.contains(fragment2);
                }
                i++;
            }
        }
        this.A0J = arrayList;
        return z;
    }

    public final boolean A0i(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (!(fragment == null || fragment.A0V || !fragment.A0G.A0i(menuItem))) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean A0j(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (!(fragment == null || fragment.A0V || !fragment.A0G.A0j(menuItem))) {
                    return true;
                }
            }
        }
        return false;
    }

    @NonNull
    public final String toString() {
        int identityHashCode;
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.A02;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(fragment);
        } else {
            AbstractC05340vL<?> r1 = this.A05;
            sb.append(r1.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(r1);
        }
        sb.append(Integer.toHexString(identityHashCode));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/ArrayList<LX/0vO;>;Ljava/util/ArrayList<Ljava/lang/Boolean;>;)V */
    public static void A09(@NonNull AnonymousClass09b r4, @NonNull ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!((AbstractC003809q) arrayList.get(i)).A0E) {
                    if (i2 != i) {
                        r4.A0B(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (((Boolean) arrayList2.get(i)).booleanValue()) {
                        while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((AbstractC003809q) arrayList.get(i2)).A0E) {
                            i2++;
                        }
                    }
                    r4.A0B(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                r4.A0B(arrayList, arrayList2, i2, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable A0E() {
        /*
        // Method dump skipped, instructions count: 302
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09b.A0E():android.os.Parcelable");
    }

    public final void A0R(@NonNull Fragment fragment) {
        A0U(fragment);
        if (!fragment.A0T) {
            this.A0O.A02(fragment);
            fragment.A0b = false;
            fragment.A0W = false;
            if (A0C(fragment)) {
                this.A0F = true;
            }
        }
    }

    public final void A0f(boolean z) {
        A0A(this, z);
        while (true) {
            ArrayList<AnonymousClass0vO> arrayList = this.A0A;
            ArrayList<Boolean> arrayList2 = this.A09;
            ArrayList<AnonymousClass09a> arrayList3 = this.A0Q;
            synchronized (arrayList3) {
                if (arrayList3.isEmpty()) {
                    break;
                }
                int size = arrayList3.size();
                boolean z2 = false;
                for (int i = 0; i < size; i++) {
                    z2 |= arrayList3.get(i).A3H(arrayList, arrayList2);
                }
                arrayList3.clear();
                this.A05.A02.removeCallbacks(this.A07);
                if (!z2) {
                    break;
                }
                this.A0D = true;
                try {
                    A09(this, this.A0A, this.A09);
                } finally {
                    A02(this);
                }
            }
        }
        A04(this);
        if (this.A0E) {
            this.A0E = false;
            A03(this);
        }
        this.A0O.A01.values().removeAll(Collections.singleton(null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0314, code lost:
        if (r2.A02 > 0) goto L_0x0316;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0080, code lost:
        if (r10 != 3) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x021d, code lost:
        if (r4 <= -1) goto L_0x0159;
     */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0Y(@androidx.annotation.NonNull androidx.fragment.app.Fragment r21, int r22) {
        /*
        // Method dump skipped, instructions count: 1402
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09b.A0Y(androidx.fragment.app.Fragment, int):void");
    }
}
