package X;

import com.google.gson.internal.bind.TypeAdapters$35;
import java.io.IOException;

/* renamed from: X.0WC  reason: invalid class name */
public class AnonymousClass0WC extends AnonymousClass0Bd<T1> {
    public final /* synthetic */ TypeAdapters$35 A00;
    public final /* synthetic */ Class A01;

    public AnonymousClass0WC(TypeAdapters$35 typeAdapters$35, Class cls) {
        this.A00 = typeAdapters$35;
        this.A01 = cls;
    }

    @Override // X.AnonymousClass0Bd
    public final T1 A02(AnonymousClass0Fo r6) throws IOException {
        T1 t1 = (T1) this.A00.A00.A02(r6);
        if (t1 != null) {
            Class cls = this.A01;
            if (!cls.isInstance(t1)) {
                throw new AnonymousClass0XQ(AnonymousClass006.A08("Expected a ", cls.getName(), " but was ", t1.getClass().getName()));
            }
        }
        return t1;
    }

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, T1 t1) throws IOException {
        this.A00.A00.A03(r2, t1);
    }
}
