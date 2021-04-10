package X;

import java.io.File;

/* renamed from: X.1oB  reason: invalid class name */
public class AnonymousClass1oB implements AnonymousClass1oK {
    public boolean A00;
    public final /* synthetic */ AnonymousClass1oA A01;

    public AnonymousClass1oB(AnonymousClass1oA r1) {
        this.A01 = r1;
    }

    @Override // X.AnonymousClass1oK
    public final void A7O(File file) {
        AnonymousClass1oA r1 = this.A01;
        if (!r1.A01.equals(file) && !this.A00) {
            file.delete();
        }
        if (this.A00 && file.equals(r1.A02)) {
            this.A00 = false;
        }
    }

    @Override // X.AnonymousClass1oK
    public final void A7P(File file) {
        if (!this.A00 && file.equals(this.A01.A02)) {
            this.A00 = true;
        }
    }

    @Override // X.AnonymousClass1oK
    public final void A9u(File file) {
        if (this.A00) {
            AnonymousClass1oA r3 = this.A01;
            C09561oF A002 = AnonymousClass1oA.A00(r3, file);
            boolean z = false;
            if (A002 != null) {
                String str = A002.A01;
                if (str != ".tmp") {
                    if (str == ".cnt") {
                        z = true;
                    }
                    AnonymousClass0KU.A03(z);
                    return;
                } else if (file.lastModified() > r3.A00.now() - AnonymousClass1oA.A05) {
                    return;
                }
            }
        }
        file.delete();
    }
}
