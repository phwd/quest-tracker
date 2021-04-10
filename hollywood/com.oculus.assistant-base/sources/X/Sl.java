package X;

public final class Sl {
    public final C1107su A00 = new C1107su();

    public final void A00(Exception exc) {
        C1107su suVar = this.A00;
        RZ.A02(exc, "Exception must not be null");
        synchronized (suVar.A04) {
            if (!suVar.A02) {
                suVar.A02 = true;
                suVar.A00 = exc;
                suVar.A03.A00(suVar);
            }
        }
    }
}
