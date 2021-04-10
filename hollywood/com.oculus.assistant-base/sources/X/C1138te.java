package X;

import java.util.List;

/* renamed from: X.te  reason: case insensitive filesystem */
public final class C1138te {
    public int A00;
    public final C0551bo A01;
    public final C1141th A02;
    public final c9 A03;
    public final AbstractC0571cA A04;
    public final int A05;
    public final List A06;

    public final C0554br A00(C0551bo boVar, c9 c9Var, AbstractC0571cA cAVar, C1141th thVar) {
        int i = this.A05;
        List list = this.A06;
        if (i < list.size()) {
            int i2 = this.A00 + 1;
            this.A00 = i2;
            AbstractC0571cA cAVar2 = this.A04;
            if (cAVar2 != null) {
                C0544bh bhVar = boVar.A03;
                String str = bhVar.A02;
                C0544bh bhVar2 = this.A02.A0D.A02.A09;
                if (!str.equals(bhVar2.A02) || bhVar.A00 != bhVar2.A00) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(list.get(i - 1));
                    sb.append(" must retain the same host and port");
                    throw new IllegalStateException(sb.toString());
                }
            }
            if (cAVar2 == null || i2 <= 1) {
                C1138te teVar = new C1138te(list, c9Var, cAVar, thVar, i + 1, boVar);
                AbstractC0545bi biVar = (AbstractC0545bi) list.get(i);
                C0554br A3L = biVar.A3L(teVar);
                if (cAVar != null && i + 1 < list.size() && teVar.A00 != 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(biVar);
                    sb2.append(" must call proceed() exactly once");
                    throw new IllegalStateException(sb2.toString());
                } else if (A3L != null) {
                    return A3L;
                } else {
                    StringBuilder sb3 = new StringBuilder("interceptor ");
                    sb3.append(biVar);
                    sb3.append(" returned null");
                    throw new NullPointerException(sb3.toString());
                }
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("network interceptor ");
                sb4.append(list.get(i - 1));
                sb4.append(" must call proceed() exactly once");
                throw new IllegalStateException(sb4.toString());
            }
        } else {
            throw new AssertionError();
        }
    }

    public C1138te(List list, c9 c9Var, AbstractC0571cA cAVar, C1141th thVar, int i, C0551bo boVar) {
        this.A06 = list;
        this.A02 = thVar;
        this.A03 = c9Var;
        this.A04 = cAVar;
        this.A05 = i;
        this.A01 = boVar;
    }
}
