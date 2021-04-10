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

/* renamed from: X.0Cp  reason: invalid class name */
public final class AnonymousClass0Cp {
    public final AnonymousClass0Fd A00 = AnonymousClass0Fd.A00;
    public final Map<Type, InstanceCreator<?>> A01;

    public final <T> AbstractC01170Du<T> A00(AnonymousClass0Fe<T> r5) {
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
            return new AnonymousClass0XH(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            if (Collection.class.isAssignableFrom(cls)) {
                if (SortedSet.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XG(this);
                }
                if (EnumSet.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XF(this, type);
                }
                if (Set.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XE(this);
                }
                if (Queue.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XD(this);
                }
                return new AnonymousClass0XC(this);
            } else if (!Map.class.isAssignableFrom(cls)) {
                return new AnonymousClass0XI(this, cls, type);
            } else {
                if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XB(this);
                }
                if (ConcurrentMap.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XM(this);
                }
                if (SortedMap.class.isAssignableFrom(cls)) {
                    return new AnonymousClass0XL(this);
                }
                if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new AnonymousClass0Fe(((ParameterizedType) type).getActualTypeArguments()[0]).A01)) {
                    return new AnonymousClass0XJ(this);
                }
                return new AnonymousClass0XK(this);
            }
        }
    }

    public final String toString() {
        return this.A01.toString();
    }

    public AnonymousClass0Cp(Map<Type, InstanceCreator<?>> map) {
        this.A01 = map;
    }
}
