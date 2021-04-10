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

public abstract class WW extends ZD implements Serializable {
    public static final HashMap<String, Class<? extends Map>> A00;
    public static final HashMap<String, Class<? extends Collection>> A01;
    public final dT _factoryConfig;

    @Override // X.ZD
    public final JsonDeserializer<?> A0G(AbstractC0226Wn wn, AnonymousClass75 r10, jm jmVar) throws C0223Wj {
        AbstractC0224Wl A03 = r10.A03();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AnonymousClass8M r4 = wn._config;
        V4 v4 = (V4) A03._typeHandler;
        if (v4 == null) {
            v4 = A0L(r4, A03);
        }
        for (Z6 z6 : new N4(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A25 = z6.A25(r10, r4, jmVar, v4, jsonDeserializer);
            if (A25 != null) {
                dT dTVar = this._factoryConfig;
                if (!dTVar.A00()) {
                    return A25;
                }
                Iterator it = new N4(dTVar._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A25;
            }
        }
        return null;
    }

    @Override // X.ZD
    public final JsonDeserializer<?> A0I(AbstractC0226Wn wn, AnonymousClass74 r11, jm jmVar) throws C0223Wj {
        AbstractC0224Wl A04 = r11.A04();
        AbstractC0224Wl A03 = r11.A03();
        AnonymousClass8M r4 = wn._config;
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AbstractC0420hV hVVar = (AbstractC0420hV) A04._valueHandler;
        V4 v4 = (V4) A03._typeHandler;
        if (v4 == null) {
            v4 = A0L(r4, A03);
        }
        for (Z6 z6 : new N4(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A29 = z6.A29(r11, r4, jmVar, hVVar, v4, jsonDeserializer);
            if (A29 != null) {
                dT dTVar = this._factoryConfig;
                if (!dTVar.A00()) {
                    return A29;
                }
                Iterator it = new N4(dTVar._modifiers).iterator();
                while (it.hasNext()) {
                    it.next();
                }
                return A29;
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

    public static final <T extends AbstractC0224Wl> T A05(AbstractC0226Wn wn, VV vv, T t) throws C0223Wj {
        Class<?> A0K;
        IllegalArgumentException e;
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> A07;
        AbstractC0420hV A0E;
        Wp A012 = wn._config.A01();
        Class<?> A0L = A012.A0L(vv, t);
        if (A0L != null) {
            try {
                t = (T) t.A06(A0L);
            } catch (IllegalArgumentException e2) {
                StringBuilder sb2 = new StringBuilder("Failed to narrow type ");
                sb2.append(t);
                sb2.append(" with concrete-type annotation (value ");
                sb2.append(A0L.getName());
                sb2.append("), method '");
                sb2.append(vv.A0K());
                sb2.append("': ");
                sb2.append(e2.getMessage());
                throw new C0223Wj(sb2.toString(), null, e2);
            }
        }
        if (!t.A0J()) {
            return t;
        }
        A0K = A012.A0K(vv, t.A04());
        if (A0K != null) {
            if (t instanceof AnonymousClass74) {
                try {
                    t = (T) ((AnonymousClass74) t).A0L(A0K);
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
                throw new C0223Wj(sb3.toString());
            }
        }
        AbstractC0224Wl A04 = t.A04();
        if (!(A04 == null || A04._valueHandler != null || (A0E = wn.A0E(vv, A012.A0Q(vv))) == null)) {
            t = ((AnonymousClass74) t).A0O(A0E);
        }
        A0K = A012.A0J(vv, t.A03());
        if (A0K != null) {
            try {
                t = (T) t.A08(A0K);
            } catch (IllegalArgumentException e4) {
                e = e4;
                sb = new StringBuilder("Failed to narrow content type ");
                sb.append(t);
                str = " with content-type annotation (";
            }
        }
        return (t.A03()._valueHandler != null || (A07 = wn.A07(vv, A012.A0N(vv))) == null) ? t : (T) t.A0A(A07);
        sb.append(str);
        sb.append(A0K.getName());
        sb.append("): ");
        sb.append(e.getMessage());
        throw new C0223Wj(sb.toString(), null, e);
    }

    public static final JsonDeserializer<Object> A06(AbstractC0226Wn wn, VV vv) throws C0223Wj {
        Object A0P = wn._config.A01().A0P(vv);
        if (A0P == null) {
            return null;
        }
        return wn.A07(vv, A0P);
    }

    private final JsonDeserializer<?> A07(Class<?> cls, AnonymousClass8M r4, jm jmVar) throws C0223Wj {
        for (Z6 z6 : new N4(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A26 = z6.A26(cls, r4, jmVar);
            if (A26 != null) {
                return A26;
            }
        }
        return null;
    }

    private final AnonymousClass8I A08(AbstractC0226Wn wn, jm jmVar, String str, int i, CC cc, Object obj) throws C0223Wj {
        boolean z;
        Boolean A0I;
        AnonymousClass8M r5 = wn._config;
        Wp A012 = r5.A01();
        if (A012 == null || (A0I = A012.A0I(cc)) == null) {
            z = false;
        } else {
            z = A0I.booleanValue();
        }
        AbstractC0224Wl A09 = r5._base._typeFactory.A09(cc._type, jmVar.A0B());
        DJ dj = new DJ(str, A09, null, jmVar.A0C(), cc, z);
        AbstractC0224Wl A0M = A0M(wn, A09, cc);
        if (A0M != A09) {
            dj = new DJ(dj.A03, A0M, dj.A00, dj.A02, dj.A01, dj.A04);
        }
        JsonDeserializer<Object> A06 = A06(wn, cc);
        AbstractC0224Wl A05 = A05(wn, cc, A0M);
        V4 v4 = (V4) A05._typeHandler;
        if (v4 == null) {
            v4 = A0L(r5, A05);
        }
        AnonymousClass8I r7 = new AnonymousClass8I(str, A05, dj.A00, v4, jmVar.A0C(), cc, i, obj, dj.A04);
        if (A06 != null) {
            return new AnonymousClass8I(r7, A06);
        }
        return r7;
    }

    public static final M9<?> A09(Class<?> cls, AnonymousClass8M r7, AnonymousClass7P r8) {
        Enum[] enumArr;
        HashMap hashMap;
        if (r8 != null) {
            Method method = r8.A00;
            if (r7.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                Mv.A05(method);
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
        } else if (r7.A06(EnumC0225Wm.READ_ENUMS_USING_TO_STRING)) {
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
                throw new IllegalArgumentException(AnonymousClass06.A04("No enum constants for class ", cls.getName()));
            }
        }
        return new M9<>(cls, enumArr, hashMap);
    }

    @Override // X.ZD
    public final AbstractC0224Wl A0C(AnonymousClass8M r3, AbstractC0224Wl wl) throws C0223Wj {
        js[] jsVarArr = this._factoryConfig._abstractTypeResolvers;
        if (jsVarArr.length > 0) {
            Iterator it = new N4(jsVarArr).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        return wl;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: X.Z6 */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.ZD
    public final JsonDeserializer<?> A0D(AnonymousClass8M r4, AbstractC0224Wl wl, jm jmVar) throws C0223Wj {
        Class<?> cls = wl._class;
        for (Z6 z6 : new N4(this._factoryConfig._additionalDeserializers)) {
            JsonDeserializer<?> A2B = z6.A2B(cls, r4, jmVar);
            if (A2B != null) {
                return A2B;
            }
        }
        if (cls == AnonymousClass2z.class) {
            return JsonNodeDeserializer.ObjectDeserializer.A00;
        }
        if (cls == AnonymousClass38.class) {
            return JsonNodeDeserializer.ArrayDeserializer.A00;
        }
        return JsonNodeDeserializer.A00;
    }

    @Override // X.ZD
    public final JsonDeserializer<?> A0E(AbstractC0226Wn wn, AbstractC0224Wl wl, jm jmVar) throws C0223Wj {
        StringBuilder sb;
        String str;
        Class cls;
        AnonymousClass8M r5 = wn._config;
        Class<?> cls2 = wl._class;
        JsonDeserializer<?> A07 = A07(cls2, r5, jmVar);
        if (A07 == null) {
            Iterator<AnonymousClass7P> it = jmVar.A0J().iterator();
            while (true) {
                if (!it.hasNext()) {
                    A07 = new EnumDeserializer(A09(cls2, r5, jmVar.A08()));
                    break;
                }
                AnonymousClass7P next = it.next();
                if (wn._config.A01().A0c(next)) {
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
                            if (r5.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                Mv.A05(method);
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
        dT dTVar = this._factoryConfig;
        if (dTVar.A00()) {
            Iterator it2 = new N4(dTVar._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return A07;
    }

    @Override // X.ZD
    public final JsonDeserializer<?> A0F(AbstractC0226Wn wn, AnonymousClass78 r12, jm jmVar) throws C0223Wj {
        JsonDeserializer<?> objectArrayDeserializer;
        AnonymousClass8M r6 = wn._config;
        AbstractC0224Wl A03 = r12.A03();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        V4 v4 = (V4) A03._typeHandler;
        if (v4 == null) {
            v4 = A0L(r6, A03);
        }
        Iterator it = new N4(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                objectArrayDeserializer = ((Z6) it.next()).A22(r12, r6, jmVar, v4, jsonDeserializer);
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
                objectArrayDeserializer = new ObjectArrayDeserializer(r12, jsonDeserializer, v4);
            }
        }
        dT dTVar = this._factoryConfig;
        if (dTVar.A00()) {
            Iterator it2 = new N4(dTVar._modifiers).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return objectArrayDeserializer;
    }

    @Override // X.ZD
    public final JsonDeserializer<?> A0H(AbstractC0226Wn wn, AnonymousClass2W r19, jm jmVar) throws C0223Wj {
        JsonDeserializer<?> collectionDeserializer;
        AbstractC0224Wl A07;
        jm jmVar2 = jmVar;
        AnonymousClass2W r6 = r19;
        AbstractC0224Wl A03 = r6.A03();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) A03._valueHandler;
        AnonymousClass8M r7 = wn._config;
        V4 v4 = (V4) A03._typeHandler;
        if (v4 == null) {
            v4 = A0L(r7, A03);
        }
        Iterator it = new N4(this._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                collectionDeserializer = ((Z6) it.next()).A24(r6, r7, jmVar2, v4, jsonDeserializer);
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
                        jmVar2 = r7._base._classIntrospector.A01(r7, A07, r7);
                        r6 = A07;
                    }
                    AbstractC0262Ym A0N = A0N(wn, jmVar2);
                    if (!A0N.A0K() && r6._class == ArrayBlockingQueue.class) {
                        return new ArrayBlockingQueueDeserializer(r6, jsonDeserializer, v4, A0N, null);
                    }
                    if (A03._class == String.class) {
                        collectionDeserializer = new StringCollectionDeserializer(r6, A0N, null, jsonDeserializer);
                    } else {
                        collectionDeserializer = new CollectionDeserializer(r6, jsonDeserializer, v4, A0N, null);
                    }
                } else {
                    collectionDeserializer = new EnumSetDeserializer(A03, null);
                }
            }
        }
        dT dTVar = this._factoryConfig;
        if (dTVar.A00()) {
            Iterator it2 = new N4(dTVar._modifiers).iterator();
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
    @Override // X.ZD
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A0J(X.AbstractC0226Wn r20, X.AnonymousClass2V r21, X.jm r22) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 245
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WW.A0J(X.Wn, X.2V, X.jm):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.ZD
    public final AbstractC0420hV A0K(AbstractC0226Wn wn, AbstractC0224Wl wl) throws C0223Wj {
        AbstractC0420hV cm;
        StringBuilder sb;
        String str;
        AnonymousClass8M r4 = wn._config;
        if (this._factoryConfig._additionalKeyDeserializers.length > 0) {
            jm A02 = r4.A02(r4.A03(wl._class));
            Iterator it = new N4(this._factoryConfig._additionalKeyDeserializers).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                cm = ((AbstractC0265Yp) it.next()).A27(wl, r4, A02);
                if (cm != null) {
                    break;
                }
            }
        }
        if (wl._class.isEnum()) {
            AnonymousClass8M r5 = wn._config;
            jm A022 = r5._base._classIntrospector.A02(r5, wl, r5);
            JsonDeserializer<Object> A06 = A06(wn, A022.A05());
            if (A06 == null) {
                Class<?> cls = wl._class;
                if (A07(cls, r5, A022) == null) {
                    M9<?> A09 = A09(cls, r5, A022.A08());
                    for (AnonymousClass7P r3 : A022.A0J()) {
                        if (r5.A01().A0c(r3)) {
                            if (r3.A0W() == 1) {
                                Method method = r3.A00;
                                if (method.getReturnType().isAssignableFrom(cls)) {
                                    if (r3.A0U(0) == String.class) {
                                        if (r5.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                                            Mv.A05(method);
                                        }
                                        return new C0068Cb(A09, r3);
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
                    return new C0068Cb(A09, null);
                }
            }
            return new WO(wl._class, A06);
        }
        jm A023 = r4._base._classIntrospector.A02(r4, wl, r4);
        Constructor<?> A0F = A023.A0F(String.class);
        if (A0F != null) {
            if (r4.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                Mv.A05(A0F);
            }
            cm = new CN(A0F);
        } else {
            Method A0G = A023.A0G(String.class);
            if (A0G == null) {
                return null;
            }
            if (r4.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                Mv.A05(A0G);
            }
            cm = new CM(A0G);
        }
        dT dTVar = this._factoryConfig;
        if (!dTVar.A00()) {
            return cm;
        }
        Iterator it2 = new N4(dTVar._modifiers).iterator();
        while (it2.hasNext()) {
            it2.next();
        }
        return cm;
    }

    @Override // X.ZD
    public final V4 A0L(AnonymousClass8M r6, AbstractC0224Wl wl) throws C0223Wj {
        AbstractC0224Wl A0C;
        Class<?> cls;
        WK A05 = r6.A02(r6.A03(wl._class)).A05();
        Wp A012 = r6.A01();
        V2<?> A0B = A012.A0B(r6, A05, wl);
        Collection<V7> collection = null;
        if (A0B == null) {
            A0B = r6._base._typeResolverBuilder;
            if (A0B == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(A05, r6, A012);
        }
        if (A0B.A2Q() == null && wl.A0E() && (A0C = A0C(r6, wl)) != null && (cls = A0C._class) != wl._class) {
            A0B.A1n(cls);
        }
        return A0B.A1W(r6, wl, collection);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r2 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x024b, code lost:
        if (r5 == r7) goto L_0x024d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC0262Ym A0N(X.AbstractC0226Wn r30, X.jm r31) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 1102
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WW.A0N(X.Wn, X.jm):X.Ym");
    }

    public WW(dT dTVar) {
        this._factoryConfig = dTVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        if (r8 != null) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC0224Wl A0M(X.AbstractC0226Wn r6, X.AbstractC0224Wl r7, X.WJ r8) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 131
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WW.A0M(X.Wn, X.Wl, X.WJ):X.Wl");
    }
}
