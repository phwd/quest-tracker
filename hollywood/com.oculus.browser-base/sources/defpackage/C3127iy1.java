package defpackage;

import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;

/* renamed from: iy1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3127iy1 implements WebsitePreferenceBridge.StorageInfoClearedCallback {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f10179a;
    public final AbstractC3298jy1 b;

    public C3127iy1(int[] iArr, AbstractC3298jy1 jy1) {
        this.f10179a = iArr;
        this.b = jy1;
    }

    @Override // org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge.StorageInfoClearedCallback
    public void onStorageInfoCleared() {
        int[] iArr = this.f10179a;
        AbstractC3298jy1 jy1 = this.b;
        int i = iArr[0] - 1;
        iArr[0] = i;
        if (i == 0) {
            jy1.a();
        }
    }
}
