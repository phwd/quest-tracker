package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ComparatorOrdering;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.ReverseNaturalOrdering;
import com.google.common.collect.ReverseOrdering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class RF<T> implements Comparator<T> {
    @Override // java.util.Comparator
    @CanIgnoreReturnValue
    public final int compare(@NullableDecl T t, @NullableDecl T t2) {
        if (this instanceof ReverseOrdering) {
            return ((ReverseOrdering) this).forwardOrder.compare(t2, t);
        }
        if (this instanceof ReverseNaturalOrdering) {
            T t3 = t2;
            if (t == null) {
                throw null;
            } else if (t == t3) {
                return 0;
            } else {
                return t3.compareTo(t);
            }
        } else if (!(this instanceof NaturalOrdering)) {
            return ((ComparatorOrdering) this).comparator.compare(t, t2);
        } else {
            T t4 = t;
            if (t4 == null) {
                throw null;
            } else if (t2 != null) {
                return t4.compareTo(t2);
            } else {
                throw null;
            }
        }
    }
}
