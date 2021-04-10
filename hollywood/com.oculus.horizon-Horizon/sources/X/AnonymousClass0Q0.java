package X;

/* renamed from: X.0Q0  reason: invalid class name */
public class AnonymousClass0Q0 extends ThreadLocal<AnonymousClass0QF> {
    public final /* synthetic */ AnonymousClass04R A00;

    public AnonymousClass0Q0(AnonymousClass04R r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final AnonymousClass0QF initialValue() {
        return new AnonymousClass0QF(this.A00.A04);
    }
}
