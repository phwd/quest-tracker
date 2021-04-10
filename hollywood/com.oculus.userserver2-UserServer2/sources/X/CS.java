package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManagerImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class CS {
    @Deprecated
    public final void A00(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        TU tu = ((TT) this).A00;
        if (tu.A00.A01() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            AnonymousClass3D<LoaderManagerImpl.LoaderInfo> r1 = tu.A00;
            if (0 < r1.A01()) {
                if (r1.A01) {
                    AnonymousClass3D.A00(r1);
                }
                printWriter.print(str);
                printWriter.print("  #");
                AnonymousClass3D<LoaderManagerImpl.LoaderInfo> r12 = tu.A00;
                if (r12.A01) {
                    AnonymousClass3D.A00(r12);
                }
                printWriter.print(r12.A02[0]);
                printWriter.print(": ");
                throw null;
            }
        }
    }
}
