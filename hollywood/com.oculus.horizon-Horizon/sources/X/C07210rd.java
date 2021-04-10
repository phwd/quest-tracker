package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManagerImpl;
import com.facebook.FacebookSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: X.0rd  reason: invalid class name and case insensitive filesystem */
public final class C07210rd extends AbstractC00540Am {
    @NonNull
    public final AnonymousClass0AR A00;
    @NonNull
    public final C07220re A01;

    @Override // X.AbstractC00540Am
    public final void A00() {
        C000702p<LoaderManagerImpl.LoaderInfo> r2 = this.A01.A00;
        if (0 < r2.A01()) {
            if (r2.A01) {
                C000702p.A00(r2);
            }
            throw null;
        }
    }

    @Override // X.AbstractC00540Am
    @Deprecated
    public final void A01(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        C07220re r3 = this.A01;
        if (r3.A00.A01() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            C000702p<LoaderManagerImpl.LoaderInfo> r1 = r3.A00;
            if (0 < r1.A01()) {
                if (r1.A01) {
                    C000702p.A00(r1);
                }
                printWriter.print(str);
                printWriter.print("  #");
                C000702p<LoaderManagerImpl.LoaderInfo> r12 = r3.A00;
                if (r12.A01) {
                    C000702p.A00(r12);
                }
                printWriter.print(r12.A02[0]);
                printWriter.print(": ");
                throw null;
            }
        }
    }

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder((int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        AnonymousClass0AR r1 = this.A00;
        sb.append(r1.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(r1)));
        sb.append("}}");
        return sb.toString();
    }

    public C07210rd(@NonNull AnonymousClass0AR r3, @NonNull C00520Aj r4) {
        this.A00 = r3;
        this.A01 = (C07220re) new C00510Ai(r4, C07220re.A01).A00(C07220re.class);
    }
}
