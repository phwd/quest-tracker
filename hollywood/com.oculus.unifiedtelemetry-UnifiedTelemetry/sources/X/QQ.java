package X;

import java.util.ArrayList;
import java.util.Set;

public class QQ<T> extends ArrayList<T> implements Set<T> {
    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Set, java.util.ArrayList
    public final boolean add(T t) {
        if (contains(t) || !super.add(t)) {
            return false;
        }
        return true;
    }
}
