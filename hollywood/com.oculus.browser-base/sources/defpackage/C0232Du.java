package defpackage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: Du  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0232Du {

    /* renamed from: a  reason: collision with root package name */
    public static C0232Du f7922a = new C0232Du();
    public final Map b = new HashMap();
    public final Map c = new HashMap();

    public final C0110Bu a(Class cls, Method[] methodArr) {
        int i;
        C0110Bu b2;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (!(superclass == null || (b2 = b(superclass)) == null)) {
            hashMap.putAll(b2.b);
        }
        for (Class cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : b(cls2).b.entrySet()) {
                c(hashMap, (C0171Cu) entry.getKey(), (EnumC3157j80) entry.getValue(), cls);
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
            AbstractC0960Ps0 ps0 = (AbstractC0960Ps0) method.getAnnotation(AbstractC0960Ps0.class);
            if (ps0 != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(AbstractC4524r80.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                EnumC3157j80 value = ps0.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(EnumC3157j80.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (value == EnumC3157j80.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (parameterTypes.length <= 2) {
                    c(hashMap, new C0171Cu(i, method), value, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0110Bu bu = new C0110Bu(hashMap);
        this.b.put(cls, bu);
        this.c.put(cls, Boolean.valueOf(z));
        return bu;
    }

    public C0110Bu b(Class cls) {
        C0110Bu bu = (C0110Bu) this.b.get(cls);
        if (bu != null) {
            return bu;
        }
        return a(cls, null);
    }

    public final void c(Map map, C0171Cu cu, EnumC3157j80 j80, Class cls) {
        EnumC3157j80 j802 = (EnumC3157j80) map.get(cu);
        if (j802 != null && j80 != j802) {
            Method method = cu.b;
            StringBuilder i = AbstractC2531fV.i("Method ");
            i.append(method.getName());
            i.append(" in ");
            i.append(cls.getName());
            i.append(" already declared with different @OnLifecycleEvent value: previous value ");
            i.append(j802);
            i.append(", new value ");
            i.append(j80);
            throw new IllegalArgumentException(i.toString());
        } else if (j802 == null) {
            map.put(cu, j80);
        }
    }
}
