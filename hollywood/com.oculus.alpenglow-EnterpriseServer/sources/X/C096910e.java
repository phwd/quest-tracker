package X;

import com.facebook.gk.store.NamesFileContent;
import com.facebook.gk.store.StateFileContent;
import java.io.File;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.10e  reason: invalid class name and case insensitive filesystem */
public final class C096910e {
    public final AnonymousClass160 A00;
    public final C09620zl<NamesFileContent> A01;
    public final C09620zl<StateFileContent> A02;
    public final AnonymousClass10g A03;
    public final File A04;

    public C096910e(AnonymousClass160 r5, File file) {
        C09620zl<StateFileContent> r3 = new C09620zl<>(new C096810d(), file, "gk_state");
        C09620zl<NamesFileContent> r0 = new C09620zl<>(new AnonymousClass10f(), file, "gk_names");
        this.A00 = r5;
        this.A02 = r3;
        this.A01 = r0;
        this.A04 = file;
        this.A03 = new AnonymousClass10g(new File(file, "file_lock"));
    }
}
