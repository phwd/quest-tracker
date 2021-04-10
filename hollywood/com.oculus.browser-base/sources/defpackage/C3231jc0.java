package defpackage;

import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;

/* renamed from: jc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3231jc0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ManualFillingComponentBridge f10217a;
    public final int b;

    public C3231jc0(ManualFillingComponentBridge manualFillingComponentBridge, int i) {
        this.f10217a = manualFillingComponentBridge;
        this.b = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2807h50 h50 = (C2807h50) obj;
        this.f10217a.b(this.b);
    }
}
