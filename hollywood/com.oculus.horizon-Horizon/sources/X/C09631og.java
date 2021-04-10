package X;

import com.facebook.cache.disk.DiskStorage;

/* renamed from: X.1og  reason: invalid class name and case insensitive filesystem */
public class C09631og implements AnonymousClass0IZ {
    public final /* synthetic */ C09621of A00;

    public C09631og(C09621of r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(DiskStorage.Entry entry, DiskStorage.Entry entry2) {
        long A002 = ((AnonymousClass1oE) entry).A00();
        long A003 = ((AnonymousClass1oE) entry2).A00();
        if (A002 < A003) {
            return -1;
        }
        if (A003 == A002) {
            return 0;
        }
        return 1;
    }
}
