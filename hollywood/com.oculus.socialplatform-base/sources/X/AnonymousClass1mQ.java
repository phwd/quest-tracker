package X;

import com.facebook.cache.disk.DiskStorage;

/* renamed from: X.1mQ  reason: invalid class name */
public class AnonymousClass1mQ implements AnonymousClass0H8 {
    public final /* synthetic */ AnonymousClass1mP A00;

    public AnonymousClass1mQ(AnonymousClass1mP r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(DiskStorage.Entry entry, DiskStorage.Entry entry2) {
        long A002 = ((C10361lt) entry).A00();
        long A003 = ((C10361lt) entry2).A00();
        if (A002 < A003) {
            return -1;
        }
        if (A003 == A002) {
            return 0;
        }
        return 1;
    }
}
