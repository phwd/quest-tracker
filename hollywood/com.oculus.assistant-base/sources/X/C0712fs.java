package X;

import com.facebook.assistant.oacr.Oacr;
import com.facebook.assistant.oacr.OacrMCName;

/* renamed from: X.fs  reason: case insensitive filesystem */
public final class C0712fs extends Oacr.MobileConfigProvider {
    public final /* synthetic */ C0740gP A00;

    public C0712fs(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.Oacr.MobileConfigProvider
    public final void logExposure(OacrMCName oacrMCName) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "logging mobile config %s", oacrMCName.toString());
    }
}
