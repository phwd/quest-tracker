package X;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint({"RestrictedApi"})
public final class Bz {
    public boolean A00 = true;
    @Nullable
    public Bundle A01;
    public AnonymousClass2U<String, AbstractC0067By> A02 = new AnonymousClass2U<>();
    public boolean A03;
    public Zh A04;

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

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<+LX/Bx;>;)V */
    @MainThread
    public final void A01() {
        if (this.A00) {
            if (this.A04 == null) {
                this.A04 = new Zh(this);
            }
            try {
                Zt.class.getDeclaredConstructor(new Class[0]);
                Zh zh = this.A04;
                zh.A00.add(Zt.class.getName());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(AnonymousClass06.A05("Class", Zt.class.getSimpleName(), " must have default constructor in order to be automatically recreated"), e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }
}
