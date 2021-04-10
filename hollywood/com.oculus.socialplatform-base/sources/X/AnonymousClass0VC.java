package X;

/* renamed from: X.0VC  reason: invalid class name */
public final class AnonymousClass0VC<T> extends AnonymousClass0mO<T> {
    public final int A00;

    public static AnonymousClass0VC A00(int i, AnonymousClass0lg r2) {
        return new AnonymousClass0VC(i, r2);
    }

    @Override // X.AnonymousClass0mO
    public final T A01(AnonymousClass0lg r4) {
        try {
            return (T) AnonymousClass1TK.A00(this.A00, r4, null);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public AnonymousClass0VC(int i, AnonymousClass0lg r2) {
        super(r2);
        this.A00 = i;
    }
}
