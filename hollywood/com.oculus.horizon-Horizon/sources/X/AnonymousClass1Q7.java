package X;

/* renamed from: X.1Q7  reason: invalid class name */
public final class AnonymousClass1Q7 implements AnonymousClass1kO {
    public final /* synthetic */ AnonymousClass1Q8 A00;

    @Override // X.AnonymousClass1kO
    public final void A6d(float f) {
    }

    @Override // X.AnonymousClass1kO
    public final void onStart() {
    }

    public AnonymousClass1Q7(AnonymousClass1Q8 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1kO
    public final void A5u(AnonymousClass1aJ r5) {
        AnonymousClass1Q8 r3 = this.A00;
        AnonymousClass1QP r2 = r3.A01.get(this);
        if (r2 != null) {
            AnonymousClass1QP r1 = r2;
            r1.A02 = r5;
            r1.A00 = null;
            synchronized (r2) {
                r2.notify();
            }
            r3.A01.remove(this);
        }
    }

    @Override // X.AnonymousClass1kO
    public final void A6A(AnonymousClass1QC r5) {
        AnonymousClass1Q8 r3 = this.A00;
        AnonymousClass1QP r2 = r3.A01.get(this);
        if (r2 != null) {
            AnonymousClass1QP r1 = r2;
            r1.A02 = null;
            r1.A00 = r5;
            synchronized (r2) {
                r2.notify();
            }
            r3.A01.remove(this);
        }
    }
}
