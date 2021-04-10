package X;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1ee  reason: invalid class name and case insensitive filesystem */
public class C08441ee implements AbstractC08531en {
    public final /* synthetic */ C08431ed A00;

    public C08441ee(C08431ed r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC08531en
    @NonNull
    public final Set<AnonymousClass1g0> A3m() {
        Set<C08431ed> A0F = this.A00.A0F();
        HashSet hashSet = new HashSet(A0F.size());
        for (C08431ed r0 : A0F) {
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
