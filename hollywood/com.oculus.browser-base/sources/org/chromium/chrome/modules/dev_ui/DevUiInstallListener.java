package org.chromium.chrome.modules.dev_ui;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DevUiInstallListener implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public long f10809a;

    public DevUiInstallListener(long j) {
        this.f10809a = j;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        long j = this.f10809a;
        if (j != 0) {
            N.MaWzS2R6(j, z);
        }
    }

    public final void onNativeDestroy() {
        this.f10809a = 0;
    }
}
