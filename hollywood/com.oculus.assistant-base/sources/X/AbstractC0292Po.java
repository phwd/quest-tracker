package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.Po  reason: case insensitive filesystem */
public abstract class AbstractC0292Po {
    public final JsonSerializer A00(Class cls) {
        if (this instanceof C1064re) {
            C1064re reVar = (C1064re) this;
            if (cls == reVar.A01) {
                return reVar.A00;
            }
            return null;
        } else if (this instanceof C1063rd) {
            C0291Pn[] pnArr = ((C1063rd) this).A00;
            for (C0291Pn pn : pnArr) {
                if (pn.A01 == cls) {
                    return pn.A00;
                }
            }
            return null;
        } else if (this instanceof C1062rc) {
            return null;
        } else {
            C1061rb rbVar = (C1061rb) this;
            if (cls == rbVar.A02) {
                return rbVar.A00;
            }
            if (cls == rbVar.A03) {
                return rbVar.A01;
            }
            return null;
        }
    }

    public final AbstractC0292Po A01(Class cls, JsonSerializer jsonSerializer) {
        if (this instanceof C1064re) {
            C1064re reVar = (C1064re) this;
            return new C1061rb(reVar.A01, reVar.A00, cls, jsonSerializer);
        } else if (this instanceof C1063rd) {
            C1063rd rdVar = (C1063rd) this;
            C0291Pn[] pnArr = rdVar.A00;
            int length = pnArr.length;
            if (length == 8) {
                return rdVar;
            }
            C0291Pn[] pnArr2 = new C0291Pn[(length + 1)];
            System.arraycopy(pnArr, 0, pnArr2, 0, length);
            pnArr2[length] = new C0291Pn(cls, jsonSerializer);
            return new C1063rd(pnArr2);
        } else if (this instanceof C1062rc) {
            return new C1064re(cls, jsonSerializer);
        } else {
            C1061rb rbVar = (C1061rb) this;
            return new C1063rd(new C0291Pn[]{new C0291Pn(rbVar.A02, rbVar.A00), new C0291Pn(rbVar.A03, rbVar.A01)});
        }
    }
}
