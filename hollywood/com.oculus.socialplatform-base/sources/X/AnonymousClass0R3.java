package X;

/* renamed from: X.0R3  reason: invalid class name */
public class AnonymousClass0R3 extends ThreadLocal<AnonymousClass0RH> {
    public final /* synthetic */ C002105e A00;

    public AnonymousClass0R3(C002105e r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final AnonymousClass0RH initialValue() {
        return new AnonymousClass0RH(this.A00.A04);
    }
}
