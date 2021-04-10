package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public final class O4 {
    public static final AbstractC0224Wl A06 = new AnonymousClass73(Object.class);
    public static final AbstractC0224Wl[] A07 = new AbstractC0224Wl[0];
    public Map<String, AbstractC0224Wl> A00;
    public HashSet<String> A01;
    public final AbstractC0224Wl A02;
    public final NT A03;
    public final Class<?> A04;
    public final O4 A05;

    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A00(X.O4 r5) {
        /*
            java.lang.Class<?> r0 = r5.A04
            r5.A01(r0)
            X.Wl r4 = r5.A02
            if (r4 == 0) goto L_0x0020
            int r3 = r4.A02()
            if (r3 <= 0) goto L_0x0020
            r2 = 0
        L_0x0010:
            java.lang.String r1 = r4.A0D(r2)
            X.Wl r0 = r4.A05(r2)
            r5.A04(r1, r0)
            int r2 = r2 + 1
            if (r2 >= r3) goto L_0x0020
            goto L_0x0010
        L_0x0020:
            java.util.Map<java.lang.String, X.Wl> r0 = r5.A00
            if (r0 != 0) goto L_0x002a
            java.util.Map r0 = java.util.Collections.emptyMap()
            r5.A00 = r0
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.O4.A00(X.O4):void");
    }

    private final void A01(Type type) {
        Class cls;
        int length;
        Map<String, AbstractC0224Wl> map;
        AbstractC0224Wl A09;
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
                            Map<String, AbstractC0224Wl> map2 = this.A00;
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
                    AbstractC0224Wl[] wlArr = null;
                    AbstractC0224Wl wl = this.A02;
                    if (wl != null && cls.isAssignableFrom(wl._class)) {
                        wlArr = this.A03.A0A(wl, cls);
                    }
                    for (int i2 = 0; i2 < length; i2++) {
                        TypeVariable<Class<?>> typeVariable = typeParameters2[i2];
                        String name2 = typeVariable.getName();
                        Type type2 = typeVariable.getBounds()[0];
                        if (type2 != null) {
                            Map<String, AbstractC0224Wl> map3 = this.A00;
                            if (map3 == null) {
                                this.A00 = new LinkedHashMap();
                            } else if (map3.containsKey(name2)) {
                            }
                            A03(name2);
                            if (wlArr != null) {
                                map = this.A00;
                                A09 = wlArr[i2];
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
    public final X.AbstractC0224Wl A02(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, X.Wl> r0 = r4.A00
            if (r0 != 0) goto L_0x0007
            A00(r4)
        L_0x0007:
            java.util.Map<java.lang.String, X.Wl> r0 = r4.A00
            java.lang.Object r0 = r0.get(r5)
            X.Wl r0 = (X.AbstractC0224Wl) r0
            if (r0 != 0) goto L_0x001d
            java.util.HashSet<java.lang.String> r0 = r4.A01
            if (r0 == 0) goto L_0x001e
            boolean r0 = r0.contains(r5)
            if (r0 == 0) goto L_0x001e
        L_0x001b:
            X.Wl r0 = X.O4.A06
        L_0x001d:
            return r0
        L_0x001e:
            X.O4 r0 = r4.A05
            if (r0 == 0) goto L_0x0027
            X.Wl r0 = r0.A02(r5)
            return r0
        L_0x0027:
            java.lang.Class<?> r1 = r4.A04
            if (r1 == 0) goto L_0x003c
            java.lang.Class r0 = r1.getEnclosingClass()
            if (r0 == 0) goto L_0x003c
            int r0 = r1.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 != 0) goto L_0x0059
            goto L_0x001b
        L_0x003c:
            if (r1 != 0) goto L_0x0059
            X.Wl r0 = r4.A02
            if (r0 == 0) goto L_0x0056
            java.lang.String r3 = r0.toString()
        L_0x0046:
            java.lang.String r2 = "Type variable '"
            java.lang.String r1 = "' can not be resolved (with context of class "
            java.lang.String r0 = ")"
            java.lang.String r1 = X.AnonymousClass06.A07(r2, r5, r1, r3, r0)
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
        throw new UnsupportedOperationException("Method not decompiled: X.O4.A02(java.lang.String):X.Wl");
    }

    public final void A03(String str) {
        HashSet<String> hashSet = this.A01;
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.A01 = hashSet;
        }
        hashSet.add(str);
    }

    public final void A04(String str, AbstractC0224Wl wl) {
        Map<String, AbstractC0224Wl> map = this.A00;
        if (map == null || map.size() == 0) {
            this.A00 = new LinkedHashMap();
        }
        this.A00.put(str, wl);
    }

    public final String toString() {
        String name;
        if (this.A00 == null) {
            A00(this);
        }
        StringBuilder sb = new StringBuilder("[TypeBindings for ");
        AbstractC0224Wl wl = this.A02;
        if (wl != null) {
            name = wl.toString();
        } else {
            name = this.A04.getName();
        }
        sb.append(name);
        sb.append(": ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public O4(NT nt, O4 o4, Class<?> cls, AbstractC0224Wl wl) {
        this.A03 = nt;
        this.A05 = o4;
        this.A04 = cls;
        this.A02 = wl;
    }
}
