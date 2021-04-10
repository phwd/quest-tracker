package X;

import androidx.annotation.Nullable;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0AL  reason: invalid class name */
public final class AnonymousClass0AL {
    public static AnonymousClass0AL A02 = new AnonymousClass0AL();
    public final Map<Class<?>, AnonymousClass0AJ> A00 = new HashMap();
    public final Map<Class<?>, Boolean> A01 = new HashMap();

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;[Ljava/lang/reflect/Method;)LX/0AJ; */
    public static AnonymousClass0AJ A00(AnonymousClass0AL r12, @Nullable Class cls, Method[] methodArr) {
        int i;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            AnonymousClass0AJ r0 = r12.A00.get(superclass);
            if (r0 == null) {
                r0 = A00(r12, superclass, null);
            }
            hashMap.putAll(r0.A00);
        }
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            AnonymousClass0AJ r02 = r12.A00.get(cls2);
            if (r02 == null) {
                r02 = A00(r12, cls2, null);
            }
            for (Map.Entry<AnonymousClass0AK, AnonymousClass0AO> entry : r02.A00.entrySet()) {
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
                } else if (parameterTypes[0].isAssignableFrom(AnonymousClass0AS.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                AnonymousClass0AO value = onLifecycleEvent.value();
                if (length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(AnonymousClass0AO.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (value == AnonymousClass0AO.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (length <= 2) {
                    A01(hashMap, new AnonymousClass0AK(i, method), value, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        AnonymousClass0AJ r2 = new AnonymousClass0AJ(hashMap);
        r12.A00.put(cls, r2);
        r12.A01.put(cls, Boolean.valueOf(z));
        return r2;
    }

    public static void A01(Map<AnonymousClass0AK, AnonymousClass0AO> map, AnonymousClass0AK r5, AnonymousClass0AO r6, Class<?> cls) {
        AnonymousClass0AO r3 = map.get(r5);
        if (r3 == null) {
            map.put(r5, r6);
        } else if (r6 != r3) {
            Method method = r5.A01;
            StringBuilder sb = new StringBuilder("Method ");
            sb.append(method.getName());
            sb.append(" in ");
            sb.append(cls.getName());
            sb.append(" already declared with different @OnLifecycleEvent value: previous value ");
            sb.append(r3);
            sb.append(", new value ");
            sb.append(r6);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
