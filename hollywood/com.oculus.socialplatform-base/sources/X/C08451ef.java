package X;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1ef  reason: invalid class name and case insensitive filesystem */
public class C08451ef implements AbstractC08531en {
    public final /* synthetic */ FragmentC08421ec A00;

    public C08451ef(FragmentC08421ec r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC08531en
    @NonNull
    public final Set<AnonymousClass1g0> A3m() {
        Set<FragmentC08421ec> A01 = this.A00.A01();
        HashSet hashSet = new HashSet(A01.size());
        for (FragmentC08421ec r0 : A01) {
            AnonymousClass1g0 r02 = r0.A01;
            if (r02 != null) {
                hashSet.add(r02);
            }
        }
        return hashSet;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{fragment=");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }
}
