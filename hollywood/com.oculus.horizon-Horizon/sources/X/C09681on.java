package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1on  reason: invalid class name and case insensitive filesystem */
public final class C09681on<T> {
    public final AbstractC09721or<T> A00;
    public final File A01;
    public final File A02;
    public final File A03;

    public C09681on(AbstractC09721or<T> r5, File file, String str) {
        File file2 = new File(file, str);
        File file3 = new File(file, AnonymousClass006.A05(str, ".tmp"));
        File file4 = new File(file, AnonymousClass006.A05(str, ".old"));
        this.A00 = r5;
        this.A02 = file2;
        this.A03 = file3;
        this.A01 = file4;
    }
}
