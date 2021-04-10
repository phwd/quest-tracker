package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: X.rI  reason: case insensitive filesystem */
public final class C1043rI extends P9 {
    public static final C1045rK[] A0C = new C1045rK[0];
    public C00090s A00;
    public PD A01;
    public C1045rK A02;
    public List A03;
    public List A04;
    public List A05;
    public boolean A06 = false;
    public final AbstractC1020qp A07;
    public final PF A08;
    public final Class A09;
    public final Class A0A;
    public final List A0B;

    public C1043rI(Class cls, List list, AbstractC1020qp qpVar, PF pf) {
        Class A25;
        this.A09 = cls;
        this.A0B = list;
        this.A07 = qpVar;
        this.A08 = pf;
        if (pf == null) {
            A25 = null;
        } else {
            A25 = pf.A25(cls);
        }
        this.A0A = A25;
        this.A02 = null;
    }

    public static C1043rI A00(Class cls, AbstractC1020qp qpVar, PF pf) {
        ArrayList arrayList = new ArrayList(8);
        A0E(cls, null, arrayList, false);
        return new C1043rI(cls, arrayList, qpVar, pf);
    }

    private final Map A04(Class cls) {
        Class A25;
        AbstractC1044rJ rJVar;
        C1045rK rKVar;
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
                            rKVar = new C1045rK();
                        } else {
                            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                            rKVar = new C1045rK();
                            A0B(rKVar, declaredAnnotations);
                        }
                        map.put(name, new St(field, rKVar));
                    }
                }
            }
            PF pf = this.A08;
            if (!(pf == null || (A25 = pf.A25(cls)) == null)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(A25);
                A0E(A25, superclass, arrayList, false);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Field[] declaredFields2 = ((Class) it.next()).getDeclaredFields();
                    for (Field field2 : declaredFields2) {
                        if (!field2.isSynthetic()) {
                            int modifiers2 = field2.getModifiers();
                            if (!Modifier.isStatic(modifiers2) && !Modifier.isTransient(modifiers2) && (rJVar = (AbstractC1044rJ) map.get(field2.getName())) != null) {
                                A09(rJVar, field2.getDeclaredAnnotations());
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    private final C1045rK[] A0H(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        C1045rK[] rKVarArr = new C1045rK[length];
        for (int i = 0; i < length; i++) {
            Annotation[] annotationArr2 = annotationArr[i];
            C1045rK rKVar = new C1045rK();
            A0B(rKVar, annotationArr2);
            rKVarArr[i] = rKVar;
        }
        return rKVarArr;
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
    private final X.C00090s A02(java.lang.reflect.Constructor r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1043rI.A02(java.lang.reflect.Constructor, boolean):X.0s");
    }

    private final AnonymousClass0q A03(Method method) {
        C1045rK rKVar;
        if (this.A07 == null) {
            rKVar = new C1045rK();
        } else {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            rKVar = new C1045rK();
            A0B(rKVar, declaredAnnotations);
        }
        return new AnonymousClass0q(method, rKVar, null);
    }

    public static void A05(C1043rI rIVar) {
        C1045rK rKVar = new C1045rK();
        rIVar.A02 = rKVar;
        if (rIVar.A07 != null) {
            Class cls = rIVar.A0A;
            if (cls != null) {
                rIVar.A0A(rKVar, rIVar.A09, cls);
            }
            rIVar.A0B(rIVar.A02, rIVar.A09.getDeclaredAnnotations());
            for (Class cls2 : rIVar.A0B) {
                C1045rK rKVar2 = rIVar.A02;
                PF pf = rIVar.A08;
                if (pf != null) {
                    rIVar.A0A(rKVar2, cls2, pf.A25(cls2));
                }
                rIVar.A0B(rIVar.A02, cls2.getDeclaredAnnotations());
            }
            C1045rK rKVar3 = rIVar.A02;
            PF pf2 = rIVar.A08;
            if (pf2 != null) {
                rIVar.A0A(rKVar3, Object.class, pf2.A25(Object.class));
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:110:0x0021 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0039 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0, types: [X.rI] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v26, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v27, types: [java.util.AbstractCollection] */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.C1043rI r18) {
        /*
        // Method dump skipped, instructions count: 509
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1043rI.A06(X.rI):void");
    }

    public static void A07(C1043rI rIVar) {
        LinkedHashMap linkedHashMap;
        Class A25;
        Class A252;
        PD pd = new PD();
        rIVar.A01 = pd;
        PD pd2 = new PD();
        Class cls = rIVar.A09;
        rIVar.A0C(cls, pd, rIVar.A0A, pd2);
        for (Class cls2 : rIVar.A0B) {
            PF pf = rIVar.A08;
            if (pf == null) {
                A252 = null;
            } else {
                A252 = pf.A25(cls2);
            }
            rIVar.A0C(cls2, rIVar.A01, A252, pd2);
        }
        PF pf2 = rIVar.A08;
        if (!(pf2 == null || (A25 = pf2.A25(Object.class)) == null)) {
            rIVar.A0D(cls, rIVar.A01, A25, pd2);
        }
        if (!(rIVar.A07 == null || (linkedHashMap = pd2.A00) == null || linkedHashMap.size() == 0)) {
            Iterator it = pd2.iterator();
            while (it.hasNext()) {
                AnonymousClass0q r3 = (AnonymousClass0q) it.next();
                try {
                    String A0K = r3.A0K();
                    Class<?>[] clsArr = r3._paramClasses;
                    if (clsArr == null) {
                        clsArr = r3.A00.getParameterTypes();
                        r3._paramClasses = clsArr;
                    }
                    Method declaredMethod = Object.class.getDeclaredMethod(A0K, clsArr);
                    if (declaredMethod != null) {
                        AnonymousClass0q A032 = rIVar.A03(declaredMethod);
                        rIVar.A0F(r3.A00, A032, false);
                        rIVar.A01.A00(A032);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void A08(AbstractC1044rJ rJVar, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC1020qp qpVar = this.A07;
                if (qpVar == null || !qpVar.A0P(annotation)) {
                    rJVar.A00.A00(annotation);
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
                    A08(rJVar, (Annotation[]) it.next());
                }
            }
        }
    }

    private void A09(AbstractC1044rJ rJVar, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC1020qp qpVar = this.A07;
                if (qpVar == null || !qpVar.A0P(annotation)) {
                    C1045rK rKVar = rJVar.A00;
                    HashMap hashMap = rKVar.A00;
                    if (hashMap == null) {
                        hashMap = new HashMap();
                        rKVar.A00 = hashMap;
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
                    A09(rJVar, (Annotation[]) it.next());
                }
            }
        }
    }

    private final void A0A(C1045rK rKVar, Class cls, Class cls2) {
        if (cls2 != null) {
            A0B(rKVar, cls2.getDeclaredAnnotations());
            ArrayList<Class> arrayList = new ArrayList(8);
            A0E(cls2, cls, arrayList, false);
            for (Class cls3 : arrayList) {
                A0B(rKVar, cls3.getDeclaredAnnotations());
            }
        }
    }

    private void A0B(C1045rK rKVar, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList linkedList = null;
            for (Annotation annotation : annotationArr) {
                AbstractC1020qp qpVar = this.A07;
                if (qpVar == null || !qpVar.A0P(annotation)) {
                    rKVar.A00(annotation);
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
                    A0B(rKVar, (Annotation[]) it.next());
                }
            }
        }
    }

    private final void A0C(Class cls, PD pd, Class cls2, PD pd2) {
        AnonymousClass0q r0;
        SV sv;
        if (cls2 != null) {
            A0D(cls, pd, cls2, pd2);
        }
        if (cls != null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap linkedHashMap = pd.A00;
                    if (linkedHashMap == null || (sv = (SV) linkedHashMap.get(new PI(method))) == null) {
                        AnonymousClass0q A032 = A03(method);
                        pd.A00(A032);
                        LinkedHashMap linkedHashMap2 = pd2.A00;
                        if (!(linkedHashMap2 == null || (r0 = (AnonymousClass0q) linkedHashMap2.remove(new PI(method))) == null)) {
                            A0F(r0.A00, A032, false);
                        }
                    } else {
                        A08(sv, method.getDeclaredAnnotations());
                        if (sv.A0P().isInterface() && !method.getDeclaringClass().isInterface()) {
                            pd.A00(new AnonymousClass0q(method, sv.A00, sv._paramAnnotations));
                        }
                    }
                }
            }
        }
    }

    private final void A0D(Class cls, PD pd, Class cls2, PD pd2) {
        AbstractC1044rJ rJVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(cls2);
        A0E(cls2, cls, arrayList, false);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Method[] declaredMethods = ((Class) it.next()).getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (A0G(method)) {
                    LinkedHashMap linkedHashMap = pd.A00;
                    if (linkedHashMap == null || (rJVar = (AbstractC1044rJ) linkedHashMap.get(new PI(method))) == null) {
                        pd2.A00(A03(method));
                    } else {
                        A08(rJVar, method.getDeclaredAnnotations());
                    }
                }
            }
        }
    }

    public static void A0E(Class cls, Class cls2, Collection collection, boolean z) {
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

    public final Iterable A0P() {
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
        return AnonymousClass08.A05("[AnnotedClass ", this.A09.getName(), "]");
    }

    public static C1043rI A01(Class cls, AbstractC1020qp qpVar, PF pf) {
        return new C1043rI(cls, Collections.emptyList(), qpVar, pf);
    }

    private final void A0F(Method method, AnonymousClass0q r9, boolean z) {
        A09(r9, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    r9.A0W(i, annotation);
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
