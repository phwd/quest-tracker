package X;

import com.google.common.collect.TreeMultiset;

/* renamed from: X.0M9  reason: invalid class name */
public class AnonymousClass0M9 extends AnonymousClass0f2<E> {
    public final /* synthetic */ C05650wb A00;
    public final /* synthetic */ TreeMultiset A01;

    public AnonymousClass0M9(TreeMultiset treeMultiset, C05650wb r2) {
        this.A01 = treeMultiset;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0f2
    public final int A00() {
        int i = this.A00.A01;
        if (i == 0) {
            return this.A01.A2J(A01());
        }
        return i;
    }

    @Override // X.AnonymousClass0f2
    public final E A01() {
        return this.A00.A08;
    }
}
