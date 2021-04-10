package X;

import com.oculus.config.updater.ConfigUpdaterDumperPlugin;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/* renamed from: X.0lq  reason: invalid class name and case insensitive filesystem */
public final class C05780lq {
    public HashSet<String> A00;
    public LinkedHashMap<Object, AnonymousClass0g9> A01;
    public LinkedList<AnonymousClass0g9> A02;
    public LinkedList<AnonymousClass07O> A03;
    public LinkedList<AnonymousClass0GS> A04;
    public LinkedList<AnonymousClass07O> A05;
    public final AbstractC04040gi A06;
    public final AbstractC04000gb A07;
    public final AbstractC03910gQ<?> A08;
    public final C03810gA A09;
    public final AbstractC05820lu<?> A0A;
    public final String A0B;
    public final LinkedHashMap<String, AnonymousClass0GS> A0C = new LinkedHashMap<>();

    private final AnonymousClass0GS A00(String str) {
        LinkedHashMap<String, AnonymousClass0GS> linkedHashMap = this.A0C;
        AnonymousClass0GS r1 = linkedHashMap.get(str);
        if (r1 != null) {
            return r1;
        }
        AnonymousClass0GS r12 = new AnonymousClass0GS(str, this.A06);
        linkedHashMap.put(str, r12);
        return r12;
    }

    public static String A01(AnonymousClass07O r3, String str) {
        Class<?> A0J;
        if (!str.startsWith("is") || ((A0J = r3.A0J()) != Boolean.class && A0J != Boolean.TYPE)) {
            return null;
        }
        return A03(str.substring(2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        if (r1.startsWith(r0) != false) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String A02(X.AnonymousClass07O r3, java.lang.String r4) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05780lq.A02(X.07O, java.lang.String):java.lang.String");
    }

    private final void A04(Object obj, AnonymousClass0g9 r7) {
        if (obj != null) {
            LinkedHashMap<Object, AnonymousClass0g9> linkedHashMap = this.A01;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>();
                this.A01 = linkedHashMap;
            }
            if (linkedHashMap.put(obj, r7) != null) {
                throw new IllegalArgumentException(AnonymousClass006.A09("Duplicate injectable value with id '", String.valueOf(obj), "' (of type ", obj.getClass().getName(), ")"));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        if (r1.A0c(r10) == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x060f, code lost:
        if (r1 == null) goto L_0x0600;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A05() {
        /*
        // Method dump skipped, instructions count: 1747
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05780lq.A05():void");
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0gQ<*>;ZLX/0gb;LX/0gA;Ljava/lang/String;)V */
    public C05780lq(AbstractC03910gQ r3, AbstractC04000gb r4, C03810gA r5, String str) {
        AbstractC05820lu<?> A092;
        AbstractC04040gi r1 = null;
        this.A04 = null;
        this.A02 = null;
        this.A03 = null;
        this.A05 = null;
        this.A08 = r3;
        this.A07 = r4;
        this.A09 = r5;
        this.A0B = str == null ? ConfigUpdaterDumperPlugin.COMMAND_SET : str;
        r1 = r3.A05(EnumC03960gV.USE_ANNOTATIONS) ? r3.A01() : r1;
        this.A06 = r1;
        if (r1 == null) {
            A092 = this.A08.A04();
        } else {
            A092 = r1.A09(r5, this.A08.A04());
        }
        this.A0A = A092;
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
}
