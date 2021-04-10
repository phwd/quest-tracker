package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.Pv  reason: case insensitive filesystem */
public final class C0299Pv {
    public static final AbstractC1024qt A06 = new fF(Object.class);
    public static final AbstractC1024qt[] A07 = new AbstractC1024qt[0];
    public Map A00;
    public HashSet A01;
    public final AbstractC1024qt A02;
    public final C0300Pw A03;
    public final Class A04;
    public final C0299Pv A05;

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A00(X.C0299Pv r5) {
        /*
            java.lang.Class r0 = r5.A04
            r5.A01(r0)
            X.qt r4 = r5.A02
            if (r4 == 0) goto L_0x004c
            int r3 = r4.A03()
            if (r3 <= 0) goto L_0x004c
            r2 = 0
        L_0x0010:
            boolean r0 = r4 instanceof X.fF
            if (r0 != 0) goto L_0x003d
            boolean r0 = r4 instanceof X.C0681fG
            if (r0 != 0) goto L_0x0032
            boolean r0 = r4 instanceof X.C0682fH
            if (r0 != 0) goto L_0x002d
            boolean r0 = r4 instanceof X.C0683fI
            if (r0 != 0) goto L_0x002d
        L_0x0020:
            r1 = 0
        L_0x0021:
            X.qt r0 = r4.A06(r2)
            r5.A04(r1, r0)
            int r2 = r2 + 1
            if (r2 >= r3) goto L_0x004c
            goto L_0x0010
        L_0x002d:
            if (r2 != 0) goto L_0x0020
            java.lang.String r1 = "E"
            goto L_0x0021
        L_0x0032:
            if (r2 != 0) goto L_0x0037
            java.lang.String r1 = "K"
            goto L_0x0021
        L_0x0037:
            r0 = 1
            if (r2 != r0) goto L_0x0020
            java.lang.String r1 = "V"
            goto L_0x0021
        L_0x003d:
            r0 = r4
            X.fF r0 = (X.fF) r0
            if (r2 < 0) goto L_0x0020
            java.lang.String[] r1 = r0._typeNames
            if (r1 == 0) goto L_0x0020
            int r0 = r1.length
            if (r2 >= r0) goto L_0x0020
            r1 = r1[r2]
            goto L_0x0021
        L_0x004c:
            java.util.Map r0 = r5.A00
            if (r0 != 0) goto L_0x0056
            java.util.Map r0 = java.util.Collections.emptyMap()
            r5.A00 = r0
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0299Pv.A00(X.Pv):void");
    }

    private final void A01(Type type) {
        Class cls;
        int length;
        Map map;
        AbstractC1024qt A09;
        int length2;
        if (type != null) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments != null && (length2 = actualTypeArguments.length) > 0) {
                    Class cls2 = (Class) parameterizedType.getRawType();
                    TypeVariable[] typeParameters = cls2.getTypeParameters();
                    int length3 = typeParameters.length;
                    if (length3 == length2) {
                        for (int i = 0; i < length2; i++) {
                            String name = typeParameters[i].getName();
                            Map map2 = this.A00;
                            if (map2 == null) {
                                this.A00 = new LinkedHashMap();
                            } else if (map2.containsKey(name)) {
                            }
                            A03(name);
                            this.A00.put(name, this.A03.A09(actualTypeArguments[i], this));
                        }
                    } else {
                        StringBuilder sb = new StringBuilder("Strange parametrized type (in class ");
                        sb.append(cls2.getName());
                        sb.append("): number of type arguments != number of type parameters (");
                        sb.append(length2);
                        sb.append(" vs ");
                        sb.append(length3);
                        sb.append(")");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                cls = (Class) parameterizedType.getRawType();
            } else if (type instanceof Class) {
                cls = (Class) type;
                Class<?> declaringClass = cls.getDeclaringClass();
                if (declaringClass != null && !declaringClass.isAssignableFrom(cls)) {
                    A01(cls.getDeclaringClass());
                }
                TypeVariable<Class<?>>[] typeParameters2 = cls.getTypeParameters();
                if (typeParameters2 != null && (length = typeParameters2.length) > 0) {
                    AbstractC1024qt[] qtVarArr = null;
                    AbstractC1024qt qtVar = this.A02;
                    if (qtVar != null && cls.isAssignableFrom(qtVar._class)) {
                        qtVarArr = this.A03.A0A(qtVar, cls);
                    }
                    for (int i2 = 0; i2 < length; i2++) {
                        TypeVariable<Class<?>> typeVariable = typeParameters2[i2];
                        String name2 = typeVariable.getName();
                        Type type2 = typeVariable.getBounds()[0];
                        if (type2 != null) {
                            Map map3 = this.A00;
                            if (map3 == null) {
                                this.A00 = new LinkedHashMap();
                            } else if (map3.containsKey(name2)) {
                            }
                            A03(name2);
                            if (qtVarArr != null) {
                                map = this.A00;
                                A09 = qtVarArr[i2];
                            } else {
                                map = this.A00;
                                A09 = this.A03.A09(type2, this);
                            }
                            map.put(name2, A09);
                        }
                    }
                }
            } else {
                return;
            }
            A01(cls.getGenericSuperclass());
            for (Type type3 : cls.getGenericInterfaces()) {
                A01(type3);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        if (java.lang.reflect.Modifier.isStatic(r1.getModifiers()) == false) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC1024qt A02(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map r0 = r4.A00
            if (r0 != 0) goto L_0x0007
            A00(r4)
        L_0x0007:
            java.util.Map r0 = r4.A00
            java.lang.Object r0 = r0.get(r5)
            X.qt r0 = (X.AbstractC1024qt) r0
            if (r0 != 0) goto L_0x001d
            java.util.HashSet r0 = r4.A01
            if (r0 == 0) goto L_0x001e
            boolean r0 = r0.contains(r5)
            if (r0 == 0) goto L_0x001e
        L_0x001b:
            X.qt r0 = X.C0299Pv.A06
        L_0x001d:
            return r0
        L_0x001e:
            X.Pv r0 = r4.A05
            if (r0 == 0) goto L_0x0027
            X.qt r0 = r0.A02(r5)
            return r0
        L_0x0027:
            java.lang.Class r1 = r4.A04
            if (r1 == 0) goto L_0x003c
            java.lang.Class r0 = r1.getEnclosingClass()
            if (r0 == 0) goto L_0x003c
            int r0 = r1.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 != 0) goto L_0x0059
            goto L_0x001b
        L_0x003c:
            if (r1 != 0) goto L_0x0059
            X.qt r0 = r4.A02
            if (r0 == 0) goto L_0x0056
            java.lang.String r3 = r0.toString()
        L_0x0046:
            java.lang.String r2 = "Type variable '"
            java.lang.String r1 = "' can not be resolved (with context of class "
            java.lang.String r0 = ")"
            java.lang.String r1 = X.AnonymousClass08.A07(r2, r5, r1, r3, r0)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        L_0x0056:
            java.lang.String r3 = "UNKNOWN"
            goto L_0x0046
        L_0x0059:
            java.lang.String r3 = r1.getName()
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0299Pv.A02(java.lang.String):X.qt");
    }

    public final void A03(String str) {
        HashSet hashSet = this.A01;
        if (hashSet == null) {
            hashSet = new HashSet();
            this.A01 = hashSet;
        }
        hashSet.add(str);
    }

    public final void A04(String str, AbstractC1024qt qtVar) {
        Map map = this.A00;
        if (map == null || map.size() == 0) {
            this.A00 = new LinkedHashMap();
        }
        this.A00.put(str, qtVar);
    }

    public final String toString() {
        String name;
        if (this.A00 == null) {
            A00(this);
        }
        StringBuilder sb = new StringBuilder("[TypeBindings for ");
        AbstractC1024qt qtVar = this.A02;
        if (qtVar != null) {
            name = qtVar.toString();
        } else {
            name = this.A04.getName();
        }
        sb.append(name);
        sb.append(": ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public C0299Pv(C0300Pw pw, C0299Pv pv, Class cls, AbstractC1024qt qtVar) {
        this.A03 = pw;
        this.A05 = pv;
        this.A04 = cls;
        this.A02 = qtVar;
    }
}
