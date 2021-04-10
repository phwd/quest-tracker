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

/* renamed from: X.13k  reason: invalid class name */
public final class AnonymousClass13k {
    public final AnonymousClass14G A00 = AnonymousClass14G.A00;
    public final Map<Type, InstanceCreator<?>> A01;

    public final <T> AnonymousClass143<T> A00(AnonymousClass14H<T> r5) {
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
            return new AnonymousClass0eI(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            if (Collection.class.isAssignableFrom(cls)) {
                if (SortedSet.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eH(this);
                }
                if (EnumSet.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eG(this, type);
                }
                if (Set.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eF(this);
                }
                if (Queue.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eE(this);
                }
                return new AnonymousClass0eD(this);
            } else if (!Map.class.isAssignableFrom(cls)) {
                return new AnonymousClass0eJ(this, cls, type);
            } else {
                if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eC(this);
                }
                if (ConcurrentMap.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eN(this);
                }
                if (SortedMap.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0eM(this);
                }
                if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new AnonymousClass14H(((ParameterizedType) type).getActualTypeArguments()[0]).A01)) {
                    return new AnonymousClass0eK(this);
                }
                return new AnonymousClass0eL(this);
            }
        }
    }

    public final String toString() {
        return this.A01.toString();
    }

    public AnonymousClass13k(Map<Type, InstanceCreator<?>> map) {
        this.A01 = map;
    }
}
