package defpackage;

import J.N;
import org.chromium.chrome.browser.password_manager.CredentialLeakDialogBridge;

/* renamed from: jB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3165jB extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final CredentialLeakDialogBridge f10193a;

    public C3165jB(CredentialLeakDialogBridge credentialLeakDialogBridge) {
        this.f10193a = credentialLeakDialogBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        CredentialLeakDialogBridge credentialLeakDialogBridge = this.f10193a;
        int intValue = ((Integer) obj).intValue();
        long j = credentialLeakDialogBridge.f10737a;
        if (j != 0) {
            if (intValue == 1) {
                N.Mmumo5h_(j, credentialLeakDialogBridge);
            } else if (intValue != 2) {
                N.MEu0f3Ks(j, credentialLeakDialogBridge);
            } else {
                N.M2h75In5(j, credentialLeakDialogBridge);
            }
        }
    }
}
