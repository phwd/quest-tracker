package defpackage;

import org.chromium.chrome.browser.safe_browsing.SafeBrowsingPasswordReuseDialogBridge;

/* renamed from: mO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3714mO0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SafeBrowsingPasswordReuseDialogBridge f10419a;

    public C3714mO0(SafeBrowsingPasswordReuseDialogBridge safeBrowsingPasswordReuseDialogBridge) {
        this.f10419a = safeBrowsingPasswordReuseDialogBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SafeBrowsingPasswordReuseDialogBridge safeBrowsingPasswordReuseDialogBridge = this.f10419a;
        ((Integer) obj).intValue();
        safeBrowsingPasswordReuseDialogBridge.a();
    }
}
