package X;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint({"RestrictedApi"})
/* renamed from: X.0GJ  reason: invalid class name */
public final class AnonymousClass0GJ {
    public boolean A00 = true;
    @Nullable
    public Bundle A01;
    public C005905t<String, AnonymousClass0GI> A02 = new C005905t<>();
    public boolean A03;
    public C03390cD A04;

    @Nullable
    @MainThread
    public final Bundle A00(@NonNull String str) {
        if (this.A03) {
            Bundle bundle = this.A01;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            this.A01.remove(str);
            if (this.A01.isEmpty()) {
                this.A01 = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<+LX/0GH;>;)V */
    @MainThread
    public final void A01() {
        if (this.A00) {
            if (this.A04 == null) {
                this.A04 = new C03390cD(this);
            }
            try {
                C03510cZ.class.getDeclaredConstructor(new Class[0]);
                C03390cD r0 = this.A04;
                r0.A00.add(C03510cZ.class.getName());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(AnonymousClass006.A07("Class", C03510cZ.class.getSimpleName(), " must have default constructor in order to be automatically recreated"), e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }
}
