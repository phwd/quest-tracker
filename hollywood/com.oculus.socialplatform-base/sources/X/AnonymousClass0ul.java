package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManagerImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: X.0ul  reason: invalid class name */
public final class AnonymousClass0ul extends AbstractC00490An {
    @NonNull
    public final AnonymousClass0AS A00;
    @NonNull
    public final AnonymousClass0un A01;

    @Override // X.AbstractC00490An
    public final void A00() {
        C000602w<LoaderManagerImpl.LoaderInfo> r2 = this.A01.A00;
        if (0 < r2.A01()) {
            if (r2.A01) {
                C000602w.A00(r2);
            }
            throw new NullPointerException("markForRedelivery");
        }
    }

    @Override // X.AbstractC00490An
    @Deprecated
    public final void A01(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        AnonymousClass0un r3 = this.A01;
        if (r3.A00.A01() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            C000602w<LoaderManagerImpl.LoaderInfo> r1 = r3.A00;
            if (0 < r1.A01()) {
                if (r1.A01) {
                    C000602w.A00(r1);
                }
                printWriter.print(str);
                printWriter.print("  #");
                C000602w<LoaderManagerImpl.LoaderInfo> r12 = r3.A00;
                if (r12.A01) {
                    C000602w.A00(r12);
                }
                printWriter.print(r12.A02[0]);
                printWriter.print(": ");
                throw new NullPointerException("toString");
            }
        }
    }

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        AnonymousClass0AS r1 = this.A00;
        sb.append(r1.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(r1)));
        sb.append("}}");
        return sb.toString();
    }

    public AnonymousClass0ul(@NonNull AnonymousClass0AS r3, @NonNull C00470Ak r4) {
        this.A00 = r3;
        this.A01 = (AnonymousClass0un) new C00460Aj(r4, AnonymousClass0un.A01).A00(AnonymousClass0un.class);
    }
}
