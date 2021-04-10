package X;

import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

@VisibleForTesting
public class IE implements Z9 {
    public final Z9 A00;
    public final GX A01;

    @Override // X.Z9
    public final boolean A2z() {
        return this.A00.A2z();
    }

    @Override // X.ST
    public final boolean A3G() {
        return this.A00.A3G();
    }

    @Override // X.ST
    public final void A3R() {
        File file;
        boolean equals;
        this.A00.A3R();
        GX gx = this.A01;
        String valueOf = String.valueOf(Fj.A00());
        String valueOf2 = String.valueOf(Fj.A01());
        ArrayList<ZA> arrayList = gx.A00;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ZA za = arrayList.get(i);
            if (za instanceof C00268q) {
                file = ((Fo) za).A00;
                equals = file.getName().equals(valueOf2);
            } else {
                boolean z = za instanceof AnonymousClass8r;
                file = ((Fo) za).A00;
                if (z) {
                    equals = file.getName().equals(valueOf);
                }
                GY.A00(file);
            }
            if (equals) {
            }
            GY.A00(file);
        }
        arrayList.clear();
    }

    @Override // X.ST
    public final void A5v(Writer writer) throws IOException {
        this.A00.A5v(writer);
    }

    @Override // X.Z9
    public final void unlock() {
        this.A00.unlock();
    }

    public IE(Z9 z9, GX gx) {
        this.A00 = z9;
        this.A01 = gx;
    }
}
