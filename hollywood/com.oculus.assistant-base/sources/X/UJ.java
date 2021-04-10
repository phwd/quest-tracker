package X;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;

public abstract class UJ<K, V> extends AbstractMap<K, V> {
    public transient Collection A00;
    public transient Set A01;
    public transient Set A02;

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.A01;
        if (set == null) {
            if (!(this instanceof C1177uY)) {
                set = new Dz((C1149tp) this);
            } else {
                set = new C0132Cl((C1177uY) this);
            }
            this.A01 = set;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        Set set = this.A02;
        if (set != null) {
            return set;
        }
        C1175uW uWVar = new C1175uW(this);
        this.A02 = uWVar;
        return uWVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.A00;
        if (collection != null) {
            return collection;
        }
        UI ui = new UI(this);
        this.A00 = ui;
        return ui;
    }
}
