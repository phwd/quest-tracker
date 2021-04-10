package X;

import com.google.common.collect.ComparatorOrdering;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.ReverseNaturalOrdering;
import com.google.common.collect.ReverseOrdering;
import java.util.Comparator;

public abstract class UR implements Comparator {
    public final UR A00() {
        if (this instanceof ReverseOrdering) {
            return ((ReverseOrdering) this).forwardOrder;
        }
        if (this instanceof ReverseNaturalOrdering) {
            return NaturalOrdering.A00;
        }
        if (!(this instanceof NaturalOrdering)) {
            return new ReverseOrdering(this);
        }
        return ReverseNaturalOrdering.A00;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        if (this instanceof ReverseOrdering) {
            return ((ReverseOrdering) this).forwardOrder.compare(obj2, obj);
        }
        if (this instanceof ReverseNaturalOrdering) {
            Comparable comparable = (Comparable) obj2;
            if (obj == null) {
                throw null;
            } else if (obj == comparable) {
                return 0;
            } else {
                return comparable.compareTo(obj);
            }
        } else if (!(this instanceof NaturalOrdering)) {
            return ((ComparatorOrdering) this).comparator.compare(obj, obj2);
        } else {
            Comparable comparable2 = (Comparable) obj;
            if (comparable2 == null) {
                throw null;
            } else if (obj2 != null) {
                return comparable2.compareTo(obj2);
            } else {
                throw null;
            }
        }
    }
}
