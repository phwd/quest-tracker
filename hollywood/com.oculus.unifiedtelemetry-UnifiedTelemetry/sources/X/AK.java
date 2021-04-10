package X;

import androidx.annotation.Nullable;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class AK {
    public static AK A02 = new AK();
    public final Map<Class<?>, AI> A00 = new HashMap();
    public final Map<Class<?>, Boolean> A01 = new HashMap();

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;[Ljava/lang/reflect/Method;)LX/AI; */
    public static AI A00(AK ak, @Nullable Class cls, Method[] methodArr) {
        String str;
        int i;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            AI ai = ak.A00.get(superclass);
            if (ai == null) {
                ai = A00(ak, superclass, null);
            }
            hashMap.putAll(ai.A00);
        }
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            AI ai2 = ak.A00.get(cls2);
            if (ai2 == null) {
                ai2 = A00(ak, cls2, null);
            }
            for (Map.Entry<AJ, AN> entry : ai2.A00.entrySet()) {
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
                } else if (parameterTypes[0].isAssignableFrom(AR.class)) {
                    i = 1;
                } else {
                    str = "invalid parameter type. Must be one and instanceof LifecycleOwner";
                    throw new IllegalArgumentException(str);
                }
                AN value = onLifecycleEvent.value();
                if (length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(AN.class)) {
                        str = "invalid parameter type. second arg must be an event";
                    } else if (value == AN.ON_ANY) {
                        i = 2;
                    } else {
                        str = "Second arg is supported only for ON_ANY value";
                    }
                    throw new IllegalArgumentException(str);
                }
                if (length <= 2) {
                    A01(hashMap, new AJ(i, method), value, cls);
                    z = true;
                } else {
                    str = "cannot have more than 2 params";
                    throw new IllegalArgumentException(str);
                }
            }
        }
        AI ai3 = new AI(hashMap);
        ak.A00.put(cls, ai3);
        ak.A01.put(cls, Boolean.valueOf(z));
        return ai3;
    }

    public static void A01(Map<AJ, AN> map, AJ aj, AN an, Class<?> cls) {
        AN an2 = map.get(aj);
        if (an2 == null) {
            map.put(aj, an);
        } else if (an != an2) {
            Method method = aj.A01;
            StringBuilder sb = new StringBuilder("Method ");
            sb.append(method.getName());
            sb.append(" in ");
            sb.append(cls.getName());
            sb.append(" already declared with different @OnLifecycleEvent value: previous value ");
            sb.append(an2);
            sb.append(", new value ");
            sb.append(an);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
