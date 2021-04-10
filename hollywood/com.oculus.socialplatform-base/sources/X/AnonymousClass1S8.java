package X;

import android.content.Context;
import androidx.annotation.NonNull;

/* renamed from: X.1S8  reason: invalid class name */
public final class AnonymousClass1S8 implements AnonymousClass1S9 {
    @Override // X.AnonymousClass1S9
    @NonNull
    public final AnonymousClass0Dx A1p(@NonNull Context context, @NonNull AnonymousClass1SD r3) {
        if (AnonymousClass04O.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0) {
            return new AnonymousClass1S4(context, r3);
        }
        return new AnonymousClass1SA();
    }
}
