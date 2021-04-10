package X;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public final class VM {
    public HashSet<String> A00;
    public LinkedHashMap<Object, WJ> A01;
    public LinkedList<WJ> A02;
    public LinkedList<AnonymousClass7P> A03;
    public LinkedList<C7> A04;
    public LinkedList<AnonymousClass7P> A05;
    public final Wp A06;
    public final AbstractC0224Wl A07;
    public final WZ<?> A08;
    public final WK A09;
    public final VI<?> A0A;
    public final String A0B;
    public final LinkedHashMap<String, C7> A0C = new LinkedHashMap<>();

    private final C7 A00(String str) {
        LinkedHashMap<String, C7> linkedHashMap = this.A0C;
        C7 c7 = linkedHashMap.get(str);
        if (c7 != null) {
            return c7;
        }
        C7 c72 = new C7(str, this.A06);
        linkedHashMap.put(str, c72);
        return c72;
    }

    public static String A01(AnonymousClass7P r3, String str) {
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
    public static java.lang.String A02(X.AnonymousClass7P r3, java.lang.String r4) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VM.A02(X.7P, java.lang.String):java.lang.String");
    }

    private final void A04(Object obj, WJ wj) {
        if (obj != null) {
            LinkedHashMap<Object, WJ> linkedHashMap = this.A01;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>();
                this.A01 = linkedHashMap;
            }
            if (linkedHashMap.put(obj, wj) != null) {
                throw new IllegalArgumentException(AnonymousClass06.A07("Duplicate injectable value with id '", String.valueOf(obj), "' (of type ", obj.getClass().getName(), ")"));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        if (r1.A0d(r10) == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x05ff, code lost:
        if (r1 == null) goto L_0x05f0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A05() {
        /*
        // Method dump skipped, instructions count: 1731
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VM.A05():void");
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/WZ<*>;ZLX/Wl;LX/WK;Ljava/lang/String;)V */
    public VM(WZ wz, AbstractC0224Wl wl, WK wk, String str) {
        VI<?> A0A2;
        Wp wp = null;
        this.A04 = null;
        this.A02 = null;
        this.A03 = null;
        this.A05 = null;
        this.A08 = wz;
        this.A07 = wl;
        this.A09 = wk;
        this.A0B = str == null ? "set" : str;
        wp = wz.A05(EnumC0220Wf.USE_ANNOTATIONS) ? wz.A01() : wp;
        this.A06 = wp;
        if (wp == null) {
            A0A2 = this.A08.A04();
        } else {
            A0A2 = wp.A0A(wk, this.A08.A04());
        }
        this.A0A = A0A2;
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
