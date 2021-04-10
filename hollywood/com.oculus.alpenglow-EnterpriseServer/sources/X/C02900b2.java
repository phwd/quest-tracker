package X;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* renamed from: X.0b2  reason: invalid class name and case insensitive filesystem */
public final class C02900b2 implements AnonymousClass0ST {
    public AtomicReference<String> A00 = new AtomicReference<>("");

    @Override // X.AnonymousClass0ST
    public final void deleteOldUserData(int i) {
    }

    @Override // X.AnonymousClass0ST
    @Nullable
    public final AnonymousClass0SV getLatestHandle() {
        return null;
    }

    @Override // X.AnonymousClass0ST
    @Nullable
    public final AnonymousClass0Sr getNewOverridesTableIfExists() {
        return null;
    }

    @Override // X.AnonymousClass0ST
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.AnonymousClass0ST
    public final boolean isValid() {
        return false;
    }

    @Override // X.AnonymousClass0ST
    public final void logExposure(String str, String str2, String str3) {
    }

    @Override // X.AnonymousClass0ST
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    @Override // X.AnonymousClass0ST
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.AnonymousClass0ST
    public final String syncFetchReason() {
        return AnonymousClass006.A05("MobileConfigManagerHolderNoop: ", this.A00.get());
    }

    @Override // X.AnonymousClass0ST
    public final boolean updateConfigs() {
        return false;
    }
}
