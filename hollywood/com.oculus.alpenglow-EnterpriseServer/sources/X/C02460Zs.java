package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
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

/* renamed from: X.0Zs  reason: invalid class name and case insensitive filesystem */
public final class C02460Zs extends AbstractC06640nb {
    public static final C02440Zq[] A0C = new C02440Zq[0];
    public AnonymousClass0EJ A00;
    public C06680nf A01;
    public C02440Zq A02;
    public List<AnonymousClass0EJ> A03;
    public List<AnonymousClass0EC> A04;
    public List<AnonymousClass0KC> A05;
    public boolean A06 = false;
    public final AbstractC02590aM A07;
    public final AbstractC06690ng A08;
    public final Class<?> A09;
    public final Class<?> A0A;
    public final List<Class<?>> A0B;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/util/List<Ljava/lang/Class<*>;>;LX/0aM;LX/0ng;LX/0Zq;)V */
    public C02460Zs(Class cls, List list, AbstractC02590aM r5, AbstractC06690ng r6) {
        Class<?> A2l;
        this.A09 = cls;
        this.A0B = list;
        this.A07 = r5;
        this.A08 = r6;
        if (r6 == null) {
            A2l = null;
        } else {
            A2l = r6.A2l(cls);
        }
        this.A0A = A2l;
        this.A02 = null;
    }

    public static C02460Zs A00(Class<?> cls, AbstractC02590aM r4, AbstractC06690ng r5) {
        ArrayList arrayList = new ArrayList(8);
        A0E(cls, null, arrayList, false);
        return new C02460Zs(cls, arrayList, r4, r5);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/util/Map<Ljava/lang/String;LX/0KC;>;)Ljava/util/Map<Ljava/lang/String;LX/0KC;>; */
    private final Map A04(Class cls) {
        Class<?> A2l;
        AbstractC02450Zr r1;
        C02440Zq r12;
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
                            r12 = new C02440Zq();
                        } else {
                            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                            r12 = new C02440Zq();
                            A0B(r12, declaredAnnotations);
                        }
                        map.put(name, new AnonymousClass0KC(field, r12));
                    }
                }
            }
            AbstractC06690ng r0 = this.A08;
            if (!(r0 == null || (A2l = r0.A2l(cls)) == null)) {
                ArrayList<Class> arrayList = new ArrayList();
                arrayList.add(A2l);
                A0E(A2l, superclass, arrayList, false);
                for (Class cls2 : arrayList) {
                    Field[] declaredFields2 = cls2.getDeclaredFields();
                    for (Field field2 : declaredFields2) {
                        if (!field2.isSynthetic()) {
                            int modifiers2 = field2.getModifiers();
                            if (!Modifier.isStatic(modifiers2) && !Modifier.isTransient(modifiers2) && (r1 = (AbstractC02450Zr) map.get(field2.getName())) != null) {
                                A09(r1, field2.getDeclaredAnnotations());
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    private final C02440Zq[] A0H(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        C02440Zq[] r3 = new C02440Zq[length];
        for (int i = 0; i < length; i++) {
            Annotation[] annotationArr2 = annotationArr[i];
            C02440Zq r0 = new C02440Zq();
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
    private final X.AnonymousClass0EJ A02(java.lang.reflect.Constructor<?> r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02460Zs.A02(java.lang.reflect.Constructor, boolean):X.0EJ");
    }

    private final AnonymousClass0EC A03(Method method) {
        C02440Zq r1;
        if (this.A07 == null) {
            r1 = new C02440Zq();
        } else {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            r1 = new C02440Zq();
            A0B(r1, declaredAnnotations);
        }
        return new AnonymousClass0EC(method, r1, null);
    }

    public static void A05(C02460Zs r4) {
        C02440Zq r2 = new C02440Zq();
        r4.A02 = r2;
        if (r4.A07 != null) {
            Class<?> cls = r4.A0A;
            if (cls != null) {
                r4.A0A(r2, r4.A09, cls);
            }
            r4.A0B(r4.A02, r4.A09.getDeclaredAnnotations());
            for (Class<?> cls2 : r4.A0B) {
                C02440Zq r1 = r4.A02;
                AbstractC06690ng r0 = r4.A08;
                if (r0 != null) {
                    r4.A0A(r1, cls2, r0.A2l(cls2));
                }
                r4.A0B(r4.A02, cls2.getDeclaredAnnotations());
            }
            C02440Zq r22 = r4.A02;
            AbstractC06690ng r02 = r4.A08;
            if (r02 != null) {
                r4.A0A(r22, Object.class, r02.A2l(Object.class));
            }
        }
    }

    public static void A06(C02460Zs r18) {
        AnonymousClass0EC r4;
        C02440Zq[] r6;
        int size;
        Class<?> cls = r18.A09;
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        ArrayList arrayList = null;
        List<AnonymousClass0EJ> list = null;
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length == 0) {
                r18.A00 = r18.A02(constructor, true);
            } else {
                if (list == null) {
                    list = new ArrayList<>(Math.max(10, length));
                }
                list.add(r18.A02(constructor, false));
            }
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        r18.A03 = list;
        Class<?> cls2 = r18.A0A;
        if (cls2 != null && (r18.A00 != null || !list.isEmpty())) {
            List<AnonymousClass0EJ> list2 = r18.A03;
            if (list2 == null) {
                size = 0;
            } else {
                size = list2.size();
            }
            Constructor<?>[] declaredConstructors2 = cls2.getDeclaredConstructors();
            C06710nj[] r14 = null;
            for (Constructor<?> constructor2 : declaredConstructors2) {
                if (constructor2.getParameterTypes().length == 0) {
                    AbstractC02450Zr r1 = r18.A00;
                    if (r1 != null) {
                        r18.A09(r1, constructor2.getDeclaredAnnotations());
                    }
                } else {
                    if (r14 == null) {
                        r14 = new C06710nj[size];
                        for (int i = 0; i < size; i++) {
                            r14[i] = new C06710nj("", r18.A03.get(i)._constructor.getParameterTypes());
                        }
                    }
                    C06710nj r42 = new C06710nj("", constructor2.getParameterTypes());
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        } else if (!r42.equals(r14[i2])) {
                            i2++;
                        } else {
                            AnonymousClass0EJ r15 = r18.A03.get(i2);
                            r18.A09(r15, constructor2.getDeclaredAnnotations());
                            Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                            int length2 = parameterAnnotations.length;
                            for (int i3 = 0; i3 < length2; i3++) {
                                for (Annotation annotation : parameterAnnotations[i3]) {
                                    r15.A0Y(i3, annotation);
                                }
                            }
                        }
                    }
                }
            }
        }
        AbstractC02590aM r5 = r18.A07;
        if (r5 != null) {
            AnonymousClass0EJ r0 = r18.A00;
            if (r0 != null && r5.A0p(r0)) {
                r18.A00 = null;
            }
            List<AnonymousClass0EJ> list3 = r18.A03;
            if (list3 != null) {
                int size2 = list3.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        break;
                    } else if (r5.A0p(r18.A03.get(size2))) {
                        r18.A03.remove(size2);
                    }
                }
            }
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (Modifier.isStatic(method.getModifiers())) {
                if (arrayList == null) {
                    arrayList = new ArrayList(8);
                }
                if (r5 == null) {
                    C02440Zq r12 = new C02440Zq();
                    int length3 = method.getParameterTypes().length;
                    if (length3 == 0) {
                        r6 = A0C;
                    } else {
                        r6 = new C02440Zq[length3];
                        int i4 = 0;
                        do {
                            r6[i4] = new C02440Zq();
                            i4++;
                        } while (i4 < length3);
                    }
                    r4 = new AnonymousClass0EC(method, r12, r6);
                } else {
                    Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                    C02440Zq r13 = new C02440Zq();
                    r18.A0B(r13, declaredAnnotations);
                    r4 = new AnonymousClass0EC(method, r13, r18.A0H(method.getParameterAnnotations()));
                }
                arrayList.add(r4);
            }
        }
        if (arrayList == null) {
            r18.A04 = Collections.emptyList();
        } else {
            r18.A04 = arrayList;
            if (cls2 != null) {
                int size3 = arrayList.size();
                Method[] declaredMethods2 = cls2.getDeclaredMethods();
                C06710nj[] r8 = null;
                for (Method method2 : declaredMethods2) {
                    if (Modifier.isStatic(method2.getModifiers()) && method2.getParameterTypes().length != 0) {
                        if (r8 == null) {
                            r8 = new C06710nj[size3];
                            for (int i5 = 0; i5 < size3; i5++) {
                                r8[i5] = new C06710nj(r18.A04.get(i5).A00);
                            }
                        }
                        C06710nj r43 = new C06710nj(method2);
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size3) {
                                break;
                            }
                            if (r43.equals(r8[i6])) {
                                r18.A0F(method2, r18.A04.get(i6), true);
                                break;
                            }
                            i6++;
                        }
                    }
                }
            }
            if (r5 != null) {
                int size4 = r18.A04.size();
                while (true) {
                    size4--;
                    if (size4 < 0) {
                        break;
                    } else if (r5.A0p(r18.A04.get(size4))) {
                        r18.A04.remove(size4);
                    }
                }
            }
        }
        r18.A06 = true;
    }

    public static void A07(C02460Zs r6) {
        LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap;
        Class<?> A2l;
        Class<?> A2l2;
        C06680nf r1 = new C06680nf();
        r6.A01 = r1;
        C06680nf r4 = new C06680nf();
        Class<?> cls = r6.A09;
        r6.A0C(cls, r1, r6.A0A, r4);
        for (Class<?> cls2 : r6.A0B) {
            AbstractC06690ng r0 = r6.A08;
            if (r0 == null) {
                A2l2 = null;
            } else {
                A2l2 = r0.A2l(cls2);
            }
            r6.A0C(cls2, r6.A01, A2l2, r4);
        }
        AbstractC06690ng r12 = r6.A08;
        if (!(r12 == null || (A2l = r12.A2l(Object.class)) == null)) {
            r6.A0D(cls, r6.A01, A2l, r4);
        }
        if (!(r6.A07 == null || (linkedHashMap = r4.A00) == null || linkedHashMap.size() == 0)) {
            Iterator<AnonymousClass0EC> it = r4.iterator();
            while (it.hasNext()) {
                AnonymousClass0EC next = it.next();
                try {
                    String A0L = next.A0L();
                    Class<?>[] clsArr = next._paramClasses;
                    if (clsArr == null) {
                        clsArr = next.A00.getParameterTypes();
                        next._paramClasses = clsArr;
                    }
                    Method declaredMethod = Object.class.getDeclaredMethod(A0L, clsArr);
                    if (declaredMethod != null) {
                        AnonymousClass0EC A032 = r6.A03(declaredMethod);
                        r6.A0F(next.A00, A032, false);
                        r6.A01.A00(A032);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void A08(AbstractC02450Zr r6, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC02590aM r0 = this.A07;
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
                for (Annotation[] annotationArr2 : linkedList) {
                    A08(r6, annotationArr2);
                }
            }
        }
    }

    private void A09(AbstractC02450Zr r7, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC02590aM r0 = this.A07;
                if (r0 == null || !r0.A0t(annotation)) {
                    C02440Zq r02 = r7.A00;
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
                for (Annotation[] annotationArr2 : linkedList) {
                    A09(r7, annotationArr2);
                }
            }
        }
    }

    private final void A0A(C02440Zq r3, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            A0B(r3, cls2.getDeclaredAnnotations());
            ArrayList<Class> arrayList = new ArrayList(8);
            A0E(cls2, cls, arrayList, false);
            for (Class cls3 : arrayList) {
                A0B(r3, cls3.getDeclaredAnnotations());
            }
        }
    }

    private void A0B(C02440Zq r6, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC02590aM r0 = this.A07;
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
                for (Annotation[] annotationArr2 : linkedList) {
                    A0B(r6, annotationArr2);
                }
            }
        }
    }

    private final void A0C(Class<?> cls, C06680nf r10, Class<?> cls2, C06680nf r12) {
        AnonymousClass0EC remove;
        AnonymousClass0EC r1;
        if (cls2 != null) {
            A0D(cls, r10, cls2, r12);
        }
        if (cls != null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap = r10.A00;
                    if (linkedHashMap == null || (r1 = linkedHashMap.get(new C06710nj(method))) == null) {
                        AnonymousClass0EC A032 = A03(method);
                        r10.A00(A032);
                        LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap2 = r12.A00;
                        if (!(linkedHashMap2 == null || (remove = linkedHashMap2.remove(new C06710nj(method))) == null)) {
                            A0F(remove.A00, A032, false);
                        }
                    } else {
                        A08(r1, method.getDeclaredAnnotations());
                        if (r1.A0P().isInterface() && !method.getDeclaringClass().isInterface()) {
                            r10.A00(new AnonymousClass0EC(method, ((AbstractC02450Zr) r1).A00, r1._paramAnnotations));
                        }
                    }
                }
            }
        }
    }

    private final void A0D(Class<?> cls, C06680nf r9, Class<?> cls2, C06680nf r11) {
        AnonymousClass0EC r1;
        ArrayList<Class> arrayList = new ArrayList();
        arrayList.add(cls2);
        A0E(cls2, cls, arrayList, false);
        for (Class cls3 : arrayList) {
            Method[] declaredMethods = cls3.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap = r9.A00;
                    if (linkedHashMap == null || (r1 = linkedHashMap.get(new C06710nj(method))) == null) {
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

    @Override // X.AbstractC06640nb
    public final Class<?> A0K() {
        return this.A09;
    }

    @Override // X.AbstractC06640nb
    public final String A0L() {
        return this.A09.getName();
    }

    @Override // X.AbstractC06640nb
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

    @Override // X.AbstractC06640nb
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this.A09;
    }

    @Override // X.AbstractC06640nb
    public final Type A0O() {
        return this.A09;
    }

    public final Iterable<AnonymousClass0KC> A0P() {
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
        return AnonymousClass006.A07("[AnnotedClass ", this.A09.getName(), "]");
    }

    public static C02460Zs A01(Class<?> cls, AbstractC02590aM r3, AbstractC06690ng r4) {
        return new C02460Zs(cls, Collections.emptyList(), r3, r4);
    }

    private final void A0F(Method method, AnonymousClass0EC r9, boolean z) {
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
}
