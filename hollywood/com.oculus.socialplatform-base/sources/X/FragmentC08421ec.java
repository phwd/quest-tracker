package X;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Deprecated
/* renamed from: X.1ec  reason: invalid class name and case insensitive filesystem */
public final class FragmentC08421ec extends Fragment {
    @Nullable
    public Fragment A00;
    @Nullable
    public AnonymousClass1g0 A01;
    @Nullable
    public FragmentC08421ec A02;
    public final C08461eg A03;
    public final AbstractC08531en A04 = new C08451ef(this);
    public final Set<FragmentC08421ec> A05 = new HashSet();

    public FragmentC08421ec() {
        C08461eg r1 = new C08461eg();
        this.A03 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0021, code lost:
        if (r0.isFinishing() == false) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(@androidx.annotation.NonNull X.FragmentC08421ec r4, android.app.Activity r5) {
        /*
            X.1ec r0 = r4.A02
            if (r0 == 0) goto L_0x000c
            java.util.Set<X.1ec> r0 = r0.A05
            r0.remove(r4)
            r0 = 0
            r4.A02 = r0
        L_0x000c:
            X.1cl r0 = X.ComponentCallbacks2C07631cl.A01(r5)
            X.1eb r3 = r0.A06
            android.app.FragmentManager r2 = r5.getFragmentManager()
            android.app.Activity r0 = X.C08411eb.A00(r5)
            if (r0 == 0) goto L_0x0023
            boolean r0 = r0.isFinishing()
            r1 = 0
            if (r0 != 0) goto L_0x0024
        L_0x0023:
            r1 = 1
        L_0x0024:
            r0 = 0
            X.1ec r0 = X.C08411eb.A01(r3, r2, r0, r1)
            r4.A02 = r0
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L_0x0038
            X.1ec r0 = r4.A02
            java.util.Set<X.1ec> r0 = r0.A05
            r0.add(r4)
        L_0x0038:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.FragmentC08421ec.A00(X.1ec, android.app.Activity):void");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:20:0x000a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.HashSet, java.util.AbstractCollection] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.Set<X.1ec>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    @android.annotation.TargetApi(17)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Set<X.FragmentC08421ec> A01() {
        /*
            r6 = this;
            X.1ec r0 = r6.A02
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x000f
            java.util.Set<X.1ec> r5 = r6.A05
        L_0x000a:
            java.util.Set r0 = java.util.Collections.unmodifiableSet(r5)
            return r0
        L_0x000f:
            X.1ec r0 = r6.A02
            if (r0 == 0) goto L_0x0049
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.Set r0 = r0.A01()
            java.util.Iterator r4 = r0.iterator()
        L_0x0020:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x000a
            java.lang.Object r3 = r4.next()
            android.app.Fragment r3 = (android.app.Fragment) r3
            android.app.Fragment r2 = r3.getParentFragment()
            android.app.Fragment r1 = r6.getParentFragment()
        L_0x0034:
            android.app.Fragment r0 = r2.getParentFragment()
            if (r0 == 0) goto L_0x0020
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            r5.add(r3)
            goto L_0x0020
        L_0x0044:
            android.app.Fragment r2 = r2.getParentFragment()
            goto L_0x0034
        L_0x0049:
            java.util.Set r0 = java.util.Collections.emptySet()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.FragmentC08421ec.A01():java.util.Set");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = this.A00;
        }
        sb.append(parentFragment);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.app.Fragment
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            A00(this, activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.A03.A00();
        FragmentC08421ec r0 = this.A02;
        if (r0 != null) {
            r0.A05.remove(this);
            this.A02 = null;
        }
    }

    public final void onDetach() {
        super.onDetach();
        FragmentC08421ec r0 = this.A02;
        if (r0 != null) {
            r0.A05.remove(this);
            this.A02 = null;
        }
    }

    public final void onStart() {
        super.onStart();
        this.A03.A01();
    }

    public final void onStop() {
        super.onStop();
        this.A03.A02();
    }
}
