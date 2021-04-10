package java.util;

import java.io.Serializable;

public class LinkedHashSet extends HashSet implements Set, Cloneable, Serializable {
    private static final long serialVersionUID = -2851667679971038690L;

    public LinkedHashSet() {
        super(16, 0.75f, true);
    }

    public LinkedHashSet(Collection collection) {
        super(Math.max(collection.size() * 2, 11), 0.75f, true);
        addAll(collection);
    }
}
