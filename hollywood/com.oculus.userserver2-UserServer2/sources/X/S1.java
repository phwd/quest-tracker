package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;

@GwtCompatible
public interface S1<T> extends Iterable<T> {
    Comparator<? super T> comparator();
}
