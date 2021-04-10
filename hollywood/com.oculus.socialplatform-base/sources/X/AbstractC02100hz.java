package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* renamed from: X.0hz  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02100hz extends AbstractC04250pf implements Serializable {
    public static final HashMap<String, Class<? extends Map>> A00;
    public static final HashMap<String, Class<? extends Collection>> A01;
    public final C04160pS _factoryConfig;

    @Override // X.AbstractC04250pf
    public final JsonDeserializer<?> A0G(AbstractC02210iH r9, AnonymousClass0C9 r10, AbstractC04010oz r11) throws C02180iD {
        AbstractC02190iF A04 = r10.A04();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AnonymousClass0HU r4 = r9._config;
        AbstractC04520qa r6 = (AbstractC04520qa) A04._typeHandler;
        if (r6 == null) {
            r6 = A0M(r4, A04);
        }
        for (AbstractC04260pg r2 : new C04780rF(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A31 = r2.A31(r10, r4, r11, r6, jsonDeserializer);
            if (A31 != null) {
                C04160pS r1 = this._factoryConfig;
                if (!r1.A00()) {
                    return A31;
                }
                Iterator it = new C04780rF(r1._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A31;
            }
        }
        return null;
    }

    @Override // X.AbstractC04250pf
    public final JsonDeserializer<?> A0I(AbstractC02210iH r10, AnonymousClass0C8 r11, AbstractC04010oz r12) throws C02180iD {
        AbstractC02190iF A05 = r11.A05();
        AbstractC02190iF A04 = r11.A04();
        AnonymousClass0HU r4 = r10._config;
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AnonymousClass0p6 r6 = (AnonymousClass0p6) A05._valueHandler;
        AbstractC04520qa r7 = (AbstractC04520qa) A04._typeHandler;
        if (r7 == null) {
            r7 = A0M(r4, A04);
        }
        for (AbstractC04260pg r2 : new C04780rF(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A37 = r2.A37(r11, r4, r12, r6, r7, jsonDeserializer);
            if (A37 != null) {
                C04160pS r1 = this._factoryConfig;
                if (!r1.A00()) {
                    return A37;
                }
                Iterator it = new C04780rF(r1._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A37;
            }
        }
        return null;
    }

    public abstract AbstractC04250pf A0O(C04160pS v);

    static {
        HashMap<String, Class<? extends Map>> hashMap = new HashMap<>();
        A00 = hashMap;
        hashMap.put(Map.class.getName(), LinkedHashMap.class);
        HashMap<String, Class<? extends Map>> hashMap2 = A00;
        hashMap2.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        hashMap2.put(SortedMap.class.getName(), TreeMap.class);
        hashMap2.put("java.util.NavigableMap", TreeMap.class);
        try {
            A00.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            StringBuilder sb = new StringBuilder("Problems with (optional) types: ");
            sb.append(th);
            printStream.println(sb.toString());
        }
        HashMap<String, Class<? extends Collection>> hashMap3 = new HashMap<>();
        A01 = hashMap3;
        hashMap3.put(Collection.class.getName(), ArrayList.class);
        HashMap<String, Class<? extends Collection>> hashMap4 = A01;
        hashMap4.put(List.class.getName(), ArrayList.class);
        hashMap4.put(Set.class.getName(), HashSet.class);
        hashMap4.put(SortedSet.class.getName(), TreeSet.class);
        hashMap4.put(Queue.class.getName(), LinkedList.class);
        hashMap4.put("java.util.Deque", LinkedList.class);
        hashMap4.put("java.util.NavigableSet", TreeSet.class);
    }

    public static final <T extends AbstractC02190iF> T A05(AbstractC02210iH r6, AnonymousClass0qA r7, T t) throws C02180iD {
        JsonDeserializer<Object> A0A;
        AnonymousClass0p6 A0I;
        AbstractC02230iJ A012 = r6._config.A01();
        Class<?> A0P = A012.A0P(r7, t);
        if (A0P != null) {
            try {
                t = (T) t.A07(A0P);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder("Failed to narrow type ");
                sb.append(t);
                sb.append(" with concrete-type annotation (value ");
                sb.append(A0P.getName());
                sb.append("), method '");
                sb.append(r7.A0L());
                sb.append("': ");
                sb.append(e.getMessage());
                throw new C02180iD(sb.toString(), null, e);
            }
        }
        if (!t.A0N()) {
            return t;
        }
        Class<?> A0O = A012.A0O(r7, t.A05());
        if (A0O != null) {
            if (t instanceof AnonymousClass0C8) {
                try {
                    t = (T) ((AnonymousClass0C8) t).A0P(A0O);
                } catch (IllegalArgumentException e2) {
                    StringBuilder sb2 = new StringBuilder("Failed to narrow key type ");
                    sb2.append(t);
                    sb2.append(" with key-type annotation (");
                    sb2.append(A0O.getName());
                    sb2.append("): ");
                    sb2.append(e2.getMessage());
                    throw new C02180iD(sb2.toString(), null, e2);
                }
            } else {
                StringBuilder sb3 = new StringBuilder("Illegal key-type annotation: type ");
                sb3.append(t);
                sb3.append(" is not a Map(-like) type");
                throw new C02180iD(sb3.toString());
            }
        }
        AbstractC02190iF A05 = t.A05();
        if (!(A05 == null || A05._valueHandler != null || (A0I = r6.A0I(r7, A012.A0X(r7))) == null)) {
            t = ((AnonymousClass0C8) t).A0S(A0I);
        }
        Class<?> A0N = A012.A0N(r7, t.A04());
        if (A0N != null) {
            try {
                t = (T) t.A0A(A0N);
            } catch (IllegalArgumentException e3) {
                StringBuilder sb4 = new StringBuilder("Failed to narrow content type ");
                sb4.append(t);
                sb4.append(" with content-type annotation (");
                sb4.append(A0N.getName());
                sb4.append("): ");
                sb4.append(e3.getMessage());
                throw new C02180iD(sb4.toString(), null, e3);
            }
        }
        return (t.A04()._valueHandler != null || (A0A = r6.A0A(r7, A012.A0T(r7))) == null) ? t : (T) t.A0D(A0A);
    }

    public static final JsonDeserializer<Object> A06(AbstractC02210iH r1, AnonymousClass0qA r2) throws C02180iD {
        Object A0W = r1._config.A01().A0W(r2);
        if (A0W == null) {
            return null;
        }
        return r1.A0A(r2, A0W);
    }

    private final JsonDeserializer<?> A07(Class<?> cls, AnonymousClass0HU r4, AbstractC04010oz r5) throws C02180iD {
        for (AbstractC04260pg r0 : new C04780rF(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A34 = r0.A34(cls, r4, r5);
            if (A34 != null) {
                return A34;
            }
        }
        return null;
    }

    private final AnonymousClass0HD A08(AbstractC02210iH r22, AbstractC04010oz r23, String str, int i, AnonymousClass0Ox r26, Object obj) throws C02180iD {
        boolean z;
        Boolean A0K;
        AnonymousClass0HU r5 = r22._config;
        AbstractC02230iJ A012 = r5.A01();
        if (A012 == null || (A0K = A012.A0K(r26)) == null) {
            z = false;
        } else {
            z = A0K.booleanValue();
        }
        AbstractC02190iF A09 = r5._base._typeFactory.A09(r26._type, r23.A0E());
        AnonymousClass0Sn r14 = new AnonymousClass0Sn(str, A09, null, r23.A0F(), r26, z);
        AbstractC02190iF A0N = A0N(r22, A09, r26);
        if (A0N != A09) {
            r14 = new AnonymousClass0Sn(r14.A03, A0N, r14.A00, r14.A02, r14.A01, r14.A04);
        }
        JsonDeserializer<Object> A06 = A06(r22, r26);
        AbstractC02190iF A05 = A05(r22, r26, A0N);
        AbstractC04520qa r11 = (AbstractC04520qa) A05._typeHandler;
        if (r11 == null) {
            r11 = A0M(r5, A05);
        }
        AnonymousClass0HD r7 = new AnonymousClass0HD(str, A05, r14.A00, r11, r23.A0F(), r26, i, obj, r14.A04);
        if (A06 != null) {
            return new AnonymousClass0HD(r7, A06);
        }
        return r7;
    }

    public static final C04820rL<?> A09(Class<?> cls, AnonymousClass0HU r7, AnonymousClass0Cr r8) {
        Enum[] enumArr;
        HashMap hashMap;
        if (r8 != null) {
            Method method = r8.A00;
            if (r7.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C04810rI.A06(method);
            }
            enumArr = (Enum[]) cls.getEnumConstants();
            hashMap = new HashMap();
            int length = enumArr.length;
            while (true) {
                length--;
                if (length < 0) {
                    break;
                }
                Enum r3 = enumArr[length];
                try {
                    Object invoke = method.invoke(r3, new Object[0]);
                    if (invoke != null) {
                        hashMap.put(invoke.toString(), r3);
                    }
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder("Failed to access @JsonValue of Enum value ");
                    sb.append(r3);
                    sb.append(": ");
                    sb.append(e.getMessage());
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        } else {
            if ((EnumC02200iG.READ_ENUMS_USING_TO_STRING.getMask() & r7._deserFeatures) != 0) {
                enumArr = (Enum[]) cls.getEnumConstants();
                hashMap = new HashMap();
                int length2 = enumArr.length;
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        break;
                    }
                    Enum r1 = enumArr[length2];
                    hashMap.put(r1.toString(), r1);
                }
            } else {
                r7.A01();
                enumArr = (Enum[]) cls.getEnumConstants();
                if (enumArr != null) {
                    hashMap = new HashMap();
                    for (Enum r12 : enumArr) {
                        hashMap.put(r12.name(), r12);
                    }
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A07("No enum constants for class ", cls.getName()));
                }
            }
        }
        return new C04820rL<>(cls, enumArr, hashMap);
    }

    @Override // X.AbstractC04250pf
    public final AbstractC02190iF A0C(AnonymousClass0HU r3, AbstractC02190iF r4) throws C02180iD {
        AbstractC03990ow[] r1 = this._factoryConfig._abstractTypeResolvers;
        if (r1.length > 0) {
            Iterator it = new C04780rF(r1).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        return r4;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: X.0pg */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC04250pf
    public final JsonDeserializer<?> A0D(AnonymousClass0HU r4, AbstractC02190iF r5, AbstractC04010oz r6) throws C02180iD {
        Class<?> cls = r5._class;
        for (AbstractC04260pg r0 : new C04780rF(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A3C = r0.A3C(cls, r4, r6);
            if (A3C != null) {
                return A3C;
            }
        }
        if (cls == AnonymousClass04L.class) {
            return JsonNodeDeserializer.ObjectDeserializer.A00;
        }
        if (cls == AnonymousClass04X.class) {
            return JsonNodeDeserializer.ArrayDeserializer.A00;
        }
        return JsonNodeDeserializer.A00;
    }

    @Override // X.AbstractC04250pf
    public final JsonDeserializer<?> A0E(AbstractC02210iH r7, AbstractC02190iF r8, AbstractC04010oz r9) throws C02180iD {
        Class cls;
        AnonymousClass0HU r5 = r7._config;
        Class<?> cls2 = r8._class;
        JsonDeserializer<?> A07 = A07(cls2, r5, r9);
        if (A07 == null) {
            Iterator<AnonymousClass0Cr> it = r9.A0O().iterator();
            while (true) {
                if (!it.hasNext()) {
                    A07 = new EnumDeserializer(A09(cls2, r5, r9.A0B()));
                    break;
                }
                AnonymousClass0Cr next = it.next();
                if (r7._config.A01().A0o(next)) {
                    if (next.A0Z() == 1) {
                        Method method = next.A00;
                        if (method.getReturnType().isAssignableFrom(cls2)) {
                            Class A0a = next.A0a();
                            if (A0a == String.class) {
                                cls = null;
                            } else if (A0a == Integer.TYPE || A0a == Integer.class) {
                                cls = Integer.class;
                            } else if (A0a == Long.TYPE || A0a == Long.class) {
                                cls = Long.class;
                            } else {
                                StringBuilder sb = new StringBuilder("Parameter #0 type for factory method (");
                                sb.append(next);
                                sb.append(") not suitable, must be java.lang.String or int/Integer/long/Long");
                                throw new IllegalArgumentException(sb.toString());
                            }
                            if (r5.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                C04810rI.A06(method);
                            }
                            A07 = new EnumDeserializer.FactoryBasedDeserializer(cls2, next, cls);
                        }
                    }
                    StringBuilder sb2 = new StringBuilder("Unsuitable method (");
                    sb2.append(next);
                    sb2.append(") decorated with @JsonCreator (for Enum type ");
                    sb2.append(cls2.getName());
                    sb2.append(")");
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
        }
        C04160pS r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C04780rF(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return A07;
    }

    @Override // X.AbstractC04250pf
    public final JsonDeserializer<?> A0F(AbstractC02210iH r11, AnonymousClass0CA r12, AbstractC04010oz r13) throws C02180iD {
        JsonDeserializer<?> objectArrayDeserializer;
        AnonymousClass0HU r6 = r11._config;
        AbstractC02190iF A04 = r12.A04();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AbstractC04520qa r8 = (AbstractC04520qa) A04._typeHandler;
        if (r8 == null) {
            r8 = A0M(r6, A04);
        }
        Iterator it = new C04780rF(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                objectArrayDeserializer = ((AbstractC04260pg) it.next()).A2x(r12, r6, r13, r8, jsonDeserializer);
                if (objectArrayDeserializer != null) {
                    break;
                }
            } else {
                if (jsonDeserializer == null) {
                    Class<?> cls = A04._class;
                    if (cls.isPrimitive()) {
                        if (cls == Integer.TYPE) {
                            return PrimitiveArrayDeserializers.IntDeser.A00;
                        }
                        if (cls == Long.TYPE) {
                            return PrimitiveArrayDeserializers.LongDeser.A00;
                        }
                        if (cls == Byte.TYPE) {
                            return new PrimitiveArrayDeserializers.ByteDeser();
                        }
                        if (cls == Short.TYPE) {
                            return new PrimitiveArrayDeserializers.ShortDeser();
                        }
                        if (cls == Float.TYPE) {
                            return new PrimitiveArrayDeserializers.FloatDeser();
                        }
                        if (cls == Double.TYPE) {
                            return new PrimitiveArrayDeserializers.DoubleDeser();
                        }
                        if (cls == Boolean.TYPE) {
                            return new PrimitiveArrayDeserializers.BooleanDeser();
                        }
                        if (cls == Character.TYPE) {
                            return new PrimitiveArrayDeserializers.CharDeser();
                        }
                        throw new IllegalStateException();
                    } else if (cls == String.class) {
                        return StringArrayDeserializer.A00;
                    }
                }
                objectArrayDeserializer = new ObjectArrayDeserializer(r12, jsonDeserializer, r8);
            }
        }
        C04160pS r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C04780rF(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return objectArrayDeserializer;
    }

    @Override // X.AbstractC04250pf
    public final JsonDeserializer<?> A0H(AbstractC02210iH r18, AnonymousClass03E r19, AbstractC04010oz r20) throws C02180iD {
        JsonDeserializer<?> collectionDeserializer;
        AbstractC02190iF A07;
        AbstractC04010oz r8 = r20;
        AnonymousClass03E r6 = r19;
        AbstractC02190iF A04 = r6.A04();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AnonymousClass0HU r7 = r18._config;
        AbstractC04520qa r9 = (AbstractC04520qa) A04._typeHandler;
        if (r9 == null) {
            r9 = A0M(r7, A04);
        }
        Iterator it = new C04780rF(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                collectionDeserializer = ((AbstractC04260pg) it.next()).A30(r6, r7, r8, r9, jsonDeserializer);
                if (collectionDeserializer != null) {
                    break;
                }
            } else {
                Class<?> cls = r6._class;
                if (jsonDeserializer != null || !EnumSet.class.isAssignableFrom(cls)) {
                    if (r6._class.isInterface() || r6.A0I()) {
                        Class<? extends Collection> cls2 = A01.get(r6._class.getName());
                        if (cls2 == null || (A07 = r7._base._typeFactory.A07(r6, cls2)) == null) {
                            StringBuilder sb = new StringBuilder("Can not find a deserializer for non-concrete Collection type ");
                            sb.append(r6);
                            throw new IllegalArgumentException(sb.toString());
                        }
                        r8 = r7._base._classIntrospector.A02(r7, A07, r7);
                        r6 = A07;
                    }
                    AbstractC04300pk A0P = A0P(r18, r8);
                    if (!A0P.A0K() && r6._class == ArrayBlockingQueue.class) {
                        return new ArrayBlockingQueueDeserializer(r6, jsonDeserializer, r9, A0P, null);
                    }
                    if (A04._class == String.class) {
                        collectionDeserializer = new StringCollectionDeserializer(r6, A0P, null, jsonDeserializer);
                    } else {
                        collectionDeserializer = new CollectionDeserializer(r6, jsonDeserializer, r9, A0P, null);
                    }
                } else {
                    collectionDeserializer = new EnumSetDeserializer(A04, null);
                }
            }
        }
        C04160pS r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C04780rF(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return collectionDeserializer;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0043 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.fasterxml.jackson.databind.deser.std.MapDeserializer] */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer] */
    /* JADX WARN: Type inference failed for: r13v2, types: [com.fasterxml.jackson.databind.JsonDeserializer<?>] */
    /* JADX WARN: Type inference failed for: r13v3, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        if (r0 != false) goto L_0x0089;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AbstractC04250pf
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A0J(X.AbstractC02210iH r20, X.AnonymousClass03D r21, X.AbstractC04010oz r22) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 239
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC02100hz.A0J(X.0iH, X.03D, X.0oz):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.AbstractC04250pf
    public final AnonymousClass0p6 A0K(AbstractC02210iH r8, AbstractC02190iF r9) throws C02180iD {
        AnonymousClass0p6 r0;
        AnonymousClass0HU r4 = r8._config;
        if (this._factoryConfig._additionalKeyDeserializers.length > 0) {
            AbstractC04010oz A02 = r4.A02(r4.A03(r9._class));
            Iterator it = new C04780rF(this._factoryConfig._additionalKeyDeserializers).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                r0 = ((AbstractC04270ph) it.next()).A35(r9, r4, A02);
                if (r0 != null) {
                    break;
                }
            }
        }
        if (r9._class.isEnum()) {
            AnonymousClass0HU r5 = r8._config;
            AbstractC04010oz A03 = r5._base._classIntrospector.A03(r5, r9, r5);
            JsonDeserializer<Object> A06 = A06(r8, A03.A07());
            if (A06 == null) {
                Class<?> cls = r9._class;
                if (A07(cls, r5, A03) == null) {
                    C04820rL<?> A09 = A09(cls, r5, A03.A0B());
                    for (AnonymousClass0Cr r3 : A03.A0O()) {
                        if (r5.A01().A0o(r3)) {
                            if (r3.A0Z() == 1) {
                                Method method = r3.A00;
                                if (method.getReturnType().isAssignableFrom(cls)) {
                                    if (r3.A0X(0) == String.class) {
                                        if (r5.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                            C04810rI.A06(method);
                                        }
                                        return new C01050Pl(A09, r3);
                                    }
                                    StringBuilder sb = new StringBuilder("Parameter #0 type for factory method (");
                                    sb.append(r3);
                                    sb.append(") not suitable, must be java.lang.String");
                                    throw new IllegalArgumentException(sb.toString());
                                }
                            }
                            StringBuilder sb2 = new StringBuilder("Unsuitable method (");
                            sb2.append(r3);
                            sb2.append(") decorated with @JsonCreator (for Enum type ");
                            sb2.append(cls.getName());
                            sb2.append(")");
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    }
                    return new C01050Pl(A09, null);
                }
            }
            return new C02040hr(r9._class, A06);
        }
        AbstractC04010oz A032 = r4._base._classIntrospector.A03(r4, r9, r4);
        Constructor<?> A0K = A032.A0K(String.class);
        if (A0K != null) {
            if (r4.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C04810rI.A06(A0K);
            }
            r0 = new AnonymousClass0PV(A0K);
        } else {
            Method A0L = A032.A0L(String.class);
            if (A0L == null) {
                return null;
            }
            if (r4.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C04810rI.A06(A0L);
            }
            r0 = new AnonymousClass0PD(A0L);
        }
        C04160pS r2 = this._factoryConfig;
        if (!r2.A00()) {
            return r0;
        }
        Iterator it2 = new C04780rF(r2._modifiers).iterator();
        while (it2.hasNext()) {
            it2.next();
        }
        return r0;
    }

    @Override // X.AbstractC04250pf
    public final AbstractC04250pf A0L(AbstractC04260pg r8) {
        C04160pS r1 = this._factoryConfig;
        if (r8 != null) {
            return A0O(new C04160pS((AbstractC04260pg[]) C04790rG.A01(r1._additionalDeserializers, r8), r1._additionalKeyDeserializers, r1._modifiers, r1._abstractTypeResolvers, r1._valueInstantiators));
        }
        throw new IllegalArgumentException("Can not pass null Deserializers");
    }

    @Override // X.AbstractC04250pf
    public final AbstractC04520qa A0M(AnonymousClass0HU r6, AbstractC02190iF r7) throws C02180iD {
        AbstractC02190iF A0C;
        Class<?> cls;
        C02000hn A07 = r6.A02(r6.A03(r7._class)).A07();
        AbstractC02230iJ A012 = r6.A01();
        AbstractC04540qc<?> A0D = A012.A0D(r6, A07, r7);
        Collection<AnonymousClass0qX> collection = null;
        if (A0D == null) {
            A0D = r6._base._typeResolverBuilder;
            if (A0D == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(A07, r6, A012);
        }
        if (A0D.A3l() == null && r7.A0I() && (A0C = A0C(r6, r7)) != null && (cls = A0C._class) != r7._class) {
            A0D.A2Y(cls);
        }
        return A0D.A1s(r6, r7, collection);
    }

    public AbstractC02100hz(C04160pS r1) {
        this._factoryConfig = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        if (r8 != null) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC02190iF A0N(X.AbstractC02210iH r6, X.AbstractC02190iF r7, X.AbstractC01990hm r8) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 131
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC02100hz.A0N(X.0iH, X.0iF, X.0hm):X.0iF");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r1 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0251, code lost:
        if (r5 == r7) goto L_0x0253;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC04300pk A0P(X.AbstractC02210iH r30, X.AbstractC04010oz r31) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 1098
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC02100hz.A0P(X.0iH, X.0oz):X.0pk");
    }
}
