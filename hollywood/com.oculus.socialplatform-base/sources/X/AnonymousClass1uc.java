package X;

import androidx.annotation.NonNull;

/* renamed from: X.1uc  reason: invalid class name */
public class AnonymousClass1uc implements AbstractC12101uv {
    public transient AnonymousClass1ug mCallbacks;

    @Override // X.AbstractC12101uv
    public void addOnPropertyChangedCallback(@NonNull AbstractC12091uu r2) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new AnonymousClass1ug();
            }
        }
        this.mCallbacks.A03(r2);
    }

    public void notifyChange() {
        AnonymousClass1ug r0;
        synchronized (this) {
            r0 = this.mCallbacks;
        }
        if (r0 != null) {
            this.mCallbacks.A05(this, 0);
        }
    }

    public void notifyPropertyChanged(int i) {
        AnonymousClass1ug r0;
        synchronized (this) {
            r0 = this.mCallbacks;
        }
        if (r0 != null) {
            this.mCallbacks.A05(this, i);
        }
    }

    @Override // X.AbstractC12101uv
    public void removeOnPropertyChangedCallback(@NonNull AbstractC12091uu r2) {
        AnonymousClass1ug r0;
        synchronized (this) {
            r0 = this.mCallbacks;
        }
        if (r0 != null) {
            this.mCallbacks.A04(r2);
        }
    }
}
