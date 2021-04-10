package X;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import retrofit.Endpoints;

/* renamed from: X.1aA  reason: invalid class name */
public final class AnonymousClass1aA {
    public final AnonymousClass1aB A00;
    public final AbstractC09211aF A01;
    public final String A02;
    public final ThreadLocal<ConcurrentLinkedQueue<C09201aE>> A03;
    public final ThreadLocal<Boolean> A04;
    public final Map<Class<?>, Set<Class<?>>> A05;
    public final ConcurrentMap<Class<?>, Set<AnonymousClass1a9>> A06;
    public final ConcurrentMap<Class<?>, AnonymousClass1a8> A07;

    private void A00(AnonymousClass1a9 r4, AnonymousClass1a8 r5) {
        try {
            Object invoke = r5.A01.invoke(r5.A00, new Object[0]);
            if (invoke != null) {
                A01(invoke, r4);
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof Error) {
                throw e2.getCause();
            }
            throw e2;
        } catch (InvocationTargetException e3) {
            StringBuilder sb = new StringBuilder("Producer ");
            sb.append(r5);
            sb.append(" threw an exception.");
            A02(sb.toString(), e3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final void A01(Object obj, AnonymousClass1a9 r5) {
        try {
            r5.A01.invoke(r5.A00, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof Error) {
                throw e2.getCause();
            }
            throw e2;
        } catch (InvocationTargetException e3) {
            StringBuilder sb = new StringBuilder("Could not dispatch event: ");
            sb.append(obj.getClass());
            sb.append(" to handler ");
            sb.append(r5);
            A02(sb.toString(), e3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final void A03(Object obj) {
        if (obj != null) {
            this.A01.A2R(this);
            Class<?> cls = obj.getClass();
            Map<Class<?>, Set<Class<?>>> map = this.A05;
            Set<Class<?>> set = map.get(cls);
            Set<Object> set2 = set;
            if (set == null) {
                LinkedList linkedList = new LinkedList();
                HashSet hashSet = new HashSet();
                linkedList.add(cls);
                while (!linkedList.isEmpty()) {
                    Class cls2 = (Class) linkedList.remove(0);
                    hashSet.add(cls2);
                    Class superclass = cls2.getSuperclass();
                    if (superclass != null) {
                        linkedList.add(superclass);
                    }
                }
                map.put(cls, hashSet);
                set2 = hashSet;
            }
            boolean z = false;
            for (Object obj2 : set2) {
                Set<AnonymousClass1a9> set3 = this.A06.get(obj2);
                if (set3 != null && !set3.isEmpty()) {
                    z = true;
                    for (AnonymousClass1a9 r2 : set3) {
                        this.A03.get().offer(new C09201aE(obj, r2));
                    }
                }
            }
            if (!z && !(obj instanceof AnonymousClass1Xr)) {
                A03(new AnonymousClass1Xr(this, obj));
            }
            ThreadLocal<Boolean> threadLocal = this.A04;
            if (!threadLocal.get().booleanValue()) {
                threadLocal.set(true);
                while (true) {
                    try {
                        C09201aE poll = this.A03.get().poll();
                        if (poll != null) {
                            A01(poll.A01, poll.A00);
                        } else {
                            return;
                        }
                    } finally {
                        threadLocal.set(false);
                    }
                }
            }
        } else {
            throw new NullPointerException("Event to post must not be null.");
        }
    }

    public final void A04(Object obj) {
        Set<AnonymousClass1a9> putIfAbsent;
        this.A01.A2R(this);
        AnonymousClass1aB r7 = this.A00;
        Map<Class<?>, AnonymousClass1a8> A2d = r7.A2d(obj);
        for (Class<?> cls : A2d.keySet()) {
            AnonymousClass1a8 r3 = A2d.get(cls);
            AnonymousClass1a8 putIfAbsent2 = this.A07.putIfAbsent(cls, r3);
            if (putIfAbsent2 == null) {
                Set<AnonymousClass1a9> set = this.A06.get(cls);
                if (set != null && !set.isEmpty()) {
                    for (AnonymousClass1a9 r0 : set) {
                        A00(r0, r3);
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder("Producer method for type ");
                sb.append(cls);
                sb.append(" found on type ");
                sb.append(r3.A00.getClass());
                sb.append(", but already registered by type ");
                sb.append(putIfAbsent2.A00.getClass());
                sb.append(".");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        Map<Class<?>, Set<AnonymousClass1a9>> A2e = r7.A2e(obj);
        for (Class<?> cls2 : A2e.keySet()) {
            ConcurrentMap<Class<?>, Set<AnonymousClass1a9>> concurrentMap = this.A06;
            Set<AnonymousClass1a9> set2 = concurrentMap.get(cls2);
            if (set2 == null && (putIfAbsent = concurrentMap.putIfAbsent(cls2, (set2 = new CopyOnWriteArraySet<>()))) != null) {
                set2 = putIfAbsent;
            }
            set2.addAll(A2e.get(cls2));
        }
        for (Map.Entry<Class<?>, Set<AnonymousClass1a9>> entry : A2e.entrySet()) {
            AnonymousClass1a8 r2 = this.A07.get(entry.getKey());
            if (r2 != null) {
                for (AnonymousClass1a9 r02 : entry.getValue()) {
                    A00(r02, r2);
                }
            }
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[Bus \"", this.A02, "\"]");
    }

    public static void A02(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(AnonymousClass006.A07(str, ": ", cause.getMessage()), cause);
        }
        throw new RuntimeException(AnonymousClass006.A07(str, ": ", invocationTargetException.getMessage()), invocationTargetException);
    }

    public AnonymousClass1aA() {
        this(AbstractC09211aF.A01);
    }

    public AnonymousClass1aA(AbstractC09211aF r4) {
        AnonymousClass1aB r1 = AnonymousClass1aB.A00;
        this.A06 = new ConcurrentHashMap();
        this.A07 = new ConcurrentHashMap();
        this.A03 = new AnonymousClass1aC(this);
        this.A04 = new AnonymousClass1aD(this);
        this.A05 = new HashMap();
        this.A01 = r4;
        this.A02 = Endpoints.DEFAULT_NAME;
        this.A00 = r1;
    }
}
