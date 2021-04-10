package X;

public abstract class QC {
    public static final QC A00 = new C1073rp();

    public final String A00(String str) {
        if (this instanceof C1077rt) {
            C1077rt rtVar = (C1077rt) this;
            return rtVar.A00.A00(rtVar.A01.A00(str));
        } else if (this instanceof C1076rs) {
            return AnonymousClass08.A04(str, ((C1076rs) this).A00);
        } else {
            if (this instanceof C1075rr) {
                return AnonymousClass08.A04(((C1075rr) this).A00, str);
            }
            if (!(this instanceof C1074rq)) {
                return str;
            }
            C1074rq rqVar = (C1074rq) this;
            return AnonymousClass08.A05(rqVar.A00, str, rqVar.A01);
        }
    }
}
