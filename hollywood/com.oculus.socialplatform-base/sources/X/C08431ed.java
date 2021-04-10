package X;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1ed  reason: invalid class name and case insensitive filesystem */
public final class C08431ed extends Fragment {
    public static final String __redex_internal_original_name = "com.bumptech.glide.manager.SupportRequestManagerFragment";
    @Nullable
    public Fragment A00;
    @Nullable
    public AnonymousClass1g0 A01;
    @Nullable
    public C08431ed A02;
    public final C08461eg A03;
    public final AbstractC08531en A04 = new C08441ee(this);
    public final Set<C08431ed> A05 = new HashSet();

    public C08431ed() {
        C08461eg r1 = new C08461eg();
        this.A03 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001d, code lost:
        if (r0.isFinishing() == false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(@androidx.annotation.NonNull X.C08431ed r3, @androidx.annotation.NonNull android.content.Context r4, X.AnonymousClass09b r5) {
        /*
            X.1ed r0 = r3.A02
            if (r0 == 0) goto L_0x000c
            java.util.Set<X.1ed> r0 = r0.A05
            r0.remove(r3)
            r0 = 0
            r3.A02 = r0
        L_0x000c:
            X.1cl r0 = X.ComponentCallbacks2C07631cl.A01(r4)
            X.1eb r2 = r0.A06
            android.app.Activity r0 = X.C08411eb.A00(r4)
            if (r0 == 0) goto L_0x001f
            boolean r0 = r0.isFinishing()
            r1 = 0
            if (r0 != 0) goto L_0x0020
        L_0x001f:
            r1 = 1
        L_0x0020:
            r0 = 0
            X.1ed r0 = X.C08411eb.A02(r2, r5, r0, r1)
            r3.A02 = r0
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0034
            X.1ed r0 = r3.A02
            java.util.Set<X.1ed> r0 = r0.A05
            r0.add(r3)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08431ed.A01(X.1ed, android.content.Context, X.09b):void");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x0011 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.HashSet, java.util.AbstractCollection] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.Set<X.1ed>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Set<X.C08431ed> A0F() {
        /*
            r6 = this;
            X.1ed r0 = r6.A02
            if (r0 != 0) goto L_0x0009
            java.util.Set r0 = java.util.Collections.emptySet()
            return r0
        L_0x0009:
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0016
            java.util.Set<X.1ed> r5 = r6.A05
        L_0x0011:
            java.util.Set r0 = java.util.Collections.unmodifiableSet(r5)
            return r0
        L_0x0016:
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            X.1ed r0 = r6.A02
            java.util.Set r0 = r0.A0F()
            java.util.Iterator r4 = r0.iterator()
        L_0x0025:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0011
            java.lang.Object r3 = r4.next()
            X.1ed r3 = (X.C08431ed) r3
            androidx.fragment.app.Fragment r2 = r3.A0D
            if (r2 != 0) goto L_0x0037
            androidx.fragment.app.Fragment r2 = r3.A00
        L_0x0037:
            androidx.fragment.app.Fragment r1 = r6.A0D
            if (r1 != 0) goto L_0x003d
            androidx.fragment.app.Fragment r1 = r6.A00
        L_0x003d:
            androidx.fragment.app.Fragment r0 = r2.A0D
            if (r0 == 0) goto L_0x0025
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x004b
            r5.add(r3)
            goto L_0x0025
        L_0x004b:
            androidx.fragment.app.Fragment r2 = r2.A0D
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08431ed.A0F():java.util.Set");
    }

    @Override // androidx.fragment.app.Fragment
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment fragment = this.A0D;
        if (fragment == null) {
            fragment = this.A00;
        }
        sb.append(fragment);
        sb.append("}");
        return sb.toString();
    }

    @Override // androidx.fragment.app.Fragment
    public final void A0A() {
        super.A0A();
        this.A03.A00();
        C08431ed r0 = this.A02;
        if (r0 != null) {
            r0.A05.remove(this);
            this.A02 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void A0B() {
        super.A0B();
        this.A00 = null;
        C08431ed r0 = this.A02;
        if (r0 != null) {
            r0.A05.remove(this);
            this.A02 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void A0C() {
        super.A0C();
        this.A03.A01();
    }

    @Override // androidx.fragment.app.Fragment
    public final void A0D() {
        super.A0D();
        this.A03.A02();
    }

    @Override // androidx.fragment.app.Fragment
    public final void A0E(Context context) {
        super.A0E(context);
        Fragment fragment = this;
        while (fragment.A0D != null) {
            fragment = fragment.A0D;
        }
        AnonymousClass09b r1 = fragment.A0H;
        if (r1 != null) {
            try {
                A01(this, A02(), r1);
            } catch (IllegalStateException e) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e);
                }
            }
        } else if (Log.isLoggable("SupportRMFragment", 5)) {
            Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
        }
    }
}
