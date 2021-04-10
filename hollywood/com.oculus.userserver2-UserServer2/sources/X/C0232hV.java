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

/* renamed from: X.hV  reason: case insensitive filesystem */
public final class C0232hV {
    public final h7 A00 = h7.A00;
    public final Map<Type, InstanceCreator<?>> A01;

    public final <T> hL<T> A00(h6<T> h6Var) {
        Type type = h6Var.type;
        Class<? super T> cls = h6Var.rawType;
        Map<Type, InstanceCreator<?>> map = this.A01;
        map.get(type);
        map.get(cls);
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.A00.A00(declaredConstructor);
            }
            return new C0094Lp(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            if (Collection.class.isAssignableFrom(cls)) {
                if (SortedSet.class.isAssignableFrom(cls)) {
                    return new C0093Lo(this);
                }
                if (EnumSet.class.isAssignableFrom(cls)) {
                    return new C0092Ln(this, type);
                }
                if (Set.class.isAssignableFrom(cls)) {
                    return new C0091Lm(this);
                }
                if (Queue.class.isAssignableFrom(cls)) {
                    return new C0090Ll(this);
                }
                return new C0089Lk(this);
            } else if (!Map.class.isAssignableFrom(cls)) {
                return new C0095Lq(this, cls, type);
            } else {
                if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                    return new C0088Lj(this);
                }
                if (ConcurrentMap.class.isAssignableFrom(cls)) {
                    return new C0098Lu(this);
                }
                if (SortedMap.class.isAssignableFrom(cls)) {
                    return new C0097Lt(this);
                }
                if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new h6(((ParameterizedType) type).getActualTypeArguments()[0]).rawType)) {
                    return new C0096Lr(this);
                }
                return new Ls(this);
            }
        }
    }

    public final String toString() {
        return this.A01.toString();
    }

    public C0232hV(Map<Type, InstanceCreator<?>> map) {
        this.A01 = map;
    }
}
