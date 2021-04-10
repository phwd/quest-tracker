package X;

import com.facebook.cache.disk.DiskStorage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1oC  reason: invalid class name */
public class AnonymousClass1oC implements AnonymousClass1oK {
    public final List<DiskStorage.Entry> A00 = new ArrayList();
    public final /* synthetic */ AnonymousClass1oA A01;

    @Override // X.AnonymousClass1oK
    public final void A7O(File file) {
    }

    @Override // X.AnonymousClass1oK
    public final void A7P(File file) {
    }

    public AnonymousClass1oC(AnonymousClass1oA r2) {
        this.A01 = r2;
    }

    @Override // X.AnonymousClass1oK
    public final void A9u(File file) {
        C09561oF A002 = AnonymousClass1oA.A00(this.A01, file);
        if (A002 != null && A002.A01 == ".cnt") {
            this.A00.add(new AnonymousClass1oE(A002.A00, file));
        }
    }
}
