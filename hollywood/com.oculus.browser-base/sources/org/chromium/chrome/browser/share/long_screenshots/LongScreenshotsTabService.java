package org.chromium.chrome.browser.share.long_screenshots;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LongScreenshotsTabService implements AbstractC0156Cm0 {

    /* renamed from: a  reason: collision with root package name */
    public C0387Gh f10757a;
    public long b;

    public LongScreenshotsTabService(long j) {
        this.b = j;
    }

    @Override // defpackage.AbstractC0156Cm0
    public long a() {
        return this.b;
    }

    public final void onNativeDestroyed() {
        this.b = 0;
    }

    public final void processCaptureTabStatus(int i) {
        C0387Gh gh = this.f10757a;
        if (gh != null) {
            gh.a(null, i);
        }
    }

    public final void processPaintPreviewResponse(byte[] bArr) {
        if (this.f10757a != null) {
            try {
                this.f10757a.a((C1149Sv0) AbstractC2360eV.k(C1149Sv0.e, bArr), 1);
            } catch (Exception unused) {
                processCaptureTabStatus(8);
            }
        }
    }
}
