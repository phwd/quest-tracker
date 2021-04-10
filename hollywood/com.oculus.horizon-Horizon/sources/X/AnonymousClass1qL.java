package X;

/* renamed from: X.1qL  reason: invalid class name */
public class AnonymousClass1qL extends AnonymousClass1qN {
    public final /* synthetic */ AnonymousClass1q3 A00;

    @Override // X.AnonymousClass1qN
    public final synchronized boolean A08(AnonymousClass1qQ r3, int i) {
        boolean z;
        if ((i & 1) == 1) {
            z = super.A08(r3, i);
        } else {
            z = false;
        }
        return z;
    }

    /* JADX WARN: Incorrect types in method signature: (Lcom/facebook/imagepipeline/producers/Consumer<LX/1qa<LX/1q1;>;>;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1qL(AnonymousClass1q3 r1, AnonymousClass1qD r2, AnonymousClass1qU r3, int i) {
        super(r1, r2, r3, i);
        this.A00 = r1;
    }
}
