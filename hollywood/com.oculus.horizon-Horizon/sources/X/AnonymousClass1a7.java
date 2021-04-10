package X;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: X.1a7  reason: invalid class name */
public class AnonymousClass1a7 implements AnonymousClass1aB {
    @Override // X.AnonymousClass1aB
    public final Map<Class<?>, AnonymousClass1a8> A2d(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        Map<Class<?>, Map<Class<?>, Method>> map = AnonymousClass1a1.A00;
        if (!map.containsKey(cls)) {
            AnonymousClass1a1.A00(cls);
        }
        Map<Class<?>, Method> map2 = map.get(cls);
        if (!map2.isEmpty()) {
            for (Map.Entry<Class<?>, Method> entry : map2.entrySet()) {
                hashMap.put(entry.getKey(), new AnonymousClass1a8(obj, entry.getValue()));
            }
        }
        return hashMap;
    }

    @Override // X.AnonymousClass1aB
    public final Map<Class<?>, Set<AnonymousClass1a9>> A2e(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        Map<Class<?>, Map<Class<?>, Set<Method>>> map = AnonymousClass1a1.A01;
        if (!map.containsKey(cls)) {
            AnonymousClass1a1.A00(cls);
        }
        Map<Class<?>, Set<Method>> map2 = map.get(cls);
        if (!map2.isEmpty()) {
            for (Map.Entry<Class<?>, Set<Method>> entry : map2.entrySet()) {
                HashSet hashSet = new HashSet();
                for (Method method : entry.getValue()) {
                    hashSet.add(new AnonymousClass1a9(obj, method));
                }
                hashMap.put(entry.getKey(), hashSet);
            }
        }
        return hashMap;
    }
}
