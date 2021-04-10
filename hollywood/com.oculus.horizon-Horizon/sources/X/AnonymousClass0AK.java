package X;

import androidx.annotation.Nullable;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0AK  reason: invalid class name */
public final class AnonymousClass0AK {
    public static AnonymousClass0AK A02 = new AnonymousClass0AK();
    public final Map<Class<?>, AnonymousClass0AI> A00 = new HashMap();
    public final Map<Class<?>, Boolean> A01 = new HashMap();

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;[Ljava/lang/reflect/Method;)LX/0AI; */
    public static AnonymousClass0AI A00(AnonymousClass0AK r12, @Nullable Class cls, Method[] methodArr) {
        String str;
        int i;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            AnonymousClass0AI r0 = r12.A00.get(superclass);
            if (r0 == null) {
                r0 = A00(r12, superclass, null);
            }
            hashMap.putAll(r0.A00);
        }
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            AnonymousClass0AI r02 = r12.A00.get(cls2);
            if (r02 == null) {
                r02 = A00(r12, cls2, null);
            }
            for (Map.Entry<AnonymousClass0AJ, AnonymousClass0AN> entry : r02.A00.entrySet()) {
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
                } else if (parameterTypes[0].isAssignableFrom(AnonymousClass0AR.class)) {
                    i = 1;
                } else {
                    str = "invalid parameter type. Must be one and instanceof LifecycleOwner";
                    throw new IllegalArgumentException(str);
                }
                AnonymousClass0AN value = onLifecycleEvent.value();
                if (length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(AnonymousClass0AN.class)) {
                        str = "invalid parameter type. second arg must be an event";
                    } else if (value == AnonymousClass0AN.ON_ANY) {
                        i = 2;
                    } else {
                        str = "Second arg is supported only for ON_ANY value";
                    }
                    throw new IllegalArgumentException(str);
                }
                if (length <= 2) {
                    A01(hashMap, new AnonymousClass0AJ(i, method), value, cls);
                    z = true;
                } else {
                    str = "cannot have more than 2 params";
                    throw new IllegalArgumentException(str);
                }
            }
        }
        AnonymousClass0AI r2 = new AnonymousClass0AI(hashMap);
        r12.A00.put(cls, r2);
        r12.A01.put(cls, Boolean.valueOf(z));
        return r2;
    }

    public static void A01(Map<AnonymousClass0AJ, AnonymousClass0AN> map, AnonymousClass0AJ r5, AnonymousClass0AN r6, Class<?> cls) {
        AnonymousClass0AN r3 = map.get(r5);
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
