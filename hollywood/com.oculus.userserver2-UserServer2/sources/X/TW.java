package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public abstract class TW<E> implements Iterator<E> {
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
