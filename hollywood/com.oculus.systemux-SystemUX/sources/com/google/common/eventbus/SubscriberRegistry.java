package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public final class SubscriberRegistry {
    private static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableSet<Class<?>>>() {
        /* class com.google.common.eventbus.SubscriberRegistry.AnonymousClass2 */

        public ImmutableSet<Class<?>> load(Class<?> cls) {
            return ImmutableSet.copyOf((Collection) TypeToken.of((Class) cls).getTypes().rawTypes());
        }
    });
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableList<Method>>() {
        /* class com.google.common.eventbus.SubscriberRegistry.AnonymousClass1 */

        public ImmutableList<Method> load(Class<?> cls) throws Exception {
            return SubscriberRegistry.getAnnotatedMethodsNotCached(cls);
        }
    });
    @Weak
    private final EventBus bus;
    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers = Maps.newConcurrentMap();

    SubscriberRegistry(EventBus eventBus) {
        this.bus = (EventBus) Preconditions.checkNotNull(eventBus);
    }

    /* access modifiers changed from: package-private */
    public void register(Object obj) {
        for (Map.Entry<Class<?>, Collection<Subscriber>> entry : findAllSubscribers(obj).asMap().entrySet()) {
            Class<?> key = entry.getKey();
            Collection<Subscriber> value = entry.getValue();
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(key);
            if (copyOnWriteArraySet == null) {
                CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet2 = new CopyOnWriteArraySet<>();
                copyOnWriteArraySet = (CopyOnWriteArraySet) MoreObjects.firstNonNull(this.subscribers.putIfAbsent(key, copyOnWriteArraySet2), copyOnWriteArraySet2);
            }
            copyOnWriteArraySet.addAll(value);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregister(java.lang.Object r5) {
        /*
            r4 = this;
            com.google.common.collect.Multimap r0 = r4.findAllSubscribers(r5)
            java.util.Map r0 = r0.asMap()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0010:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0055
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.Class r2 = (java.lang.Class) r2
            java.lang.Object r1 = r1.getValue()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, java.util.concurrent.CopyOnWriteArraySet<com.google.common.eventbus.Subscriber>> r3 = r4.subscribers
            java.lang.Object r2 = r3.get(r2)
            java.util.concurrent.CopyOnWriteArraySet r2 = (java.util.concurrent.CopyOnWriteArraySet) r2
            if (r2 == 0) goto L_0x0039
            boolean r1 = r2.removeAll(r1)
            if (r1 == 0) goto L_0x0039
            goto L_0x0010
        L_0x0039:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "missing event subscriber for an annotated method. Is "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = " registered?"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.eventbus.SubscriberRegistry.unregister(java.lang.Object):void");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Set<Subscriber> getSubscribersForTesting(Class<?> cls) {
        return (Set) MoreObjects.firstNonNull(this.subscribers.get(cls), ImmutableSet.of());
    }

    /* access modifiers changed from: package-private */
    public Iterator<Subscriber> getSubscribers(Object obj) {
        ImmutableSet<Class<?>> flattenHierarchy = flattenHierarchy(obj.getClass());
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(flattenHierarchy.size());
        UnmodifiableIterator<Class<?>> it = flattenHierarchy.iterator();
        while (it.hasNext()) {
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(it.next());
            if (copyOnWriteArraySet != null) {
                newArrayListWithCapacity.add(copyOnWriteArraySet.iterator());
            }
        }
        return Iterators.concat(newArrayListWithCapacity.iterator());
    }

    private Multimap<Class<?>, Subscriber> findAllSubscribers(Object obj) {
        HashMultimap create = HashMultimap.create();
        UnmodifiableIterator<Method> it = getAnnotatedMethods(obj.getClass()).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            create.put(next.getParameterTypes()[0], Subscriber.create(this.bus, obj, next));
        }
        return create;
    }

    private static ImmutableList<Method> getAnnotatedMethods(Class<?> cls) {
        return subscriberMethodsCache.getUnchecked(cls);
    }

    /* access modifiers changed from: private */
    public static ImmutableList<Method> getAnnotatedMethodsNotCached(Class<?> cls) {
        Set<Class> rawTypes = TypeToken.of((Class) cls).getTypes().rawTypes();
        HashMap newHashMap = Maps.newHashMap();
        for (Class cls2 : rawTypes) {
            Method[] declaredMethods = cls2.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    boolean z = true;
                    if (parameterTypes.length != 1) {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "Method %s has @Subscribe annotation but has %s parameters.Subscriber methods must have exactly 1 parameter.", (Object) method, parameterTypes.length);
                    MethodIdentifier methodIdentifier = new MethodIdentifier(method);
                    if (!newHashMap.containsKey(methodIdentifier)) {
                        newHashMap.put(methodIdentifier, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(newHashMap.values());
    }

    @VisibleForTesting
    static ImmutableSet<Class<?>> flattenHierarchy(Class<?> cls) {
        try {
            return flattenHierarchyCache.getUnchecked(cls);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    /* access modifiers changed from: private */
    public static final class MethodIdentifier {
        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public int hashCode() {
            return Objects.hashCode(this.name, this.parameterTypes);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (!(obj instanceof MethodIdentifier)) {
                return false;
            }
            MethodIdentifier methodIdentifier = (MethodIdentifier) obj;
            if (!this.name.equals(methodIdentifier.name) || !this.parameterTypes.equals(methodIdentifier.parameterTypes)) {
                return false;
            }
            return true;
        }
    }
}
