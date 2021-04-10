package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;

/* renamed from: hz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2958hz extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f10116a;

    public C2958hz(Callback callback) {
        this.f10116a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ContextMenuNativeDelegateImpl.ImageCallbackResult imageCallbackResult = (ContextMenuNativeDelegateImpl.ImageCallbackResult) obj;
        AbstractC1667aU0.b(imageCallbackResult.f10640a, imageCallbackResult.b, this.f10116a);
    }
}
