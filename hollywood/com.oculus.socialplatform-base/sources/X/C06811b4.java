package X;

import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.1b4  reason: invalid class name and case insensitive filesystem */
public final class C06811b4<Data> implements AbstractC07011bT<File, Data> {
    public final AbstractC06781b1<Data> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull File file, int i, int i2, @NonNull AnonymousClass1cO r7) {
        File file2 = file;
        return new C07091bb(new AnonymousClass1S3(file2), new C06771b0(file2, this.A00));
    }

    public C06811b4(AbstractC06781b1<Data> r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull File file) {
        return true;
    }
}
