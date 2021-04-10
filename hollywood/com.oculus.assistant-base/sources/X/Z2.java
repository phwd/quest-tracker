package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.Multisets$ImmutableEntry;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.TreeMultiset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

public abstract class Z2<E> extends AbstractC1157tx<E> implements AbstractC0118Bl<E> {
    public transient AbstractC0118Bl A00;
    public final Comparator comparator;

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A3f() {
        C0366Ub ub = new C0366Ub((TreeMultiset) this);
        if (ub.hasNext()) {
            return (AbstractC1179ua) ub.next();
        }
        return null;
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A4W() {
        C0366Ub ub = new C0366Ub((TreeMultiset) this);
        if (!ub.hasNext()) {
            return null;
        }
        AbstractC1179ua uaVar = (AbstractC1179ua) ub.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(uaVar.A01(), uaVar.A00());
        ub.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A1g() {
        AbstractC0118Bl bl = this.A00;
        if (bl != null) {
            return bl;
        }
        AnonymousClass24 r0 = new AnonymousClass24(this);
        this.A00 = r0;
        return r0;
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A5A(Object obj, BoundType boundType, Object obj2, BoundType boundType2) {
        if (boundType == null) {
            throw null;
        } else if (boundType2 != null) {
            return A5F(obj, boundType).A3B(obj2, boundType2);
        } else {
            throw null;
        }
    }

    @Override // X.AbstractC0118Bl
    /* renamed from: A1l */
    public final NavigableSet A1m() {
        return (NavigableSet) super.A1m();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A29() {
        Iterator A03 = A03();
        if (A03.hasNext()) {
            return (AbstractC1179ua) A03.next();
        }
        return null;
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A4V() {
        Iterator A03 = A03();
        if (!A03.hasNext()) {
            return null;
        }
        AbstractC1179ua uaVar = (AbstractC1179ua) A03.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(uaVar.A01(), uaVar.A00());
        A03.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.UY, X.AbstractC0118Bl
    public final Comparator comparator() {
        return this.comparator;
    }

    public Z2() {
        this(NaturalOrdering.A00);
    }

    public Z2(Comparator comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
