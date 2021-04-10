package X;

public class Ob extends ThreadLocal<Op> {
    public final /* synthetic */ AnonymousClass3G A00;

    public Ob(AnonymousClass3G r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final Op initialValue() {
        return new Op(this.A00.A04);
    }
}
