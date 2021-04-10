package X;

/* renamed from: X.1xc  reason: invalid class name */
public class AnonymousClass1xc implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ C11061xY A01;

    public AnonymousClass1xc(C11061xY r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        C11061xY r1 = this.A01;
        r1.A02.A5R("recording_controller_error", "RecordingControllerImpl", (long) hashCode(), r1.A03.A01(), new C11081xd(th), "low", "Warmup");
        this.A00.A62(th);
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        this.A00.onSuccess();
    }
}
