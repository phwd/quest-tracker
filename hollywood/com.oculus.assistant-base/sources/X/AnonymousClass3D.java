package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.3D  reason: invalid class name */
public final class AnonymousClass3D implements AnonymousClass2z, AbstractC1198uz {
    public static final b7 A01 = new b7();
    public static final HashMap A02;
    public static final HashMap A03;
    public static final HashMap A04;
    public static final Map A05;
    public static final Map A06;
    public final Class A00;

    static {
        Map map;
        String str;
        int i = 0;
        List asList = Arrays.asList(AbstractC0496aj.class, AbstractC0507au.class, AbstractC0511ay.class, AbstractC0512az.class, b0.class, b1.class, b2.class, b3.class, b4.class, b5.class, AbstractC0497ak.class, AbstractC0498al.class, AbstractC0499am.class, AbstractC0500an.class, AbstractC0501ao.class, AbstractC0502ap.class, AbstractC0503aq.class, AbstractC0504ar.class, AbstractC0505as.class, AbstractC0506at.class, AbstractC0508av.class, AbstractC0509aw.class, AbstractC0510ax.class);
        C0514bB.A01(asList, "ArraysUtilJVM.asList(this)");
        int i2 = 10;
        C0514bB.A02(asList, "$this$collectionSizeOrDefault");
        if (asList instanceof Collection) {
            i2 = asList.size();
        }
        ArrayList arrayList = new ArrayList(i2);
        for (Object obj : asList) {
            int i3 = i + 1;
            if (i < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            arrayList.add(new C0467aC(obj, Integer.valueOf(i)));
            i = i3;
        }
        if (arrayList instanceof Collection) {
            int size = arrayList.size();
            if (size != 0) {
                if (size != 1) {
                    map = new LinkedHashMap(AnonymousClass2a.A00(arrayList.size()));
                    AnonymousClass3E.A01(arrayList, map);
                } else {
                    C0467aC aCVar = (C0467aC) arrayList.get(0);
                    C0514bB.A02(aCVar, "pair");
                    map = Collections.singletonMap(aCVar.first, aCVar.second);
                    str = "java.util.Collections.si…(pair.first, pair.second)";
                    C0514bB.A01(map, str);
                }
            }
            map = C0486aW.A00;
        } else {
            map = new LinkedHashMap();
            AnonymousClass3E.A01(arrayList, map);
            int size2 = map.size();
            if (size2 != 0) {
                if (size2 == 1) {
                    Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
                    map = Collections.singletonMap(entry.getKey(), entry.getValue());
                    str = "with(entries.iterator().…ingletonMap(key, value) }";
                    C0514bB.A01(map, str);
                }
            }
            map = C0486aW.A00;
        }
        A05 = map;
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        A03 = hashMap;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        A04 = hashMap2;
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(A03);
        hashMap3.putAll(A04);
        Collection<String> values = A03.values();
        C0514bB.A01(values, "primitiveFqNames.values");
        for (String str2 : values) {
            C0514bB.A01(str2, "kotlinName");
            C0467aC aCVar2 = new C0467aC(AnonymousClass08.A05("kotlin.jvm.internal.", AnonymousClass0o.A01(str2), "CompanionObject"), AnonymousClass08.A04(str2, ".Companion"));
            hashMap3.put(aCVar2.first, aCVar2.second);
        }
        for (Map.Entry entry2 : A05.entrySet()) {
            hashMap3.put(((Class) entry2.getKey()).getName(), AnonymousClass08.A00("kotlin.Function", ((Number) entry2.getValue()).intValue()));
        }
        A02 = hashMap3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(AnonymousClass2a.A00(hashMap3.size()));
        for (Map.Entry entry3 : hashMap3.entrySet()) {
            linkedHashMap.put(entry3.getKey(), AnonymousClass0o.A01((String) entry3.getValue()));
        }
        A06 = linkedHashMap;
    }

    public AnonymousClass3D(Class cls) {
        C0514bB.A02(cls, "jClass");
        this.A00 = cls;
    }

    public static final Class A00(AbstractC1198uz uzVar) {
        C0514bB.A02(uzVar, "$this$javaObjectType");
        Class A2X = ((AnonymousClass2z) uzVar).A2X();
        if (A2X.isPrimitive()) {
            String name = A2X.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return A2X;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass3D) || !C0514bB.A05(A00(this), A00((AbstractC1198uz) obj))) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass2z
    public final Class A2X() {
        return this.A00;
    }

    public final int hashCode() {
        return A00(this).hashCode();
    }

    public final String toString() {
        return AnonymousClass08.A04(A2X().toString(), " (Kotlin reflection is not available)");
    }
}
