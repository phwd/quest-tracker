package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedListMultimap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: X.tw  reason: case insensitive filesystem */
public abstract class AbstractC1156tw implements UK {
    public transient Collection A00;
    public transient Map A01;
    public transient Set A02;

    public Collection A02() {
        Collection collection = this.A00;
        if (collection == null) {
            if (this instanceof LinkedListMultimap) {
                collection = new UD((LinkedListMultimap) this);
            } else if (this instanceof ImmutableMultimap) {
                collection = new ImmutableMultimap.EntryCollection((ImmutableMultimap) this);
            } else if (this instanceof AbstractC1183ui) {
                collection = new C0146Dl(this);
            } else {
                collection = new C1155tv(this);
            }
            this.A00 = collection;
        }
        return collection;
    }

    @Override // X.UK
    public final Map A1G() {
        boolean z = this instanceof ImmutableMultimap;
        if (z) {
            return ((ImmutableMultimap) this).A01;
        }
        Map map = this.A01;
        if (map == null) {
            if (this instanceof LinkedListMultimap) {
                map = new C1177uY(this);
            } else if (!z) {
                AbstractMapBasedMultimap abstractMapBasedMultimap = (AbstractMapBasedMultimap) this;
                map = new C1149tp(abstractMapBasedMultimap, abstractMapBasedMultimap.A01);
            } else {
                throw new AssertionError("should never be called");
            }
            this.A01 = map;
        }
        return map;
    }

    @Override // X.UK
    public final boolean A4Y(Object obj, Object obj2) {
        if (this instanceof LinkedListMultimap) {
            LinkedListMultimap.A00((LinkedListMultimap) this, obj, obj2, null);
            return true;
        } else if (this instanceof ImmutableMultimap) {
            throw new UnsupportedOperationException();
        } else if (!(this instanceof AbstractMapBasedMultimap)) {
            return A2E(obj).add(obj2);
        } else {
            AbstractMapBasedMultimap abstractMapBasedMultimap = (AbstractMapBasedMultimap) this;
            Collection collection = (Collection) abstractMapBasedMultimap.A01.get(obj);
            if (collection == null) {
                Collection A03 = abstractMapBasedMultimap.A03();
                if (A03.add(obj2)) {
                    abstractMapBasedMultimap.A00++;
                    abstractMapBasedMultimap.A01.put(obj, A03);
                    return true;
                }
                throw new AssertionError("New Collection violated the Collection spec");
            } else if (!collection.add(obj2)) {
                return false;
            } else {
                abstractMapBasedMultimap.A00++;
                return true;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UK) {
            return A1G().equals(((UK) obj).A1G());
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000b A[ORIG_RETURN, RETURN, SYNTHETIC] */
    @Override // X.UK
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isEmpty() {
        /*
            r2 = this;
            boolean r0 = r2 instanceof com.google.common.collect.LinkedListMultimap
            if (r0 != 0) goto L_0x000d
            int r1 = r2.size()
            r0 = 0
            if (r1 != 0) goto L_0x000c
        L_0x000b:
            r0 = 1
        L_0x000c:
            return r0
        L_0x000d:
            r0 = r2
            com.google.common.collect.LinkedListMultimap r0 = (com.google.common.collect.LinkedListMultimap) r0
            X.uP r1 = r0.A02
            r0 = 0
            if (r1 != 0) goto L_0x000c
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1156tw.isEmpty():boolean");
    }

    @Override // X.UK
    public final Set keySet() {
        boolean z = this instanceof ImmutableMultimap;
        if (z) {
            return ((ImmutableMultimap) this).A01.A08();
        }
        Set set = this.A02;
        if (set == null) {
            if (this instanceof LinkedListMultimap) {
                set = new C1167uO((LinkedListMultimap) this);
            } else if (!z) {
                AbstractMapBasedMultimap abstractMapBasedMultimap = (AbstractMapBasedMultimap) this;
                set = new C0152Du(abstractMapBasedMultimap, abstractMapBasedMultimap.A01);
            } else {
                throw new AssertionError("unreachable");
            }
            this.A02 = set;
        }
        return set;
    }

    @Override // X.UK
    public final boolean remove(Object obj, Object obj2) {
        if (!(this instanceof ImmutableMultimap)) {
            Collection collection = (Collection) A1G().get(obj);
            if (collection == null || !collection.remove(obj2)) {
                return false;
            }
            return true;
        }
        throw new UnsupportedOperationException();
    }

    @Override // X.UK
    public final boolean A1U(Object obj, Object obj2) {
        Collection collection = (Collection) A1G().get(obj);
        if (collection == null || !collection.contains(obj2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return A1G().hashCode();
    }

    public final String toString() {
        return A1G().toString();
    }
}
