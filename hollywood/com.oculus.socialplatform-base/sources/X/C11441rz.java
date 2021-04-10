package X;

import android.content.Context;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

/* renamed from: X.1rz  reason: invalid class name and case insensitive filesystem */
public final class C11441rz {
    public static C11441rz A03;
    public final Context A00;
    public final LocationManager A01;
    public final C11551sA A02 = new C11551sA();

    @VisibleForTesting
    public C11441rz(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.A00 = context;
        this.A01 = locationManager;
    }
}
