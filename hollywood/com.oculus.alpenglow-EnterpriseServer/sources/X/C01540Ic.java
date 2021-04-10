package X;

import com.google.common.collect.TreeMultiset;

/* renamed from: X.0Ic  reason: invalid class name and case insensitive filesystem */
public class C01540Ic extends AnonymousClass0Y0<E> {
    public final /* synthetic */ AnonymousClass0u0 A00;
    public final /* synthetic */ TreeMultiset A01;

    public C01540Ic(TreeMultiset treeMultiset, AnonymousClass0u0 r2) {
        this.A01 = treeMultiset;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0Y0
    public final int A00() {
        int i = this.A00.A01;
        if (i == 0) {
            return this.A01.A1s(A01());
        }
        return i;
    }

    @Override // X.AnonymousClass0Y0
    public final E A01() {
        return this.A00.A08;
    }
}
