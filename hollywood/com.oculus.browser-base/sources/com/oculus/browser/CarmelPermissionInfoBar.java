package com.oculus.browser;

import android.util.Log;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CarmelPermissionInfoBar implements AbstractC3834n6 {
    public long F;

    public CarmelPermissionInfoBar(long j) {
        this.F = j;
    }

    public static CarmelPermissionInfoBar create(long j) {
        return new CarmelPermissionInfoBar(j);
    }

    @Override // defpackage.AbstractC3834n6
    public void e() {
        Log.i("CarmelPermissionInfoBar", "onAndroidPermissionCanceled");
        nativeOnAndroidPermissionCanceled(this.F);
    }

    @Override // defpackage.AbstractC3834n6
    public void g() {
        Log.i("CarmelPermissionInfoBar", "onAndroidPermissionAccepted");
        nativeOnAndroidPermissionAccepted(this.F);
    }

    public final native void nativeOnAndroidPermissionAccepted(long j);

    public final native void nativeOnAndroidPermissionCanceled(long j);

    public void requestAndroidPermissions(Tab tab, int[] iArr) {
        Log.i("CarmelPermissionInfoBar", "requestAndroidPermissions");
        if (!AbstractC4005o6.a(tab.i(), (int[]) iArr.clone(), this)) {
            Log.i("CarmelPermissionInfoBar", "onAndroidPermissionAccepted");
            nativeOnAndroidPermissionAccepted(this.F);
        }
    }
}
