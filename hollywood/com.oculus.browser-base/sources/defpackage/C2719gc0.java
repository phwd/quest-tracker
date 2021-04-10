package defpackage;

import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;

/* renamed from: gc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2719gc0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ManualFillingComponentBridge f10006a;

    public C2719gc0(ManualFillingComponentBridge manualFillingComponentBridge) {
        this.f10006a = manualFillingComponentBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2636g50 g50 = (C2636g50) obj;
        this.f10006a.c();
    }
}
