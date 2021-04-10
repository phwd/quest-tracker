package X;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public final class PK {
    public HashSet A00;
    public LinkedHashMap A01;
    public LinkedList A02;
    public LinkedList A03;
    public LinkedList A04;
    public LinkedList A05;
    public final AbstractC1020qp A06;
    public final AbstractC1024qt A07;
    public final AbstractC1032r3 A08;
    public final C1043rI A09;
    public final PN A0A;
    public final String A0B;
    public final LinkedHashMap A0C = new LinkedHashMap();
    public final boolean A0D;

    private final C1052rR A00(String str) {
        LinkedHashMap linkedHashMap = this.A0C;
        C1052rR rRVar = (C1052rR) linkedHashMap.get(str);
        if (rRVar != null) {
            return rRVar;
        }
        C1052rR rRVar2 = new C1052rR(str, this.A06, this.A0D);
        linkedHashMap.put(str, rRVar2);
        return rRVar2;
    }

    public static String A01(AnonymousClass0q r3, String str) {
        Class A0J;
        if (!str.startsWith("is") || ((A0J = r3.A0J()) != Boolean.class && A0J != Boolean.TYPE)) {
            return null;
        }
        return A03(str.substring(2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        if (r1.startsWith(r0) != false) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String A02(X.AnonymousClass0q r3, java.lang.String r4) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PK.A02(X.0q, java.lang.String):java.lang.String");
    }

    public static final void A04(PK pk, String str) {
        StringBuilder sb = new StringBuilder("Problem with definition of ");
        sb.append(pk.A09);
        sb.append(": ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    private final void A05(Object obj, AbstractC1044rJ rJVar) {
        if (obj != null) {
            LinkedHashMap linkedHashMap = this.A01;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.A01 = linkedHashMap;
            }
            if (linkedHashMap.put(obj, rJVar) != null) {
                throw new IllegalArgumentException(AnonymousClass08.A07("Duplicate injectable value with id '", String.valueOf(obj), "' (of type ", obj.getClass().getName(), ")"));
            }
        }
    }

    public final AnonymousClass0q A06() {
        LinkedList linkedList = this.A05;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() <= 1) {
            return (AnonymousClass0q) linkedList.get(0);
        }
        StringBuilder sb = new StringBuilder("Multiple value properties defined (");
        sb.append(linkedList.get(0));
        sb.append(" vs ");
        sb.append(this.A05.get(1));
        sb.append(")");
        A04(this, sb.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        if (r1.A0O(r10) == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x04a2, code lost:
        if (r8.A0F() != false) goto L_0x04a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x04ee, code lost:
        if (r8.A0G() != false) goto L_0x04f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x0684, code lost:
        if (r1 == null) goto L_0x0675;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (r17.A08.A05(X.EnumC1027qy.ALLOW_FINAL_FIELDS_AS_MUTATORS) != false) goto L_0x0018;
     */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x04ab  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x04bc  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x04ca  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x04d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A07() {
        /*
        // Method dump skipped, instructions count: 1832
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PK.A07():void");
    }

    public PK(AbstractC1032r3 r3Var, boolean z, AbstractC1024qt qtVar, C1043rI rIVar, String str) {
        PN A042;
        JsonAutoDetect jsonAutoDetect;
        AbstractC1020qp qpVar = null;
        this.A04 = null;
        this.A02 = null;
        this.A03 = null;
        this.A05 = null;
        this.A08 = r3Var;
        this.A0D = z;
        this.A07 = qtVar;
        this.A09 = rIVar;
        this.A0B = str == null ? "set" : str;
        qpVar = r3Var.A05(EnumC1027qy.USE_ANNOTATIONS) ? r3Var.A01() : qpVar;
        this.A06 = qpVar;
        if (qpVar == null) {
            A042 = this.A08.A04();
        } else {
            A042 = this.A08.A04();
            if ((qpVar instanceof Rw) && (jsonAutoDetect = (JsonAutoDetect) rIVar.A0L(JsonAutoDetect.class)) != null) {
                A042 = A042.A5a(jsonAutoDetect);
            }
        }
        this.A0A = A042;
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
