package X;

import androidx.annotation.Nullable;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0DT  reason: invalid class name */
public final class AnonymousClass0DT {
    public static AnonymousClass0DT A02 = new AnonymousClass0DT();
    public final Map<Class<?>, AnonymousClass0DR> A00 = new HashMap();
    public final Map<Class<?>, Boolean> A01 = new HashMap();

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;[Ljava/lang/reflect/Method;)LX/0DR; */
    public static AnonymousClass0DR A00(AnonymousClass0DT r12, @Nullable Class cls, Method[] methodArr) {
        String str;
        int i;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            AnonymousClass0DR r0 = r12.A00.get(superclass);
            if (r0 == null) {
                r0 = A00(r12, superclass, null);
            }
            hashMap.putAll(r0.A00);
        }
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            AnonymousClass0DR r02 = r12.A00.get(cls2);
            if (r02 == null) {
                r02 = A00(r12, cls2, null);
            }
            for (Map.Entry<AnonymousClass0DS, AnonymousClass0DW> entry : r02.A00.entrySet()) {
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
                } else if (parameterTypes[0].isAssignableFrom(AbstractC01030Da.class)) {
                    i = 1;
                } else {
                    str = "invalid parameter type. Must be one and instanceof LifecycleOwner";
                    throw new IllegalArgumentException(str);
                }
                AnonymousClass0DW value = onLifecycleEvent.value();
                if (length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(AnonymousClass0DW.class)) {
                        str = "invalid parameter type. second arg must be an event";
                    } else if (value == AnonymousClass0DW.ON_ANY) {
                        i = 2;
                    } else {
                        str = "Second arg is supported only for ON_ANY value";
                    }
                    throw new IllegalArgumentException(str);
                }
                if (length <= 2) {
                    A01(hashMap, new AnonymousClass0DS(i, method), value, cls);
                    z = true;
                } else {
                    str = "cannot have more than 2 params";
                    throw new IllegalArgumentException(str);
                }
            }
        }
        AnonymousClass0DR r2 = new AnonymousClass0DR(hashMap);
        r12.A00.put(cls, r2);
        r12.A01.put(cls, Boolean.valueOf(z));
        return r2;
    }

    public static void A01(Map<AnonymousClass0DS, AnonymousClass0DW> map, AnonymousClass0DS r5, AnonymousClass0DW r6, Class<?> cls) {
        AnonymousClass0DW r3 = map.get(r5);
        if (r3 == null) {
            map.put(r5, r6);
        } else if (r6 != r3) {
            Method method = r5.A01;
            throw new IllegalArgumentException("Method " + method.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + r3 + ", new value " + r6);
        }
    }
}
