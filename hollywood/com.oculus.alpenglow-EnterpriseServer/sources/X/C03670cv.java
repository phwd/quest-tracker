package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/* renamed from: X.0cv  reason: invalid class name and case insensitive filesystem */
public final class C03670cv extends AbstractC01010Cy implements AnonymousClass0Ci, AbstractC00970Cf {
    public int A00;
    public final AnonymousClass0Cj A01;

    @Override // X.AbstractC01010Cy
    public final void A00() {
        if (!this.A0D) {
            AnonymousClass0Cj r3 = this.A01;
            if (r3.A05 != null && !r3.A0C) {
                AnonymousClass0Cj.A0A(r3, true);
                ArrayList<C03670cv> arrayList = r3.A0A;
                ArrayList<Boolean> arrayList2 = r3.A09;
                if (A2r(arrayList, arrayList2)) {
                    r3.A0D = true;
                    try {
                        AnonymousClass0Cj.A09(r3, arrayList, arrayList2);
                    } finally {
                        AnonymousClass0Cj.A02(r3);
                    }
                }
                AnonymousClass0Cj.A04(r3);
                if (r3.A0E) {
                    r3.A0E = false;
                    AnonymousClass0Cj.A03(r3);
                }
                r3.A0O.A01.values().removeAll(Collections.singleton(null));
                return;
            }
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public final void A02(int i) {
        if (this.A0D) {
            ArrayList<C01000Cx> arrayList = this.A0A;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                AnonymousClass0MN r1 = arrayList.get(i2).A05;
                if (r1 != null) {
                    r1.A02 += i;
                }
            }
        }
    }

    public final void A03(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.A09);
            printWriter.print(" mIndex=");
            printWriter.print(this.A00);
            printWriter.print(" mCommitted=");
            printWriter.println(false);
            if (this.A06 != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.A06));
            }
            if (!(this.A02 == 0 && this.A03 == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.A02));
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
                C01000Cx r3 = this.A0A.get(i);
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
            sb.append(" ");
            sb.append(str);
        }
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C03670cv(@androidx.annotation.NonNull X.AnonymousClass0Cj r3) {
        /*
            r2 = this;
            X.0Ca r1 = r3.A0I()
            X.0cs<?> r0 = r3.A05
            if (r0 == 0) goto L_0x0017
            android.content.Context r0 = r0.A01
            java.lang.ClassLoader r0 = r0.getClassLoader()
        L_0x000e:
            r2.<init>(r1, r0)
            r0 = -1
            r2.A00 = r0
            r2.A01 = r3
            return
        L_0x0017:
            r0 = 0
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03670cv.<init>(X.0Cj):void");
    }

    @Override // X.AbstractC01010Cy
    public final void A01(int i, AnonymousClass0MN r3, @Nullable String str, int i2) {
        super.A01(i, r3, str, i2);
        r3.A0H = this.A01;
    }

    @Override // X.AnonymousClass0Ci
    public final boolean A2r(@NonNull ArrayList<C03670cv> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.A0D) {
            return true;
        }
        AnonymousClass0Cj r1 = this.A01;
        ArrayList<C03670cv> arrayList3 = r1.A08;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList<>();
            r1.A08 = arrayList3;
        }
        arrayList3.add(this);
        return true;
    }
}
