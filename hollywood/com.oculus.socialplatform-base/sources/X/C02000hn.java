package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: X.0hn  reason: invalid class name and case insensitive filesystem */
public final class C02000hn extends AnonymousClass0qA {
    public static final AnonymousClass0hl[] A0C = new AnonymousClass0hl[0];
    public AnonymousClass0Cs A00;
    public AnonymousClass0qE A01;
    public AnonymousClass0hl A02;
    public List<AnonymousClass0Cs> A03;
    public List<AnonymousClass0Cr> A04;
    public List<AnonymousClass0Oy> A05;
    public boolean A06 = false;
    public final AbstractC02230iJ A07;
    public final AnonymousClass0qF A08;
    public final Class<?> A09;
    public final Class<?> A0A;
    public final List<Class<?>> A0B;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/util/List<Ljava/lang/Class<*>;>;LX/0iJ;LX/0qF;LX/0hl;)V */
    public C02000hn(Class cls, List list, AbstractC02230iJ r5, AnonymousClass0qF r6) {
        Class<?> A3A;
        this.A09 = cls;
        this.A0B = list;
        this.A07 = r5;
        this.A08 = r6;
        if (r6 == null) {
            A3A = null;
        } else {
            A3A = r6.A3A(cls);
        }
        this.A0A = A3A;
        this.A02 = null;
    }

    public static C02000hn A00(Class<?> cls, AbstractC02230iJ r4, AnonymousClass0qF r5) {
        ArrayList arrayList = new ArrayList(8);
        A0E(cls, null, arrayList, false);
        return new C02000hn(cls, arrayList, r4, r5);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/util/Map<Ljava/lang/String;LX/0Oy;>;)Ljava/util/Map<Ljava/lang/String;LX/0Oy;>; */
    private final Map A04(Class cls) {
        Class<?> A3A;
        AbstractC01990hm r1;
        AnonymousClass0hl r12;
        Map map = null;
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            map = A04(superclass);
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (!field.isSynthetic()) {
                    int modifiers = field.getModifiers();
                    if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                        if (map == null) {
                            map = new LinkedHashMap();
                        }
                        String name = field.getName();
                        if (this.A07 == null) {
                            r12 = new AnonymousClass0hl();
                        } else {
                            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                            r12 = new AnonymousClass0hl();
                            A0B(r12, declaredAnnotations);
                        }
                        map.put(name, new AnonymousClass0Oy(field, r12));
                    }
                }
            }
            AnonymousClass0qF r0 = this.A08;
            if (!(r0 == null || (A3A = r0.A3A(cls)) == null)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(A3A);
                A0E(A3A, superclass, arrayList, false);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Field[] declaredFields2 = ((Class) it.next()).getDeclaredFields();
                    for (Field field2 : declaredFields2) {
                        if (!field2.isSynthetic()) {
                            int modifiers2 = field2.getModifiers();
                            if (!Modifier.isStatic(modifiers2) && !Modifier.isTransient(modifiers2) && (r1 = (AbstractC01990hm) map.get(field2.getName())) != null) {
                                A09(r1, field2.getDeclaredAnnotations());
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    private final AnonymousClass0hl[] A0H(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnonymousClass0hl[] r3 = new AnonymousClass0hl[length];
        for (int i = 0; i < length; i++) {
            Annotation[] annotationArr2 = annotationArr[i];
            AnonymousClass0hl r0 = new AnonymousClass0hl();
            A0B(r0, annotationArr2);
            r3[i] = r0;
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        if (r4 == r0) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        r0 = new java.lang.annotation.Annotation[r0][];
        java.lang.System.arraycopy(r6, 0, r0, r1, r3);
        r2 = A0H(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007c, code lost:
        if (r4 == r0) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AnonymousClass0Cs A02(java.lang.reflect.Constructor<?> r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02000hn.A02(java.lang.reflect.Constructor, boolean):X.0Cs");
    }

    private final AnonymousClass0Cr A03(Method method) {
        AnonymousClass0hl r1;
        if (this.A07 == null) {
            r1 = new AnonymousClass0hl();
        } else {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            r1 = new AnonymousClass0hl();
            A0B(r1, declaredAnnotations);
        }
        return new AnonymousClass0Cr(method, r1, null);
    }

    public static void A05(C02000hn r4) {
        AnonymousClass0hl r2 = new AnonymousClass0hl();
        r4.A02 = r2;
        if (r4.A07 != null) {
            Class<?> cls = r4.A0A;
            if (cls != null) {
                r4.A0A(r2, r4.A09, cls);
            }
            r4.A0B(r4.A02, r4.A09.getDeclaredAnnotations());
            for (Class<?> cls2 : r4.A0B) {
                AnonymousClass0hl r1 = r4.A02;
                AnonymousClass0qF r0 = r4.A08;
                if (r0 != null) {
                    r4.A0A(r1, cls2, r0.A3A(cls2));
                }
                r4.A0B(r4.A02, cls2.getDeclaredAnnotations());
            }
            AnonymousClass0hl r22 = r4.A02;
            AnonymousClass0qF r02 = r4.A08;
            if (r02 != null) {
                r4.A0A(r22, Object.class, r02.A3A(Object.class));
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:110:0x0021 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0039 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0, types: [X.0hn] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List<X.0Cs>, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v26, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v27, types: [java.util.AbstractCollection] */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.C02000hn r18) {
        /*
        // Method dump skipped, instructions count: 509
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02000hn.A06(X.0hn):void");
    }

    public static void A07(C02000hn r6) {
        LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap;
        Class<?> A3A;
        Class<?> A3A2;
        AnonymousClass0qE r1 = new AnonymousClass0qE();
        r6.A01 = r1;
        AnonymousClass0qE r4 = new AnonymousClass0qE();
        Class<?> cls = r6.A09;
        r6.A0C(cls, r1, r6.A0A, r4);
        for (Class<?> cls2 : r6.A0B) {
            AnonymousClass0qF r0 = r6.A08;
            if (r0 == null) {
                A3A2 = null;
            } else {
                A3A2 = r0.A3A(cls2);
            }
            r6.A0C(cls2, r6.A01, A3A2, r4);
        }
        AnonymousClass0qF r12 = r6.A08;
        if (!(r12 == null || (A3A = r12.A3A(Object.class)) == null)) {
            r6.A0D(cls, r6.A01, A3A, r4);
        }
        if (!(r6.A07 == null || (linkedHashMap = r4.A00) == null || linkedHashMap.size() == 0)) {
            Iterator<AnonymousClass0Cr> it = r4.iterator();
            while (it.hasNext()) {
                AnonymousClass0Cr next = it.next();
                try {
                    String A0L = next.A0L();
                    Class<?>[] clsArr = next._paramClasses;
                    if (clsArr == null) {
                        clsArr = next.A00.getParameterTypes();
                        next._paramClasses = clsArr;
                    }
                    Method declaredMethod = Object.class.getDeclaredMethod(A0L, clsArr);
                    if (declaredMethod != null) {
                        AnonymousClass0Cr A032 = r6.A03(declaredMethod);
                        r6.A0F(next.A00, A032, false);
                        r6.A01.A00(A032);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void A08(AbstractC01990hm r6, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC02230iJ r0 = this.A07;
                if (r0 == null || !r0.A0t(annotation)) {
                    r6.A00.A00(annotation);
                } else {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                }
            }
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    A08(r6, (Annotation[]) it.next());
                }
            }
        }
    }

    private void A09(AbstractC01990hm r7, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC02230iJ r0 = this.A07;
                if (r0 == null || !r0.A0t(annotation)) {
                    AnonymousClass0hl r02 = r7.A00;
                    HashMap<Class<? extends Annotation>, Annotation> hashMap = r02.A00;
                    if (hashMap == null) {
                        hashMap = new HashMap<>();
                        r02.A00 = hashMap;
                    }
                    hashMap.put(annotation.annotationType(), annotation);
                } else {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                }
            }
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    A09(r7, (Annotation[]) it.next());
                }
            }
        }
    }

    private final void A0A(AnonymousClass0hl r3, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            A0B(r3, cls2.getDeclaredAnnotations());
            ArrayList<Class> arrayList = new ArrayList(8);
            A0E(cls2, cls, arrayList, false);
            for (Class cls3 : arrayList) {
                A0B(r3, cls3.getDeclaredAnnotations());
            }
        }
    }

    private void A0B(AnonymousClass0hl r6, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC02230iJ r0 = this.A07;
                if (r0 == null || !r0.A0t(annotation)) {
                    r6.A00(annotation);
                } else {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                }
            }
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    A0B(r6, (Annotation[]) it.next());
                }
            }
        }
    }

    private final void A0C(Class<?> cls, AnonymousClass0qE r10, Class<?> cls2, AnonymousClass0qE r12) {
        AnonymousClass0Cr remove;
        AnonymousClass0Cr r1;
        if (cls2 != null) {
            A0D(cls, r10, cls2, r12);
        }
        if (cls != null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap = r10.A00;
                    if (linkedHashMap == null || (r1 = linkedHashMap.get(new C04470qI(method))) == null) {
                        AnonymousClass0Cr A032 = A03(method);
                        r10.A00(A032);
                        LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap2 = r12.A00;
                        if (!(linkedHashMap2 == null || (remove = linkedHashMap2.remove(new C04470qI(method))) == null)) {
                            A0F(remove.A00, A032, false);
                        }
                    } else {
                        A08(r1, method.getDeclaredAnnotations());
                        if (r1.A0P().isInterface() && !method.getDeclaringClass().isInterface()) {
                            r10.A00(new AnonymousClass0Cr(method, ((AbstractC01990hm) r1).A00, r1._paramAnnotations));
                        }
                    }
                }
            }
        }
    }

    private final void A0D(Class<?> cls, AnonymousClass0qE r9, Class<?> cls2, AnonymousClass0qE r11) {
        AnonymousClass0Cr r1;
        ArrayList arrayList = new ArrayList();
        arrayList.add(cls2);
        A0E(cls2, cls, arrayList, false);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Method[] declaredMethods = ((Class) it.next()).getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap = r9.A00;
                    if (linkedHashMap == null || (r1 = linkedHashMap.get(new C04470qI(method))) == null) {
                        r11.A00(A03(method));
                    } else {
                        A08(r1, method.getDeclaredAnnotations());
                    }
                }
            }
        }
    }

    public static void A0E(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (!(cls == cls2 || cls == null || cls == Object.class)) {
            if (z) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class<?> cls3 : cls.getInterfaces()) {
                A0E(cls3, cls2, collection, true);
            }
            A0E(cls.getSuperclass(), cls2, collection, true);
        }
    }

    @Override // X.AnonymousClass0qA
    public final String A0L() {
        return this.A09.getName();
    }

    @Override // X.AnonymousClass0qA
    public final <A extends Annotation> A A0M(Class<A> cls) {
        if (this.A02 == null) {
            A05(this);
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A02.A00;
        if (hashMap == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    public final Iterable<AnonymousClass0Oy> A0P() {
        if (this.A05 == null) {
            Map A042 = A04(this.A09);
            if (A042 == null || A042.size() == 0) {
                this.A05 = Collections.emptyList();
            } else {
                ArrayList arrayList = new ArrayList(A042.size());
                this.A05 = arrayList;
                arrayList.addAll(A042.values());
            }
        }
        return this.A05;
    }

    public final String toString() {
        return AnonymousClass006.A09("[AnnotedClass ", this.A09.getName(), "]");
    }

    public static C02000hn A01(Class<?> cls, AbstractC02230iJ r3, AnonymousClass0qF r4) {
        return new C02000hn(cls, Collections.emptyList(), r3, r4);
    }

    private final void A0F(Method method, AnonymousClass0Cr r9, boolean z) {
        A09(r9, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    r9.A0Y(i, annotation);
                }
            }
        }
    }

    public static final boolean A0G(Method method) {
        if (Modifier.isStatic(method.getModifiers()) || method.isSynthetic() || method.isBridge() || method.getParameterTypes().length > 2) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass0qA
    public final Class<?> A0K() {
        return this.A09;
    }

    @Override // X.AnonymousClass0qA
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this.A09;
    }

    @Override // X.AnonymousClass0qA
    public final Type A0O() {
        return this.A09;
    }
}
