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
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import com.oculus.alpenglow.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0Cj  reason: invalid class name */
public abstract class AnonymousClass0Cj {
    public int A00 = -1;
    public AnonymousClass01R A01;
    public AnonymousClass0MN A02;
    @Nullable
    public AnonymousClass0MN A03;
    public AnonymousClass0CX A04;
    public AbstractC03650cs<?> A05;
    public C03590cl A06;
    public Runnable A07 = new AnonymousClass0Ce(this);
    public ArrayList<C03670cv> A08;
    public ArrayList<Boolean> A09;
    public ArrayList<C03670cv> A0A;
    public ConcurrentHashMap<AnonymousClass0MN, HashSet<AnonymousClass091>> A0B = new ConcurrentHashMap<>();
    public boolean A0C;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public boolean A0H;
    public C00940Ca A0I = new C03620cp(this);
    public ArrayList<AnonymousClass0MN> A0J;
    public ArrayList<AnonymousClass0MN> A0K;
    public final AnonymousClass01Q A0L = new C03640cr(this);
    public final LayoutInflater$Factory2C00950Cb A0M = new LayoutInflater$Factory2C00950Cb(this);
    public final AnonymousClass0Cd A0N = new AnonymousClass0Cd(this);
    public final AnonymousClass0Cr A0O = new AnonymousClass0Cr();
    public final AnonymousClass0D4 A0P = new C03630cq(this);
    public final ArrayList<AnonymousClass0Ci> A0Q = new ArrayList<>();
    public final AtomicInteger A0R = new AtomicInteger();

    public static void A02(AnonymousClass0Cj r1) {
        r1.A0D = false;
        r1.A09.clear();
        r1.A0A.clear();
    }

