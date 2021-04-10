package X;

import java.util.LinkedList;

public abstract class PG {
    public final O4 A02(AnonymousClass2I r3, AbstractC1024qt qtVar, PF pf) {
        C1047rM rMVar = (C1047rM) this;
        C1046rL A00 = C1047rM.A00(qtVar);
        if (A00 == null) {
            return C1046rL.A00(C1047rM.A01(rMVar, r3, qtVar, pf, false));
        }
        return A00;
    }

    public final O4 A03(AnonymousClass2H r6, AbstractC1024qt qtVar, PF pf) {
        AbstractC1044rJ rJVar;
        C1047rM rMVar = (C1047rM) this;
        C1046rL A00 = C1047rM.A00(qtVar);
        if (A00 == null) {
            PK A01 = C1047rM.A01(rMVar, r6, qtVar, pf, true);
            A00 = new C1046rL(A01);
            A00.A02 = A01.A06();
            LinkedList linkedList = A01.A02;
            if (linkedList == null) {
                rJVar = null;
            } else if (linkedList.size() > 1) {
                StringBuilder sb = new StringBuilder("Multiple 'any-getters' defined (");
                sb.append(linkedList.get(0));
                sb.append(" vs ");
                sb.append(A01.A02.get(1));
                sb.append(")");
                PK.A04(A01, sb.toString());
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                rJVar = (AbstractC1044rJ) linkedList.getFirst();
            }
            A00.A00 = rJVar;
        }
        return A00;
    }
}
