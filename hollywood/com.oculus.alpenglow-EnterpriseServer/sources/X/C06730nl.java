package X;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/* renamed from: X.0nl  reason: invalid class name and case insensitive filesystem */
public final class C06730nl {
    public HashSet<String> A00;
    public LinkedHashMap<Object, AbstractC02450Zr> A01;
    public LinkedList<AbstractC02450Zr> A02;
    public LinkedList<AnonymousClass0EC> A03;
    public LinkedList<AnonymousClass0K7> A04;
    public LinkedList<AnonymousClass0EC> A05;
    public final AbstractC02590aM A06;
    public final AnonymousClass0aI A07;
    public final AnonymousClass0a7<?> A08;
    public final C02460Zs A09;
    public final AbstractC06760no<?> A0A;
    public final String A0B;
    public final LinkedHashMap<String, AnonymousClass0K7> A0C = new LinkedHashMap<>();
    public final boolean A0D;

    private final AnonymousClass0K7 A00(String str) {
        LinkedHashMap<String, AnonymousClass0K7> linkedHashMap = this.A0C;
        AnonymousClass0K7 r2 = linkedHashMap.get(str);
        if (r2 != null) {
            return r2;
        }
        AnonymousClass0K7 r22 = new AnonymousClass0K7(str, this.A06, this.A0D);
        linkedHashMap.put(str, r22);
        return r22;
    }

    public static String A01(AnonymousClass0EC r3, String str) {
        Class<?> A0K;
        if (!str.startsWith("is") || ((A0K = r3.A0K()) != Boolean.class && A0K != Boolean.TYPE)) {
            return null;
        }
        return A03(str.substring(2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        if (r1.startsWith(r0) != false) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String A02(X.AnonymousClass0EC r3, java.lang.String r4) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06730nl.A02(X.0EC, java.lang.String):java.lang.String");
    }

    public static final void A04(C06730nl r2, String str) {
        throw new IllegalArgumentException("Problem with definition of " + r2.A09 + ": " + str);
    }

    private final void A05(Object obj, AbstractC02450Zr r7) {
        if (obj != null) {
            LinkedHashMap<Object, AbstractC02450Zr> linkedHashMap = this.A01;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>();
                this.A01 = linkedHashMap;
            }
            if (linkedHashMap.put(obj, r7) != null) {
                throw new IllegalArgumentException(AnonymousClass006.A09("Duplicate injectable value with id '", String.valueOf(obj), "' (of type ", obj.getClass().getName(), ")"));
            }
        }
    }

    public final AnonymousClass0EC A06() {
        LinkedList<AnonymousClass0EC> linkedList = this.A05;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() <= 1) {
            return linkedList.get(0);
        }
        A04(this, "Multiple value properties defined (" + linkedList.get(0) + " vs " + this.A05.get(1) + ")");
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public C06730nl(AnonymousClass0a7<?> r3, boolean z, AnonymousClass0aI r5, C02460Zs r6, String str) {
        AbstractC06760no<?> A0C2;
        AbstractC02590aM r1 = null;
        this.A04 = null;
        this.A02 = null;
        this.A03 = null;
        this.A05 = null;
        this.A08 = r3;
        this.A0D = z;
        this.A07 = r5;
        this.A09 = r6;
        this.A0B = str == null ? "set" : str;
        r1 = r3.A05(EnumC02540aC.USE_ANNOTATIONS) ? r3.A01() : r1;
        this.A06 = r1;
        if (r1 == null) {
            A0C2 = this.A08.A04();
        } else {
            A0C2 = r1.A0C(r6, this.A08.A04());
        }
        this.A0A = A0C2;
    }

    public static String A03(String str) {
        int length = str.length();
        StringBuilder sb = null;
        if (length == 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            char lowerCase = Character.toLowerCase(charAt);
            if (charAt == lowerCase) {
                break;
            }
            if (sb == null) {
                sb = new StringBuilder(str);
            }
            sb.setCharAt(i, lowerCase);
        }
        if (sb != null) {
            return sb.toString();
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:202:0x031b, code lost:
        if (r8.A0L() != false) goto L_0x0336;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x031d, code lost:
        r11.remove();
        r6 = r8.A0E();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0324, code lost:
        if (r3 != false) goto L_0x02ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0326, code lost:
        r5 = r17.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0328, code lost:
        if (r5 != null) goto L_0x0331;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x032a, code lost:
        r5 = new java.util.HashSet<>();
        r17.A00 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0331, code lost:
        r5.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0336, code lost:
        if (r9 == null) goto L_0x033c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0338, code lost:
        r9 = r9.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x033c, code lost:
        r8.A01 = r9;
        r5 = r8.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0340, code lost:
        if (r5 == null) goto L_0x0346;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0342, code lost:
        r5 = r5.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0346, code lost:
        r8.A02 = r5;
        r5 = r8.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x034a, code lost:
        if (r5 == null) goto L_0x0350;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x034c, code lost:
        r5 = r5.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0350, code lost:
        r8.A03 = r5;
        r5 = r8.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0354, code lost:
        if (r5 == null) goto L_0x035a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0356, code lost:
        r5 = r5.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        if (r1.A0p(r10) == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x035a, code lost:
        r8.A00 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x035c, code lost:
        if (r3 != false) goto L_0x0376;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0362, code lost:
        if (r8.A0A() != null) goto L_0x0376;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0364, code lost:
        r6 = r8.A0E();
        r5 = r17.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x036a, code lost:
        if (r5 != null) goto L_0x0373;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x036c, code lost:
        r5 = new java.util.HashSet<>();
        r17.A00 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0373, code lost:
        r5.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0483, code lost:
        if (r8.A0I() != false) goto L_0x0485;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x04d7, code lost:
        if (r8.A0J() != false) goto L_0x04d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x0638, code lost:
        if (r1 == null) goto L_0x0629;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (r17.A08.A05(X.EnumC02540aC.ALLOW_FINAL_FIELDS_AS_MUTATORS) != false) goto L_0x0018;
     */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x0497  */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x04a5  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x04ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A07() {
        /*
        // Method dump skipped, instructions count: 1788
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06730nl.A07():void");
    }
}
