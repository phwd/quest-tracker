package X;

import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.1b8  reason: invalid class name and case insensitive filesystem */
public class C06851b8<Data> implements AbstractC07401c9<File, Data> {
    public final AbstractC06781b1<Data> A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<File, Data> A1o(@NonNull C07381c7 r3) {
        return new C06811b4(this.A00);
    }

    public C06851b8(AbstractC06781b1<Data> r1) {
        this.A00 = r1;
    }
}
