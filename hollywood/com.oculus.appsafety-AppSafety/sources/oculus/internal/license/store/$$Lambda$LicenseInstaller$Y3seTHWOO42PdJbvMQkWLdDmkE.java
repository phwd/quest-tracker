package oculus.internal.license.store;

import android.util.Log;
import java.util.function.Consumer;

/* renamed from: oculus.internal.license.store.-$$Lambda$LicenseInstaller$Y3seTHWOO42-PdJbvMQkWLdDmkE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LicenseInstaller$Y3seTHWOO42PdJbvMQkWLdDmkE implements Consumer {
    public static final /* synthetic */ $$Lambda$LicenseInstaller$Y3seTHWOO42PdJbvMQkWLdDmkE INSTANCE = new $$Lambda$LicenseInstaller$Y3seTHWOO42PdJbvMQkWLdDmkE();

    private /* synthetic */ $$Lambda$LicenseInstaller$Y3seTHWOO42PdJbvMQkWLdDmkE() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Log.e(LicenseInstaller.TAG, String.format("Error inserting license", new Object[0]), (Exception) obj);
    }
}
