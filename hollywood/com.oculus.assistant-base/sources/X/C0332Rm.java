package X;

import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;

/* renamed from: X.Rm  reason: case insensitive filesystem */
public final class C0332Rm {
    public QT A00;
    public final SparseIntArray A01;

    public C0332Rm() {
        this(GoogleApiAvailability.A00);
    }

    public C0332Rm(QT qt) {
        this.A01 = new SparseIntArray();
        RZ.A01(qt);
        this.A00 = qt;
    }
}
