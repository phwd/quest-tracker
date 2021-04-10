package X;

import java.util.ArrayList;

public final class KA {
    public static final K9 A06 = new K9();
    public final K8 A00;
    public final String A01;
    public final String A02;
    public final int[] A03;
    public final Object[] A04;
    public final String[] A05;

    public KA(String str, String str2, String[] strArr, Object[] objArr, int[] iArr, K8 k8) {
        C0514bB.A02(str, "statementDebugName");
        C0514bB.A02(str2, "statementSql");
        C0514bB.A02(strArr, "affectedTables");
        C0514bB.A02(objArr, "procArgs");
        C0514bB.A02(iArr, "bindArgsIndices");
        C0514bB.A02(k8, "config");
        this.A01 = str;
        this.A02 = str2;
        this.A05 = strArr;
        this.A04 = objArr;
        this.A03 = iArr;
        this.A00 = k8;
    }

    public final Object A00(Integer num, AbstractC0496aj ajVar) {
        C0514bB.A02(num, "type");
        C0514bB.A02(ajVar, "op");
        return ((K5) this.A00.A00.getValue()).A00.A5h(num, this.A01, ajVar);
    }

    public final void A01() {
        int[] iArr = this.A03;
        int length = iArr.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i : iArr) {
            if (!(this.A04[i] instanceof K0)) {
                arrayList.add(C0482aR.A00);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(this.A01);
                sb.append(": argument ");
                sb.append(i);
                sb.append(" is not set (null or otherwise)");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        this.A00.A00.getValue();
    }
}
