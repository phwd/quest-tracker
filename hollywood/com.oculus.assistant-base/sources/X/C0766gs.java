package X;

import java.util.Collections;

/* renamed from: X.gs  reason: case insensitive filesystem */
public final class C0766gs implements AbstractC0105Aj {
    public final /* synthetic */ C00658s A00;

    public C0766gs(C00658s r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        h4 h4Var = (h4) ak.A2L();
        String str = h4Var.A00;
        C00658s r4 = this.A00;
        r4.A09 = h4Var.A01;
        String[] split = str.split(" ");
        int length = split.length;
        if (length > 0) {
            String lowerCase = split[length - 1].toLowerCase();
            r4.A0A = lowerCase;
            if (str.endsWith(" ")) {
                int lastIndexOf = r4.A0C.lastIndexOf(lowerCase);
                if (lastIndexOf >= 0) {
                    r4.A05 += Collections.frequency(r4.A0C, lowerCase);
                    int size = r4.A0C.size();
                    boolean[] zArr = r4.A0E;
                    int length2 = lastIndexOf - (size - zArr.length);
                    if (length2 >= 0 && r4.A0D && zArr[length2]) {
                        r4.A0D = false;
                        r4.A06++;
                    }
                }
                r4.A0C.clear();
            }
        }
    }
}
