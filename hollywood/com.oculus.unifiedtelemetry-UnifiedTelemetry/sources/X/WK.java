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

public final class WK extends VV {
    public static final WI[] A0C = new WI[0];
    public AnonymousClass7Q A00;
    public VR A01;
    public WI A02;
    public List<AnonymousClass7Q> A03;
    public List<AnonymousClass7P> A04;
    public List<CD> A05;
    public boolean A06 = false;
    public final Wp A07;
    public final VQ A08;
    public final Class<?> A09;
    public final Class<?> A0A;
    public final List<Class<?>> A0B;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/util/List<Ljava/lang/Class<*>;>;LX/Wp;LX/VQ;LX/WI;)V */
    public WK(Class cls, List list, Wp wp, VQ vq) {
        Class<?> A2A;
        this.A09 = cls;
        this.A0B = list;
        this.A07 = wp;
        this.A08 = vq;
        if (vq == null) {
            A2A = null;
        } else {
            A2A = vq.A2A(cls);
        }
        this.A0A = A2A;
        this.A02 = null;
    }

    public static WK A00(Class<?> cls, Wp wp, VQ vq) {
        ArrayList arrayList = new ArrayList(8);
        A0E(cls, null, arrayList, false);
        return new WK(cls, arrayList, wp, vq);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/util/Map<Ljava/lang/String;LX/CD;>;)Ljava/util/Map<Ljava/lang/String;LX/CD;>; */
    private final Map A04(Class cls) {
        Class<?> A2A;
        WJ wj;
        WI wi;
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
                            wi = new WI();
                        } else {
                            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                            wi = new WI();
                            A0B(wi, declaredAnnotations);
                        }
                        map.put(name, new CD(field, wi));
                    }
                }
            }
            VQ vq = this.A08;
            if (!(vq == null || (A2A = vq.A2A(cls)) == null)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(A2A);
                A0E(A2A, superclass, arrayList, false);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Field[] declaredFields2 = ((Class) it.next()).getDeclaredFields();
                    for (Field field2 : declaredFields2) {
                        if (!field2.isSynthetic()) {
                            int modifiers2 = field2.getModifiers();
                            if (!Modifier.isStatic(modifiers2) && !Modifier.isTransient(modifiers2) && (wj = (WJ) map.get(field2.getName())) != null) {
                                A09(wj, field2.getDeclaredAnnotations());
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    private final WI[] A0H(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        WI[] wiArr = new WI[length];
        for (int i = 0; i < length; i++) {
            Annotation[] annotationArr2 = annotationArr[i];
            WI wi = new WI();
            A0B(wi, annotationArr2);
            wiArr[i] = wi;
        }
        return wiArr;
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
    private final X.AnonymousClass7Q A02(java.lang.reflect.Constructor<?> r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WK.A02(java.lang.reflect.Constructor, boolean):X.7Q");
    }

    private final AnonymousClass7P A03(Method method) {
        WI wi;
        if (this.A07 == null) {
            wi = new WI();
        } else {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            wi = new WI();
            A0B(wi, declaredAnnotations);
        }
        return new AnonymousClass7P(method, wi, null);
    }

    public static void A05(WK wk) {
        WI wi = new WI();
        wk.A02 = wi;
        if (wk.A07 != null) {
            Class<?> cls = wk.A0A;
            if (cls != null) {
                wk.A0A(wi, wk.A09, cls);
            }
            wk.A0B(wk.A02, wk.A09.getDeclaredAnnotations());
            for (Class<?> cls2 : wk.A0B) {
                WI wi2 = wk.A02;
                VQ vq = wk.A08;
                if (vq != null) {
                    wk.A0A(wi2, cls2, vq.A2A(cls2));
                }
                wk.A0B(wk.A02, cls2.getDeclaredAnnotations());
            }
            WI wi3 = wk.A02;
            VQ vq2 = wk.A08;
            if (vq2 != null) {
                wk.A0A(wi3, Object.class, vq2.A2A(Object.class));
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:110:0x0021 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0039 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0, types: [X.WK] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List, java.util.List<X.7Q>] */
    /* JADX WARN: Type inference failed for: r1v26, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v27, types: [java.util.AbstractCollection] */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.WK r18) {
        /*
        // Method dump skipped, instructions count: 509
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WK.A06(X.WK):void");
    }

    public static void A07(WK wk) {
        LinkedHashMap<VO, AnonymousClass7P> linkedHashMap;
        Class<?> A2A;
        Class<?> A2A2;
        VR vr = new VR();
        wk.A01 = vr;
        VR vr2 = new VR();
        Class<?> cls = wk.A09;
        wk.A0C(cls, vr, wk.A0A, vr2);
        for (Class<?> cls2 : wk.A0B) {
            VQ vq = wk.A08;
            if (vq == null) {
                A2A2 = null;
            } else {
                A2A2 = vq.A2A(cls2);
            }
            wk.A0C(cls2, wk.A01, A2A2, vr2);
        }
        VQ vq2 = wk.A08;
        if (!(vq2 == null || (A2A = vq2.A2A(Object.class)) == null)) {
            wk.A0D(cls, wk.A01, A2A, vr2);
        }
        if (!(wk.A07 == null || (linkedHashMap = vr2.A00) == null || linkedHashMap.size() == 0)) {
            Iterator<AnonymousClass7P> it = vr2.iterator();
            while (it.hasNext()) {
                AnonymousClass7P next = it.next();
                try {
                    String A0K = next.A0K();
                    Class<?>[] clsArr = next._paramClasses;
                    if (clsArr == null) {
                        clsArr = next.A00.getParameterTypes();
                        next._paramClasses = clsArr;
                    }
                    Method declaredMethod = Object.class.getDeclaredMethod(A0K, clsArr);
                    if (declaredMethod != null) {
                        AnonymousClass7P A032 = wk.A03(declaredMethod);
                        wk.A0F(next.A00, A032, false);
                        wk.A01.A00(A032);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void A08(WJ wj, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                Wp wp = this.A07;
                if (wp == null || !wp.A0h(annotation)) {
                    wj.A00.A00(annotation);
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
                    A08(wj, (Annotation[]) it.next());
                }
            }
        }
    }

    private void A09(WJ wj, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                Wp wp = this.A07;
                if (wp == null || !wp.A0h(annotation)) {
                    WI wi = wj.A00;
                    HashMap<Class<? extends Annotation>, Annotation> hashMap = wi.A00;
                    if (hashMap == null) {
                        hashMap = new HashMap<>();
                        wi.A00 = hashMap;
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
                    A09(wj, (Annotation[]) it.next());
                }
            }
        }
    }

    private final void A0A(WI wi, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            A0B(wi, cls2.getDeclaredAnnotations());
            ArrayList<Class> arrayList = new ArrayList(8);
            A0E(cls2, cls, arrayList, false);
            for (Class cls3 : arrayList) {
                A0B(wi, cls3.getDeclaredAnnotations());
            }
        }
    }

    private void A0B(WI wi, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                Wp wp = this.A07;
                if (wp == null || !wp.A0h(annotation)) {
                    wi.A00(annotation);
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
                    A0B(wi, (Annotation[]) it.next());
                }
            }
        }
    }

    private final void A0C(Class<?> cls, VR vr, Class<?> cls2, VR vr2) {
        AnonymousClass7P remove;
        AnonymousClass7P r1;
        if (cls2 != null) {
            A0D(cls, vr, cls2, vr2);
        }
        if (cls != null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap<VO, AnonymousClass7P> linkedHashMap = vr.A00;
                    if (linkedHashMap == null || (r1 = linkedHashMap.get(new VO(method))) == null) {
                        AnonymousClass7P A032 = A03(method);
                        vr.A00(A032);
                        LinkedHashMap<VO, AnonymousClass7P> linkedHashMap2 = vr2.A00;
                        if (!(linkedHashMap2 == null || (remove = linkedHashMap2.remove(new VO(method))) == null)) {
                            A0F(remove.A00, A032, false);
                        }
                    } else {
                        A08(r1, method.getDeclaredAnnotations());
                        if (r1.A0O().isInterface() && !method.getDeclaringClass().isInterface()) {
                            vr.A00(new AnonymousClass7P(method, ((WJ) r1).A00, r1._paramAnnotations));
                        }
                    }
                }
            }
        }
    }

    private final void A0D(Class<?> cls, VR vr, Class<?> cls2, VR vr2) {
        AnonymousClass7P r1;
        ArrayList arrayList = new ArrayList();
        arrayList.add(cls2);
        A0E(cls2, cls, arrayList, false);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Method[] declaredMethods = ((Class) it.next()).getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap<VO, AnonymousClass7P> linkedHashMap = vr.A00;
                    if (linkedHashMap == null || (r1 = linkedHashMap.get(new VO(method))) == null) {
                        vr2.A00(A03(method));
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

    @Override // X.VV
    public final String A0K() {
        return this.A09.getName();
    }

    @Override // X.VV
    public final <A extends Annotation> A A0L(Class<A> cls) {
        if (this.A02 == null) {
            A05(this);
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A02.A00;
        if (hashMap == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    public final Iterable<CD> A0O() {
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
        return AnonymousClass06.A05("[AnnotedClass ", this.A09.getName(), "]");
    }

    public static WK A01(Class<?> cls, Wp wp, VQ vq) {
        return new WK(cls, Collections.emptyList(), wp, vq);
    }

    private final void A0F(Method method, AnonymousClass7P r9, boolean z) {
        A09(r9, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    r9.A0V(i, annotation);
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

    @Override // X.VV
    public final Class<?> A0J() {
        return this.A09;
    }

    @Override // X.VV
    public final AnnotatedElement A0M() {
        return this.A09;
    }

    @Override // X.VV
    public final Type A0N() {
        return this.A09;
    }
}
