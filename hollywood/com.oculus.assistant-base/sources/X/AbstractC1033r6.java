package X;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* renamed from: X.r6  reason: case insensitive filesystem */
public abstract class AbstractC1033r6 extends AbstractC0266Of implements Serializable {
    public static final HashMap A00;
    public static final HashMap A01;
    public final OU _factoryConfig;

    static {
        HashMap hashMap = new HashMap();
        A01 = hashMap;
        hashMap.put(Map.class.getName(), LinkedHashMap.class);
        HashMap hashMap2 = A01;
        hashMap2.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        hashMap2.put(SortedMap.class.getName(), TreeMap.class);
        hashMap2.put("java.util.NavigableMap", TreeMap.class);
        try {
            A01.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            StringBuilder sb = new StringBuilder("Problems with (optional) types: ");
            sb.append(th);
            printStream.println(sb.toString());
        }
        HashMap hashMap3 = new HashMap();
        A00 = hashMap3;
        hashMap3.put(Collection.class.getName(), ArrayList.class);
        HashMap hashMap4 = A00;
        hashMap4.put(List.class.getName(), ArrayList.class);
        hashMap4.put(Set.class.getName(), HashSet.class);
        hashMap4.put(SortedSet.class.getName(), TreeSet.class);
        hashMap4.put(Queue.class.getName(), LinkedList.class);
        hashMap4.put("java.util.Deque", LinkedList.class);
        hashMap4.put("java.util.NavigableSet", TreeSet.class);
    }

    public static final AbstractC1024qt A05(AbstractC1022qr qrVar, P9 p9, AbstractC1024qt qtVar) {
        Class cls;
        Class cls2;
        JsonDeserializer A09;
        JsonDeserialize jsonDeserialize;
        Class contentAs;
        OD A0E;
        JsonDeserialize jsonDeserialize2;
        AbstractC1020qp A012 = qrVar._config.A01();
        boolean z = A012 instanceof Rw;
        if (!z || (jsonDeserialize2 = (JsonDeserialize) p9.A0L(JsonDeserialize.class)) == null || (cls = jsonDeserialize2.as()) == OR.class) {
            cls = null;
        }
        if (cls != null) {
            try {
                qtVar = qtVar.A09(cls);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder("Failed to narrow type ");
                sb.append(qtVar);
                sb.append(" with concrete-type annotation (value ");
                sb.append(cls.getName());
                sb.append("), method '");
                sb.append(p9.A0K());
                sb.append("': ");
                sb.append(e.getMessage());
                throw new C1025qv(sb.toString(), null, e);
            }
        }
        if (!qtVar.A0H()) {
            return qtVar;
        }
        if (!z) {
            cls2 = null;
        } else {
            JsonDeserialize jsonDeserialize3 = (JsonDeserialize) p9.A0L(JsonDeserialize.class);
            if (jsonDeserialize3 == null || (cls2 = jsonDeserialize3.keyAs()) == OR.class) {
                cls2 = null;
            }
        }
        if (cls2 != null) {
            if (qtVar instanceof C0681fG) {
                try {
                    qtVar = ((C0681fG) qtVar).A0J(cls2);
                } catch (IllegalArgumentException e2) {
                    StringBuilder sb2 = new StringBuilder("Failed to narrow key type ");
                    sb2.append(qtVar);
                    sb2.append(" with key-type annotation (");
                    sb2.append(cls2.getName());
                    sb2.append("): ");
                    sb2.append(e2.getMessage());
                    throw new C1025qv(sb2.toString(), null, e2);
                }
            } else {
                StringBuilder sb3 = new StringBuilder("Illegal key-type annotation: type ");
                sb3.append(qtVar);
                sb3.append(" is not a Map(-like) type");
                throw new C1025qv(sb3.toString());
            }
        }
        AbstractC1024qt A05 = qtVar.A05();
        if (!(A05 == null || A05._valueHandler != null || (A0E = qrVar.A0E(p9, A012.A0E(p9))) == null)) {
            qtVar = ((C0681fG) qtVar).A0K(A0E);
        }
        if (!(!z || (jsonDeserialize = (JsonDeserialize) p9.A0L(JsonDeserialize.class)) == null || (contentAs = jsonDeserialize.contentAs()) == OR.class || contentAs == null)) {
            try {
                qtVar = qtVar.A08(contentAs);
            } catch (IllegalArgumentException e3) {
                StringBuilder sb4 = new StringBuilder("Failed to narrow content type ");
                sb4.append(qtVar);
                sb4.append(" with content-type annotation (");
                sb4.append(contentAs.getName());
                sb4.append("): ");
                sb4.append(e3.getMessage());
                throw new C1025qv(sb4.toString(), null, e3);
            }
        }
        if (qtVar.A04()._valueHandler != null || (A09 = qrVar.A09(p9, A012.A0B(p9))) == null) {
            return qtVar;
        }
        return qtVar.A0C(A09);
    }

