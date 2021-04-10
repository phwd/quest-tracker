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

/* renamed from: X.0a4  reason: invalid class name */
public abstract class AnonymousClass0a4 extends AbstractC06540n6 implements Serializable {
    public static final HashMap<String, Class<? extends Map>> A00;
    public static final HashMap<String, Class<? extends Collection>> A01;
    public final C06440mt _factoryConfig;

    @Override // X.AbstractC06540n6
    public final JsonDeserializer<?> A0G(AbstractC02570aK r9, AnonymousClass0CB r10, AbstractC06260mR r11) throws AnonymousClass0aG {
        AnonymousClass0aI A04 = r10.A04();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        C01260Fu r4 = r9._config;
        AnonymousClass0o3 r6 = (AnonymousClass0o3) A04._typeHandler;
        if (r6 == null) {
            r6 = A0L(r4, A04);
        }
        for (AnonymousClass0n7 r2 : new C07100oj(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2c = r2.A2c(r10, r4, r11, r6, jsonDeserializer);
            if (A2c != null) {
                C06440mt r1 = this._factoryConfig;
                if (!r1.A00()) {
                    return A2c;
                }
                Iterator it = new C07100oj(r1._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A2c;
            }
        }
        return null;
    }

    @Override // X.AbstractC06540n6
    public final JsonDeserializer<?> A0I(AbstractC02570aK r10, AnonymousClass0CA r11, AbstractC06260mR r12) throws AnonymousClass0aG {
        AnonymousClass0aI A05 = r11.A05();
        AnonymousClass0aI A04 = r11.A04();
        C01260Fu r4 = r10._config;
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AnonymousClass0mY r6 = (AnonymousClass0mY) A05._valueHandler;
        AnonymousClass0o3 r7 = (AnonymousClass0o3) A04._typeHandler;
        if (r7 == null) {
            r7 = A0L(r4, A04);
        }
        for (AnonymousClass0n7 r2 : new C07100oj(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2i = r2.A2i(r11, r4, r12, r6, r7, jsonDeserializer);
            if (A2i != null) {
                C06440mt r1 = this._factoryConfig;
                if (!r1.A00()) {
                    return A2i;
                }
                Iterator it = new C07100oj(r1._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A2i;
            }
        }
        return null;
    }

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
            printStream.println("Problems with (optional) types: " + th);
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

    public static final <T extends AnonymousClass0aI> T A05(AbstractC02570aK r6, AbstractC06640nb r7, T t) throws AnonymousClass0aG {
        Class<?> A0O;
        IllegalArgumentException e;
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> A0A;
        AnonymousClass0mY A0H;
        AbstractC02590aM A012 = r6._config.A01();
        Class<?> A0P = A012.A0P(r7, t);
        if (A0P != null) {
            try {
                t = (T) t.A07(A0P);
            } catch (IllegalArgumentException e2) {
                throw new AnonymousClass0aG("Failed to narrow type " + t + " with concrete-type annotation (value " + A0P.getName() + "), method '" + r7.A0L() + "': " + e2.getMessage(), null, e2);
            }
        }
        if (!t.A0N()) {
            return t;
        }
        A0O = A012.A0O(r7, t.A05());
        if (A0O != null) {
            if (t instanceof AnonymousClass0CA) {
                try {
                    t = (T) ((AnonymousClass0CA) t).A0P(A0O);
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    sb = new StringBuilder("Failed to narrow key type ");
                    sb.append(t);
                    str = " with key-type annotation (";
                }
            } else {
                throw new AnonymousClass0aG("Illegal key-type annotation: type " + t + " is not a Map(-like) type");
            }
        }
        AnonymousClass0aI A05 = t.A05();
        if (!(A05 == null || A05._valueHandler != null || (A0H = r6.A0H(r7, A012.A0X(r7))) == null)) {
            t = ((AnonymousClass0CA) t).A0S(A0H);
        }
        A0O = A012.A0N(r7, t.A04());
        if (A0O != null) {
            try {
                t = (T) t.A0A(A0O);
            } catch (IllegalArgumentException e4) {
                e = e4;
                sb = new StringBuilder("Failed to narrow content type ");
                sb.append(t);
                str = " with content-type annotation (";
            }
        }
        return (t.A04()._valueHandler != null || (A0A = r6.A0A(r7, A012.A0T(r7))) == null) ? t : (T) t.A0D(A0A);
        sb.append(str);
        sb.append(A0O.getName());
        sb.append("): ");
        sb.append(e.getMessage());
        throw new AnonymousClass0aG(sb.toString(), null, e);
    }

    public static final JsonDeserializer<Object> A06(AbstractC02570aK r1, AbstractC06640nb r2) throws AnonymousClass0aG {
        Object A0W = r1._config.A01().A0W(r2);
        if (A0W == null) {
            return null;
        }
        return r1.A0A(r2, A0W);
    }

    private final JsonDeserializer<?> A07(Class<?> cls, C01260Fu r4, AbstractC06260mR r5) throws AnonymousClass0aG {
        for (AnonymousClass0n7 r0 : new C07100oj(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2f = r0.A2f(cls, r4, r5);
            if (A2f != null) {
                return A2f;
            }
        }
        return null;
    }

    private final AnonymousClass0F3 A08(AbstractC02570aK r22, AbstractC06260mR r23, String str, int i, AnonymousClass0KB r26, Object obj) throws AnonymousClass0aG {
        boolean z;
        Boolean A0K;
        C01260Fu r5 = r22._config;
        AbstractC02590aM A012 = r5.A01();
        if (A012 == null || (A0K = A012.A0K(r26)) == null) {
            z = false;
        } else {
            z = A0K.booleanValue();
        }
        AnonymousClass0aI A09 = r5._base._typeFactory.A09(r26._type, r23.A0E());
        AnonymousClass0LE r14 = new AnonymousClass0LE(str, A09, null, r23.A0F(), r26, z);
        AnonymousClass0aI A0M = A0M(r22, A09, r26);
        if (A0M != A09) {
            r14 = new AnonymousClass0LE(r14.A03, A0M, r14.A00, r14.A02, r14.A01, r14.A04);
        }
        JsonDeserializer<Object> A06 = A06(r22, r26);
        AnonymousClass0aI A05 = A05(r22, r26, A0M);
        AnonymousClass0o3 r11 = (AnonymousClass0o3) A05._typeHandler;
        if (r11 == null) {
            r11 = A0L(r5, A05);
        }
        AnonymousClass0F3 r7 = new AnonymousClass0F3(str, A05, r14.A00, r11, r23.A0F(), r26, i, obj, r14.A04);
        if (A06 != null) {
            return new AnonymousClass0F3(r7, A06);
        }
        return r7;
    }

    public static final C07160op<?> A09(Class<?> cls, C01260Fu r7, AnonymousClass0EC r8) {
        Enum[] enumArr;
        HashMap hashMap;
        if (r8 != null) {
            Method method = r8.A00;
            if (r7.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C07130om.A06(method);
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
                    throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + r3 + ": " + e.getMessage());
                }
            }
        } else {
            if ((EnumC02560aJ.READ_ENUMS_USING_TO_STRING.getMask() & r7._deserFeatures) != 0) {
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
                    throw new IllegalArgumentException(AnonymousClass006.A05("No enum constants for class ", cls.getName()));
                }
            }
        }
        return new C07160op<>(cls, enumArr, hashMap);
    }

    @Override // X.AbstractC06540n6
    public final AnonymousClass0aI A0C(C01260Fu r3, AnonymousClass0aI r4) throws AnonymousClass0aG {
        AbstractC06240mO[] r1 = this._factoryConfig._abstractTypeResolvers;
        if (r1.length > 0) {
            Iterator it = new C07100oj(r1).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        return r4;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: X.0n7 */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC06540n6
    public final JsonDeserializer<?> A0D(C01260Fu r4, AnonymousClass0aI r5, AbstractC06260mR r6) throws AnonymousClass0aG {
        Class<?> cls = r5._class;
        for (AnonymousClass0n7 r0 : new C07100oj(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2n = r0.A2n(cls, r4, r6);
            if (A2n != null) {
                return A2n;
            }
        }
        if (cls == C007107f.class) {
            return JsonNodeDeserializer.ObjectDeserializer.A00;
        }
        if (cls == AnonymousClass07p.class) {
            return JsonNodeDeserializer.ArrayDeserializer.A00;
        }
        return JsonNodeDeserializer.A00;
    }

    @Override // X.AbstractC06540n6
    public final JsonDeserializer<?> A0E(AbstractC02570aK r7, AnonymousClass0aI r8, AbstractC06260mR r9) throws AnonymousClass0aG {
        StringBuilder sb;
        String str;
        Class cls;
        C01260Fu r5 = r7._config;
        Class<?> cls2 = r8._class;
        JsonDeserializer<?> A07 = A07(cls2, r5, r9);
        if (A07 == null) {
            Iterator<AnonymousClass0EC> it = r9.A0O().iterator();
            while (true) {
                if (!it.hasNext()) {
                    A07 = new EnumDeserializer(A09(cls2, r5, r9.A0B()));
                    break;
                }
                AnonymousClass0EC next = it.next();
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
                                sb = new StringBuilder("Parameter #0 type for factory method (");
                                sb.append(next);
                                str = ") not suitable, must be java.lang.String or int/Integer/long/Long";
                                sb.append(str);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            if (r5.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                C07130om.A06(method);
                            }
                            A07 = new EnumDeserializer.FactoryBasedDeserializer(cls2, next, cls);
                        }
                    }
                    sb = new StringBuilder("Unsuitable method (");
                    sb.append(next);
                    sb.append(") decorated with @JsonCreator (for Enum type ");
                    sb.append(cls2.getName());
                    str = ")";
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        C06440mt r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C07100oj(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return A07;
    }

    @Override // X.AbstractC06540n6
    public final JsonDeserializer<?> A0F(AbstractC02570aK r11, AnonymousClass0CC r12, AbstractC06260mR r13) throws AnonymousClass0aG {
        JsonDeserializer<?> objectArrayDeserializer;
        C01260Fu r6 = r11._config;
        AnonymousClass0aI A04 = r12.A04();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AnonymousClass0o3 r8 = (AnonymousClass0o3) A04._typeHandler;
        if (r8 == null) {
            r8 = A0L(r6, A04);
        }
        Iterator it = new C07100oj(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                objectArrayDeserializer = ((AnonymousClass0n7) it.next()).A2Y(r12, r6, r13, r8, jsonDeserializer);
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
        C06440mt r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C07100oj(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return objectArrayDeserializer;
    }

    @Override // X.AbstractC06540n6
    public final JsonDeserializer<?> A0H(AbstractC02570aK r18, C006606d r19, AbstractC06260mR r20) throws AnonymousClass0aG {
        JsonDeserializer<?> collectionDeserializer;
        AnonymousClass0aI A07;
        AbstractC06260mR r8 = r20;
        C006606d r6 = r19;
        AnonymousClass0aI A04 = r6.A04();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        C01260Fu r7 = r18._config;
        AnonymousClass0o3 r9 = (AnonymousClass0o3) A04._typeHandler;
        if (r9 == null) {
            r9 = A0L(r7, A04);
        }
        Iterator it = new C07100oj(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                collectionDeserializer = ((AnonymousClass0n7) it.next()).A2b(r6, r7, r8, r9, jsonDeserializer);
                if (collectionDeserializer != null) {
                    break;
                }
            } else {
                Class<?> cls = r6._class;
                if (jsonDeserializer != null || !EnumSet.class.isAssignableFrom(cls)) {
                    if (r6._class.isInterface() || r6.A0I()) {
                        Class<? extends Collection> cls2 = A01.get(r6._class.getName());
                        if (cls2 == null || (A07 = r7._base._typeFactory.A07(r6, cls2)) == null) {
                            throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + r6);
                        }
                        r8 = r7._base._classIntrospector.A02(r7, A07, r7);
                        r6 = A07;
                    }
                    AnonymousClass0nB A0N = A0N(r18, r8);
                    if (!A0N.A0K() && r6._class == ArrayBlockingQueue.class) {
                        return new ArrayBlockingQueueDeserializer(r6, jsonDeserializer, r9, A0N, null);
                    }
                    if (A04._class == String.class) {
                        collectionDeserializer = new StringCollectionDeserializer(r6, A0N, null, jsonDeserializer);
                    } else {
                        collectionDeserializer = new CollectionDeserializer(r6, jsonDeserializer, r9, A0N, null);
                    }
                } else {
                    collectionDeserializer = new EnumSetDeserializer(A04, null);
                }
            }
        }
        C06440mt r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C07100oj(r1._modifiers).iterator();
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
    @Override // X.AbstractC06540n6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A0J(X.AbstractC02570aK r20, X.C006506c r21, X.AbstractC06260mR r22) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 234
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0a4.A0J(X.0aK, X.06c, X.0mR):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.AbstractC06540n6
    public final AnonymousClass0mY A0K(AbstractC02570aK r8, AnonymousClass0aI r9) throws AnonymousClass0aG {
        AnonymousClass0mY r0;
        StringBuilder sb;
        String str;
        C01260Fu r4 = r8._config;
        if (this._factoryConfig._additionalKeyDeserializers.length > 0) {
            AbstractC06260mR A02 = r4.A02(r4.A03(r9._class));
            Iterator it = new C07100oj(this._factoryConfig._additionalKeyDeserializers).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                r0 = ((AnonymousClass0n8) it.next()).A2g(r9, r4, A02);
                if (r0 != null) {
                    break;
                }
            }
        }
        if (r9._class.isEnum()) {
            C01260Fu r5 = r8._config;
            AbstractC06260mR A03 = r5._base._classIntrospector.A03(r5, r9, r5);
            JsonDeserializer<Object> A06 = A06(r8, A03.A07());
            if (A06 == null) {
                Class<?> cls = r9._class;
                if (A07(cls, r5, A03) == null) {
                    C07160op<?> A09 = A09(cls, r5, A03.A0B());
                    for (AnonymousClass0EC r3 : A03.A0O()) {
                        if (r5.A01().A0o(r3)) {
                            if (r3.A0Z() == 1) {
                                Method method = r3.A00;
                                if (method.getReturnType().isAssignableFrom(cls)) {
                                    if (r3.A0X(0) == String.class) {
                                        if (r5.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                            C07130om.A06(method);
                                        }
                                        return new AnonymousClass0KU(A09, r3);
                                    }
                                    sb = new StringBuilder("Parameter #0 type for factory method (");
                                    sb.append(r3);
                                    str = ") not suitable, must be java.lang.String";
                                    sb.append(str);
                                    throw new IllegalArgumentException(sb.toString());
                                }
                            }
                            sb = new StringBuilder("Unsuitable method (");
                            sb.append(r3);
                            sb.append(") decorated with @JsonCreator (for Enum type ");
                            sb.append(cls.getName());
                            str = ")";
                            sb.append(str);
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    return new AnonymousClass0KU(A09, null);
                }
            }
            return new C02500Zw(r9._class, A06);
        }
        AbstractC06260mR A032 = r4._base._classIntrospector.A03(r4, r9, r4);
        Constructor<?> A0K = A032.A0K(String.class);
        if (A0K != null) {
            if (r4.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C07130om.A06(A0K);
            }
            r0 = new AnonymousClass0KN(A0K);
        } else {
            Method A0L = A032.A0L(String.class);
            if (A0L == null) {
                return null;
            }
            if (r4.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C07130om.A06(A0L);
            }
            r0 = new AnonymousClass0KM(A0L);
        }
        C06440mt r2 = this._factoryConfig;
        if (!r2.A00()) {
            return r0;
        }
        Iterator it2 = new C07100oj(r2._modifiers).iterator();
        while (it2.hasNext()) {
            it2.next();
        }
        return r0;
    }

    @Override // X.AbstractC06540n6
    public final AnonymousClass0o3 A0L(C01260Fu r6, AnonymousClass0aI r7) throws AnonymousClass0aG {
        AnonymousClass0aI A0C;
        Class<?> cls;
        C02460Zs A07 = r6.A02(r6.A03(r7._class)).A07();
        AbstractC02590aM A012 = r6.A01();
        AnonymousClass0o5<?> A0D = A012.A0D(r6, A07, r7);
        Collection<AnonymousClass0o0> collection = null;
        if (A0D == null) {
            A0D = r6._base._typeResolverBuilder;
            if (A0D == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(A07, r6, A012);
        }
        if (A0D.A3N() == null && r7.A0I() && (A0C = A0C(r6, r7)) != null && (cls = A0C._class) != r7._class) {
            A0D.A25(cls);
        }
        return A0D.A1b(r6, r7, collection);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r2 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x024b, code lost:
        if (r5 == r7) goto L_0x024d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass0nB A0N(X.AbstractC02570aK r30, X.AbstractC06260mR r31) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 1102
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0a4.A0N(X.0aK, X.0mR):X.0nB");
    }

    public AnonymousClass0a4(C06440mt r1) {
        this._factoryConfig = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        if (r8 != null) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass0aI A0M(X.AbstractC02570aK r6, X.AnonymousClass0aI r7, X.AbstractC02450Zr r8) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 131
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0a4.A0M(X.0aK, X.0aI, X.0Zr):X.0aI");
    }
}
