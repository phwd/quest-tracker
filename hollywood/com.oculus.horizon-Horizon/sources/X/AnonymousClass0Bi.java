package X;

import com.google.common.collect.TreeMultiset;

/* renamed from: X.0Bi  reason: invalid class name */
public class AnonymousClass0Bi extends AnonymousClass0dN<E> {
    public final /* synthetic */ C07330rw A00;
    public final /* synthetic */ TreeMultiset A01;

    public AnonymousClass0Bi(TreeMultiset treeMultiset, C07330rw r2) {
        this.A01 = treeMultiset;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0dN
    public final int A00() {
        int i = this.A00.A01;
        if (i == 0) {
            return this.A01.A1v(A01());
        }
        return i;
    }

    @Override // X.AnonymousClass0dN
    public final E A01() {
        return this.A00.A08;
    }
}
