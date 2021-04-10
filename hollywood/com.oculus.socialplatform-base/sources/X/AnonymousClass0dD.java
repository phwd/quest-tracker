package X;

import com.google.gson.internal.bind.TypeAdapters$35;
import java.io.IOException;

/* renamed from: X.0dD  reason: invalid class name */
public class AnonymousClass0dD extends AnonymousClass13Y<T1> {
    public final /* synthetic */ TypeAdapters$35 A00;
    public final /* synthetic */ Class A01;

    public AnonymousClass0dD(TypeAdapters$35 typeAdapters$35, Class cls) {
        this.A00 = typeAdapters$35;
        this.A01 = cls;
    }

    @Override // X.AnonymousClass13Y
    public final T1 A02(AnonymousClass14I r6) throws IOException {
        T1 t1 = (T1) this.A00.A00.A02(r6);
        if (t1 != null) {
            Class cls = this.A01;
            if (!cls.isInstance(t1)) {
                throw new AnonymousClass0eR(AnonymousClass006.A0B("Expected a ", cls.getName(), " but was ", t1.getClass().getName()));
            }
        }
        return t1;
    }

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, T1 t1) throws IOException {
        this.A00.A00.A03(r2, t1);
    }
}
