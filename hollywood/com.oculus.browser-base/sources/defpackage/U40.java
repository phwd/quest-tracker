package defpackage;

import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;

/* renamed from: U40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class U40 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2294e50 f9004a;

    public U40(C2294e50 e50) {
        this.f9004a = e50;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f9004a.e = (KeyboardAccessoryView) obj;
    }
}
