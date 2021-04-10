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

/* renamed from: X.0gN  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03900gN extends AbstractC05460lB implements Serializable {
    public static final HashMap<String, Class<? extends Map>> A00;
    public static final HashMap<String, Class<? extends Collection>> A01;
    public final C05370kx _factoryConfig;

    @Override // X.AbstractC05460lB
    public final JsonDeserializer<?> A0G(AbstractC04020gg r9, AnonymousClass073 r10, AbstractC05180kU r11) throws C03990gZ {
        AbstractC04000gb A03 = r10.A03();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AnonymousClass08X r4 = r9._config;
        AnonymousClass0m9 r6 = (AnonymousClass0m9) A03._typeHandler;
        if (r6 == null) {
            r6 = A0L(r4, A03);
        }
        for (AnonymousClass0lC r2 : new C06300mr(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2i = r2.A2i(r10, r4, r11, r6, jsonDeserializer);
            if (A2i != null) {
                C05370kx r1 = this._factoryConfig;
                if (!r1.A00()) {
                    return A2i;
                }
                Iterator it = new C06300mr(r1._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A2i;
            }
        }
        return null;
    }

    @Override // X.AbstractC05460lB
    public final JsonDeserializer<?> A0I(AbstractC04020gg r10, AnonymousClass072 r11, AbstractC05180kU r12) throws C03990gZ {
        AbstractC04000gb A04 = r11.A04();
        AbstractC04000gb A03 = r11.A03();
        AnonymousClass08X r4 = r10._config;
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AbstractC05240kb r6 = (AbstractC05240kb) A04._valueHandler;
        AnonymousClass0m9 r7 = (AnonymousClass0m9) A03._typeHandler;
        if (r7 == null) {
            r7 = A0L(r4, A03);
        }
        for (AnonymousClass0lC r2 : new C06300mr(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2m = r2.A2m(r11, r4, r12, r6, r7, jsonDeserializer);
            if (A2m != null) {
                C05370kx r1 = this._factoryConfig;
                if (!r1.A00()) {
                    return A2m;
                }
                Iterator it = new C06300mr(r1._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A2m;
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

    public static final <T extends AbstractC04000gb> T A05(AbstractC04020gg r6, AbstractC05680lg r7, T t) throws C03990gZ {
        Class<?> A0J;
        IllegalArgumentException e;
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> A06;
        AbstractC05240kb A0A;
        AbstractC04040gi A012 = r6._config.A01();
        Class<?> A0K = A012.A0K(r7, t);
        if (A0K != null) {
            try {
                t = (T) t.A06(A0K);
            } catch (IllegalArgumentException e2) {
                StringBuilder sb2 = new StringBuilder("Failed to narrow type ");
                sb2.append(t);
                sb2.append(" with concrete-type annotation (value ");
                sb2.append(A0K.getName());
                sb2.append("), method '");
                sb2.append(r7.A0K());
                sb2.append("': ");
                sb2.append(e2.getMessage());
                throw new C03990gZ(sb2.toString(), null, e2);
            }
        }
        if (!t.A0J()) {
            return t;
        }
        A0J = A012.A0J(r7, t.A04());
        if (A0J != null) {
            if (t instanceof AnonymousClass072) {
                try {
                    t = (T) ((AnonymousClass072) t).A0L(A0J);
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    sb = new StringBuilder("Failed to narrow key type ");
                    sb.append(t);
                    str = " with key-type annotation (";
                }
            } else {
                StringBuilder sb3 = new StringBuilder("Illegal key-type annotation: type ");
                sb3.append(t);
                sb3.append(" is not a Map(-like) type");
                throw new C03990gZ(sb3.toString());
            }
        }
        AbstractC04000gb A04 = t.A04();
        if (!(A04 == null || A04._valueHandler != null || (A0A = r6.A0A(r7, A012.A0P(r7))) == null)) {
            t = ((AnonymousClass072) t).A0O(A0A);
        }
        A0J = A012.A0I(r7, t.A03());
        if (A0J != null) {
            try {
                t = (T) t.A08(A0J);
            } catch (IllegalArgumentException e4) {
                e = e4;
                sb = new StringBuilder("Failed to narrow content type ");
                sb.append(t);
                str = " with content-type annotation (";
            }
        }
        return (t.A03()._valueHandler != null || (A06 = r6.A06(r7, A012.A0M(r7))) == null) ? t : (T) t.A0A(A06);
        sb.append(str);
        sb.append(A0J.getName());
        sb.append("): ");
        sb.append(e.getMessage());
        throw new C03990gZ(sb.toString(), null, e);
    }

    public static final JsonDeserializer<Object> A06(AbstractC04020gg r1, AbstractC05680lg r2) throws C03990gZ {
        Object A0O = r1._config.A01().A0O(r2);
        if (A0O == null) {
            return null;
        }
        return r1.A06(r2, A0O);
    }

    private final JsonDeserializer<?> A07(Class<?> cls, AnonymousClass08X r4, AbstractC05180kU r5) throws C03990gZ {
        for (AnonymousClass0lC r0 : new C06300mr(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2j = r0.A2j(cls, r4, r5);
            if (A2j != null) {
                return A2j;
            }
        }
        return null;
    }

    private final AnonymousClass08T A08(AbstractC04020gg r22, AbstractC05180kU r23, String str, int i, AnonymousClass0GW r26, Object obj) throws C03990gZ {
        boolean z;
        Boolean A0H;
        AnonymousClass08X r5 = r22._config;
        AbstractC04040gi A012 = r5.A01();
        if (A012 == null || (A0H = A012.A0H(r26)) == null) {
            z = false;
        } else {
            z = A0H.booleanValue();
        }
        AbstractC04000gb A09 = r5._base._typeFactory.A09(r26._type, r23.A0B());
        AnonymousClass0HM r14 = new AnonymousClass0HM(str, A09, null, r23.A0C(), r26, z);
        AbstractC04000gb A0M = A0M(r22, A09, r26);
        if (A0M != A09) {
            r14 = new AnonymousClass0HM(r14.A03, A0M, r14.A00, r14.A02, r14.A01, r14.A04);
        }
        JsonDeserializer<Object> A06 = A06(r22, r26);
        AbstractC04000gb A05 = A05(r22, r26, A0M);
        AnonymousClass0m9 r11 = (AnonymousClass0m9) A05._typeHandler;
        if (r11 == null) {
            r11 = A0L(r5, A05);
        }
        AnonymousClass08T r7 = new AnonymousClass08T(str, A05, r14.A00, r11, r23.A0C(), r26, i, obj, r14.A04);
        if (A06 != null) {
            return new AnonymousClass08T(r7, A06);
        }
        return r7;
    }

    public static final C06360mx<?> A09(Class<?> cls, AnonymousClass08X r7, AnonymousClass07O r8) {
        Enum[] enumArr;
        HashMap hashMap;
        if (r8 != null) {
            Method method = r8.A00;
            if (r7.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C06330mu.A05(method);
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
            if ((EnumC04010gf.READ_ENUMS_USING_TO_STRING.getMask() & r7._deserFeatures) != 0) {
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
        return new C06360mx<>(cls, enumArr, hashMap);
    }

    @Override // X.AbstractC05460lB
    public final AbstractC04000gb A0C(AnonymousClass08X r3, AbstractC04000gb r4) throws C03990gZ {
        AbstractC05160kR[] r1 = this._factoryConfig._abstractTypeResolvers;
        if (r1.length > 0) {
            Iterator it = new C06300mr(r1).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        return r4;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: X.0lC */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC05460lB
    public final JsonDeserializer<?> A0D(AnonymousClass08X r4, AbstractC04000gb r5, AbstractC05180kU r6) throws C03990gZ {
        Class<?> cls = r5._class;
        for (AnonymousClass0lC r0 : new C06300mr(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2o = r0.A2o(cls, r4, r6);
            if (A2o != null) {
                return A2o;
            }
        }
        if (cls == AnonymousClass033.class) {
            return JsonNodeDeserializer.ObjectDeserializer.A00;
        }
        if (cls == AnonymousClass03C.class) {
            return JsonNodeDeserializer.ArrayDeserializer.A00;
        }
        return JsonNodeDeserializer.A00;
    }

    @Override // X.AbstractC05460lB
    public final JsonDeserializer<?> A0E(AbstractC04020gg r7, AbstractC04000gb r8, AbstractC05180kU r9) throws C03990gZ {
        StringBuilder sb;
        String str;
        Class cls;
        AnonymousClass08X r5 = r7._config;
        Class<?> cls2 = r8._class;
        JsonDeserializer<?> A07 = A07(cls2, r5, r9);
        if (A07 == null) {
            Iterator<AnonymousClass07O> it = r9.A0J().iterator();
            while (true) {
                if (!it.hasNext()) {
                    A07 = new EnumDeserializer(A09(cls2, r5, r9.A08()));
                    break;
                }
                AnonymousClass07O next = it.next();
                if (r7._config.A01().A0b(next)) {
                    if (next.A0W() == 1) {
                        Method method = next.A00;
                        if (method.getReturnType().isAssignableFrom(cls2)) {
                            Class A0X = next.A0X();
                            if (A0X == String.class) {
                                cls = null;
                            } else if (A0X == Integer.TYPE || A0X == Integer.class) {
                                cls = Integer.class;
                            } else if (A0X == Long.TYPE || A0X == Long.class) {
                                cls = Long.class;
                            } else {
                                sb = new StringBuilder("Parameter #0 type for factory method (");
                                sb.append(next);
                                str = ") not suitable, must be java.lang.String or int/Integer/long/Long";
                                sb.append(str);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            if (r5.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                C06330mu.A05(method);
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
        C05370kx r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C06300mr(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return A07;
    }

    @Override // X.AbstractC05460lB
    public final JsonDeserializer<?> A0F(AbstractC04020gg r11, AnonymousClass076 r12, AbstractC05180kU r13) throws C03990gZ {
        JsonDeserializer<?> objectArrayDeserializer;
        AnonymousClass08X r6 = r11._config;
        AbstractC04000gb A03 = r12.A03();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AnonymousClass0m9 r8 = (AnonymousClass0m9) A03._typeHandler;
        if (r8 == null) {
            r8 = A0L(r6, A03);
        }
        Iterator it = new C06300mr(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                objectArrayDeserializer = ((AnonymousClass0lC) it.next()).A2f(r12, r6, r13, r8, jsonDeserializer);
                if (objectArrayDeserializer != null) {
                    break;
                }
            } else {
                if (jsonDeserializer == null) {
                    Class<?> cls = A03._class;
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
        C05370kx r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C06300mr(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return objectArrayDeserializer;
    }

    @Override // X.AbstractC05460lB
    public final JsonDeserializer<?> A0H(AbstractC04020gg r18, AnonymousClass02Z r19, AbstractC05180kU r20) throws C03990gZ {
        JsonDeserializer<?> collectionDeserializer;
        AbstractC04000gb A07;
        AbstractC05180kU r8 = r20;
        AnonymousClass02Z r6 = r19;
        AbstractC04000gb A03 = r6.A03();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AnonymousClass08X r7 = r18._config;
        AnonymousClass0m9 r9 = (AnonymousClass0m9) A03._typeHandler;
        if (r9 == null) {
            r9 = A0L(r7, A03);
        }
        Iterator it = new C06300mr(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                collectionDeserializer = ((AnonymousClass0lC) it.next()).A2h(r6, r7, r8, r9, jsonDeserializer);
                if (collectionDeserializer != null) {
                    break;
                }
            } else {
                Class<?> cls = r6._class;
                if (jsonDeserializer != null || !EnumSet.class.isAssignableFrom(cls)) {
                    if (r6._class.isInterface() || r6.A0E()) {
                        Class<? extends Collection> cls2 = A01.get(r6._class.getName());
                        if (cls2 == null || (A07 = r7._base._typeFactory.A07(r6, cls2)) == null) {
                            StringBuilder sb = new StringBuilder("Can not find a deserializer for non-concrete Collection type ");
                            sb.append(r6);
                            throw new IllegalArgumentException(sb.toString());
                        }
                        r8 = r7._base._classIntrospector.A01(r7, A07, r7);
                        r6 = A07;
                    }
                    AnonymousClass0lG A0N = A0N(r18, r8);
                    if (!A0N.A0K() && r6._class == ArrayBlockingQueue.class) {
                        return new ArrayBlockingQueueDeserializer(r6, jsonDeserializer, r9, A0N, null);
                    }
                    if (A03._class == String.class) {
                        collectionDeserializer = new StringCollectionDeserializer(r6, A0N, null, jsonDeserializer);
                    } else {
                        collectionDeserializer = new CollectionDeserializer(r6, jsonDeserializer, r9, A0N, null);
                    }
                } else {
                    collectionDeserializer = new EnumSetDeserializer(A03, null);
                }
            }
        }
        C05370kx r1 = this._factoryConfig;
        if (r1.A00()) {
            Iterator it2 = new C06300mr(r1._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return collectionDeserializer;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0043 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.fasterxml.jackson.databind.deser.std.MapDeserializer] */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer] */
    /* JADX WARN: Type inference failed for: r13v2, types: [com.fasterxml.jackson.databind.JsonDeserializer<?>] */
    /* JADX WARN: Type inference failed for: r13v3, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        if (r0 != false) goto L_0x0089;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AbstractC05460lB
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A0J(X.AbstractC04020gg r20, X.AnonymousClass02Y r21, X.AbstractC05180kU r22) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 245
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03900gN.A0J(X.0gg, X.02Y, X.0kU):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.AbstractC05460lB
    public final AbstractC05240kb A0K(AbstractC04020gg r8, AbstractC04000gb r9) throws C03990gZ {
        AbstractC05240kb r0;
        StringBuilder sb;
        String str;
        AnonymousClass08X r6 = r8._config;
        if (this._factoryConfig._additionalKeyDeserializers.length > 0) {
            AbstractC05180kU A02 = r6.A02(r6.A03(r9._class));
            Iterator it = new C06300mr(this._factoryConfig._additionalKeyDeserializers).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                r0 = ((AnonymousClass0lD) it.next()).A2k(r9, r6, A02);
                if (r0 != null) {
                    break;
                }
            }
        }
        if (r9._class.isEnum()) {
            AnonymousClass08X r5 = r8._config;
            AbstractC05180kU A022 = r5._base._classIntrospector.A02(r5, r9, r5);
            JsonDeserializer<Object> A06 = A06(r8, A022.A05());
            if (A06 == null) {
                Class<?> cls = r9._class;
                if (A07(cls, r5, A022) == null) {
                    C06360mx<?> A09 = A09(cls, r5, A022.A08());
                    for (AnonymousClass07O r3 : A022.A0J()) {
                        if (r5.A01().A0b(r3)) {
                            if (r3.A0W() == 1) {
                                Method method = r3.A00;
                                if (method.getReturnType().isAssignableFrom(cls)) {
                                    if (r3.A0U(0) == String.class) {
                                        if (r5.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                            C06330mu.A05(method);
                                        }
                                        return new C00880Gx(A09, r3);
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
                    return new C00880Gx(A09, null);
                }
            }
            return new C03850gE(r9._class, A06);
        }
        AbstractC05180kU A023 = r6._base._classIntrospector.A02(r6, r9, r6);
        Constructor<?> A0F = A023.A0F(String.class);
        if (A0F != null) {
            if (r6.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C06330mu.A05(A0F);
            }
            r0 = new C00800Gj(A0F);
        } else {
            Method A0G = A023.A0G(String.class);
            if (A0G == null) {
                return null;
            }
            if (r6.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                C06330mu.A05(A0G);
            }
            r0 = new C00790Gi(A0G);
        }
        C05370kx r2 = this._factoryConfig;
        if (!r2.A00()) {
            return r0;
        }
        Iterator it2 = new C06300mr(r2._modifiers).iterator();
        while (it2.hasNext()) {
            it2.next();
        }
        return r0;
    }

    @Override // X.AbstractC05460lB
    public final AnonymousClass0m9 A0L(AnonymousClass08X r6, AbstractC04000gb r7) throws C03990gZ {
        AbstractC04000gb A0C;
        Class<?> cls;
        C03810gA A05 = r6.A02(r6.A03(r7._class)).A05();
        AbstractC04040gi A012 = r6.A01();
        AbstractC05950mB<?> A0A = A012.A0A(r6, A05, r7);
        Collection<C05910m6> collection = null;
        if (A0A == null) {
            A0A = r6._base._typeResolverBuilder;
            if (A0A == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(A05, r6, A012);
        }
        if (A0A.A3I() == null && r7.A0E() && (A0C = A0C(r6, r7)) != null && (cls = A0C._class) != r7._class) {
            A0A.A2C(cls);
        }
        return A0A.A1W(r6, r7, collection);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r2 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x024b, code lost:
        if (r5 == r7) goto L_0x024d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass0lG A0N(X.AbstractC04020gg r30, X.AbstractC05180kU r31) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 1102
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03900gN.A0N(X.0gg, X.0kU):X.0lG");
    }

    public AbstractC03900gN(C05370kx r1) {
        this._factoryConfig = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        if (r8 != null) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC04000gb A0M(X.AbstractC04020gg r6, X.AbstractC04000gb r7, X.AnonymousClass0g9 r8) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 131
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03900gN.A0M(X.0gg, X.0gb, X.0g9):X.0gb");
    }
}
