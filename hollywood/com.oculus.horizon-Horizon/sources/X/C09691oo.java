package X;

import com.facebook.gk.store.NamesFileContent;
import com.facebook.gk.store.StateFileContent;
import java.io.File;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1oo  reason: invalid class name and case insensitive filesystem */
public final class C09691oo {
    public final AnonymousClass12I A00;
    public final C09681on<NamesFileContent> A01;
    public final C09681on<StateFileContent> A02;
    public final C09731os A03;
    public final File A04;

    public C09691oo(AnonymousClass12I r5, File file) {
        C09681on<StateFileContent> r3 = new C09681on<>(new C09701op(), file, "gk_state");
        C09681on<NamesFileContent> r0 = new C09681on<>(new C09711oq(), file, "gk_names");
        this.A00 = r5;
        this.A02 = r3;
        this.A01 = r0;
        this.A04 = file;
        this.A03 = new C09731os(new File(file, "file_lock"));
    }
}
