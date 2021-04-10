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
import com.facebook.FacebookSdk;
import com.oculus.horizon.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.09a  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC003209a {
    public int A00 = -1;
    public AnonymousClass01Q A01;
    public Fragment A02;
    @Nullable
    public Fragment A03;
    public AnonymousClass09N A04;
    public AnonymousClass0s9<?> A05;
    public C07370s0 A06;
    public Runnable A07 = new AnonymousClass09U(this);
    public ArrayList<AnonymousClass0sD> A08;
    public ArrayList<AnonymousClass0s4> A09;
    public ArrayList<Boolean> A0A;
    public ArrayList<AnonymousClass0sD> A0B;
    public ConcurrentHashMap<Fragment, HashSet<AnonymousClass05d>> A0C = new ConcurrentHashMap<>();
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public boolean A0H;
    public boolean A0I;
    public AnonymousClass09Q A0J = new AnonymousClass0s6(this);
    public ArrayList<Fragment> A0K;
    public ArrayList<Fragment> A0L;
    public final AnonymousClass01P A0M = new AnonymousClass0s8(this);
    public final AnonymousClass09R A0N = new AnonymousClass09R(this);
    public final AnonymousClass09T A0O = new AnonymousClass09T(this);
    public final C003709i A0P = new C003709i();
    public final ArrayList<AnonymousClass09Z> A0Q = new ArrayList<>();
    public final AtomicInteger A0R = new AtomicInteger();
    public final AbstractC004709v A0S = new AnonymousClass0s7(this);

    private void A01() {
        this.A0E = false;
        this.A0A.clear();
        this.A0B.clear();
    }

    /* JADX INFO: finally extract failed */
    public static void A07(AbstractC003209a r6, int i) {
        try {
            r6.A0E = true;
            C003709i r5 = r6.A0P;
            Iterator<Fragment> it = r5.A00.iterator();
            while (it.hasNext()) {
                C003609h r0 = r5.A01.get(it.next().mWho);
                if (r0 != null) {
                    r0.A00 = i;
                }
            }
            for (C003609h r02 : r5.A01.values()) {
                if (r02 != null) {
                    r02.A00 = i;
                }
            }
            r6.A0Q(i, false);
            r6.A0E = false;
            r6.A0n(true);
        } catch (Throwable th) {
            r6.A0E = false;
            throw th;
        }
    }

    public final void A0N() {
        this.A0D = true;
        A0n(true);
        A02();
        A07(this, -1);
        this.A05 = null;
        this.A04 = null;
        this.A02 = null;
        if (this.A01 != null) {
            Iterator<AnonymousClass01M> it = this.A0M.A00.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.A01 = null;
        }
    }

    public final void A0P() {
        this.A0H = false;
        this.A0I = false;
        for (Fragment fragment : this.A0P.A01()) {
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public final boolean A0o() {
        A0n(false);
        A0E(true);
        Fragment fragment = this.A03;
        if (fragment != null && fragment.getChildFragmentManager().A0o()) {
            return true;
        }
        ArrayList<AnonymousClass0sD> arrayList = this.A0B;
        ArrayList<Boolean> arrayList2 = this.A0A;
        boolean A0u = A0u(arrayList, arrayList2, -1, 0);
        if (A0u) {
            this.A0E = true;
            try {
                A0C(arrayList, arrayList2);
            } finally {
                A01();
            }
        }
        A06(this);
        if (this.A0F) {
            this.A0F = false;
            A03();
        }
        this.A0P.A01.values().removeAll(Collections.singleton(null));
        return A0u;
    }

    public final boolean A0t(@Nullable Fragment fragment) {
        if (fragment != null) {
            AbstractC003209a r1 = fragment.mFragmentManager;
            if (!fragment.equals(r1.A03) || !A0t(r1.A02)) {
                return false;
            }
        }
        return true;
    }

    private void A02() {
        if (!this.A0C.isEmpty()) {
            for (Fragment fragment : this.A0C.keySet()) {
                A05(fragment);
                A0e(fragment, fragment.getStateAfterAnimating());
            }
        }
    }

    private void A03() {
        for (Fragment fragment : this.A0P.A00()) {
            if (fragment != null && fragment.mDeferStart) {
                if (this.A0E) {
                    this.A0F = true;
                } else {
                    fragment.mDeferStart = false;
                    A0e(fragment, this.A00);
                }
            }
        }
    }

    private void A04(@NonNull C000502c<Fragment> r5) {
        int i = this.A00;
        if (i >= 1) {
            int min = Math.min(i, 3);
            for (Fragment fragment : this.A0P.A01()) {
                if (fragment.mState < min) {
                    A0e(fragment, min);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        r5.add(fragment);
                    }
                }
            }
        }
    }

    private void A05(@NonNull Fragment fragment) {
        HashSet<AnonymousClass05d> hashSet = this.A0C.get(fragment);
        if (hashSet != null) {
            Iterator<AnonymousClass05d> it = hashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass05d next = it.next();
                synchronized (next) {
                    if (!next.A02) {
                        next.A02 = true;
                        next.A01 = true;
                        AnonymousClass05c r0 = next.A00;
                        if (r0 != null) {
                            try {
                                r0.A5m();
                            } catch (Throwable th) {
                                th = th;
                            }
                        }
                        synchronized (next) {
                            next.A01 = false;
                            next.notifyAll();
                        }
                    }
                }
            }
            hashSet.clear();
            A08(this, fragment);
            this.A0C.remove(fragment);
            return;
        }
        return;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        if (r0 == null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0.size() <= 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r3.A0t(r3.A02) == false) goto L_0x0028;
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
        r1 = r3.A0M;
        r0 = r3.A08;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.AbstractC003209a r3) {
        /*
            java.util.ArrayList<X.09Z> r1 = r3.A0Q
            monitor-enter(r1)
            boolean r0 = r1.isEmpty()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r0 != 0) goto L_0x0010
            X.01P r0 = r3.A0M     // Catch:{ all -> 0x002a }
            r0.A01 = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x0010:
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            X.01P r1 = r3.A0M
            java.util.ArrayList<X.0sD> r0 = r3.A08
            if (r0 == 0) goto L_0x0028
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0028
            androidx.fragment.app.Fragment r0 = r3.A02
            boolean r0 = r3.A0t(r0)
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
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A06(X.09a):void");
    }

    public static void A09(@Nullable AbstractC003209a r1, Fragment fragment) {
        if (fragment != null && fragment.equals(r1.A0I(fragment.mWho))) {
            fragment.performPrimaryNavigationFragmentChanged();
        }
    }

    public static void A0A(@NonNull AbstractC003209a r1, Fragment fragment) {
        ViewGroup viewGroup;
        if (fragment.mContainerId > 0 && r1.A04.A01()) {
            View A002 = r1.A04.A00(fragment.mContainerId);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null) {
                if (viewGroup.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    viewGroup.setTag(R.id.visible_removing_fragment_view_tag, fragment);
                }
                ((Fragment) viewGroup.getTag(R.id.visible_removing_fragment_view_tag)).setNextAnim(fragment.getNextAnim());
            }
        }
    }

    private void A0B(@Nullable ArrayList<AnonymousClass0sD> arrayList, @Nullable ArrayList<Boolean> arrayList2) {
        boolean z;
        AnonymousClass0sD r1;
        int indexOf;
        int indexOf2;
        ArrayList<AnonymousClass0s4> arrayList3 = this.A09;
        if (arrayList3 != null) {
            int size = arrayList3.size();
            int i = 0;
            while (i < size) {
                AnonymousClass0s4 r6 = this.A09.get(i);
                if (arrayList == null || (z = r6.A02) || (indexOf2 = arrayList.indexOf((r1 = r6.A01))) == -1 || arrayList2 == null || !arrayList2.get(indexOf2).booleanValue()) {
                    if (r6.A00 == 0 || (arrayList != null && r6.A01.A0E(arrayList, 0, arrayList.size()))) {
                        this.A09.remove(i);
                        i--;
                        size--;
                        if (arrayList == null || (z = r6.A02) || (indexOf = arrayList.indexOf((r1 = r6.A01))) == -1 || arrayList2 == null || !arrayList2.get(indexOf).booleanValue()) {
                            r6.A00();
                        }
                    }
                    i++;
                } else {
                    this.A09.remove(i);
                    i--;
                    size--;
                }
                r1.A02.A0U(r1, z, false, false);
                i++;
            }
        }
    }

    private void A0D(@NonNull ArrayList<AnonymousClass0sD> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i, int i2) {
        int i3;
        int i4 = i;
        boolean z = arrayList.get(i4).A0E;
        ArrayList<Fragment> arrayList3 = this.A0L;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            this.A0L = arrayList3;
        } else {
            arrayList3.clear();
        }
        arrayList3.addAll(this.A0P.A01());
        Fragment fragment = this.A03;
        boolean z2 = false;
        for (int i5 = i4; i5 < i2; i5++) {
            AnonymousClass0sD r11 = arrayList.get(i5);
            if (!arrayList2.get(i5).booleanValue()) {
                ArrayList<Fragment> arrayList4 = this.A0L;
                int i6 = 0;
                while (true) {
                    ArrayList<C004009n> arrayList5 = r11.A0A;
                    if (i6 < arrayList5.size()) {
                        C004009n r13 = arrayList5.get(i6);
                        int i7 = r13.A00;
                        if (i7 != 1) {
                            if (i7 == 2) {
                                Fragment fragment2 = r13.A05;
                                int i8 = fragment2.mContainerId;
                                boolean z3 = false;
                                for (int size = arrayList4.size() - 1; size >= 0; size--) {
                                    Fragment fragment3 = arrayList4.get(size);
                                    if (fragment3.mContainerId == i8) {
                                        if (fragment3 == fragment2) {
                                            z3 = true;
                                        } else {
                                            if (fragment3 == fragment) {
                                                r11.A0A.add(i6, new C004009n(9, fragment3));
                                                i6++;
                                                fragment = null;
                                            }
                                            C004009n r15 = new C004009n(3, fragment3);
                                            r15.A01 = r13.A01;
                                            r15.A03 = r13.A03;
                                            r15.A02 = r13.A02;
                                            r15.A04 = r13.A04;
                                            r11.A0A.add(i6, r15);
                                            arrayList4.remove(fragment3);
                                            i6++;
                                        }
                                    }
                                }
                                if (z3) {
                                    r11.A0A.remove(i6);
                                    i6--;
                                } else {
                                    r13.A00 = 1;
                                    arrayList4.add(fragment2);
                                }
                            } else if (i7 == 3 || i7 == 6) {
                                arrayList4.remove(r13.A05);
                                if (r13.A05 == fragment) {
                                    r11.A0A.add(i6, new C004009n(9, r13.A05));
                                    i6++;
                                    fragment = null;
                                }
                            } else if (i7 != 7) {
                                if (i7 == 8) {
                                    arrayList5.add(i6, new C004009n(9, fragment));
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
                ArrayList<Fragment> arrayList6 = this.A0L;
                for (int size2 = r11.A0A.size() - 1; size2 >= 0; size2--) {
                    C004009n r2 = r11.A0A.get(size2);
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
                if (!r11.A0D) {
                }
            }
            z2 = true;
        }
        this.A0L.clear();
        if (!z && this.A00 >= 1) {
            AnonymousClass09x.A05(this.A05.A01, this.A04, arrayList, arrayList2, i4, i2, false, this.A0S);
        }
        for (int i10 = i4; i10 < i2; i10++) {
            AnonymousClass0sD r4 = arrayList.get(i10);
            boolean z4 = true;
            if (arrayList2.get(i10).booleanValue()) {
                r4.A0A(-1);
                if (i10 != i2 - 1) {
                    z4 = false;
                }
                r4.A0C(z4);
            } else {
                r4.A0A(1);
                r4.A09();
            }
        }
        if (z) {
            C000502c<Fragment> r12 = new C000502c<>();
            A04(r12);
            i3 = i2;
            for (int i11 = i2 - 1; i11 >= i4; i11--) {
                AnonymousClass0sD r112 = arrayList.get(i11);
                boolean booleanValue = arrayList2.get(i11).booleanValue();
                int i12 = 0;
                while (true) {
                    ArrayList<C004009n> arrayList7 = r112.A0A;
                    if (i12 < arrayList7.size()) {
                        if (!AnonymousClass0sD.A01(arrayList7.get(i12))) {
                            i12++;
                        } else if (!r112.A0E(arrayList, i11 + 1, i2)) {
                            ArrayList<AnonymousClass0s4> arrayList8 = this.A09;
                            if (arrayList8 == null) {
                                arrayList8 = new ArrayList<>();
                                this.A09 = arrayList8;
                            }
                            AnonymousClass0s4 r42 = new AnonymousClass0s4(r112, booleanValue);
                            arrayList8.add(r42);
                            int i13 = 0;
                            while (true) {
                                ArrayList<C004009n> arrayList9 = r112.A0A;
                                if (i13 < arrayList9.size()) {
                                    C004009n r152 = arrayList9.get(i13);
                                    if (AnonymousClass0sD.A01(r152)) {
                                        r152.A05.setOnStartEnterTransitionListener(r42);
                                    }
                                    i13++;
                                } else {
                                    if (booleanValue) {
                                        r112.A09();
                                    } else {
                                        r112.A0C(false);
                                    }
                                    i3--;
                                    if (i11 != i3) {
                                        arrayList.remove(i11);
                                        arrayList.add(i3, r112);
                                    }
                                    A04(r12);
                                }
                            }
                        }
                    }
                }
            }
            int size3 = r12.size();
            for (int i14 = 0; i14 < size3; i14++) {
                Fragment fragment4 = (Fragment) r12.A03[i14];
                if (!fragment4.mAdded) {
                    View requireView = fragment4.requireView();
                    fragment4.mPostponedAlpha = requireView.getAlpha();
                    requireView.setAlpha(0.0f);
                }
            }
        } else {
            z = false;
            i3 = i2;
        }
        if (i3 != i4 && z) {
            if (this.A00 >= 1) {
                AnonymousClass09x.A05(this.A05.A01, this.A04, arrayList, arrayList2, i4, i3, true, this.A0S);
            }
            A0Q(this.A00, true);
        }
        while (i4 < i2) {
            AnonymousClass0sD r1 = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue() && r1.A01 >= 0) {
                r1.A01 = -1;
            }
            i4++;
        }
    }

    private void A0E(boolean z) {
        String str;
        if (this.A0E) {
            str = "FragmentManager is already executing transactions";
        } else if (this.A05 == null) {
            if (this.A0D) {
                str = "FragmentManager has been destroyed";
            } else {
                str = "FragmentManager has not been attached to a host.";
            }
        } else if (Looper.myLooper() != this.A05.A02.getLooper()) {
            str = "Must be called from main thread of fragment host";
        } else if (z || (!this.A0H && !this.A0I)) {
            if (this.A0B == null) {
                this.A0B = new ArrayList<>();
                this.A0A = new ArrayList<>();
            }
            this.A0E = true;
            try {
                A0B(null, null);
                return;
            } finally {
                this.A0E = false;
            }
        } else {
            str = "Can not perform this action after onSaveInstanceState";
        }
        throw new IllegalStateException(str);
    }

    public static boolean A0F(@NonNull Fragment fragment) {
        if (fragment.mHasMenu && fragment.mMenuVisible) {
            return true;
        }
        for (Fragment fragment2 : fragment.mChildFragmentManager.A0P.A00()) {
            if (fragment2 != null && A0F(fragment2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable A0G() {
        /*
        // Method dump skipped, instructions count: 329
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A0G():android.os.Parcelable");
    }

    @Nullable
    public final Fragment A0H(@IdRes int i) {
        C003709i r4 = this.A0P;
        ArrayList<Fragment> arrayList = r4.A00;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = arrayList.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            } else {
                for (C003609h r0 : r4.A01.values()) {
                    if (r0 != null) {
                        Fragment fragment2 = r0.A01;
                        if (fragment2.mFragmentId == i) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    @Nullable
    public final Fragment A0I(@NonNull String str) {
        C003609h r0 = this.A0P.A01.get(str);
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    @Nullable
    public final Fragment A0J(@Nullable String str) {
        C003709i r4 = this.A0P;
        if (str == null) {
            return null;
        }
        ArrayList<Fragment> arrayList = r4.A00;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = arrayList.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            } else {
                for (C003609h r0 : r4.A01.values()) {
                    if (r0 != null) {
                        Fragment fragment2 = r0.A01;
                        if (str.equals(fragment2.mTag)) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public final Fragment A0K(@NonNull String str) {
        Fragment findFragmentByWho;
        for (C003609h r0 : this.A0P.A01.values()) {
            if (!(r0 == null || (findFragmentByWho = r0.A01.findFragmentByWho(str)) == null)) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    @NonNull
    public final AnonymousClass09Q A0L() {
        Fragment fragment = this.A02;
        if (fragment != null) {
            return fragment.mFragmentManager.A0L();
        }
        return this.A0J;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r0.isEmpty() != false) goto L_0x0010;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0M() {
        /*
            r5 = this;
            java.util.ArrayList<X.09Z> r4 = r5.A0Q
            monitor-enter(r4)
            java.util.ArrayList<X.0s4> r0 = r5.A09     // Catch:{ all -> 0x0033 }
            r3 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0033 }
            r1 = 1
            if (r0 == 0) goto L_0x0011
        L_0x0010:
            r1 = 0
        L_0x0011:
            int r0 = r4.size()     // Catch:{ all -> 0x0033 }
            if (r0 != r2) goto L_0x0018
            r3 = 1
        L_0x0018:
            if (r1 != 0) goto L_0x001c
            if (r3 == 0) goto L_0x0031
        L_0x001c:
            X.0s9<?> r0 = r5.A05     // Catch:{ all -> 0x0033 }
            android.os.Handler r1 = r0.A02     // Catch:{ all -> 0x0033 }
            java.lang.Runnable r0 = r5.A07     // Catch:{ all -> 0x0033 }
            r1.removeCallbacks(r0)     // Catch:{ all -> 0x0033 }
            X.0s9<?> r0 = r5.A05     // Catch:{ all -> 0x0033 }
            android.os.Handler r1 = r0.A02     // Catch:{ all -> 0x0033 }
            java.lang.Runnable r0 = r5.A07     // Catch:{ all -> 0x0033 }
            r1.post(r0)     // Catch:{ all -> 0x0033 }
            A06(r5)     // Catch:{ all -> 0x0033 }
        L_0x0031:
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            return
        L_0x0033:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A0M():void");
    }

    public final void A0O() {
        for (Fragment fragment : this.A0P.A01()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public final void A0Q(int i, boolean z) {
        AnonymousClass0s9<?> r2;
        if (this.A05 == null && i != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.A00) {
            this.A00 = i;
            C003709i r22 = this.A0P;
            for (Fragment fragment : r22.A01()) {
                A0a(fragment);
            }
            for (Fragment fragment2 : r22.A00()) {
                if (fragment2 != null && !fragment2.mIsNewlyAdded) {
                    A0a(fragment2);
                }
            }
            A03();
            if (this.A0G && (r2 = this.A05) != null && this.A00 == 4) {
                r2.A04();
                this.A0G = false;
            }
        }
    }

    public final void A0R(@NonNull Configuration configuration) {
        for (Fragment fragment : this.A0P.A01()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public final void A0S(@Nullable Parcelable parcelable) {
        Fragment fragment;
        Fragment fragment2;
        C003609h r5;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.A02 != null) {
                C003709i r4 = this.A0P;
                HashMap<String, C003609h> hashMap = r4.A01;
                hashMap.clear();
                Iterator<FragmentState> it = fragmentManagerState.A02.iterator();
                while (it.hasNext()) {
                    FragmentState next = it.next();
                    if (next != null) {
                        Fragment fragment3 = this.A06.A02.get(next.A07);
                        if (fragment3 != null) {
                            r5 = new C003609h(this.A0O, fragment3, next);
                        } else {
                            r5 = new C003609h(this.A0O, this.A05.A01.getClassLoader(), A0L(), next);
                        }
                        Fragment fragment4 = r5.A01;
                        fragment4.mFragmentManager = this;
                        r5.A00(this.A05.A01.getClassLoader());
                        hashMap.put(fragment4.mWho, r5);
                        r5.A00 = this.A00;
                    }
                }
                for (Fragment fragment5 : this.A06.A02.values()) {
                    if (!hashMap.containsKey(fragment5.mWho)) {
                        A0e(fragment5, 1);
                        fragment5.mRemoving = true;
                        A0e(fragment5, -1);
                    }
                }
                ArrayList<String> arrayList = fragmentManagerState.A03;
                r4.A00.clear();
                if (arrayList != null) {
                    for (String str : arrayList) {
                        C003609h r0 = hashMap.get(str);
                        if (r0 == null || (fragment2 = r0.A01) == null) {
                            throw new IllegalStateException(AnonymousClass006.A07("No instantiated fragment for (", str, ")"));
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
                        AnonymousClass0sD r2 = new AnonymousClass0sD(this);
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            int[] iArr = backStackState.A0D;
                            if (i2 >= iArr.length) {
                                break;
                            }
                            C004009n r9 = new C004009n();
                            int i4 = i2 + 1;
                            r9.A00 = iArr[i2];
                            String str2 = backStackState.A07.get(i3);
                            if (str2 != null) {
                                fragment = A0I(str2);
                            } else {
                                fragment = null;
                            }
                            r9.A05 = fragment;
                            r9.A07 = AnonymousClass0AO.values()[backStackState.A0C[i3]];
                            r9.A06 = AnonymousClass0AO.values()[backStackState.A0B[i3]];
                            int i5 = i4 + 1;
                            int i6 = iArr[i4];
                            r9.A01 = i6;
                            int i7 = i5 + 1;
                            int i8 = iArr[i5];
                            r9.A02 = i8;
                            int i9 = i7 + 1;
                            int i10 = iArr[i7];
                            r9.A03 = i10;
                            i2 = i9 + 1;
                            int i11 = iArr[i9];
                            r9.A04 = i11;
                            ((AbstractC004109o) r2).A02 = i6;
                            r2.A03 = i8;
                            r2.A04 = i10;
                            r2.A05 = i11;
                            r2.A02(r9);
                            i3++;
                        }
                        r2.A06 = backStackState.A03;
                        r2.A09 = backStackState.A06;
                        r2.A01 = backStackState.A02;
                        r2.A0D = true;
                        ((AbstractC004109o) r2).A01 = backStackState.A01;
                        r2.A08 = backStackState.A05;
                        ((AbstractC004109o) r2).A00 = backStackState.A00;
                        r2.A07 = backStackState.A04;
                        r2.A0B = backStackState.A08;
                        r2.A0C = backStackState.A09;
                        r2.A0E = backStackState.A0A;
                        r2.A0A(1);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            PrintWriter printWriter = new PrintWriter(new AnonymousClass0A9());
                            r2.A0B("  ", printWriter, false);
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
                    Fragment A0I2 = A0I(str3);
                    this.A03 = A0I2;
                    A09(this, A0I2);
                }
            }
        }
    }

    public final void A0T(@NonNull Menu menu) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0P.A01()) {
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public final void A0U(@NonNull AnonymousClass0sD r9, boolean z, boolean z2, boolean z3) {
        View view;
        if (z) {
            r9.A0C(z3);
        } else {
            r9.A09();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(r9);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.A00 >= 1) {
            AnonymousClass09x.A05(this.A05.A01, this.A04, arrayList, arrayList2, 0, 1, true, this.A0S);
        }
        if (z3) {
            A0Q(this.A00, true);
        }
        for (Fragment fragment : this.A0P.A00()) {
            if (fragment != null && (view = fragment.mView) != null && fragment.mIsNewlyAdded && r9.A0D(fragment.mContainerId)) {
                float f = fragment.mPostponedAlpha;
                if (f > 0.0f) {
                    view.setAlpha(f);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public final void A0W(@NonNull Fragment fragment) {
        if (!this.A0H && !this.A0I) {
            HashMap<String, Fragment> hashMap = this.A06.A02;
            if (!hashMap.containsKey(fragment.mWho)) {
                hashMap.put(fragment.mWho, fragment);
            }
        }
    }

    public final void A0X(@NonNull Fragment fragment) {
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                this.A0P.A02(fragment);
                if (A0F(fragment)) {
                    this.A0G = true;
                }
            }
        }
    }

    public final void A0Y(@NonNull Fragment fragment) {
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                ArrayList<Fragment> arrayList = this.A0P.A00;
                synchronized (arrayList) {
                    arrayList.remove(fragment);
                }
                fragment.mAdded = false;
                if (A0F(fragment)) {
                    this.A0G = true;
                }
                A0A(this, fragment);
            }
        }
    }

    public final void A0Z(@NonNull Fragment fragment) {
        C003709i r1 = this.A0P;
        String str = fragment.mWho;
        HashMap<String, C003609h> hashMap = r1.A01;
        if (!hashMap.containsKey(str)) {
            C003609h r12 = new C003609h(this.A0O, fragment);
            r12.A00(this.A05.A01.getClassLoader());
            hashMap.put(r12.A01.mWho, r12);
            if (fragment.mRetainInstanceChangedWhileDetached) {
                if (fragment.mRetainInstance) {
                    A0W(fragment);
                } else {
                    A0c(fragment);
                }
                fragment.mRetainInstanceChangedWhileDetached = false;
            }
            r12.A00 = this.A00;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e1, code lost:
        if (r7.isHideReplaced() != false) goto L_0x00e3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0a(@androidx.annotation.NonNull androidx.fragment.app.Fragment r7) {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A0a(androidx.fragment.app.Fragment):void");
    }

    public final void A0c(@NonNull Fragment fragment) {
        if (!this.A0H && !this.A0I) {
            this.A06.A02.remove(fragment.mWho);
        }
    }

    public final void A0d(@Nullable Fragment fragment) {
        if (fragment == null || (fragment.equals(A0I(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.A03;
            this.A03 = fragment;
            A09(this, fragment2);
            A09(this, this.A03);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(fragment);
        sb.append(" is not an active fragment of FragmentManager ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01d2, code lost:
        if (r2 <= -1) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x035e, code lost:
        if (r3.isInBackStack() != false) goto L_0x0360;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0083, code lost:
        if (r0 != 3) goto L_0x0085;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:150:0x026d */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x026d A[LOOP:0: B:150:0x026d->B:254:0x026d, LOOP_START, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0e(@androidx.annotation.NonNull androidx.fragment.app.Fragment r19, int r20) {
        /*
        // Method dump skipped, instructions count: 1152
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A0e(androidx.fragment.app.Fragment, int):void");
    }

    public final void A0f(@NonNull Fragment fragment, @NonNull AnonymousClass0AO r4) {
        if (!fragment.equals(A0I(fragment.mWho)) || !(fragment.mHost == null || fragment.mFragmentManager == this)) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(fragment);
            sb.append(" is not an active fragment of FragmentManager ");
            sb.append(this);
            throw new IllegalArgumentException(sb.toString());
        }
        fragment.mMaxState = r4;
    }

    public final void A0g(@NonNull Fragment fragment, boolean z) {
        ViewGroup viewGroup;
        if (fragment.mContainerId > 0 && this.A04.A01()) {
            View A002 = this.A04.A00(fragment.mContainerId);
            if ((A002 instanceof ViewGroup) && (viewGroup = (ViewGroup) A002) != null && (viewGroup instanceof AnonymousClass09O)) {
                ((AnonymousClass09O) viewGroup).A00 = !z;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v15, resolved type: X.0sz */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A0h(@NonNull AnonymousClass0s9<?> r6, @NonNull AnonymousClass09N r7, @Nullable Fragment fragment) {
        C07370s0 r0;
        if (this.A05 == null) {
            this.A05 = r6;
            this.A04 = r7;
            this.A02 = fragment;
            if (fragment != null) {
                A06(this);
            }
            if (r6 instanceof AbstractC07560sz) {
                AbstractC07560sz r02 = (AbstractC07560sz) r6;
                AnonymousClass01Q onBackPressedDispatcher = r02.getOnBackPressedDispatcher();
                this.A01 = onBackPressedDispatcher;
                Fragment fragment2 = r02;
                if (fragment != null) {
                    fragment2 = fragment;
                }
                AnonymousClass01P r3 = this.A0M;
                AnonymousClass0AP lifecycle = fragment2.getLifecycle();
                if (lifecycle.A05() != AnonymousClass0AO.DESTROYED) {
                    r3.A00.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(onBackPressedDispatcher, lifecycle, r3));
                }
            }
            if (fragment != null) {
                C07370s0 r32 = fragment.mFragmentManager.A06;
                HashMap<String, C07370s0> hashMap = r32.A01;
                C07370s0 r1 = hashMap.get(fragment.mWho);
                if (r1 == null) {
                    r1 = new C07370s0(r32.A04);
                    hashMap.put(fragment.mWho, r1);
                }
                this.A06 = r1;
                return;
            }
            if (r6 instanceof AbstractC00530Ak) {
                r0 = (C07370s0) new C00510Ai(((AbstractC00530Ak) r6).getViewModelStore(), C07370s0.A05).A00(C07370s0.class);
            } else {
                r0 = new C07370s0(false);
            }
            this.A06 = r0;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    public final void A0i(@NonNull AnonymousClass09Z r3, boolean z) {
        if (!z || (this.A05 != null && !this.A0D)) {
            A0E(z);
            if (r3.A2s(this.A0B, this.A0A)) {
                this.A0E = true;
                try {
                    A0C(this.A0B, this.A0A);
                } finally {
                    A01();
                }
            }
            A06(this);
            if (this.A0F) {
                this.A0F = false;
                A03();
            }
            this.A0P.A01.values().removeAll(Collections.singleton(null));
        }
    }

    public final void A0j(@NonNull AnonymousClass09Z r4, boolean z) {
        String str;
        if (!z) {
            if (this.A05 == null) {
                if (this.A0D) {
                    str = "FragmentManager has been destroyed";
                } else {
                    str = "FragmentManager has not been attached to a host.";
                }
            } else if (this.A0H || this.A0I) {
                str = "Can not perform this action after onSaveInstanceState";
            }
            throw new IllegalStateException(str);
        }
        ArrayList<AnonymousClass09Z> arrayList = this.A0Q;
        synchronized (arrayList) {
            if (this.A05 != null) {
                arrayList.add(r4);
                A0M();
            } else if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00fb A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0k(@androidx.annotation.NonNull java.lang.String r7, @androidx.annotation.Nullable java.io.FileDescriptor r8, @androidx.annotation.NonNull java.io.PrintWriter r9, @androidx.annotation.Nullable java.lang.String[] r10) {
        /*
        // Method dump skipped, instructions count: 411
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A0k(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    public final void A0l(boolean z) {
        for (Fragment fragment : this.A0P.A01()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public final void A0m(boolean z) {
        for (Fragment fragment : this.A0P.A01()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public final boolean A0p(@NonNull Menu menu) {
        boolean z = false;
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0P.A01()) {
                if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final boolean A0q(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        int i = 0;
        if (this.A00 < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.A0P.A01()) {
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.A0K != null) {
            while (true) {
                ArrayList<Fragment> arrayList2 = this.A0K;
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
        this.A0K = arrayList;
        return z;
    }

    public final boolean A0r(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0P.A01()) {
                if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean A0s(@NonNull MenuItem menuItem) {
        if (this.A00 >= 1) {
            for (Fragment fragment : this.A0P.A01()) {
                if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/ArrayList<LX/0sD;>;Ljava/util/ArrayList<Ljava/lang/Boolean;>;Ljava/lang/String;II)Z */
    public final boolean A0u(@NonNull ArrayList arrayList, @NonNull ArrayList arrayList2, @Nullable int i, int i2) {
        int i3;
        ArrayList<AnonymousClass0sD> arrayList3 = this.A08;
        if (arrayList3 != null) {
            if (i >= 0) {
                i3 = arrayList3.size() - 1;
                while (true) {
                    if (i3 < 0) {
                        break;
                    } else if (i != arrayList3.get(i3).A01) {
                        i3--;
                    } else if ((i2 & 1) != 0) {
                        do {
                            i3--;
                            if (i3 < 0) {
                                break;
                            }
                        } while (i == arrayList3.get(i3).A01);
                    }
                }
            } else if ((i2 & 1) == 0) {
                int size = arrayList3.size() - 1;
                if (size >= 0) {
                    arrayList.add(arrayList3.remove(size));
                    arrayList2.add(true);
                }
            } else {
                i3 = -1;
                if (i3 != arrayList3.size() - 1) {
                    for (int size2 = arrayList3.size() - 1; size2 > i3; size2--) {
                        arrayList.add(arrayList3.remove(size2));
                        arrayList2.add(true);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @NonNull
    public final String toString() {
        int identityHashCode;
        StringBuilder sb = new StringBuilder((int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.A02;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(fragment);
        } else {
            AnonymousClass0s9<?> r1 = this.A05;
            sb.append(r1.getClass().getSimpleName());
            sb.append("{");
            identityHashCode = System.identityHashCode(r1);
        }
        sb.append(Integer.toHexString(identityHashCode));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007a, code lost:
        if (r1 != null) goto L_0x007c;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004d */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004d A[SYNTHETIC, Splitter:B:17:0x004d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass09K A00(@androidx.annotation.NonNull android.content.Context r6, @androidx.annotation.NonNull X.AnonymousClass09N r7, @androidx.annotation.NonNull androidx.fragment.app.Fragment r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC003209a.A00(android.content.Context, X.09N, androidx.fragment.app.Fragment, boolean):X.09K");
    }

    public static void A08(@NonNull AbstractC003209a r2, Fragment fragment) {
        fragment.performDestroyView();
        r2.A0O.A00(fragment);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.A02(null);
        fragment.mInLayout = false;
    }

    private void A0C(@NonNull ArrayList<AnonymousClass0sD> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            A0B(arrayList, arrayList2);
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!arrayList.get(i).A0E) {
                    if (i2 != i) {
                        A0D(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (arrayList2.get(i).booleanValue()) {
                        while (i2 < size && arrayList2.get(i2).booleanValue() && !arrayList.get(i2).A0E) {
                            i2++;
                        }
                    }
                    A0D(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                A0D(arrayList, arrayList2, i2, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    public final void A0V(@NonNull Fragment fragment) {
        A0Z(fragment);
        if (!fragment.mDetached) {
            this.A0P.A02(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (A0F(fragment)) {
                this.A0G = true;
            }
        }
    }

    public final void A0b(@NonNull Fragment fragment) {
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            ArrayList<Fragment> arrayList = this.A0P.A00;
            synchronized (arrayList) {
                arrayList.remove(fragment);
            }
            fragment.mAdded = false;
            if (A0F(fragment)) {
                this.A0G = true;
            }
            fragment.mRemoving = true;
            A0A(this, fragment);
        }
    }

    public final void A0n(boolean z) {
        A0E(z);
        while (true) {
            ArrayList<AnonymousClass0sD> arrayList = this.A0B;
            ArrayList<Boolean> arrayList2 = this.A0A;
            ArrayList<AnonymousClass09Z> arrayList3 = this.A0Q;
            synchronized (arrayList3) {
                if (arrayList3.isEmpty()) {
                    break;
                }
                int size = arrayList3.size();
                boolean z2 = false;
                for (int i = 0; i < size; i++) {
                    z2 |= arrayList3.get(i).A2s(arrayList, arrayList2);
                }
                arrayList3.clear();
                this.A05.A02.removeCallbacks(this.A07);
                if (!z2) {
                    break;
                }
                this.A0E = true;
                try {
                    A0C(this.A0B, this.A0A);
                } finally {
                    A01();
                }
            }
        }
        A06(this);
        if (this.A0F) {
            this.A0F = false;
            A03();
        }
        this.A0P.A01.values().removeAll(Collections.singleton(null));
    }
}
