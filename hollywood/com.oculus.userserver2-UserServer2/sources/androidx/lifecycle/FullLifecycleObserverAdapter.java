package androidx.lifecycle;

import X.Bs;
import X.EnumC0039Bo;
import X.Td;
import androidx.annotation.NonNull;

public class FullLifecycleObserverAdapter implements Td {
    public final Td A00;

    @Override // X.Td
    public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
        switch (bo.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                throw null;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                Td td = this.A00;
                if (td != null) {
                    td.A2i(bs, bo);
                    return;
                }
                return;
        }
    }
}
