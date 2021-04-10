package X;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint({"RestrictedApi"})
/* renamed from: X.0C4  reason: invalid class name */
public final class AnonymousClass0C4 {
    public boolean A00 = true;
    @Nullable
    public Bundle A01;
    public AnonymousClass02b<String, AnonymousClass0C3> A02 = new AnonymousClass02b<>();
    public boolean A03;
    public C05190ud A04;

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

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<+LX/0C2;>;)V */
    @MainThread
    public final void A01() {
        if (this.A00) {
            if (this.A04 == null) {
                this.A04 = new C05190ud(this);
            }
            try {
                AnonymousClass0us.class.getDeclaredConstructor(new Class[0]);
                C05190ud r0 = this.A04;
                r0.A00.add(AnonymousClass0us.class.getName());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(AnonymousClass006.A09("Class", AnonymousClass0us.class.getSimpleName(), " must have default constructor in order to be automatically recreated"), e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }
}
