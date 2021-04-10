package X;

import java.util.List;
import java.util.ListIterator;

/* renamed from: X.tq  reason: case insensitive filesystem */
public final class C1150tq extends C0357Th implements ListIterator {
    public final /* synthetic */ C1151tr A00;

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        C1151tr trVar = this.A00;
        boolean isEmpty = trVar.isEmpty();
        C0357Th.A00(this);
        ((ListIterator) this.A01).add(obj);
        trVar.A00.A00++;
        if (isEmpty) {
            trVar.A00();
        }
    }

    public final boolean hasPrevious() {
        C0357Th.A00(this);
        return ((ListIterator) this.A01).hasPrevious();
    }

    public final int nextIndex() {
        C0357Th.A00(this);
        return ((ListIterator) this.A01).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        C0357Th.A00(this);
        return ((ListIterator) this.A01).previous();
    }

    public final int previousIndex() {
        C0357Th.A00(this);
        return ((ListIterator) this.A01).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        C0357Th.A00(this);
        ((ListIterator) this.A01).set(obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1150tq(C1151tr trVar) {
        super(trVar);
        this.A00 = trVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1150tq(C1151tr trVar, int i) {
        super(trVar, ((List) trVar.A00).listIterator(i));
        this.A00 = trVar;
    }
}
