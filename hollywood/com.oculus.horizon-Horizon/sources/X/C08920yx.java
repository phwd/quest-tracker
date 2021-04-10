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

/* renamed from: X.0yx  reason: invalid class name and case insensitive filesystem */
public final class C08920yx {
    public final AbstractC09100zP A00 = AbstractC09100zP.A00;
    public final Map<Type, InstanceCreator<?>> A01;

    public final <T> AbstractC09010zC<T> A00(C09110zQ<T> r5) {
        Type type = r5.A02;
        Class<? super T> cls = r5.A01;
        Map<Type, InstanceCreator<?>> map = this.A01;
        map.get(type);
        map.get(cls);
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.A00.A00(declaredConstructor);
            }
            return new C02810bZ(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            if (Collection.class.isAssignableFrom(cls)) {
                if (SortedSet.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0bX(this);
                }
                if (EnumSet.class.isAssignableFrom(cls)) {
                    return new C02630av(this, type);
                }
                if (Set.class.isAssignableFrom(cls)) {
                    return new C02560ak(this);
                }
                if (Queue.class.isAssignableFrom(cls)) {
                    return new C02380aO(this);
                }
                return new C02370aN(this);
            } else if (!Map.class.isAssignableFrom(cls)) {
                return new C03000bt(this, cls, type);
            } else {
                if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                    return new C02360aM(this);
                }
                if (ConcurrentMap.class.isAssignableFrom(cls)) {
                    return new C03050bz(this);
                }
                if (SortedMap.class.isAssignableFrom(cls)) {
                    return new C03040bx(this);
                }
                if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new C09110zQ(((ParameterizedType) type).getActualTypeArguments()[0]).A01)) {
                    return new C03010bu(this);
                }
                return new C03020bv(this);
            }
        }
    }

    public final String toString() {
        return this.A01.toString();
    }

    public C08920yx(Map<Type, InstanceCreator<?>> map) {
        this.A01 = map;
    }
}
