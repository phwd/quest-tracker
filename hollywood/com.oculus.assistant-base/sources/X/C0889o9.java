package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.o9  reason: case insensitive filesystem */
public final class C0889o9 implements AbstractC0162Fh {
    public AtomicReference A00 = new AtomicReference(OacrConstants.AUTO_SPEECH_DOMAIN);

    @Override // X.AbstractC0162Fh
    public final AbstractC0163Fj getLatestHandle() {
        return null;
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0168Ft getNewOverridesTable() {
        return null;
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0168Ft getNewOverridesTableIfExists() {
        return null;
    }

    @Override // X.AbstractC0162Fh
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.AbstractC0162Fh
    public final boolean isValid() {
        return false;
    }

    @Override // X.AbstractC0162Fh
    public final void logExposure(String str, String str2, String str3) {
    }

    @Override // X.AbstractC0162Fh
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    @Override // X.AbstractC0162Fh
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.AbstractC0162Fh
    public final String syncFetchReason() {
        return AnonymousClass08.A04("MobileConfigManagerHolderNoop: ", (String) this.A00.get());
    }
}
