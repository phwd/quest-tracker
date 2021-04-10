package X;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.0vO  reason: invalid class name */
public final class AnonymousClass0vO extends AbstractC003809q implements AnonymousClass09a, AnonymousClass09X {
    public int A00;
    public boolean A01;
    public final AnonymousClass09b A02;

    @Override // X.AbstractC003809q
    public final int A00() {
        int i;
        if (!this.A01) {
            if (Log.isLoggable("FragmentManager", 2)) {
                PrintWriter printWriter = new PrintWriter(new AnonymousClass0AA());
                A04("  ", printWriter, true);
                printWriter.close();
            }
            this.A01 = true;
            if (this.A0D) {
                i = this.A02.A0R.getAndIncrement();
            } else {
                i = -1;
            }
            this.A00 = i;
            AnonymousClass09b r3 = this.A02;
            ArrayList<AnonymousClass09a> arrayList = r3.A0Q;
            synchronized (arrayList) {
                if (r3.A05 != null) {
                    arrayList.add(this);
                    if (arrayList.size() == 1) {
                        r3.A05.A02.removeCallbacks(r3.A07);
                        r3.A05.A02.post(r3.A07);
                        AnonymousClass09b.A04(r3);
                    }
                }
            }
            return this.A00;
        }
        throw new IllegalStateException("commit already called");
    }

    @Override // X.AbstractC003809q
    public final void A01() {
        if (!this.A0D) {
            AnonymousClass09b r2 = this.A02;
            if (r2.A05 != null && !r2.A0C) {
                AnonymousClass09b.A0A(r2, true);
                if (A3H(r2.A0A, r2.A09)) {
                    r2.A0D = true;
                    try {
                        AnonymousClass09b.A09(r2, r2.A0A, r2.A09);
                    } finally {
                        AnonymousClass09b.A02(r2);
                    }
                }
                AnonymousClass09b.A04(r2);
                if (r2.A0E) {
                    r2.A0E = false;
                    AnonymousClass09b.A03(r2);
                }
                r2.A0O.A01.values().removeAll(Collections.singleton(null));
                return;
            }
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public final void A03(int i) {
        if (this.A0D) {
            int size = this.A0A.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = this.A0A.get(i2).A05;
                if (fragment != null) {
                    fragment.A02 += i;
                }
            }
        }
    }

    public final void A04(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.A09);
            printWriter.print(" mIndex=");
            printWriter.print(this.A00);
            printWriter.print(" mCommitted=");
            printWriter.println(this.A01);
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
                C003709p r3 = this.A0A.get(i);
                int i2 = r3.A00;
                switch (i2) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
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
                        str2 = AnonymousClass006.A03("cmd=", i2);
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
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

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        int i = this.A00;
        if (i >= 0) {
            sb.append(" #");
            sb.append(i);
        }
        String str = this.A09;
        if (str != null) {
            sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            sb.append(str);
        }
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass0vO(@androidx.annotation.NonNull X.AnonymousClass09b r3) {
        /*
            r2 = this;
            X.09S r1 = r3.A0J()
            X.0vL<?> r0 = r3.A05
            if (r0 == 0) goto L_0x0017
            android.content.Context r0 = r0.A01
            java.lang.ClassLoader r0 = r0.getClassLoader()
        L_0x000e:
            r2.<init>(r1, r0)
            r0 = -1
            r2.A00 = r0
            r2.A02 = r3
            return
        L_0x0017:
            r0 = 0
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0vO.<init>(X.09b):void");
    }

    @Override // X.AbstractC003809q
    public final void A02(int i, Fragment fragment, @Nullable String str, int i2) {
        super.A02(i, fragment, str, i2);
        fragment.A0H = this.A02;
    }

    @Override // X.AnonymousClass09a
    public final boolean A3H(@NonNull ArrayList<AnonymousClass0vO> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.A0D) {
            return true;
        }
        AnonymousClass09b r1 = this.A02;
        ArrayList<AnonymousClass0vO> arrayList3 = r1.A08;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            r1.A08 = arrayList3;
        }
        arrayList3.add(this);
        return true;
    }
}
