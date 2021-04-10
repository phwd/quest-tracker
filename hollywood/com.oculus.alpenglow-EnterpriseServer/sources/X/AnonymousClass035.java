package X;

import android.content.Context;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

/* renamed from: X.035  reason: invalid class name */
public final class AnonymousClass035 {
    public static AnonymousClass035 A03;
    public final Context A00;
    public final LocationManager A01;
    public final AnonymousClass034 A02 = new AnonymousClass034();

    @VisibleForTesting
    public AnonymousClass035(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.A00 = context;
        this.A01 = locationManager;
    }
}
