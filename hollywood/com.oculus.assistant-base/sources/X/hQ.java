package X;

public final class hQ {
    public yZ A00;

    public final AnonymousClass6D A00(String str) {
        C0514bB.A02(str, "eventName");
        AnonymousClass6F r1 = AnonymousClass6F.A02;
        C0514bB.A01(r1, "EventConfig.DEFAULT");
        C0514bB.A02(str, "eventName");
        C0514bB.A02(r1, "config");
        yZ yZVar = this.A00;
        if (yZVar != null) {
            Integer num = r1.A00;
            boolean z = false;
            if (num == AnonymousClass09.A01) {
                z = true;
            }
            C00446t A01 = yZVar.A01("com.facebook.assistant", str, z);
            if (A01.A05()) {
                yZ yZVar2 = this.A00;
                C0514bB.A00(yZVar2);
                return new hP(yZVar2, A01);
            }
        }
        return hR.A00;
    }
}