    public static final JsonDeserializer A06(AbstractC1022qr qrVar, P9 p9) {
        JsonDeserialize jsonDeserialize;
        Class using;
        if (!(qrVar._config.A01() instanceof Rw) || (jsonDeserialize = (JsonDeserialize) p9.A0L(JsonDeserialize.class)) == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class || using == null) {
            return null;
        }
        return qrVar.A09(p9, using);
    }

    private final C0372Ui A07(AbstractC1022qr qrVar, O4 o4, String str, int i, Ss ss, Object obj) {
        boolean z;
        JsonProperty jsonProperty;
        Boolean valueOf;
        AnonymousClass2I r2 = qrVar._config;
        AbstractC1020qp A012 = r2.A01();
        if (A012 == null || !(A012 instanceof Rw) || (jsonProperty = (JsonProperty) ss.A0L(JsonProperty.class)) == null || (valueOf = Boolean.valueOf(jsonProperty.required())) == null) {
            z = false;
        } else {
            z = valueOf.booleanValue();
        }
        AbstractC1024qt A09 = r2._base._typeFactory.A09(ss._type, o4.A05());
        C1021qq qqVar = new C1021qq(str, A09, null, o4.A06(), ss, z);
        AbstractC1024qt A0B = A0B(qrVar, A09, ss);
        if (A0B != A09) {
            qqVar = new C1021qq(qqVar.A03, A0B, qqVar.A00, qqVar.A02, qqVar.A01, qqVar.A04);
        }
        JsonDeserializer A06 = A06(qrVar, ss);
        AbstractC1024qt A05 = A05(qrVar, ss, A0B);
        PR pr = (PR) A05._typeHandler;
        if (pr == null) {
            pr = A0A(r2, A05);
        }
        C0372Ui ui = new C0372Ui(str, A05, qqVar.A00, pr, o4.A06(), ss, i, obj, qqVar.A04);
        if (A06 != null) {
            return new C0372Ui(ui, A06);
        }
        return ui;
    }

    public static final Q8 A08(Class cls, AnonymousClass2I r7, AnonymousClass0q r8) {
        Enum[] enumArr;
        HashMap hashMap;
        if (r8 != null) {
            Method method = r8.A00;
            if (r7.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                Q5.A06(method);
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
            if ((EnumC1023qs.READ_ENUMS_USING_TO_STRING.getMask() & r7._deserFeatures) != 0) {
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
                    throw new IllegalArgumentException(AnonymousClass08.A04("No enum constants for class ", cls.getName()));
                }
            }
        }
        return new Q8(cls, enumArr, hashMap);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:9:0x002b */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [X.Ok] */
    /* JADX WARN: Type inference failed for: r1v6, types: [X.rH] */
    /* JADX WARN: Type inference failed for: r1v35, types: [X.rC] */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x026c, code lost:
        if (r6 == r3) goto L_0x026e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if (r1 == 0) goto L_0x002d;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.Ok A0C(X.AbstractC1022qr r31, X.O4 r32) {
        /*
        // Method dump skipped, instructions count: 1136
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1033r6.A0C(X.qr, X.O4):X.Ok");
    }

    public AbstractC1033r6(OU ou) {
        this._factoryConfig = ou;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        if (r8 != null) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC1024qt A0B(X.AbstractC1022qr r6, X.AbstractC1024qt r7, X.AbstractC1044rJ r8) {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1033r6.A0B(X.qr, X.qt, X.rJ):X.qt");
    }
}
