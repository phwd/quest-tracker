package X;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.facebook.FacebookSdk;
import com.oculus.horizon.service.ODHInterfaceService;
import com.oculus.horizon.vrbugreporter.BugReporterService;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.PrintWriter;
import java.util.ArrayList;

/* renamed from: X.0sD  reason: invalid class name */
public final class AnonymousClass0sD extends AbstractC004109o implements AnonymousClass09Z, AnonymousClass09W {
    public boolean A00;
    public int A01;
    public final AbstractC003209a A02;

    @Override // X.AbstractC004109o
    public final int A03() {
        return A00(false);
    }

    @Override // X.AbstractC004109o
    public final int A04() {
        return A00(true);
    }

    public final boolean A0E(ArrayList<AnonymousClass0sD> arrayList, int i, int i2) {
        int i3;
        if (i2 != i) {
            ArrayList<C004009n> arrayList2 = this.A0A;
            int size = arrayList2.size();
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                C004009n r1 = arrayList2.get(i5);
                if (!(r1.A05 == null || (i3 = r1.A05.mContainerId) == 0 || i3 == i4)) {
                    for (int i6 = i; i6 < i2; i6++) {
                        AnonymousClass0sD r3 = arrayList.get(i6);
                        int size2 = r3.A0A.size();
                        for (int i7 = 0; i7 < size2; i7++) {
                            Fragment fragment = r3.A0A.get(i7).A05;
                            if (fragment != null && fragment.mContainerId == i3) {
                                return true;
                            }
                        }
                    }
                    i4 = i3;
                }
            }
        }
        return false;
    }

    private final int A00(boolean z) {
        int i;
        if (!this.A00) {
            if (Log.isLoggable("FragmentManager", 2)) {
                PrintWriter printWriter = new PrintWriter(new AnonymousClass0A9());
                A0B("  ", printWriter, true);
                printWriter.close();
            }
            this.A00 = true;
            if (this.A0D) {
                i = this.A02.A0R.getAndIncrement();
            } else {
                i = -1;
            }
            this.A01 = i;
            this.A02.A0j(this, z);
            return this.A01;
        }
        throw new IllegalStateException("commit already called");
    }

    public static boolean A01(C004009n r1) {
        Fragment fragment = r1.A05;
        if (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC004109o
    @NonNull
    public final AbstractC004109o A05(@NonNull Fragment fragment) {
        AbstractC003209a r1 = fragment.mFragmentManager;
        if (r1 == null || r1 == this.A02) {
            super.A05(fragment);
            return this;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Cannot remove Fragment attached to a different FragmentManager. Fragment ", fragment.toString(), " is already attached to a FragmentManager."));
    }

    @Override // X.AbstractC004109o
    public final void A06() {
        if (!this.A0D) {
            this.A02.A0i(this, false);
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    @Override // X.AbstractC004109o
    public final void A07() {
        if (!this.A0D) {
            this.A02.A0i(this, true);
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public final void A09() {
        AbstractC003209a r1;
        int size = this.A0A.size();
        for (int i = 0; i < size; i++) {
            C004009n r7 = this.A0A.get(i);
            Fragment fragment = r7.A05;
            if (fragment != null) {
                fragment.setNextTransition(this.A06);
            }
            int i2 = r7.A00;
            switch (i2) {
                case 1:
                    fragment.setNextAnim(r7.A01);
                    r1 = this.A02;
                    r1.A0g(fragment, false);
                    r1.A0V(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException(AnonymousClass006.A01("Unknown cmd: ", i2));
                case 3:
                    fragment.setNextAnim(r7.A02);
                    r1 = this.A02;
                    r1.A0b(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(r7.A02);
                    r1 = this.A02;
                    if (!fragment.mHidden) {
                        fragment.mHidden = true;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                        AbstractC003209a.A0A(r1, fragment);
                        break;
                    }
                    break;
                case 5:
                    fragment.setNextAnim(r7.A01);
                    r1 = this.A02;
                    r1.A0g(fragment, false);
                    if (fragment.mHidden) {
                        fragment.mHidden = false;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                        break;
                    }
                    break;
                case 6:
                    fragment.setNextAnim(r7.A02);
                    r1 = this.A02;
                    r1.A0Y(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(r7.A01);
                    r1 = this.A02;
                    r1.A0g(fragment, false);
                    r1.A0X(fragment);
                    break;
                case 8:
                    r1 = this.A02;
                    r1.A0d(fragment);
                    break;
                case 9:
                    r1 = this.A02;
                    r1.A0d(null);
                    break;
                case 10:
                    r1 = this.A02;
                    r1.A0f(fragment, r7.A06);
                    break;
            }
            if (!(this.A0E || r7.A00 == 1 || fragment == null)) {
                r1.A0a(fragment);
            }
        }
        if (!this.A0E) {
            AbstractC003209a r12 = this.A02;
            r12.A0Q(r12.A00, true);
        }
    }

    public final void A0A(int i) {
        if (this.A0D) {
            int size = this.A0A.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = this.A0A.get(i2).A05;
                if (fragment != null) {
                    fragment.mBackStackNesting += i;
                }
            }
        }
    }

    public final void A0B(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.A09);
            printWriter.print(" mIndex=");
            printWriter.print(this.A01);
            printWriter.print(" mCommitted=");
            printWriter.println(this.A00);
            if (this.A06 != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.A06));
            }
            if (!(super.A02 == 0 && this.A03 == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(super.A02));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.A03));
            }
            if (!(this.A04 == 0 && this.A05 == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.A04));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.A05));
            }
            if (!(super.A01 == 0 && this.A08 == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(super.A01));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.A08);
            }
            if (!(super.A00 == 0 && this.A07 == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(super.A00));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.A07);
            }
        }
        if (!this.A0A.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = this.A0A.size();
            for (int i = 0; i < size; i++) {
                C004009n r3 = this.A0A.get(i);
                int i2 = r3.A00;
                switch (i2) {
                    case 0:
                        str2 = ODHInterfaceService.NULL_VALUE;
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = DiskLruCache.REMOVE;
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case 10:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        str2 = AnonymousClass006.A01("cmd=", i2);
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(r3.A05);
                if (z) {
                    if (!(r3.A01 == 0 && r3.A02 == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(r3.A01));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(r3.A02));
                    }
                    if (r3.A03 != 0 || r3.A04 != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(r3.A03));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(r3.A04));
                    }
                }
            }
        }
    }

    public final void A0C(boolean z) {
        for (int size = this.A0A.size() - 1; size >= 0; size--) {
            C004009n r5 = this.A0A.get(size);
            Fragment fragment = r5.A05;
            if (fragment != null) {
                int i = this.A06;
                int i2 = 8194;
                if (i != 4097) {
                    if (i != 4099) {
                        i2 = BugReporterService.JOB_ID_BUG_REPORTER_UPLOADER;
                        if (i != 8194) {
                            i2 = 0;
                        }
                    } else {
                        i2 = 4099;
                    }
                }
                fragment.setNextTransition(i2);
            }
            int i3 = r5.A00;
            switch (i3) {
                case 1:
                    fragment.setNextAnim(r5.A04);
                    AbstractC003209a r0 = this.A02;
                    r0.A0g(fragment, true);
                    r0.A0b(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException(AnonymousClass006.A01("Unknown cmd: ", i3));
                case 3:
                    fragment.setNextAnim(r5.A03);
                    this.A02.A0V(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(r5.A03);
                    if (fragment.mHidden) {
                        fragment.mHidden = false;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                        break;
                    }
                    break;
                case 5:
                    fragment.setNextAnim(r5.A04);
                    AbstractC003209a r1 = this.A02;
                    r1.A0g(fragment, true);
                    if (!fragment.mHidden) {
                        fragment.mHidden = true;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                        AbstractC003209a.A0A(r1, fragment);
                        break;
                    }
                    break;
                case 6:
                    fragment.setNextAnim(r5.A03);
                    this.A02.A0X(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(r5.A04);
                    AbstractC003209a r02 = this.A02;
                    r02.A0g(fragment, true);
                    r02.A0Y(fragment);
                    break;
                case 8:
                    this.A02.A0d(null);
                    break;
                case 9:
                    this.A02.A0d(fragment);
                    break;
                case 10:
                    this.A02.A0f(fragment, r5.A07);
                    break;
            }
            if (!(this.A0E || r5.A00 == 3 || fragment == null)) {
                this.A02.A0a(fragment);
            }
        }
        if (!this.A0E && z) {
            AbstractC003209a r12 = this.A02;
            r12.A0Q(r12.A00, true);
        }
    }

    public final boolean A0D(int i) {
        int i2;
        ArrayList<C004009n> arrayList = this.A0A;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            C004009n r1 = arrayList.get(i3);
            if (!(r1.A05 == null || (i2 = r1.A05.mContainerId) == 0 || i2 != i)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        int i = this.A01;
        if (i >= 0) {
            sb.append(" #");
            sb.append(i);
        }
        String str = this.A09;
        if (str != null) {
            sb.append(" ");
            sb.append(str);
        }
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass0sD(@androidx.annotation.NonNull X.AbstractC003209a r3) {
        /*
            r2 = this;
            X.09Q r1 = r3.A0L()
            X.0s9<?> r0 = r3.A05
            if (r0 == 0) goto L_0x0017
            android.content.Context r0 = r0.A01
            java.lang.ClassLoader r0 = r0.getClassLoader()
        L_0x000e:
            r2.<init>(r1, r0)
            r0 = -1
            r2.A01 = r0
            r2.A02 = r3
            return
        L_0x0017:
            r0 = 0
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0sD.<init>(X.09a):void");
    }

    @Override // X.AbstractC004109o
    public final void A08(int i, Fragment fragment, @Nullable String str, int i2) {
        super.A08(i, fragment, str, i2);
        fragment.mFragmentManager = this.A02;
    }

    @Override // X.AnonymousClass09Z
    public final boolean A2s(@NonNull ArrayList<AnonymousClass0sD> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.A0D) {
            return true;
        }
        AbstractC003209a r1 = this.A02;
        ArrayList<AnonymousClass0sD> arrayList3 = r1.A08;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            r1.A08 = arrayList3;
        }
        arrayList3.add(this);
        return true;
    }
}
