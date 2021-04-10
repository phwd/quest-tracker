package X;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* renamed from: X.Xd  reason: case insensitive filesystem */
public final class C0235Xd implements RU {
    public AtomicReference<String> A00 = new AtomicReference<>("");

    @Override // X.RU
    public final void deleteOldUserData(int i) {
    }

    @Override // X.RU
    @Nullable
    public final RW getLatestHandle() {
        return null;
    }

    @Override // X.RU
    @Nullable
    public final Re getNewOverridesTableIfExists() {
        return null;
    }

    @Override // X.RU
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.RU
    public final boolean isValid() {
        return false;
    }

    @Override // X.RU
    public final void logExposure(String str, String str2, String str3) {
    }

    @Override // X.RU
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    @Override // X.RU
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.RU
    public final String syncFetchReason() {
        return AnonymousClass06.A04("MobileConfigManagerHolderNoop: ", this.A00.get());
    }

    @Override // X.RU
    public final boolean updateConfigs() {
        return false;
    }
}
