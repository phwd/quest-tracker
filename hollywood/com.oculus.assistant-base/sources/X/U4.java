package X;

import com.google.common.collect.CompactHashMap;
import com.google.common.collect.CompactHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class U4 {
    public Map A00 = new CompactHashMap();

    public U4 A00(Object obj, Iterable iterable) {
        Collection collection;
        if (obj != null) {
            Collection collection2 = (Collection) this.A00.get(obj);
            Iterator it = iterable.iterator();
            if (collection2 != null) {
                while (it.hasNext()) {
                    Object next = it.next();
                    C0361Tm.A01(obj, next);
                    collection2.add(next);
                }
            } else if (it.hasNext()) {
                if (!(this instanceof C1164uJ)) {
                    collection = new ArrayList();
                } else {
                    collection = new CompactHashSet();
                }
                while (it.hasNext()) {
                    Object next2 = it.next();
                    C0361Tm.A01(obj, next2);
                    collection.add(next2);
                }
                this.A00.put(obj, collection);
                return this;
            }
            return this;
        }
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        for (Object obj2 : iterable) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(obj2);
        }
        sb.append(']');
        throw new NullPointerException(AnonymousClass08.A04("null key in entry: null=", sb.toString()));
    }
}
