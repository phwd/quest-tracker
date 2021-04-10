package X;

import com.google.android.gms.common.api.Status;

/* renamed from: X.sD  reason: case insensitive filesystem */
public final class C1089sD implements Qz {
    @Override // X.Qz
    public final Exception A2Q(Status status) {
        if (status.A01 != null) {
            return new C1088sC(status);
        }
        return new C0308Qe(status);
    }
}
