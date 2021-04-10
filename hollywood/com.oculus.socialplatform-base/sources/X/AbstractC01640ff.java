package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

@GwtCompatible
/* renamed from: X.0ff  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01640ff<E> extends AbstractC05120uD implements Collection<E> {
    /* renamed from: A02 */
    public abstract Collection<E> A00();

    public String A01() {
        int size = size();
        AnonymousClass0th.A00(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('[');
        boolean z = true;
        for (E e : this) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            if (e == this) {
                sb.append("(this Collection)");
            } else {
                sb.append((Object) e);
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public boolean A03(Collection<?> collection) {
        Iterator<E> it = iterator();
        if (collection != null) {
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }
        throw null;
    }

    public final <T> T[] A04(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        int i = 0;
        for (E e : this) {
            tArr[i] = e;
            i++;
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public final boolean add(E e) {
        return A00().add(e);
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        return A00().addAll(collection);
    }

    public void clear() {
        A00().clear();
    }

    public boolean contains(Object obj) {
        return A00().contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return A00().containsAll(collection);
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return A00().iterator();
    }

    @CanIgnoreReturnValue
    public boolean remove(Object obj) {
        return A00().remove(obj);
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public boolean removeAll(Collection<?> collection) {
        return A00().removeAll(collection);
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public boolean retainAll(Collection<?> collection) {
        return A00().retainAll(collection);
    }

    public final int size() {
        return A00().size();
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        return (T[]) A00().toArray(tArr);
    }

    public Object[] toArray() {
        return A00().toArray();
    }
}
