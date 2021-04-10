package defpackage;

import J.N;
import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;

/* renamed from: hc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2890hc0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ManualFillingComponentBridge f10085a;
    public final int b;

    public C2890hc0(ManualFillingComponentBridge manualFillingComponentBridge, int i) {
        this.f10085a = manualFillingComponentBridge;
        this.b = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ManualFillingComponentBridge manualFillingComponentBridge = this.f10085a;
        N.M2tSygph(manualFillingComponentBridge.d, manualFillingComponentBridge, this.b, ((Boolean) obj).booleanValue());
    }
}
