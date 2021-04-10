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
import com.oculus.unifiedtelemetry.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.9a  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00279a {
    public int A00 = -1;
    public AnonymousClass1Q A01;
    public Fragment A02;
    @Nullable
    public Fragment A03;
    public AnonymousClass9O A04;
    public AbstractC0286a9<?> A05;
    public C0281a2 A06;
    public Runnable A07 = new AnonymousClass9V(this);
    public ArrayList<aC> A08;
    public ArrayList<Boolean> A09;
    public ArrayList<aC> A0A;
    public ConcurrentHashMap<Fragment, HashSet<AnonymousClass5d>> A0B = new ConcurrentHashMap<>();
    public boolean A0C;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public boolean A0H;
    public AnonymousClass9R A0I = new C0285a6(this);
    public ArrayList<Fragment> A0J;
    public ArrayList<Fragment> A0K;
    public final AnonymousClass1P A0L = new a8(this);
    public final AnonymousClass9S A0M = new AnonymousClass9S(this);
    public final AnonymousClass9U A0N = new AnonymousClass9U(this);
    public final C00339i A0O = new C00339i();
    public final AbstractC00409v A0P = new a7(this);
    public final ArrayList<AnonymousClass9Z> A0Q = new ArrayList<>();
    public final AtomicInteger A0R = new AtomicInteger();

    public static void A02(AbstractC00279a r1) {
        r1.A0D = false;
        r1.A09.clear();
        r1.A0A.clear();
    }

    /* JADX INFO: finally extract failed */
    public static void A05(AbstractC00279a r6, int i) {
        try {
            r6.A0D = true;
            C00339i r5 = r6.A0O;
            Iterator<Fragment> it = r5.A00.iterator();
            while (it.hasNext()) {
                C00329h r0 = r5.A01.get(it.next().A0P);
                if (r0 != null) {
                    r0.A00 = i;
                }
            }
            for (C00329h r02 : r5.A01.values()) {
                if (r02 != null) {
                    r02.A00 = i;
                }
            }
            r6.A0M(i, false);
            r6.A0D = false;
            r6.A0e(true);
        } catch (Throwable th) {
            r6.A0D = false;
            throw th;
        }
    }

    public static final boolean A0D(AbstractC00279a r7) {
        boolean z;
        int size;
        r7.A0e(false);
        A0A(r7, true);
        Fragment fragment = r7.A03;
        if (fragment != null) {
            if (fragment.A0F == null) {
                StringBuilder sb = new StringBuilder("Fragment ");
                sb.append(fragment);
                sb.append(" has not been attached yet.");
                throw new IllegalStateException(sb.toString());
            } else if (A0D(fragment.A0G)) {
                return true;
            }
        }
        ArrayList<aC> arrayList = r7.A0A;
        ArrayList<Boolean> arrayList2 = r7.A09;
        ArrayList<aC> arrayList3 = r7.A08;
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

    private final boolean A0j(@Nullable Fragment fragment) {
        if (fragment != null) {
            AbstractC00279a r1 = fragment.A0H;
            if (!fragment.equals(r1.A03) || !A0j(r1.A02)) {
                return false;
            }
        }
        return true;
    }

    public final void A0J() {
        this.A0C = true;
        A0e(true);
        A00();
        A05(this, -1);
        this.A05 = null;
        this.A04 = null;
        this.A02 = null;
        if (this.A01 != null) {
            Iterator<AnonymousClass1M> it = this.A0L.A00.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.A01 = null;
        }
    }

    public final void A0L() {
        this.A0G = false;
        this.A0H = false;
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.A0G.A0L();
            }
        }
    }

    private void A00() {
        int i;
        if (!this.A0B.isEmpty()) {
            for (Fragment fragment : this.A0B.keySet()) {
                A01(fragment);
                AnonymousClass9D r0 = fragment.A0C;
                if (r0 == null) {
                    i = 0;
                } else {
                    i = r0.A01;
                }
                A0X(fragment, i);
            }
        }
    }

    private void A01(@NonNull Fragment fragment) {
        HashSet<AnonymousClass5d> hashSet = this.A0B.get(fragment);
        if (hashSet != null) {
            Iterator<AnonymousClass5d> it = hashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass5d next = it.next();
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

    public static void A03(AbstractC00279a r3) {
        for (Fragment fragment : r3.A0O.A00()) {
            if (fragment != null && fragment.A0S) {
                if (r3.A0D) {
                    r3.A0E = true;
                } else {
                    fragment.A0S = false;
                    r3.A0X(fragment, r3.A00);
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
        if (r3.A0j(r3.A02) == false) goto L_0x0028;
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
    public static void A04(X.AbstractC00279a r3) {
        /*
            java.util.ArrayList<X.9Z> r1 = r3.A0Q
            monitor-enter(r1)
            boolean r0 = r1.isEmpty()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r0 != 0) goto L_0x0010
            X.1P r0 = r3.A0L     // Catch:{ all -> 0x002a }
            r0.A01 = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x0010:
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            X.1P r1 = r3.A0L
            java.util.ArrayList<X.aC> r0 = r3.A08
            if (r0 == 0) goto L_0x0028
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0028
            androidx.fragment.app.Fragment r0 = r3.A02
            boolean r0 = r3.A0j(r0)
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
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00279a.A04(X.9a):void");
    }

    public static void A06(@NonNull AbstractC00279a r3, Fragment fragment) {
        A05(fragment.A0G, 1);
        fragment.A05 = 1;
        fragment.A0R = false;
        fragment.A0R = true;
        new Zn(fragment, fragment.getViewModelStore()).A00();
        fragment.A0a = false;
        r3.A0N.A00(fragment);
        fragment.A0B = null;
        fragment.A0I = null;
        fragment.A00.A02(null);
        fragment.A0X = false;
    }

    public static void A07(@Nullable AbstractC00279a r1, Fragment fragment) {
        if (fragment != null && fragment.equals(r1.A0G(fragment.A0P))) {
            boolean A0j = fragment.A0H.A0j(fragment);
            Boolean bool = fragment.A0M;
            if (bool == null || bool.booleanValue() != A0j) {
                fragment.A0M = Boolean.valueOf(A0j);
                AbstractC00279a r12 = fragment.A0G;
                A04(r12);
                A07(r12, r12.A03);
            }
        }
    }

    public static void A08(@NonNull AbstractC00279a r1, Fragment fragment) {
        ViewGroup viewGroup;
        int i;
        if (fragment.A03 > 0 && r1.A04.A01()) {
            View A002 = r1.A04.A00(fragment.A03);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null) {
                if (viewGroup.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    viewGroup.setTag(R.id.visible_removing_fragment_view_tag, fragment);
                }
                Fragment fragment2 = (Fragment) viewGroup.getTag(R.id.visible_removing_fragment_view_tag);
                AnonymousClass9D r0 = fragment.A0C;
                if (r0 == null) {
                    i = 0;
                } else {
                    i = r0.A00;
                }
                fragment2.A05(i);
            }
        }
    }

    public static void A0A(AbstractC00279a r2, boolean z) {
        String str;
        if (r2.A0D) {
            str = "FragmentManager is already executing transactions";
        } else if (r2.A05 == null) {
            if (r2.A0C) {
                str = "FragmentManager has been destroyed";
            } else {
                str = "FragmentManager has not been attached to a host.";
            }
        } else if (Looper.myLooper() != r2.A05.A02.getLooper()) {
            str = "Must be called from main thread of fragment host";
        } else if (z || (!r2.A0G && !r2.A0H)) {
            if (r2.A0A == null) {
                r2.A0A = new ArrayList<>();
                r2.A09 = new ArrayList<>();
            }
            r2.A0D = true;
            r2.A0D = false;
            return;
        } else {
            str = "Can not perform this action after onSaveInstanceState";
        }
        throw new IllegalStateException(str);
    }

    private void A0B(@NonNull ArrayList<aC> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i, int i2) {
        int i3;
        AbstractC00279a r1;
        int i4 = i;
        boolean z = arrayList.get(i4).A0E;
        ArrayList<Fragment> arrayList3 = this.A0K;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            this.A0K = arrayList3;
        } else {
            arrayList3.clear();
        }
        C00339i r0 = this.A0O;
        arrayList3.addAll(r0.A01());
        Fragment fragment = this.A03;
        boolean z2 = false;
        for (int i5 = i4; i5 < i2; i5++) {
            aC aCVar = arrayList.get(i5);
            if (!arrayList2.get(i5).booleanValue()) {
                ArrayList<Fragment> arrayList4 = this.A0K;
                int i6 = 0;
                while (true) {
                    ArrayList<C00349o> arrayList5 = aCVar.A0A;
                    if (i6 < arrayList5.size()) {
                        C00349o r13 = arrayList5.get(i6);
                        int i7 = r13.A00;
                        if (i7 != 1) {
                            if (i7 == 2) {
                                Fragment fragment2 = r13.A05;
                                int i8 = fragment2.A03;
                                boolean z3 = false;
                                for (int size = arrayList4.size() - 1; size >= 0; size--) {
                                    Fragment fragment3 = arrayList4.get(size);
                                    if (fragment3.A03 == i8) {
                                        if (fragment3 == fragment2) {
                                            z3 = true;
                                        } else {
                                            if (fragment3 == fragment) {
                                                aCVar.A0A.add(i6, new C00349o(9, fragment3));
                                                i6++;
                                                fragment = null;
                                            }
                                            C00349o r15 = new C00349o(3, fragment3);
                                            r15.A01 = r13.A01;
                                            r15.A03 = r13.A03;
                                            r15.A02 = r13.A02;
                                            r15.A04 = r13.A04;
                                            aCVar.A0A.add(i6, r15);
                                            arrayList4.remove(fragment3);
                                            i6++;
                                        }
                                    }
                                }
                                if (z3) {
                                    aCVar.A0A.remove(i6);
                                    i6--;
                                } else {
                                    r13.A00 = 1;
                                    arrayList4.add(fragment2);
                                }
                            } else if (i7 == 3 || i7 == 6) {
                                arrayList4.remove(r13.A05);
                                if (r13.A05 == fragment) {
                                    aCVar.A0A.add(i6, new C00349o(9, r13.A05));
                                    i6++;
                                    fragment = null;
                                }
                            } else if (i7 != 7) {
                                if (i7 == 8) {
                                    arrayList5.add(i6, new C00349o(9, fragment));
                                    i6++;
                                    fragment = r13.A05;
                                }
                            }
                            i6++;
                        }
                        arrayList4.add(r13.A05);
                        i6++;
                    }
                }
            } else {
                ArrayList<Fragment> arrayList6 = this.A0K;
                for (int size2 = aCVar.A0A.size() - 1; size2 >= 0; size2--) {
                    C00349o r2 = aCVar.A0A.get(size2);
                    int i9 = r2.A00;
                    if (i9 != 1) {
                        if (i9 != 3) {
                            switch (i9) {
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
                if (!aCVar.A0D) {
                }
            }
            z2 = true;
        }
        this.A0K.clear();
        if (!z && this.A00 >= 1) {
            C00429x.A02(this.A05.A01, this.A04, arrayList, arrayList2, i4, i2, false, this.A0P);
        }
        for (int i10 = i4; i10 < i2; i10++) {
            aC aCVar2 = arrayList.get(i10);
            boolean z4 = true;
            if (arrayList2.get(i10).booleanValue()) {
                aCVar2.A02(-1);
                if (i10 != i2 - 1) {
                    z4 = false;
                }
                for (int size3 = aCVar2.A0A.size() - 1; size3 >= 0; size3--) {
                    C00349o r12 = aCVar2.A0A.get(size3);
                    Fragment fragment4 = r12.A05;
                    if (fragment4 != null) {
                        int i11 = aCVar2.A06;
                        char c = 8194;
                        if (i11 != 4097) {
                            if (i11 != 4099) {
                                c = 4097;
                                if (i11 != 8194) {
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
                    i3 = r12.A00;
                    switch (i3) {
                        case 1:
                            fragment4.A05(r12.A04);
                            AbstractC00279a r02 = aCVar2.A01;
                            r02.A0Z(fragment4, true);
                            r02.A0V(fragment4);
                            break;
                        case 3:
                            fragment4.A05(r12.A03);
                            aCVar2.A01.A0Q(fragment4);
                            break;
                        case 4:
                            fragment4.A05(r12.A03);
                            if (fragment4.A0V) {
                                fragment4.A0V = false;
                                fragment4.A0W = !fragment4.A0W;
                                break;
                            }
                            break;
                        case 5:
                            fragment4.A05(r12.A04);
                            AbstractC00279a r14 = aCVar2.A01;
                            r14.A0Z(fragment4, true);
                            if (!fragment4.A0V) {
                                fragment4.A0V = true;
                                fragment4.A0W = !fragment4.A0W;
                                A08(r14, fragment4);
                                break;
                            }
                            break;
                        case 6:
                            fragment4.A05(r12.A03);
                            aCVar2.A01.A0R(fragment4);
                            break;
                        case 7:
                            fragment4.A05(r12.A04);
                            AbstractC00279a r03 = aCVar2.A01;
                            r03.A0Z(fragment4, true);
                            r03.A0S(fragment4);
                            break;
                        case 8:
                            aCVar2.A01.A0W(null);
                            break;
                        case 9:
                            aCVar2.A01.A0W(fragment4);
                            break;
                        case 10:
                            aCVar2.A01.A0Y(fragment4, r12.A07);
                            break;
                    }
                    if (!(aCVar2.A0E || r12.A00 == 3 || fragment4 == null)) {
                        aCVar2.A01.A0U(fragment4);
                    }
                }
                if (!aCVar2.A0E) {
                    if (!z4) {
                    }
                    AbstractC00279a r16 = aCVar2.A01;
                    r16.A0M(r16.A00, true);
                }
            } else {
                aCVar2.A02(1);
                int size4 = aCVar2.A0A.size();
                for (int i12 = 0; i12 < size4; i12++) {
                    C00349o r152 = aCVar2.A0A.get(i12);
                    Fragment fragment5 = r152.A05;
                    if (fragment5 != null) {
                        int i13 = aCVar2.A06;
                        if (!(fragment5.A0C == null && i13 == 0)) {
                            Fragment.A00(fragment5);
                        }
                    }
                    i3 = r152.A00;
                    switch (i3) {
                        case 1:
                            fragment5.A05(r152.A01);
                            r1 = aCVar2.A01;
                            r1.A0Z(fragment5, false);
                            r1.A0Q(fragment5);
                            break;
                        case 3:
                            fragment5.A05(r152.A02);
                            r1 = aCVar2.A01;
                            r1.A0V(fragment5);
                            break;
                        case 4:
                            fragment5.A05(r152.A02);
                            r1 = aCVar2.A01;
                            if (!fragment5.A0V) {
                                fragment5.A0V = true;
                                fragment5.A0W = !fragment5.A0W;
                                A08(r1, fragment5);
                                break;
                            }
                            break;
                        case 5:
                            fragment5.A05(r152.A01);
                            r1 = aCVar2.A01;
                            r1.A0Z(fragment5, false);
                            if (fragment5.A0V) {
                                fragment5.A0V = false;
                                fragment5.A0W = !fragment5.A0W;
                                break;
                            }
                            break;
                        case 6:
                            fragment5.A05(r152.A02);
                            r1 = aCVar2.A01;
                            r1.A0S(fragment5);
                            break;
                        case 7:
                            fragment5.A05(r152.A01);
                            r1 = aCVar2.A01;
                            r1.A0Z(fragment5, false);
                            r1.A0R(fragment5);
                            break;
                        case 8:
                            r1 = aCVar2.A01;
                            r1.A0W(fragment5);
                            break;
                        case 9:
                            r1 = aCVar2.A01;
                            r1.A0W(null);
                            break;
                        case 10:
                            r1 = aCVar2.A01;
                            r1.A0Y(fragment5, r152.A06);
                            break;
                    }
                    if (!(aCVar2.A0E || r152.A00 == 1 || fragment5 == null)) {
                        r1.A0U(fragment5);
                    }
                }
                if (aCVar2.A0E) {
                }
                AbstractC00279a r162 = aCVar2.A01;
                r162.A0M(r162.A00, true);
            }
            throw new IllegalArgumentException(AnonymousClass06.A01("Unknown cmd: ", i3));
        }
        if (z) {
            C00052c r6 = new C00052c();
            int i14 = this.A00;
            if (i14 >= 1) {
                int min = Math.min(i14, 3);
                for (Fragment fragment6 : r0.A01()) {
                    if (fragment6.A05 < min) {
                        A0X(fragment6, min);
                    }
                }
            }
            for (int i15 = i2 - 1; i15 >= i4; i15--) {
                aC aCVar3 = arrayList.get(i15);
                arrayList2.get(i15);
                int i16 = 0;
                while (true) {
                    ArrayList<C00349o> arrayList7 = aCVar3.A0A;
                    if (i16 < arrayList7.size()) {
                        arrayList7.get(i16);
                        i16++;
                    }
                }
            }
            int size5 = r6.size();
            for (int i17 = 0; i17 < size5; i17++) {
                Fragment fragment7 = (Fragment) r6.A03[i17];
                if (!fragment7.A0Q) {
                    fragment7.A04();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else {
            z = false;
        }
        if (i2 != i4 && z) {
            if (this.A00 >= 1) {
                C00429x.A02(this.A05.A01, this.A04, arrayList, arrayList2, i4, i2, true, this.A0P);
            }
            A0M(this.A00, true);
        }
        while (i4 < i2) {
            aC aCVar4 = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue() && aCVar4.A00 >= 0) {
                aCVar4.A00 = -1;
            }
            i4++;
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
        C00329h r0 = this.A0O.A01.get(str);
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    private final void A0M(int i, boolean z) {
        AbstractC0286a9<?> a9Var;
        if (this.A05 == null && i != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.A00) {
            this.A00 = i;
            C00339i r2 = this.A0O;
            for (Fragment fragment : r2.A01()) {
                A0U(fragment);
            }
            for (Fragment fragment2 : r2.A00()) {
                if (fragment2 != null) {
                    A0U(fragment2);
                }
            }
            A03(this);
            if (this.A0F && (a9Var = this.A05) != null && this.A00 == 4) {
                a9Var.A04();
                this.A0F = false;
            }
        }
    }

    private final void A0R(@NonNull Fragment fragment) {
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

    private final void A0S(@NonNull Fragment fragment) {
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

    private final void A0U(@NonNull Fragment fragment) {
        C00339i r0 = this.A0O;
        if (r0.A01.containsKey(fragment.A0P)) {
            A0X(fragment, this.A00);
            if (fragment.A0W) {
                if (fragment.A0Q && A0C(fragment)) {
                    this.A0F = true;
                }
                fragment.A0W = false;
            }
        }
    }

    private final void A0V(@NonNull Fragment fragment) {
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

    private final void A0W(@Nullable Fragment fragment) {
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

    private final void A0Y(@NonNull Fragment fragment, @NonNull AO ao) {
        if (!fragment.equals(A0G(fragment.A0P)) || !(fragment.A0F == null || fragment.A0H == this)) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(fragment);
            sb.append(" is not an active fragment of FragmentManager ");
            sb.append(this);
            throw new IllegalArgumentException(sb.toString());
        }
        fragment.A0J = ao;
    }

    private final void A0Z(@NonNull Fragment fragment, boolean z) {
        ViewGroup viewGroup;
        if (fragment.A03 > 0 && this.A04.A01()) {
            View A002 = this.A04.A00(fragment.A03);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null && (viewGroup instanceof AnonymousClass9P)) {
                ((AnonymousClass9P) viewGroup).A00 = !z;
            }
        }
    }

    @Nullable
    public final Fragment A0F(@IdRes int i) {
        C00339i r4 = this.A0O;
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
                for (C00329h r0 : r4.A01.values()) {
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

    public final Fragment A0H(@NonNull String str) {
        for (C00329h r0 : this.A0O.A01.values()) {
            if (r0 != null) {
                Fragment fragment = r0.A01;
                if (str.equals(fragment.A0P) || (fragment = fragment.A0G.A0H(str)) != null) {
                    return fragment;
                }
            }
        }
        return null;
    }

    @NonNull
    public final AnonymousClass9R A0I() {
        Fragment fragment = this.A02;
        if (fragment != null) {
            return fragment.A0H.A0I();
        }
        return this.A0I;
    }

    public final void A0K() {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.onLowMemory();
                fragment.A0G.A0K();
            }
        }
    }

    public final void A0N(@NonNull Configuration configuration) {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.onConfigurationChanged(configuration);
                fragment.A0G.A0N(configuration);
            }
        }
    }

    public final void A0O(@Nullable Parcelable parcelable) {
        Fragment fragment;
        Fragment fragment2;
        C00329h r5;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.A02 != null) {
                C00339i r4 = this.A0O;
                HashMap<String, C00329h> hashMap = r4.A01;
                hashMap.clear();
                Iterator<FragmentState> it = fragmentManagerState.A02.iterator();
                while (it.hasNext()) {
                    FragmentState next = it.next();
                    if (next != null) {
                        Fragment fragment3 = this.A06.A02.get(next.A07);
                        if (fragment3 != null) {
                            r5 = new C00329h(this.A0N, fragment3, next);
                        } else {
                            r5 = new C00329h(this.A0N, this.A05.A01.getClassLoader(), A0I(), next);
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
                        A0X(fragment5, 1);
                        fragment5.A0b = true;
                        A0X(fragment5, -1);
                    }
                }
                ArrayList<String> arrayList = fragmentManagerState.A03;
                r4.A00.clear();
                if (arrayList != null) {
                    for (String str : arrayList) {
                        C00329h r0 = hashMap.get(str);
                        if (r0 == null || (fragment2 = r0.A01) == null) {
                            throw new IllegalStateException(AnonymousClass06.A05("No instantiated fragment for (", str, ")"));
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
                        aC aCVar = new aC(this);
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            int[] iArr = backStackState.A0D;
                            if (i2 >= iArr.length) {
                                break;
                            }
                            C00349o r7 = new C00349o();
                            int i4 = i2 + 1;
                            r7.A00 = iArr[i2];
                            String str2 = backStackState.A07.get(i3);
                            if (str2 != null) {
                                fragment = A0G(str2);
                            } else {
                                fragment = null;
                            }
                            r7.A05 = fragment;
                            r7.A07 = AO.values()[backStackState.A0C[i3]];
                            r7.A06 = AO.values()[backStackState.A0B[i3]];
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
                            aCVar.A02 = i6;
                            aCVar.A03 = i8;
                            aCVar.A04 = i10;
                            aCVar.A05 = i11;
                            aCVar.A0A.add(r7);
                            r7.A01 = aCVar.A02;
                            r7.A02 = aCVar.A03;
                            r7.A03 = aCVar.A04;
                            r7.A04 = aCVar.A05;
                            i3++;
                        }
                        aCVar.A06 = backStackState.A03;
                        aCVar.A09 = backStackState.A06;
                        aCVar.A00 = backStackState.A02;
                        aCVar.A0D = true;
                        ((AbstractC00359p) aCVar).A01 = backStackState.A01;
                        aCVar.A08 = backStackState.A05;
                        ((AbstractC00359p) aCVar).A00 = backStackState.A00;
                        aCVar.A07 = backStackState.A04;
                        aCVar.A0B = backStackState.A08;
                        aCVar.A0C = backStackState.A09;
                        aCVar.A0E = backStackState.A0A;
                        aCVar.A02(1);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            PrintWriter printWriter = new PrintWriter(new A9());
                            aCVar.A03("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.A08.add(aCVar);
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

    public final void A0P(@NonNull Menu menu) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (fragment != null && !fragment.A0V) {
                    fragment.A0G.A0P(menu);
                }
            }
        }
    }

    public final void A0T(@NonNull Fragment fragment) {
        C00339i r1 = this.A0O;
        String str = fragment.A0P;
        HashMap<String, C00329h> hashMap = r1.A01;
        if (!hashMap.containsKey(str)) {
            C00329h r12 = new C00329h(this.A0N, fragment);
            r12.A00(this.A05.A01.getClassLoader());
            hashMap.put(r12.A01.A0P, r12);
            r12.A00 = this.A00;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0301, code lost:
        if (r3.A02 > 0) goto L_0x0303;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007d, code lost:
        if (r8 != 3) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x020f, code lost:
        if (r4 <= -1) goto L_0x0151;
     */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0X(@androidx.annotation.NonNull androidx.fragment.app.Fragment r15, int r16) {
        /*
        // Method dump skipped, instructions count: 1220
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00279a.A0X(androidx.fragment.app.Fragment, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v15, resolved type: X.ad */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A0a(@NonNull AbstractC0286a9<?> a9Var, @NonNull AnonymousClass9O r7, @Nullable Fragment fragment) {
        C0281a2 a2Var;
        if (this.A05 == null) {
            this.A05 = a9Var;
            this.A04 = r7;
            this.A02 = fragment;
            if (fragment != null) {
                A04(this);
            }
            if (a9Var instanceof AbstractC0305ad) {
                AbstractC0305ad adVar = (AbstractC0305ad) a9Var;
                AnonymousClass1Q onBackPressedDispatcher = adVar.getOnBackPressedDispatcher();
                this.A01 = onBackPressedDispatcher;
                Fragment fragment2 = adVar;
                if (fragment != null) {
                    fragment2 = fragment;
                }
                AnonymousClass1P r3 = this.A0L;
                AP lifecycle = fragment2.getLifecycle();
                if (lifecycle.A05() != AO.DESTROYED) {
                    r3.A00.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(onBackPressedDispatcher, lifecycle, r3));
                }
            }
            if (fragment != null) {
                C0281a2 a2Var2 = fragment.A0H.A06;
                HashMap<String, C0281a2> hashMap = a2Var2.A01;
                C0281a2 a2Var3 = hashMap.get(fragment.A0P);
                if (a2Var3 == null) {
                    a2Var3 = new C0281a2(a2Var2.A04);
                    hashMap.put(fragment.A0P, a2Var3);
                }
                this.A06 = a2Var3;
                return;
            }
            if (a9Var instanceof AbstractC0047Ak) {
                a2Var = (C0281a2) new C0045Ai(((AbstractC0047Ak) a9Var).getViewModelStore(), C0281a2.A05).A00(C0281a2.class);
            } else {
                a2Var = new C0281a2(false);
            }
            this.A06 = a2Var;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x02a3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0b(@androidx.annotation.NonNull java.lang.String r8, @androidx.annotation.Nullable java.io.FileDescriptor r9, @androidx.annotation.NonNull java.io.PrintWriter r10, @androidx.annotation.Nullable java.lang.String[] r11) {
        /*
        // Method dump skipped, instructions count: 835
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00279a.A0b(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    public final void A0c(boolean z) {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.A0G.A0c(z);
            }
        }
    }

    public final void A0d(boolean z) {
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null) {
                fragment.A0G.A0d(z);
            }
        }
    }

    public final boolean A0f(@NonNull Menu menu) {
        boolean z = false;
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (fragment != null && !fragment.A0V && (false || fragment.A0G.A0f(menu))) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final boolean A0g(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        int i = 0;
        if (this.A00 < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.A0O.A01()) {
            if (fragment != null && !fragment.A0V && (fragment.A0G.A0g(menu, menuInflater) || false)) {
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

    public final boolean A0h(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0O.A01()) {
                if (!(fragment == null || fragment.A0V || !fragment.A0G.A0h(menuItem))) {
                    return true;
                }
            }
        }
        return false;
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
            AbstractC0286a9<?> a9Var = this.A05;
            sb.append(a9Var.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(a9Var);
        }
        sb.append(Integer.toHexString(identityHashCode));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/ArrayList<LX/aC;>;Ljava/util/ArrayList<Ljava/lang/Boolean;>;)V */
    public static void A09(@NonNull AbstractC00279a r4, @NonNull ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!((AbstractC00359p) arrayList.get(i)).A0E) {
                    if (i2 != i) {
                        r4.A0B(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (((Boolean) arrayList2.get(i)).booleanValue()) {
                        while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((AbstractC00359p) arrayList.get(i2)).A0E) {
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

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable A0E() {
        /*
        // Method dump skipped, instructions count: 301
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00279a.A0E():android.os.Parcelable");
    }

    public final void A0Q(@NonNull Fragment fragment) {
        A0T(fragment);
        if (!fragment.A0T) {
            this.A0O.A02(fragment);
            fragment.A0b = false;
            fragment.A0W = false;
            if (A0C(fragment)) {
                this.A0F = true;
            }
        }
    }

    public final void A0e(boolean z) {
        A0A(this, z);
        while (true) {
            ArrayList<aC> arrayList = this.A0A;
            ArrayList<Boolean> arrayList2 = this.A09;
            ArrayList<AnonymousClass9Z> arrayList3 = this.A0Q;
            synchronized (arrayList3) {
                if (arrayList3.isEmpty()) {
                    break;
                }
                int size = arrayList3.size();
                boolean z2 = false;
                for (int i = 0; i < size; i++) {
                    z2 |= arrayList3.get(i).A2E(arrayList, arrayList2);
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
}