    /* JADX INFO: finally extract failed */
    public static void A05(AnonymousClass0Cj r6, int i) {
        try {
            r6.A0D = true;
            AnonymousClass0Cr r5 = r6.A0O;
            Iterator<AnonymousClass0MN> it = r5.A00.iterator();
            while (it.hasNext()) {
                AnonymousClass0Cq r0 = r5.A01.get(it.next().A0P);
                if (r0 != null) {
                    r0.A00 = i;
                }
            }
            for (AnonymousClass0Cq r02 : r5.A01.values()) {
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

    public static final boolean A0D(AnonymousClass0Cj r7) {
        boolean z;
        int size;
        r7.A0e(false);
        A0A(r7, true);
        AnonymousClass0MN r2 = r7.A03;
        if (r2 != null) {
            if (r2.A0F == null) {
                throw new IllegalStateException("Fragment " + r2 + " has not been attached yet.");
            } else if (A0D(r2.A0G)) {
                return true;
            }
        }
        ArrayList<C03670cv> arrayList = r7.A0A;
        ArrayList<Boolean> arrayList2 = r7.A09;
        ArrayList<C03670cv> arrayList3 = r7.A08;
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

    private final boolean A0j(@Nullable AnonymousClass0MN r4) {
        if (r4 != null) {
            AnonymousClass0Cj r1 = r4.A0H;
            if (!r4.equals(r1.A03) || !A0j(r1.A02)) {
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
            Iterator<AnonymousClass01N> it = this.A0L.A00.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.A01 = null;
        }
    }

    public final void A0L() {
        this.A0G = false;
        this.A0H = false;
        for (AnonymousClass0MN r0 : this.A0O.A01()) {
            if (r0 != null) {
                r0.A0G.A0L();
            }
        }
    }

    private void A00() {
        int i;
        if (!this.A0B.isEmpty()) {
            for (AnonymousClass0MN r1 : this.A0B.keySet()) {
                A01(r1);
                AnonymousClass0CM r0 = r1.A0C;
                if (r0 == null) {
                    i = 0;
                } else {
                    i = r0.A01;
                }
                A0X(r1, i);
            }
        }
    }

    private void A01(@NonNull AnonymousClass0MN r5) {
        HashSet<AnonymousClass091> hashSet = this.A0B.get(r5);
        if (hashSet != null) {
            Iterator<AnonymousClass091> it = hashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass091 next = it.next();
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
            A06(this, r5);
            this.A0B.remove(r5);
        }
    }

    public static void A03(AnonymousClass0Cj r3) {
        for (AnonymousClass0MN r1 : r3.A0O.A00()) {
            if (r1 != null && r1.A0S) {
                if (r3.A0D) {
                    r3.A0E = true;
                } else {
                    r1.A0S = false;
                    r3.A0X(r1, r3.A00);
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
    public static void A04(X.AnonymousClass0Cj r3) {
        /*
            java.util.ArrayList<X.0Ci> r1 = r3.A0Q
            monitor-enter(r1)
            boolean r0 = r1.isEmpty()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r0 != 0) goto L_0x0010
            X.01Q r0 = r3.A0L     // Catch:{ all -> 0x002a }
            r0.A01 = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x0010:
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            X.01Q r1 = r3.A0L
            java.util.ArrayList<X.0cv> r0 = r3.A08
            if (r0 == 0) goto L_0x0028
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0028
            X.0MN r0 = r3.A02
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Cj.A04(X.0Cj):void");
    }

    public static void A06(@NonNull AnonymousClass0Cj r3, AnonymousClass0MN r4) {
        A05(r4.A0G, 1);
        r4.A05 = 1;
        r4.A0R = false;
        r4.A0R = true;
        new C03460cT(r4, r4.getViewModelStore()).A00();
        r4.A0a = false;
        r3.A0N.A00(r4);
        r4.A0B = null;
        r4.A0I = null;
        r4.A00.A02(null);
        r4.A0X = false;
    }

    public static void A07(@Nullable AnonymousClass0Cj r1, AnonymousClass0MN r2) {
        if (r2 != null && r2.equals(r1.A0G(r2.A0P))) {
            boolean A0j = r2.A0H.A0j(r2);
            Boolean bool = r2.A0M;
            if (bool == null || bool.booleanValue() != A0j) {
                r2.A0M = Boolean.valueOf(A0j);
                AnonymousClass0Cj r12 = r2.A0G;
                A04(r12);
                A07(r12, r12.A03);
            }
        }
    }

    public static void A08(@NonNull AnonymousClass0Cj r1, AnonymousClass0MN r2) {
        ViewGroup viewGroup;
        int i;
        if (r2.A03 > 0 && r1.A04.A01()) {
            View A002 = r1.A04.A00(r2.A03);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null) {
                if (viewGroup.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    viewGroup.setTag(R.id.visible_removing_fragment_view_tag, r2);
                }
                AnonymousClass0MN r12 = (AnonymousClass0MN) viewGroup.getTag(R.id.visible_removing_fragment_view_tag);
                AnonymousClass0CM r0 = r2.A0C;
                if (r0 == null) {
                    i = 0;
                } else {
                    i = r0.A00;
                }
                r12.A05(i);
            }
        }
    }

    public static void A0A(AnonymousClass0Cj r2, boolean z) {
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

    public static boolean A0C(@NonNull AnonymousClass0MN r1) {
        for (AnonymousClass0MN r0 : r1.A0G.A0O.A00()) {
            if (r0 != null && A0C(r0)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private final AnonymousClass0MN A0G(@NonNull String str) {
        AnonymousClass0Cq r0 = this.A0O.A01.get(str);
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    private final void A0M(int i, boolean z) {
        AbstractC03650cs<?> r2;
        if (this.A05 == null && i != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.A00) {
            this.A00 = i;
            AnonymousClass0Cr r22 = this.A0O;
            for (AnonymousClass0MN r0 : r22.A01()) {
                A0U(r0);
            }
            for (AnonymousClass0MN r02 : r22.A00()) {
                if (r02 != null) {
                    A0U(r02);
                }
            }
            A03(this);
            if (this.A0F && (r2 = this.A05) != null && this.A00 == 4) {
                r2.A04();
                this.A0F = false;
            }
        }
    }

    private final void A0R(@NonNull AnonymousClass0MN r2) {
        if (r2.A0T) {
            r2.A0T = false;
            if (!r2.A0Q) {
                this.A0O.A02(r2);
                if (A0C(r2)) {
                    this.A0F = true;
                }
            }
        }
    }

    private final void A0S(@NonNull AnonymousClass0MN r4) {
        if (!r4.A0T) {
            r4.A0T = true;
            if (r4.A0Q) {
                ArrayList<AnonymousClass0MN> arrayList = this.A0O.A00;
                synchronized (arrayList) {
                    arrayList.remove(r4);
                }
                r4.A0Q = false;
                if (A0C(r4)) {
                    this.A0F = true;
                }
                A08(this, r4);
            }
        }
    }

    private final void A0U(@NonNull AnonymousClass0MN r4) {
        AnonymousClass0Cr r0 = this.A0O;
        if (r0.A01.containsKey(r4.A0P)) {
            A0X(r4, this.A00);
            if (r4.A0W) {
                if (r4.A0Q && A0C(r4)) {
                    this.A0F = true;
                }
                r4.A0W = false;
            }
        }
    }

    private final void A0V(@NonNull AnonymousClass0MN r4) {
        boolean z = false;
        if (r4.A02 > 0) {
            z = true;
        }
        boolean z2 = !z;
        if (!r4.A0T || z2) {
            ArrayList<AnonymousClass0MN> arrayList = this.A0O.A00;
            synchronized (arrayList) {
                arrayList.remove(r4);
            }
            r4.A0Q = false;
            if (A0C(r4)) {
                this.A0F = true;
            }
            r4.A0b = true;
            A08(this, r4);
        }
    }

    private final void A0W(@Nullable AnonymousClass0MN r3) {
        if (r3 == null || (r3.equals(A0G(r3.A0P)) && (r3.A0F == null || r3.A0H == this))) {
            AnonymousClass0MN r0 = this.A03;
            this.A03 = r3;
            A07(this, r0);
            A07(this, this.A03);
            return;
        }
        throw new IllegalArgumentException("Fragment " + r3 + " is not an active fragment of FragmentManager " + this);
    }

    private final void A0Y(@NonNull AnonymousClass0MN r3, @NonNull AnonymousClass0DX r4) {
        if (!r3.equals(A0G(r3.A0P)) || !(r3.A0F == null || r3.A0H == this)) {
            throw new IllegalArgumentException("Fragment " + r3 + " is not an active fragment of FragmentManager " + this);
        }
        r3.A0J = r4;
    }

    private final void A0Z(@NonNull AnonymousClass0MN r3, boolean z) {
        ViewGroup viewGroup;
        if (r3.A03 > 0 && this.A04.A01()) {
            View A002 = this.A04.A00(r3.A03);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null && (viewGroup instanceof AnonymousClass0CY)) {
                ((AnonymousClass0CY) viewGroup).A00 = !z;
            }
        }
    }

    @Nullable
    public final AnonymousClass0MN A0F(@IdRes int i) {
        AnonymousClass0Cr r4 = this.A0O;
        ArrayList<AnonymousClass0MN> arrayList = r4.A00;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass0MN r2 = arrayList.get(size);
                if (r2 != null && r2.A04 == i) {
                    return r2;
                }
            } else {
                for (AnonymousClass0Cq r0 : r4.A01.values()) {
                    if (r0 != null) {
                        AnonymousClass0MN r22 = r0.A01;
                        if (r22.A04 == i) {
                            return r22;
                        }
                    }
                }
                return null;
            }
        }
    }

    public final AnonymousClass0MN A0H(@NonNull String str) {
        for (AnonymousClass0Cq r0 : this.A0O.A01.values()) {
            if (r0 != null) {
                AnonymousClass0MN r1 = r0.A01;
                if (str.equals(r1.A0P) || (r1 = r1.A0G.A0H(str)) != null) {
                    return r1;
                }
            }
        }
        return null;
    }

    @NonNull
    public final C00940Ca A0I() {
        AnonymousClass0MN r0 = this.A02;
        if (r0 != null) {
            return r0.A0H.A0I();
        }
        return this.A0I;
    }

    public final void A0K() {
        for (AnonymousClass0MN r0 : this.A0O.A01()) {
            if (r0 != null) {
                r0.onLowMemory();
                r0.A0G.A0K();
            }
        }
    }

    public final void A0N(@NonNull Configuration configuration) {
        for (AnonymousClass0MN r0 : this.A0O.A01()) {
            if (r0 != null) {
                r0.onConfigurationChanged(configuration);
                r0.A0G.A0N(configuration);
            }
        }
    }

    public final void A0O(@Nullable Parcelable parcelable) {
        AnonymousClass0MN r0;
        AnonymousClass0MN r02;
        AnonymousClass0Cq r5;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.A02 != null) {
                AnonymousClass0Cr r4 = this.A0O;
                HashMap<String, AnonymousClass0Cq> hashMap = r4.A01;
                hashMap.clear();
                Iterator<FragmentState> it = fragmentManagerState.A02.iterator();
                while (it.hasNext()) {
                    FragmentState next = it.next();
                    if (next != null) {
                        AnonymousClass0MN r1 = this.A06.A02.get(next.A07);
                        if (r1 != null) {
                            r5 = new AnonymousClass0Cq(this.A0N, r1, next);
                        } else {
                            r5 = new AnonymousClass0Cq(this.A0N, this.A05.A01.getClassLoader(), A0I(), next);
                        }
                        AnonymousClass0MN r12 = r5.A01;
                        r12.A0H = this;
                        r5.A00(this.A05.A01.getClassLoader());
                        hashMap.put(r12.A0P, r5);
                        r5.A00 = this.A00;
                    }
                }
                for (AnonymousClass0MN r13 : this.A06.A02.values()) {
                    if (!hashMap.containsKey(r13.A0P)) {
                        A0X(r13, 1);
                        r13.A0b = true;
                        A0X(r13, -1);
                    }
                }
                ArrayList<String> arrayList = fragmentManagerState.A03;
                r4.A00.clear();
                if (arrayList != null) {
                    for (String str : arrayList) {
                        AnonymousClass0Cq r03 = hashMap.get(str);
                        if (r03 == null || (r02 = r03.A01) == null) {
                            throw new IllegalStateException(AnonymousClass006.A07("No instantiated fragment for (", str, ")"));
                        }
                        r4.A02(r02);
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
                        C03670cv r2 = new C03670cv(this);
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            int[] iArr = backStackState.A0D;
                            if (i2 >= iArr.length) {
                                break;
                            }
                            C01000Cx r10 = new C01000Cx();
                            int i4 = i2 + 1;
                            r10.A00 = iArr[i2];
                            String str2 = backStackState.A07.get(i3);
                            if (str2 != null) {
                                r0 = A0G(str2);
                            } else {
                                r0 = null;
                            }
                            r10.A05 = r0;
                            r10.A07 = AnonymousClass0DX.values()[backStackState.A0C[i3]];
                            r10.A06 = AnonymousClass0DX.values()[backStackState.A0B[i3]];
                            int i5 = i4 + 1;
                            int i6 = iArr[i4];
                            r10.A01 = i6;
                            int i7 = i5 + 1;
                            int i8 = iArr[i5];
                            r10.A02 = i8;
                            int i9 = i7 + 1;
                            int i10 = iArr[i7];
                            r10.A03 = i10;
                            i2 = i9 + 1;
                            int i11 = iArr[i9];
                            r10.A04 = i11;
                            r2.A02 = i6;
                            r2.A03 = i8;
                            r2.A04 = i10;
                            r2.A05 = i11;
                            r2.A0A.add(r10);
                            r10.A01 = i6;
                            r10.A02 = i8;
                            r10.A03 = i10;
                            r10.A04 = i11;
                            i3++;
                        }
                        r2.A06 = backStackState.A03;
                        r2.A09 = backStackState.A06;
                        r2.A00 = backStackState.A02;
                        r2.A0D = true;
                        ((AbstractC01010Cy) r2).A01 = backStackState.A01;
                        r2.A08 = backStackState.A05;
                        ((AbstractC01010Cy) r2).A00 = backStackState.A00;
                        r2.A07 = backStackState.A04;
                        r2.A0B = backStackState.A08;
                        r2.A0C = backStackState.A09;
                        r2.A0E = backStackState.A0A;
                        r2.A02(1);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            PrintWriter printWriter = new PrintWriter(new AnonymousClass0DI());
                            r2.A03("  ", printWriter, false);
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
                    AnonymousClass0MN A0G2 = A0G(str3);
                    this.A03 = A0G2;
                    A07(this, A0G2);
                }
            }
        }
    }

    public final void A0P(@NonNull Menu menu) {
        if (this.A00 >= 1) {
            for (AnonymousClass0MN r1 : this.A0O.A01()) {
                if (r1 != null && !r1.A0V) {
                    r1.A0G.A0P(menu);
                }
            }
        }
    }

    public final void A0T(@NonNull AnonymousClass0MN r4) {
        AnonymousClass0Cr r1 = this.A0O;
        String str = r4.A0P;
        HashMap<String, AnonymousClass0Cq> hashMap = r1.A01;
        if (!hashMap.containsKey(str)) {
            AnonymousClass0Cq r12 = new AnonymousClass0Cq(this.A0N, r4);
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
    public final void A0X(@androidx.annotation.NonNull X.AnonymousClass0MN r15, int r16) {
        /*
        // Method dump skipped, instructions count: 1220
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Cj.A0X(X.0MN, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v15, resolved type: X.0f4 */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A0a(@NonNull AbstractC03650cs<?> r6, @NonNull AnonymousClass0CX r7, @Nullable AnonymousClass0MN r8) {
        C03590cl r0;
        if (this.A05 == null) {
            this.A05 = r6;
            this.A04 = r7;
            this.A02 = r8;
            if (r8 != null) {
                A04(this);
            }
            if (r6 instanceof AnonymousClass0f4) {
                AnonymousClass0f4 r02 = (AnonymousClass0f4) r6;
                AnonymousClass01R onBackPressedDispatcher = r02.getOnBackPressedDispatcher();
                this.A01 = onBackPressedDispatcher;
                AnonymousClass0MN r03 = r02;
                if (r8 != null) {
                    r03 = r8;
                }
                AnonymousClass01Q r3 = this.A0L;
                AnonymousClass0DY lifecycle = r03.getLifecycle();
                if (lifecycle.A05() != AnonymousClass0DX.DESTROYED) {
                    r3.A00.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(onBackPressedDispatcher, lifecycle, r3));
                }
            }
            if (r8 != null) {
                C03590cl r32 = r8.A0H.A06;
                HashMap<String, C03590cl> hashMap = r32.A01;
                C03590cl r1 = hashMap.get(r8.A0P);
                if (r1 == null) {
                    r1 = new C03590cl(r32.A04);
                    hashMap.put(r8.A0P, r1);
                }
                this.A06 = r1;
                return;
            }
            if (r6 instanceof AbstractC01160Dt) {
                r0 = (C03590cl) new C01140Dr(((AbstractC01160Dt) r6).getViewModelStore(), C03590cl.A05).A00(C03590cl.class);
            } else {
                r0 = new C03590cl(false);
            }
            this.A06 = r0;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    public final void A0c(boolean z) {
        for (AnonymousClass0MN r0 : this.A0O.A01()) {
            if (r0 != null) {
                r0.A0G.A0c(z);
            }
        }
    }

    public final void A0d(boolean z) {
        for (AnonymousClass0MN r0 : this.A0O.A01()) {
            if (r0 != null) {
                r0.A0G.A0d(z);
            }
        }
    }

    public final boolean A0f(@NonNull Menu menu) {
        boolean z = false;
        if (this.A00 >= 1) {
            for (AnonymousClass0MN r2 : this.A0O.A01()) {
                if (r2 != null && !r2.A0V && (false || r2.A0G.A0f(menu))) {
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
        ArrayList<AnonymousClass0MN> arrayList = null;
        boolean z = false;
        for (AnonymousClass0MN r1 : this.A0O.A01()) {
            if (r1 != null && !r1.A0V && (r1.A0G.A0g(menu, menuInflater) || false)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(r1);
                z = true;
            }
        }
        if (this.A0J != null) {
            while (true) {
                ArrayList<AnonymousClass0MN> arrayList2 = this.A0J;
                if (i >= arrayList2.size()) {
                    break;
                }
                AnonymousClass0MN r0 = arrayList2.get(i);
                if (arrayList != null) {
                    arrayList.contains(r0);
                }
                i++;
            }
        }
        this.A0J = arrayList;
        return z;
    }

    public final boolean A0h(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (AnonymousClass0MN r1 : this.A0O.A01()) {
                if (!(r1 == null || r1.A0V || !r1.A0G.A0h(menuItem))) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean A0i(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (AnonymousClass0MN r1 : this.A0O.A01()) {
                if (!(r1 == null || r1.A0V || !r1.A0G.A0i(menuItem))) {
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
        AnonymousClass0MN r1 = this.A02;
        if (r1 != null) {
            sb.append(r1.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(r1);
        } else {
            AbstractC03650cs<?> r12 = this.A05;
            sb.append(r12.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(r12);
        }
        sb.append(Integer.toHexString(identityHashCode));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/ArrayList<LX/0cv;>;Ljava/util/ArrayList<Ljava/lang/Boolean;>;)V */
    public static void A09(@NonNull AnonymousClass0Cj r4, @NonNull ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!((AbstractC01010Cy) arrayList.get(i)).A0E) {
                    if (i2 != i) {
                        r4.A0B(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (((Boolean) arrayList2.get(i)).booleanValue()) {
                        while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((AbstractC01010Cy) arrayList.get(i2)).A0E) {
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

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0123  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable A0E() {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Cj.A0E():android.os.Parcelable");
    }

    public final void A0Q(@NonNull AnonymousClass0MN r2) {
        A0T(r2);
        if (!r2.A0T) {
            this.A0O.A02(r2);
            r2.A0b = false;
            r2.A0W = false;
            if (A0C(r2)) {
                this.A0F = true;
            }
        }
    }

    public final void A0e(boolean z) {
        A0A(this, z);
        while (true) {
            ArrayList<C03670cv> arrayList = this.A0A;
            ArrayList<Boolean> arrayList2 = this.A09;
            ArrayList<AnonymousClass0Ci> arrayList3 = this.A0Q;
            synchronized (arrayList3) {
                if (arrayList3.isEmpty()) {
                    break;
                }
                int size = arrayList3.size();
                boolean z2 = false;
                for (int i = 0; i < size; i++) {
                    z2 |= arrayList3.get(i).A2r(arrayList, arrayList2);
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

    private void A0B(@NonNull ArrayList<C03670cv> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i, int i2) {
        int i3;
        AnonymousClass0Cj r1;
        int i4 = i;
        boolean z = arrayList.get(i4).A0E;
        ArrayList<AnonymousClass0MN> arrayList3 = this.A0K;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            this.A0K = arrayList3;
        } else {
            arrayList3.clear();
        }
        AnonymousClass0Cr r0 = this.A0O;
        arrayList3.addAll(r0.A01());
        AnonymousClass0MN r14 = this.A03;
        boolean z2 = false;
        for (int i5 = i4; i5 < i2; i5++) {
            C03670cv r6 = arrayList.get(i5);
            if (!arrayList2.get(i5).booleanValue()) {
                ArrayList<AnonymousClass0MN> arrayList4 = this.A0K;
                int i6 = 0;
                while (true) {
                    ArrayList<C01000Cx> arrayList5 = r6.A0A;
                    if (i6 < arrayList5.size()) {
                        C01000Cx r13 = arrayList5.get(i6);
                        int i7 = r13.A00;
                        if (i7 != 1) {
                            if (i7 == 2) {
                                AnonymousClass0MN r3 = r13.A05;
                                int i8 = r3.A03;
                                boolean z3 = false;
                                for (int size = arrayList4.size() - 1; size >= 0; size--) {
                                    AnonymousClass0MN r12 = arrayList4.get(size);
                                    if (r12.A03 == i8) {
                                        if (r12 == r3) {
                                            z3 = true;
                                        } else {
                                            if (r12 == r14) {
                                                r6.A0A.add(i6, new C01000Cx(9, r12));
                                                i6++;
                                                r14 = null;
                                            }
                                            C01000Cx r15 = new C01000Cx(3, r12);
                                            r15.A01 = r13.A01;
                                            r15.A03 = r13.A03;
                                            r15.A02 = r13.A02;
                                            r15.A04 = r13.A04;
                                            r6.A0A.add(i6, r15);
                                            arrayList4.remove(r12);
                                            i6++;
                                        }
                                    }
                                }
                                if (z3) {
                                    r6.A0A.remove(i6);
                                    i6--;
                                } else {
                                    r13.A00 = 1;
                                    arrayList4.add(r3);
                                }
                            } else if (i7 == 3 || i7 == 6) {
                                arrayList4.remove(r13.A05);
                                if (r13.A05 == r14) {
                                    r6.A0A.add(i6, new C01000Cx(9, r13.A05));
                                    i6++;
                                    r14 = null;
                                }
                            } else if (i7 != 7) {
                                if (i7 == 8) {
                                    arrayList5.add(i6, new C01000Cx(9, r14));
                                    i6++;
                                    r14 = r13.A05;
                                }
                            }
                            i6++;
                        }
                        arrayList4.add(r13.A05);
                        i6++;
                    }
                }
            } else {
                ArrayList<AnonymousClass0MN> arrayList6 = this.A0K;
                for (int size2 = r6.A0A.size() - 1; size2 >= 0; size2--) {
                    C01000Cx r2 = r6.A0A.get(size2);
                    int i9 = r2.A00;
                    if (i9 != 1) {
                        if (i9 != 3) {
                            switch (i9) {
                                case 8:
                                    r14 = null;
                                    break;
                                case 9:
                                    r14 = r2.A05;
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
            AnonymousClass0D6.A02(this.A05.A01, this.A04, arrayList, arrayList2, i4, i2, false, this.A0P);
        }
        for (int i10 = i4; i10 < i2; i10++) {
            C03670cv r132 = arrayList.get(i10);
            boolean z4 = true;
            if (arrayList2.get(i10).booleanValue()) {
                r132.A02(-1);
                if (i10 != i2 - 1) {
                    z4 = false;
                }
                for (int size3 = r132.A0A.size() - 1; size3 >= 0; size3--) {
                    C01000Cx r122 = r132.A0A.get(size3);
                    AnonymousClass0MN r4 = r122.A05;
                    if (r4 != null) {
                        int i11 = r132.A06;
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
                        if (!(r4.A0C == null && c == 0)) {
                            AnonymousClass0MN.A00(r4);
                        }
                    }
                    i3 = r122.A00;
                    switch (i3) {
                        case 1:
                            r4.A05(r122.A04);
                            AnonymousClass0Cj r02 = r132.A01;
                            r02.A0Z(r4, true);
                            r02.A0V(r4);
                            break;
                        case 3:
                            r4.A05(r122.A03);
                            r132.A01.A0Q(r4);
                            break;
                        case 4:
                            r4.A05(r122.A03);
                            if (r4.A0V) {
                                r4.A0V = false;
                                r4.A0W = !r4.A0W;
                                break;
                            }
                            break;
                        case 5:
                            r4.A05(r122.A04);
                            AnonymousClass0Cj r16 = r132.A01;
                            r16.A0Z(r4, true);
                            if (!r4.A0V) {
                                r4.A0V = true;
                                r4.A0W = !r4.A0W;
                                A08(r16, r4);
                                break;
                            }
                            break;
                        case 6:
                            r4.A05(r122.A03);
                            r132.A01.A0R(r4);
                            break;
                        case 7:
                            r4.A05(r122.A04);
                            AnonymousClass0Cj r03 = r132.A01;
                            r03.A0Z(r4, true);
                            r03.A0S(r4);
                            break;
                        case 8:
                            r132.A01.A0W(null);
                            break;
                        case 9:
                            r132.A01.A0W(r4);
                            break;
                        case 10:
                            r132.A01.A0Y(r4, r122.A07);
                            break;
                    }
                    if (!(r132.A0E || r122.A00 == 3 || r4 == null)) {
                        r132.A01.A0U(r4);
                    }
                }
                if (!r132.A0E) {
                    if (!z4) {
                    }
                    AnonymousClass0Cj r17 = r132.A01;
                    r17.A0M(r17.A00, true);
                }
            } else {
                r132.A02(1);
                int size4 = r132.A0A.size();
                for (int i12 = 0; i12 < size4; i12++) {
                    C01000Cx r152 = r132.A0A.get(i12);
                    AnonymousClass0MN r42 = r152.A05;
                    if (r42 != null) {
                        int i13 = r132.A06;
                        if (!(r42.A0C == null && i13 == 0)) {
                            AnonymousClass0MN.A00(r42);
                        }
                    }
                    i3 = r152.A00;
                    switch (i3) {
                        case 1:
                            r42.A05(r152.A01);
                            r1 = r132.A01;
                            r1.A0Z(r42, false);
                            r1.A0Q(r42);
                            break;
                        case 3:
                            r42.A05(r152.A02);
                            r1 = r132.A01;
                            r1.A0V(r42);
                            break;
                        case 4:
                            r42.A05(r152.A02);
                            r1 = r132.A01;
                            if (!r42.A0V) {
                                r42.A0V = true;
                                r42.A0W = !r42.A0W;
                                A08(r1, r42);
                                break;
                            }
                            break;
                        case 5:
                            r42.A05(r152.A01);
                            r1 = r132.A01;
                            r1.A0Z(r42, false);
                            if (r42.A0V) {
                                r42.A0V = false;
                                r42.A0W = !r42.A0W;
                                break;
                            }
                            break;
                        case 6:
                            r42.A05(r152.A02);
                            r1 = r132.A01;
                            r1.A0S(r42);
                            break;
                        case 7:
                            r42.A05(r152.A01);
                            r1 = r132.A01;
                            r1.A0Z(r42, false);
                            r1.A0R(r42);
                            break;
                        case 8:
                            r1 = r132.A01;
                            r1.A0W(r42);
                            break;
                        case 9:
                            r1 = r132.A01;
                            r1.A0W(null);
                            break;
                        case 10:
                            r1 = r132.A01;
                            r1.A0Y(r42, r152.A06);
                            break;
                    }
                    if (!(r132.A0E || r152.A00 == 1 || r42 == null)) {
                        r1.A0U(r42);
                    }
                }
                if (r132.A0E) {
                }
                AnonymousClass0Cj r172 = r132.A01;
                r172.A0M(r172.A00, true);
            }
            throw new IllegalArgumentException(AnonymousClass006.A01("Unknown cmd: ", i3));
        }
        if (z) {
            AnonymousClass061 r62 = new AnonymousClass061();
            int i14 = this.A00;
            if (i14 >= 1) {
                int min = Math.min(i14, 3);
                for (AnonymousClass0MN r18 : r0.A01()) {
                    if (r18.A05 < min) {
                        A0X(r18, min);
                    }
                }
            }
            for (int i15 = i2 - 1; i15 >= i4; i15--) {
                C03670cv r5 = arrayList.get(i15);
                arrayList2.get(i15);
                int i16 = 0;
                while (true) {
                    ArrayList<C01000Cx> arrayList7 = r5.A0A;
                    if (i16 < arrayList7.size()) {
                        arrayList7.get(i16);
                        i16++;
                    }
                }
            }
            int size5 = r62.size();
            for (int i17 = 0; i17 < size5; i17++) {
                AnonymousClass0MN r19 = (AnonymousClass0MN) r62.A03[i17];
                if (!r19.A0Q) {
                    r19.A04();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else {
            z = false;
        }
        if (i2 != i4 && z) {
            if (this.A00 >= 1) {
                AnonymousClass0D6.A02(this.A05.A01, this.A04, arrayList, arrayList2, i4, i2, true, this.A0P);
            }
            A0M(this.A00, true);
        }
        while (i4 < i2) {
            C03670cv r110 = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue() && r110.A00 >= 0) {
                r110.A00 = -1;
            }
            i4++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x02a7 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0b(@androidx.annotation.NonNull java.lang.String r8, @androidx.annotation.Nullable java.io.FileDescriptor r9, @androidx.annotation.NonNull java.io.PrintWriter r10, @androidx.annotation.Nullable java.lang.String[] r11) {
        /*
        // Method dump skipped, instructions count: 839
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Cj.A0b(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }
}
