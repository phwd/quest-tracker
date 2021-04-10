package X;

/* renamed from: X.1l7  reason: invalid class name */
public class AnonymousClass1l7 implements AbstractC00750Ik<AnonymousClass0M8<IMAGE>> {
    public final /* synthetic */ AbstractC09911kL A00;
    public final /* synthetic */ AnonymousClass1m0 A01;
    public final /* synthetic */ Integer A02;
    public final /* synthetic */ Object A03;
    public final /* synthetic */ Object A04;
    public final /* synthetic */ String A05;

    public AnonymousClass1l7(AbstractC09911kL r1, AnonymousClass1m0 r2, String str, Object obj, Object obj2, Integer num) {
        this.A00 = r1;
        this.A01 = r2;
        this.A05 = str;
        this.A04 = obj;
        this.A03 = obj2;
        this.A02 = num;
    }

    @Override // X.AbstractC00750Ik
    public final Object get() {
        AnonymousClass1l4 r6;
        C09621iy r7;
        String str;
        AbstractC09911kL r1 = this.A00;
        AnonymousClass1m0 r2 = this.A01;
        String str2 = this.A05;
        Object obj = this.A04;
        Object obj2 = this.A03;
        Integer num = this.A02;
        AnonymousClass1kA r4 = (AnonymousClass1kA) obj;
        AnonymousClass1k6 r3 = ((AnonymousClass1k8) r1).A01;
        int intValue = num.intValue();
        switch (intValue) {
            case 0:
                r6 = AnonymousClass1l4.FULL_FETCH;
                break;
            case 1:
                r6 = AnonymousClass1l4.DISK_CACHE;
                break;
            case 2:
                r6 = AnonymousClass1l4.BITMAP_MEMORY_CACHE;
                break;
            default:
                switch (intValue) {
                    case 1:
                        str = "DISK_CACHE";
                        break;
                    case 2:
                        str = "BITMAP_MEMORY_CACHE";
                        break;
                    default:
                        str = "FULL_FETCH";
                        break;
                }
                throw new RuntimeException(AnonymousClass006.A09("Cache level", str, "is not supported. "));
        }
        if (r2 instanceof AnonymousClass1k9) {
            AnonymousClass1k9 r22 = (AnonymousClass1k9) r2;
            synchronized (r22) {
                r7 = null;
                AnonymousClass1j6 r12 = r22.A03;
                if (r12 != null) {
                    r7 = new C09621iy(((AbstractC09891kF) r22).A07, r12);
                }
            }
        } else {
            r7 = null;
        }
        return r3.A00(r4, obj2, r6, r7, str2);
    }

    public final String toString() {
        C00720Ig A002 = C00730Ih.A00(this);
        C00720Ig.A00(A002, "request", this.A04.toString());
        return A002.toString();
    }
}
