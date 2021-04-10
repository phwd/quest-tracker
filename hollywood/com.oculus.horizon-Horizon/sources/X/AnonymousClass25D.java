package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.capture.VideoCapture;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.25D  reason: invalid class name */
public class AnonymousClass25D implements AnonymousClass1z3 {
    public AnonymousClass25E mCameraCoreConfig = new AnonymousClass25E(new AnonymousClass25C());

    @Override // X.AnonymousClass1z3
    public final boolean A90() {
        return false;
    }

    @Override // X.AnonymousClass1z3
    public final int A30() {
        return this.mCameraCoreConfig.A30();
    }

    @Override // X.AnonymousClass1z3
    public final int A31() {
        return this.mCameraCoreConfig.A31();
    }

    @Override // X.AnonymousClass1z3
    public final int A32() {
        return this.mCameraCoreConfig.A32();
    }

    @Override // X.AnonymousClass1z3
    public final int A33() {
        return this.mCameraCoreConfig.A33();
    }

    @Override // X.AnonymousClass1z3
    @Nullable
    public final String A3M() {
        return this.mCameraCoreConfig.A3M();
    }

    @Override // X.AnonymousClass1z3
    public final int A4U() {
        return this.mCameraCoreConfig.A4U();
    }

    @Override // X.AnonymousClass1z3
    public final boolean A91() {
        if (!(this instanceof VideoCapture.AnonymousClass6)) {
            return this.mCameraCoreConfig.A91();
        }
        return true;
    }

    @Override // X.AnonymousClass1z3
    public final boolean A92() {
        return this.mCameraCoreConfig.A92();
    }
}
