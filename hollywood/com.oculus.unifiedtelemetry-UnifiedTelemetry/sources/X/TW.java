package X;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

public final class TW {
    public final AbstractC0448kW A00 = AbstractC0448kW.A00;
    public final Map<Type, InstanceCreator<?>> A01;

    public final <T> VE<T> A00(lQ<T> lQVar) {
        Type type = lQVar.type;
        Class<? super T> cls = lQVar.rawType;
        Map<Type, InstanceCreator<?>> map = this.A01;
        map.get(type);
        map.get(cls);
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.A00.A00(declaredConstructor);
            }
            return new C0172Tr(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            if (Collection.class.isAssignableFrom(cls)) {
                if (SortedSet.class.isAssignableFrom(cls)) {
                    return new C0171Tq(this);
                }
                if (EnumSet.class.isAssignableFrom(cls)) {
                    return new C0170Tp(this, type);
                }
                if (Set.class.isAssignableFrom(cls)) {
                    return new C0169To(this);
                }
                if (Queue.class.isAssignableFrom(cls)) {
                    return new Tn(this);
                }
                return new C0168Tm(this);
            } else if (!Map.class.isAssignableFrom(cls)) {
                return new C0173Ts(this, cls, type);
            } else {
                if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                    return new Tl(this);
                }
                if (ConcurrentMap.class.isAssignableFrom(cls)) {
                    return new C0176Tw(this);
                }
                if (SortedMap.class.isAssignableFrom(cls)) {
                    return new C0175Tv(this);
                }
                if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new lQ(((ParameterizedType) type).getActualTypeArguments()[0]).rawType)) {
                    return new C0174Tt(this);
                }
                return new Tu(this);
            }
        }
    }

    public final String toString() {
        return this.A01.toString();
    }

    public TW(Map<Type, InstanceCreator<?>> map) {
        this.A01 = map;
    }
}
