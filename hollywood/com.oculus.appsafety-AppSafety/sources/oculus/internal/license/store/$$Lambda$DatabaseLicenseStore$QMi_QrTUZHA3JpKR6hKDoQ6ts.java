package oculus.internal.license.store;

import java.util.function.Consumer;

/* renamed from: oculus.internal.license.store.-$$Lambda$DatabaseLicenseStore$QMi_QrTUZHA3JpKR6hKDo-Q6-ts  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DatabaseLicenseStore$QMi_QrTUZHA3JpKR6hKDoQ6ts implements Consumer {
    public static final /* synthetic */ $$Lambda$DatabaseLicenseStore$QMi_QrTUZHA3JpKR6hKDoQ6ts INSTANCE = new $$Lambda$DatabaseLicenseStore$QMi_QrTUZHA3JpKR6hKDoQ6ts();

    private /* synthetic */ $$Lambda$DatabaseLicenseStore$QMi_QrTUZHA3JpKR6hKDoQ6ts() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((LicenseStoreObserver) obj).onBeginChange();
    }
}
