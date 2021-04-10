package X;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Km  reason: case insensitive filesystem */
public class C0071Km extends hh<AbstractC0241hm> {
    public static final void A01(C0071Km km, h3 h3Var, AbstractC0241hm hmVar) throws IOException {
        String str;
        if (hmVar == null || (hmVar instanceof M3)) {
            h3Var.A09();
        } else if (hmVar instanceof M1) {
            M1 m1 = (M1) hmVar;
            Object obj = m1.A00;
            if (obj instanceof Number) {
                h3Var.A0B(m1.A02());
            } else if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                h3.A03(h3Var);
                h3.A02(h3Var);
                Writer writer = h3Var.A07;
                if (booleanValue) {
                    str = "true";
                } else {
                    str = "false";
                }
                writer.write(str);
            } else {
                h3Var.A0D(m1.A03());
            }
        } else if (hmVar instanceof M5) {
            h3Var.A05();
            Iterator<AbstractC0241hm> it = ((M5) hmVar).iterator();
            while (it.hasNext()) {
                A01(km, h3Var, it.next());
            }
            h3Var.A07();
        } else if (hmVar instanceof M2) {
            h3Var.A06();
            for (Map.Entry<String, AbstractC0241hm> entry : ((M2) hmVar).A00.entrySet()) {
                h3Var.A0C(entry.getKey());
                A01(km, h3Var, entry.getValue());
            }
            h3Var.A08();
        } else {
            StringBuilder sb = new StringBuilder("Couldn't write ");
            sb.append(hmVar.getClass());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static final AbstractC0241hm A00(C0071Km km, h5 h5Var) throws IOException {
        switch (h5Var.A0G().intValue()) {
            case 0:
                M5 m5 = new M5();
                h5Var.A0L();
                while (h5Var.A0R()) {
                    m5.A00.add(A00(km, h5Var));
                }
                h5Var.A0N();
                return m5;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException();
            case 2:
                M2 m2 = new M2();
                h5Var.A0M();
                while (h5Var.A0R()) {
                    m2.A00.put(h5Var.A0I(), A00(km, h5Var));
                }
                h5Var.A0O();
                return m2;
            case 5:
                return new M1(h5Var.A0J());
            case 6:
                return new M1(new hS(h5Var.A0J()));
            case 7:
                return new M1(Boolean.valueOf(h5Var.A0S()));
            case 8:
                h5Var.A0P();
                return M3.A00;
        }
    }
}
