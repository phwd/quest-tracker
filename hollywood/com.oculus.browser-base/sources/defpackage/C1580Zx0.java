package defpackage;

import J.N;
import org.chromium.chrome.browser.password_manager.PasswordGenerationDialogBridge;

/* renamed from: Zx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1580Zx0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PasswordGenerationDialogBridge f9388a;

    public C1580Zx0(PasswordGenerationDialogBridge passwordGenerationDialogBridge) {
        this.f9388a = passwordGenerationDialogBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PasswordGenerationDialogBridge passwordGenerationDialogBridge = this.f9388a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        long j = passwordGenerationDialogBridge.f10738a;
        if (j != 0) {
            if (booleanValue) {
                N.M1W3B6UT(j, passwordGenerationDialogBridge, passwordGenerationDialogBridge.c);
            } else {
                N.Ml5BeqqW(j, passwordGenerationDialogBridge);
            }
            C1760ay0 ay0 = passwordGenerationDialogBridge.b;
            ay0.f9503a.b(ay0.d, 3);
        }
    }
}
