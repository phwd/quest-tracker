package X;

import androidx.annotation.Nullable;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.Bl  reason: case insensitive filesystem */
public final class C0037Bl {
    public static C0037Bl A02 = new C0037Bl();
    public final Map<Class<?>, Bj> A00 = new HashMap();
    public final Map<Class<?>, Boolean> A01 = new HashMap();

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;[Ljava/lang/reflect/Method;)LX/Bj; */
    public static Bj A00(C0037Bl bl, @Nullable Class cls, Method[] methodArr) {
        String str;
        int i;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            Bj bj = bl.A00.get(superclass);
            if (bj == null) {
                bj = A00(bl, superclass, null);
            }
            hashMap.putAll(bj.A00);
        }
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            Bj bj2 = bl.A00.get(cls2);
            if (bj2 == null) {
                bj2 = A00(bl, cls2, null);
            }
            for (Map.Entry<C0036Bk, EnumC0039Bo> entry : bj2.A00.entrySet()) {
                A01(hashMap, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            OnLifecycleEvent onLifecycleEvent = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (onLifecycleEvent != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                int length = parameterTypes.length;
                if (length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(Bs.class)) {
                    i = 1;
                } else {
                    str = "invalid parameter type. Must be one and instanceof LifecycleOwner";
                    throw new IllegalArgumentException(str);
                }
                EnumC0039Bo value = onLifecycleEvent.value();
                if (length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(EnumC0039Bo.class)) {
                        str = "invalid parameter type. second arg must be an event";
                    } else if (value == EnumC0039Bo.ON_ANY) {
                        i = 2;
                    } else {
                        str = "Second arg is supported only for ON_ANY value";
                    }
                    throw new IllegalArgumentException(str);
                }
                if (length <= 2) {
                    A01(hashMap, new C0036Bk(i, method), value, cls);
                    z = true;
                } else {
                    str = "cannot have more than 2 params";
                    throw new IllegalArgumentException(str);
                }
            }
        }
        Bj bj3 = new Bj(hashMap);
        bl.A00.put(cls, bj3);
        bl.A01.put(cls, Boolean.valueOf(z));
        return bj3;
    }

    public static void A01(Map<C0036Bk, EnumC0039Bo> map, C0036Bk bk, EnumC0039Bo bo, Class<?> cls) {
        EnumC0039Bo bo2 = map.get(bk);
        if (bo2 == null) {
            map.put(bk, bo);
        } else if (bo != bo2) {
            Method method = bk.A01;
            StringBuilder sb = new StringBuilder("Method ");
            sb.append(method.getName());
            sb.append(" in ");
            sb.append(cls.getName());
            sb.append(" already declared with different @OnLifecycleEvent value: previous value ");
            sb.append(bo2);
            sb.append(", new value ");
            sb.append(bo);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
