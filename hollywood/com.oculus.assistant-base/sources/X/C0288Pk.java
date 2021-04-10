package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.Map;

/* renamed from: X.Pk  reason: case insensitive filesystem */
public final class C0288Pk {
    public final C0287Pj[] A00;

    public C0288Pk(Map map) {
        int size = map.size();
        int i = 8;
        while (i < (size > 64 ? size + (size >> 2) : size + size)) {
            i += i;
        }
        int i2 = i - 1;
        C0287Pj[] pjArr = new C0287Pj[i];
        for (Map.Entry entry : map.entrySet()) {
            C0283Pf pf = (C0283Pf) entry.getKey();
            int hashCode = pf.hashCode() & i2;
            pjArr[hashCode] = new C0287Pj(pjArr[hashCode], pf, (JsonSerializer) entry.getValue());
        }
        this.A00 = pjArr;
    }

    public final JsonSerializer A00(C0283Pf pf) {
        int hashCode = pf.hashCode();
        C0287Pj[] pjArr = this.A00;
        C0287Pj pj = pjArr[hashCode & (pjArr.length - 1)];
        if (pj == null) {
            return null;
        }
        while (!pf.equals(pj.A01)) {
            pj = pj.A02;
            if (pj == null) {
            }
        }
        return pj.A00;
        return null;
    }
}
