package X;

import com.google.common.collect.TreeMultiset;

/* renamed from: X.Az  reason: case insensitive filesystem */
public class C0061Az extends AbstractC0181Ug<E> {
    public final /* synthetic */ AnonymousClass8V A00;
    public final /* synthetic */ TreeMultiset A01;

    public C0061Az(TreeMultiset treeMultiset, AnonymousClass8V r2) {
        this.A01 = treeMultiset;
        this.A00 = r2;
    }

    @Override // X.AbstractC0181Ug
    public final int A00() {
        int i = this.A00.A01;
        if (i == 0) {
            return this.A01.A1c(A01());
        }
        return i;
    }

    @Override // X.AbstractC0181Ug
    public final E A01() {
        return this.A00.A08;
    }
}
