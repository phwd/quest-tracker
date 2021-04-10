package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManagerImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: X.0cT  reason: invalid class name and case insensitive filesystem */
public final class C03460cT extends AbstractC01180Dv {
    @NonNull
    public final AbstractC01030Da A00;
    @NonNull
    public final C03470cU A01;

    @Override // X.AbstractC01180Dv
    public final void A00() {
        AnonymousClass06E<LoaderManagerImpl.LoaderInfo> r2 = this.A01.A00;
        if (0 < r2.A01()) {
            if (r2.A01) {
                AnonymousClass06E.A00(r2);
            }
            throw null;
        }
    }

    @Override // X.AbstractC01180Dv
    @Deprecated
    public final void A01(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        C03470cU r3 = this.A01;
        if (r3.A00.A01() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            AnonymousClass06E<LoaderManagerImpl.LoaderInfo> r1 = r3.A00;
            if (0 < r1.A01()) {
                if (r1.A01) {
                    AnonymousClass06E.A00(r1);
                }
                printWriter.print(str);
                printWriter.print("  #");
                AnonymousClass06E<LoaderManagerImpl.LoaderInfo> r12 = r3.A00;
                if (r12.A01) {
                    AnonymousClass06E.A00(r12);
                }
                printWriter.print(r12.A02[0]);
                printWriter.print(": ");
                throw null;
            }
        }
    }

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        AbstractC01030Da r1 = this.A00;
        sb.append(r1.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(r1)));
        sb.append("}}");
        return sb.toString();
    }

    public C03460cT(@NonNull AbstractC01030Da r3, @NonNull C01150Ds r4) {
        this.A00 = r3;
        this.A01 = (C03470cU) new C01140Dr(r4, C03470cU.A01).A00(C03470cU.class);
    }
}
