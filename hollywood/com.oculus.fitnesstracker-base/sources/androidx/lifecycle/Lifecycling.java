package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Lifecycling {
    private static Map<Class<?>, Integer> sCallbackCache = new HashMap();
    private static Map<Class<?>, List<Constructor<? extends Object>>> sClassToAdapters = new HashMap();

    /* renamed from: androidx.lifecycle.Lifecycling$1  reason: invalid class name */
    static class AnonymousClass1 {
    }
}
