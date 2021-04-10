package defpackage;

import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: Vk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Vk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ToolbarPhone f9102a;

    public Vk1(ToolbarPhone toolbarPhone) {
        this.f9102a = toolbarPhone;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ToolbarPhone toolbarPhone = this.f9102a;
        toolbarPhone.F0 = ((Float) obj).floatValue();
        toolbarPhone.B0();
        toolbarPhone.A0();
    }
}
