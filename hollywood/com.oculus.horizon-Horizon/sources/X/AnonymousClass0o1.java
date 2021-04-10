package X;

import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* renamed from: X.0o1  reason: invalid class name */
public final class AnonymousClass0o1 implements AnonymousClass0RX {
    public AtomicReference<String> A00 = new AtomicReference<>("");

    @Override // X.AnonymousClass0RX
    public final void deleteOldUserData(int i) {
    }

    @Override // X.AnonymousClass0RX
    @Nullable
    public final AnonymousClass0RZ getLatestHandle() {
        return null;
    }

    @Override // X.AnonymousClass0RX
    @Nullable
    public final AnonymousClass0Ri getNewOverridesTableIfExists() {
        return null;
    }

    @Override // X.AnonymousClass0RX
    public final boolean isConsistencyLoggingNeeded(AnonymousClass0RO r2) {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final boolean isValid() {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final void logConfigs(String str, AnonymousClass0RO r2, Map<String, String> map) {
    }

    @Override // X.AnonymousClass0RX
    public final void logExposure(String str, String str2, String str3) {
    }

    @Override // X.AnonymousClass0RX
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    @Override // X.AnonymousClass0RX
    public final boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener) {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final boolean saveCurrentParamsMapToDisk() {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final boolean updateConfigsSynchronouslyWithDefaultUpdater(int i) {
        return false;
    }

    @Override // X.AnonymousClass0RX
    public final String syncFetchReason() {
        return AnonymousClass006.A05("MobileConfigManagerHolderNoop: ", this.A00.get());
    }

    @Override // X.AnonymousClass0RX
    public final boolean updateConfigs() {
        return false;
    }
}
