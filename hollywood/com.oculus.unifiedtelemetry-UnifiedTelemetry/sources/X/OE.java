package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OE<T> {
    public final OD<T> A00;
    public final File A01;
    public final File A02;
    public final File A03;

    public OE(OD<T> od, File file, String str) {
        File file2 = new File(file, str);
        File file3 = new File(file, AnonymousClass06.A04(str, ".tmp"));
        File file4 = new File(file, AnonymousClass06.A04(str, ".old"));
        this.A00 = od;
        this.A02 = file2;
        this.A03 = file3;
        this.A01 = file4;
    }
}
