package X;

/* renamed from: X.g7  reason: case insensitive filesystem */
public final class C0725g7 implements AbstractC00548e {
    public final /* synthetic */ C0740gP A00;

    public C0725g7(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC00548e
    public final void A4A() {
        C0740gP gPVar = this.A00;
        C0112Aq aq = gPVar.A0h;
        EnumC00528b r3 = EnumC00528b.LISTENING;
        aq.A01(new C0809hx(r3));
        for (C0741gQ gQVar : gPVar.A10) {
            gQVar.A00.logStateChanged(r3.toString());
        }
    }
}
