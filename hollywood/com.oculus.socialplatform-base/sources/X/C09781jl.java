package X;

/* renamed from: X.1jl  reason: invalid class name and case insensitive filesystem */
public class C09781jl extends AbstractC09701ja {
    public final /* synthetic */ AnonymousClass1je A00;

    @Override // X.AbstractC09701ja
    public final synchronized boolean A09(AnonymousClass0PZ r3, int i) {
        boolean z;
        if ((i & 1) == 1) {
            z = super.A09(r3, i);
        } else {
            z = false;
        }
        return z;
    }

    /* JADX WARN: Incorrect types in method signature: (Lcom/facebook/imagepipeline/producers/Consumer<LX/0Ju<LX/0VM;>;>;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C09781jl(AnonymousClass1je r1, AbstractC10011kf r2, C10161kv r3, int i) {
        super(r1, r2, r3, i);
        this.A00 = r1;
    }
}
