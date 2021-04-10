package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManagerImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public final class Zn extends AbstractC0049Am {
    @NonNull
    public final AR A00;
    @NonNull
    public final Zo A01;

    @Override // X.AbstractC0049Am
    public final void A00() {
        C00072p<LoaderManagerImpl.LoaderInfo> r2 = this.A01.A00;
        if (0 < r2.A01()) {
            if (r2.A01) {
                C00072p.A00(r2);
            }
            throw null;
        }
    }

    @Override // X.AbstractC0049Am
    @Deprecated
    public final void A01(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        Zo zo = this.A01;
        if (zo.A00.A01() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            C00072p<LoaderManagerImpl.LoaderInfo> r1 = zo.A00;
            if (0 < r1.A01()) {
                if (r1.A01) {
                    C00072p.A00(r1);
                }
                printWriter.print(str);
                printWriter.print("  #");
                C00072p<LoaderManagerImpl.LoaderInfo> r12 = zo.A00;
                if (r12.A01) {
                    C00072p.A00(r12);
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
        AR ar = this.A00;
        sb.append(ar.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(ar)));
        sb.append("}}");
        return sb.toString();
    }

    public Zn(@NonNull AR ar, @NonNull C0046Aj aj) {
        this.A00 = ar;
        this.A01 = (Zo) new C0045Ai(aj, Zo.A01).A00(Zo.class);
    }
}
