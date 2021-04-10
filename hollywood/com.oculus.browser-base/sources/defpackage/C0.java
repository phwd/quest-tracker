package defpackage;

import org.chromium.chrome.browser.keyboard_accessory.sheet_component.AccessorySheetView;

/* renamed from: C0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0 implements Runnable {
    public final AccessorySheetView F;
    public final int G;

    public C0(AccessorySheetView accessorySheetView, int i) {
        this.F = accessorySheetView;
        this.G = i;
    }

    public void run() {
        AccessorySheetView accessorySheetView = this.F;
        accessorySheetView.F.x(this.G);
    }
}
